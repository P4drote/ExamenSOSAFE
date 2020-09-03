package com.andrescermeno.examensosafe.ui.detailsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrescermeno.examensosafe.R
import com.andrescermeno.examensosafe.data.model.FavoritesEntity
import com.andrescermeno.examensosafe.remote.Place
import com.andrescermeno.examensosafe.vo.Resource
import com.bumptech.glide.Glide
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: DetailsViewModel by viewModels()
    private var urlPicture: String =
        "https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyAh_bAfEqdromedJ2yOECCzkggzolKoI4E&maxwidth=400&photoreference="
    private lateinit var mMap: GoogleMap
    private var locationPlace: LatLng? = null
    private lateinit var place : Place


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            viewModel.placeId.value = it.getString("placeId").toString()
            viewModel.verifyData()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(null)
        mapView.onResume()
        mapView.getMapAsync(this)
        setupObservers()
        setupRecyclerView()
        setupBtnFavorite()
    }

    private fun setupBtnFavorite() {
        iv_favorite.setOnClickListener {
            if (viewModel.addFavorite.value!!) {
                iv_favorite.setImageResource(R.drawable.ic_favorite)
                viewModel.deleteFavorite()
                Toast.makeText(this.requireContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
            }else{
                iv_favorite.setImageResource(R.drawable.ic_favorite_red)
                viewModel.insertFavorite(FavoritesEntity(viewModel.placeId.value!!, place.result.name,
                    urlPicture + place.result.photos[0].photo_reference, place.result.rating, place.result.formatted_address,
                    place.result.geometry.location.lat, place.result.geometry.location.lng))
                viewModel.insertReviews(place.result.reviews)
                Toast.makeText(this.requireContext(), "Agregado a favoritos", Toast.LENGTH_SHORT).show()
            }
            viewModel.addFavorite.value = !viewModel.addFavorite.value!!
        }
    }

    private fun setupRecyclerView() {
        recyclerviewReviews.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerviewReviews.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers() {
        viewModel.favoritePlace.observe(viewLifecycleOwner, { place ->
            if (place != null){
                viewModel.getReviews()
                putLocalData(place)
            }else{
                viewModel.search.value = true
            }
        })

        viewModel.allReviews.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                recyclerviewReviews.adapter = AdapterReviews(this.requireContext(), it)
            }
        })

        viewModel.detailsPlace.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    place = result.data
                    putDataWeb(result.data)
                }
                is Resource.Loading -> {

                }
                is Resource.Failure -> {
                    Toast.makeText(
                        this.requireContext(),
                        "Que pena! Sucedio un error!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }

    private fun putLocalData(place: FavoritesEntity?) {
        locationPlace = LatLng(place!!.latitude, place.longitude)
        putPin(locationPlace!!)
        Glide.with(requireContext()).load(place.picture).centerCrop().into(img_referential)
        tv_name.text = place.placeName
        tv_ratingNumber.text = place.rating.toString()
        ratingBar.rating = place.rating.toFloat()
        tv_Direction.text = place.direction
        iv_favorite.setImageResource(R.drawable.ic_favorite_red)
        viewModel.addFavorite.value = true
    }


    private fun putDataWeb(data: Place) {
        val completeURL = urlPicture + data.result.photos[0].photo_reference
        locationPlace = LatLng(data.result.geometry.location.lat, data.result.geometry.location.lng)
        putPin(locationPlace!!)
        Glide.with(requireContext()).load(completeURL).centerCrop().into(img_referential)
        tv_name.text = data.result.name
        tv_ratingNumber.text = data.result.rating.toString()
        ratingBar.rating = data.result.rating.toFloat()
        tv_Direction.text = data.result.formatted_address
        recyclerviewReviews.adapter = AdapterReviews(this.requireContext(), data.result.reviews)
    }

    private fun putPin(locationPlace: LatLng) {
        if(mMap != null){
            mMap.uiSettings?.isZoomControlsEnabled = true
            mMap.addMarker(MarkerOptions().position(locationPlace).title(tv_name.text.toString()))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationPlace))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationPlace, 20.0F))
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        MapsInitializer.initialize(this.requireContext())
        if (googleMap != null) {
            mMap = googleMap
        }
    }



}