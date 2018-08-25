package app.ecomm.data.api.model

const val RESOLUTION_PLEASE_RETRY = "Please retry"

class Error(val errorCode: Int, val message: String?, val resolution: String? = "") {

    companion object {
        fun build(errorValue: ErrorValue, code: Int = errorValue.code,
                  message: String? = errorValue.message, resolution: String? = errorValue.resolution): Error {
            return Error(code, message, resolution)
        }
    }

    enum class ErrorValue(val code: Int, val message: String?, val resolution: String?) {
        ERROR_NETWORK_ERROR(0, "", RESOLUTION_PLEASE_RETRY),
    }
}