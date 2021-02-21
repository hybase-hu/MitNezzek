package eu.hybase.mitnezzek.jsons

import eu.hybase.mitnezzek.models.GenreList
import eu.hybase.mitnezzek.models.Genres
import eu.hybase.mitnezzek.models.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresResponse {


    @GET("list")
    fun getAllGenre(
        @Query("language") language: String,
        @Query("api_key") api_key:String
    ) : Call<GenreList>
}