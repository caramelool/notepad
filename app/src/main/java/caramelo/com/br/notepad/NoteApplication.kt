package caramelo.com.br.notepad

import android.app.Application
import caramelo.com.br.notepad.di.DaggerNoteComponent
import caramelo.com.br.notepad.di.NoteComponent
import caramelo.com.br.notepad.di.module.RepositoryModule
import caramelo.com.br.notepad.di.module.RetrofitModule

/**
 * Created by lucascaramelo on 02/03/18.
 */
class NoteApplication: Application() {

    companion object {
        lateinit var noteComponent: NoteComponent
    }

    override fun onCreate() {
        super.onCreate()
        noteComponent = DaggerNoteComponent
                .builder()
                .retrofitModule(RetrofitModule())
                .repositoryModule(RepositoryModule())
                .build()
    }

}