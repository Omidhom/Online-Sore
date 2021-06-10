package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var brandsURL = "http://192.168.1.4:88/OnlineStoreApp/fetch_brands.php"
        var brandsList = ArrayList<String>()

        var requestQ = Volley.newRequestQueue(this@HomeScreen)
        var jsonAR = JsonArrayRequest(Request.Method.GET , brandsURL , null,
                Response.Listener { response ->

                    for (jsonObject in 0.until(response.length())) {
                        brandsList.add(response.getJSONObject(jsonObject).getString("brand"))

                    }

                    var brandsListAdapter = ArrayAdapter(this@HomeScreen,
                            R.layout.brand_item_text_view , brandsList)
                    brands_listView.adapter = brandsListAdapter

                }, Response.ErrorListener { error ->

            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Error")
            alertDialog.setMessage(error.message)
            alertDialog.create().show()
        })

        requestQ.add(jsonAR)


        brands_listView.setOnItemClickListener { parent, view, position, id ->

            var tappedBrand = brandsList.get(position)
            var intent = Intent(this@HomeScreen , FetchEProductsActivity::class.java)

            intent.putExtra("BRAND" , tappedBrand)
            startActivity(intent)
        }


    }
}