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
        JsonTypeConverters.IntTypeConverters::class,
        JsonTypeConverters.IntListTypeConverters::class,
        JsonTypeConverters.ProductTypeConverters::class,
        JsonTypeConverters.CategoriesTypeConverters::class,
        JsonTypeConverters.ProductListTypeConverters::class,
        JsonTypeConverters.CategoriesListTypeConverters::class,
        JsonTypeConverters.VariantTypeConverters::class,
        JsonTypeConverters.VariantListTypeConverters::class,
        JsonTypeConverters.TaxTypeConverters::class,
        JsonTypeConverters.RankingListTypeConverters::class,
        JsonTypeConverters.RankingTypeConverters::class,
        JsonTypeConverters.RankingProductListTypeConverters::class,
        JsonTypeConverters.RankingProductTypeConverters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun contentDao(): ContentDao
}