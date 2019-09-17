package com.nikumeshi_azddi9.memosample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase(){

    abstract fun memoDao(): MemoDao

    companion object{
        @Volatile
        private var instance: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase = instance ?: synchronized(this){
            Room.databaseBuilder(context, MemoDatabase::class.java, "room.db").build()
        }
    }
}