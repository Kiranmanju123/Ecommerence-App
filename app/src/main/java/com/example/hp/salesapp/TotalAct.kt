package com.example.hp.salesapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_total.*

class TotalAct : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId="com.example.hp.salesapp"
    private val description="Ordered Sucessfully"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total)

        var url="http://192.168.43.101/SalesWeb/get_total.php?bill_no="+intent.getStringExtra("bno")
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var sr= StringRequest(Request.Method.GET,url, Response.Listener { response ->

           total_tv.text=response



        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()

        })

        rq.add(sr)


        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        cash.setOnClickListener {
//            var obj=SmsManager.getDefault()
//            obj.sendTextMessage("7022505972",null,"Your Order Successfully Placed",null,null)

            val intent = Intent(this,LauncherActivity::class.java)
            val pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor=Color.BLUE
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder= Notification.Builder(this,channelId).setContentTitle("Kiran").setContentText("Your Order Sucessfully Placed!!THANK YOU").setSmallIcon(R.mipmap.ic_launcher_round).setLargeIcon(BitmapFactory.decodeResource(this.resources,R.mipmap.ic_launcher)).setContentIntent(pendingIntent)

            }else
            {
                builder= Notification.Builder(this).setContentTitle("Kiran").setContentText("Your Order Sucessfully Placed!!THANK YOU").setSmallIcon(R.drawable.ic_launcher_round).setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher)).setContentIntent(pendingIntent)
            }
            notificationManager.notify(1234,builder.build())



        }

    }
}
