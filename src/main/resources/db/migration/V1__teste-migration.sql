
-- public.ur01_aplicacao definition

-- Drop table

-- DROP TABLE ur01_aplicacao;

CREATE TABLE ur01_aplicacao
(
    ur01_cod_aplicacao uuid         NOT NULL DEFAULT gen_random_uuid(),
    ur01_nome          varchar(100) NOT NULL,
    CONSTRAINT tb01_cod_aplicacao PRIMARY KEY (ur01_cod_aplicacao)
);


-- public.ur03_perfil definition

-- Drop table

-- DROP TABLE ur03_perfil;

CREATE TABLE ur03_perfil
(
    ur03_cod_perfil uuid         NOT NULL DEFAULT gen_random_uuid(),
    ur03_nome       varchar(100) NOT NULL,
    ur03_descricao  varchar(500) NULL,
    ur03_ativo      bool         NOT NULL,
    CONSTRAINT tb03_perfil_pk PRIMARY KEY (ur03_cod_perfil)
);


-- public.ur05_usuario definition

-- Drop table

-- DROP TABLE ur05_usuario;

CREATE TABLE ur05_usuario
(
    ur05_cod_usuario                uuid         NOT NULL DEFAULT gen_random_uuid(),
    fkur05ur05_cod_usuario_cadastro uuid NULL,
    ur05_id                         varchar(100) NOT NULL,
    ur05_nome                       varchar(100) NOT NULL,
    ur05_senha                      varchar(100) NULL,
    ur05_data_cadastro              date         NOT NULL,
    ur05_ultimo_acesso              date NULL,
    ur05_bloqueado                  bool NULL,
    ur05_resetar_senha              bool NULL,
    ur05_data_bloqueio              date NULL,
    ur05_cpf                        varchar(11)  NOT NULL,
    ur05_email                      varchar(100) NOT NULL,
    ur05_telefone                   varchar(16) NULL,
    CONSTRAINT tb05_usuario_ak_1 UNIQUE (ur05_id, ur05_cpf),
    CONSTRAINT tb05_usuario_pk PRIMARY KEY (ur05_cod_usuario)
);


-- public.ur06_permissao definition

-- Drop table

-- DROP TABLE ur06_permissao;

CREATE TABLE ur06_permissao
(
    ur06_cod_permissao uuid         NOT NULL DEFAULT gen_random_uuid(),
    ur06_descricao     varchar(100) NOT NULL,
    CONSTRAINT tb73_permissao_pk PRIMARY KEY (ur06_cod_permissao)
);


-- public.ur02_modulo definition

-- Drop table

-- DROP TABLE ur02_modulo;

CREATE TABLE ur02_modulo
(
    ur02_cod_modulo          uuid         NOT NULL DEFAULT gen_random_uuid(),
    fkur02ur02_modulo_pai    uuid NULL,
    fkur02ur01_cod_aplicacao uuid         NOT NULL,
    ur02_descricao           varchar(100) NULL,
    ur02_nome                varchar(500) NOT NULL,
    ur02_icone               varchar(100) NULL,
    ur02_path                varchar(100) NULL,
    CONSTRAINT tb02_modulo_pk PRIMARY KEY (ur02_cod_modulo),
    CONSTRAINT tb02_modulo_tb01_aplicacao FOREIGN KEY (fkur02ur01_cod_aplicacao) REFERENCES ur01_aplicacao (ur01_cod_aplicacao),
    CONSTRAINT tb02_modulo_tb02_modulo FOREIGN KEY (fkur02ur02_modulo_pai) REFERENCES ur02_modulo (ur02_cod_modulo)
);


-- public.ur04_usuario_perfil definition

-- Drop table

-- DROP TABLE ur04_usuario_perfil;

