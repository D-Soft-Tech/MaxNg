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
import com.example.maxng.databinding.FragmentFilmsBinding
import com.example.maxng.models.mapper.Domain
import com.example.maxng.ui.adapters.LikeOnClick
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Films : Fragment(), LikeOnClick {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentFilmsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: StarWarsRecyclerViewAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films, container, false)

        myAdapter = StarWarsRecyclerViewAdapter(this, arrayListOf())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.filmsRv

        progressBar = binding.progressBar

        recyclerView.apply {
            itemAnimator?.changeDuration = 0
            adapter = myAdapter
        }

        progressBar.show()

        // Fetch from database
        viewModel.fetchFromDataBase("Films")

        // Setting data to adapter
        viewModel.singleLiveData.observe(
            viewLifecycleOwner,
            {
                progressBar.hide()
                myAdapter.setStarWars(it)
            }
        )

        binding.apply { lifecycleOwner = viewLifecycleOwner }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun favourite(view: ImageView, data: Domain) {
        viewModel.showFavourite(data.liked, data)
    }
}
