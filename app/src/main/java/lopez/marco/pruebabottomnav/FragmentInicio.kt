package lopez.marco.proyectoeterem

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import lopez.marco.pruebabottomnav.*
import lopez.marco.pruebabottomnav.databinding.FragmentInicioBinding

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
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private var adapter: AdaptadorLugares? = null

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
        val view_lugar: View? = inflater.inflate(R.layout.view_lugar, container, false)
        val detalles_lugar: ImageView = view_lugar!!.findViewById(R.id.detalles_lugar)
        val view_perfil: View = myFragmentView!!.findViewById(R.id.view_perfil)
        val btn_opciones: View = myFragmentView!!.findViewById(R.id.view_opciones)

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first){
            fillLugares()
            first = false
        }

        adapter = AdaptadorLugares(root.context, lugares)
        val gridTasks: GridView = root.findViewById(R.id.gridViewLugaresInicio)
        gridTasks.adapter = adapter
        println(lugares.toString())

        detalles_lugar.setOnClickListener{
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

    fun fillLugares(){
        lugares.add(Lugar("Los tacos de las seis",
            "Los tradicionales y mejores tacos dorados y al vapor de Ciudad Obregón, Sonora.",
            "barato",
            4.5F))
        lugares.add(Lugar("Veranda food garden",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce porttitorlacus bibendum viverra sagittis..",
            "caro",
            4.0F))
    }

    companion object {
        var first = true
        var lugares = ArrayList<Lugar>()
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

    private class AdaptadorLugares: BaseAdapter {
        var lugares = ArrayList<Lugar>()
        var context: Context? = null
        constructor(context: Context, lugares: ArrayList<Lugar>){
            this.context = context
            this.lugares = lugares
        }

        override fun getCount(): Int {
            return lugares.size
        }
        override fun getItem(p0: Int): Any {
            return lugares[p0]
        }
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var lugar = lugares[p0]
            var inflater = LayoutInflater.from(context)
            var view = inflater.inflate(R.layout.view_lugar, null)

            var descripcion: TextView = view.findViewById(R.id.descripcion_lugar)
            var precio: TextView = view.findViewById(R.id.precio)
            var rating: RatingBar = view.findViewById(R.id.ratingBar)

            descripcion.setText(lugar.descripcion)
            precio.setText(lugar.precio)
            rating.rating = lugar.valoracion

            return view
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = getParentFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }
}