package com.example.movie_db.Request

import com.example.movie_db.Request.reponses.MovieDetailResponse
import com.example.movie_db.Request.reponses.MovieListResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    //get movie list in the homepage
    @GET("discover/movie?")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("primary_release_date.gte") primaryReleaseDateGte: String,
        @Query("primary_release_date.lte") primaryReleaseDateLte: String,
        @Query("language") language: String,
        @Query("page") page : Int
    ): Call<MovieListResponse>

    //get movie detail in the detailpage
    @GET("movie/{movieId}?")
    fun getMovieDetilById(
        @Path("movieId")movieId:String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ) : Call<MovieDetailResponse>
}

interface MovieDetailRequestApi {
    @GET("movie/{movieId}")
    fun getMovieDetailById(
        @Path("movieId") movieId: String,
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Observable<MovieDetailResponse>

}

//interface MovieApi {
//    //get movie list in the homepage
//    @GET("discover/movie?")
//    fun getMovies(
//        @Query("api_key") api_key: String,
//        @Query("primary_release_date.gte") primaryReleaseDateGte: String,
//        @Query("primary_release_date.lte") primaryReleaseDateLte: String,
//        @Query("language") language: String
//    ): Observable<MovieListResponse>
//}

//interface MovieDetalApi{
//    //get movie detail in the detailpage
//    @GET("movie/{movieId}")
//    fun getMovieDetilById(
//        @Path("movieId")movieId:String,
//        @Query("language") language: String,
//        @Query("api_key") api_key: String
//    ) : Observable<MovieDetailResponse>
//}


