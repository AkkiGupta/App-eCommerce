package app.ecomm.data.repo

import android.arch.lifecycle.LiveData
import app.ecomm.data.api.MiddlewareAPi
import app.ecomm.data.api.model.ApiResponse
import app.ecomm.data.api.model.AppExecutors
import app.ecomm.data.api.model.Resource
import app.ecomm.data.db.ContentDao
import app.ecomm.data.livedata.NetworkBoundResource
import app.ecomm.data.model.content.Categories
import app.ecomm.data.model.content.ECommContent
import app.ecomm.data.model.content.Product
import app.ecomm.data.model.content.Ranking
import app.ecomm.data.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Fetch contents from sources (both local and remote).
 * Repositories should interact with the client and provide data directly.
 */
class ContentRepository @Inject
constructor(private val appExecutors: AppExecutors,
            private val middlewareAPi: MiddlewareAPi, private val contentDao: ContentDao) : Repository() {
    private val contentListRateLimiter = RateLimiter<String>(10, TimeUnit.SECONDS)

    fun loadContentList(shouldFetch: Boolean): LiveData<Resource<ECommContent>> {
        return object : NetworkBoundResource<ECommContent, ECommContent>(appExecutors) {
            override fun saveCallResult(entity: ECommContent) {
                contentDao.clearAllTables()
                contentDao.insertECommContent(entity)
            }

            override fun shouldFetch(data: ECommContent?): Boolean {
                return shouldFetch && contentListRateLimiter.shouldFetch(contentListUrl())
            }

            override fun loadFromDb(): LiveData<ECommContent> {
                return contentDao.loadContentList()
            }

            override fun createCall(): LiveData<ApiResponse<ECommContent>> {
                return middlewareAPi.getContent(contentListUrl())
            }

            override fun onFetchFailed() {
                contentListRateLimiter.reset(contentListUrl())
            }

        }.asLiveData()
    }

    fun getCategoriesbyIdLD(categoryIds: List<Int>): LiveData<List<Categories>> {
        return contentDao.getCategoriesByIdLD(categoryIds)
    }

    fun getCategoriesbyId(categoryIds: List<Int>): List<Categories> {
        return contentDao.getCategoriesById(categoryIds)
    }

    fun getProductById(productId: Int): LiveData<Product> {
        return contentDao.getProductById(productId)
    }

    fun getProductByRank(rank: String): LiveData<Ranking> {
        return contentDao.getProductByRank(rank)
    }

    fun getAllProductsRankWise(): LiveData<List<Ranking>> {
        return contentDao.getAllProductRankWise()
    }

    fun loadContentByCatIdList(catId: Int): LiveData<List<Categories>> {
        return contentDao.getProductsByCatId(catId)
    }
}