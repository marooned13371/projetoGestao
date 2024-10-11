package gestorclientes;
import clientes.*;
import security.ValidadorCliente;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        ClienteDTO cliente = new ClienteDTO();
        ClienteDAO clienteDAO = new ClienteDAO();
        
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Busca de cliente por CPF");
            System.out.println("3. SAIR");
            System.out.println("-----------------------------");
            
            String escolhaSwitch = sc.nextLine();
            
            switch (escolhaSwitch) {
                case "1":
                    cliente = new ClienteDTO(); 
                    
                    System.out.print("Digite o email do cliente: ");
                    cliente.setEmailCliente(sc.nextLine());

                    System.out.print("Digite a senha do cliente: ");
                    cliente.setSenhaCliente(sc.nextLine());

                    System.out.print("Digite o nome do cliente: ");
                    cliente.setNomeCliente(sc.nextLine());

                    System.out.print("Digite o CPF do cliente: ");
                    cliente.setCpfCliente(sc.nextLine());
                    String cpfFormatado = ValidadorCliente.formatarCpf(cliente.getCpfCliente());
                    cliente.setCpfCliente(cpfFormatado);

                    System.out.println("Digite o tipo de endereco do cliente ");
                    System.out.print("Disponiveis: Residencial, Temporario, Casa, Apartamento, Escritorio e Rural ");
                    cliente.setTipoEnderecoCliente(sc.nextLine());

                    System.out.print("Digite o endereco do cliente: ");
                    cliente.setEnderecoCliente(sc.nextLine());

                    System.out.print("Digite o CEP do cliente: ");
                    cliente.setCepCliente(sc.nextLine());
                    String cepFormatado = ValidadorCliente.formatarCep(cliente.getCepCliente());
                    cliente.setCepCliente(cepFormatado);
                    
                    ValidadorCliente.verificarCadastroCliente verificador = new ValidadorCliente.verificarCadastroCliente();
                    
                    // Analisando o input do usuário
                    verificador.verificar(
                        cliente.getEmailCliente(),
                        cliente.getSenhaCliente(),
                        cliente.getNomeCliente(),
                        cliente.getCpfCliente(),
                        cliente.getTipoEnderecoCliente(),
                        cliente.getEnderecoCliente(),
                        cliente.getCepCliente()
                    );
                    
                    // Verificar CPF no banco de dados
                    if (clienteDAO.cpfJaCadastrado(cliente.getCpfCliente())) {
                        System.out.println("Erro: CPF ja cadastrado.");
                        continue; 
                    }
                    
                    if (!verificador.isValido()) {
                        System.out.println("Cadastro invalido. Erros:");
                        for (String erro: verificador.getErros()) {
                            System.out.println("- " + erro);
                        }
                        continue; 
                    }
                    
                    try {
                        clienteDAO.cadastrarCliente(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Houve um erro: " + e);
                    }
                    break;

                case "2":
                    System.out.print("Insira o CPF para a consulta (Somente os números): ");
                    cliente.setCpfCliente(sc.nextLine());
                    try {
                        clienteDAO.buscarClientePorCPF(cliente);
                    } catch (Exception e) {
                        System.out.println("Houve um erro: " + e);
                    }
                    break;

                case "3":
                    System.out.println("Saindo do programa!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}
