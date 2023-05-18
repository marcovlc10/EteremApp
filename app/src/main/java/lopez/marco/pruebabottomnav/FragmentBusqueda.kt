package lopez.marco.pruebabottomnav

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import lopez.marco.proyectoeterem.FragmentInicio
import lopez.marco.proyectoeterem.FragmentSocial

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentBusqueda.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentBusqueda : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var programmingLanguagesLV: ListView
    lateinit var listAdapter: ArrayAdapter<String>
    var listaLugares: ArrayList<Lugar> = ArrayList();
    var listaNombresLugares: ArrayList<String> = ArrayList()
    lateinit var searchView: SearchView

    private val db = Firebase.firestore

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
        // Inflate the layout for this fragment
        val myFragmentView: View? = inflater.inflate(R.layout.fragment_busqueda, container, false)
        val btn_cerrar: ImageView = myFragmentView!!.findViewById(R.id.btn_busqueda_regresar)
        val btn_logo: ImageView = myFragmentView!!.findViewById(R.id.logo)

        val programmingLanguagesLV: ListView = myFragmentView!!.findViewById(R.id.list_view_busqueda)
        val searchView: SearchView = myFragmentView!!.findViewById(R.id.search_bar_busqueda)

        getLugares()

        listAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, listaNombresLugares)

        programmingLanguagesLV.adapter = listAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                if (listaNombresLugares.contains(query)) {
                    // if query exist within list we
                    // are filtering our list adapter.
                    listAdapter.filter.filter(query)
                } else {
                    // if query is not present we are displaying
                    // a toast message as no  data found..
                    Toast.makeText(requireContext(), "Error al obtener la lista de lugares...", Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                listAdapter.filter.filter(newText)
                return false
            }
        })

        btn_cerrar.setOnClickListener{
            loadFragment(home)
        }
        btn_logo.setOnClickListener{
            loadFragment(home)
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
         * @return A new instance of fragment FragmentBusqueda.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentBusqueda().apply {
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

    private fun getLugares() {
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
                    listaLugares.add(lugar!!)
                    listaNombresLugares.add(nombre!!)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error al obtener los datos: ", exception)
            }
    }
}