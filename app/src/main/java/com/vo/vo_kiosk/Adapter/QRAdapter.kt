package com.vo.vo_kiosk.Adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.vo.vo_kiosk.R

class QRAdapter(private val qrDataList: List<Int>) : RecyclerView.Adapter<QRAdapter.QrViewHolder>() {

//    qr 생성 및 설정
    private fun generateQRCode(data: String): Bitmap {
        val qrCodeWriter = QRCodeWriter()
        val width = 600
        val height = 600
        val bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height)
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

//    qr_item View 선언
    inner class QrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val qrImageView: ImageView = itemView.findViewById(R.id.qr_imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.qr_item, parent, false)
        return QrViewHolder(itemView)
    }

//    Position 데이터 값을 할당해주고 qr 생성
    override fun onBindViewHolder(holder: QrViewHolder, position: Int) {
        val data = qrDataList[position].toString()
        val bitmap = generateQRCode(data)
        holder.qrImageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return qrDataList.size
    }

}