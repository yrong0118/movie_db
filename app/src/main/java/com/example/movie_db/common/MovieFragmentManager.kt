package com.example.movie_db.common

import android.os.Bundle

interface MovieFragmentManager {

    fun doFragmentTransaction(movieBasicFragment: MovieBasicFragment );

    fun startActivityWithBundle(
        clazz: Class<*>,
        isFinished: Boolean,
        bundle: Bundle
    )

    fun showSnackBar(message: String)
}