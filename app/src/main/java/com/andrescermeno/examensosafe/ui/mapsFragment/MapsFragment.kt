package com.andrescermeno.examensosafe.ui.mapsFragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.andrescermeno.examensosafe.R
import com.andrescermeno.examensosafe.databinding.FragmentMapsBinding
import com.andrescermeno.examensosafe.remote.Result
import com.andrescermeno.examensosafe.vo.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mancj.materialsearchbar.MaterialSearchBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel by activityViewModels<MainViewModel>()
    private var listOfPin : List<Result> = listOf()

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        submitUserLocate()
        onClickMarkInGoogleMap()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_maps,container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment?.getMapAsync(callback)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(binding.root.context)

        setHasOptionsMenu(true)

        setupObservers()

        btnClickMyLocation()

        setupSearchBar()

        setupChips()

        return binding.root
    }

    private fun onClickMarkInGoogleMap() {
        if (mMap.isBuildingsEnabled){
            mMap.setOnInfoWindowClickListener { pin ->
                val location = listOfPin.filter {  list ->
                    list.geometry.location.lat == pin.position.latitude && list.geometry.location.lng == pin.position.longitude
                }
                val bundle = Bundle()
                bundle.putString("placeId",location[0].place_id)
                findNavController().navigate(R.id.action_mapsFragment_to_detailFragment,bundle)
            }}

    }

    private fun btnClickMyLocation() {
        binding.ibMyLocation.setOnClickListener {
            submitUserLocate()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupChips() {
        binding.chipFuelStation.setOnTouchListener { view, motionEvent ->
            viewModel.wordToSearch.value = "Gasolinera"
            return@setOnTouchListener true
        }
        binding.chipAirPort.setOnTouchListener { view, motionEvent ->
            viewModel.wordToSearch.value = "Aeropuerto"
            return@setOnTouchListener true
        }
        binding.chipCoffeShop.setOnTouchListener { view, motionEvent ->
            viewModel.wordToSearch.value = "Café"
            return@setOnTouchListener true
        }
        binding.chipGroceries.setOnTouchListener { view, motionEvent ->
            viewModel.wordToSearch.value = "Supermercado"
            return@setOnTouchListener true
        }
        binding.chipRestaurant.setOnTouchListener { view, motionEvent ->
            viewModel.wordToSearch.value = "Restaurante"
            return@setOnTouchListener true
        }
    }

    private fun setupSearchBar() {
        binding.svLocation.setOnSearchActionListener(object: MaterialSearchBar.OnSearchActionListener{
            override fun onSearchStateChanged(enabled: Boolean) {
                //No se implementara
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                viewModel.wordToSearch.value = text.toString()
            }

            override fun onButtonClicked(buttonCode: Int) {
                //No se implementara
            }

        })
    }

    private fun submitUserLocate() {
        if (ActivityCompat.checkSelfPermission(
                binding.root.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                binding.root.context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0
            )
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val myLocation = LatLng(location.latitude, location.longitude)
                    viewModel.firstLocation.value = "${location.latitude},${location.longitude}"
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15.0F))
                    mMap.isMyLocationEnabled = true

                }
            }
    }

    private fun setupObservers() {
        viewModel.locationsList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Success ->{
                    putPin(result.data.toMutableList())
                }
                is Resource.Failure ->{
                    Toast.makeText(binding.root.context, "No funciono", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading ->{

                }
            }
        })
    }

    private fun putPin(locationList: MutableList<Result>?) {
        if (locationList != null) {
            listOfPin = locationList
            var pin: LatLng
            var name : String
            mMap.clear()
            for(location in locationList){
                pin = LatLng(location.geometry.location.lat, location.geometry.location.lng)
                name = location.name
                mMap.addMarker(MarkerOptions().position(pin).title(name))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.application_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_mapsFragment_to_favoritesFragment)
        return super.onOptionsItemSelected(item)
    }

}