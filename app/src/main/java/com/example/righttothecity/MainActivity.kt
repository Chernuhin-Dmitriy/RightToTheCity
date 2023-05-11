package com.example.righttothecity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var reportProblemVIew: ImageView
    private lateinit var listProblemView: ImageView
    private lateinit var profileView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reportProblemVIew = findViewById(R.id.ivProblem)
        listProblemView = findViewById(R.id.ivList)
        profileView = findViewById(R.id.ivProfile)

        reportProblemVIew.setOnClickListener {
            val intent = Intent(this, ReportProblemActivity::class.java)
            startActivity(intent)
        }
    }




}