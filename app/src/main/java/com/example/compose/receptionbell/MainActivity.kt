package com.example.compose.receptionbell

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.Fuel


class MainActivity : AppCompatActivity() {
    var b: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        // ボタンを押すと音を再生
        val mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.sample_sound)
        b = findViewById<View>(R.id.ReceptionButton) as Button

        b!!.setOnClickListener {
            mediaPlayer?.start()
            // MainActivity2へ画面遷移
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))

            // ボタンを押すとSlackに通知がいく
            val webhook: String = "Webhook URLが入る"
            val body: String = "{ \"text\" : \"<!here>　:bell:お客様がいらっしゃいました\" }"
            Fuel.post(webhook).body(body).responseString { _, response, result ->
                result.fold({ _ ->
                    Log.d("res", response.toString())
                }, { err ->
                    Log.e("err", err.toString())
                })
            }
        }
    }

        companion object {
            var mediaPlayer:MediaPlayer? = null
        }
    }