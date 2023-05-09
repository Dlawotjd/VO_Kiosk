package com.vo.vo_kiosk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R

class MenuAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<MenuDTO, MenuAdapter.MenuHolder>(MenuDiffCallback()){

    interface OnItemClickListener {
        fun onItemClick(menu: MenuDTO, itemView: View)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.clickmenu_item, parent, false)

        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {

        val menu = getItem(position)
        holder.bind(menu)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(menu, holder.itemView)
        }

    }

    inner class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val menuImage : AppCompatImageView = itemView.findViewById(R.id.cmi_imageView)
        private val menuText : TextView = itemView.findViewById(R.id.cmi_textView)
        private val menuPrice : TextView = itemView.findViewById(R.id.cmi_price)

        fun bind(menu : MenuDTO) {
            menuText.text = menu.mainMenu
            menuPrice.text = menu.mainPrice.toString()
            menu.mainImg.let {
                Glide.with(itemView)
                    .load(it)
                    .into(menuImage)
            }
        }
    }

    class MenuDiffCallback : DiffUtil.ItemCallback<MenuDTO>(){
        override fun areItemsTheSame(oldItem: MenuDTO, newItem: MenuDTO): Boolean {
            return oldItem.mainMenu == newItem.mainMenu
        }

        override fun areContentsTheSame(oldItem: MenuDTO, newItem: MenuDTO): Boolean {
            return oldItem == newItem
        }

    }
}