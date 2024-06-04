package com.example.mailservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mailservice.objects.Usuario
import com.google.gson.Gson
import org.json.JSONArray

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Mover a Registro
        val btnMoveToRegister = findViewById<TextView>(R.id.btnMoveToRegister)
        btnMoveToRegister.setOnClickListener {
            btnMoveToRegister.setTextColor(getColor(R.color.white))
            val segue = Intent(this, RegisterActivity::class.java)
            startActivity(segue)
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val user = findViewById<EditText>(R.id.txtUserLog).text.toString()
            val email = findViewById<EditText>(R.id.txtEmailLog).text.toString()
            val pass = findViewById<EditText>(R.id.txtPassLog).text.toString()
            login(user,email,pass)
        }
    }
    private fun login(user: String, email: String, pass: String) {
        val url = resources.getString(R.string.ws) + "endPointLogin.php"
        Log.i("Data","name:"+user+"   email: "+email+"   pass:"+pass)
        val request = object : StringRequest(Request.Method.POST, url, { interpreta(it) }, { Log.e("LoginError", "" + it.message) }) {
            override fun getParams(): MutableMap<String, String>? {
                val dictionary = HashMap<String, String>()
                dictionary.put("name", user)
                dictionary.put("email",email)
                dictionary.put("password",pass)
                return dictionary
            }
        }

        Volley.newRequestQueue(this).add(request)
    }
    private fun interpreta(result: String) {
        Log.i("LoginResult", result)

        try {
            val jsonArray = JSONArray(result)
            if(jsonArray.length() > 0) {
                val usuario = Gson().fromJson(
                    jsonArray.get(0).toString(),
                    Usuario::class.java
                )
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_LONG).show()
                val segue = Intent(this, MailActivity::class.java)
                segue.putExtra("usuario",usuario.email)
                startActivity(segue)
            } else {
                Toast.makeText(
                    this, "Usuario y/o contraseña incorrectos",
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this, "Error", Toast.LENGTH_LONG
            ).show()
        }
}
}