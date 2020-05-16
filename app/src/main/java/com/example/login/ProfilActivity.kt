package com.example.login


import android.content.ClipDescription
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profil.*


lateinit var db :FirebaseFirestore
lateinit var auth: FirebaseAuth
lateinit var profilTitleText : TextView
lateinit var ratingBar: RatingBar
lateinit var taskPreformd : TextView
lateinit var langText1 : TextView
lateinit var langText2 : TextView
lateinit var langText3 : TextView
lateinit var aboutMe : TextView
lateinit var generalInfoText1 : TextView
lateinit var generalInfoText2 : TextView
lateinit var generalInfoText3 : TextView
lateinit var generalInfoText4 : TextView
lateinit var description : TextView


class ProfilActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        profilTitleText = findViewById<TextView>(R.id.rubrik)
        ratingBar = findViewById(R.id.rating)
        taskPreformd = findViewById(R.id.tasks)
        langText1 = findViewById(R.id.lang1)
        langText2 = findViewById(R.id.lang2)
        langText3 = findViewById<TextView>(R.id.lang3)
        aboutMe = findViewById(R.id.GeneralAboutMe)
        generalInfoText1 = findViewById(R.id.genarall1)
        generalInfoText2 = findViewById(R.id.generall2)
        generalInfoText3 = findViewById(R.id.genarall3)
        generalInfoText4 = findViewById(R.id.generall4)
        description = findViewById(R.id.bioText)


        appBar()
        set()


        val fab2 = findViewById<View>(R.id.floatingActionButtonProfilPage2)
        val fab = findViewById<View>(R.id.floatingActionButtonProfilPage)
        fab.setOnClickListener {
            fab()
        }

       /* fab2.setOnClickListener {
            auth.signOut()
            println("!!! : Signing out")
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/
    }


    private fun fab(){
            intent = Intent(this, ProfilEditorActivity::class.java)
            startActivity(intent)
    }

    fun set(){
        val user = auth.currentUser ?: return
        val shoppingItems = mutableListOf<Profil>()
        val itemsRef = db.collection("Users").document(user.uid)

        itemsRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                shoppingItems.clear()
                val newItem = snapshot.toObject(Profil::class.java)
                if (newItem != null) {
                    shoppingItems.add(newItem)
                    setProfilInfo(newItem)
                }
            }
        }
    }


    fun setProfilInfo(newItem : Profil) {
        profilTitleText.text = newItem.profilName
        ratingBar.numStars = 5
        // här
        taskPreformd.text = newItem.workType
        langText1.text = newItem.editLanguage1
        langText2.text = newItem.editLanguage2
        langText3.text = newItem.editLanguage3
        aboutMe.text = "Om " + newItem.profilName
        generalInfoText1.text = newItem.profilOtherInfo1
        generalInfoText2.text = newItem.profilOtherInfo2
        generalInfoText3.text = newItem.profilOtherInfo3
        generalInfoText4.text = newItem.profilOtherInfo4
        description.text = newItem.profilDescription
        displayPreofilPage()


    }


    fun appBar(){
        val accountItem = findViewById<View>(R.id.accountItem)
        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        /*val locationItem = findViewById<View>(R.id.locationItem)
            locationItem.setOnClickListener {
             intent = Intent(this, )
         }*/

        val bussinesItem = findViewById<View>(R.id.bussniesItem)
        bussinesItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }
    }

    fun displayPreofilPage(){
        if (langText1.text == ""){
            lang1.visibility = View.GONE
        }
        if (langText2.text ==""){
            lang2.visibility = View.GONE
        }
        if (langText3.text == ""){
            lang3.visibility = View.GONE
        }
        if (generalInfoText1.text ==""){
            genarall1.visibility = View.GONE
        }
        if (generalInfoText2.text ==""){
            generall2.visibility = View.GONE
        }
        if (generalInfoText3.text ==""){
            genarall3.visibility = View.GONE
        }
        if (generalInfoText4.text ==""){
            generall4.visibility = View.GONE
        }
    }
}
