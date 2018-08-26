package app.ecomm.ui.di.modules

import app.ecomm.ui.fragment.DetailFragment
import app.ecomm.ui.fragment.MainFragment
import app.ecomm.ui.fragment.RankFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * DI module for Fragments.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    internal abstract fun contributeRankFragment(): RankFragment
}