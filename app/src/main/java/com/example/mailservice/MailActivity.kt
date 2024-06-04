package com.example.mailservice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mailservice.databinding.ActivityMailBinding

class MailActivity : AppCompatActivity() {

    private lateinit var bingus: ActivityMailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bingus = ActivityMailBinding.inflate(layoutInflater)
        setContentView(bingus.root)

        val user = intent.getStringExtra("usuario")

        // Crea un Bundle con el valor de user
        val bundle = Bundle().apply {
            putString("usuario", user)
        }

        val fragmentoTodo = todos().apply {
            arguments = bundle
        }
        val fragmentoNewMail = newMail().apply {
            arguments = bundle
        }
        fragmentoTodo.arguments = bundle
        fragmentoNewMail.arguments = bundle
        replaceFragment(fragmentoNewMail)
        bingus.btnNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.newmail->replaceFragment(fragmentoNewMail)
                R.id.todos->replaceFragment(fragmentoTodo)
                else->{

                }

            }
            true
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_layout,fragment)
        fragmentTransaction.commit()
    }


}