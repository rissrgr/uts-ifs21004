package com.ifs21004.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21004.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataDinoFamily = ArrayList<FamilyDino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(false)
        dataDinoFamily.addAll(getListFamilyDino())
        showRecyclerList()
        binding.imageprofil.setOnClickListener {
            navigateToProfile()
        }

    }


    @SuppressLint("Recycle")
    private fun navigateToProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun getListFamilyDino(): ArrayList<FamilyDino> {
        val dataImage = resources.obtainTypedArray(R.array.family_images)
        val dataName = resources.getStringArray(R.array.family_name)
        val dataDesc = resources.getStringArray(R.array.family_descriptions)
        val dataPeriod = resources.getStringArray(R.array.dinosaur_lifespans)
        val dataPhysical = resources.getStringArray(R.array.dinosaur_physical_characteristics)
        val dataHabits = resources.getStringArray(R.array.dinosaur_habitats)
        val dataClassif = resources.getStringArray(R.array.dinosaur_classifications)
        val listDinoFamily = ArrayList<FamilyDino>()
        for (i in dataName.indices) {
            val dinoFamily = FamilyDino(
                dataImage.getResourceId(i, -1), dataName[i],
                dataDesc[i], dataPeriod[i],
                dataPhysical[i], dataHabits[i], dataClassif[i]
            )
            listDinoFamily.add(dinoFamily)
        }
        return listDinoFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            binding.recyclerView.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.recyclerView.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoFamilyAdapter = DinoFamilyAdapter(dataDinoFamily)
        binding.recyclerView.adapter = listDinoFamilyAdapter
        listDinoFamilyAdapter.setOnItemClickCallback(object :
            DinoFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: FamilyDino) {
                showSelectedDinoFamily(data)
            }
        })
    }

    private fun showSelectedDinoFamily(dinoFamily: FamilyDino) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINOFAMILY, dinoFamily)
        startActivity(intentWithData)
    }
}
