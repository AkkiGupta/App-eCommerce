package app.ecomm.data.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import app.ecomm.data.util.Objects
import app.ecomm.data.api.model.AppExecutors
import app.ecomm.data.api.model.Resource

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 *
 *
 * You can read more about it in the [Architecture
 * Guide](https://developer.android.com/arch).
 * @param <ResultType>
 * @param <RequestType>
</RequestType></ResultType> */
abstract class NetworkResource<ResultType, RequestType> @MainThread
internal constructor(private val appExecutors: AppExecutors) {

    val result = MediatorLiveData<Resource<ResultType>>()

    @MainThread
    fun setValue(newValue: Resource<ResultType>) {
        if (!Objects.equals(result.value, newValue)) {
            result.value = newValue
        }
    }

    open fun onFetchFailed() {}

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }
}
