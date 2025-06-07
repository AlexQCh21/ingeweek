package com.cursokotlin.ingeweek.recyclerV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.ingeweek.R
import android.content.Context
import com.cursokotlin.ingeweek.recyclerV.CarouselAdapter.CarouselViewHolder

class ProfesionAdapter( private val carreras:List<Carrera>):RecyclerView.Adapter<ProfesionAdapter.ProfesionHolder>() {

    class ProfesionHolder(view: View):RecyclerView.ViewHolder(view){

        val nombrePrefesion = view.findViewById<TextView>(R.id.nombrePrefesion)
        val directorProfesion = view.findViewById<TextView>(R.id.directorProfesion)
        val campusProfesion = view.findViewById<TextView>(R.id.campusProfesion)
        val img = view.findViewById<ImageView>(R.id.imgProfesion)

        fun render(item:Carrera){
            val nameImg = item.img
            nombrePrefesion.text = item.carrera
            directorProfesion.text = "${item.director}"
            campusProfesion.text = "${item.campus}"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_profesion, parent, false)
        return ProfesionHolder(view)
    }

    override fun getItemCount(): Int = carreras.size

    override fun onBindViewHolder(holder: ProfesionHolder, position: Int) {
        val item = carreras[position]
        val imageRes = when (carreras[position].img) {
            "f_sistemas" -> R.drawable.f_sistemas
            "f_civil" -> R.drawable.f_civil
            "f_agroindustrial" -> R.drawable.f_agroindustrial
            "f_mecanica" -> R.drawable.f_mecanica
            "f_energia" -> R.drawable.f_energia
            "f_agronoma" -> R.drawable.f_agronoma
            else -> R.drawable.uns
        }
        holder.img.setImageResource(imageRes)
        holder.render(item)
    }

}