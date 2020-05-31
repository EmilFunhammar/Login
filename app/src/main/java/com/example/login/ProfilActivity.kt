package com.example.login


import android.content.ClipDescription
import android.content.DialogInterface
import android.content.Intent
import android.icu.text.Transliterator
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil.*
import java.text.ParsePosition


class ProfilActivity : AppCompatActivity() {


    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var profilPicture : ImageView
    lateinit var profilTitleText: TextView
    lateinit var ratingBar: RatingBar
    lateinit var langText1: TextView
    lateinit var langText2: TextView
    lateinit var langText3: TextView
    lateinit var aboutMe: TextView
    lateinit var generalInfoText1: TextView
    lateinit var generalInfoText2: TextView
    lateinit var generalInfoText3: TextView
    lateinit var generalInfoText4: TextView
    lateinit var description: TextView
    var workType: String = "employer"
    lateinit var persons: List<Profil>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)




        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        profilPicture = findViewById<ImageView>(R.id.profilPicture)
        profilTitleText = findViewById<TextView>(R.id.rubrik)
        ratingBar = findViewById(R.id.rating)
        langText1 = findViewById(R.id.lang1)
        langText2 = findViewById(R.id.lang2)
        langText3 = findViewById<TextView>(R.id.lang3)
        aboutMe = findViewById(R.id.GeneralAboutMe)
        generalInfoText1 = findViewById(R.id.genarall1)
        generalInfoText2 = findViewById(R.id.generall2)
        generalInfoText3 = findViewById(R.id.genarall3)
        generalInfoText4 = findViewById(R.id.generall4)
        description = findViewById(R.id.bioText)

        typOfUser()
        val user = auth.currentUser
      /*  db.collection("Users").document(user!!.uid).get().addOnSuccessListener { document ->
            if (document != null) {
                workType = document.data?.get("workType").toString()
                println("!!! : $workType")
                typOfUser()

            }
        }*/

        val accountItem = findViewById<View>(R.id.accountItem)
        accountItem.setOnClickListener {
            intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        val bussinesItem = findViewById<View>(R.id.bussniesItem)
        bussinesItem.setOnClickListener {
            intent = Intent(this, HeadActivity::class.java)
            startActivity(intent)
        }

        val application = findViewById<View>(R.id.messageItem)
        application.setOnClickListener {
            intent = Intent(this, EmployesApplicationActivity::class.java)
            startActivity(intent)
        }

        val alert = AlertDialog.Builder(this)
        val signOutItem = findViewById<View>(R.id.sign_Out_Item)
        signOutItem.setOnClickListener {
            println("!!! : signout pressd")
            alert.setTitle("Är du säker?")
            alert.setMessage("Vill du logga ut?")
            alert.setPositiveButton("Ja") { dialogInterface: DialogInterface, i: Int ->
                auth.signOut()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            alert.setNegativeButton("Nej") { dialogInterface: DialogInterface, i: Int -> }
            alert.show()
        }

        val fab = findViewById<View>(R.id.floatingActionButtonProfilPage)
        fab.setOnClickListener {
                intent = Intent(this, ProfilEditorActivity::class.java)
                startActivity(intent)
        }

        snapshotListiner()
    }




    fun snapshotListiner() {
        val user = auth.currentUser
        val shoppingItems = mutableListOf<Profil>()
        val itemsRef = db.collection("Users").document(user?.uid!!)

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


    fun setProfilInfo(newItem: Profil) {
        if (Picasso.get().load(newItem.userImageUri) != null) {
            println("!!!  displayApplication else: ${newItem.userImageUri}")
            Picasso.get().load(newItem.userImageUri).into(profilPicture)
        }
        profilTitleText.text = newItem.profilName
        ratingBar.numStars = 4
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



    fun displayPreofilPage() {
        ratingBar.rating = 5F
        if (langText1.text == "") {
            lang1.visibility = View.GONE
        }
        if (langText2.text == "") {
            lang2.visibility = View.GONE
        }
        if (langText3.text == "") {
            lang3.visibility = View.GONE
        }
        if (generalInfoText1.text == "") {
            genarall1.visibility = View.GONE
        }
        if (generalInfoText2.text == "") {
            generall2.visibility = View.GONE
        }
        if (generalInfoText3.text == "") {
            genarall3.visibility = View.GONE
        }
        if (generalInfoText4.text == "") {
            generall4.visibility = View.GONE
        }
    }
    fun typOfUser() {
        println("!!! : $workType")
        if (workType.equals("worker")){
            val signOutItem = findViewById<View>(R.id.sign_Out_Item)
            val messageItem = findViewById<View>(R.id.messageItem)
            messageItem.visibility = View.GONE
            signOutItem.visibility = View.GONE
        }
        if (workType.equals("employer")){
            println("!!! : $workType")
            val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
            val alert = AlertDialog.Builder(this)
            val signOutItem = findViewById<View>(R.id.sign_Out_Item)
            val mapsItem = findViewById<View>(R.id.locationItem)
            val accountItem = findViewById<View>(R.id.accountItem)
            mapsItem.visibility = View.INVISIBLE
            accountItem.visibility = View.GONE
            signOutItem.setOnClickListener {
                println("!!! : signout pressd")
                alert.setTitle("Är du säker?")
                alert.setMessage("Vill du logga ut?")
                alert.setPositiveButton("Ja") { dialogInterface: DialogInterface, i: Int ->
                    auth.signOut()
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                alert.setNegativeButton("Nej") { dialogInterface: DialogInterface, i: Int -> }
                alert.show()
            }


        }
    }
}