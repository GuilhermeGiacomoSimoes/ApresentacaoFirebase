package com.example.apresentacaofirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_lista_usuarios.*

class ListaUsuariosActivity : AppCompatActivity() {

    private lateinit var firebase: DatabaseReference
    private var listString = ArrayList<String>()
    private var listUsuario = ArrayList<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuarios)

        firebase = ConfiguracaoFirebase.firebase

        listarUsuario()

        listaUsuarios.setOnItemClickListener { parent, view, position, id ->
            val usuario = listUsuario[position]
            val intent = Intent(this, EditaActivity ::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }

    private fun listarUsuario(){
        firebase.child("usuario").addValueEventListener(object  : ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                for(data in p0.children){
                    val usuario = data.getValue(Usuario ::class.java)
                    listUsuario.add(usuario!!)
                    listString.add(usuario.nome)
                }

                inflarList()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    private fun  inflarList(){
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listString)
        listaUsuarios.adapter = adapter
    }
}
