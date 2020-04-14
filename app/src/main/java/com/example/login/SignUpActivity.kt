package com.example.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up_layout.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_layout)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener{
            signUpUser()

        }
    }

  private fun signUpUser(){
            if (email.text.toString().isEmpty()) {
                email.error = "ange Emailadress"
                email.requestFocus()
                return
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                email.error = "ange rätt Emailadress"
                email.requestFocus()
                return
            }
            if (password.text.toString().isEmpty()){
                password.error = "ange lösenord"
                password.requestFocus()
                return
            }

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                                    Log.d("mail", "Email sent.")
                                }
                            }

                    } else {
                        Toast.makeText(baseContext, "registrering misslyckad. Try agian",
                            Toast.LENGTH_SHORT).show()
                    }
                }
    }
}
