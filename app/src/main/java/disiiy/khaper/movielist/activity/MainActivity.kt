package disiiy.khaper.movielist.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import disiiy.khaper.movielist.MoviesAdapter
import disiiy.khaper.movielist.R
import disiiy.khaper.movielist.model.ResponseMovies
import disiiy.khaper.movielist.service.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var refUsers : DatabaseReference? = null
    var firebaseUser : FirebaseUser? = null

    companion object{
        fun getLaunchService (from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        tv_profile_name_main.setOnClickListener(this)
        getMovies()

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().getReference("User").child(firebaseUser!!.uid)
        refUsers!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (p0 in snapshot.children){
                    val photo = snapshot.child("photo").value.toString()
                    val name = snapshot.child("fullName").value.toString()

                    tv_profile_name_main.text = name

                    Glide.with(this@MainActivity).load(photo).into(iv_profile_main)
                }
            }
        })

    }

    private fun getMovies() {
        val apiKey = "168b56c96e0f9a95ad3fafd35b04d2f1"
        val lang = "en-US"

        val loading = ProgressDialog.show(this, "Request Data", "Loading...")
        RetrofitConfig.getInstance().getMovieData(apiKey, lang).enqueue(
            object : Callback<ResponseMovies>{
                override fun onResponse(
                    call: Call<ResponseMovies>,
                    response: Response<ResponseMovies>
                ){
                    Log.d("Response", "Success" + response.body()?.results)
                    loading.dismiss()
                    if (response.isSuccessful){
                        Log.e("TAG", "onResponse: ${response.body()?.results?.get(0)?.title}" )
                        Toast.makeText(this@MainActivity, "Data Success !", Toast.LENGTH_SHORT).show()
                        val movieData = response.body()?.results
                        val movieAdapter = MoviesAdapter(this@MainActivity, movieData)
                        rv_main.adapter = movieAdapter
                        rv_main.layoutManager = LinearLayoutManager(this@MainActivity)

                    }else{
                        Toast.makeText(this@MainActivity, "Data Failed !", Toast.LENGTH_SHORT).show()
                    }

                }
                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    Log.d("Response","Failed : " + t.localizedMessage)
                    loading.dismiss()
                }
            }
        )
    }



    override fun onClick(v: View) {
        when(v.id){
            R.id.tv_profile_name_main -> startActivity(Intent(ProfileActivity.getLaunchService(this)))
        }
    }
}