import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Carro que representa um carro no sistema
class Carro {
    private String modelo;
    private boolean disponivel;

    public Carro(String modelo, boolean disponivel) {
        this.modelo = modelo;
        this.disponivel = disponivel;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void reservar() {
        this.disponivel = false; // Marca o carro como indisponível após reserva
    }
}

// Classe principal que contém a lógica do programa
public class GerenciamentoDeCarros {
    // Lista de carros disponíveis no sistema
    private List<Carro> carros;

    public GerenciamentoDeCarros() {
        // Inicializa a lista de carros
        carros = new ArrayList<>();
        // Adiciona alguns carros à lista (pode ser alterado conforme necessário)
        carros.add(new Carro("Fusca", true));
        carros.add(new Carro("Civic", true));
        carros.add(new Carro("Caminhonete", false)); // Este carro não está disponível
    }

    // Método para verificar e reservar carros
    public void verificarEReservarCarros() {
        Scanner scanner = new Scanner(System.in);

        // Solicita a data de retirada
        System.out.println("Digite a data de retirada (formato: yyyy-mm-dd): ");
        String dataRetirada = scanner.nextLine();

        // Verifica se a data de retirada é válida (opcional: adicionar validação de data)
        if (dataRetirada.compareTo("2024-10-01") < 0) {
            System.out.println("A data de retirada deve ser a mesma ou uma data futura.");
            return;
        }

        // Exibe os carros disponíveis
        System.out.println("Carros disponíveis:");
        for (Carro carro : carros) {
            if (carro.isDisponivel()) {
                System.out.println("- " + carro.getModelo());
            }
        }

        // Solicita ao usuário escolher um carro
        System.out.println("Escolha um carro para reservar:");
        String carroEscolhido = scanner.nextLine();

        // Procura o carro escolhido
        Carro carroReservado = null;
        for (Carro carro : carros) {
            if (carro.getModelo().equalsIgnoreCase(carroEscolhido) && carro.isDisponivel()) {
                carroReservado = carro;
                break;
            }
        }

        // Verifica se o carro escolhido está disponível
        if (carroReservado != null) {
            // Solicita as informações do motorista
            System.out.println("Preencha as informações do motorista:");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Contato: ");
            String contato = scanner.nextLine();
            System.out.print("Número da licença: ");
            String numeroLicenca = scanner.nextLine();

            // Reserva o carro
            carroReservado.reservar();
            System.out.println("Reserva concluída com sucesso para " + nome + "!");
        } else {
            System.out.println("Carro não disponível ou não encontrado.");
        }

        scanner.close(); // Fecha o scanner
    }

    public static void main(String[] args) {
        GerenciamentoDeCarros sistema = new GerenciamentoDeCarros();
        sistema.verificarEReservarCarros(); // Executa o método de verificação e reserva
    }
}
