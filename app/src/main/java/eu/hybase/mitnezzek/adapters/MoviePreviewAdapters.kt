package eu.hybase.mitnezzek.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.hybase.mitnezzek.R
import eu.hybase.mitnezzek.constants.Constants
import eu.hybase.mitnezzek.databinding.ItemMoviePreviousBinding
import eu.hybase.mitnezzek.jsons.JsonQueries
import eu.hybase.mitnezzek.models.Genres
import eu.hybase.mitnezzek.models.Movie

class MoviePreviewAdapters (private val context: Context,private var list:ArrayList<Movie>?)
    : RecyclerView.Adapter<MoviePreviewAdapters.ViewHolder>() {

    private var mPageNumber : Int = 1
    private var mGenres : ArrayList<Genres> = ArrayList()

    companion object {
        private const val TAG = "MoviePreviewAdapters"

    }


    inner class ViewHolder(itemView: ItemMoviePreviousBinding) : RecyclerView.ViewHolder(itemView.root) {
        val postersImage = itemView.itemMoviePrevPosters
        val movieTitle = itemView.itemMoviePrevTitle
        val movieVote = itemView.itemMoviePrevVote
        val movieCategory = itemView.itemMoviePrevCategory
        val movieReleaseDate = itemView.itemMoviePrevRelease
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviePreviousBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (list!!.isNotEmpty()) {
            val currentMovie = list!![position]

            Glide.with(context)
                .load(Constants.POSTERS_HTTP_PATH + currentMovie.poster_path.toString())
                .placeholder(R.drawable.movie_placeholder)
                .into(holder.postersImage)


            for (genre in currentMovie.genre_ids) {
                val equalGenreId = Constants.ALL_GENRE_LIST.filter { it ->
                    it.id == genre
                }
                mGenres.addAll(equalGenreId)
            }

            holder.movieTitle.text = currentMovie.title
            holder.movieVote.text = currentMovie.vote_average.toString()
            holder.movieCategory.text = mGenres.toString()
            holder.movieReleaseDate.text = currentMovie.release_date

            if (position == list!!.size-1) {
                mPageNumber += 1
                Log.d(TAG, "onBindViewHolder: NEW PREVIEW PAGE $mPageNumber")
                JsonQueries().getPopularMovies(context,adapters = this,mPageNumber)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty()) 0 else (list as ArrayList<Movie>).size
    }

    fun addNextPageForMovies(movies : ArrayList<Movie>) {
        if (list.isNullOrEmpty()) {
            list = movies
        }
        else {
            list!!.addAll(movies)
        }
        this.notifyDataSetChanged()

    }


}