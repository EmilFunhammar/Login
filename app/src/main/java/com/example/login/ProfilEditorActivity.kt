package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfilEditorActivity : AppCompatActivity() {


    lateinit var profilPicture : ImageView
    lateinit var profilName : EditText
    lateinit var editLanguage1 : EditText
    lateinit var editLanguage2: EditText
    lateinit var editLanguage3 : EditText
    lateinit var profilOtherInfo1 : EditText
    lateinit var profilOtherInfo2 : EditText
    lateinit var profilOtherInfo3 : EditText
    lateinit var profilOtherInfo4 : EditText
    lateinit var profilDescription : EditText
    lateinit var db :FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_editor)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser



            val saveButton = findViewById<Button>(R.id.save_ButtonEdit)
            saveButton.setOnClickListener {
               // addProfilInfo()
                println("David : aaaa")
                saveProfilInformation()
                intent = Intent(this, ProfilActivity::class.java)
                startActivity(intent)
            }

                /*profilPicture = findViewById<ImageView>(R.id.profilPicture)*/
                profilName = findViewById<EditText>(R.id.editProfilName)
                editLanguage1 = findViewById<EditText>(R.id.lang_Edit_Text1)
                editLanguage2 = findViewById<EditText>(R.id.lang_Edit_Text2)
                editLanguage3 = findViewById<EditText>(R.id.lang_Edit_Text3)
                profilOtherInfo1 = findViewById<EditText>(R.id.other_InfoEdit1)
                profilOtherInfo2 = findViewById<EditText>(R.id.other_InfoEdit2)
                profilOtherInfo3 = findViewById<EditText>(R.id.other_InfoEdit3)
                profilOtherInfo4 = findViewById<EditText>(R.id.other_InfoEdit4)
                profilDescription = findViewById<EditText>(R.id.editProfilDescription)


    }

   /* fun doneEditing(){
        intent = Intent(this, ProfilActivity::class.java)
        startActivity(intent)
        finish()
    }*/
   /* fun saveProfilInformation() {
       val shoppingItems = mutableListOf<Profil>()

       val itemsRef = db.collection("user")//.document(auth.currentUser.toString()).collection("users")

       itemsRef.addSnapshotListener { snapshot, _ ->
           if (snapshot != null) {
               println("!!! : bbbbb")
               //shoppingItems.clear()
               for (document in snapshot.documents) {
                   val newItem = document.toObject(Profil::class.java)
                   if (newItem != null)
                       println("!!! : cccc")
                       shoppingItems.add(newItem!!)
                   println("!!! : ${newItem}")
                   println("!!! : ddddd")
               }
           }
       }
   }*/

    fun saveProfilInformation(){
     val profilInformation = Profil(/*profilPicture,*/
            profilName.text.toString(),
            editLanguage1.text.toString(),
            editLanguage2.text.toString(),
            editLanguage3.text.toString(),
            profilOtherInfo1.text.toString(),
            profilOtherInfo2.text.toString(),
            profilOtherInfo3.text.toString(),
            profilOtherInfo4.text.toString(),
            profilDescription.text.toString())
        val user = auth.currentUser
        if (user == null)
            return

        db.collection("Users").document(user!!.uid).collection("userInformation").add(profilInformation)
            .addOnSuccessListener { println("!!!: complete")}
            .addOnCanceledListener { println("!!!: cancel")}
            .addOnSuccessListener { println("!!!: write")}
            .addOnFailureListener{ println("!!!: did not write")}
    }

}
