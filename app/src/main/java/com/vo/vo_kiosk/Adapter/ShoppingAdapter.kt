package com.vo.vo_kiosk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vo.vo_kiosk.DTO.OrderList
import com.vo.vo_kiosk.R
import com.vo.vo_kiosk.ViewModel.OrderMenuViewModel

class ShoppingAdapter : ListAdapter<OrderList, ShoppingAdapter.OrderViewHolder>(DiffCallback()) {

    private lateinit var orderMenuViewModel: OrderMenuViewModel

    fun setViewModel(viewModel: OrderMenuViewModel) {
        orderMenuViewModel = viewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val menuName : TextView = itemView.findViewById(R.id.shop_i_name)
        private val sideMenu : TextView = itemView.findViewById(R.id.shop_i_side)
        private val drinkMenu : TextView = itemView.findViewById(R.id.shop_i_drink)
        private val menuImg : ImageView = itemView.findViewById(R.id.shop_i_img)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val order = getItem(position)
                    orderMenuViewModel.removeOrder(order)
                }
            }
        }

        fun bind(order: OrderList) {
            menuName.text = order.menuName
            if (order.dessertName != null) {
                sideMenu.text = "- ${order.dessertName}"
                drinkMenu.text = "- ${order.drinkName}"
                sideMenu.visibility = View.VISIBLE
                drinkMenu.visibility = View.VISIBLE
            } else {
                sideMenu.visibility = View.GONE
                drinkMenu.visibility = View.GONE
            }
            Glide.with(itemView)
                .load("http://oceanit.synology.me:3502/img/${order.menuImg}")
                .into(menuImg)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<OrderList>() {
        override fun areItemsTheSame(oldItem: OrderList, newItem: OrderList): Boolean {
            return oldItem.menuId == newItem.menuId
        }

        override fun areContentsTheSame(oldItem: OrderList, newItem: OrderList): Boolean {
            return oldItem == newItem
        }
    }
}
