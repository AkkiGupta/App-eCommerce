package app.ecomm.ui.di

import android.app.Application
import app.ecomm.EcommApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import app.ecomm.ui.di.modules.ActivityModule
import app.ecomm.ui.di.modules.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule::class), (AndroidInjectionModule::class), (ActivityModule::class),
    (ViewModelModule::class), (NetworkModule::class)])
internal interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(app: EcommApplication)
}
