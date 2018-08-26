package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import app.ecomm.util.isNotNullOrEmpty
import com.google.gson.annotations.SerializedName

@Entity
data class Categories(
        @PrimaryKey
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("products")
        var products: List<Product>,
        @SerializedName("child_categories")
        var child_categories: List<Int>? = null,
        var superCategoryType:Boolean = child_categories.isNotNullOrEmpty())