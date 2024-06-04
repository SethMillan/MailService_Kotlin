package com.example.mailservice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [newMail.newInstance] factory method to
 * create an instance of this fragment.
 */
class newMail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  var user :String = ""
    private lateinit var txtCorreo: EditText
    private lateinit var txtAsunto: EditText
    private lateinit var txtContenido: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            user = it.getString("usuario").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val miVista = inflater.inflate(R.layout.fragment_new_mail, container, false)
        val btnEnviar= miVista.findViewById<Button>(R.id.btnSendNewMessage)
        txtCorreo = miVista.findViewById<EditText>(R.id.txtEmail)
        txtAsunto = miVista.findViewById<EditText>(R.id.txtAsunto)
        txtContenido = miVista.findViewById<EditText>(R.id.txtContenido)


        btnEnviar.setOnClickListener {
            if(txtCorreo.text.toString().isEmpty() || txtAsunto.text.toString().isEmpty() || txtContenido.text.toString().isEmpty() ) {
                Toast.makeText(requireContext(), "Complete todos los campos", Toast.LENGTH_LONG).show()
            }else{
                getIdConversacion()
            }
        }


        return miVista
    }

    private fun getIdConversacion() {
        val url = resources.getString(R.string.ws) + "endPointCountConversaciones.php"
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                interpreta(it)
            },
            { Log.e("IdConversacion", "${it.message}") }
        )
        Volley.newRequestQueue(requireContext()).add(request)
    }
    private fun interpreta(result:String){
        Log.i("WAOS", "EL numero es"+result)
        try {
            val modifiedString = result.substring(14, result.length - 3)
            val numero = modifiedString.toInt()+1
            Toast.makeText(requireContext(), "Indice: "+numero, Toast.LENGTH_SHORT).show()
            sendMessage(txtCorreo.text.toString(),txtAsunto.text.toString(),txtContenido.text.toString(), numero.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendMessage(email:String, asunto:String, contenido: String, idConversacion : String?){
        val url = resources.getString(R.string.ws) + "endPointAddNewMensaje.php"
        if (idConversacion!=null) {
            val request = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    Toast.makeText(requireContext(), "Mensaje Enviado con exito", Toast.LENGTH_LONG).show()
                    txtCorreo.setText("")
                    txtAsunto.setText("")
                    txtContenido.setText("")
                },
                { Log.e("LoginError", "" + it.message) }) {
                override fun getParams(): MutableMap<String, String>? {
                    val dictionary = HashMap<String, String>()
                    dictionary.put("idConversacion", idConversacion)
                    dictionary.put("correoEmisor", user)
                    dictionary.put("correoReceptor", email)
                    dictionary.put("asunto", asunto)
                    dictionary.put("mensaje", contenido)

                    return dictionary
                }
            }

            Volley.newRequestQueue(requireContext()).add(request)
        }

    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            newMail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}