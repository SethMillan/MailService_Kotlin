package com.example.mailservice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.adapter.conversacionAdapter
import com.example.mailservice.databinding.ActivityMailBinding
import com.example.mailservice.objects.Mensaje

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [todos.newInstance] factory method to
 * create an instance of this fragment.
 */
class todos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  var user :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            user = it.getString("usuario").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val miVista= inflater.inflate(R.layout.fragment_todos, container, false)
        val recyclerView = miVista.findViewById<RecyclerView>(R.id.recyclerConversaciones)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val mensajes = listOf(
            Mensaje(1, 1, "alice@example.com", "bob@example.com", "Reunion importante", "Hola Bob, solo quería recordarte sobre nuestra importante reunión mañana a las 10am. Por favor, llega a tiempo.", false, false),
            Mensaje(2, 1, "bob@example.com", "alice@example.com", "Re: Reunion importante", "Gracias Alice, estaré allí. ¿Podrías enviarme la agenda?", true, true),
            Mensaje(3, 2, "charlie@example.com", "equipo@example.com", "Anuncio de nuevo proyecto", "Hola equipo, estoy emocionado de anunciar nuestro nuevo proyecto, 'XYZ'. Trabajaremos en este proyecto durante los próximos meses.", true, true),
            Mensaje(4, 3, "david@example.com", "sarah@example.com", "Pregunta rápida", "Hola Sarah, tengo una pregunta rápida sobre el informe. ¿Puedes llamarme cuando estés libre?", false, false),
            Mensaje(5, 3, "sarah@example.com", "david@example.com", "Re: Pregunta rápida", "Claro David, te llamaré en 5 minutos.", true, true)
        )
        recyclerView.adapter = conversacionAdapter(mensajes)

        return miVista
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            todos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}