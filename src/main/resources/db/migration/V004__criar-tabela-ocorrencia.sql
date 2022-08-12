CREATE TABLE ocorrencia (
	id BIGINT NOT NULL auto_increment,
	entrega_id BIGINT NOT NULL,
	descricao TEXT NOT NULL,
	data_registro DATETIME NOT NULL,

	PRIMARY KEY(id)

);

ALTER TABLE ocorrencia ADD CONSTRAINT fk_ocorrencia_entrega FOREIGN KEY (entrega_id) REFERENCES entrega (id);