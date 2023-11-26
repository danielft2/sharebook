-- This is an empty migration.
INSERT INTO "Livro"(
    id,
    isbn,
    nome,
    sinopse,
    autor,
    usuario_id,
    edicao,
    idioma,
    pode_buscar,
    quer_receber, 
    capa, 
    imagens, 
    estado_id
    ) VALUES (
    '1f65ae09-61e4-49eb-ba3e-f7aa5a72eddc',
    '9780385121675',
    'O iluminado',
    '"O lugar perfeito para recomeçar", é o que pensa Jack Torrance ao ser contratado como zelador para o inverno. Hora de deixar para trás o alcoolismo, os acessos de fúria, os repetidos fracassos. Isolado pela neve com a esposa e o filho, tudo o que Jack deseja é um pouco de paz para se dedicar à escrita.',
    '{"Stephen King"}',
    '58d43f0c-5442-48b4-8103-4ec2bcd42ea6',
    1,
    'Inglês',
    TRUE,
    TRUE,
    'O Iluminado',
    '{"b468a625-1b22-42fe-a15d-ac8ed82a4808"}',
    '448358ea-c333-4982-ac6e-627b75d2e6cc'), 
    (
    '9e8716ef-a32c-4dac-8158-2a6e654a96cf',
    '9780075536321',
    'Ana carienina',
    'Anna Karenina, casada um alto funcionário do governo russo, envolve-se com o Conde Vronsky, oficial da cavalaria, chocando a alta sociedade de 1874. Ela pede o divórcio, mas o marido, além de se recusar a concedê-lo, ainda a impede de ver o filho.',
    '{"Liev Tolstoi"}',
    '58d43f0c-5442-48b4-8103-4ec2bcd42ea6',
    1,
    'Inglês',
    TRUE,
    TRUE,
    'Ana carienina',
    '{""}',
    '448358ea-c333-4982-ac6e-627b75d2e6cc'
    );

INSERT INTO "GeneroLivro"( livro_id, genero_id )
VALUES ('9e8716ef-a32c-4dac-8158-2a6e654a96cf', 'b7d791c1-2060-4aae-a788-bef977341a2f'),
('9e8716ef-a32c-4dac-8158-2a6e654a96cf', '6e265cbe-3aaf-4268-862d-55f5c41f26c0'),
('1f65ae09-61e4-49eb-ba3e-f7aa5a72eddc', '1c527b0a-6da8-4ccd-b23f-fcd946cf488a');

INSERT INTO "Solicitacao"( id, usuario_solicitante_id, livro_id, livro_oferecido_id, "status") VALUES 
('13139b6c-6b53-41e6-9c6e-91d3bc3120be', '1734ed59-eaa8-4c31-9b38-f546790668e8', 'dfe8be88-3b0d-496e-baab-395b63751f43', 'c0741322-0592-46a2-8206-9fd950b97f00', 'ATIVO'),
('e6239585-b8e1-4110-827f-5bebeff66ce4', '58d43f0c-5442-48b4-8103-4ec2bcd42ea6', 'dfe8be88-3b0d-496e-baab-395b63751f43', 'eb43f38c-887b-410e-ad5f-624861cf6c95', 'ATIVO');

-- INSERT INTO "ProcessoDeTroca"( id, usuario_solicitante_id, livro_id, "status")
-- VALUES ('05306472-8a06-11ee-b9d1-0242ac120002', '05304a82-8a06-11ee-b9d1-0242ac120002', 'dfe8be88-3b0d-496e-baab-395b63751f43', 'ATIVO');
