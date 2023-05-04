package com.vo.vo_kiosk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vo.vo_kiosk.DTO.MenuDTO
import com.vo.vo_kiosk.R

class MenuAdapter : ListAdapter<MenuDTO, MenuAdapter.MenuHolder>(MenuDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.clickmenu_item, parent, false)


        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val menu = getItem(position)
        holder.bind(menu)
    }

    inner class MenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val menuImage : AppCompatImageView = itemView.findViewById(R.id.cmi_imageView)
        private val menuText : TextView = itemView.findViewById(R.id.cmi_textView)

        fun bind(menu : MenuDTO) {
            menuText.text = "불고기 버거"
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