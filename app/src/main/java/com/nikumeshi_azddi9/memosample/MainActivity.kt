package com.nikumeshi_azddi9.memosample

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_list.*
import java.io.File
import java.math.BigInteger
import java.nio.ByteBuffer
import java.util.*

class MainActivity : AppCompatActivity()/*, InputFragment.OnFileOutputListener*/, ListFragment.OnFileSelectListener {

    override fun onFileSelected(memo: Memo){
        (supportFragmentManager.findFragmentById(R.id.inputFragment) as InputFragment).show(memo)
        drawerLayout?.closeDrawer(GravityCompat.START)
    }

//    override fun onFileOutput(){
//        (supportFragmentManager.findFragmentById(R.id.listFragment) as ListFragment).show()
//    }

    private val DATE_FORMAT = "yyyy-MM-dd-hh-mm-ss"
    private var actBarDrawerToggle: ActionBarDrawerToggle? = null
    private val memoVM by lazy { ViewModelProviders.of(this).get(MemoViewModel::class.java) }

    private fun setupDrawer(drawerLayout: DrawerLayout){
        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name).apply {
            isDrawerIndicatorEnabled = true
        }
        drawerLayout.addDrawerListener(toggle)
        actBarDrawerToggle = toggle

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun setViews(){
        setContentView(R.layout.activity_main)
        val drawerLayout = drawerLayout
        drawerLayout?.let { setupDrawer(drawerLayout) }

        memoVM.allmemos.observe(this, Observer {
            val memoAdapter = MemoAdapter(it){memo ->
                memoContent.setText(memo.memoContent)
                drawerLayout?.closeDrawer(GravityCompat.START)
            }
            Log.d("hoge", "$it")

            memoList.run {
                setHasFixedSize(true)
                adapter = memoAdapter
                layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            }
        })

        saveBtn.setOnClickListener {
            //            onFileOutputListener.onFileOutput()
            val timestamp = android.text.format.DateFormat.format(DATE_FORMAT, Date())
            val memoViewModel = ViewModelProviders.of(this).get(MemoViewModel::class.java)
            memoViewModel.insert(Memo(
                initUuid(),
                "memo-$timestamp",
                getString(R.string.last_modified, Date()),
                memoContent.text.toString()
            ))
        }
    }
    private fun initUuid(): Long {
        var value: Long
        do {
            val buffer = ByteBuffer.wrap(ByteArray(16)).apply {
                UUID.randomUUID().also { uid ->
                    putLong(uid.leastSignificantBits)
                    putLong(uid.mostSignificantBits)
                }
            }
            value = BigInteger(buffer.array()).toLong()
        } while (value < 0)
        return value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actBarDrawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actBarDrawerToggle?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem) = if (actBarDrawerToggle?.onOptionsItemSelected(item) == true) true else super.onOptionsItemSelected(item)
}
