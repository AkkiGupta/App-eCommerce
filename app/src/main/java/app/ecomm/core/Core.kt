package app.ecomm.core

import android.app.Application
import app.ecomm.util.NoArgSingletonHolder
import com.airtel.vision.di.DaggerInjector
import javax.inject.Singleton

/**
 * Author : Akash Gupta
 * Created On : 25/08/18
 *
 */
@Singleton
class Core private constructor() {
    companion object : NoArgSingletonHolder<Core>(::Core)

//    internal fun init(application: Application){
//        initDagger(application, this)
//    }
//
//    private fun initDagger(application: Application, core: Core) {
//        DaggerInjector.injectAll(application, core)
//    }
}