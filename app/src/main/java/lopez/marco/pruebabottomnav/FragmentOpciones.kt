package lopez.marco.pruebabottomnav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import lopez.marco.proyectoeterem.FragmentInicio

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOpciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOpciones : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        var inicio = FragmentInicio()
        val myFragmentView: View? = inflater.inflate(R.layout.fragment_opciones, container, false)
        val btn_volver: View = myFragmentView!!.findViewById(R.id.btn_opciones_volver)
        val btn_cerrar_sesion: Button = myFragmentView!!.findViewById(R.id.btn_cerrar_sesion)

        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        btn_volver.setOnClickListener{
            loadFragment(inicio)
        }

        btn_cerrar_sesion.setOnClickListener{
            auth.signOut()
            val intent = Intent(requireContext(), ActivityInicioSesion::class.java)
            startActivity(intent)
            requireActivity().finish()
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
         * @return A new instance of fragment FragmentOpciones.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOpciones().apply {
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



}