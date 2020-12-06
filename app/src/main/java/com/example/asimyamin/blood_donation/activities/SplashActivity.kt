package com.example.asimyamin.blood_donation.activities

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.asimyamin.blood_donation.R
import com.example.asimyamin.blood_donation.activities.on_board.OnBoardActivity
import com.example.asimyamin.blood_donation.databinding.ActivitySplashBinding
import com.example.asimyamin.blood_donation.utils.Utils.Companion.navigateAndClearBackStack

class SplashActivity : AppCompatActivity() {

    private lateinit var activitySplashBinding: ActivitySplashBinding
    private val SPLASH_DISPLAY_LENGTH = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

       /* Handler(Looper.getMainLooper()).postDelayed({
            run {
                val mIntent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(mIntent)
                finish()
            }
        }, 3000)*/
    }
    fun init(){
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

//        val ivSplash = findViewById<ImageView>(R.id.splash_iv_logo)
        val animation1 = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_anim)
        activitySplashBinding.splashImg.startAnimation(animation1)
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(SPLASH_DISPLAY_LENGTH.toLong())
                    navigateAndClearBackStack(OnBoardActivity::class.java, this@SplashActivity)

                    /*if (SharedPrefHelper.getInstance().isLoggedIn()) {
                        navigateAndClearBackStack(BottomNavActivity::class.java, this@SplashActivity)
                    } else {
                        navigateAndClearBackStack(OnBoardActivity::class.java, this@SplashActivity)
                    }*/
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }


}