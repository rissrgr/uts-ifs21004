package com.ifs21004.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21004.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(false)
        dataDino.addAll(getListDinos())
        showRecyclerList()

    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dino> {
        val dataImage = resources.obtainTypedArray(R.array.family_images)
        val dataName = resources.getStringArray(R.array.dinosaur_families)
        val dataDesc = resources.getStringArray(R.array.family_descriptions)
        val dataOrigin = resources.getStringArray(R.array.Origin_of_Name)
        val dataMain = resources.getStringArray(R.array.Main_Physical_Characteristics)
        val dataInteresting = resources.getStringArray(R.array.Interesting_Facts)

        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataImage.getResourceId(i, -1),
                dataName[i],
                dataDesc[i],
                dataOrigin[i],
                dataMain[i],
                dataInteresting[i]
            )
            listDino.add(dino)
        }
        dataImage.recycle()
        return listDino
    }


    private fun showRecyclerList() {
        val isLandscape =
            resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val layoutManager = if (isLandscape) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }

        binding.recyclerView.layoutManager = layoutManager
        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.recyclerView.adapter = listDinoAdapter

        listDinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
}

