package app.ecomm.data.api

import android.arch.lifecycle.LiveData
import app.ecomm.data.model.content.ECommContent
import retrofit2.http.*
import app.ecomm.data.api.model.ApiResponse

/**
 * REST API access points
 */

interface MiddlewareAPi {
    @GET
    fun getContent(@Url url: String): LiveData<ApiResponse<ECommContent>>
}
