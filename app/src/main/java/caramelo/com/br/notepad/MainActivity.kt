package caramelo.com.br.notepad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.repository.NoteRepository

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val repository by lazy { NoteRepository() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

        }

        repository.list(
                onListNoteSuccess = { list: List<Note>? ->
                    list
                },
                onListNoteFail = {
                    it
                }
        )
    }
}
