package com.example.compose.receptionbell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ハンドラのメソッド呼び出す
        loadingDelay()
        setContentView(R.layout.activity_main2)
        //ナビゲーションバーを消す
        supportActionBar?.hide()
    }

    private fun loadingDelay() {
        //ハンドラを生成し、遅延時間を2秒に設定
        Handler().postDelayed({
            //10秒以降に画面を遷移するためのIntent設定
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }, 10000)
    }
}
