package caramelo.com.br.notepad

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startMain()
        }, 2000)
    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
