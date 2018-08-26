package app.ecomm.data.model.content

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Int = -1,
        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String = "",
        @ColumnInfo(name = "date_added")
        @SerializedName("date_added")
        var date_added: String = "",
        @SerializedName("variants")
        var variants: List<Variant>? = null,
        @ColumnInfo(name = "tax")
        @SerializedName("tax")
        var tax: Tax? = null)