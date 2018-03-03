package caramelo.com.br.notepad.ui.notelist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import caramelo.com.br.notepad.R

import kotlinx.android.synthetic.main.activity_note_list.*
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import caramelo.com.br.notepad.model.Note
import caramelo.com.br.notepad.ui.notedetail.NoteDetailActivity
import caramelo.com.br.notepad.ui.util.SwipeToDeleteHandler
import kotlinx.android.synthetic.main.content_main.*


class NoteListActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_NOTE_DETAIL = 10
    }

    private val model by lazy {
        ViewModelProviders.of(this).get(NoteListViewModel::class.java)
    }
    private val adapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        bindView()

        model.isLoading.observe(this, loadingObserver)
        model.isEmptyView.observe(this, emptyViewObserver)
        model.isRecyclerView.observe(this, recyclerViewObserver)
        model.noteList?.observe(this, noteListObserver)
    }

    private fun bindView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.setOnNoteItemClickListener {
            startNoteDetail(it)
        }

        fab.setOnClickListener {
            startNoteDetail()
        }
    }

    /**
     * Swipe to delete lawsuit
     */

    private val swipeHandler by lazy {
        object : SwipeToDeleteHandler(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                model.deleteDelete(viewHolder.adapterPosition)
            }
        }
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

    private val recyclerViewObserver = Observer<Boolean> {
        if (it == true) {
            recyclerView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.GONE
        }
    }

    private val noteListObserver = Observer<List<Note>> { data ->
        adapter.data = data
    }

    private fun startNoteDetail(note: Note? = null) {
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_ID, note?.id)
        startActivityForResult(intent, REQUEST_NOTE_DETAIL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_NOTE_DETAIL) {
            model.loadNotes()
        }
    }
}
