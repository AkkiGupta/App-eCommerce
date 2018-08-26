package app.ecomm.ui.adapter

import app.ecomm.ItemCategoriesHeaderBindingModel_
import app.ecomm.ItemCategoriesSeparatorBindingModel_
import app.ecomm.ItemProductCardBindingModel_
import app.ecomm.ItemSubcategoryCardBindingModel_
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.Product
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.Typed2EpoxyController
import java.util.*


class HomeAdapter constructor(val itemClickListener: ItemClickListener)
    : Typed2EpoxyController<List<Categories>, String>() {
    var argString = ""

    override fun buildModels(list: List<Categories>?, argString: String) {
        setFilterDuplicates(true)
        this.argString = argString

        // logic to add row items is temporary and for demo purposes only
        list?.forEach {it ->
            it?.let {category ->
                val models = addProductItems(category).distinct()
                addHeadingItem(category)
                addCarouselModels(category, models, 2.4f)
                if(category.superCategoryType){
                }
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

//    private fun addSubCategoryItems(categories: Categories): ArrayList<ItemSubcategoryCardBindingModel_> {
//        val models = arrayListOf<ItemSubcategoryCardBindingModel_>()
//        categories?.products?.forEach {product ->
//            models.add(ItemSubcategoryCardBindingModel_()
//                    .id(product.id!!)
//                    .itemClickListener { model, parentView, clickedView, position ->
//                        itemClickListener.onContentItemClicked(model.product())
//                    }
//                    .product(product))
//        }
//        return models
//    }

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