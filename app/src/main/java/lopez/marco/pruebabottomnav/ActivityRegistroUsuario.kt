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
import org.w3c.dom.Text

class ActivityRegistroUsuario : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val usuario: EditText = findViewById(R.id.nombre_usuario)
        val contrasenia: EditText = findViewById(R.id.contrasenia)
        val confirmar_contrasenia: EditText = findViewById(R.id.confirmar_contrasenia)
        val email: EditText = findViewById(R.id.email)

        val btn_crear_cuenta: Button = findViewById(R.id.btn_crear_cuenta)
        val text_iniciar_sesion: TextView = findViewById(R.id.text_iniciar_sesion)

        btn_crear_cuenta.setOnClickListener{
            auth.createUserWithEmailAndPassword(email.text.toString(), contrasenia.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {3
                        //
                        val user = auth.currentUser
                        //Set user display name
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(usuario.text.toString())
                            .build()

                        user?.updateProfile(profileUpdates)

                        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        //Add user data to local storage
                        editor.putString("email", email.text.toString())
                        editor.putString("username", usuario.text.toString())
                        editor.apply()
                        // Sign in success, go to the next activity
//                        val intent = Intent(this, EventosActivity::class.java)
//                        startActivity(intent)
//                        finish()
                        Toast.makeText(baseContext, "Registro exitoso, bienvenido",
                            Toast.LENGTH_LONG).show()
                        reload2()
                    } else {
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Ha ocurrido un error",
                            Toast.LENGTH_SHORT).show()
                    }
                }

                reload2()

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

}