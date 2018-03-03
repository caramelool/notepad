package caramelo.com.br.notepad.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import caramelo.com.br.notepad.ui.notelist.NoteListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startMain()
            finish()
        }, 2000)
    }

    private fun startMain() {
        val intent = Intent(this, NoteListActivity::class.java)
        startActivity(intent)
    }
}
