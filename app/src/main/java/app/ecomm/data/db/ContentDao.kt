package app.ecomm.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import app.ecomm.data.model.content.ECommContent

@Dao
abstract class ContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertContentList(contents: ECommContent)

    @Query("select * from ecommcontent")
    abstract fun loadContentList(): LiveData<ECommContent>

    @Query("delete from ecommcontent")
    abstract fun clearContentList()

}