package com.example.login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profil_editor.*

class ProfilEditorActivity : AppCompatActivity() {




    lateinit var profilPicture: ImageView
    lateinit var editprofilName: EditText
    lateinit var editLanguage1: EditText
    lateinit var editLanguage2: EditText
    lateinit var editLanguage3: EditText
    lateinit var profilOtherInfo1: EditText
    lateinit var profilOtherInfo2: EditText
    lateinit var profilOtherInfo3: EditText
    lateinit var profilOtherInfo4: EditText
    lateinit var profilDescription: EditText
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var workType : String
    var selectedPhotoUri : Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_editor)

        supportActionBar?.title = ""

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        profilPicture = findViewById<ImageView>(R.id.selectphoto_Image)
        editprofilName = findViewById<EditText>(R.id.editProfilName)
        editLanguage1 = findViewById<EditText>(R.id.lang_Edit_Text1)
        editLanguage2 = findViewById<EditText>(R.id.lang_Edit_Text2)
        editLanguage3 = findViewById<EditText>(R.id.lang_Edit_Text3)
        profilOtherInfo1 = findViewById<EditText>(R.id.other_InfoEdit1)
        profilOtherInfo2 = findViewById<EditText>(R.id.other_InfoEdit2)
        profilOtherInfo3 = findViewById<EditText>(R.id.other_InfoEdit3)
        profilOtherInfo4 = findViewById<EditText>(R.id.other_InfoEdit4)
        profilDescription = findViewById<EditText>(R.id.editProfilDescription)
        val docRef = db.collection("Users").document(user!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    workType = document.data!!["workType"].toString()
                    editprofilName.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilName"].toString())
                    editLanguage1.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["editLanguage1"].toString())
                    editLanguage2.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["editLanguage2"].toString())
                    editLanguage3.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["editLanguage3"].toString())
                    profilDescription.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilDescription"].toString())
                    profilOtherInfo1.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilOtherInfo1"].toString())
                    profilOtherInfo2.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilOtherInfo2"].toString())
                    profilOtherInfo3.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilOtherInfo3"].toString())
                    profilOtherInfo4.text = Editable.Factory.getInstance()
                        .newEditable(document.data!!["profilOtherInfo4"].toString())
                }
            }

        val saveButton = findViewById<Button>(R.id.save_ButtonEdit)
        saveButton.setOnClickListener {
            uploadeImageToFirebaseStorage()
            //intent = Intent(this, ProfilActivity::class.java)
            //startActivity(intent)
        }

        //
        selectphoto_Image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
    } // OnCreate

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            println("!!! : photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            selectphoto_Image.setImageBitmap(bitmap)
        }
    }
    fun uploadeImageToFirebaseStorage(){
        if (selectedPhotoUri == null) return

        val fileName = auth.currentUser
        val ref = FirebaseStorage.getInstance()
            .getReference("userImages").child(fileName?.uid!!)

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                println("!!! : sucsses ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    it.toString()
                    println("!!! : File location $it")
                    saveUserToFirebaseDatabase(it.toString())
                }
            }
    }


    fun saveUserToFirebaseDatabase(selectedPhotoUri: String) {
        val user = auth.currentUser ?: return
        val profilInformation = Profil(
            selectedPhotoUri,
            editprofilName.text.toString(),
            editLanguage1.text.toString(),
            editLanguage2.text.toString(),
            editLanguage3.text.toString(),
            profilOtherInfo1.text.toString(),
            profilOtherInfo2.text.toString(),
            profilOtherInfo3.text.toString(),
            profilOtherInfo4.text.toString(),
            profilDescription.text.toString(),
            workType = workType
        )
        db.collection("Users").document(user.uid).set(profilInformation).addOnCompleteListener {
            intent = Intent(this, ProfilActivity::class.java)
        startActivity(intent)}
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}
