package app.ecomm.data.util

import android.os.SystemClock
import android.support.v4.util.ArrayMap

import java.util.concurrent.TimeUnit

/**
 * Utility class that decides whether we should fetch the data based on a given timeout.
 */
class RateLimiter<in KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = ArrayMap<KEY, Long>()
    private val timeout: Long = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Boolean {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }
        return false
    }

    private fun now(): Long {
        return SystemClock.uptimeMillis()
    }

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }
}
