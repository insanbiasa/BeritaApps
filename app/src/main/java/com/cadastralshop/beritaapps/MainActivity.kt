package com.cadastralshop.beritaapps

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cadastralshop.beritaapps.adapter.BeritaAdapter
import com.cadastralshop.beritaapps.model.Berita
import com.cadastralshop.beritaapps.model.ResponseServer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_berita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isConnect()) {

            ConfigNetwork.getRetrofit().getDataBerita().enqueue(object : Callback<ResponseServer> {
                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {

                    progress_circular.visibility = View.GONE
                    Log.d("Server Gangguan", t.message)
                }

                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("responsenya apa", response.message())


                    if (response.isSuccessful) {
                        progress_circular.visibility = View.GONE

                        val status = response.body()?.status

                        if (status == "ok") {
                            val data = response.body()?.articles

                            showData(data)
                        }
                    }

                }
            })
        }else{

            progress_circular.visibility = View.GONE
            Toast.makeText(this, "Silakan Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show()
        }


    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo !=null && connect.activeNetworkInfo.isConnected
    }

    private fun showData(data: ArrayList<Berita>?) {

        ListBerita.adapter = BeritaAdapter(data)

    }
}
