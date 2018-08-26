package app.ecomm.util.epoxy

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

/**
 * Carousels can be subclassed for various customizations.
 * We have created our own Carousel making it suitable for a grid.
 * [@ModelView] generates a model for this carousel.
 */
@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_MATCH_HEIGHT)
internal class VerticalGridCarousel(context: Context) : Carousel(context) {

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
    }
}