package com.airtel.vision.di.modules

import app.ecomm.data.di.NetworkModule
import dagger.Module

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = [NetworkModule::class])
abstract class ApplicationModule {
}
