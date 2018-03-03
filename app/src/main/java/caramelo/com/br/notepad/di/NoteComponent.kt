package caramelo.com.br.notepad.di

import caramelo.com.br.notepad.di.module.RepositoryModule
import caramelo.com.br.notepad.di.module.RetrofitModule
import caramelo.com.br.notepad.ui.notedetail.NoteDetailViewModel
import caramelo.com.br.notepad.ui.notelist.NoteListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lucascaramelo on 02/03/18.
 */
@Singleton
@Component(modules = [
    RetrofitModule::class,
    RepositoryModule::class
])
interface NoteComponent {
    fun inject(viewModel: NoteListViewModel)
    fun inject(viewModel: NoteDetailViewModel)
}