package com.cursokotlin.ingeweek.recyclerEventos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.ingeweek.R

class ActividadAdapter(private var actividades:List<Actividad>):RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder>() {
    class ActividadViewHolder(view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.txtname)
        val txtCategoria = view.findViewById<TextView>(R.id.txtCategoria)
        val txtFecha = view.findViewById<TextView>(R.id.txtFecha)
        val txtHora = view.findViewById<TextView>(R.id.txtHora)
        val imgCategoria = view.findViewById<ImageView>(R.id.imgCategoria)
        val layoutPonente = view.findViewById<LinearLayout>(R.id.layoutPonente)
        val txtPonente = view.findViewById<TextView>(R.id.txtPonente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event,parent,false)
        return ActividadViewHolder(view)
    }

    override fun getItemCount(): Int =actividades.size

    override fun onBindViewHolder(holder: ActividadViewHolder, position: Int) {
        holder.name.text = if (actividades[position].categoria == "deporte") {
            actividades[position].actividad
        } else {
            actividades[position].tema
        }

        if(actividades[position].categoria == "deporte"){
            holder.imgCategoria.setImageResource(R.drawable.ic_soccer)
            holder.layoutPonente.visibility = View.GONE
        }else{
            holder.imgCategoria.setImageResource(R.drawable.ic_school)
            holder.layoutPonente.visibility = View.VISIBLE
            holder.txtPonente.text = actividades[position].ponente
        }
        holder.txtCategoria.text = actividades[position].categoria
        holder.txtFecha.text = actividades[position].fecha
        holder.txtHora.text = actividades[position].hora

    }

    fun updateList(nuevaLista: List<Actividad>) {
        actividades = nuevaLista
        notifyDataSetChanged()
    }
}