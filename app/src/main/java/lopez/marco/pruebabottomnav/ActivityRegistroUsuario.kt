package lopez.marco.pruebabottomnav

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class ActivityRegistroUsuario : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        auth= FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        val et_usuario: EditText = findViewById(R.id.nombre_usuario)
        val et_contrasenia: EditText = findViewById(R.id.contrasenia)
        val et_confirmar_contrasenia: EditText = findViewById(R.id.confirmar_contrasenia)
        val et_email: EditText = findViewById(R.id.email)

        val btn_crear_cuenta: Button = findViewById(R.id.btn_crear_cuenta)
        val text_iniciar_sesion: TextView = findViewById(R.id.text_iniciar_sesion)

        btn_crear_cuenta.setOnClickListener{
            val email=et_email.text.toString()
            val password=et_contrasenia.text.toString()
            val confirm_password=et_confirmar_contrasenia.text.toString()
            val usuario=et_usuario.text.toString()

            if (!usuario.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirm_password.isEmpty()) {
                if (password.length >= 6) {
                    registerUser(email, password, usuario)
                } else {
                    Toast.makeText(this@ActivityRegistroUsuario, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@ActivityRegistroUsuario, "Debes completar los campos", Toast.LENGTH_SHORT).show()
            }
//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {3
//                        val user = auth.currentUser
//                        //Set user display name
//                        val profileUpdates = UserProfileChangeRequest.Builder()
//                            .setDisplayName(usuario)
//                            .build()
//                        user?.updateProfile(profileUpdates)
//
//                        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
//                        val editor = sharedPreferences.edit()



                        //Add user data to local storage
//                        editor.putString("email", email)
//                        editor.putString("username", usuario)
//                        editor.apply()
//                        registerUser(email, password, usuario)
//                        Toast.makeText(baseContext, "Registro exitoso, bienvenido", Toast.LENGTH_LONG).show()
//                        reload2()
////                    } else {
//                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        Toast.makeText(baseContext, "Ha ocurrido un error",
//                            Toast.LENGTH_SHORT).show()
////                    }
//                }
        }

            text_iniciar_sesion.setOnClickListener {
                val intent = Intent(this, ActivityInicioSesion::class.java)
                startActivity(intent)
                finish()
            }

    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }

    private fun reload2() {
        val intent = Intent(this, MainActivityNav::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerUser(emailNode:String, passwordNode:String, usuarioNode:String) {
        auth.createUserWithEmailAndPassword(emailNode, passwordNode)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val map = hashMapOf(
                        "usuario" to usuarioNode,
                        "email" to emailNode,
                        "password" to passwordNode
                    )
                    val id = auth.currentUser?.uid
                    if (id != null) {
                        database.child("Users").child(id).setValue(map)
                    }
                    Toast.makeText(this@ActivityRegistroUsuario, "Cuentra creada exitosamente", Toast.LENGTH_SHORT).show()
                    reload2()
                } else {
                    Toast.makeText(this@ActivityRegistroUsuario, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show()
                }
            }
    }


}