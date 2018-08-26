package app.ecomm.data.db

import android.arch.persistence.room.TypeConverter
import app.ecomm.data.model.content.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

/**
 * Type converters to be used for database entities.
 */

class JsonTypeConverters {

    class IntTypeConverters {
        @TypeConverter
        fun stringToObject(data: String?): Int? {
            return Gson().fromJson(data, Int::class.java)
        }

        @TypeConverter
        fun objectToString(stringList: Int?): String? {
            return Gson().toJson(stringList)
        }
    }

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

    class IntListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<Int>? {
            val listType = object : TypeToken<ArrayList<Int>>() {

            }.type
            return Gson().fromJson<List<Int>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<Int>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class CategoriesListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<Categories>? {
            val listType = object : TypeToken<ArrayList<Categories>>() {

            }.type
            return Gson().fromJson<List<Categories>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<Categories>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class ProductListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<Product>? {
            val listType = object : TypeToken<ArrayList<Product>>() {

            }.type
            return Gson().fromJson<List<Product>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<Product>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class VariantListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<Variant>? {
            val listType = object : TypeToken<ArrayList<Variant>>() {

            }.type
            return Gson().fromJson<List<Variant>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<Variant>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class RankingListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<Ranking>? {
            val listType = object : TypeToken<ArrayList<Ranking>>() {

            }.type
            return Gson().fromJson<List<Ranking>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<Ranking>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }

    class RankingProductListTypeConverters {
        @TypeConverter
        fun stringToList(data: String): List<RankingProduct>? {
            val listType = object : TypeToken<ArrayList<RankingProduct>>() {

            }.type
            return Gson().fromJson<List<RankingProduct>>(data, listType)
        }

        @TypeConverter
        fun listToString(stringList: List<RankingProduct>?): String {
            val gson = Gson()
            return gson.toJson(stringList)
        }
    }
}
