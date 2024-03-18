package com.ifs21004.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21004.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_DINO,
                Dino::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dino ${dino!!.name}"
            binding.tvDetailTitle.text = dino!!.name
            loadData(dino!!)
        } else {
            // Handle case when dino is null
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailImage.setImageResource(dino.image)
        binding.tvDetailTitle.text = dino.name
        binding.tvDetailDesc.text = dino.desc
        binding.tvDetailOrign.text = dino.origin
        binding.tvDetailMain.text = dino.main
        binding.tvDetailInteresting.text = dino.interesting
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*fungsi 2*/
    private fun shareDino() {
        if (dino != null) {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Info Dino: ${dino!!.name}")
            val shareMessage = "Nama Family: ${dino!!.name}\n" +
                    "Deskripsi: ${dino!!.desc}\n" +
                    "Asal Usul Nama : ${dino!!.origin}\n" +
                    "Karakteristik Utama: ${dino!!.main}\n" +
                    "Fakta Menarik: ${dino!!.interesting}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
        }
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
