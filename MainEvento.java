import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Participante {
    private String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Local {
    private String nome;
    private String endereco;

    public Local(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
}

class Evento {
    private String nome;
    private String data;
    private Local local;
    private int vagasDisponiveis;
    private List<Participante> participantes;

    public Evento(String nome, String data, Local local, int vagasDisponiveis) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.vagasDisponiveis = vagasDisponiveis;
        this.participantes = new ArrayList<>();
    }

    public void cadastrarParticipante(Participante participante) {
        if (vagasDisponiveis > 0) {
            participantes.add(participante);
            vagasDisponiveis--;
            System.out.println("Participante cadastrado!");
        } else {
            System.out.println("Sem vagas disponíveis!");
        }
    }

    public void removerParticipante(String nomeParticipante) {
        boolean participanteRemovido = participantes.removeIf(participante -> participante.getNome().equals(nomeParticipante));
        if (participanteRemovido) {
            vagasDisponiveis++;
            System.out.println("Participante removido!");
        } else {
            System.out.println("Participante não encontrado!");
        }
    }

    public void consultarVagas() {
        System.out.println("Vagas disponíveis: " + vagasDisponiveis);
    }


    public void gerarRelatorioParticipacao() {
        System.out.println("\n--- Relatório de Participação ---");
        System.out.println("Evento: " + nome);
        System.out.println("Data: " + data);
        System.out.println("Local: " + local.getNome() + ", " + local.getEndereco());
        System.out.println("Participantes:");
        for (Participante participante : participantes) {
            System.out.println("- " + participante.getNome());
        }
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public Local getLocal() {
        return local;
    }
}

public class MainEvento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        Local local = new Local("Centro de Convenções", "Rua Exemplo, 123");

        Evento evento = new Evento("Workshop Java", "15/09/2024", local, 10);

        int opcao;
        do {
            System.out.println("\n--- Sistema de Eventos ---");
            System.out.println("1. Cadastrar participante");
            System.out.println("2. Remover participante");
            System.out.println("3. Consultar vagas");
            System.out.println("4. Gerar relatório de participação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do participante: ");
                    String nomeParticipante = scanner.nextLine();
                    Participante participante = new Participante(nomeParticipante);
                    evento.cadastrarParticipante(participante);
                    break;
                case 2:
                    System.out.print("Digite o nome do participante a ser removido: ");
                    String nomeRemover = scanner.nextLine();
                    evento.removerParticipante(nomeRemover);
                    break;
                case 3:
                    evento.consultarVagas();
                    break;
                case 4:
                    evento.gerarRelatorioParticipacao();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
