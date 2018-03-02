package caramelo.com.br.notepad.ui.notelist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import caramelo.com.br.notepad.R

import kotlinx.android.synthetic.main.activity_note_list.*
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.ui.notedetail.NoteDetailActivity
import kotlinx.android.synthetic.main.content_main.*


class NoteListActivity : AppCompatActivity() {

    private val model by lazy {
        ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }
    private val adapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        fab.setOnClickListener(startNoteDetail)

        model.loading.observe(this, loadingObserver)
        model.emptyView.observe(this, emptyViewObserver)
        model.noteList?.observe(this, noteListObserver)
    }

    private val loadingObserver = Observer<Boolean> {
        if (it == true) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    private val emptyViewObserver = Observer<Boolean> {
        if (it == true) {
            emptyView.visibility = View.VISIBLE
        } else {
            emptyView.visibility = View.GONE
        }
    }

    private val noteListObserver = Observer<List<Note>> {
        it?.let { data ->
            adapter.data = data
        }
    }

    private val startNoteDetail: (View) -> Unit = {
        val intent = Intent(this, NoteDetailActivity::class.java)
        startActivity(intent)
    }
}
