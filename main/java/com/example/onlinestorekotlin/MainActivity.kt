package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_btnLogin.setOnClickListener {

            var loginURL = "http://192.168.1.4:88/OnlineStoreApp/login_app_user.php?email=" +
                    activity_main_edtLoginEmail.text.toString() + "&pass=" +
                    activity_main_edtLoginPassword.text.toString()

            var requestQ = Volley.newRequestQueue(this)
            var stringRequest = StringRequest(Request.Method.GET , loginURL , Response.Listener { response ->

                if (response == "The user does exist.") {

                    Person.email = activity_main_edtLoginEmail.text.toString()
                    Toast.makeText(this, response , Toast.LENGTH_SHORT).show()
                    var homeIntent = Intent(this, HomeScreen::class.java)
                    startActivity(homeIntent)

                } else{

                    var alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Message")
                    alertDialog.setMessage(response)
                    alertDialog.create().show()
                }

            } , Response.ErrorListener { error ->

                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Error")
                alertDialog.setMessage(error.message)
                alertDialog.create().show()
            })

            requestQ.add(stringRequest)


        }


        activity_main_btnSignUp.setOnClickListener {

            var signUpIntent = Intent(this@MainActivity, SignUpLayout::class.java)
            startActivity(signUpIntent)
        }

    }
}