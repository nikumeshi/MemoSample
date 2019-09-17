package com.nikumeshi_azddi9.memosample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao{

    @Query("select * from memos")
    fun getAllMemos(): LiveData<List<Memo>>

    @Query("select * from memos where id = :id")
    fun getMemo(id: Long): Memo

    @Insert
    fun insert(memo: Memo)
}