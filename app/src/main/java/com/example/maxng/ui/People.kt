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
import com.example.maxng.databinding.FragmentPeopleBinding
import com.example.maxng.ui.adapters.StarWarsRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class People : Fragment() {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentPeopleBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StarWarsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)

        adapter = StarWarsRecyclerViewAdapter()

        // Setting data to the adapter
        viewModel.peopleStarWars.observe(
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
