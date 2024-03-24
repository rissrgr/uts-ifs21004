package com.ifs21004.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21004.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dinoFamily: FamilyDino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan data DinoFamily dari intent
        dinoFamily = intent.getParcelableExtra(EXTRA_DINOFAMILY)

        // Menampilkan tombol back di ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Jika data DinoFamily tidak null, maka tampilkan detailnya
        dinoFamily?.let {
            supportActionBar?.title = it.family_name
            loadData(it)
        } ?: finish() // Jika data DinoFamily null, maka tutup activity

        // Mendapatkan referensi button
        val buttonViewDinosaurs = findViewById<Button>(R.id.buttonViewDinosaurs)

        // Menambahkan listener klik pada button
        buttonViewDinosaurs.setOnClickListener {
            // Pindah ke activity MainDino dengan membawa nama family sebagai data tambahan
            val intent = Intent(this@DetailActivity, MainDino::class.java)
            intent.putExtra(MainDino.EXTRA_SELECTED_FAMILY, dinoFamily?.family_name)
            startActivity(intent)
        }
    }

    private fun loadData(familyDino: FamilyDino) {
        // Memuat data DinoFamily ke dalam tampilan
        binding.ivDetailImage.setImageResource(familyDino.family_images)
        binding.tvDetailTitle.text = familyDino.family_name
        binding.textview1.text = familyDino.family_descriptions
        binding.textView2.text = familyDino.dinosaur_lifespans
        binding.textView3.text = familyDino.dinosaur_physical_characteristics
        binding.textView4.text = familyDino.dinosaur_habitats
        binding.textView6.text = familyDino.dinosaur_classifications
    }

    companion object {
        const val EXTRA_DINOFAMILY = "EXTRA_DINOFAMILY"
    }

    fun onBackClicked(view: View) {
        onBackPressed()
    }

    fun onShareClicked(view: View) {
        shareContent()
    }

    private fun shareContent() {
        dinoFamily?.let {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Info Family Dino: ${it.family_name}")
            val shareMessage = "Nama Family: ${it.family_name}\n" +
                    "Deskripsi: ${it.family_descriptions}\n" +
                    "Periode Hidup: ${it.dinosaur_lifespans}\n" +
                    "Karakter Fisik: ${it.dinosaur_physical_characteristics}\n" +
                    "Habitat dan Lingkungan: ${it.dinosaur_habitats}\n" +
                    "Kalsifikasi: ${it.dinosaur_classifications}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
        }
    }
}
