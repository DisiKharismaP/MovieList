package disiiy.khaper.movielist.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import disiiy.khaper.movielist.R
import kotlinx.android.synthetic.main.activity_onboarding1.*

class Onboarding1Activity : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, Onboarding1Activity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
        supportActionBar?.hide()
        ib_onboarding1.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ib_onboarding1 -> startActivity(Intent(Onboarding2Activity.getLaunchService(this)))
        }
    }
}