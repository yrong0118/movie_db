package com.example.movie_db.Util

import android.util.Log
import com.example.movie_db.Homepage.MovieListFragment
import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieList

class Testing {
    companion object {
        fun printMovies( list : List<MovieList>, tag:String) {
            for (cur   in 0 until list.size) {
                Log.d(tag,"----------onchange: ${list.get(cur).id}----------")
            }
        }
        fun printMovie(description : String, tag:String) {
            Log.d(tag,"-----------onchange: ${description}--------------")
        }
    }
}