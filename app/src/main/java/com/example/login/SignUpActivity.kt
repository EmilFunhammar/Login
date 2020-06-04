package com.example.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up_layout.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var type : String? = null
    lateinit var workerCheckBox: CheckBox
    lateinit var employerCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_layout)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener{
            status()
            signUpUser()
        }
    }

    fun status(){
        employerCheckBox = findViewById<CheckBox>(R.id.checkEmployerBox)

        if (employerCheckBox.isChecked){
            type = "employer"
        }
        workerCheckBox = findViewById<CheckBox>(R.id.checkWorkerBox)
        if (workerCheckBox.isChecked){
            type = "worker"
        }
        return
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
                                val db = FirebaseFirestore.getInstance()

                                val profil = Profil(workType = type)
                                db.collection("Users").document(user.uid).set(profil)

                                startActivity(Intent(this, MainActivity::class.java))
                                Log.d("mail", "Email sent.")
                            }
                        }

                }else {
                    Toast.makeText(baseContext, "registrering misslyckad. Try agian",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}
