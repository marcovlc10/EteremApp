package lopez.marco.pruebabottomnav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lopez.marco.proyectoeterem.FragmentInicio
import lopez.marco.proyectoeterem.FragmentSocial

class MainActivityNav : AppCompatActivity() {

    var home = FragmentInicio()
    var promos = FragmentPromos()
    var social = FragmentSocial()
    var search = FragmentBusqueda()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(home)

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