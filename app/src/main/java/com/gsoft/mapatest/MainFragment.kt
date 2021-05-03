package com.gsoft.mapatest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.gsoft.mapatest.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main), OnMapReadyCallback {

    private var mapa: GoogleMap? = null
    private lateinit var binding : FragmentMainBinding
    private val mapViewBundleKey = "MapViewBundleKey"
    private val miLocation : LatLng = LatLng(-33.888491, -60.582174)
    private val zoom : Float =  18f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        //binding.map.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            initGoogleMap(savedInstanceState)
        };

        binding.bOtro.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mapsFragment)
        }

    }

    fun initGoogleMap(savedInstanceState: Bundle){
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(mapViewBundleKey)
        }
       // binding.map.onCreate(mapViewBundle)
       // binding.map.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        //binding.map?.onResume()
    }

    override fun onStart() {
        super.onStart()
       // binding.map?.onStart()
    }

    override fun onStop() {
        super.onStop()
      //  binding.map?.onStop()
    }

    override fun onPause() {
        super.onPause()
       // binding.map?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
      //  binding.map?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
      //  binding.map?.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(mapViewBundleKey)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(mapViewBundleKey, mapViewBundle)
        }
      //  binding.map.onSaveInstanceState(mapViewBundle)
    }

    override fun onMapReady(myMap: GoogleMap?) {

        if (activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) }
                != PackageManager.PERMISSION_GRANTED
                && activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
                != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "No permit",Toast.LENGTH_LONG ).show()
            return;
        }
        myMap?.isMyLocationEnabled = true;
        myMap?.setMinZoomPreference(zoom)
        myMap?.moveCamera(CameraUpdateFactory.newLatLng(miLocation));
    }



}