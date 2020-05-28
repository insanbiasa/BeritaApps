package com.cadastralshop.beritaapps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cadastralshop.beritaapps.R
import com.cadastralshop.beritaapps.detail.DetailBeritaActivity
import com.cadastralshop.beritaapps.model.Berita
import kotlinx.android.synthetic.main.item_berita.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BeritaAdapter(var data: ArrayList<Berita>?) :
    RecyclerView.Adapter<BeritaAdapter.BeritaHolder>() {
    class BeritaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val photo = itemView.ivImg
        val textAuthor = itemView.tvAuthor
        val textJudul = itemView.tvTitle

        //        val textTanggalOri = itemView.tvPublished
        val textWaktu = itemView.tvTime
        val textTanggal = itemView.tvDate

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)

        val holder = BeritaHolder(view)

        return holder
    }


    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BeritaHolder, position: Int) {

        holder.textAuthor.text = data?.get(position)?.author
        holder.textJudul.text = data?.get(position)?.title
//        holder.textTanggalOri.text = data?.get(position)?.publishedAt
        holder.textWaktu.text = data?.get(position)?.publishedAt?.convertpublishedAt("HH:mm")
        holder.textTanggal.text = data?.get(position)?.publishedAt?.convertpublishedAt("dd-mm-yyyy")

        Glide.with(holder.itemView.context).load(data?.get(position)?.urlToImage).into(holder.photo)


        holder.itemView.setOnClickListener {

        //action klik
        val intent = Intent(holder.itemView.context, DetailBeritaActivity::class.java)
        intent.putExtra("author", data?.get(position)?.author)
        intent.putExtra("title", data?.get(position)?.title)
        intent.putExtra("photo", data?.get(position)?.urlToImage)
        intent.putExtra("description", data?.get(position)?.description)
        intent.putExtra("content", data?.get(position)?.content)
        intent.putExtra("date", data?.get(position)?.publishedAt?.convertpublishedAt("dd-mm-yy"))
        intent.putExtra("time", data?.get(position)?.publishedAt?.convertpublishedAt("HH:mm"))



        holder.itemView.context.startActivity(intent)
        }


    }


    fun String.convertpublishedAt(format: String): String {
        val current = this
        val formatter = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val formatterIND = SimpleDateFormat(format, Locale.getDefault())
        val mDate = formatter.parse(current)
        formatterIND.timeZone = TimeZone.getDefault()
        return formatterIND.format(mDate)
    }
}