package eu.hybase.mitnezzek.jsons

import eu.hybase.mitnezzek.models.Movie
import eu.hybase.mitnezzek.models.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieResponse {

    @GET("movie")
    fun getPopularMovie(
        @Query("language") language: String,
        @Query("page") pageNumber: Int,
        @Query("api_key") api_key:String
    ) : Call<MoviesList>
}