package app.ecomm.ui.adapter

import app.ecomm.ItemCategoriesHeaderBindingModel_
import app.ecomm.ItemCategoriesSeparatorBindingModel_
import app.ecomm.ItemProductCardBindingModel_
import app.ecomm.data.model.content.*
import app.ecomm.util.epoxy.VerticalGridCarouselModel_
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import java.util.*


class RankWiseAdapter constructor(val itemClickListener: ItemClickListener)
    : TypedEpoxyController<List<Ranking>>() {

    override fun buildModels(list: List<Ranking>?) {
        setFilterDuplicates(true)

        list?.forEach { ranking ->
            ranking?.let { it ->
                addHeadingItem(it)
                val models = addRankingProductItems(it).distinct()
                addCarouselModels(it, models, 2.4f)
                addSeparatorItem(it)
            }
        }
    }

    private fun addHeadingItem(it: Ranking) {
        ItemCategoriesHeaderBindingModel_()
                .id("${it.pid} heading")
                .text(it.name)
                .addTo(this)
    }

    private fun addRankingProductItems(ranking: Ranking): ArrayList<ItemProductCardBindingModel_> {
        val models = arrayListOf<ItemProductCardBindingModel_>()
        ranking?.products?.forEach { product ->
            models.add(ItemProductCardBindingModel_()
                    .id(product.id!!)
                    .itemClickListener { model, parentView, clickedView, position ->
                        itemClickListener.onContentItemClicked(model.product())
                    }
                    .product(Product(product.id, "Click on me to see details")))
        }
        return models
    }

    private fun addCarouselModels(it: Ranking, models: List<EpoxyModel<*>>, noOnScreen: Float) {
        CarouselModel_()
                .id("${it.name} carousel")
                .models(models)
                .numViewsToShowOnScreen(noOnScreen)
                .addTo(this)
    }

    private fun addSeparatorItem(it: Ranking) {
        ItemCategoriesSeparatorBindingModel_()
                .id("${it.name} sep")
                .addTo(this)
    }

    interface ItemClickListener {
        fun onContentItemClicked(prodcut: Product)
    }
}