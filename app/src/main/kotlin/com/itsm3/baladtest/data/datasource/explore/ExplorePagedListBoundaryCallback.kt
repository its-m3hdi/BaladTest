package com.itsm3.baladtest.data.datasource.explore

import android.util.Log
import android.widget.Toast
import androidx.paging.PagedList
import com.itsm3.baladtest.data.common.ioScheduler
import com.itsm3.baladtest.data.datasource.explore.local.IExploreDbDataSource
import com.itsm3.baladtest.data.datasource.explore.remote.IExploreApiDataSource
import com.itsm3.baladtest.domain.common.ResultState
import com.itsm3.baladtest.domain.entity.VenuesEntity
import io.reactivex.Flowable
import javax.inject.Inject

class ExplorePagedListBoundaryCallback @Inject constructor(
    private val apiDataSource: IExploreApiDataSource,
    private val dbDataSource: IExploreDbDataSource,
) : PagedList.BoundaryCallback<VenuesEntity.Explore>() {

    private var latLng: String = ""
    private var limit: Int = -1
    private var radius: Int = -1

    private var offset = 0
    private var allPagesGrabbed = false //to know when we fetch all data from server
    private var firstRequestPending = true

    /**
     * ٌWe reached at the end of the db, all db items were loaded
     */
    override fun onItemAtEndLoaded(itemAtEnd: VenuesEntity.Explore) {
        requestData(false)
    }

    private fun requestData(firstLoad: Boolean) {
        if (allPagesGrabbed) return
        if (firstRequestPending)
            return

        apiDataSource.explore(latLng, limit, radius, offset)
            .ioScheduler()
            .subscribe({
                if (it.isEmpty() || it.size < limit) {
                    allPagesGrabbed = true // and we don't call api service any more
                }
                saveToDb(it, firstLoad)
                offset++
            }, {
                Log.e("Balad-Network", "error:" + offset) //TODO handle it
            })
    }

    fun requestNewData(latLng: String, radius: Int, limit: Int) {
        firstRequestPending = true
        if (latLng.isEmpty())
            return

        this.latLng = latLng
        this.radius = radius
        this.limit = limit

        apiDataSource.explore(latLng, limit, radius, offset)
            .ioScheduler()
            .subscribe({
                firstRequestPending = false
                allPagesGrabbed = it.isEmpty() || it.size < limit
                saveToDb(it, true)
                offset++
            }, {
                Log.e("Balad-Network", "error:" + offset) //TODO handle it
            })
    }

    private fun saveToDb(
        it: List<VenuesEntity.Explore>,
        firstLoad: Boolean
    ) {
        if (firstLoad) {
            dbDataSource.removeAll() {
                dbDataSource.persist(it) {
                }
            }
        } else
            dbDataSource.persist(it) {
            }
    }
}