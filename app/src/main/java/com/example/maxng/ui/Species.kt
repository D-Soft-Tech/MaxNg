package com.example.maxng.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.R
import com.example.maxng.databinding.FragmentSpeciesBinding
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Species : Fragment() {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentSpeciesBinding
    private lateinit var adapter: StarWarsRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_species, container, false)
        adapter = StarWarsRecyclerViewAdapter()

        // Set data to the adapter
        viewModel.speciesStarWars.observe(
            viewLifecycleOwner,
            {
                Log.d("Checking", it.toString())
                adapter.setStarWars(it)
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // View initialization
        recyclerView = binding.filmsRv
        recyclerView.adapter = adapter
        binding.apply { lifecycleOwner = viewLifecycleOwner }
    }
}
