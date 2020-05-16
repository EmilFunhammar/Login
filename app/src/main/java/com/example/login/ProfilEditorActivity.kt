package com.example.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profil_editor.*
import kotlinx.android.synthetic.main.activity_sign_up_layout.*


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_editor)


        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        profilPicture = findViewById<ImageView>(R.id.imageView)
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
                    //editInputInfo()
                    test()
                    //saveProfilInformation()
                    intent = Intent(this, ProfilActivity::class.java)
                    startActivity(intent)
                }
            }


        /*fun test1(){
        var user = auth.currentUser ?: return
        val docRef = db.collection("Users").document(user.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {


                    Log.d("AAA", "DocumentSnapshot data: firebase ${document.data!!["profilName"]}")
                    Log.d("AAA", "DocumentSnapshot data: editText ${editprofilName.text}")



                    if (editprofilName.text.toString() == "") {
                        println("AAA : tom")
                        editprofilName.text = Editable.Factory.getInstance().newEditable(document.data!!["profilName"].toString())
                        println("AAA :!nytt värde för editText ${editprofilName.text}")

                        //println("AAA :nytt värde för editText ${editprofilName.text}")
                        //test()
                    } else {
                        println("AAA : inte lika")
                        test()
                    }




                }

            }

    }*/

        fun editInputInfo() {
            var user = auth.currentUser ?: return
            val docRef = db.collection("Users").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {

                    }
                }
        }

                      /*  if (editprofilName.text.toString() == "") {
                            editprofilName.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilName"].toString())
                            println("AAA :!nytt värde för editText ${editprofilName.text}")
                        }
                        if (editLanguage1.text.toString() == "") {
                            editLanguage1.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["editLanguage1"].toString())
                            println("AAA :!nytt värde för editText ${editLanguage1.text}")
                        }
                        if (editLanguage2.text.toString() == "") {
                            editLanguage2.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["editLanguage2"].toString())
                            println("AAA :!nytt värde för editText ${editLanguage2.text}")
                        }
                        if (editLanguage3.text.toString() == "") {
                            editLanguage3.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["editLanguage3"].toString())
                            println("AAA :!nytt värde för editText ${editLanguage3.text}")
                        }
                        if (profilDescription.text.toString() == "") {
                            profilDescription.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilDescription"].toString())
                            println("AAA :!nytt värde för editText ${profilDescription.text}")
                        }
                        if (profilOtherInfo1.text.toString() == "") {
                            profilOtherInfo1.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilOtherInfo1"].toString())
                            println("AAA :!nytt värde för editText ${profilOtherInfo1.text}")
                        }
                        if (profilOtherInfo2.text.toString() == "") {
                            profilOtherInfo2.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilOtherInfo2"].toString())
                            println("AAA :!nytt värde för editText ${profilOtherInfo2.text}")
                        }
                        if (profilOtherInfo3.text.toString() == "") {
                            profilOtherInfo3.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilOtherInfo3"].toString())
                            println("AAA :!nytt värde för editText ${profilOtherInfo3.text}")
                        }
                        if (profilOtherInfo4.text.toString() == "") {
                            profilOtherInfo4.text = Editable.Factory.getInstance()
                                .newEditable(document.data!!["profilOtherInfo4"].toString())
                            println("AAA :!nytt värde för editText ${profilOtherInfo4.text}")
                        } else {
                            test()
                        }
                    }
                    test()
                }
        }*/

        private fun saveProfilInformation() {


            var user = auth.currentUser ?: return
            val shoppingItems = mutableListOf<Profil>()
            val itemsRef = db.collection("Users").document(user.uid)

            itemsRef.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    shoppingItems.clear()
                    val newItem = snapshot.toObject(Profil::class.java)
                    if (newItem != null) {
                        shoppingItems.add(newItem)
                        println("AAA : ${editprofilName.text}")
                        println("AAA : ${newItem.profilName}")
                        //if (newItem.profilName!! == editprofilName.text.toString()) {
                        if (editprofilName.text.toString() == "") {
                            println("AAA : tom")
                            editprofilName.setText(newItem.profilName)
                            println("AAA : ${editprofilName.text}")
                            test()
                        } else {
                            println("AAA : inte lika")
                            test()
                        }
                    }
                }
            }

            /*  db.collection("Users").document(auth.currentUser!!.uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    if (editprofilName.text.isEmpty()){
                        println("AAA : ${editprofilName.setText()")
                    }
                } else {
                }
            }*/
            // if (msg.trim().isEmpty()) {
            //    Toast.makeText(applicationContext, "Message : " + msg, Toast.LENGTH_SHORT).show()
            //  return
            //}
        }

        fun test() {
            val profilInformation = Profil(
                //profilPicture,
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


            val user = auth.currentUser ?: return

            db.collection("Users").document(user.uid).set(profilInformation)
                .addOnSuccessListener { println("!!!: complete") }
                .addOnCanceledListener { println("!!!: cancel") }
                .addOnSuccessListener { println("!!!: write") }
                .addOnFailureListener { println("!!!: did not write") }

        }

        fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)




}
