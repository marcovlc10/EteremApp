package lopez.marco.pruebabottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lopez.marco.proyectoeterem.FragmentInicio
import lopez.marco.proyectoeterem.FragmentSocial

class MainActivity : AppCompatActivity() {

//    var btn_detalle_lugar: View =findViewById(R.id.btn_detalle_lugar)
//    var btn_detalle_regresar: View =findViewById(R.id.btn_detalle_regresar)
    var home = FragmentInicio()
    var promos = FragmentPromos()
    var social = FragmentSocial()
    var search = FragmentBusqueda()
    var login = FragmentInicioSesion()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//        findViewById<View>(R.id.layout_iniciar_sesion).visibility=View.GONE
//        val myFragmentView: View? = inflater.inflate(R.layout.activity_main, container, false)
//        myFragmentView!!.visibility = View.GONE
        loadFragment(login)

//        btn_detalle_lugar.setOnClickListener{
//            var intento= Intent(this, FragmentDetalleLugar::class.java)
//            this.startActivity(intento)
//        }
//
//        btn_detalle_regresar.setOnClickListener{
//            var intento= Intent(this, FragmentInicio::class.java)
//            this.startActivity(intento)
//        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(home)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_social -> {
                    loadFragment(social)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_promotions -> {
                    loadFragment(promos)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    loadFragment(search)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }
}