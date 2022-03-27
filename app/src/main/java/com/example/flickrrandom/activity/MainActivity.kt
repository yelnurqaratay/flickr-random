package com.example.flickrrandom.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrrandom.R
import com.example.flickrrandom.adapter.DEFAULT_ADAPTER_SIZE
import com.example.flickrrandom.adapter.ImageAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val imageAdapter by lazy {
        ImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<RecyclerView>(R.id.recyclerView).adapter = imageAdapter
    }

    private fun onAddClick(): Boolean {
        imageAdapter.listSize++
        imageAdapter.notifyItemInserted(imageAdapter.listSize - 1)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onRefreshClick(): Boolean {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Glide.get(applicationContext).clearDiskCache()
                withContext(Dispatchers.Main) {
                    imageAdapter.listSize = DEFAULT_ADAPTER_SIZE
                    imageAdapter.notifyDataSetChanged()
                }
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_add -> onAddClick()
        R.id.menu_refresh -> onRefreshClick()
        else -> super.onOptionsItemSelected(item)
    }
}