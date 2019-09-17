package com.nikumeshi_azddi9.memosample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlin.concurrent.thread

class MemoViewModel (application: Application) : AndroidViewModel(application){

    private val db = MemoDatabase.getInstance(application).memoDao()
    private val _allMemos = db.getAllMemos()
    val allmemos = _allMemos

    fun insert(memo: Memo) = thread { db.insert(memo) }
}