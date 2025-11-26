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

import exceptions.RegraNegocioException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TutorController tutorController = new TutorController();
        PetController petController = new PetController();
        ServicoController servicoController = new ServicoController();
        AgendamentoController agendamentoController = new AgendamentoController();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n=========== SISTEMA ROYAU ===========");
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

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Tutor: ");
                    String nome = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    Tutor t = new Tutor(nome, telefone);
                    try {
                        tutorController.cadastrarTutor(t);
                        System.out.println("Tutor cadastrado!");
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("Nome do Pet: ");
                    String nome = sc.nextLine();

                    System.out.print("Raça: ");
                    String raca = sc.nextLine();

                    System.out.print("Idade (anos): ");
                    int idade = sc.nextInt();

                    System.out.print("ID do Tutor: ");
                    int idTutor = sc.nextInt();
                    sc.nextLine();

                    Tutor tutor = tutorController.buscarPorId(idTutor);
                    if (tutor == null) {
                        System.out.println("Tutor não encontrado.");
                        break;
                    }

                    System.out.println("Porte do Pet: 1-PEQUENO | 2-MEDIO | 3-GRANDE");
                    int p = sc.nextInt();
                    Porte porte = (p == 1 ? Porte.PEQUENO : p == 2 ? Porte.MEDIO : Porte.GRANDE);

                    System.out.println("Comportamento: 1-CALMO | 2-AGITADO | 3-AGRESSIVO");
                    int c = sc.nextInt();
                    Comportamento comportamento = (c == 1 ? Comportamento.CALMO : c == 2 ? Comportamento.AGITADO : Comportamento.AGRESSIVO);

                    System.out.println("Tipo de Pelo: 1-CURTO | 2-MEDIO | 3-LONGO");
                    int tp = sc.nextInt();
                    TipoPelo tipoPelo = (tp == 1 ? TipoPelo.CURTO : tp == 2 ? TipoPelo.MEDIO : TipoPelo.LONGO);

                    Pet pet = new Pet(nome, raca, idade, porte, comportamento, tipoPelo, tutor);

                    try {
                        petController.cadastrarPet(pet);
                        System.out.println("Pet cadastrado!");
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }


                case 3 -> {
                    System.out.println("=== SERVIÇOS DISPONÍVEIS ===");
                    servicoController.listarServicos();
                }

                case 4 -> {
                    try {
                        System.out.print("ID do Pet: ");
                        int idPet = sc.nextInt();

                        Pet pet = petController.buscarPorId(idPet);
                        if (pet == null) {
                            System.out.println("Pet não encontrado!");
                            break;
                        }

                        Tutor tutor = pet.getTutor();

                        System.out.print("ID do Serviço: ");
                        int idServ = sc.nextInt();

                        Servico servico = servicoController.buscarPorId(idServ);
                        if (servico == null) {
                            System.out.println("Serviço inválido!");
                            break;
                        }

                        LocalDateTime horario = LocalDateTime.now().plusMinutes(1);

                        Agendamento novo = new Agendamento(pet, tutor, servico, horario);

                        agendamentoController.cadastrarAgendamento(novo);
                        System.out.println("Agendamento criado!");

                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                }


                case 5 -> {
                    System.out.print("ID do Agendamento: ");
                    int idAg = sc.nextInt();

                    try {
                        agendamentoController.finalizarAgendamento(idAg);
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
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
