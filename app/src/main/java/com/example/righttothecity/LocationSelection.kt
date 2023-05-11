package com.example.righttothecity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.ScreenPoint
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import java.lang.Math.sin
import java.lang.StrictMath.cos
import java.util.*

class LocationSelection : AppCompatActivity(), CameraListener {
    private lateinit var mapView: MapView
    private lateinit var confirmButton: Button
    private lateinit var markerView: ImageView

    private var markerPosition = Point(0.0, 0.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("829324cf-1f68-4b83-b589-47428ba22df6")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_location_selection)

        mapView = findViewById(R.id.mapView)
        confirmButton = findViewById(R.id.confirmButton)
        markerView = findViewById(R.id.markerView)
//        markerView.setBackgroundColor(resources.getColor(android.R.color.transparent))

        mapView.map.move(
            CameraPosition(
                Point(55.753215, 37.622504),
                14.0f,
                0.0f,
                0.0f
            ),
            Animation(Animation.Type.SMOOTH, 1f),
            null
        )

        mapView.map.addCameraListener(this)

        confirmButton.setOnClickListener {
            // handle confirmation button click
            val resultIntent = Intent()
            var how = markerPosition.latitude
            var are = markerPosition.longitude

            resultIntent.putExtra("latitude", markerPosition.latitude)
            resultIntent.putExtra("longitude", markerPosition.longitude)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

//    override fun onCameraPositionChanged(
//        mapView: MapView,
//        cameraPosition: CameraPosition,
//        cameraUpdateReason: CameraUpdateReason,     // cameraUpdateSource: CameraUpdateSource,
//
//        finished: Boolean
//    ) {
//        if (finished) {
//            markerPosition = mapView.map.cameraPosition.target
//            val markerScreenPosition = mapView.map.mapObjects
//                .addPlacemark(markerPosition)
//                .geometry
//                .let { mapView.map.mapObjects.geometryToScreen(it) }
//
//            markerView.x = markerScreenPosition.x.toFloat() - markerView.width / 2
//            markerView.y = markerScreenPosition.y.toFloat() - markerView.height
//        }
//    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        finished: Boolean
    ) {
        if (finished) {

            // Получаем экранные координаты imageView
            val imageViewLocation = IntArray(2)
            markerView.getLocationOnScreen(imageViewLocation)
            val imageViewScreenPoint = Point(imageViewLocation[0].toDouble(), imageViewLocation[1].toDouble())

            // Преобразуем экранные координаты imageView в географические координаты
            markerPosition = mapView.map.screenToWorld(imageViewScreenPoint)
        }
    }

    fun Map.screenToWorld(screenPoint: Point): Point {
        val mapWindow = mapView.mapWindow   //added mapWindow
        val mapViewWidth = mapWindow.width()
        val mapViewHeight = mapWindow.height()
        val cameraPosition = this.cameraPosition

        val centerX = mapViewWidth / 2.0    //именно высоту карты
        val centerY = mapViewHeight / 2.0

        // Вычисляем экранные координаты точки относительно центра карты
        val dx = screenPoint.latitude - centerX
        val dy = screenPoint.longitude - centerY

        val rotatedDx = dx * cos(-cameraPosition.tilt.toDouble()) - dy * sin(-cameraPosition.tilt.toDouble())
        val rotatedDy = dx * sin(-cameraPosition.tilt.toDouble()) + dy * cos(-cameraPosition.tilt.toDouble())

        val pointX = cameraPosition.target.longitude + rotatedDx / mapViewWidth * (this.visibleRegion.bottomRight.longitude - this.visibleRegion.topLeft.longitude)
        val pointY = cameraPosition.target.latitude - rotatedDy / mapViewHeight * (this.visibleRegion.bottomRight.latitude - this.visibleRegion.topLeft.latitude)

        Toast.makeText(this@LocationSelection, (String.format(Locale.US, "%.6f\n%.6f", pointX, pointY)), Toast.LENGTH_SHORT).show()

        return Point(pointX, pointY)
    }
//    val mapViewWidth = this.mapSize.width.toFloat()
//    val mapViewHeight = this.mapSize.height.toFloat()
//
//    val x = screenPoint.x.toDouble()
//    val y = screenPoint.y.toDouble()
//
//    val topLeft = this.visibleRegion.topLeft
//    val bottomRight = this.visibleRegion.bottomRight
//
//    val centerLatitude = (topLeft.latitude + bottomRight.latitude) / 2
//    val centerLongitude = (topLeft.longitude + bottomRight.longitude) / 2
//
//    val centerX = (mapViewWidth / 2) - (topLeft.longitude - centerLongitude) * (mapViewWidth / (bottomRight.longitude - topLeft.longitude))
//    val centerY = (mapViewHeight / 2) + (centerLatitude - topLeft.latitude) * (mapViewHeight / (bottomRight.latitude - topLeft.latitude))
//
//    return Point(x - centerX, centerY - y)
}


