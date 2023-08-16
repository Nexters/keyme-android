package com.keyme.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keyme.data.local.entity.UserAuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserAuthDao {
    @Query("SELECT * FROM userAuth")
    fun getUserAuth(): Flow<UserAuthEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserAuth(user: UserAuthEntity)

    @Delete()
    suspend fun deleteUserAuth(userAuthEntity: UserAuthEntity)
}
