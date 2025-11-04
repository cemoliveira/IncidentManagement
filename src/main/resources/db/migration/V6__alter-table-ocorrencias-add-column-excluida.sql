ALTER TABLE ocorrencias
ADD COLUMN excluida TINYINT(1) NOT NULL DEFAULT 0;

UPDATE ocorrencias
SET excluida = 0;