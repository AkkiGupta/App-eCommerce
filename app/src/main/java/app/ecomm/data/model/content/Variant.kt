package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Variant(
        @PrimaryKey(autoGenerate = true)
        var pid:Int,
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("color")
        var color: String = "",
        @SerializedName("size")
        var size: Int = 0,
        @SerializedName("price")
        var price: Int = 0)