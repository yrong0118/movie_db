package com.example.movie_db.common

import android.content.Context
import androidx.fragment.app.Fragment

open class MovieBasicFragment : Fragment() {
    protected lateinit var movieFragmentManager:MovieFragmentManager
    private val uuid: String = java.util.UUID.randomUUID().toString()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieFragmentManager = context as MovieFragmentManager
    }




}
