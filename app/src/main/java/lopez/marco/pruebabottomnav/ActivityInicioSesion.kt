package lopez.marco.pruebabottomnav

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import lopez.marco.proyectoeterem.FragmentInicio
import lopez.marco.pruebabottomnav.databinding.ActivityInicioSesionBinding

class ActivityInicioSesion : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityInicioSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.textRegistrar.setOnClickListener{
            val intent = Intent(this, ActivityRegistroUsuario::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnIniciarSesion.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show()
            when{
                email.isEmpty() -> {
                    binding.etEmail.error = "Introduzca un correo válido"
                    binding.etEmail.requestFocus()
                }
                password.isEmpty() -> {
                    binding.etPassword.error = "Se requiere de una contraseña"
                    binding.etPassword.requestFocus()
                }
                else -> {
                    signIn(email, password)
                }
            }
        }

    }

    private fun signIn(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success
                Log.d("TAG", "signInWithEmail:success")
                //Get user data
                val user = auth.currentUser
                val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                //Add user data to local storage
                editor.putString("email", email)
                editor.putString("username", user?.displayName)
                editor.apply()

                Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT).show()
                reload2()
            } else {
                // Sign in failed
                Log.w("TAG", "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                // updateUI(null)
            }
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