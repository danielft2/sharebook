package com.example.sharebook.exchangerequest_feature.data.remote.response

import com.google.gson.annotations.SerializedName

data class SendRequestResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("livro_id")
    val livroId: String,

    @SerializedName("livro_oferecido_id")
    val livroOferecidoId: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("usuario_solicitante_id")
    val usuarioSolicitanteId: String
)