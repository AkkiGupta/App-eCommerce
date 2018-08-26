package com.airtel.vision.di

import android.app.Application
import app.ecomm.core.Core
import com.airtel.vision.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(ApplicationModule::class), (AndroidInjectionModule::class)])
 interface CoreAppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreAppComponent
    }
    fun inject(core: Core):Core
    fun inject(app: Application)
}
