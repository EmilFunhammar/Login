package com.example.login

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentId

class Work(
    var title: String? = null,
    var salary: String? = null,
    var description: String? = null,
    var workLocation: String? = null,
    var employerName: String? = null,
    var employerPhoneNumber: String? = null,
    var employerEmail: String? = null,
    var userUid: String? = null,
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
       // parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest != null) {
            dest.writeString(title)
            dest.writeString(salary)
            dest.writeString(description)
            dest.writeString(workLocation)
            dest.writeString(employerName)
            dest.writeString(employerPhoneNumber)
            dest.writeString(employerEmail)
            dest.writeString(userUid)
            //dest.writeString(documentId)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Work> {
        override fun createFromParcel(parcel: Parcel): Work {
            return Work(parcel)
        }

        override fun newArray(size: Int): Array<Work?> {
            return arrayOfNulls(size)
        }
    }
}