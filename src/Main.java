
import controller.TutorController;
import controller.PetController;
import controller.ServicoController;
import controller.AgendamentoController;

import model.Tutor;
import model.Pet;
import model.Agendamento;
import model.Servico;

import enums.Porte;
import enums.Comportamento;
import enums.TipoPelo;
import enums.StatusAgendamento;
import enums.TipoPagamento;

import exceptions.RegraNegocioException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Inicialização dos Controllers (Arquitetura de Camadas)
        TutorController tutorController = new TutorController();
        PetController petController = new PetController();
        ServicoController servicoController = new ServicoController();
        AgendamentoController agendamentoController = new AgendamentoController();

        int opcao = -1;

        while (opcao != 0) {

            try {
                System.out.println("\n=========== SISTEMA RoyAU ===========");
                System.out.println("1 - Cadastrar Tutor");
                System.out.println("2 - Cadastrar Pet");
                System.out.println("3 - Listar Serviços");
                System.out.println("4 - Agendar Serviço");
                System.out.println("5 - Finalizar Agendamento");
                System.out.println("6 - Listar Agendamentos");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");

                opcao = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println(" Erro: Por favor, digite apenas números para a opção do menu.");
                sc.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    // Cadastrar Tutor (Leitura completa dos 4 argumentos)
                    System.out.print("Nome do Tutor: ");
                    String nome = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();

                    Tutor t = new Tutor(nome, telefone, cpf, endereco);
                    try {
                        tutorController.cadastrarTutor(t);
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro de cadastro " + e.getMessage());
                    }
                }

                case 2 -> {
                    // Cadastrar Pet (Com listagem e tratamento de entrada)
                    try {
                        System.out.print("Nome do Pet: ");
                        String nome = sc.nextLine();

                        System.out.print("Raça: ");
                        String raca = sc.nextLine();

                        System.out.print("Idade (anos): ");
                        int idade = sc.nextInt();
                        sc.nextLine();

                        tutorController.listarIDsETutores(); // Listagem

                        System.out.print("Digite o ID do Tutor: ");
                        int idTutor = sc.nextInt();
                        sc.nextLine();

                        Tutor tutor = tutorController.buscarPorId(idTutor);
                        if (tutor == null) {
                            System.out.println("Tutor não encontrado.");
                            break;
                        }

                        // Leitura dos Enums (Com tratamento de Scanner)
                        System.out.println("Porte do Pet: 1-PEQUENO | 2-MEDIO | 3-GRANDE");
                        int p = sc.nextInt();
                        sc.nextLine();
                        Porte porte = (p == 1 ? Porte.PEQUENO : p == 2 ? Porte.MEDIO : Porte.GRANDE);

                        System.out.println("Comportamento: 1-CALMO | 2-AGITADO | 3-AGRESSIVO");
                        int c = sc.nextInt();
                        sc.nextLine();
                        Comportamento comportamento = (c == 1 ? Comportamento.CALMO : c == 2 ? Comportamento.AGITADO : Comportamento.AGRESSIVO);

                        System.out.println("Tipo de Pelo: 1-CURTO | 2-MEDIO | 3-LONGO");
                        int tp = sc.nextInt();
                        sc.nextLine();
                        TipoPelo tipoPelo = (tp == 1 ? TipoPelo.CURTO : tp == 2 ? TipoPelo.MEDIO : TipoPelo.LONGO);

                        Pet pet = new Pet(nome, raca, idade, porte, comportamento, tipoPelo, tutor);

                        petController.cadastrarPet(pet);
                        System.out.println("Pet cadastrado com sucesso!");

                    } catch (InputMismatchException e) {
                        System.out.println("Erro: Idade, ID ou Opções devem ser números inteiros.");
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro de informação: " + e.getMessage());
                    }
                }


                case 3 -> {
                    System.out.println("=== SERVIÇOS DISPONÍVEIS ===");
                    servicoController.listarServicos();
                }

                case 4 -> {
                    // Agendar Serviço Com leitura de Data/Hora e Agenda
                    try {
                        // Garante que o usuário saiba o ID do Pet
                        System.out.print("ID do Pet: ");
                        int idPet = sc.nextInt();
                        sc.nextLine();

                        Pet pet = petController.buscarPorId(idPet);
                        if (pet == null) {
                            System.out.println(" Pet não encontrado!");
                            break;
                        }

                        Tutor tutor = pet.getTutor();

                        servicoController.listarServicos(); // Lista serviços disponíveis

                        System.out.print("ID do Serviço: ");
                        int idServ = sc.nextInt();
                        sc.nextLine();

                        Servico servico = servicoController.buscarPorId(idServ);
                        if (servico == null) {
                            System.out.println("Serviço inválido!");
                            break;
                        }

                        // Leitura de Data e Hora
                        System.out.print("Digite a data do agendamento (AAAA-MM-DD): ");
                        String dataStr = sc.nextLine();
                        System.out.print("Digite o horário do agendamento (HH:MM): ");
                        String horaStr = sc.nextLine();


                        LocalDateTime horario = LocalDateTime.parse(dataStr + "T" + horaStr);

                        Agendamento novo = new Agendamento(pet, tutor, servico, horario);

                        agendamentoController.cadastrarAgendamento(novo);
                        System.out.println();

                    } catch (InputMismatchException e) {
                        System.out.println("Erro: IDs e opções devem ser números inteiros.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Erro no formato de Data/Hora. Use AAAA-MM-DD e HH:MM (ex: 2025-12-31T 17:30).");
                    } catch (RegraNegocioException e) {
                        System.out.println(" Erro de digitação: " + e.getMessage());
                    }
                }


                case 5 -> {
                    // Finalizar Agendamento
                    try {
                        System.out.print("ID do Agendamento a finalizar: ");
                        int idAg = sc.nextInt();
                        sc.nextLine();

                        // Leitura da Forma de Pagamento (Para a Ficha Profissional)
                        System.out.println("\n--- Selecione a Forma de Pagamento ---");
                        System.out.println("1 - PIX");
                        System.out.println("2 - CARTAO (informe para o clinte que aceitamos - Todas as Bandeiras)");
                        System.out.println("3 - DINHEIRO");
                        System.out.print("Opção: ");
                        int opPag = sc.nextInt();
                        sc.nextLine();


                        TipoPagamento pagamento = TipoPagamento.fromInt(opPag);


                        agendamentoController.finalizarAgendamento(idAg, pagamento);

                    } catch (InputMismatchException e) {
                        System.out.println(" Erro: ID e opções de pagamento devem ser números inteiros.");
                    } catch (RegraNegocioException e) {
                        System.out.println(" Erro de Negócio: " + e.getMessage());
                    }
                }

                case 6 -> agendamentoController.listarTodos();

                case 0 -> System.out.println("Saindo do sistema...");

                default -> System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}


// Atualização final
// Atualização final para envio