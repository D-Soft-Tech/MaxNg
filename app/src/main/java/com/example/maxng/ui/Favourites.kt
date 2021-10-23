package com.example.maxng.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.maxng.databinding.FragmentFavouritesBinding
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Favourites : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AppViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: StarWarsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        myAdapter = StarWarsRecyclerViewAdapter()

        // Setting data to the adapter
        viewModel.likedStarWars.observe(
            viewLifecycleOwner,
            {
                myAdapter.setStarWars(it)
            }
        )

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rv
        recyclerView.apply {
            itemAnimator?.changeDuration = 0
            adapter = myAdapter
        }
        binding.toolbar.setupWithNavController(findNavController())
    }
}
