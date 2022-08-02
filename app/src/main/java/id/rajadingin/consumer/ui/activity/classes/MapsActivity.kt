package id.rajadingin.consumer.ui.activity.classes

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import id.rajadingin.consumer.R
import id.rajadingin.consumer.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastLocation(mMap)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(googleMap: GoogleMap) {
        fusedLocationClient.lastLocation
            .addOnCompleteListener(OnCompleteListener<Location?> { task ->
                val location = task.result
                if (location == null) {
                    requestNewLocationData()
                } else {
                    Log.e("LAT : ", location.latitude.toString())
                    Log.e("LONG : ", location.longitude.toString())
                    val Myloc = LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(Myloc).title("Marker in Sydney"))
                    val center = CameraUpdateFactory.newLatLng(
                        LatLng(
                            location.latitude,
                            location.longitude
                        )
                    )
                    val zoom = CameraUpdateFactory.zoomTo(15f)
                    mMap.moveCamera(center);
                    mMap.animateCamera(zoom);
                }
            })
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {

        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation

            Log.e("LAT : ", mLastLocation?.latitude.toString())
            Log.e("LONG : ", mLastLocation?.longitude.toString())
            val Myloc = mLastLocation?.longitude?.let { mLastLocation?.latitude?.let { it1 ->
                LatLng(
                    it1, it)
            } }
            Myloc?.let { MarkerOptions().position(it).title("Marker in Sydney") }
                ?.let { mMap.addMarker(it) }
            Myloc?.let { CameraUpdateFactory.newLatLng(it) }?.let { mMap.moveCamera(it) }

            val center = mLastLocation?.latitude?.let {
                LatLng(
                        it,
                    mLastLocation.longitude
                    )
            }?.let {
                CameraUpdateFactory.newLatLng(
                    it
                )
            }
            val zoom = CameraUpdateFactory.zoomTo(15f)
            if (center != null) {
                mMap.moveCamera(center)
            };
            mMap.animateCamera(zoom);
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


}