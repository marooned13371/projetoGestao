package security;

import java.util.ArrayList;
import java.util.List;

/* Está classe pode ser melhorada e escalada, porém por ora só existem métodos para verificar o tamanho
e também formatar o CPF e CEP. Podendo ser implementados expressões regulares para verificar se o email é valido,
uma API externa para verificar se o endereço é válido, algoritmo para validação de CPF e etc.
*/

public class ValidadorCliente {
    // O usuário precisa digitar o tipo de endereço exatamente como está na array abaixo, caso não digitar corretamente, haverá um erro
    private static final String[] TIPOS_ENDERECO_PERMITIDOS = {
        "residencial",
        "comercial",
        "temporario",
        "temporário",
        "casa",
        "apartamento",
        "escritorio",
        "escritório",
        "rural"
    };
      
    public static boolean tipoEnderecoValido(String tipo_endereco_cliente){
        if(tipo_endereco_cliente == null || tipo_endereco_cliente.isEmpty()){
            return false;
        }
        
        // Trata o input do usuário, deixando os caracteres minusculos, diminuindo a chance de erro 
        String enderecoNormalizado = tipo_endereco_cliente.trim().toLowerCase();
        
        for (String tipoEndereco : TIPOS_ENDERECO_PERMITIDOS){
            if (tipoEndereco.equals(enderecoNormalizado)){
                return true;
            }
        }
       return false;
    }
    
    public static boolean emailValido(String email_cliente){
        if (email_cliente == null || email_cliente.isEmpty()){
            return false;
        }
        return email_cliente.length() >= 5 && email_cliente.length() <= 64;
    }
    
    /* Verifica se a senha tem mais de 5 caracteres e menos de 35,
    ele também verifica se há pelo menos 1 digito, 1 letra maiuscula, 
    1 letra minuscula e um caractere especial, se não houver, a senha é inválida 
    e o método retornará false.
    */
    public static boolean senhaValida(String senha_cliente){
        if (senha_cliente == null || senha_cliente.isEmpty()){
           return false;
        }
        
        if (senha_cliente.length() < 5 || senha_cliente.length() > 35) {
           return false;
        }

        boolean temLetraMaiuscula = false;
        boolean temLetraMinuscula = false;
        boolean temNumero = false;
        boolean temCaractereEspecial = false;

        for (char c : senha_cliente.toCharArray()) {
            if (Character.isUpperCase(c)) {
                temLetraMaiuscula = true;
            } else if (Character.isLowerCase(c)) {
                temLetraMinuscula = true;
            } else if (Character.isDigit(c)) {
                temNumero = true;
            } else if (!Character.isLetterOrDigit(c)) {
                temCaractereEspecial = true;
            }
        }
        // Define os critérios de força da senha, retorna true se todas as condições forem feitas.
        return temLetraMaiuscula && temLetraMinuscula && temNumero && temCaractereEspecial;
       }

    public static boolean nomeValido(String nome_cliente){
    if (nome_cliente == null || nome_cliente.isEmpty()){
        return false;
    }
    return nome_cliente.length()> 2 && nome_cliente.length() <= 100;
    }
   
    public static String formatarCpf(String cpf_cliente){
        if (cpf_cliente == null || cpf_cliente.isEmpty()){
            return null;
        }
        return cpf_cliente.replaceAll("[^0-9]", "");
    }
    
    // Não verifica se ele é realmente válido, somente se tem 11 digitos ou não
    public static boolean cpfValido(String cpf_cliente){
        return cpf_cliente != null && cpf_cliente.length() == 11;
    }
    
    // Verifica apenas o tamanho do endereço
    public static boolean enderecoValido(String endereco_cliente){
        if (endereco_cliente == null || endereco_cliente.isEmpty()){
            return false;
        }
        return endereco_cliente.length() >= 2 && endereco_cliente.length() <= 255;
    }
    
    public static String formatarCep(String cep_cliente){
        if (cep_cliente == null || cep_cliente.isEmpty()){
            return null;
        }
        return cep_cliente.replaceAll("[^0-9]", "");
    }
    
    // Não verifica se ele é realmente válido, somente se tem 8 digitos ou não
    public static boolean cepValido(String cep_cliente){
       return cep_cliente != null && cep_cliente.length() == 8;
    }
    
    // Criando uma subclasse para testar todos os métodos acima.
    public static class verificarCadastroCliente {
        private boolean valido = true;
        // Criar uma lista para armazenar os erros, se houver, exibir na tela.
        private List<String> erros = new ArrayList<>();
        
        public void adicionarErro(String erro){
            this.valido = false;
            this.erros.add(erro);
        }
        
        public boolean isValido(){
            return valido;
        }
        
        public List<String> getErros(){
            return erros;
        }
        
        //O método testa e registra quais são as credenciais inválidas
        public void verificar(String nome_cliente, String senha_cliente, String email_cliente, String cpf_cliente, String tipo_endereco_cliente, String endereco_cliente, String cep_cliente) {
            
            if (!nomeValido(nome_cliente)) {
                adicionarErro("Nome invalido.");
            }
            if (!senhaValida(senha_cliente)){
                adicionarErro("Senha invalida");
            }
            if (!emailValido(email_cliente)) {
                adicionarErro("Email invalido.");
            }
            if (!cpfValido(cpf_cliente)) {
                adicionarErro("CPF invalido.");
            }
            if (!tipoEnderecoValido(tipo_endereco_cliente)) {
                adicionarErro("Tipo de endereco invalido.");
            }
            if (!enderecoValido(endereco_cliente)){
                adicionarErro("Endereco invalido");
            }
            if (!cepValido(cep_cliente)) {
                adicionarErro("CEP invalido.");
            }
        }
    }   
}
