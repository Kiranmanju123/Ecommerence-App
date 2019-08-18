package com.example.hp.salesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url="http://192.168.43.101/SalesWeb/get_cat.php"
        var list=ArrayList<String>()
        var rq:RequestQueue=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->
            for(x in 0 until response.length())
                list.add(response.getJSONObject(x).getString("catagory"))

            var adp=ArrayAdapter(this,R.layout.my_textveiw,list)
            home_cat.adapter=adp
        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()

        })
        rq.add(jar)

        home_cat.setOnItemClickListener { parent, view, position, id ->
            var cat:String=list[position]
            var obj=Intent(this,ItemAct::class.java)
            obj.putExtra("cat",cat)                                   //this sends the clicked item details to itemActiviy
            startActivity(obj)
        }


    }
}
