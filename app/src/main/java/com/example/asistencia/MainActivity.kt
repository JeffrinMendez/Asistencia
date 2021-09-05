package com.example.asistencia

import android.content.ContentValues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var txtCodigo: EditText?=null
    var txtCarrera: EditText?=null
    var txtDocente: EditText?=null
    var txtAsignatura: EditText?=null
    var txtSemestre: EditText?=null
    var txtArea: EditText?=null
    var txtFecha: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtCodigo = findViewById(R.id.txtCodigo)
        txtCarrera = findViewById(R.id.txtCarrera)
        txtDocente = findViewById(R.id.txtDocente)
        txtAsignatura = findViewById(R.id.txtAsignatura)
        txtSemestre = findViewById(R.id.txtSemestre)
        txtArea = findViewById(R.id.txtArea)


    }

    fun insertar (view: View){

var con=SQLite(this, "asistencia", null,1)
var baseDatos=con.writableDatabase

        var codigo= txtCodigo?.text.toString()
        var carrera= txtCarrera?.text.toString()
        var docente= txtDocente?.text.toString()
        var asignatura= txtAsignatura?.text.toString()
        var semestre= txtSemestre?.text.toString()
        var area= txtArea?.text.toString()


        if (codigo.isEmpty()==false&& carrera.isEmpty()==false && docente.isEmpty()== false && asignatura.isEmpty()==false && semestre.isEmpty()==false && area.isEmpty()==false){

            var registro=ContentValues()


            registro.put("codigo",codigo)
            registro.put("carrera", carrera)
            registro.put("docente",docente)
            registro.put("asignatura", asignatura)
            registro.put("semestre", semestre)
            registro.put("area", area)
            baseDatos.insert("asistencia",null,registro)


            txtCodigo?.setText("")
            txtCarrera?.setText("")
            txtDocente?.setText("")
            txtAsignatura?.setText("")
            txtSemestre?.setText("")
            txtArea?.setText("")
            Toast.makeText(this, "Se ha registrado Exitosamente",Toast.LENGTH_SHORT).show()

        } else{

            Toast.makeText(this, "Los campos deben de tener texto",Toast.LENGTH_SHORT).show()



        }


    }

    fun buscar (view: View){

        val con=SQLite(this, "asistencia", null, 1)
        val baseDatos=con.writableDatabase
        val codigo=txtCodigo?.text.toString()
        if (codigo.isEmpty()==false){

            val fila=baseDatos.rawQuery("select carrera,docente,asignatura,semestre,area from asistencia where codigo = '$codigo'",null)

            if(fila.moveToFirst()==true){
                txtCarrera?.setText(fila.getString(0))
                txtDocente?.setText(fila.getString(1))
                txtAsignatura?.setText(fila.getString(2))
                txtSemestre?.setText(fila.getString(3))
                txtArea?.setText(fila.getString(4))



            }else{
                txtCarrera?.setText(fila.getString(0))
                txtDocente?.setText("")
                txtAsignatura?.setText("")
                txtSemestre?.setText("")
                txtArea?.setText("")
                Toast.makeText(this,"No se encontraron registros", Toast.LENGTH_SHORT).show()

            }


        }



    }

    fun eliminar(view: View){


        val con=SQLite(this, "asistencia",null,1)
        val baseDatos=con.writableDatabase




    }


}