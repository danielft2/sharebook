/*
  Warnings:

  - The primary key for the `GeneroLivro` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `livro_isbn` on the `GeneroLivro` table. All the data in the column will be lost.
  - The primary key for the `Livro` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The `autor` column on the `Livro` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - You are about to drop the column `livro_isbn` on the `ProcessoDeTroca` table. All the data in the column will be lost.
  - You are about to drop the column `livro_isbn` on the `Solicitacao` table. All the data in the column will be lost.
  - Added the required column `livro_id` to the `GeneroLivro` table without a default value. This is not possible if the table is not empty.
  - The required column `id` was added to the `Livro` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.
  - Added the required column `livro_id` to the `ProcessoDeTroca` table without a default value. This is not possible if the table is not empty.
  - Added the required column `livro_id` to the `Solicitacao` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "GeneroLivro" DROP CONSTRAINT "GeneroLivro_livro_isbn_fkey";

-- DropForeignKey
ALTER TABLE "ProcessoDeTroca" DROP CONSTRAINT "ProcessoDeTroca_livro_isbn_fkey";

-- DropForeignKey
ALTER TABLE "Solicitacao" DROP CONSTRAINT "Solicitacao_livro_isbn_fkey";

-- AlterTable
ALTER TABLE "GeneroLivro" DROP CONSTRAINT "GeneroLivro_pkey",
DROP COLUMN "livro_isbn",
ADD COLUMN     "livro_id" TEXT NOT NULL,
ADD CONSTRAINT "GeneroLivro_pkey" PRIMARY KEY ("livro_id", "genero_id");

-- AlterTable
ALTER TABLE "Livro" DROP CONSTRAINT "Livro_pkey",
ADD COLUMN     "id" TEXT NOT NULL,
DROP COLUMN "autor",
ADD COLUMN     "autor" TEXT[],
ADD CONSTRAINT "Livro_pkey" PRIMARY KEY ("id");

-- AlterTable
ALTER TABLE "ProcessoDeTroca" DROP COLUMN "livro_isbn",
ADD COLUMN     "livro_id" TEXT NOT NULL;

-- AlterTable
ALTER TABLE "Solicitacao" DROP COLUMN "livro_isbn",
ADD COLUMN     "livro_id" TEXT NOT NULL;

-- CreateTable
CREATE TABLE "GeneroUsuario" (
    "usuario_id" TEXT NOT NULL,
    "genero_id" TEXT NOT NULL,

    CONSTRAINT "GeneroUsuario_pkey" PRIMARY KEY ("usuario_id","genero_id")
);

-- AddForeignKey
ALTER TABLE "GeneroLivro" ADD CONSTRAINT "GeneroLivro_livro_id_fkey" FOREIGN KEY ("livro_id") REFERENCES "Livro"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "GeneroUsuario" ADD CONSTRAINT "GeneroUsuario_usuario_id_fkey" FOREIGN KEY ("usuario_id") REFERENCES "Usuario"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "GeneroUsuario" ADD CONSTRAINT "GeneroUsuario_genero_id_fkey" FOREIGN KEY ("genero_id") REFERENCES "Genero"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Solicitacao" ADD CONSTRAINT "Solicitacao_livro_id_fkey" FOREIGN KEY ("livro_id") REFERENCES "Livro"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ProcessoDeTroca" ADD CONSTRAINT "ProcessoDeTroca_livro_id_fkey" FOREIGN KEY ("livro_id") REFERENCES "Livro"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE "Livro" ADD COLUMN     "latitude" TEXT NOT NULL,
ADD COLUMN     "longitude" TEXT NOT NULL;