package caramelo.com.br.notepad.di.module

import caramelo.com.br.notepad.repository.NoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by lucascaramelo on 02/03/18.
 */
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNoteRepository(retrofit: Retrofit): NoteRepository {
        return NoteRepository(retrofit)
    }
}