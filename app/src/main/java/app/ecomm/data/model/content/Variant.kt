package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Variant(
        @PrimaryKey
        @SerializedName("id")
        var id: String = "",
        @SerializedName("color")
        var color: String = "",
        @SerializedName("size")
        var size: Int = 0,
        @SerializedName("price")
        var price: Int = 0
)