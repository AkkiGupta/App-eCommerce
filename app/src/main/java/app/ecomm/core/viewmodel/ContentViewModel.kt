package app.ecomm.core.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import app.ecomm.core.livedata.WiseLiveData
import app.ecomm.data.api.model.Error
import app.ecomm.data.api.model.Resource
import app.ecomm.data.api.model.Status
import app.ecomm.data.livedata.AbsentLiveData
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.ECommContent
import app.ecomm.data.model.content.Product
import app.ecomm.data.repo.ContentRepository
import app.ecomm.util.isNotNullOrEmpty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ContentViewModel @Inject
constructor(private val contentRepository: ContentRepository): BaseViewModel(){

    private var contentResponse: WiseLiveData<Resource<ECommContent>> = WiseLiveData()
    private var content: LiveData<Resource<ECommContent>>
    private var contentMutableLiveData = MutableLiveData<Boolean>()

    private var categoriesByIdLD: LiveData<List<Categories>>
    private var categoriesByIdLDMutableLiveData = MutableLiveData<List<Int>>()

    private var productById: LiveData<Product>
    private var productByIdMutableLiveData = MutableLiveData<Int>()
    private var productByIdResponse: WiseLiveData<Resource<Product>> = WiseLiveData()

    private var contentByCatIdResponse: WiseLiveData<Resource<ECommContent>> = WiseLiveData()
    private var contentByCatId: LiveData<List<Categories>>
    private var contentByCatIdMutableLiveData = MutableLiveData<Int>()

    init {
        content = Transformations.switchMap(contentMutableLiveData) {
            if (it == true) {
                contentRepository.loadContentList(true)
            } else {
                AbsentLiveData.create()
            }
        }
        initContentResponse()

        contentByCatId = Transformations.switchMap(contentByCatIdMutableLiveData) {
            if (it != -1) {
                contentRepository.loadContentByCatIdList(it)
            } else {
                AbsentLiveData.create()
            }
        }
        initContentByCatIdResponse()

        categoriesByIdLD = Transformations.switchMap(categoriesByIdLDMutableLiveData) {
            if(it.isNotNullOrEmpty()){
                contentRepository.getCategoriesbyIdLD(it)
            } else {
                AbsentLiveData.create<List<Categories>>()
            }
        }

        productById = Transformations.switchMap(productByIdMutableLiveData) {
            if(it != null){
                contentRepository.getProductById(it)
            } else {
                AbsentLiveData.create()
            }
        }
        initProductByIdResponse()
    }

    private fun initContentResponse() {
        contentResponse.addSource(content) { resource ->
            when(resource?.status) {
                Status.SUCCESS -> {
                    contentResponse.dispatchSuccess(resource)
                }
                Status.LOADING -> {
                    contentResponse.dispatchLoading(resource)
                }
                Status.ERROR -> {
                    contentResponse.dispatchError(resource)
                }
            }
        }
    }

    private fun initContentByCatIdResponse() {
        contentByCatIdResponse.addSource(contentByCatId) { resource ->
            if(resource != null) {
                contentByCatIdResponse.dispatchSuccess(Resource.success(ECommContent(1, resource)))
            } else {
                contentResponse.dispatchError(Resource.error(Error.build(Error.ErrorValue.ERROR_NETWORK_ERROR),resource))
            }
        }
    }

    private fun initProductByIdResponse() {
        productByIdResponse.addSource(productById) { resource ->
            if(resource == null){
                productByIdResponse.dispatchError(Resource.error(Error.build(Error.ErrorValue.ERROR_NETWORK_ERROR),resource))
            } else {
                productByIdResponse.dispatchSuccess(Resource.success(resource))
            }
        }
    }

    fun getContent(): LiveData<Resource<ECommContent>> {
        contentMutableLiveData.value = true
        return contentResponse
    }

    fun getCategoriesByIdLD(listIds: List<Int>): LiveData<List<Categories>>{
        categoriesByIdLDMutableLiveData.value = listIds
        return categoriesByIdLD
    }

    fun getProductsByCatId(catId: Int): LiveData<Resource<ECommContent>>{
        contentByCatIdMutableLiveData.value = catId
        contentByCatIdResponse.dispatchLoading(Resource.loading(null))
        return contentByCatIdResponse
    }

    fun getProductById(productId: Int): LiveData<Resource<Product>>{
        productByIdMutableLiveData.value = productId
        productByIdResponse.dispatchLoading(Resource.loading(null))
        return productByIdResponse
    }
}