package com.itsm3.baladtest.data.datasource.explore

import android.util.Log
import androidx.paging.PagedList
import com.itsm3.baladtest.data.common.ioScheduler
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.data.datasource.explore.remote.IExploreApiDataSource
import com.itsm3.baladtest.domain.entity.VenuesEntity
import javax.inject.Inject

class ExplorePagedListBoundaryCallback @Inject constructor(
    private val apiDataSource: IExploreApiDataSource,
    private val dbDataSource: IExploreDbDataSource,
) : PagedList.BoundaryCallback<VenuesEntity.Explore>() {

    var latLng: String = "40.6243, -74.0018"
    var limit: Int = 30
    var radius: Int = 300

    private var dbCleared: Boolean = false
    private var offset = 0
    private var allPagesGrabbed = false //to know when we fetch all data from server
    private var isFirstAttemp = true

    /**
     * DB returned zero items, we must call service api to get more items
     */
    override fun onZeroItemsLoaded() {
        isFirstAttemp = false
        if (!dbCleared)
            requestData(false)
        dbCleared = false
    }

    /**
     * ٌWe reached at the end of the db, all db items were loaded
     */
    override fun onItemAtEndLoaded(itemAtEnd: VenuesEntity.Explore) {
        requestData(false)
    }

    override fun onItemAtFrontLoaded(itemAtFront: VenuesEntity.Explore) {
        if (isFirstAttemp)
            requestData(true)
    }

    private fun requestData(firstLoad: Boolean) {
        if (allPagesGrabbed) return

        isFirstAttemp = false // we don't call api ourselves again, just at first time
        apiDataSource.explore(latLng, limit, radius, offset)
            .ioScheduler()
            .subscribe({
                if (it.isEmpty() || it.size < limit) {
                    allPagesGrabbed = true // and we don't call api service any more
                }
                saveToDb(it, firstLoad)
            }, {
                Log.e("Balad-Network", "error:" + offset) //TODO handle it
            })
    }

    private fun saveToDb(
        it: List<VenuesEntity.Explore>,
        firstLoad: Boolean
    ) {
        if (firstLoad) {
            dbCleared = true
            dbDataSource.removeAll() {
                dbDataSource.persist(it) {
                    offset++
                }
            }
        } else
            dbDataSource.persist(it) {
                offset++
            }
    }
}