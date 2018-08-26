package app.ecomm.util

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/** Get children of a [ViewGroup] */
val ViewGroup.children: List<*>
    get() = (0 until childCount).map { getChildAt(it) }

/** Inflate a [ViewGroup] */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/** Inflate a [ViewGroup] with DataBinding */
fun ViewGroup.inflateWithDataBinding(layoutId: Int, attachToRoot: Boolean = false): ViewDataBinding? {
    return DataBindingUtil
            .inflate(LayoutInflater.from(context),
                    layoutId, this, attachToRoot)
}

/** Apply [FragmentTransaction]s */
inline fun FragmentManager.transaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

/** Check [Collection] for null or empty */
fun Collection<*>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

/** Check [Collection] for null or empty */
fun Collection<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}