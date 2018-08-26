package app.ecomm.data.di

import android.app.Application
import android.arch.persistence.room.Room
import app.ecomm.data.db.AppDb
import dagger.Module
import dagger.Provides
import app.ecomm.data.api.MiddlewareAPi
import app.ecomm.data.api.RetrofitProvider
import app.ecomm.data.db.ContentDao
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofitService12(application: Application): MiddlewareAPi {
        return RetrofitProvider
                .provideDefaultRetrofit(application)
                .create(MiddlewareAPi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideDb(app: Application): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    internal fun provideContentDao(db: AppDb): ContentDao {
        return db.contentDao()
    }
}
