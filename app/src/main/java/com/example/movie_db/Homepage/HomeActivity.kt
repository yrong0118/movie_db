package com.example.movie_db.Homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_db.R
import com.example.movie_db.common.MovieBasicActivity
import com.example.movie_db.common.MovieBasicFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : MovieBasicActivity(){
    lateinit var bottomBar : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieListFragment = MovieListFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container,movieListFragment).commit()

        bottomBar = findViewById(R.id.bottomBar)
        bottomBar.setSelectedItemId(R.id.action_find)

        bottomBar.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.action_find ->{

                }
//                R.id.action_save ->{
//                    val intent = Intent(this, SavedMoviesActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.action_map -> {
//                    val intent = Intent(this, MapsActivity::class.java)
//                    startActivity(intent)
//                }
//                R.id.action_profile ->{
//                    val intent = Intent(this, ProfileActivity::class.java)
//                    startActivity(intent)
//                }

            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_homepage
    }

    override fun doFragmentTransaction(movieBasicFragment: MovieBasicFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,movieBasicFragment)
            .addToBackStack(movieBasicFragment.toString())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun showSnackBar(message: String) {
        TODO("Not yet implemented")
    }




}