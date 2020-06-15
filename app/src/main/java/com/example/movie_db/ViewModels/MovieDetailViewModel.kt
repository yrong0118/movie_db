package com.example.movie_db.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieList
import com.example.movie_db.Repositories.MovieRepository

class MovieDetailViewModel(application: Application): AndroidViewModel(application)  {
    //    private lateinit var mMovies : MutableLiveData<List<MovieList>>

    private var mMovieRepository: MovieRepository = MovieRepository.getInstance()

    fun getMovie(): LiveData<Movie> {
        return mMovieRepository.getMovie()
    }

    fun searchMovieByIdApi(api_key:String,language:String, movieId:String) {
        mMovieRepository.searchMovieByIdApi(api_key,language,movieId)
    }
}