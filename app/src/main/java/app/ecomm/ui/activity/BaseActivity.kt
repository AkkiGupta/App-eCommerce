package app.ecomm.ui.activity

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Top-level base activity.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    /** Returns an [AndroidInjector] of [Fragment]s associated with this activity.  */
    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment>? {
        return dispatchingAndroidInjector
    }
}
