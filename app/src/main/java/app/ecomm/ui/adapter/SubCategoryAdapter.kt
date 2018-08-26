package app.ecomm.ui.adapter

import app.ecomm.ItemCategoriesHeaderBindingModel_
import app.ecomm.ItemCategoriesSeparatorBindingModel_
import app.ecomm.ItemHomeFooterBindingModel_
import app.ecomm.ItemProductCardBindingModel_
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.Product
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.Typed2EpoxyController
import java.util.*


class SubCategoryAdapter constructor(val itemClickListener: ItemClickListener)
    : Typed2EpoxyController<List<Categories>, String>() {
    var argString = ""

    override fun buildModels(list: List<Categories>?, argString: String) {
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
        list?.forEach {it ->
            it?.let {category ->

//                addHeadingItem(category)

                val models = addProductItems(category).distinct()
                addHeadingItem(category)
                addCarouselModels(category, models, 2.4f)
                addSeparatorItem(it)
            }



            /*if (it.contents?.content != null && it.subType != null) {
                when {
                    it.subType?.name?.startsWith("TVSHOW", true)!! -> {
                        if (it.contents?.content.isNotNullOrEmpty()
                                && it.contents?.content!![0].programType == "LIVETVCHANNEL") {
                            // do not add
                        } else {
                            val models = addLandscapeItems(it).distinct()
                            addHeadingItem(it)
                            addCarouselModels(it, models, 1.5f)
                            addSeparatorItem(it)
                        }
                    }

                    it.subType == RowSubType.BANNER -> {
                        val models = addBannerItems(it).distinct()
                        addCarouselModels(it, models, 1.03f)
                    }

                    it.subType == RowSubType.CONTINUE_WATCHING -> {
                        val models = addContinueWatchingItems(it).distinct()
                        it.title = "Continue Watching"
                        addHeadingItem(it)
                        addCarouselModels(it, models, 2.75f)
                        addSeparatorItem(it)
                    }

                    else -> {
                        val models = addPortraitItems(it).distinct()
                        addHeadingItem(it)
                        addCarouselModels(it, models, 2.7f)
                        addSeparatorItem(it)
                    }
                }
            }*/
        }

        /*ItemHomeFooterBindingModel_()
                .id("id_footer")
                .addTo(this)*/
    }

    private fun addHeadingItem(it: Categories) {
        ItemCategoriesHeaderBindingModel_()
                .id("${it.id} heading")
                .text(it.name)
                .addTo(this)
    }

    private fun addProductItems(categories: Categories): ArrayList<ItemProductCardBindingModel_> {
        val models = arrayListOf<ItemProductCardBindingModel_>()
        categories?.products?.forEach {product ->
            models.add(ItemProductCardBindingModel_()
                    .id(product.id!!)
                    .itemClickListener { model, parentView, clickedView, position ->
                        itemClickListener.onContentItemClicked(model.product())
                    }
                    .product(product))
        }
        return models
    }

    private fun addCarouselModels(it: Categories, models: List<EpoxyModel<*>>, noOnScreen: Float) {
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
        fun onContentItemClicked(prodcut: Product)
    }
}