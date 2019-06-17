package com.dicoding.picodiploma.myrecyclerview.model

import android.os.Parcel
import android.os.Parcelable

data class Hero(var name: String?, var from: String?, var photo: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(from)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hero> {
        override fun createFromParcel(parcel: Parcel): Hero {
            return Hero(parcel)
        }

        override fun newArray(size: Int): Array<Hero?> {
            return arrayOfNulls(size)
        }
    }
}