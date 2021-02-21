package eu.hybase.mitnezzek.models

import java.io.Serializable

data class Genres(
    val id : Int,
    val name : String
) : Serializable {
    override fun toString(): String {
        return this.name
    }
}