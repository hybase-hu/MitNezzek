package eu.hybase.mitnezzek.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.hybase.mitnezzek.databinding.ItemGenreSelectBinding
import eu.hybase.mitnezzek.models.Genres

class GenreFilterAdapter(private val context: Context, private val list:ArrayList<Genres>)
    :RecyclerView.Adapter<GenreFilterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : ItemGenreSelectBinding) : RecyclerView.ViewHolder(itemView.root) {
        val genreName = itemView.itemGenreName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreSelectBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGenre = list[position]
        holder.genreName.text = currentGenre.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

}