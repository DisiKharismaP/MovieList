package disiiy.khaper.movielist.service

import disiiy.khaper.movielist.model.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    fun getMovieData(
            @Query("api_key") apiKey : String?,
            @Query("language") lang : String?
    ): Call<ResponseMovies>
}