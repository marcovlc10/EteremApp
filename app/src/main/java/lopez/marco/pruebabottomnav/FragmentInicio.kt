package lopez.marco.proyectoeterem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import lopez.marco.pruebabottomnav.FragmentDetalleLugar
import lopez.marco.pruebabottomnav.FragmentOpciones
import lopez.marco.pruebabottomnav.FragmentPerfil
import lopez.marco.pruebabottomnav.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentInicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentInicio : Fragment() {
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
        var detalle_lugar = FragmentDetalleLugar()
        var perfil = FragmentPerfil()
        var opciones = FragmentOpciones()
        val myFragmentView: View? = inflater.inflate(R.layout.fragment_inicio, container, false)
        val btn_detalles_lugar: AppCompatButton = myFragmentView!!.findViewById(R.id.btn_detalle_lugar)
        val view_perfil: View = myFragmentView!!.findViewById(R.id.view_perfil)
        val btn_opciones: View = myFragmentView!!.findViewById(R.id.view_opciones)

        btn_detalles_lugar.setOnClickListener{
            loadFragment(detalle_lugar)
        }

        view_perfil.setOnClickListener{
            loadFragment(perfil)
        }

        btn_opciones.setOnClickListener{
            loadFragment(opciones)
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
         * @return A new instance of fragment FragmentInicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentInicio().apply {
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