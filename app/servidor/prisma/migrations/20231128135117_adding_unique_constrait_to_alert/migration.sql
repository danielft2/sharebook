/*
  Warnings:

  - A unique constraint covering the columns `[usuario_id,nome_livro]` on the table `Alerta` will be added. If there are existing duplicate values, this will fail.

*/
-- CreateIndex
CREATE UNIQUE INDEX "Alerta_usuario_id_nome_livro_key" ON "Alerta"("usuario_id", "nome_livro");
