package com.itsm3.baladtest.domain.common

import androidx.paging.PagedList
import com.itsm3.baladtest.domain.entity.VenuesEntity

/**
 *Wrapper for DB and Network states
 */
sealed class ResultState<T> {
    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(val data: T) : ResultState<T>()

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()


    data class End<T>(val data: T) : ResultState<T>()

    data class FirstLoad<T>(val data: T?) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Fail<T>(val throwable: Throwable, val data: T?) : ResultState<T>()
}