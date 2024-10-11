package clientes;

// Classe padrão de getters e setters

public class ClienteDTO {
    private String email_cliente;
    private String senha_cliente;
    private String nome_cliente;
    private String cpf_cliente;
    private String tipo_endereco_cliente;
    private String endereco_cliente;
    private String cep_cliente;

    public String getEmailCliente(){
        return email_cliente;
    }

    public void setEmailCliente(String email_cliente){
        this.email_cliente = email_cliente;
    }

    public String getSenhaCliente(){
        return senha_cliente;
    }

    public void setSenhaCliente(String senha_cliente){
        this.senha_cliente = senha_cliente;
    }

    public String getNomeCliente(){
        return nome_cliente;
    }

    public void setNomeCliente(String nome_cliente){
        this.nome_cliente = nome_cliente;
    }

    public String getCpfCliente(){
        return cpf_cliente;
    }

    public void setCpfCliente(String cpf_cliente){
        this.cpf_cliente = cpf_cliente; 
    }

    public String getTipoEnderecoCliente(){
        return tipo_endereco_cliente;
    }

    public void setTipoEnderecoCliente(String tipo_endereco_cliente){
        this.tipo_endereco_cliente = tipo_endereco_cliente;
    }

    public String getEnderecoCliente(){
        return endereco_cliente;
    }

    public void setEnderecoCliente(String endereco_cliente){
        this.endereco_cliente = endereco_cliente;
    }

    public String getCepCliente(){
        return cep_cliente;
    }

    public void setCepCliente(String cep_cliente){
        this.cep_cliente = cep_cliente;
    }
        
}
