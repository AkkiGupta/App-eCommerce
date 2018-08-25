package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Categories(
        @PrimaryKey
        @SerializedName("id")
        var id: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("products")
        var products: List<Product>? = null,
        @SerializedName("child_categories")
        var child_categories: List<Int>? = null)