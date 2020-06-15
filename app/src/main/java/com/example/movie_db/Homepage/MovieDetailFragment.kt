package com.example.movie_db.Homepage

import android.os.Bundle
import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movie_db.Models.Movie
import com.example.movie_db.Models.MovieId
import com.example.movie_db.Models.MovieList
import com.example.movie_db.R
import com.example.movie_db.Util.Constants
import com.example.movie_db.Util.Testing
import com.example.movie_db.Util.Util
import com.example.movie_db.ViewModels.MovieDetailViewModel
import com.example.movie_db.ViewModels.MovieListViewModel
import com.example.movie_db.common.MovieBasicFragment
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : MovieBasicFragment() {
    lateinit var goBack:ImageView
    lateinit var titleText:TextView
    var currMovie: MovieList? = null
    lateinit var movieTitleDetail: TextView
    lateinit var movieBGImg : ImageView
    lateinit var movieImg: ImageView
    lateinit var movieRating: TextView
    lateinit var movieDescription: TextView
    lateinit var movieFav: ImageView

    var uid :String? = ""

    companion object{
        val TAG = "MovieDetailFragment"

        fun newInstance(): MovieDetailFragment {
            var args = Bundle()
            var fragment = MovieDetailFragment()
            fragment.setArguments(args)
            return fragment
        }
    }
    private lateinit var mMovieDetailViewModel : MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_movie_detail, container, false)
        val bundle = this.arguments
        if (bundle != null) {
            currMovie= bundle.getParcelable<MovieList>("movie")!!
        }

        initialView(view)
        subscribeObservers()
        updateMovieFromViewModel()
//        movieRating.text = currMovie!!.rating
//        movieDescription.text = currMovie!!.description

        return view
    }

    private fun subscribeObservers() {
        mMovieDetailViewModel.getMovie().observe(this.viewLifecycleOwner, object :
            Observer<Movie> {
            override fun onChanged(@NonNull movie: Movie) {

                if (movie != null) {
                    Testing.printMovie(movie.description, TAG)
                    setMovie(movie)
                }


            }
        })
    }

    private fun initialView(view: View) {
        goBack = (this.activity as HomeActivity).findViewById(R.id.ic_go_back)
        titleText = (this.activity as HomeActivity).findViewById(R.id.app_header)
        goBack.visibility = View.VISIBLE

        movieTitleDetail = view.findViewById(R.id.movie_title_detailpage)
        movieBGImg = view.findViewById(R.id.movie_img_background_detailpage)
        movieImg = view.findViewById(R.id.movie_img_detailpage)
        movieRating = view.findViewById(R.id.movie_rating_detailpage)
        movieDescription = view.findViewById(R.id.movie_detail_description)
        movieFav = view.findViewById(R.id.movie_liked)

        titleText.text = currMovie!!.title
        movieTitleDetail.text = currMovie!!.title

        if (currMovie!!.imageUrl != ""){
            Picasso
                .get()
                .load(Constants.img_front_path + currMovie!!.imageUrl)
                .placeholder(R.drawable.no_images_available)
                .into(movieBGImg)

            Picasso
                .get()
                .load(Constants.img_front_path + currMovie!!.imageUrl)
                .placeholder(R.drawable.no_images_available)
                .into(movieImg)
        }

        mMovieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }

    fun setMovie(movie:Movie) {
        movieDescription.text = movie.description
        movieRating.text = movie.rating
//        movieRating.text = movie.rating



    }

    private fun updateMovieFromViewModel() {
        val api_key = getString(R.string.movie_db_KEY)
        val language = getString(R.string.language)
        searchMovieApi(api_key,language,currMovie!!.id)
    }

    private fun searchMovieApi(api_key: String,language:String,movieId: String) {
        mMovieDetailViewModel.searchMovieByIdApi(api_key,language,movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }
}