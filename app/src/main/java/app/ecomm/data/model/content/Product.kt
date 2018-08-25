package app.ecomm.data.model.content

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
        @PrimaryKey
        @SerializedName("id")
        var id: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("date_added")
        var date_added: String = "",
        @Embedded
        @SerializedName("variants")
        var variants: List<Variant>? = null,
        @SerializedName("tax")
        var tax: Tax? = null)