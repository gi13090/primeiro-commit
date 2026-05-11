import dao.BeneficiarioDAO;
import dao.DentistaDAO;
import dao.ConsultaDAO;
import entities.Beneficiario;
import entities.Consulta;
import entities.Dentista;
import enums.Prioridade;
import enums.StatusAgendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
        DentistaDAO dentistaDAO = new DentistaDAO();
        ConsultaDAO consultaDAO = new ConsultaDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome de cadastro: ");
        String name = sc.nextLine();

        while (true) {

            System.out.println("Bem vindo, " + name + "! Ver menu? (Sim/Não)");
            String escolha = sc.nextLine();

            switch (escolha.toLowerCase()) {

                case "sim", "claro" -> {

                    System.out.println("\n---MENU---");
                    System.out.println("1. Cadastrar paciente");
                    System.out.println("2. Agendar consulta");
                    System.out.println("3. Listar pacientes");
                    System.out.println("4. Atualizar paciente");
                    System.out.println("5. Deletar paciente");
                    System.out.println("6. Sair");

                    int op = sc.nextInt();
                    sc.nextLine();

                    switch (op) {
                        case 1 -> {
                            System.out.println("ID:");
                            Long id = sc.nextLong();
                            sc.nextLine();

                            System.out.println("Nome:");
                            String nome = sc.nextLine();

                            System.out.println("CPF:");
                            String cpf = sc.nextLine();

                            System.out.println("Telefone:");
                            String telefone = sc.nextLine();

                            System.out.println("Email:");
                            String email = sc.nextLine();

                            System.out.println("Endereço:");
                            String endereco = sc.nextLine();

                            Beneficiario b = new Beneficiario(id, nome, cpf, telefone, email, endereco);
                            beneficiarioDAO.inserir(b);

                            System.out.println("Paciente cadastrado!");
                        }
                        case 2 -> {
                            System.out.println("CPF do paciente:");
                            String cpf = sc.nextLine();

                            List<Beneficiario> lista = beneficiarioDAO.listar();
                            Beneficiario encontrado = null;

                            for (Beneficiario b : lista) {
                                if (b.getCpf().equals(cpf)) {
                                    encontrado = b;
                                    break;
                                }
                            }

                            if (encontrado == null) {
                                System.out.println("Paciente não encontrado!");
                                break;
                            }

                            System.out.println("Nome do dentista:");
                            String nomeDentista = sc.nextLine();

                            System.out.println("CRO:");
                            String cro = sc.nextLine();

                            System.out.println("ID do dentista:");
                            Long idDentista = sc.nextLong();
                            sc.nextLine();

                            Dentista d = new Dentista(idDentista, nomeDentista, null, null, null, cro);
                            dentistaDAO.inserir(d);

                            System.out.println("Data (dd/MM/yyyy):");
                            String dataStr = sc.nextLine();

                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDate data = LocalDate.parse(dataStr, formato);

                            LocalDateTime dataHora = data.atStartOfDay();

                            System.out.println("ID da consulta:");
                            Long idConsulta = sc.nextLong();
                            sc.nextLine();

                            Consulta c = new Consulta(encontrado, d, dataHora, Prioridade.MEDIA, StatusAgendamento.PENDENTE );
                            c.setId(idConsulta);

                            consultaDAO.inserir(c);

                            System.out.println("Consulta agendada!");
                        }
                        case 3 -> {
                            List<Beneficiario> lista = beneficiarioDAO.listar();

                            for (Beneficiario b : lista) {
                                System.out.println(b);
                            }
                        }
                        case 4 -> {
                            System.out.println("ID:");
                            Long id = sc.nextLong();
                            sc.nextLine();

                            System.out.println("Novo nome:");
                            String nome = sc.nextLine();

                            System.out.println("CPF:");
                            String cpf = sc.nextLine();

                            System.out.println("Telefone:");
                            String telefone = sc.nextLine();

                            System.out.println("Email:");
                            String email = sc.nextLine();

                            System.out.println("Endereço:");
                            String endereco = sc.nextLine();

                            Beneficiario b = new Beneficiario(id, nome, cpf, telefone, email, endereco);
                            beneficiarioDAO.atualizar(b);

                            System.out.println("Atualizado!");
                        }
                        case 5 -> {
                            System.out.println("ID:");
                            Long id = sc.nextLong();
                            sc.nextLine();

                            beneficiarioDAO.deletar(id);
                            System.out.println("Deletado!");
                        }
                        case 6 -> {
                            System.out.println("Saindo...");
                            return;
                        }
                    }
                }
                case "nao", "não", "n" -> {
                    System.out.println("Até mais!");
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}