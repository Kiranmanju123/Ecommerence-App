package com.example.hp.salesapp

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class itemAdapter(var context:Context,var list:ArrayList<item>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
       var v:View=LayoutInflater.from(context).inflate(R.layout.item_row,p0,false)
        return  ItemHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ItemHolder).bind(list[p1].name,list[p1].price,list[p1].photo,list[p1].id)
    }


    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(n:String,p:Double,u:String,item_id:Int)
        {
            itemView.item_name.text=n
            itemView.item_price.text=p.toString()
            Picasso.with(itemView.context).load("http://192.168.43.101/SalesWeb/images/"+u).into(itemView.item_photo)

            itemView.item_add_photo.setOnClickListener {

                UserInfo.itemId=item_id

                var obj=QtyFragment()
                var manager=(itemView.context as Activity).fragmentManager
                obj.show(manager,"QTY")


            }
        }
    }
}