package com.example.righttothecity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


class YandexMapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
//    private lateinit var questionTextView: TextView
//    private lateinit var yesButton: Button
//    private lateinit var noButton: Button
//    private lateinit var answerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("829324cf-1f68-4b83-b589-47428ba22df6")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_yandex_map)


        // Находим View элементы по их id из разметки xml
        mapView = findViewById(R.id.mapview)
//        questionTextView = findViewById(R.id.question_textview)
//        yesButton = findViewById(R.id.yes_button)
//        noButton = findViewById(R.id.no_button)
//        answerLayout = findViewById(R.id.answer_layout)

        // Устанавливаем слушателей на кнопки
//        yesButton.setOnClickListener { onYesButtonClick() }
//        noButton.setOnClickListener { onNoButtonClick() }

//        mapView.visibility = View.VISIBLE

        mapView.map.move(
            CameraPosition(Point(48.528179, 44.558784), 11f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 10f), null)

        var mapkit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()

        var locationonmapkit = mapkit.createUserLocationLayer(mapView.mapWindow)
        locationonmapkit.isVisible = true
}

    private fun requestLocationPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
            return
        }
    }

    private fun onYesButtonClick() {
        // Показываем MapView и запрашиваем разрешение на доступ к местоположению
        mapView.visibility = View.VISIBLE
        askForLocationPermission()
    }

    private fun onNoButtonClick() {
        // Показываем MapView и ждем, пока пользователь укажет местоположение
        mapView.visibility = View.VISIBLE
    }

    private fun askForLocationPermission() {
        // Проверяем, есть ли разрешение на доступ к местоположению
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION // ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Если разрешения нет, то запрашиваем его у пользователя
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),  // ACCESS_FINE_LOCATION
                PERMISSION_REQUEST_CODE
            )
        } else {
            // Если разрешение есть, то получаем текущее местоположение и показываем его на карте
            showCurrentLocationOnMap()
        }
    }


    private fun showCurrentLocationOnMap() {
        val mapKit = MapKitFactory.getInstance()
        val locationManager = mapKit.createLocationManager()

        locationManager.requestSingleUpdate(object : LocationListener {
            override fun onLocationUpdated(location: Location) {
                val currentLocation = Point(
                    location.position.latitude,
                    location.position.longitude
                )
                mapView.map.move(
                    CameraPosition(currentLocation, 14.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 1f),
                    null
                )


                // Можно добавить маркер на текущее местоположение
                 val marker = mapView.map.mapObjects.addPlacemark(currentLocation)
            }



            override fun onLocationStatusUpdated(locationStatus: LocationStatus) {
                // Обработка изменения статуса местоположения пользователя
            }
        })
    }






    private fun showLocationOnMap(latitude: Double, longitude: Double) {
        // Добавляем маркер на карту с указанными координатами
        if (mapView.map != null) {
            if (mapObjectCollection == null) {
                mapObjectCollection = mapView.map.mapObjects.addCollection()
            }
            val point = Point(latitude, longitude)
            mapObjectCollection!!.clear()
            val placemark = mapObjectCollection!!.addPlacemark(point)
            placemark.setIcon(ImageProvider.fromResource(this, R.drawable.marker))
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
        private const val MIN_TIME_BW_UPDATES: Long = 1000 * 60 * 1
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES = 10f
        private var mapObjectCollection: MapObjectCollection? = null
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()

    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
}
