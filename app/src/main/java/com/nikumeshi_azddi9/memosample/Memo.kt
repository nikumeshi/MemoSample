package com.nikumeshi_azddi9.memosample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class Memo(
    @PrimaryKey
    var id: Long,

    @ColumnInfo(name = "title")
    var name: String,

    @ColumnInfo(name = "lastMod")
    var lastModified: String,

    @ColumnInfo(name = "memoContent")
    var memoContent: String
)