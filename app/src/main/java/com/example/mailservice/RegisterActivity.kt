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
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnMoveToLogin = findViewById<TextView>(R.id.btnMoveToLogin)
        btnMoveToLogin.setOnClickListener {
            btnMoveToLogin.setTextColor(getColor(R.color.white))
            val segue = Intent(this, LoginActivity::class.java)
            startActivity(segue)
        }

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            val user = findViewById<EditText>(R.id.txtUserLog).text.toString()
            val email = findViewById<EditText>(R.id.txtEmailLog).text.toString()
            val pass = findViewById<EditText>(R.id.txtPassLog).text.toString()

            register(user,email,pass)
        }
    }
    private fun register(user: String, email: String, pass: String) {
        val url = resources.getString(R.string.ws) + "endPointCreateAcount.php"
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

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(
                this, "Error", Toast.LENGTH_LONG
            ).show()
        }
    }
}