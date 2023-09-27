-- CreateTable
CREATE TABLE "Usuario" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "telefone" TEXT NOT NULL,
    "senha" TEXT NOT NULL,
    "cep" TEXT NOT NULL,
    "estado" TEXT NOT NULL,
    "cidade" TEXT NOT NULL,
    "foto_perfil" TEXT,

    CONSTRAINT "Usuario_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Livro" (
    "isbn" TEXT NOT NULL,
    "nome" TEXT NOT NULL,
    "sinopse" TEXT,
    "autor" TEXT NOT NULL,
    "usuario_id" TEXT NOT NULL,
    "edicao" INTEGER NOT NULL DEFAULT 1,
    "idioma" TEXT NOT NULL,
    "pode_buscar" BOOLEAN NOT NULL DEFAULT false,
    "quer_receber" BOOLEAN NOT NULL DEFAULT true,
    "capa" TEXT NOT NULL,
    "imagens" TEXT[],
    "estado_id" TEXT NOT NULL,

    CONSTRAINT "Livro_pkey" PRIMARY KEY ("isbn")
);

-- CreateTable
CREATE TABLE "GeneroLivro" (
    "livro_isbn" TEXT NOT NULL,
    "genero_id" TEXT NOT NULL,

    CONSTRAINT "GeneroLivro_pkey" PRIMARY KEY ("livro_isbn","genero_id")
);

-- CreateTable
CREATE TABLE "Genero" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,

    CONSTRAINT "Genero_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Estado" (
    "id" TEXT NOT NULL,
    "nome" TEXT NOT NULL,

    CONSTRAINT "Estado_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Solicitacao" (
    "id" TEXT NOT NULL,
    "usuario_solicitante_id" TEXT NOT NULL,
    "livro_isbn" TEXT NOT NULL,
    "status" TEXT NOT NULL,

    CONSTRAINT "Solicitacao_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "ProcessoDeTroca" (
    "id" TEXT NOT NULL,
    "usuario_solicitante_id" TEXT NOT NULL,
    "livro_isbn" TEXT NOT NULL,
    "status" TEXT NOT NULL,

    CONSTRAINT "ProcessoDeTroca_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Alerta" (
    "id" TEXT NOT NULL,
    "nome_livro" TEXT NOT NULL,
    "usuario_id" TEXT NOT NULL,
    "filtrado_por_estado" BOOLEAN NOT NULL DEFAULT false,
    "filtrado_por_cidade" BOOLEAN NOT NULL DEFAULT false,

    CONSTRAINT "Alerta_pkey" PRIMARY KEY ("id")
);

-- CreateTable
CREATE TABLE "Avaliacao" (
    "id" TEXT NOT NULL,
    "usuario_avaliador_id" TEXT NOT NULL,
    "nota" DOUBLE PRECISION NOT NULL,
    "comentario" TEXT NOT NULL,
    "usuario_avaliado_id" TEXT NOT NULL,

    CONSTRAINT "Avaliacao_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "Usuario_email_key" ON "Usuario"("email");

-- CreateIndex
CREATE UNIQUE INDEX "Usuario_telefone_key" ON "Usuario"("telefone");

-- AddForeignKey
ALTER TABLE "Livro" ADD CONSTRAINT "Livro_usuario_id_fkey" FOREIGN KEY ("usuario_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Livro" ADD CONSTRAINT "Livro_estado_id_fkey" FOREIGN KEY ("estado_id") REFERENCES "Estado"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "GeneroLivro" ADD CONSTRAINT "GeneroLivro_livro_isbn_fkey" FOREIGN KEY ("livro_isbn") REFERENCES "Livro"("isbn") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "GeneroLivro" ADD CONSTRAINT "GeneroLivro_genero_id_fkey" FOREIGN KEY ("genero_id") REFERENCES "Genero"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Solicitacao" ADD CONSTRAINT "Solicitacao_usuario_solicitante_id_fkey" FOREIGN KEY ("usuario_solicitante_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Solicitacao" ADD CONSTRAINT "Solicitacao_livro_isbn_fkey" FOREIGN KEY ("livro_isbn") REFERENCES "Livro"("isbn") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProcessoDeTroca" ADD CONSTRAINT "ProcessoDeTroca_usuario_solicitante_id_fkey" FOREIGN KEY ("usuario_solicitante_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProcessoDeTroca" ADD CONSTRAINT "ProcessoDeTroca_livro_isbn_fkey" FOREIGN KEY ("livro_isbn") REFERENCES "Livro"("isbn") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Alerta" ADD CONSTRAINT "Alerta_usuario_id_fkey" FOREIGN KEY ("usuario_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Avaliacao" ADD CONSTRAINT "Avaliacao_usuario_avaliado_id_fkey" FOREIGN KEY ("usuario_avaliado_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
