package eu.hybase.mitnezzek.models

import eu.hybase.mitnezzek.models.Movie

data class MoviesList(
    val total_pages : Int,
    val total_results: Int,
    val results: ArrayList<Movie>
) {
}