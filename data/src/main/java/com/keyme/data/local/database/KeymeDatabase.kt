package com.keyme.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.keyme.data.local.dao.UserAuthDao
import com.keyme.data.local.entity.UserAuthEntity

@Database(entities = [UserAuthEntity::class], version = 1)
abstract class KeymeDatabase : RoomDatabase() {
    abstract fun userAuthDao(): UserAuthDao
}
