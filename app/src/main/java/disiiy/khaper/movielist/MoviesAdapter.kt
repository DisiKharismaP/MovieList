package disiiy.khaper.movielist

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toFile
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import disiiy.khaper.movielist.activity.DetailActivity
import disiiy.khaper.movielist.model.ResultsItem
import disiiy.khaper.movielist.utils.Const.BASE_URL_IMAGE
import kotlinx.android.synthetic.main.movie_list.view.*
import okhttp3.internal.notifyAll
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.wrapContent
import retrofit2.http.Url

class MoviesAdapter
    (var context : Context, var listMovie : List<ResultsItem?>?): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: ResultsItem) {
            with(itemView){
                tv_movie_title_list.text = movie.title
                tv_release_date_list.text = movie.releaseDate.toString()
                tv_movie_genre_list.text= movie.genreIds.toString()
                tv_vc_percent_list.text = movie.voteCount.toString()
                tv_synopsis_desc_list.text = movie.overview

                Glide.with(context).load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .centerCrop()
                    .into(iv_poster_list)

                itemView.setOnClickListener{
                    itemView.context.startActivity(
                        itemView.context.intentFor<DetailActivity>(
                            "EXTRA_MOVIE" to movie
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie!!.size

    override fun onBindViewHolder(holder: MoviesAdapter.ViewHolder, position: Int) {
        holder.bind(listMovie?.get(position)!!)
    }
}



