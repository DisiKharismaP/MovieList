package disiiy.khaper.movielist.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import disiiy.khaper.movielist.R

class VerificationActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 6000

    companion object{
        fun getLaunchService (from: Context) = Intent(from, VerificationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)
        supportActionBar?.hide()

        Handler().postDelayed({
            startActivity(Intent(this,
                    SignInActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}