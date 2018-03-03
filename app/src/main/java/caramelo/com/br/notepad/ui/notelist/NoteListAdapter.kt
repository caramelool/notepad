package caramelo.com.br.notepad.ui.notelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import caramelo.com.br.notepad.R
import caramelo.com.br.notepad.model.Note

/**
 * Created by lucascaramelo on 02/03/18.
 */
class NoteListAdapter: RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    var data: List<Note>? = mutableListOf()
        set(value) {
            field = value ?: mutableListOf()
            notifyDataSetChanged()
        }

    private var onNoteItemClickListener: (Note) -> Unit = {}

    fun setOnNoteItemClickListener(l: (Note) -> Unit) {
        onNoteItemClickListener = l
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.adapter_note_list, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = data!!.size

    override fun onBindViewHolder(holder: NoteViewHolder?, position: Int) {
        holder?.let {
            val note = data!![position]
            it.bind(note)
            it.itemView.setOnClickListener {
                onNoteItemClickListener(note)
            }
        }
    }

    inner class NoteViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        fun bind(note: Note) {
            itemView.findViewById<TextView>(R.id.noteTitleTextView).text = note.title
            itemView.findViewById<TextView>(R.id.noteContentTextView).text = note.content
        }
    }
}