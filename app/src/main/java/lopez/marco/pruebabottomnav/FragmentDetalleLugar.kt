package lopez.marco.pruebabottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import lopez.marco.proyectoeterem.FragmentInicio

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetalleLugar.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetalleLugar : Fragment() {
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
        var home = FragmentInicio()
        var perfil = FragmentPerfil()
        val myFragmentView: View? = inflater.inflate(R.layout.fragment_detalle_lugar, container, false)
        val btn_regresar: ImageView = myFragmentView!!.findViewById(R.id.btn_detalle_regresar)
        val btn_logo: ImageView = myFragmentView!!.findViewById(R.id.logo)
        val view_perfil: View = myFragmentView!!.findViewById(R.id.view_perfil)
        val usuario_reseña: View = myFragmentView!!.findViewById(R.id.usuario_reseña)

        btn_logo.setOnClickListener{
            loadFragment(home)
        }

        btn_regresar.setOnClickListener{
            loadFragment(home)
        }

        view_perfil.setOnClickListener{
            loadFragment(perfil)
        }

        usuario_reseña.setOnClickListener{
            loadFragment(perfil)
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
         * @return A new instance of fragment DetalleLugar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetalleLugar().apply {
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