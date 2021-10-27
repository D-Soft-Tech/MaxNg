package com.example.maxng.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.R
import com.example.maxng.constants.AppConstants.hide
import com.example.maxng.constants.AppConstants.show
import com.example.maxng.databinding.FragmentSpeciesBinding
import com.example.maxng.models.mapper.Domain
import com.example.maxng.ui.adapters.LikeOnClick
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Species : Fragment(), LikeOnClick {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentSpeciesBinding
    private lateinit var myAdapter: StarWarsRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_species, container, false)
        myAdapter = StarWarsRecyclerViewAdapter(this, arrayListOf())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = binding.progressBar

        progressBar.show()

        // Fetch from database
        viewModel.fetchFromDataBase("Species")

        // Set data to the adapter
        viewModel.singleLiveData.observe(
            viewLifecycleOwner,
            {
                progressBar.hide()
                myAdapter.setStarWars(it)
            }
        )
        // View initialization
        recyclerView = binding.filmsRv
        recyclerView.apply {
            itemAnimator?.changeDuration = 0
            adapter = myAdapter
        }
        binding.apply { lifecycleOwner = viewLifecycleOwner }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun favourite(view: ImageView, data: Domain) {
        val likedDrawable = resources.getDrawable(
            R.drawable.ic_liked,
            requireContext().theme
        )
        viewModel.showFavourite((view.drawable == likedDrawable), data)
    }
}
