package com.cadastralshop.beritaapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.cadastralshop.beritaapps.R
import kotlinx.android.synthetic.main.activity_detail_berita.*
import kotlinx.android.synthetic.main.item_berita.*

class DetailBeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val author = intent.getStringExtra("author")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")
        val photo = intent.getStringExtra("photo")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")

        tvAuthorDetail.text = author
        tvTitleDetail.text = title
        tvDescriptionDetail.text = description
        tvContentDetail.text = content
        tvDateDetail.text = date
        tvTimeDetail.text = time

        Glide.with(this).load(photo).into(ivDetailImg)



    }
}
