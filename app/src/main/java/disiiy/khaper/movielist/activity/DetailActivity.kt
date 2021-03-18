package disiiy.khaper.movielist.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import disiiy.khaper.movielist.R
import disiiy.khaper.movielist.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        ib_detail_back.setOnClickListener {
            startActivity(Intent(MainActivity.getLaunchService(this)))
        }

        val movie = intent.getParcelableExtra<ResultsItem>("EXTRA_MOVIE") as ResultsItem
        Glide.with(this).load(movie.posterPath).into(iv_detail_movie_poster)
        Glide.with(this).load(movie.backdropPath).into(iv_detail_backdrop)
        tv_detail_synopsis_desc.text = movie.overview
        tv_detail_movie_title.text = movie.title
        tv_detail_movie_genre.text = movie.genreIds.toString()
        tv_detail_release_date.text = movie.releaseDate.toString()
        tv_detail_user_score_percent.text = movie.voteCount.toString()
        ib_detail_trailer.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"))
            startActivity(i)
        }
    }
}