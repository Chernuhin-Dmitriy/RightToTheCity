package com.example.righttothecity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashScreen : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstance: Bundle?){
        super.onCreate(savedInstance)

        Completable.timer(3, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .subscribe {
//                Toast.makeText(this@SplashScreen, "Done!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LocationSelection::class.java)
                startActivity(intent)
                finish()
            }
    }
}