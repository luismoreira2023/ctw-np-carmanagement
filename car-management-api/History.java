import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe para representar uma Reserva de Carro
class Reserva {
    private String motorista;
    private String dataRetirada;
    private String dataDevolucao;

    // Construtor da classe Reserva
    public Reserva(String motorista, String dataRetirada, String dataDevolucao) {
        this.motorista = motorista;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
    }

    // Método para obter a data de retirada
    public String getDataRetirada() {
        return dataRetirada;
    }

    // Método para obter a data de devolução
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    // Método para exibir informações da reserva
    public String detalhesReserva() {
        return "Motorista: " + motorista + ", Retirada: " + dataRetirada + ", Devolução: " + dataDevolucao;
    }
}

// Classe para gerenciar o histórico de reservas
class GerenciadorDeReservas {
    private List<Reserva> reservas;

    // Construtor da classe GerenciadorDeReservas
    public GerenciadorDeReservas() {
        reservas = new ArrayList<>();
    }

    // Método para adicionar uma reserva
    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // Método para consultar reservas com base nas datas de retirada e devolução
    public List<Reserva> consultarReservas(String dataRetirada, String dataDevolucao) {
        List<Reserva> reservasEncontradas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            // Verifica se a reserva está dentro do intervalo de tempo solicitado
            if (reserva.getDataRetirada().equals(dataRetirada) && reserva.getDataDevolucao().equals(dataDevolucao)) {
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;
    }
}

// Classe principal para executar o programa
public class History {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeReservas gerenciador = new GerenciadorDeReservas();

        // Adicionando algumas reservas de exemplo
        gerenciador.adicionarReserva(new Reserva("João", "2024-10-01 10:00", "2024-10-01 12:00"));
        gerenciador.adicionarReserva(new Reserva("Maria", "2024-10-02 09:00", "2024-10-02 11:00"));
        
        // Solicitando dados de entrada do usuário
        System.out.println("Digite a data/hora de retirada (ex: 2024-10-01 10:00): ");
        String dataRetirada = scanner.nextLine();
        
        System.out.println("Digite a data/hora de devolução (ex: 2024-10-01 12:00): ");
        String dataDevolucao = scanner.nextLine();

        // Consultando reservas
        List<Reserva> reservasEncontradas = gerenciador.consultarReservas(dataRetirada, dataDevolucao);

        // Exibindo resultados
        if (reservasEncontradas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para o intervalo de tempo especificado.");
        } else {
            System.out.println("Reservas encontradas:");
            for (Reserva reserva : reservasEncontradas) {
                System.out.println(reserva.detalhesReserva());
            }
        }

        // Fechando o scanner
        scanner.close();
    }
}
