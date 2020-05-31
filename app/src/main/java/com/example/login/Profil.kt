package com.example.login

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentId

data class Profil(
    var userImageUri: String? = null,
    var profilName: String? = "",
    var editLanguage1: String? = "",
    var editLanguage2: String? = "",
    var editLanguage3: String? = "",
    var profilOtherInfo1: String? = "",
    var profilOtherInfo2: String? = "",
    var profilOtherInfo3: String? = "",
    var profilOtherInfo4: String? = "",
    var profilDescription: String? = "",
    var workType: String? = "",
    @DocumentId
    var documentId : String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {

    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest != null) {
            dest.writeString(userImageUri)
            dest.writeString(profilName)
            dest.writeString(editLanguage1)
            dest.writeString(editLanguage2)
            dest.writeString(editLanguage3)
            dest.writeString(profilOtherInfo1)
            dest.writeString(profilOtherInfo2)
            dest.writeString(profilOtherInfo3)
            dest.writeString(profilOtherInfo4)
            dest.writeString(profilDescription)
            dest.writeString(workType)
        }
    }
    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR : Parcelable.Creator<Profil> {
        override fun createFromParcel(parcel: Parcel): Profil {
            return Profil(parcel)
        }

        override fun newArray(size: Int): Array<Profil?> {
            return arrayOfNulls(size)
        }
    }
}

