insert into PAUTAS( id, titulo, descricao, data_criacao, data_abertura, data_fechamento, duracao, aberta_votacao) values(1, 'titulo teste', 'descricao teste', '2024-08-25 17:12:48', '2024-08-25 17:15:48', '2024-08-25 17:20:48', 5, 0);

insert into ASSOCIADOS(id, nome, cpf, email, ativo)values(1, 'andre luis', '01033856061', 'andreluis@email.com', 1);
insert into ASSOCIADOS(id, nome, cpf, email, ativo)values(2, 'João', '01033856062', 'joao@email.com', 1);
insert into ASSOCIADOS(id, nome, cpf, email, ativo)values(3, 'Jaime', '01033856065', 'jaime@email.com', 1);


insert into VOTOS(id, valor_voto, data_voto, id_pauta, id_associado)values(1, 'SIM', '2024-08-25 17:16:48', 1, 1);
insert into VOTOS(id, valor_voto, data_voto, id_pauta, id_associado)values(2, 'SIM', '2024-08-25 17:16:50', 1, 2);
insert into VOTOS(id, valor_voto, data_voto, id_pauta, id_associado)values(3, 'NAO', '2024-08-25 17:16:45', 1, 3);