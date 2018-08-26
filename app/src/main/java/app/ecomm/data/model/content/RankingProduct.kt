package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RankingProduct(
        @PrimaryKey(autoGenerate = true)
        var pId: Int,
        @SerializedName("id")
        var id: Int = -1,
        @SerializedName("order_count")
        var order_count: Long = 0,
        @SerializedName("view_count")
        var view_count: Long = 0,
        @SerializedName("shares")
        var shares: Long = 0)