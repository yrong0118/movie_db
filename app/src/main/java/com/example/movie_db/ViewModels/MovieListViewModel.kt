package com.example.movie_db.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_db.Models.MovieList
import com.example.movie_db.Repositories.MovieRepository


class MovieListViewModel(application: Application): AndroidViewModel(application)  {
//    private lateinit var mMovies : MutableLiveData<List<MovieList>>

    private var mMovieRepository: MovieRepository = MovieRepository.getInstance()

    fun getMovieList():LiveData<List<MovieList>>{
        return mMovieRepository.getMovies()
    }

    fun searchMoviesApi(api_key:String,primaryReleaseDateGte:String,primaryReleaseDateLte:String,language:String, page:Int) {
        mMovieRepository.searchMoviesApi(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page)
    }
}