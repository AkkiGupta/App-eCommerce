package app.ecomm.data.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Observer
import android.view.ViewGroup

/**
 * Created by VipulKumar on 2/7/18.
 * Helpful extension functions for data manipulation.
 */

/** Get children of a [ViewGroup] */
val ViewGroup.children: List<*>
    get() = (0 until childCount).map { getChildAt(it) }

/** Check [Collection] for null or empty */
fun Collection<*>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

/** Check [Collection] for null or empty */
fun Collection<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/** Check [String] for null or empty */
fun String?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

/** Check [String] for null or empty */
fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

fun <T> LiveData<T>.getDistinct(): LiveData<T> {
    val distinctLiveData = MediatorLiveData<T>()
    distinctLiveData.addSource(this, object : Observer<T> {
        private var initialized = false
        private var lastObj: T? = null
        override fun onChanged(obj: T?) {
            if (!initialized) {
                initialized = true
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            } else if ((obj == null && lastObj != null)
                    || obj != lastObj) {
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            }
        }
    })
    return distinctLiveData
}
