package com.example.onlinestorekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_finalize_shopping.*

class FinalizeShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_shopping)


        var calculateTotalPriceURL = "http://192.168.1.4:88/OnlineStoreApp/calculate_total_price.php?invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"
        var requestQ = Volley.newRequestQueue(this)
        var stringRequest = StringRequest(Request.Method.GET, calculateTotalPriceURL , Response.Listener { response ->

            btnPaymentProcessing.text = "Pay $response USD via PayPal now."

        }, Response.ErrorListener { error ->

            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Error")
            alertDialog.setMessage(error.message)
            alertDialog.create().show()
        })

        requestQ.add(stringRequest)
    }
}