package lopez.marco.pruebabottomnav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import lopez.marco.proyectoeterem.FragmentInicio
import lopez.marco.proyectoeterem.FragmentSocial
import lopez.marco.pruebabottomnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var home = FragmentInicio()
    var promos = FragmentPromos()
    var social = FragmentSocial()
    var search = FragmentBusqueda()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        Handler().postDelayed({
            val intent = Intent(this, ActivityInicioSesion::class.java)
            startActivity(intent)
            finish()
        }, 500)

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