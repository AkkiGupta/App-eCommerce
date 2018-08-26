package app.ecomm.ui.adapter

import app.ecomm.ItemCategoriesHeaderBindingModel_
import app.ecomm.ItemCategoriesSeparatorBindingModel_
import app.ecomm.ItemProductCardBindingModel_
import app.ecomm.ItemSubcategoryCardBindingModel_
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.ChildCategories
import app.ecomm.data.model.content.Product
import app.ecomm.util.isNotNullOrEmpty
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.Typed2EpoxyController
import java.util.*


class HomeAdapter constructor(val itemClickListener: ItemClickListener)
    : Typed2EpoxyController<List<Categories>, String>() {
    var argString = ""
    var catIdMap = mutableMapOf<Int, String>()

    override fun buildModels(list: List<Categories>?, argString: String) {
        setFilterDuplicates(true)
        this.argString = argString

        // logic to add row items is temporary and for demo purposes only
        list?.forEach {it ->
            it?.let {category ->
                addHeadingItem(category)
                if(category.child_categories.isNotNullOrEmpty()){
                    var subCatModels = addSubCategoryItems(category).distinct()
                    addCarouselModels(category, subCatModels, 3.5f)
                }else{
                    val models = addProductItems(category).distinct()
                    addCarouselModels(category, models, 2.4f)
                }
                addSeparatorItem(it)
            }
        }
    }

    private fun addHeadingItem(it: Categories) {
        catIdMap[it?.id] = it.name
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

    private fun addSubCategoryItems(categories: Categories): ArrayList<ItemSubcategoryCardBindingModel_> {
        val models = arrayListOf<ItemSubcategoryCardBindingModel_>()
        categories?.child_categories?.forEach { childCatId ->
            models.add(ItemSubcategoryCardBindingModel_()
                    .id(childCatId)
                    .itemClickListener { model, parentView, clickedView, position ->
                        itemClickListener.onSubCategoryItemClicked(model.category())
                    }
                    .category(ChildCategories(childCatId, catIdMap[childCatId]?: "Name Not Available")))
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
        fun onSubCategoryItemClicked(childCategory: ChildCategories)
    }
}