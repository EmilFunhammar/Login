package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil.*
import org.w3c.dom.Text
import java.text.ParsePosition


lateinit var language2: TextView
lateinit var language3: TextView
lateinit var aboutMe: TextView
lateinit var profilInformation1: TextView
lateinit var profilInformation2: TextView
lateinit var profilInformation3: TextView
lateinit var profilInformation4: TextView

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DisplayApplicationProfil : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_application_profil)

        supportActionBar?.title = ""


        val profilImage = findViewById<ImageView>(R.id.profilPicture)
        val profilName = findViewById<TextView>(R.id.rubrik)
        val language1 = findViewById<TextView>(R.id.lang1)
        aboutMe = findViewById<TextView>(R.id.GeneralAboutMe)
        language2 = findViewById<TextView>(R.id.lang2)
        language3 = findViewById<TextView>(R.id.lang3)
        profilInformation1 = findViewById<TextView>(R.id.genarall1)
        profilInformation2 = findViewById<TextView>(R.id.generall2)
        profilInformation3 = findViewById<TextView>(R.id.genarall3)
        profilInformation4 = findViewById<TextView>(R.id.generall4)
        val profilDescription = findViewById<TextView>(R.id.bioText)

        val product = intent.getParcelableExtra<Profil>("AnyNameOrKey")
        if (product != null) {
            if (Picasso.get().load(product.userImageUri) != null) {
                    Picasso.get().load(product.userImageUri).into(profilImage)
                }
            profilName.text = product.profilName
            language1.text = product.editLanguage1
            language2.text = product.editLanguage2
            language3.text = product.editLanguage3
            aboutMe.text = "Om ${product.profilName}"
            profilInformation1.text = product.profilOtherInfo1
            profilInformation2.text = product.profilOtherInfo2
            profilInformation3.text = product.profilOtherInfo3
            profilInformation4.text = product.profilOtherInfo4
            profilDescription.text = product.profilDescription
            displayPreofilPage()
        }
    }

    fun displayPreofilPage() {
        rating.rating = 4F
        if (language2.text == "") {
            lang2.visibility = View.GONE
        }
        if (language3.text == "") {
            lang3.visibility = View.GONE
        }
        if (profilInformation1.text == "") {
            profilInformation1.visibility = View.GONE
        }
        if (profilInformation2.text == "") {
            profilInformation2.visibility = View.GONE
        }
        if (profilInformation3.text == "") {
            profilInformation3.visibility = View.GONE
        }
        if (profilInformation4.text == "") {
            profilInformation4.visibility = View.GONE
        }
    }
}
