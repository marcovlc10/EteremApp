package lopez.marco.pruebabottomnav

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import lopez.marco.proyectoeterem.FragmentInicio

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInicioSesion.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInicioSesion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var auth: FirebaseAuth
//    private lateinit var binding: FragmentInicioSesion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        var inicio = FragmentInicio()
        var registrarUsuario=FragmentRegistrarUsuario()

        val myFragmentView: View? = inflater.inflate(R.layout.fragment_inicio_sesion, container, false)

        val btn_iniciar_sesion: AppCompatButton = myFragmentView!!.findViewById(R.id.btn_iniciar_sesion)
        val btn_registrar_sesion: TextView = myFragmentView!!.findViewById(R.id.text_registrar)


        btn_iniciar_sesion.setOnClickListener{
            loadFragment(inicio)
//            val email = myFragmentView.findViewById<EditText>(R.id.et_email).text.toString()
//            val contrasenia = myFragmentView.findViewById<EditText>(R.id.et_password).text.toString()
//            Toast.makeText(activity, "Iniciando sesión...", Toast.LENGTH_SHORT).show()
//            when{
//                email.isEmpty() -> {
//                    myFragmentView.findViewById<EditText>(R.id.et_email).error="Email requerido"
//                    myFragmentView.findViewById<EditText>(R.id.et_email).requestFocus()
//                }
//                contrasenia.isEmpty() -> {
//                    myFragmentView.findViewById<EditText>(R.id.et_password).error="Contraseña requerido"
//                    myFragmentView.findViewById<EditText>(R.id.et_password).requestFocus()
//                }
//                else -> {
//                    signIn(email, contrasenia)
//                }
//            }
        }

        btn_registrar_sesion.setOnClickListener{
            loadFragment(registrarUsuario)
        }

        return myFragmentView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentInicioSesion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentInicioSesion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = getParentFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }

    private fun signIn(email:String, password:String){
        var inicio = FragmentInicio()
        activity?.let {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d("TAG", "signInWithEmail:success")
                    //Get user data
                    val user = auth.currentUser
                    val sharedPreferences = activity?.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    //Add user data to local storage
                    editor?.putString("email", email)
                    editor?.putString("username", user?.displayName)
                    editor?.apply()

                    Toast.makeText(activity, "Authentication success.", Toast.LENGTH_SHORT).show()
                    loadFragment(inicio)
                } else {
                    // Sign in failed
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(activity, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    // updateUI(null)
                }
            }
        }
    }

}