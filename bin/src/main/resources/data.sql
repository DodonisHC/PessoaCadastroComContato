INSERT INTO PESSOA(nome, cpf, data_nascimento) VALUES('João dos Santos', '99999999999', '2005-05-05 18:00:00');
INSERT INTO PESSOA(nome, cpf, data_nascimento) VALUES('Maria Pereira dos Santos', '99999999999', '2005-05-05 18:00:00');

INSERT INTO USUARIO(nome, email, senha) VALUES('Padrão', 'padrao@email.com', '$2a$10$11Acr4YDrZyCD8JLrflCL.5jNTSS0wB8UDqz29s7uRbqQOJQE25wS');
INSERT INTO USUARIO(nome, email, senha) VALUES('Administrador', 'padrao@email.com', '$2a$10$11Acr4YDrZyCD8JLrflCL.5jNTSS0wB8UDqz29s7uRbqQOJQE25wS');

INSERT INTO CONTATO(nome, telefone, email, pessoa_id) VALUES('João dos Santos', '43996477562', 'joao@email.com', 1);
INSERT INTO CONTATO(nome, telefone, email, pessoa_id) VALUES('Maria Pereira dos Santos', '43996477561', 'maria@email.com', 1);
INSERT INTO CONTATO(nome, telefone, email, pessoa_id) VALUES('Mario Quintana', '43996477560', 'mario@email.com', 2);

INSERT INTO PERFIL(id, nome) VALUES(1, 'ROLE_PADRAO');
INSERT INTO PERFIL(id, nome) VALUES(2, 'ROLE_ADMINISTRADOR');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2, 2);
