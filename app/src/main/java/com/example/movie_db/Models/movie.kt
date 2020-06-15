package com.example.movie_db.Models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
//    @SerializedName("title")
    var title : String,
//    @SerializedName("id")
    val id : String,
//    @SerializedName("poster_path")
    val imageUrl : String,
//    @SerializedName("release_date")
    val releaseDate : String,
//    @SerializedName("overview")
    var description :String,
//    @SerializedName("vote_average")
    var rating:String
): Parcelable {
    constructor():this("","","","","","")
}

@Parcelize
data class MovieList(
    @SerializedName("title")
    var title : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("poster_path")
    val imageUrl : String,
    @SerializedName("release_date")
    val releaseDate : String
): Parcelable {
    constructor():this("","","","")
}

data class MovieId(

    val id : String
){
    constructor():this("")
}