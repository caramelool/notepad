package caramelo.com.br.notepad.ui.notedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import caramelo.com.br.notepad.R
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.onTextChange

import kotlinx.android.synthetic.main.activity_note_detail.*

class NoteDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE_ID = "extra_note_id"
    }

    private val model by lazy {
        ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
        setSupportActionBar(toolbar)

        bindView()

        model.intent = intent
        model.isLoading.observe(this, loadingObserver)
        model.note?.observe(this, noteObserver)
    }

    private fun bindView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        noteTitleEditText.onTextChange {
            model.setTitle(it)
        }

        noteContentEditText.onTextChange {
            model.setContent(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        model.save()
    }

    private val loadingObserver = Observer<Boolean> {
        it?.let {
            noteContentEditText.isEnabled = !it
            noteContentEditText.isEnabled = !it
        }
    }

    private val noteObserver = Observer<Note> {
        it?.let { data ->
            noteTitleEditText.setText(data.title)
            noteContentEditText.setText(data.content)
        }
    }
}
