package com.ifs21004.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21004.dinopedia.databinding.DinoDetailBinding

class DetailDino : AppCompatActivity() {

    private lateinit var binding: DinoDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DinoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Membaca data dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)

        // Menampilkan tombol back di ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Memeriksa apakah data dino null atau tidak
        if (dino != null) {
            // Mengatur judul ActionBar sesuai nama dinosaurus
            supportActionBar?.title = dino!!.dinosaurs_name
            // Memuat data ke tampilan
            loadData(dino!!)
        } else {
            // Jika data dino null, maka akhiri aktivitas ini
            finish()
        }

        // Menampilkan tombol kembali di ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadData(dino: Dino) {
        // Memuat data ke tampilan
        binding.imageViewFamily.setImageResource(dino.dino_image)
        binding.textViewDinosaurName.text = dino.dinosaurs_name
        binding.textView28.text = dino.dinosaurs_desc
        binding.textView29.text = dino.dinosaurs_carac
        binding.textViewb.text = dino.dinosaurs_cel
        binding.textView32.text = dino.dinosaurs_habit
        binding.textView0.text = dino.dinosaurs_eats
        binding.textViewa.text = dino.dinosaurs_length
        binding.textView11.text = dino.dinosaurs_long
        binding.textView25.text = dino.dinosaurs_weigth
        binding.textView12.text = dino.dinosaurs_weak
    }


    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
    fun onBackClicked(view: View) {
        onBackPressed()
    }

    fun onShareClicked(view: View) {
        shareContent()
    }
    private fun shareContent() {
        dino?.let {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Info Dino: ${it.dinosaurs_name}")
            val shareMessage = "Nama Dino: ${it.dinosaurs_name}\n" +
                    "Deskripsi: ${it.dinosaurs_desc}\n" +
                    "Karakteristik: ${it.dinosaurs_carac}\n" +
                    "Sel: ${it.dinosaurs_cel}\n" +
                    "Habitat: ${it.dinosaurs_habit}\n" +
                    "Makanan: ${it.dinosaurs_eats}\n" +
                    "Panjang: ${it.dinosaurs_length}\n" +
                    "Umur: ${it.dinosaurs_long}\n" +
                    "Berat: ${it.dinosaurs_weigth}\n" +
                    "Kekurangan: ${it.dinosaurs_weak}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
        }
    }

}
