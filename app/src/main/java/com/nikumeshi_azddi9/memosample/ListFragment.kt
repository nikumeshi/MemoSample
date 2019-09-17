package com.nikumeshi_azddi9.memosample

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*
import java.lang.ClassCastException

class ListFragment : Fragment() {

    interface OnFileSelectListener {
        fun onFileSelected(memo: Memo)
    }

    private lateinit var onFileSelectListener: OnFileSelectListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFileSelectListener) {
            onFileSelectListener = context
        } else {
            throw ClassCastException("${context.toString()} must implement OnFileSelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        memoList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        show()
//    }
//
//    fun show(){
//
//        val memoViewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)
//        memoViewModel.allmemos.value?.let {
//            memoList.adapter = MemoAdapter(it){ memo ->
//                onFileSelectListener.onFileSelected(memo)
//            }
//        }
//    }
}
