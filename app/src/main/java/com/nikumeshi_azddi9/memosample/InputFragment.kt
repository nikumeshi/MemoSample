package com.nikumeshi_azddi9.memosample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_input.*
import java.lang.ClassCastException
import java.math.BigInteger
import java.nio.ByteBuffer
import java.text.DateFormat
import java.util.*

class InputFragment : Fragment() {

    private lateinit var onFileOutputListener: OnFileOutputListener

    interface OnFileOutputListener{
        fun onFileOutput()
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnFileOutputListener) {
//            onFileOutputListener = context
//        } else {
//            throw ClassCastException("${context.toString()} must implement OnFileOutputListener")
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        saveBtn.setOnClickListener {
////            onFileOutputListener.onFileOutput()
//            val timestamp = android.text.format.DateFormat.format(DATE_FORMAT, Date())
//            val memoViewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)
//            memoViewModel.insert(Memo(
//                initUuid(),
//                "memo-$timestamp",
//                getString(R.string.last_modified, Date()),
//                memoContent.text.toString()
//            ))
//        }
//    }

    fun show(memo: Memo){
        val content: EditText = view?.findViewById(R.id.memoContent) ?: return
        memoContent.setText(memo.memoContent)
    }
}