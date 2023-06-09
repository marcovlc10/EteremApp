package lopez.marco.proyectoeterem

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import lopez.marco.pruebabottomnav.*
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
//    private var _binding: FragmentInicioBinding? = null
//    private val binding get() = _binding!!
//    private var adapter: AdaptadorLugares? = null
    private lateinit var lugaresRecyclerview : RecyclerView
    private lateinit var lugaresArrayList : ArrayList<Lugar>
    private lateinit var dbref : DatabaseReference
    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()

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

        val imagen_detalles_lugar: ImageView = view_lugar!!.findViewById(R.id.imagen_detalles_lugar)
        val view_perfil: View = myFragmentView!!.findViewById(R.id.view_perfil)
        val btn_opciones: View = myFragmentView!!.findViewById(R.id.view_opciones)

        lugaresRecyclerview = myFragmentView!!.findViewById(R.id.lugaresList)
        lugaresRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        lugaresRecyclerview.setHasFixedSize(true)

        lugaresArrayList = arrayListOf<Lugar>()
        getLugarData()

        imagen_detalles_lugar.setOnClickListener{
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

    private fun getLugarData() {
        db.collection("Restaurante")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var descripcion=document.get("descripcion").toString()
                    var nombre=document.get("nombre").toString()
                    var precio=document.get("precio").toString()
                    var ubicacion=document.get("ubicacion").toString()
                    var valoracion=document.get("valoracion").toString()
                    var imagen= document.get("imagen").toString()

                    var lugar=Lugar(nombre,descripcion,precio, valoracion,ubicacion, imagen)
//                    println(lugar)
                    lugaresArrayList.add(lugar!!)
                }
                lugaresRecyclerview.adapter = AdapterLugares(lugaresArrayList)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error al obtener los datos: ", exception)
            }
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

    private fun loadFragment(fragment: Fragment) {
        val transaction = getParentFragmentManager().beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }
}