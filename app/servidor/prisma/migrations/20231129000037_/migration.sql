/*
  Warnings:

  - A unique constraint covering the columns `[usuario_solicitante_id,livro_id]` on the table `Solicitacao` will be added. If there are existing duplicate values, this will fail.

*/
-- CreateIndex
CREATE UNIQUE INDEX "Solicitacao_usuario_solicitante_id_livro_id_key" ON "Solicitacao"("usuario_solicitante_id", "livro_id");
