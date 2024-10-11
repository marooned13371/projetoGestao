DROP DATABASE IF EXISTS db_cadastro_clientes;
CREATE DATABASE IF NOT EXISTS db_cadastro_clientes;
USE db_cadastro_clientes;

CREATE TABLE tb_cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nm_email_cliente VARCHAR(64) NOT NULL,
    nm_senha_cliente VARCHAR(70) NOT NULL,
    nm_nome_cliente VARCHAR(100) NOT NULL,
    id_cpf VARCHAR(11) NOT NULL UNIQUE,
    nm_tipo_endereco_cliente VARCHAR(50) NOT NULL,
    nm_endereco VARCHAR(255) NOT NULL,
    id_cep VARCHAR(9) NOT NULL
);
