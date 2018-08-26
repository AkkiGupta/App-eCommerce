package com.airtel.vision.di

import android.app.Application
import app.ecomm.core.Core

object DaggerInjector {

    fun injectAll(application: Application, core: Core) {
        val visionAppComponent = DaggerCoreAppComponent.builder()
                .application(application)
                .build()
        visionAppComponent.inject(application)
        visionAppComponent.inject(core)
    }
}