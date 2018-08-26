package app.ecomm.util.epoxy

import com.airbnb.epoxy.Carousel

object EpoxyGlobalSettings {
    fun setSnapHelper() {
        Carousel.setDefaultGlobalSnapHelperFactory(null)
    }
}
