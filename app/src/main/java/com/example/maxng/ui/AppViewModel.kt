package com.example.maxng.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maxng.R
import com.example.maxng.api.Repository
import com.example.maxng.constants.AppConstants
import com.example.maxng.models.mapper.Domain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var _filmsStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val filmsStarWars get(): LiveData<List<Domain>> = _filmsStarWars

    private var _peopleStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val peopleStarWars get(): LiveData<List<Domain>> = _peopleStarWars

    private var _spaceShipsStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val spaceShipsStarWars get(): LiveData<List<Domain>> = _spaceShipsStarWars

    private var _speciesStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val speciesStarWars get(): LiveData<List<Domain>> = _speciesStarWars

    private var _planetsStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val planetsStarWars get(): LiveData<List<Domain>> = _planetsStarWars

    private var _vehiclesStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val vehiclesStarWars get(): LiveData<List<Domain>> = _vehiclesStarWars

    private var _likedStarWars: MutableLiveData<List<Domain>> = MutableLiveData()
    val likedStarWars get(): LiveData<List<Domain>> = _likedStarWars

    init {
        getLiked()
        fetchAllCategories()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @BindingAdapter("favouriteStarWar")
    fun showFavourite(
        imageView: ImageView,
        currentData: Domain
    ) {
        if (currentData.liked) {
            imageView.setImageResource(AppConstants.hearts[1])
        } else {
            imageView.setImageResource(AppConstants.hearts[0])
        }

        imageView.setOnClickListener {
            val cur = ((imageView.drawable) as Drawable)
            val likedDrawable = imageView.context.resources.getDrawable(
                R.drawable.ic_liked,
                imageView.context.theme
            )
            val yetToLikeDrawable = imageView.context.resources.getDrawable(
                R.drawable.ic_yet_to_like,
                imageView.context.theme
            )

            if (cur == likedDrawable) {
                currentData.apply {
                    liked = false
                }
                like(currentData)
                imageView.setImageResource(R.drawable.ic_yet_to_like)
            } else if (cur == yetToLikeDrawable) {
                currentData.apply { liked = true }
                like(currentData)
                imageView.setImageResource(R.drawable.ic_liked)
            }
        }
    }

    private fun fetchAllCategories() {
        fetchFromDataBase("films", _filmsStarWars)
        fetchFromDataBase("people", _peopleStarWars)
        fetchFromDataBase("starShips", _spaceShipsStarWars)
        fetchFromDataBase("species", _speciesStarWars)
        fetchFromDataBase("planets", _planetsStarWars)
        fetchFromDataBase("vehicles", _vehiclesStarWars)
    }

    private fun fetchFromDataBase(
        category: String,
        mutableLiveDataToHoldTheResult: MutableLiveData<List<Domain>>
    ) {
        val hasFetchedAndCached = repository.fetchResultsAndSaveToRoom()

        Log.d("DoneFetching?", hasFetchedAndCached.toString())

        if (hasFetchedAndCached) {
            viewModelScope.launch {
                repository.fetchFromRoom(hasFetchedAndCached, category)
                    ?.collect { dataFromLocalDB ->
                        Log.d("gettingSomethingFromDB?", dataFromLocalDB.size.toString())
                        mutableLiveDataToHoldTheResult.postValue(dataFromLocalDB)
                    }
            }
        }
    }

    private fun like(data: Domain) {
        viewModelScope.launch {
            repository.updateLike(data)
        }
    }

    private fun getLiked() {
        viewModelScope.launch {
            repository.getLiked().collect { likedData ->
                _likedStarWars.postValue(likedData)
            }
        }
    }
}
