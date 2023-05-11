package com.example.righttothecity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ReportProblemActivity : AppCompatActivity() {

    private lateinit var rvPhotos: RecyclerView
    private lateinit var btnAddPhoto: Button
    private lateinit var btnAddVideo: Button
    private lateinit var btnUpload: Button

    private val adapterPhoto = PhotoAdapter()
    private val adapterVideo = VideoAdapter()

    val concatAdapter = ConcatAdapter(adapterPhoto, adapterVideo)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_problem)

        rvPhotos = findViewById(R.id.rvPhotos)
        btnAddPhoto = findViewById(R.id.btnAddPhoto)
        btnAddVideo = findViewById(R.id.btnAddVideo)
        btnUpload = findViewById(R.id.btnUpload)

        //rvPhotos.layoutManager = LinearLayoutManager(this)    //Все фотки были в столбик
        rvPhotos.layoutManager = GridLayoutManager(this@ReportProblemActivity, 3)
        rvPhotos.adapter = concatAdapter



        btnAddPhoto.setOnClickListener {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
            if(true) {
                pickPhotoFromGallery()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    companion.REQUEST_PERMISSION
                )
            }
        }

        btnAddVideo.setOnClickListener {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
            if(true) {
                pickVideoFromGallery()
            }
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    companion.REQUEST_PERMISSION
                )
            }

        }

        btnUpload.setOnClickListener {
            // Здесь можно добавить код для загрузки содержимого буфера на сервер
//            photosList.clear()
//            adapter.notifyDataSetChanged()

            val intent = Intent(this, CommentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, companion.REQUEST_PICK_PHOTO)
    }

    private fun pickVideoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, companion.REQUEST_PICK_VIDEO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                companion.REQUEST_PICK_PHOTO -> {
                    val selectedPhotoUri = data.data
                    if (selectedPhotoUri != null) {
                        adapterPhoto.photosList.add(selectedPhotoUri)
                        adapterPhoto.notifyItemInserted(adapterPhoto.photosList.size - 1)
                    }
                }
                companion.REQUEST_PICK_VIDEO -> {
                    val selectedVideoUri = data.data

                    if (selectedVideoUri != null) {
                        adapterVideo.videoList.add(selectedVideoUri)
                        adapterVideo.notifyItemInserted(adapterVideo.videoList.size - 1)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == companion.REQUEST_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            when {
                permissions[0] == Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    pickPhotoFromGallery()
                }
            }
        }
    }
}
