package com.example.movie_db.Request

import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_db.AppExecutors
import com.example.movie_db.Homepage.MovieListFragment
import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieList
import com.example.movie_db.R
import com.example.movie_db.Repositories.MovieRepository
import com.example.movie_db.Request.reponses.MovieDetailResponse
import com.example.movie_db.Request.reponses.MovieListResponse
import com.example.movie_db.Util.Constants
import com.example.movie_db.Util.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.http.Query
import java.io.IOException
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit


 class MovieApiClient {

    private val mMovies: MutableLiveData<List<MovieList>> = MutableLiveData<List<MovieList>>()
     private val mMovie: MutableLiveData<Movie> = MutableLiveData<Movie>()
    private var retrieveMoviesRunnable:RetrieveMoviesRunnable? = null
     private var retrieveMovieRunnable:RetrieveMovieRunnable? = null
    companion object {
        private val TAG = "MovieApiClient"
        private var instance : MovieApiClient? = null

        fun getInstance() : MovieApiClient {
            if (instance == null) {
                instance = MovieApiClient()
            }
            return instance!!
        }
    }

    fun getMovies(): LiveData<List<MovieList>> {
        return mMovies
    }

     fun getMovie(): LiveData<Movie> {
         return mMovie
     }


    fun searchMoviesApi(api_key:String,primaryReleaseDateGte:String,primaryReleaseDateLte:String,language:String, page:Int){

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null
        }
        retrieveMoviesRunnable = RetrieveMoviesRunnable(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page)
        val handler= AppExecutors.getInstance().netWorkIO().submit(
            retrieveMoviesRunnable
//            object :Runnable{
//            override fun run() {
//                // retrieve data from rest api
//                //mMovies.postValue();
//            }
//        }
        )
        AppExecutors.getInstance().netWorkIO().schedule(object : Runnable {
            override fun run() {
                //let the user know its time out
                handler.cancel(true)
            }
        },Constants.NETWORK_TIMEOUT,TimeUnit.MILLISECONDS)
    }

     fun searchMovieByIdApi(api_key: String,language: String,movieId: String) {
         if (retrieveMovieRunnable != null) {
             retrieveMovieRunnable = null
         }
         retrieveMovieRunnable = RetrieveMovieRunnable(api_key,language,movieId)
         val handler = AppExecutors.getInstance().netWorkIO().submit(
             retrieveMovieRunnable
         )
         AppExecutors.getInstance().netWorkIO().schedule(object : Runnable {
             override fun run() {
                 //let's user know the timeout
                 handler.cancel(true)
             }
         },Constants.NETWORK_TIMEOUT,TimeUnit.MILLISECONDS)
     }

    inner class RetrieveMoviesRunnable(_api_key:String,_primaryReleaseDateGte:String,_primaryReleaseDateLte:String,_language:String, _page:Int): Runnable{
        private val primaryReleaseDateGte: String = _primaryReleaseDateGte
        private val primaryReleaseDateLte: String = _primaryReleaseDateLte
        private val language: String = _language
        private val page:Int = _page
        private val api_key : String = _api_key
        var cancelRequest:Boolean = false

        override fun run() {
            try {
                val response = getMovies(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page).execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val list  = ArrayList<MovieList>((response.body() as MovieListResponse).getMovies())
                    if (page == 1) {
                        mMovies.postValue(list)
                    } else {
                        val currentMovies = mMovies.value as ArrayList<MovieList>
                        currentMovies.addAll(list)
                        mMovies.postValue(currentMovies)
                    }
                } else {
                    val error: String = response.errorBody().toString()
                    Log.e(TAG,"run:   $error")
                    mMovies.postValue(null)
                }
            } catch (e:IOException) {
                e.printStackTrace()
                mMovies.postValue(null)
            }


        }
        private fun getMovies(api_key:String,primaryReleaseDateGte:String, primaryReleaseDateLte:String, language:String,page:Int): Call<MovieListResponse> {
            return ServiceGenerator.getMovieApi().getMovies(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page)
        }

        private fun cancelRequest() {
            Log.d(TAG,"cancelRequest: canceling the movieList request")
            cancelRequest = true
        }
    }
     inner class RetrieveMovieRunnable(_api_key:String,_language:String,_movieId: String): Runnable{
         private val movieId: String = _movieId
         private val api_key : String = _api_key
         private val language : String = _language
         var cancelRequest:Boolean = false

         override fun run() {
             try {
                 val response = getMovie(api_key,language,movieId).execute()
                 if (cancelRequest) {
                     return
                 }
                 if (response.code() == 200) {
                     val description  = ((response.body() as MovieDetailResponse).getMovie())
                     mMovie.postValue(description)
                 } else {
                     val error: String = response.errorBody().toString()
                     Log.e(TAG,"run:   $error")
                     mMovie.postValue(null)
                 }
             } catch (e:IOException) {
                 e.printStackTrace()
                 mMovie.postValue(null)
             }


         }
         private fun getMovie(api_key:String,language: String, movieId: String): Call<MovieDetailResponse> {
             return ServiceGenerator.getMovieApi().getMovieDetilById(movieId,api_key,language)
         }

         private fun cancelRequest() {
             Log.d(TAG,"cancelRequest: canceling the movieList request")
             cancelRequest = true
         }
     }

//     public fun cancelRequest() {
//         if (retrieveMoviesRunnable != null) {
//             retrieveMoviesRunnable as RetrieveMoviesRunnable.cancelRequest
//         }
//     }

 }

