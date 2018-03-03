package caramelo.com.br.notepad.model

import com.google.gson.annotations.SerializedName

/**
 * Created by lucascaramelo on 01/03/18.
 */
data class Note(@SerializedName("id") val id: String? = null,
                @SerializedName("title") var title: String? = null,
                @SerializedName("content") var content: String? = null)