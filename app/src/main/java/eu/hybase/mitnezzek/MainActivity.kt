package eu.hybase.mitnezzek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import eu.hybase.mitnezzek.adapters.GenreFilterAdapter
import eu.hybase.mitnezzek.adapters.MoviePreviewAdapters
import eu.hybase.mitnezzek.constants.Constants
import eu.hybase.mitnezzek.databinding.ActivityMainBinding
import eu.hybase.mitnezzek.jsons.JsonQueries
import eu.hybase.mitnezzek.jsons.MovieResponse
import eu.hybase.mitnezzek.models.Movie
import eu.hybase.mitnezzek.models.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(mainBinding.root)
        showPopularMovieRecyclerView()
        JsonQueries().getAllGenres(this)
    }


    private fun showPopularMovieRecyclerView() {
        val adapters = MoviePreviewAdapters(this,null)
        val movieRecyclerView = mainBinding.mainActivityRecyclerView
        JsonQueries().getPopularMovies(this,adapters,pageNumber = 1)
        movieRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        movieRecyclerView.adapter = adapters
    }


    fun showGenreFilter() {
        val adapters = GenreFilterAdapter(context = this,Constants.ALL_GENRE_LIST)
        val genreRecyclerView = mainBinding.includeFilteredMovieLayout.filterGenreRecyclerView
        genreRecyclerView.adapter = adapters
        genreRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }




}