CREATE TABLE ur04_usuario_perfil
(
    ur04_cod_usuario_perfil uuid NOT NULL DEFAULT gen_random_uuid(),
    fkur04ur05_cod_usuario  uuid NOT NULL,
    fkur04ur03_cod_perfil   uuid NOT NULL,
    ur04_data_habilitacao   date NULL,
    ur04_data_desabilitacao date NULL,
    ur04_ativo              bool NULL,
    CONSTRAINT tb04_usuario_perfil_pk PRIMARY KEY (ur04_cod_usuario_perfil),
    CONSTRAINT fktb04tb05_cod_usuario FOREIGN KEY (fkur04ur05_cod_usuario) REFERENCES ur05_usuario (ur05_cod_usuario),
    CONSTRAINT tb04_usuario_perfil_tb03_perfil FOREIGN KEY (fkur04ur03_cod_perfil) REFERENCES ur03_perfil (ur03_cod_perfil)
);


-- public.ur08_modulo_perfil definition

-- Drop table

-- DROP TABLE ur08_modulo_perfil;

CREATE TABLE ur08_modulo_perfil
(
    ur08_cod_modulo_perfil uuid NOT NULL DEFAULT gen_random_uuid(),
    fkur08ur02_cod_modulo  uuid NOT NULL,
    fkur08ur03_cod_perfil  uuid NOT NULL,
    ur08_data_cadastro     date NULL,
    ur08_data_alteracao    date NULL,
    ur08_data_exclusao     date NULL,
    ur08_ativo             bool NULL,
    CONSTRAINT tb49_modulo_perfil_pk PRIMARY KEY (ur08_cod_modulo_perfil),
    CONSTRAINT tb49_modulo_perfil_tb02_modulo FOREIGN KEY (fkur08ur02_cod_modulo) REFERENCES ur02_modulo (ur02_cod_modulo),
    CONSTRAINT tb49_modulo_perfil_tb03_perfil FOREIGN KEY (fkur08ur03_cod_perfil) REFERENCES ur03_perfil (ur03_cod_perfil)
);


-- public.ur10_empresa_modulo definition

-- Drop table

-- DROP TABLE ur10_empresa_modulo;

CREATE TABLE ur10_empresa_modulo
(
    ur10_cod_empresa_modulo uuid NOT NULL DEFAULT gen_random_uuid(),
    fkur10ur02_cod_modulo   uuid NOT NULL,
    ur10_data_cadastro      date NULL,
    ur10_data_alteracao     date NULL,
    ur10_ativo              bool NULL,
    ur10_cod_empresa        uuid NULL,
    CONSTRAINT ur10_empresa_modulo_pk PRIMARY KEY (ur10_cod_empresa_modulo),
    CONSTRAINT fkur10ur02_cod_empresa FOREIGN KEY (fkur10ur02_cod_modulo) REFERENCES ur02_modulo (ur02_cod_modulo)
);


-- public.ur07_permissao_modulo definition

-- Drop table

-- DROP TABLE ur07_permissao_modulo;

CREATE TABLE ur07_permissao_modulo
(
    ur07_cod_permissao_modulo    uuid NOT NULL DEFAULT gen_random_uuid(),
    fkur07ur06_cod_permissao     uuid NOT NULL,
    fkur07ur03_cod_modulo_perfil uuid NOT NULL,
    ur07_ativo                   bool NOT NULL,
    ur07_data_cadastro           date NOT NULL,
    ur07_data_alteracao          date NOT NULL,
    CONSTRAINT tb74_permissao_modulo_pk PRIMARY KEY (ur07_cod_permissao_modulo),
    CONSTRAINT tb74_permissao_modulo_tb49_modulo_perfil FOREIGN KEY (fkur07ur03_cod_modulo_perfil) REFERENCES ur08_modulo_perfil (ur08_cod_modulo_perfil),
    CONSTRAINT tb74_permissao_modulo_tb73_permissao FOREIGN KEY (fkur07ur06_cod_permissao) REFERENCES ur06_permissao (ur06_cod_permissao)
);