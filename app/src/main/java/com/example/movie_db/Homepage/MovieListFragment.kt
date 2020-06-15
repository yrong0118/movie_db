package com.example.movie_db.Homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_db.Adapters.OnMovieListen
import com.example.movie_db.Adapters.RecyclerViewAdapter
import com.example.movie_db.Models.MovieList
import com.example.movie_db.R
import com.example.movie_db.Util.Testing.Companion.printMovies
import com.example.movie_db.Util.Util
import com.example.movie_db.ViewModels.MovieListViewModel
import com.example.movie_db.common.MovieBasicFragment
import com.example.movie_db.common.MovieFragmentManager

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : MovieBasicFragment(),OnMovieListen{

    companion object{
        val TAG = "MovieListFragment"

        fun newInstance(): MovieListFragment {
            var args = Bundle()
            var fragment = MovieListFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    lateinit var goBack:ImageView
    lateinit var titleText:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: RecyclerViewAdapter

    private lateinit var mMovieListViewModel : MovieListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view :View = inflater.inflate(R.layout.fragment_movie_list, container, false)
        initialView(view)

        subscribeObservers()
//        getData()
        updateMovieListFromViewModel()
        return view
    }

    private fun initialView(view: View) {
        goBack = (this.activity as HomeActivity).findViewById(R.id.ic_go_back)
        titleText = (this.activity as HomeActivity).findViewById(R.id.app_header)
        goBack.visibility = View.GONE
        titleText.text = getString(R.string.app_name)

        initRecyclerView(view)
    }

    private fun initRecyclerView(view :View) {
        recyclerView = view.findViewById(R.id.recycleview_movielist)
        recyclerView.layoutManager = GridLayoutManager(this.context,3)
        mMovieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        mAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = mAdapter
        mAdapter.displayLoading()
        recyclerView.layoutManager = GridLayoutManager(this.context,3)
        recyclerView.setHasFixedSize(true)

    }

    private fun subscribeObservers(){
        mMovieListViewModel.getMovieList().observe(this.viewLifecycleOwner, object : Observer<List<MovieList>> {
            override fun onChanged(@NonNull movies: List<MovieList>?) {

                if (movies != null) {
                    printMovies(movies, TAG)
                }

                mAdapter.setMovies(movies!!)
            }
        })
    }

    private fun searchMoviesApi(api_key: String,primaryReleaseDateGte:String,primaryReleaseDateLte:String,language:String, page:Int) {
        mMovieListViewModel.searchMoviesApi(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,page)
    }

    private fun updateMovieListFromViewModel() {
        val api_key = getString(R.string.movie_db_KEY)
        val primaryReleaseDateGte = Util.getdate(TAG, -15)
        val primaryReleaseDateLte = Util.getdate(TAG, 15)
        val language = getString(R.string.language)
        searchMoviesApi(api_key,primaryReleaseDateGte,primaryReleaseDateLte,language,1)
    }

    override fun onMovieListClick(position: Int) {
        Log.d(TAG,"onMovieList click: clicked $position")
        val bundle = Bundle()
        bundle.putParcelable("movie",mAdapter.getSelectedMovie(position))
        val movieDetailFragment = MovieDetailFragment.newInstance()
        movieDetailFragment.arguments = bundle
        movieFragmentManager.doFragmentTransaction(movieDetailFragment)
    }
//    fun getData() {
//        var movieApi = ServiceGenerator.instance.create(MovieApi::class.java)
//        val api_key = getString(R.string.movie_db_KEY)
//        val primaryReleaseDateGte = Util.getdate(TAG, -15)
//        val primaryReleaseDateLte = Util.getdate(TAG, 15)
//        val language = getString(R.string.language)
//
//        movieApi.getMovies(
//            api_key,
//            primaryReleaseDateGte,
//            primaryReleaseDateLte,
//            language
//        ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .filter { baseResponse -> baseResponse != null }
//            .subscribe(
//                { baseResponse -> detail(baseResponse) },
//                { throwable ->
//                    //*******************************
//                    if (context == null) {
//                        throw IllegalStateException("Fragment " + this + " not attached to a context.");
//                    }
//                    //*******************************
//                    Toast.makeText(this.context, "Movies API Failed", Toast.LENGTH_SHORT).show()
//                    throwable.printStackTrace()
//                }
//            )
//    }

//    fun detail(response: MovieListResponse) {
//        Log.d(TAG,"movie id is : ${response.results.get(0).id}")
//    }
}