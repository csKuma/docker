-- insert aplicacacao --
INSERT INTO ur01_aplicacao (ur01_cod_aplicacao, ur01_nome) VALUES('eca17995-6b18-424b-9a94-ca4ca5b9c32b'::uuid, 'RH');
INSERT INTO ur01_aplicacao (ur01_cod_aplicacao, ur01_nome) VALUES('281757c9-789f-4185-a62b-e536c5ae5ad2'::uuid, 'BANCARIO');
INSERT INTO ur01_aplicacao (ur01_cod_aplicacao, ur01_nome) VALUES('c7320060-4990-48b1-9903-0c55d241aa0e'::uuid, 'VENDA');

-- insert modulos ----
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('4c427bb1-5d1f-4424-a0fc-9d5f1e0b99a4'::uuid, NULL, 'eca17995-6b18-424b-9a94-ca4ca5b9c32b'::uuid, 'RH', 'RH', NULL, NULL);
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('31214fee-e859-49b4-95de-d2e25bfdd74a'::uuid, NULL, '281757c9-789f-4185-a62b-e536c5ae5ad2'::uuid, 'BANCARIO', 'BANCARIO', NULL, NULL);
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('2c585e0c-7774-4509-9ee1-095a545ae38a'::uuid, '31214fee-e859-49b4-95de-d2e25bfdd74a'::uuid, '281757c9-789f-4185-a62b-e536c5ae5ad2'::uuid, 'CRIACAO DE CONTA', 'CONTA', NULL, NULL);
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('f1464203-5c17-44d3-bf6a-9872678c49f5'::uuid, '31214fee-e859-49b4-95de-d2e25bfdd74a'::uuid, '281757c9-789f-4185-a62b-e536c5ae5ad2'::uuid, 'TRANSFERENCIA', 'TRANSFERENCIA', NULL, NULL);
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('695539db-e4f8-4970-b54d-da22622b75a1'::uuid, '31214fee-e859-49b4-95de-d2e25bfdd74a'::uuid, '281757c9-789f-4185-a62b-e536c5ae5ad2'::uuid, 'EXTRATO', 'EXTRATO', NULL, NULL);
INSERT INTO ur02_modulo (ur02_cod_modulo, fkur02ur02_modulo_pai, fkur02ur01_cod_aplicacao, ur02_descricao, ur02_nome, ur02_icone, ur02_path) VALUES('75daa6f1-2b37-4968-ac0d-ee86e9616ce5'::uuid, '4c427bb1-5d1f-4424-a0fc-9d5f1e0b99a4'::uuid, 'eca17995-6b18-424b-9a94-ca4ca5b9c32b'::uuid, 'RH FILHO', 'RHFILHO', NULL, NULL);

---- insert perfils---

INSERT INTO ur03_perfil (ur03_cod_perfil, ur03_nome, ur03_descricao, ur03_ativo) VALUES('a78b6a26-3737-40d6-a341-fad5243e7469'::uuid, 'ADM', 'ADMINISTRADOR', true);
INSERT INTO ur03_perfil (ur03_cod_perfil, ur03_nome, ur03_descricao, ur03_ativo) VALUES('5c3aefc7-6c81-497f-aba3-f36d4d7351a4'::uuid, 'FUNCIONARIO', 'FUNCIONARIO', true);
INSERT INTO ur03_perfil (ur03_cod_perfil, ur03_nome, ur03_descricao, ur03_ativo) VALUES('bff7f9ef-ed6a-4681-9fcb-60b5dd55aa5a'::uuid, 'ATENDENTE', 'ATENDENTE', true);
INSERT INTO ur03_perfil (ur03_cod_perfil, ur03_nome, ur03_descricao, ur03_ativo) VALUES('4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, 'DEV', 'DESENVOLVEDOR', true);


--- insert modulo perfil ---

INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('9182636d-2a9b-4228-9083-9f0719c7023e'::uuid, '4c427bb1-5d1f-4424-a0fc-9d5f1e0b99a4'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);
INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('e20e79f6-e894-4217-a46b-1a43e7b5d175'::uuid, '31214fee-e859-49b4-95de-d2e25bfdd74a'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);
INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('0481f7ec-59e8-495f-bab4-30bcfe6961b2'::uuid, '2c585e0c-7774-4509-9ee1-095a545ae38a'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);
INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('e804b0af-a248-4766-a7a3-f63d822d990a'::uuid, '695539db-e4f8-4970-b54d-da22622b75a1'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);
INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('ad6a19f3-159a-46ad-8bcf-13a7a160ea41'::uuid, 'f1464203-5c17-44d3-bf6a-9872678c49f5'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);
INSERT INTO ur08_modulo_perfil (ur08_cod_modulo_perfil, fkur08ur02_cod_modulo, fkur08ur03_cod_perfil, ur08_data_cadastro, ur08_data_alteracao, ur08_data_exclusao, ur08_ativo) VALUES('0e23d3bb-7b4f-41a6-97de-8d5708152cdd'::uuid, '75daa6f1-2b37-4968-ac0d-ee86e9616ce5'::uuid, '4f97d718-a53c-4c55-991a-b4c51d9af3b3'::uuid, '2023-08-12', NULL, NULL, true);

---- insert permissao ---

INSERT INTO ur06_permissao (ur06_cod_permissao, ur06_descricao) VALUES('514aea64-185d-4a5f-9651-bffcd87ef4e7'::uuid, 'CRIAR');
INSERT INTO ur06_permissao (ur06_cod_permissao, ur06_descricao) VALUES('ed039ee8-3037-408b-aa1d-7a7c660cc006'::uuid, 'EDITAR');
INSERT INTO ur06_permissao (ur06_cod_permissao, ur06_descricao) VALUES('d3476403-493f-4600-a0a0-96c2ea6c6b13'::uuid, 'LISTAR');
INSERT INTO ur06_permissao (ur06_cod_permissao, ur06_descricao) VALUES('adba1ccf-4e7a-44e8-b370-4a2474aacb91'::uuid, 'EXCLUIR');

