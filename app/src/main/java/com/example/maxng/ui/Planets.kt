package com.example.maxng.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.R
import com.example.maxng.databinding.FragmentPlanetsBinding
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Planets : Fragment() {

    private lateinit var binding: FragmentPlanetsBinding
    private val viewModel: AppViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StarWarsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planets, container, false)

        adapter = StarWarsRecyclerViewAdapter()

        // Set data to adapter
        viewModel.planetsStarWars.observe(
            viewLifecycleOwner,
            {
                adapter.setStarWars(it)
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.filmsRv
        recyclerView.adapter = adapter
        binding.apply { lifecycleOwner = viewLifecycleOwner }
    }
}
