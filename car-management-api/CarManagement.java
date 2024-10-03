import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Carro: Representa os carros da frota
class Carro {
    private String id;
    private String marca;
    private String modelo;
    private String placa;
    private String tipoMotor; // Elétrico, Híbrido, Combustão
    private int lugares;
    private String cor;

    // Construtor da classe Carro
    public Carro(String id, String marca, String modelo, String placa, String tipoMotor, int lugares, String cor) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.tipoMotor = tipoMotor;
        this.lugares = lugares;
        this.cor = cor;
    }

    // Getters e setters para acessar e modificar atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    // Exibe os detalhes do carro
    public void mostrarDetalhes() {
        System.out.println("ID: " + id);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Placa: " + placa);
        System.out.println("Tipo de Motor: " + tipoMotor);
        System.out.println("Lugares: " + lugares);
        System.out.println("Cor: " + cor);
    }
}

// Classe Reserva: Representa as reservas feitas para um carro
class Reserva {
    private Carro carro;
    private String nomeMotorista;
    private LocalDateTime dataHoraRetirada;
    private LocalDateTime dataHoraDevolucao;

    // Construtor da classe Reserva
    public Reserva(Carro carro, String nomeMotorista, LocalDateTime dataHoraRetirada, LocalDateTime dataHoraDevolucao) {
        this.carro = carro;
        this.nomeMotorista = nomeMotorista;
        this.dataHoraRetirada = dataHoraRetirada;
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public LocalDateTime getDataHoraRetirada() {
        return dataHoraRetirada;
    }

    public void setDataHoraRetirada(LocalDateTime dataHoraRetirada) {
        this.dataHoraRetirada = dataHoraRetirada;
    }

    public LocalDateTime getDataHoraDevolucao() {
        return dataHoraDevolucao;
    }

    public void setDataHoraDevolucao(LocalDateTime dataHoraDevolucao) {
        this.dataHoraDevolucao = dataHoraDevolucao;
    }

    // Verifica se há conflito de horário com outra reserva
    public boolean conflitaCom(Reserva outra) {
        return !dataHoraDevolucao.isBefore(outra.getDataHoraRetirada().minusHours(1)) &&
               !dataHoraRetirada.isAfter(outra.getDataHoraDevolucao().plusHours(1));
    }

    // Exibe os detalhes da reserva
    public void mostrarReserva() {
        System.out.println("Carro: " + carro.getModelo());
        System.out.println("Motorista: " + nomeMotorista);
        System.out.println("Retirada: " + dataHoraRetirada);
        System.out.println("Devolução: " + dataHoraDevolucao);
    }
}

// Classe CarpoolManager: Gerencia as operações relacionadas à frota e reservas
class CarpoolManager {
    private List<Carro> carros;  // Lista de carros
    private List<Reserva> reservas;  // Lista de reservas

    // Construtor da classe CarpoolManager
    public CarpoolManager() {
        this.carros = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Adiciona um carro à lista
    public void adicionarCarro(Carro carro) {
        carros.add(carro);
        System.out.println("Carro adicionado com sucesso.");
    }

    // Método getter para acessar a lista de carros
    public List<Carro> getCarros() {
        return carros;
    }

    // Realiza a reserva de um carro
    public void reservarCarro(Carro carro, String nomeMotorista, LocalDateTime retirada, LocalDateTime devolucao) {
        Reserva novaReserva = new Reserva(carro, nomeMotorista, retirada, devolucao);

        // Verifica se há conflitos com reservas existentes
        for (Reserva reserva : reservas) {
            if (reserva.getCarro().equals(carro) && reserva.conflitaCom(novaReserva)) {
                System.out.println("Conflito de horário! Reserva não pode ser feita.");
                return;
            }
        }

        reservas.add(novaReserva);
        System.out.println("Reserva concluída com sucesso.");
    }

    // Consulta o histórico de reservas de um carro específico
    public void consultarHistorico(String idCarro) {
        for (Reserva reserva : reservas) {
            if (reserva.getCarro().getId().equals(idCarro)) {
                reserva.mostrarReserva();
                System.out.println("-------------------------");
            }
        }
    }
}

// Classe principal que contém o método main
public class CarManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Para entrada de dados
        CarpoolManager manager = new CarpoolManager();

        // Solicita ao usuário quantos carros deseja adicionar
        System.out.print("Quantos carros você deseja adicionar? ");
        int quantidadeCarros = scanner.nextInt();
        scanner.nextLine();  // Consumir a nova linha após o número

        // Adiciona os carros de acordo com a entrada do usuário
        for (int i = 0; i < quantidadeCarros; i++) {
            System.out.println("Informe os dados do carro " + (i + 1) + ":");
            
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            
            System.out.print("Placa: ");
            String placa = scanner.nextLine();
            
            System.out.print("Tipo de Motor (Elétrico, Híbrido, Combustão): ");
            String tipoMotor = scanner.nextLine();
            
            System.out.print("Lugares: ");
            int lugares = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha após o número
            
            System.out.print("Cor: ");
            String cor = scanner.nextLine();

            // Cria um novo carro com os dados informados
            Carro carro = new Carro(id, marca, modelo, placa, tipoMotor, lugares, cor);
            manager.adicionarCarro(carro);  // Adiciona o carro à frota
        }

        // Pergunta se o usuário deseja fazer uma reserva
        System.out.println("Deseja fazer uma reserva? (sim/não)");
        String respostaReserva = scanner.nextLine();
        
        if (respostaReserva.equalsIgnoreCase("sim") && quantidadeCarros > 0) {
            // Seleciona um carro da lista de carros adicionados
            System.out.println("Escolha o ID do carro para reserva:");
            for (Carro carro : manager.getCarros()) {
                carro.mostrarDetalhes();
            }
            String idCarroReserva = scanner.nextLine();
            
            // Busca o carro escolhido
            Carro carroEscolhido = null;
            for (Carro carro : manager.getCarros()) {
                if (carro.getId().equals(idCarroReserva)) {
                    carroEscolhido = carro;
                    break;
                }
            }

            if (carroEscolhido != null) {
                // Solicita os detalhes da reserva
                System.out.print("Nome do motorista: ");
                String nomeMotorista = scanner.nextLine();
                
                System.out.print("Data e hora de retirada (dd/MM/yyyy HH:mm): ");
                String retiradaStr = scanner.nextLine();
                System.out.print("Data e hora de devolução (dd/MM/yyyy HH:mm): ");
                String devolucaoStr = scanner.nextLine();

                // Converte as datas de string para LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime retirada = LocalDateTime.parse(retiradaStr, formatter);
                LocalDateTime devolucao = LocalDateTime.parse(devolucaoStr, formatter);

                // Faz a reserva do carro
                manager.reservarCarro(carroEscolhido, nomeMotorista, retirada, devolucao);
            } else {
                System.out.println("Carro com ID informado não encontrado.");
            }
        }

        // Consulta o histórico de reservas para um carro
        System.out.println("Deseja consultar o histórico de reservas? (sim/não)");
        String respostaHistorico = scanner.nextLine();
        
        if (respostaHistorico.equalsIgnoreCase("sim") && quantidadeCarros > 0) {
            System.out.print("Informe o ID do carro para consultar o histórico: ");
            String idCarroHistorico = scanner.nextLine();
            manager.consultarHistorico(idCarroHistorico);
        }

        // Fecha o scanner para evitar vazamento de recurso
        scanner.close();
    }
}