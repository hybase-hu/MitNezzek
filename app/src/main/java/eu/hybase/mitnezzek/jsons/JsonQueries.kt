package eu.hybase.mitnezzek.jsons

import android.app.Activity
import android.content.Context
import android.util.Log
import eu.hybase.mitnezzek.MainActivity
import eu.hybase.mitnezzek.R
import eu.hybase.mitnezzek.adapters.MoviePreviewAdapters
import eu.hybase.mitnezzek.constants.Constants
import eu.hybase.mitnezzek.models.GenreList
import eu.hybase.mitnezzek.models.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class JsonQueries {

    companion object {
        private const val TAG = "JSONQueries"
    }

    fun getPopularMovies(context: Context,adapters: MoviePreviewAdapters,pageNumber : Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var movieApi = retrofit.create(MovieResponse::class.java)
        val callPopularMovieResponse = movieApi.getPopularMovie(language = "hu-HU",api_key = context.getString(
            R.string.api_v3),pageNumber = pageNumber)
        callPopularMovieResponse.enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                adapters.addNextPageForMovies(response.body()!!.results!!)
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                Log.e(MainActivity.TAG, "onFailure: ${t.message}", )
            }
        })
    }



    fun getAllGenres(context: Context)  {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/genre/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var genreApi = retrofit.create(GenresResponse::class.java)
        val callAllGenreApiResponse = genreApi.getAllGenre("hu-HU",api_key = context.getString(R.string.api_v3) )
        if (Constants.ALL_GENRE_LIST.size > 0) {
            Constants.ALL_GENRE_LIST.clear()
        }
        callAllGenreApiResponse.enqueue(object : Callback<GenreList> {
            override fun onResponse(call: Call<GenreList>, response: Response<GenreList>) {
                Log.d(TAG, "onResponse: ${response.body()!!.genres}")
                for (i in response.body()!!.genres) {
                    Constants.ALL_GENRE_LIST.add(i)
                }
                Log.d(TAG, "onResponse: THIS GENRE LIST : ${
                    Constants.ALL_GENRE_LIST.filter { it ->
                        it.id == 10402
                    }
                }")


                if ((context as Activity) is MainActivity) {
                    (context as MainActivity).showGenreFilter()
                    Log.d(TAG, "onResponse: IS TRUE")
                } else {
                    Log.e(TAG, "onResponse: IS NOT OKEY", )
                }


            }

            override fun onFailure(call: Call<GenreList>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}", )
            }

        })
    }


}