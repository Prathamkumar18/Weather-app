package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun fetch(){
        var et1=findViewById<EditText>(R.id.et1)
        val gettxt:String=et1.editableText.toString()
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$gettxt&units=metric&appid=f3f044b0a97b4bd511671fdcefca9311"
        val JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener{

                val jsonObjectmain=it.getJSONObject("main")
                val temp:Double=jsonObjectmain.getDouble("temp")
                val feels_like:Double=jsonObjectmain.getDouble("feels_like")
                val pressure:Double=jsonObjectmain.getDouble("pressure")
                val humidity:Double=jsonObjectmain.getDouble("humidity")

                val jsonObjectwind=it.getJSONObject("wind")
                val speed:Double=jsonObjectwind.getDouble("speed")

                val string:String="\nTemp⛅=$temp°C\nFeels like=$feels_like°C\nPressure=$pressure\nHumidity♨=$humidity\nSpeed💨=$speed"

                val resulttv=findViewById<TextView>(R.id.resulttv)

                resulttv.setText(string)
            },
            Response.ErrorListener {
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            })
        queue.add(JsonObjectRequest)
    }
    fun onClickSearch(view: View) {
        val tv=findViewById<TextView>(R.id.resulttv)

        fetch()
    }

    fun onClickClear(view: View) {
        var et1=findViewById<EditText>(R.id.et1)
        et1.setText("")
    }
}