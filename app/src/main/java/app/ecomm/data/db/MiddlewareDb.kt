package app.ecomm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import app.ecomm.data.model.content.*

/**
 * Database description.
 */
@Database(entities = [ECommContent::class, Categories::class, Product::class, Variant::class,
    Ranking::class, RankingProduct::class],
        version = 1)
@TypeConverters(
        MiddlewareTypeConverters::class,
        MiddlewareTypeConverters.ProductTypeConverters::class,
        MiddlewareTypeConverters.CategoriesTypeConverters::class,
        MiddlewareTypeConverters.ProductListTypeConverters::class,
        MiddlewareTypeConverters.CategoriesListTypeConverters::class,
        MiddlewareTypeConverters.VariantTypeConverters::class,
        MiddlewareTypeConverters.VariantListTypeConverters::class,
        MiddlewareTypeConverters.TaxTypeConverters::class,
        MiddlewareTypeConverters.RankingListTypeConverters::class,
        MiddlewareTypeConverters.RankingTypeConverters::class,
        MiddlewareTypeConverters.RankingProductListTypeConverters::class,
        MiddlewareTypeConverters.RankingProductTypeConverters::class)
abstract class MiddlewareDb : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}