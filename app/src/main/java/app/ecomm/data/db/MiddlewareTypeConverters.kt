package app.ecomm.data.db

import android.arch.persistence.room.TypeConverter
import app.ecomm.data.model.content.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Type converters to be used for database entities.
 */

class MiddlewareTypeConverters {

    class CategoriesTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Categories? {
            return Gson().fromJson(data, Categories::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Categories?): String? {
            return Gson().toJson(stringList)
        }
    }

    class ProductTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Product? {
            return Gson().fromJson(data, Product::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Product?): String? {
            return Gson().toJson(stringList)
        }
    }

    class VariantTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Variant? {
            return Gson().fromJson(data, Variant::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Variant?): String? {
            return Gson().toJson(stringList)
        }
    }

    class TaxTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Tax? {
            return Gson().fromJson(data, Tax::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Tax?): String? {
            return Gson().toJson(stringList)
        }
    }

    class RankingTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Ranking? {
            return Gson().fromJson(data, Ranking::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Ranking?): String? {
            return Gson().toJson(stringList)
        }
    }

    class RankingProductTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): RankingProduct? {
            return Gson().fromJson(data, RankingProduct::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: RankingProduct?): String? {
            return Gson().toJson(stringList)
        }
    }

    class CategoriesListTypeConverters {
        @TypeConverter
        fun objectToList(data: String): Categories {
            val listType = object : TypeToken<Categories>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        fun ListToObject(stringList: Categories): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class ProductListTypeConverters {
        @TypeConverter
        fun objectToList(data: String): Product {
            val listType = object : TypeToken<Product>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        fun ListToObject(stringList: Product): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class VariantListTypeConverters {
        @TypeConverter
        fun objectToList(data: String): Variant {
            val listType = object : TypeToken<Variant>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        fun ListToObject(stringList: Variant): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class RankingListTypeConverters {
        @TypeConverter
        fun objectToList(data: String): Ranking {
            val listType = object : TypeToken<Ranking>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        fun ListToObject(stringList: Ranking): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class RankingProductListTypeConverters {
        @TypeConverter
        fun objectToList(data: String): RankingProduct {
            val listType = object : TypeToken<RankingProduct>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        fun ListToObject(stringList: RankingProduct): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }
}
