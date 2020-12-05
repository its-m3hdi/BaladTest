package com.itsm3.baladtest.data.datasource.explore.local

import androidx.paging.DataSource
import com.itsm3.baladtest.data.db.explore.IExploreDao
import com.itsm3.baladtest.domain.entity.VenuesEntity
import java.util.concurrent.Executor
import javax.inject.Inject

class ExploreDbDataSourceImp @Inject constructor(
    private val exploreDao: IExploreDao,
    private val ioExecutor: Executor
) : IExploreDbDataSource {

    override fun getExploreDbDataSourceFactory(): DataSource.Factory<Int, VenuesEntity.Explore> =
        exploreDao.selectAllPages().map { it.map() }

    override fun persist(exploreList: List<VenuesEntity.Explore>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            exploreDao.insert(exploreList.map { it.map() })
            insertFinished()
        }
    }

    override fun removeAll(finished: () -> Unit) {
        ioExecutor.execute {
            exploreDao.truncate()
            exploreDao.resetId()
            finished()
        }

    }

}