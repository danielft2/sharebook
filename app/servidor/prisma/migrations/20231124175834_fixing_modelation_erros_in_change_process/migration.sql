/*
  Warnings:

  - Added the required column `livro_oferecido_id` to the `ProcessoDeTroca` table without a default value. This is not possible if the table is not empty.
  - Added the required column `livro_oferecido_id` to the `Solicitacao` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "ProcessoDeTroca" ADD COLUMN     "livro_oferecido_id" TEXT NOT NULL;

-- AlterTable
ALTER TABLE "Solicitacao" ADD COLUMN     "livro_oferecido_id" TEXT NOT NULL;
