package com.ifs21004.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21004.dinopedia.databinding.ActivityMainDinoBinding

class MainDino : AppCompatActivity() {

    private lateinit var binding: ActivityMainDinoBinding
    private val dataDino = ArrayList<Dino>()
    private var selectedFamily: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDino.setHasFixedSize(false)

        selectedFamily = intent.getStringExtra(EXTRA_SELECTED_FAMILY)

        selectedFamily?.let {
            dataDino.addAll(getListDinoByFamily(it))
        }

        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val dataImage = resources.obtainTypedArray(R.array.dino_image)
        val dataName = resources.getStringArray(R.array.dinosaurs_name)
        val dataDesc = resources.getStringArray(R.array.dinosaurs_desc)
        val dataCarac = resources.getStringArray(R.array.dinosaurs_carac)
        val dataCel  = resources.getStringArray(R.array.dinosaurs_cel)
        val dataHabit = resources.getStringArray(R.array.dinosaurs_habit)
        val dataEats = resources.getStringArray(R.array.dinosaurs_eat)
        val dataLength  = resources.getStringArray(R.array.dinosaurs_length)
        val dataLong = resources.getStringArray(R.array.dinosaurs_long)
        val dataWeight = resources.getStringArray(R.array.dinosaurs_weight)
        val dataWeak = resources.getStringArray(R.array.dinosaurs_weak)

        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataImage.getResourceId(i, -1),
                dataName[i],
                dataDesc[i],
                dataCarac[i],
                dataCel[i],
                dataHabit[i],
                dataEats[i],
                dataLength[i],
                dataLong[i],
                dataWeight[i],
                dataWeak[i])
            listDino.add(dino)
        }
        return listDino
    }


    @SuppressLint("Recycle")
    private fun getListDinoByFamily(family: String): ArrayList<Dino> {
        val allDino = getListDino()
        val filteredDino = ArrayList<Dino>()
        for (dino in allDino) {
            if (dino.dinosaurs_cel == family) {
                filteredDino.add(dino)
            }
        }
        return filteredDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = DinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            DinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainDino,
            DetailDino::class.java)
        intentWithData.putExtra(DetailDino.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_SELECTED_FAMILY = "EXTRA_SELECTED_FAMILY"
    }
}
