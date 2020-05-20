package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.email
import kotlinx.android.synthetic.main.activity_main.password
import kotlinx.android.synthetic.main.activity_main.signUpButton
import kotlinx.android.synthetic.main.activity_sign_up_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var value : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        auth = FirebaseAuth.getInstance()
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener{
            doLogin()
        }


        signUpButton.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }



    }

    fun doLogin() {
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

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
       super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            if (currentUser.isEmailVerified) {
                val intent = Intent(this, HeadActivity::class.java)
                intent.putExtra("currentUser", "emil macbook pro")
                startActivity(intent)
            }else{Toast.makeText(baseContext, "verifay email adress",
                Toast.LENGTH_SHORT).show()
            }

        }else{Toast.makeText(baseContext, "Inloggning misslyckad Try agian",
            Toast.LENGTH_SHORT).show()
        }
    }



}
