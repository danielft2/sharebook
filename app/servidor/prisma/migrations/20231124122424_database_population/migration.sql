INSERT INTO "Usuario"( id,nome, telefone, email, senha,cep,estado,cidade,foto_perfil)
VALUES ('05304a82-8a06-11ee-b9d1-0242ac120002', 'Joao', '88999475456', 'joaof@gmail.com', '123456', '63800000', 'Ceara', 'Quixeramobim', ''),
('1734ed59-eaa8-4c31-9b38-f546790668e8', 'Pedro', '88999475455', 'pedrolucas@gmail.com', '12345@', '63900000', 'Ceara', 'Quixada', ''),
('58d43f0c-5442-48b4-8103-4ec2bcd42ea6', 'Maria', '88988475455', 'mariajoaquina@gmail.com', '1234@@', '63900025', 'Ceara','Quixada', '');


INSERT INTO "Genero"( id, nome )
VALUES ('1c527b0a-6da8-4ccd-b23f-fcd946cf488a', 'Romance'),
('6e265cbe-3aaf-4268-862d-55f5c41f26c0', 'Diário Pessoal'),
('b7d791c1-2060-4aae-a788-bef977341a2f', 'Suspense');

INSERT INTO "Estado"( id, nome )
VALUES('05989174-b0fe-47a9-b8ee-394228fb66ca', 'Bom Estado'),
('448358ea-c333-4982-ac6e-627b75d2e6cc', 'Perfeito');

INSERT INTO "Livro"(id,isbn,nome,sinopse,autor,usuario_id,edicao,idioma,pode_buscar,quer_receber, capa, imagens, estado_id)
VALUES ('dfe8be88-3b0d-496e-baab-395b63751f43', '9780857533937','O Menino do Pijama Listrado', 'Durante a Segunda Guerra Mundial, Bruno, um garoto de oito anos,
		e sua família saem de Berlim para residir próximo a um campo de concentração, onde seu pai acaba de se tornar comandante. Infeliz e solitário, 
		ele vagueia fora de sua casa e certo dia encontra Shmuel, um menino judeu de sua idade. Embora a cerca de arame farpado do campo os separem, 
		os meninos começam uma amizade proibida.', '{"John Boyne"}', '05304a82-8a06-11ee-b9d1-0242ac120002', 1, 'Português', FALSE, TRUE, 'Capa Dura', '{""}','05989174-b0fe-47a9-b8ee-394228fb66ca'),
('c0741322-0592-46a2-8206-9fd950b97f00', '9780141032009','Diário de Anne Frank', 'Anne Frank era uma criança alemã de família judaica que, aos 13 anos, teve que se esconder com os pais,
		a irmã e outros judeus em um anexo secreto no prédio onde o pai trabalhava. Lá eles ficaram escondidos por dois anos inteiros, até agosto de 1944. Faltando um ano para a guerra acabar,
		os nazistas descobriram o esconderijo. Todos os que lá estavam foram presos e enviados para campos de concentração. Anne Frank foi enviada ao campo de Bergen-Belsen, onde morreu em fevereiro de 1945,
		com apenas 15 anos. Três meses depois, os alemães se renderam. Durante o tempo em que ficou escondida, Anne Frank escreveu um Diário contando o seu dia-a-dia no anexo secreto. O Diário de Anne Frank é um relato de adolescente.
		Porém de uma adolescente submetida a condições extremas, no período mais difícil da adolescência, tendo que não apenas se esconder, mas também que dividir um pequeno espaço com familiares e estranhos. Quando o escondreijo foi descoberto pelos nazistas,
		os moradores do anexo foram todos levados. Mas o Diário ficou para trás.', '{"Anne Frank"}','1734ed59-eaa8-4c31-9b38-f546790668e8', 2, 'Português', TRUE, TRUE, 'Capa Mole', '{""}', '05989174-b0fe-47a9-b8ee-394228fb66ca'),
('eb43f38c-887b-410e-ad5f-624861cf6c95', '9788574805986','Memórias Póstumas de Brás Cubas', 'Após ter morrido, em pleno ano de 1869, Brás Cubas decide por narrar sua história e revisitar os fatos mais importantes de sua vida, a fim de se distrair na eternidade.
		A partir de então ele relembra de amigos como Quincas Borba, de sua displicente formação acadêmica em Portugal, dos amores de sua vida e ainda do privilégio que teve de nunca ter precisado trabalhar em sua vida.', '{"Machado de Assis"}','58d43f0c-5442-48b4-8103-4ec2bcd42ea6', 3, 'Português', TRUE, TRUE, 'Capa Dura', '{""}', '448358ea-c333-4982-ac6e-627b75d2e6cc');

INSERT INTO "GeneroLivro"( livro_id, genero_id )
VALUES ('dfe8be88-3b0d-496e-baab-395b63751f43', 'b7d791c1-2060-4aae-a788-bef977341a2f'),
('c0741322-0592-46a2-8206-9fd950b97f00', '6e265cbe-3aaf-4268-862d-55f5c41f26c0'),
('eb43f38c-887b-410e-ad5f-624861cf6c95', '1c527b0a-6da8-4ccd-b23f-fcd946cf488a');

INSERT INTO "Solicitacao"( id, usuario_solicitante_id, livro_id, "status")
VALUES ('13139b6c-6b53-41e6-9c6e-91d3bc3120be', '05304a82-8a06-11ee-b9d1-0242ac120002', 'dfe8be88-3b0d-496e-baab-395b63751f43', 'ATIVO');

INSERT INTO "ProcessoDeTroca"( id, usuario_solicitante_id, livro_id, "status")
VALUES ('05306472-8a06-11ee-b9d1-0242ac120002', '05304a82-8a06-11ee-b9d1-0242ac120002', 'dfe8be88-3b0d-496e-baab-395b63751f43', 'ATIVO');

INSERT INTO "Alerta"( id, nome_livro, usuario_id, filtrado_por_envio, filtrado_por_localizacao)
VALUES('3e8bbafb-a739-4452-8192-2716a4d3f001', 'O Menino do Pijama Listrado', '05304a82-8a06-11ee-b9d1-0242ac120002', FALSE, FALSE);

INSERT INTO "Avaliacao"( id, usuario_avaliador_id, nota, comentario, usuario_avaliado_id)
VALUES('cf374f11-fcf9-4976-9e5c-31a69658f1d9', '05304a82-8a06-11ee-b9d1-0242ac120002', '5', 'Realmente está em perfeito estado!', '1734ed59-eaa8-4c31-9b38-f546790668e8');

