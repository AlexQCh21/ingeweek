package com.cursokotlin.ingeweek

import android.os.Build
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlin.ingeweek.recyclerEventos.Actividad
import com.cursokotlin.ingeweek.recyclerEventos.ActividadAdapter
import com.cursokotlin.ingeweek.recyclerEventos.GrupoActividades
import com.cursokotlin.ingeweek.recyclerV.Carrera
import com.cursokotlin.ingeweek.recyclerV.ProfesionAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var listaGrupos: List<GrupoActividades> = listOf()
    private var listaActividades: MutableList<Actividad> = mutableListOf()
    private lateinit var adapter: ActividadAdapter
    private var ordenAscendente = true

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

        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.menu_button)
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.menu_btn)
        }

        // Ordenar botón
        val btnOrdenar = view.findViewById<Button>(R.id.menu_ordenar)
        btnOrdenar.setOnClickListener {
            ordenarPorFecha()
        }

        chargerRecyclerEvents(view)
    }

    fun chargerRecyclerEvents(view: View) {
        val jsonString = context?.assets?.open("actividades.json")?.bufferedReader().use { it?.readText() ?: "" }
        val type = object : TypeToken<List<GrupoActividades>>() {}.type
        listaGrupos = Gson().fromJson(jsonString, type)

        // Por defecto muestra todas las actividades de todas las carreras (aplana la lista)
        val actividades = listaGrupos.flatMap { it.actividades }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerEvents)
        adapter = ActividadAdapter(actividades)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    // Ordenar por fecha descendente (más reciente primero)
    @RequiresApi(Build.VERSION_CODES.O)
    fun ordenarPorFecha() {
        val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        if (ordenAscendente) {
            listaActividades.sortBy {
                try {
                    LocalDate.parse(it.fecha, formato)
                } catch (e: Exception) {
                    LocalDate.MAX
                }
            }
        } else {
            listaActividades.sortByDescending {
                try {
                    LocalDate.parse(it.fecha, formato)
                } catch (e: Exception) {
                    LocalDate.MIN
                }
            }
        }
        ordenAscendente = !ordenAscendente // Cambia el sentido para la próxima vez
        adapter.notifyDataSetChanged()
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val themedContext = ContextThemeWrapper(requireContext(), R.style.PopupMenuThemeOverlay)
        val popup = PopupMenu(themedContext, v)

        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            val carreraId = getCarreraIdFromMenuItem(menuItem.itemId)
            if (carreraId != null) {
                filtrarPorCarrera(carreraId)
            }
            true
        }

        popup.setOnDismissListener {
            // Lógica al cerrar el menú
        }

        popup.show()
    }

    private fun filtrarPorCarrera(carreraId: Int) {
        // Busca el grupo correspondiente
        val grupo = listaGrupos.find { it.id == carreraId }
        val actividadesFiltradas = grupo?.actividades ?: emptyList()
        adapter?.updateList(actividadesFiltradas)
    }

    private fun getCarreraIdFromMenuItem(menuItemId: Int): Int? {
        return when (menuItemId) {
            R.id.option_1 -> 1 // Sistemas e Informática
            R.id.option_2 -> 4 // Mecánica
            R.id.option_3 -> 2 // Civil
            R.id.option_4 -> 6 // Agrónoma
            R.id.option_5 -> 3 // Agroindustrial
            R.id.option_6 -> 5 // En Energía
            else -> null
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EventsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}