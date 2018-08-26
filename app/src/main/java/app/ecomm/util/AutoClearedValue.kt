package app.ecomm.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * A value holder that automatically clears the reference if the Fragment's view is destroyed.
 * Prevents memory leaks for objects that need to be cleared after the lifecycle of a fragment.
 * @param <T>
</T> */
class AutoClearedValue<T>(fragment: Fragment, private var value: T?) {
    init {
        val fragmentManager = fragment.fragmentManager
        fragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentViewDestroyed(fm: FragmentManager?, f: Fragment?) {
                        if (f === fragment) {
                            this@AutoClearedValue.value = null
                            fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                        }
                    }
                }, false)
    }

    fun get(): T? {
        return value
    }
}
