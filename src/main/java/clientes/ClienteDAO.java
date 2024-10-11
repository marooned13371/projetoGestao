package clientes;

import connection.DatabaseConnection;
import security.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrarCliente(ClienteDTO cliente){
      String sqlQuery = "INSERT INTO tb_cliente (nm_email_cliente, nm_senha_cliente, nm_nome_cliente, id_cpf, nm_tipo_endereco_cliente, nm_endereco, id_cep) VALUES (?, ?, ?, ?, ?, ? ,?)";  
      Connection conn = null;
      PreparedStatement prst = null;
            try {
                conn = DatabaseConnection.getConnection();
                prst = conn.prepareStatement(sqlQuery);
                // Hasheando a senha com sha-256 com a classe "HashUtil", para um armazenamento mais seguro no bd, não implementei salting.
                String hashedSenha = HashUtil.hashPassword(cliente.getSenhaCliente());
              
                prst.setString(1, cliente.getEmailCliente());
                prst.setString(2, hashedSenha);
                prst.setString(3, cliente.getNomeCliente());
                prst.setString(4, cliente.getCpfCliente());
                prst.setString(5, cliente.getTipoEnderecoCliente());
                prst.setString(6, cliente.getEnderecoCliente());
                prst.setString(7, cliente.getCepCliente());
                prst.executeUpdate();
            } 
            catch (SQLException e) {
                System.out.println("Houve um erro no banco de dados: " + e); 
            }
            finally {
                try {
                    // Fechando conexões após fazer a query no bd.
                    if (conn != null) conn.close();
                    if (prst != null) prst.close();
                } catch (Exception e) {
                    System.out.println("Houve um erro : " + e);
                }
            }

       } 
    
    public void buscarClientePorCPF(ClienteDTO cliente){
        String sqlQuery = "SELECT nm_email_cliente, nm_nome_cliente, id_cpf, nm_tipo_endereco_cliente, nm_endereco, id_cep FROM tb_cliente WHERE id_cpf = ? ";
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            prst = conn.prepareStatement(sqlQuery);
            
            prst.setString(1, cliente.getCpfCliente());
            rs = prst.executeQuery();
                
                // Se o result set for bem sucedido, ele ira capturar as tabelas do bd com os nomes especificados na query abaixo.
                if(rs.next()){
                    String email_cliente = rs.getString("nm_email_cliente");
                    String nome_cliente = rs.getString("nm_nome_cliente");
                    String cpf_cliente = rs.getString("id_cpf");
                    String tipo_endereco_cliente = rs.getString("nm_tipo_endereco_cliente");
                    String endereco_cliente = rs.getString("nm_endereco");
                    String cep_cliente = rs.getString("id_cep");
                    
                    System.out.println("Cliente encontrado !");
                    System.out.println("Email: " + email_cliente);
                    System.out.println("Nome: " + nome_cliente);
                    System.out.println("CPF: " + cpf_cliente);
                    System.out.println("Endereco: " + endereco_cliente);
                    System.out.println("Tipo de endereco: " + tipo_endereco_cliente);
                    System.out.println("CEP: " + cep_cliente);
                }
                else {
                  System.out.println("Cliente nao encontrado !");
                }    
            } 
            catch (SQLException e) {
                System.out.println("Houve um erro no banco de dados: " + e); 
            }
            finally{
               try {
                    // Fechando conexões após fazer a query no bd.
                    if (conn != null) conn.close();
                    if (prst != null) prst.close();
                    if (rs != null) rs.close();
               } 
               catch (Exception e) {
                   System.out.println("Houve um erro : " + e);
                }
            } 
        
        }
    
        public boolean cpfJaCadastrado(String cpf_cliente) {
        String sqlQuery = "SELECT COUNT(*) FROM tb_cliente WHERE id_cpf = ?";
        Connection conn = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
            try {
                conn = DatabaseConnection.getConnection();
                prst = conn.prepareStatement(sqlQuery);
                prst.setString(1, cpf_cliente);
                
                rs = prst.executeQuery();
                // Se não houver resultado, retornará um false
                if (rs.next()){
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Houve um erro no banco de dados: " + e);
            }
            finally{
                try {
                    // Fechando conexões após fazer a query no bd.
                    if (conn != null) conn.close();
                    if (prst != null) prst.close();
                    if (rs != null) rs.close();
                } catch (Exception e) {
                    System.out.println("Houve um erro : " + e); 
                }
            }
            return false;
        }
 }

