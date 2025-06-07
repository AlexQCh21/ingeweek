package com.cursokotlin.ingeweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.ingeweek.recyclerV.CarouselAdapter
import com.cursokotlin.ingeweek.recyclerV.Carrera
import com.cursokotlin.ingeweek.recyclerV.ProfesionAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chargerRecycler(view)
        chargerRecyclerProfesion(view)
    }

    fun chargerRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.carouselRecyclerView)
        val adapter = CarouselAdapter(getImageList())
        recyclerView.adapter = adapter

        val carouselLayoutManager = CarouselLayoutManager()
        recyclerView.layoutManager = carouselLayoutManager
        
    }

    fun chargerRecyclerProfesion(view: View) {
        val json = context?.assets?.open("carreras.json")?.bufferedReader().use { it?.readText() ?: "" }

        val gson = Gson()
        val tipoLista = object : TypeToken<List<Carrera>>() {}.type
        val listaCarrera: List<Carrera> = gson.fromJson(json, tipoLista)

        val recyclerView = view.findViewById<RecyclerView>(R.id.carouselRecyclerViewProfesion)
        val adapter = ProfesionAdapter(listaCarrera)
        recyclerView.adapter = adapter

        // ✅ Layout vertical
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }


    private fun getImageList(): List<Int> {
        return listOf(
            R.drawable.img_sistemas,
            R.drawable.img_civil,
            R.drawable.img_agroindustrial,
            R.drawable.img_mecanica,
            R.drawable.img_energia,
            R.drawable.img_agronoma
        )
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}