package com.example.movie_db.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_db.Models.MovieList
import com.example.movie_db.R
import com.example.movie_db.Util.Constants
import com.example.movie_db.Util.Util
import com.example.movie_db.common.MovieFragmentManager
import com.squareup.picasso.Picasso

class RecyclerViewAdapter (val onMovieListen:OnMovieListen): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private val MOVIE_LIST_TYPE = 1
        private val LOADING_TYPE = 2

//        class MyViewHolde(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val movieTitle: TextView
//            val movieImg: ImageView
//            val cardView: CardView
//
//            init {
//                movieTitle = itemView.findViewById(R.id.movie_title) as TextView
//                movieImg = itemView.findViewById(R.id.movie_img) as ImageView
//                cardView = itemView.findViewById(R.id.cardView_movie) as CardView
//            }
//
//        }

    }
    var mData:List<MovieList> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var mInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var view:View? = null
        when(viewType)  {
            MOVIE_LIST_TYPE-> {
                view= mInflater.inflate(R.layout.card_view_movie_item, parent, false)

                return MovieListViewHolder(view , onMovieListen)
            }
            LOADING_TYPE-> {
                view= mInflater.inflate(R.layout.layout_loading_list_item, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                view= mInflater.inflate(R.layout.card_view_movie_item, parent, false)
                return MovieListViewHolder(view , onMovieListen)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewType = getItemViewType(position)

        if (itemViewType == MOVIE_LIST_TYPE) {
            val currentMovies = mData[position]
            if (!Util.isStringEmpty(currentMovies.title)) {
                holder as MovieListViewHolder
                holder.movieTitle.setText(mData.get(position).title)
                if (!Util.isStringEmpty(currentMovies.imageUrl)) {
                    Picasso
                        .get()
                        .load(Constants.img_front_path + mData.get(position).imageUrl)
                        .into(holder.movieImg)
                }

            }
        }


//        holder.itemView.setOnClickListener { v: View? ->
//            val intent = Intent(mContext, MovieDetailActivity::class.java)
//            intent.putExtra("movie", mData.get(position))
//            intent.putExtra("from",HomeActivity.TAG)
//            mContext.startActivity(intent)
//        }
    }

    override fun getItemViewType(position: Int): Int {
        if(mData.get(position).title.equals(Constants.LOADING)) {
            return LOADING_TYPE
        } else {
            return MOVIE_LIST_TYPE
        }
    }

    fun displayLoading(){
        if (!isLoading()) {
            val loading = MovieList()
            loading.title = Constants.LOADING
            val loadingList = arrayListOf<MovieList>()
            loadingList.add(loading)
            mData = loadingList
            notifyDataSetChanged()
        }
    }
    private fun isLoading():Boolean{
        if (mData != null) {
            if (mData.size > 0) {
                if (mData.get(mData.size - 1).title.equals(Constants.LOADING)) {
                    return true
                }
            }
        }
        return false
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setMovies(movies:List<MovieList>) {
        mData =  movies
        notifyDataSetChanged()
    }

    fun getSelectedMovie(position: Int):MovieList? {
        if (mData != null) {
            if (mData.size > 0) {
                return mData.get(position)
            }
        }
        return null
    }
}
