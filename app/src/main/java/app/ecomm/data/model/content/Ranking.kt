package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Ranking(
        @SerializedName("ranking")
        var name: String = "",
        @SerializedName("products")
        var products: List<RankingProduct>? = null)