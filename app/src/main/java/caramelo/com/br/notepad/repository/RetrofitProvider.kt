package caramelo.com.br.notepad.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * Created by lucascaramelo on 01/03/18.
 */
object RetrofitProvider {
    private val retrofit by lazy {
        val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)

        Retrofit.Builder()
                .baseUrl("https://fiap14mobcaramelo.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
    }

    fun <T : Any> create(c: KClass<T>): T {
        return retrofit.create(c.java)
    }
}