package caramelo.com.br.notepad

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by lucascaramelo on 02/03/18.
 */
fun EditText.onTextChange(l: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable) {
            l(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}