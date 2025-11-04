ALTER TABLE turmas
ADD COLUMN cancelada TINYINT(1) NOT NULL DEFAULT 0;

UPDATE turmas
SET cancelada = 0;