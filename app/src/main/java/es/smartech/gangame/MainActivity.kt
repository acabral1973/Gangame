package es.smartech.gangame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import es.smartech.gangame.deals.DealsFragment
import es.smartech.gangame.owned.TopOwnedFragment
import es.smartech.gangame.rated.TopRatedFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val DEFAULT_ITEM  = R.id.action_deals

    val fragments : HashMap<Int, Fragment> = hashMapOf(
            Pair(R.id.action_deals, DealsFragment()),
            Pair(R.id.action_top_rated, TopRatedFragment()),
            Pair(R.id.action_most_owned, TopOwnedFragment())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos la vista para que cargue el fragmento por defecto (DealsFragment)
        initView()

        navigationView.selectedItemId = DEFAULT_ITEM
        navigationView.setOnNavigationItemSelectedListener { item ->

            // Recupero el fragmento correspondiente al item del BottomNavigation sobre el que se hizo click
            val fragment : Fragment? = fragments[item.itemId]

            // Si existe un fragmento correspondiente al item del BottomNavigation sobre el que se hizo click, reemplazo el fragmento por el seleccionado
            if (fragment != null) {
                replaceFragment(fragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
    }

    fun initView() {

        // Recupero el fragmento actual
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        // si no hay ningun fragmento actual (estoy cargando la vista por primera vez), creo el fragmento que esté seteado por defecto (DEFAULT_ITEM)
        if (currentFragment == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragments[DEFAULT_ITEM])
                    .commit()
        }
    }
}
