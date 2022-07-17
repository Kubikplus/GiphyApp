package com.example.giphy_trainee.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy_trainee.MainViewModel
import com.example.giphy_trainee.OnItemClickListener
import com.example.giphy_trainee.R
import com.example.giphy_trainee.RecyclerViewAdapter
import com.example.giphy_trainee.remote.ApiInterface
import com.example.giphy_trainee.remote.DataObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.awaitResponse


//var BASE_URL = "https://api.giphy.com/v1/"

class MainActivity : AppCompatActivity() {
    private val KEY_RECYCLER_STATE = "recycler_state"
    private val viewModel by viewModel<MainViewModel>()
    private val api = inject<ApiInterface>()
    lateinit var recyclerView : RecyclerView
    lateinit var state :Parcelable

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.doNetworkCall()
        recyclerView = findViewById<RecyclerView>(R.id.gif_recycler)
        val gifs = mutableListOf<DataObject>()
        val adapter = RecyclerViewAdapter(this, gifs)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this,2)
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, GifDetailActivity::class.java)
                intent.putExtra("url", gifs[position].images.ogImage.url)
                startActivity(intent)
            }

        })
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY


        GlobalScope.launch(Dispatchers.IO) {
            val response = api.value.getGifs().awaitResponse()
            if (response.isSuccessful) {
                gifs.addAll(response.body()!!.res)
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }

//            }
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable("StoreRecyclerView",recyclerView.layoutManager?.onSaveInstanceState())
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        state = savedInstanceState.getParcelable("StoreRecyclerView")!!
//
//
//    }


}


