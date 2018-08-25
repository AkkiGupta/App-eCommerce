package app.ecomm.data.model.content

import com.google.gson.annotations.SerializedName

data class Tax(@SerializedName("name")
               var type: String = "",
               @SerializedName("value")
               var value: Double = 0.0)