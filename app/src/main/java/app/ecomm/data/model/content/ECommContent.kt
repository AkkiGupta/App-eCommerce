package app.ecomm.data.model.content

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

/**
 *
 * Table for our data
 *
 */
@Entity
data class ECommContent(
        @Embedded
        @SerializedName("categories")
        var categories: List<Categories>? = null,
        @Embedded
        @SerializedName("rankings")
        var rankings: List<Ranking>? = null)