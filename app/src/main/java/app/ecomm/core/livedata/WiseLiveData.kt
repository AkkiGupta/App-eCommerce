package app.ecomm.core.livedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData

/**
 * [LiveData] that goes as return type of every API that this Core dispatches to UI.
 */
open class WiseLiveData<T> : MediatorLiveData<T>() {
    private var cleanup: (() -> Unit)? = null

    fun addCleanupListener(cleanupListener: () -> Unit) {
        cleanup = cleanupListener
    }

    /**
     * clear the references when this [LiveData] is not needed anymore.
     */
    override fun onInactive() {
        super.onInactive()
        if (!hasObservers() && cleanup != null) {
            cleanup?.invoke()
        }
    }

    /*
    methods to dispatch values. although they might seem redundant,
    it will easier in future to modify all dispatches of a kind.
    */

    fun dispatchSuccess(resource: T) {
        value = resource
    }

    fun dispatchLoading(resource: T) {
        value = resource
    }

    fun dispatchError(resource: T) {
        value = resource
    }

    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): WiseLiveData<T> {
            return WiseLiveData()
        }
    }
}