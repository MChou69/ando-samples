package mc.com.pedago

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import mc.com.pedago.async.BackgroundTask
import mc.com.pedago.device.InfosSettingsActivity
import mc.com.pedago.tools.Tools
import mc.com.pedago.ws_rss.RssReadActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    internal val REQUEST_GET_SINGLE_FILE = 1 //final

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

       fab.setOnClickListener { view : View ->
           Snackbar.make(view, "my action..", Snackbar.LENGTH_LONG)
               .setAction("Login")
               { Tools.openActivity(applicationContext, LoginActivity::class.java)}
               .show()
       }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_rss -> Tools.openActivity(applicationContext, RssReadActivity::class.java)
            R.id.nav_gallery -> {
                Toast.makeText(this,"galery..",Toast.LENGTH_SHORT).show()
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GET_SINGLE_FILE)
            }
            R.id.nav_slideshow -> Toast.makeText(this,"slide..",Toast.LENGTH_SHORT).show()
            R.id.nav_manage -> Tools.openActivity(applicationContext, InfosSettingsActivity::class.java)
            R.id.nav_share -> Toast.makeText(this,"share..",Toast.LENGTH_SHORT).show()
            R.id.nav_send -> Toast.makeText(this,"send..",Toast.LENGTH_SHORT).show()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
