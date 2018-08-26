package app.ecomm

import android.app.Activity
import android.app.Application
import app.ecomm.ui.di.DaggerInjector
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import app.ecomm.util.epoxy.EpoxyGlobalSettings
import javax.inject.Inject

class EcommApplication: Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initDagger()
        initEpoxy()
        initCore()
    }

    private fun initCore() {
//        Core.getInstance().init(this)
    }

    private fun initDagger() {
        // prepare to inject all activities and fragments at application startup
        DaggerInjector.injectAll(this)
    }

    private fun initEpoxy() {
        // make default snapping behaviour in all recyclerViews to 'none'
        EpoxyGlobalSettings.setSnapHelper()
    }
}