package app.ecomm.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultEntity (
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("success")
    @Expose
    var success: Boolean = false
)