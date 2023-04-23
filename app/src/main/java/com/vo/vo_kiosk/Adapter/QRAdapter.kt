package com.vo.vo_kiosk.Adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.vo.vo_kiosk.R

class QRAdapter : ListAdapter<String, QRAdapter.QrViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.qr_item, parent, false)
        return QrViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QrViewHolder, position: Int) {
        val data = getItem(position)
        val bitmap = generateQRCode(data)
        holder.qrImageView.setImageBitmap(bitmap)
    }

    private fun generateQRCode(data: String): Bitmap {
        val qrCodeWriter = QRCodeWriter()
        val width = 500
        val height = 500
        val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

    inner class QrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val qrImageView: ImageView = itemView.findViewById(R.id.qr_imageView)
    }
}