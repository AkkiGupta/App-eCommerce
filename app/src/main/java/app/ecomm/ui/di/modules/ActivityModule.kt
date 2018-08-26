package app.ecomm.ui.di.modules

import app.ecomm.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    internal abstract fun contributeMainActivity(): MainActivity
}
