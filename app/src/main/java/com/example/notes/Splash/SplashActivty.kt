package com.example.notes.Splash

import android.content.Intent
import android.os.Handler
import com.example.notes.note.BaseActivity
import com.example.notes.R
import com.example.notes.login.LoginActivity


class SplashActivty : BaseActivity() {
    private val SPLASH_TIME_OUT:Long = 3000

    override fun initComponents() {
Handler().postDelayed(
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    },SPLASH_TIME_OUT) }

    override fun getLayoutId(): Int {
      return R.layout.activity_spalsh
    }
}