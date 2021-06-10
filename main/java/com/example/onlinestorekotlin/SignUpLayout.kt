package com.example.onlinestorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up_layout.*

class SignUpLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_layout)

        sign_up_layout_btnSignUp.setOnClickListener {

            if(sign_up_layout_edtPassword.text.toString() ==
                    sign_up_layout_edtConfirmPassword.text.toString()) {

                //Registration Process
                var signUpURL = "http://192.168.1.4:88/OnlineStoreApp/join_new_user.php?email=" +
                        sign_up_layout_edtEmail.text.toString() + "&username=" +
                        sign_up_layout_edtUserName.text.toString() + "&pass=" +
                        sign_up_layout_edtPassword.text.toString()

                var requestQ = Volley.newRequestQueue(this@SignUpLayout)
                var stringRequest = StringRequest(Request.Method.GET ,signUpURL , Response.Listener { response ->

                    if(response == "A user with this Email Address already exists") {

                        var alertDialog = AlertDialog.Builder(this)
                        alertDialog.setTitle("Message")
                        alertDialog.setMessage(response)
                        alertDialog.create().show()

                    }else{


//                        var alertDialog = AlertDialog.Builder(this)
//                        alertDialog.setTitle("Message")
//                        alertDialog.setMessage(response)
//                        alertDialog.create().show()

                        Person.email = sign_up_layout_edtEmail.text.toString()

                        Toast.makeText(this,response , Toast.LENGTH_SHORT).show()

                        var homeIntent = Intent(this, HomeScreen::class.java)
                        startActivity(homeIntent)

                    }


                } , Response.ErrorListener { error ->

                    var alertDialog = AlertDialog.Builder(this)
                    alertDialog.setTitle("Error")
                    alertDialog.setMessage(error.message)
                    alertDialog.create().show()


                })

                requestQ.add(stringRequest)

            }else{

                var alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Warning")
                alertDialog.setMessage("Password Mismatch")
                alertDialog.create().show()
            }
        }

        sign_up_layout_btnLogin.setOnClickListener {

            finish()
        }
    }
}