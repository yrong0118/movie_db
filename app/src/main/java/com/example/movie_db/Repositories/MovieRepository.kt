package com.example.movie_db.Repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieList
import com.example.movie_db.Request.MovieApiClient

class MovieRepository() {
    private lateinit var mMovieApiClient: MovieApiClient
    companion object {
        private val TAG = "MovieRepository"
        private var instance : MovieRepository?=null
        fun getInstance():MovieRepository{
            if (instance == null) {
                instance = MovieRepository()
            }
            return instance!!
        }
    }

//    private val mMovies: MutableLiveData<List<MovieList>> = MutableLiveData<List<MovieList>>()

    fun getMovies():LiveData<List<MovieList>> {
        mMovieApiClient = MovieApiClient.getInstance()
        return mMovieApiClient.getMovies()
    }

    fun getMovie():LiveData<Movie> {
        mMovieApiClient = MovieApiClient.getInstance()
        return mMovieApiClient.getMovie()
    }

    fun searchMoviesApi(api_key:String,primaryReleaseDateGte:String,primaryReleaseDateLte:String,language:String, page:Int) {
        if (page == 0) {
            mMovieApiClient.searchMoviesApi(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,1)
        }
        mMovieApiClient.searchMoviesApi(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page)
    }
    fun searchMovieByIdApi(api_key:String,language:String, movieId:String) {
        mMovieApiClient.searchMovieByIdApi(api_key,language,movieId)
    }
}


