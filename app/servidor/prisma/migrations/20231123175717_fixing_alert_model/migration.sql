/*
  Warnings:

  - You are about to drop the column `filtrado_por_cidade` on the `Alerta` table. All the data in the column will be lost.
  - You are about to drop the column `filtrado_por_estado` on the `Alerta` table. All the data in the column will be lost.

*/
-- AlterTable
ALTER TABLE "Alerta" DROP COLUMN "filtrado_por_cidade",
DROP COLUMN "filtrado_por_estado",
ADD COLUMN     "filtrado_por_envio" BOOLEAN NOT NULL DEFAULT false,
ADD COLUMN     "filtrado_por_localizacao" BOOLEAN NOT NULL DEFAULT false;
