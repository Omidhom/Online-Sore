package com.example.onlinestorekotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class AmountFragment : android.app.DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView =  inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtEnterAmount)
        var btnAddToCart = fragmentView.findViewById<ImageButton>(R.id.btnAddToCart)

        btnAddToCart.setOnClickListener {

            var ptoURL = "http://192.168.1.4:88/OnlineStoreApp/insert_temporary_order.php?email=" +
                    "${Person.email}&product_id=${Person.addToCartProductID}&amount=${edtEnterAmount.text}"
            var requestQueue = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET ,ptoURL , Response.Listener { response ->  

                var intent = Intent(activity ,CartProductsActivity::class.java)
                startActivity(intent)

                
            } , Response.ErrorListener { error ->
                
                
            })

            requestQueue.add(stringRequest)
        }


        return fragmentView

    }


}