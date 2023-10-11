package com.example.a13


import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.example.a13.databinding.ActivityMainBinding
import com.example.a13.utils.MyNetworkHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var myNetworkHelper: MyNetworkHelper
    lateinit var requestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestQueue = Volley.newRequestQueue(this)
        myNetworkHelper = MyNetworkHelper(this)
        loadImage()
    }
    fun loadImage(){
        val imageRequest = ImageRequest("https://i.imgur.com/Nwk25LA.jpg",
            object : Response.Listener<Bitmap> {
                override fun onResponse(response: Bitmap?) {
                    binding.imageView.setImageBitmap(response)
                }
            },
            0, 0, ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        requestQueue.add(imageRequest)
    }
}