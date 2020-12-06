package com.example.asimyamin.blood_donation.activities.on_board

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.asimyamin.blood_donation.R
import com.example.asimyamin.blood_donation.activities.auth.LoginActivity
import com.example.asimyamin.blood_donation.databinding.ActivityOnBoardBinding
import com.example.asimyamin.blood_donation.shared_pref.SharedPrefHelper
import com.example.asimyamin.blood_donation.utils.BaseView
import com.example.asimyamin.blood_donation.utils.Utils

class OnBoardActivity : AppCompatActivity(), BaseView {
    private lateinit var onBoardBinding: ActivityOnBoardBinding
    private var argbEvaluator: ArgbEvaluator? = null
    private var statusBarColorArray: IntArray? = null
    private var currentOnBoardPage = 0 //  to track page position
    private var indicatorsImgArray: Array<ImageView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setListeners()

    }

    override fun init() {
        onBoardBinding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(onBoardBinding.root)

        if (SharedPrefHelper.instance!!.getOnBoardSliderState()) {
            Utils.navigateAndClearBackStack(LoginActivity::class.java, this@OnBoardActivity)
        }

        val color1 = ContextCompat.getColor(this, R.color.white)
        val color2 = ContextCompat.getColor(this, R.color.white)
        val color3 = ContextCompat.getColor(this, R.color.white)

        statusBarColorArray = intArrayOf(color1, color2, color3)
        Utils.changeStatusBarColor(color1, this@OnBoardActivity)

        argbEvaluator = ArgbEvaluator()
        val mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        indicatorsImgArray = arrayOf(onBoardBinding.introIndicator0, onBoardBinding.introIndicator1, onBoardBinding.introIndicator2)

        // Set up the ViewPager with the sections adapter.

        // Set up the ViewPager with the sections adapter.
        onBoardBinding.onBoardingVp.adapter = mSectionsPagerAdapter

        onBoardBinding.onBoardingVp.currentItem = currentOnBoardPage
        OnBoardClickListener.instance?.updateIndicators(currentOnBoardPage, indicatorsImgArray!!)


    }

    override fun setValues() {
        TODO("Not yet implemented")
    }

    override fun setListeners() {
        OnBoardClickListener.instance?.onPageClickListener(onBoardBinding, argbEvaluator!!, statusBarColorArray!!, indicatorsImgArray!!, this)

        onBoardBinding.onBoardingBtnNext.setOnClickListener { v ->
            currentOnBoardPage += 1
            onBoardBinding.onBoardingVp.setCurrentItem(currentOnBoardPage, true)
        }

        onBoardBinding.onBoardingBtnBack.setOnClickListener { v ->
            currentOnBoardPage--
            onBoardBinding.onBoardingVp.setCurrentItem(currentOnBoardPage, true)
        }

        onBoardBinding.onBoardingBtnSkip.setOnClickListener { v ->
//            SharedPrefHelper.instance?.saveOnBoardSlider(true)
            Utils.navigateAndClearBackStack(LoginActivity::class.java, this@OnBoardActivity)
        }

        onBoardBinding.onBoardingBtnGetStarted.setOnClickListener { v ->
//            SharedPrefHelper.instance?.saveOnBoardSlider(true)
            Utils.navigateAndClearBackStack(LoginActivity::class.java, this@OnBoardActivity)
        }
    }
}