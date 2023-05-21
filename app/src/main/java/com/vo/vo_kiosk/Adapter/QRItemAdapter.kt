package com.vo.vo_kiosk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vo.vo_kiosk.DTO.OrderList
import com.vo.vo_kiosk.R

class QRItemAdapter(private val orderList: List<OrderList>) : RecyclerView.Adapter<QRItemAdapter.OrderListViewHolder>() {

    class OrderListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val qrName: TextView = view.findViewById(R.id.qr_i_name)
        val qrSide: TextView = view.findViewById(R.id.qr_i_side)
        val qrDrink: TextView = view.findViewById(R.id.qr_i_drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qr_menu_item, parent, false)
        return OrderListViewHolder(view)
    }
    override fun getItemCount() = orderList.size

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val menuData = orderList[position]
        holder.qrName.text = menuData.menuName
        holder.qrSide.text = "- ${menuData.dessertName}"
        holder.qrDrink.text = "- ${menuData.drinkName}"

        if(menuData.dessertName == null){
            holder.qrSide.visibility = View.GONE
        }else{
            holder.qrSide.visibility = View.VISIBLE
        }

        if(menuData.drinkName == null){
            holder.qrDrink.visibility = View.GONE
        }else{
            holder.qrDrink.visibility = View.VISIBLE
        }
    }
}
