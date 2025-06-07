package com.cursokotlin.ingeweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.ingeweek.recyclerV.CarouselAdapter
import com.google.android.material.carousel.CarouselLayoutManager

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UniversidadFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_universidad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chargerRecycler(view)
    }

    fun chargerRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.carouselRecyclerViewUns)
        val adapter = CarouselAdapter(getImageList())
        recyclerView.adapter = adapter

        val carouselLayoutManager = CarouselLayoutManager()
        recyclerView.layoutManager = carouselLayoutManager

    }

    private fun getImageList(): List<Int> {
        return listOf(
            R.drawable.f_sistemas,
            R.drawable.f_civil,
            R.drawable.f_agroindustrial,
            R.drawable.f_mecanica,
            R.drawable.f_energia,
            R.drawable.f_agronoma,
            R.drawable.img_uns_1,
            R.drawable.img_uns_2,
            R.drawable.img_uns_3,
            R.drawable.img_uns_4,
            R.drawable.img_uns_5
        )
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UniversidadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}