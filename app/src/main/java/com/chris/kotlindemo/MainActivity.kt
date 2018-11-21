package com.chris.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL


class MainActivity : AppCompatActivity() {
    val TAG: String = "KotlinDemo"
    private val items = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
     )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainAct_recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            var data: List<Follower> = getRequestData("https://api.github.com/users/Anima18/followers")
            uiThread{
                Log.i(TAG, "running in mainThread")
                mainAct_recyclerView.adapter = RecyclerViewAdapter(data)
            }
        }

    }

    fun getRequestData(url: String): List<Follower> {
        var data: String = URL(url).readText()
        Log.i(TAG, data)
        val followerType = genericType<List<Follower>>()
        return Gson().fromJson<List<Follower>>(data, followerType)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type
}
