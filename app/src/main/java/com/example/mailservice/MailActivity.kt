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
        replaceFragment(newMail())
        bingus.btnNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.newmail->replaceFragment(newMail())
                R.id.todos->replaceFragment(todos())
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