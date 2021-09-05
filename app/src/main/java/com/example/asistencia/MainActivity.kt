package com.example.asistencia

import android.content.ContentValues
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }



    fun Agregar(view: View){


        var click =findViewById<ImageButton>(R.id.Click)

        val intent = Intent(this, Agregar::class.java)
        startActivity(intent)


    }



}


