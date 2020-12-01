package com.guilhermecallandprojects.myawesomephotos.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class AwesomePhoto(
    val id: Int,
    val image: String?,
    val date: String?,
    val location: String? = null,
    var latitude: Double,
    var longitude: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(image)
        parcel.writeString(date)
        parcel.writeString(location)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AwesomePhoto> {
        override fun createFromParcel(parcel: Parcel): AwesomePhoto {
            return AwesomePhoto(parcel)
        }

        override fun newArray(size: Int): Array<AwesomePhoto?> {
            return arrayOfNulls(size)
        }
    }
}