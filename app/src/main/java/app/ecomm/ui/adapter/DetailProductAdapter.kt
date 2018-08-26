package app.ecomm.ui.adapter

import app.ecomm.ItemCategoriesHeaderBindingModel_
import app.ecomm.ItemCategoriesSeparatorBindingModel_
import app.ecomm.ItemProductVariantBindingModel_
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.Product
import app.ecomm.data.model.content.Variant
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.Typed2EpoxyController
import java.util.ArrayList

class DetailProductAdapter constructor(val itemClickListener: ItemClickListener)
    : Typed2EpoxyController<Product, String>() {
    var argString = ""

    override fun buildModels(product: Product?, argString: String) {
        setFilterDuplicates(true)
        this.argString = argString
        /*var pageIDs: List<PageId>? = AtvSdk.getInstance().getPageIDs()
        val titleMap = mapOf(pageIDs?.get(0)?.id to pageIDs?.get(0)?.title,
                pageIDs?.get(1)?.id to "Explore", pageIDs?.get(2)?.id to pageIDs?.get(2)?.title, pageIDs?.get(3)?.id to pageIDs?.get(3)?.title)

        // show search bar on explore page
        if (titleMap[pageId].equals("Explore")) {
            ItemHomeSearchBindingModel_()
                    .id("id_search")
                    .addTo(this)
        } else {
            ItemHomeHeadingBindingModel_()
                    .id("id_title")
                    .text(titleMap[pageId])
                    .addTo(this)
        }*/

        // logic to add row items is temporary and for demo purposes only
        product?.let {product ->

            addHeadingItem(product)
            val models = addVariantItems(product)
            addCarouselModels(product, models, 2.8f)
        }
    }

    private fun addHeadingItem(it: Product) {
        ItemCategoriesHeaderBindingModel_()
                .id("${it.id} heading")
                .text("More Variants")
                .addTo(this)
    }

    private fun addVariantItems(product: Product): ArrayList<ItemProductVariantBindingModel_> {
        val models = arrayListOf<ItemProductVariantBindingModel_>()
        product?.variants?.forEach {variant ->
            models.add(ItemProductVariantBindingModel_()
                    .id(variant.id!!)
                    .itemClickListener { model, parentView, clickedView, position ->
                        itemClickListener.onContentItemClicked(model.variant())
                    }
                    .variant(variant))
        }
        return models
    }

    private fun addCarouselModels(it: Product, models: List<EpoxyModel<*>>, noOnScreen: Float) {
        CarouselModel_()
                .id("${it.id} carousel")
                .models(models)
                .numViewsToShowOnScreen(noOnScreen)
                .addTo(this)
    }

    private fun addSeparatorItem(it: Categories) {
        ItemCategoriesSeparatorBindingModel_()
                .id("${it.id} sep")
                .addTo(this)
    }

    interface ItemClickListener {
        fun onContentItemClicked(variant: Variant)
    }
}