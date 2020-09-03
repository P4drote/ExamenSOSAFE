package com.andrescermeno.examensosafe.ui.favoriteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrescermeno.examensosafe.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorites.*

@AndroidEntryPoint
class FavoritesFragment : Fragment(), AdapterPlaces.OnFavoriteClickListener {

    //private lateinit var adapter: Adapter
    private val viewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onResume() {
        viewModel.initializedRecycler()
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.listFavorites.observe(viewLifecycleOwner, { favorites ->
            if (favorites.isEmpty()){
                tv_menssage.visibility = View.VISIBLE
            }else{
                tv_menssage.visibility = View.GONE
                recyclerViewFavorites.adapter = AdapterPlaces(this.requireContext(), favorites, this)
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFavorites.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onItemClick(placeID: String) {
        val bundle = Bundle()
        bundle.putString("placeId", placeID)
        findNavController().navigate(R.id.action_favoritesFragment_to_detailFragment, bundle)
    }

}