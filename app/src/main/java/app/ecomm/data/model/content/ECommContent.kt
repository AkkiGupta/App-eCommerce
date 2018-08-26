package app.ecomm.data.model.content

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Table for our data
 */
@Entity
data class ECommContent(
        @PrimaryKey(autoGenerate = true)
        var pid: Int,
        @SerializedName("categories")
        var categories: List<Categories>? = null,
        @SerializedName("rankings")
        var rankings: List<Ranking>? = null)