package com.example.asistencia

import android.content.ContentValues
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*

class Agregar : AppCompatActivity() {


    var txtCodigo: EditText?=null
    var txtCarrera: EditText?=null
    var txtDocente: EditText?=null
    var txtAsignatura: EditText?=null
    var txtSemestre: EditText?=null
    var txtArea: EditText?=null
    var txtFecha: EditText?=null
    var tvAsistencia:TableLayout?=null
    var tvPrincipal:TableLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)


        txtCodigo = findViewById(R.id.txtCodigo)
        txtCarrera = findViewById(R.id.txtCarrera)
        txtDocente = findViewById(R.id.txtDocente)
        txtAsignatura = findViewById(R.id.txtAsignatura)
        txtSemestre = findViewById(R.id.txtSemestre)
        txtArea = findViewById(R.id.txtArea)
        tvAsistencia = findViewById(R.id.tvAsistencia)



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

            var registro= ContentValues()


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
            Toast.makeText(this, "Se ha registrado Exitosamente", Toast.LENGTH_SHORT).show()

        } else{

            Toast.makeText(this, "Los campos deben de tener texto", Toast.LENGTH_SHORT).show()



        }

        llenarTabla()

        baseDatos.close()

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
                baseDatos.close()


            }else{

                txtCodigo?.setText("")
                txtCarrera?.setText("")
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
        val codigo = txtCodigo?.text.toString()


        if(codigo.isEmpty()==false) {

            val cant = baseDatos.delete("asistencia", "codigo='" + codigo + "'", null)
            if (cant > 0) {

                Toast.makeText(this, "El registro fue eliminado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "El registro no se encontro", Toast.LENGTH_SHORT).show()


            }
            txtCodigo?.setText("")

            txtCarrera?.setText("")
            txtDocente?.setText("")
            txtAsignatura?.setText("")
            txtSemestre?.setText("")
            txtArea?.setText("")

        }else{
            Toast.makeText(this, "El registro codigo debe tener texto", Toast.LENGTH_SHORT).show()








        }

        llenarTabla()


    }



            fun editar(view: View){

                val con=SQLite(this, "asistencia",null,1)
                val baseDatos=con.writableDatabase
                val codigo=txtCodigo?.text.toString()
                val carrera= txtCarrera?.text.toString()
                val docente= txtDocente?.text.toString()
                val asignatura= txtAsignatura?.text.toString()
                val semestre= txtSemestre?.text.toString()
                val area= txtArea?.text.toString()

                if(!codigo.isEmpty() && !carrera.isEmpty()&& !docente.isEmpty() && !asignatura.isEmpty() && !semestre.isEmpty() && !area.isEmpty()) {

                    var registro=ContentValues()
                    registro.put("codigo",codigo)
                    registro.put("carrera", carrera)
                    registro.put("docente",docente)
                    registro.put("asignatura", asignatura)
                    registro.put("semestre", semestre)
                    registro.put("area", area)

                    val cant= baseDatos.update("asistencia",registro, "codigo='$codigo'",null)
                                    if (cant>0){

                                                     Toast.makeText(this, "El registro se ha actualizado exitosamente", Toast.LENGTH_SHORT).show()
                                               }else{

                                                         Toast.makeText(this, "El registro no fue encontrado", Toast.LENGTH_SHORT).show()

                                                    }

     }else{
            Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show()

          }
                llenarTabla()


    }



    fun llenarTabla(){
        tvAsistencia?.removeAllViews()

        val con=SQLite(this, "asistencia", null,1)
        val baseDatos=con.writableDatabase
        val fila=baseDatos.rawQuery("select codigo,carrera,docente,asignatura,semestre,area from asistencia",null)


        fila.moveToFirst()
        do {
            val registro=LayoutInflater.from(this).inflate(R.layout.item_table_layout_pn,null,false)
            val tvCodigo=registro.findViewById<View>(R.id.tvCodigo) as TextView
            val tvCarrera=registro.findViewById<View>(R.id.tvCarrera) as TextView
            val tvDocente=registro.findViewById<View>(R.id.tvDocente)as TextView
            val tvAsignatura=registro.findViewById<View>(R.id.tvAsignatura) as TextView
            val tvSemestre=registro.findViewById<View>(R.id.tvSemestre) as TextView
            val tvArea=registro.findViewById<View>(R.id.tvArea) as TextView

            tvCodigo.setText(fila.getString(0))
            tvCarrera.setText(fila.getString(1))
            tvDocente.setText(fila.getString(2))
            tvAsignatura.setText(fila.getString(3))
            tvSemestre.setText(fila.getString(4))
            tvArea.setText(fila.getString(5))
            tvAsistencia?.addView(registro)
          



        }while (fila.moveToNext())


    }


    fun clickRegistroAsistencia(view: View){
        resetColorRegistros()
        view.setBackgroundColor(Color.GREEN)
        val registro=view as TableRow
        val controlCodigo=registro.getChildAt(0) as TextView
        val codigo=controlCodigo.text.toString()
        val con=SQLite(this, "asistencia",null,1)
        val baseDatos=con.writableDatabase
        if(!codigo.isEmpty()){

            val fila = baseDatos.rawQuery("select codigo,carrera,docente,asignatura,semestre,area from asistencia where codigo='$codigo'",null)
            if (fila.moveToFirst()){

                txtCodigo?.setText(fila.getString(0))
                txtCarrera?.setText(fila.getString(1))
                txtDocente?.setText(fila.getString(2))
                txtAsignatura?.setText(fila.getString(3))
                txtSemestre?.setText(fila.getString(4))
                txtArea?.setText(fila.getString(5))

            }else{

                txtCodigo?.setText("")
                txtCarrera?.setText("")
                txtDocente?.setText("")
                txtAsignatura?.setText("")
                txtSemestre?.setText("")
                txtArea?.setText("")
                Toast.makeText(this, "No se ha encontrado ningun registro", Toast.LENGTH_SHORT).show()

            }

        }



    }


    fun resetColorRegistros(){


        for (i in 0 .. tvAsistencia!!.childCount){

            val registro=tvAsistencia?.getChildAt(i)
            registro?.setBackgroundColor(Color.WHITE)

        }


    }


}

