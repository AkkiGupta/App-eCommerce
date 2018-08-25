package app.ecomm.data.repo

import app.ecomm.data.util.NetworkConstants


abstract class Repository {
    protected fun contentListUrl() = NetworkConstants.MIDDLEWARE_END_POINT + "json"
}