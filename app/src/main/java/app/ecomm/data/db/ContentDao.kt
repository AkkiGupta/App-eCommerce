package app.ecomm.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import app.ecomm.data.model.content.*

@Dao
abstract class ContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContentList(contents: ECommContent)

    @Query("select * from ecommcontent")
    abstract fun loadContentList(): LiveData<ECommContent>

    @Query("delete from ecommcontent")
    abstract fun clearContentList()

    @Query("select * from categories where id IN (:categoryId)")
    abstract fun getCategoriesByIdLD(categoryId: List<Int>) : LiveData<List<Categories>>

    @Query("select * from categories where id IN (:categoryId)")
    abstract fun getCategoriesById(categoryId: List<Int>) : List<Categories>

    @Query("select * from product where id = :productId")
    abstract fun getProductById(productId: Int) : LiveData<Product>

    @Query("select * from ranking where name = :rank")
    abstract fun getProductByRank(rank: String) : LiveData<Ranking>

    @Query("select * from ranking")
    abstract fun getAllProductRankWise() : LiveData<List<Ranking>>

    @Query("select * from categories where id = :catId")
    abstract fun getProductsByCatId(catId: Int) : LiveData<List<Categories>>

    @Query("delete from categories")
    abstract fun clearCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCategories(categories: Categories)

    @Query("delete from product")
    abstract fun clearProduct()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProduct(product: Product)

    @Query("delete from variant")
    abstract fun clearVariant()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertVariant(variant: Variant)

    @Query("delete from ranking")
    abstract fun clearRanking()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRanking(ranking: Ranking)

    @Query("delete from rankingproduct")
    abstract fun clearRankingProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRankingProduct(rankingProduct: RankingProduct)

    fun clearAllTables(){
        clearContentList()
        clearCategories()
        clearProduct()
        clearRanking()
        clearVariant()
        clearRankingProducts()
    }

    fun insertECommContent(contents: ECommContent){
        insertContentList(contents)
        contents?.categories?.forEach { category ->
            category?.let {
                insertCategories(category)
                category.products?.forEach { product ->
                    product?.let {
                        insertProduct(product)
                        product.variants?.forEach {variant ->
                            variant?.let {
                                insertVariant(variant)
                            }
                        }
                    }
                }
            }
        }
        contents?.rankings?.forEach {ranking ->
            ranking?.let {
                insertRanking(it)
                it.products?.forEach { rankingProduct ->
                    insertRankingProduct(rankingProduct)
                }
            }
        }
    }
}