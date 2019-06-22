package com.sam.iotblue.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sam.iotblue.R
import com.sam.iotblue.model.Coord
import com.sam.iotblue.ui.activities.TodaysWeatherActivity
import com.sam.iotblue.utils.AppUtils.saveListOfCoords
import kotlinx.android.synthetic.main.bookmarked_item.view.*


class BookmarkedCoordsAdapter(var list: ArrayList<Coord>,var context : Context) :
    RecyclerView.Adapter<BookmarkedCoordsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.bookmarked_item,
                parent,
                false
            ) as View
        return ViewHolder(view)
    }

    fun updateList(newList: List<Coord>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }
    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.lat.text = list.get(position).lat.toString()
        holder.lon.text = list.get(position).lon.toString()
        holder.btn.setOnClickListener {

            list.removeAt(position)

            saveListOfCoords(list,context)
            notifyDataSetChanged()

        }

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,TodaysWeatherActivity::class.java)
                .putExtra("lat",list.get(position).lat.toString())
                .putExtra("lon",list.get(position).lon.toString())
            )
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lat = itemView.tv_lat_value
        var lon = itemView.tv_lon_value
        var btn = itemView.btn_delete
    }

}