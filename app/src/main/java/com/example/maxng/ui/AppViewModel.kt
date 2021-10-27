package com.example.maxng.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maxng.api.Repository
import com.example.maxng.models.mapper.Domain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _singleLiveData: MutableLiveData<List<Domain>> = MutableLiveData()
    val singleLiveData: LiveData<List<Domain>> get() = _singleLiveData

    fun showFavourite(
        check: Boolean,
        currentData: Domain
    ) {

        if (check) {
            currentData.apply {
                liked = false
            }
            like(currentData)
        } else {
            currentData.apply { liked = true }
            like(currentData)
        }
    }

    fun fetchFromDataBase(
        category: String
    ) {
        viewModelScope.launch {
            repository.fetchFromRoom(true, "Vehicles")?.collect {
                if (it.isEmpty()) {
                    val hasFetchedAndCached = repository.fetchResultsAndSaveToRoom()

                    if (hasFetchedAndCached) {
                        repository.fetchFromRoom(hasFetchedAndCached, category)
                            ?.collect { dataFromLocalDB ->
                                _singleLiveData.postValue(dataFromLocalDB)
                            }
                    }
                } else {
                    repository.fetchFromRoom(true, category)
                        ?.collect { dataFromLocalDB ->
                            _singleLiveData.postValue(dataFromLocalDB)
                        }
                }
            }
        }
    }

    private fun like(data: Domain) {
        viewModelScope.launch {
            repository.updateLike(data)
        }
    }

    fun getLiked() {
        viewModelScope.launch {
            repository.getLiked(1).collect { likedData ->
                _singleLiveData.postValue(likedData)
            }
        }
    }
}
