package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Ranking(
        @PrimaryKey(autoGenerate = true)
        var pid:Int,
        @SerializedName("ranking")
        var name: String = "",
        @SerializedName("products")
        var products: List<RankingProduct>? = null)