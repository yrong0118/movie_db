package com.example.movie_db.Request.reponses

import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieListResponse() {
    @SerializedName("results")
    @Expose()
    private val results = listOf<MovieList>()

    fun getMovies() : List<MovieList>{
        return results
    }
}

//class MovieDetailResponse() {
//    @SerializedName("overview")
//    @Expose()
//    private val description = ""
//
//    fun getDescription() : String{
//        return description
//    }
//}

//data class MovieDetailResponse(
//    @SerializedName("id")
//    val filmId: String,
//    @SerializedName("title")
//    val filmName: String,
//    @SerializedName("poster_path")
//    val filmImg: String,
//    @SerializedName("vote_average")
//    val rating: String,
//    @SerializedName("overview")
//    val description:String,
//    @SerializedName("release_date")
//    val releaseDate:String
//)

class MovieDetailResponse() {
    @SerializedName("id")
    @Expose()
    private var id = ""
    @SerializedName("title")
    @Expose()
    private var title : String = ""
    @SerializedName("poster_path")
    @Expose()
    private var  imageUrl : String = ""
    @SerializedName("vote_average")
    @Expose()
    private var  rating:String= ""
    @SerializedName("overview")
    @Expose()
    private var  description :String= ""
    @SerializedName("release_date")
    @Expose()
    private var  releaseDate : String = ""

    fun getMovie(): Movie {
        val movie = Movie(title,id,imageUrl,releaseDate,description,rating)
        return movie
    }
}




//data class MovieListResponse (
//    @SerializedName("results")
//    val results: List<MovieList>
//)
//
//
//data class MovieDetailResponse (
//    @SerializedName("id")
//    val id : String,
//    @SerializedName("title")
//    var title : String,
//    @SerializedName("poster_path")
//    val imageUrl : String,
//    @SerializedName("vote_average")
//    var rating:String,
//    @SerializedName("overview")
//    var description :String,
//    @SerializedName("release_date")
//    val releaseDate : String
//)