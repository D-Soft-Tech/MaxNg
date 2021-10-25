package com.example.maxng.localDB

import com.example.maxng.models.mapper.Domain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDaoImplementation @Inject constructor(
    private val localDao: LocalDao
) {

    fun getAllData(category: String): Flow<List<Domain>> {
        return localDao.getData(category)
    }

    suspend fun insert(data: Domain) {
        localDao.insert(data)
    }

    suspend fun update(data: Domain) {
        localDao.update(data)
    }

    fun getLiked(check: Int): Flow<List<Domain>> =
        localDao.getLiked(check)
}
