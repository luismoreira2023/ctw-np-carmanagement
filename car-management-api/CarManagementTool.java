import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe que representa um Carro na frota
class Car {
    private String model; // O modelo do carro
    private boolean isAvailable; // Disponibilidade do carro

    public Car(String model) {
        this.model = model;
        this.isAvailable = true; // Todos os carros estão inicialmente disponíveis
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return model + " - " + (isAvailable ? "Disponível" : "Reservado");
    }
}

// Classe principal que gere a frota de carros e as reservas
public class CarManagementTool {
    private List<Car> carPool; // Lista de carros disponíveis na frota

    public CarManagementTool() {
        carPool = new ArrayList<>(); // Inicializa a lista de carros
        // Adicionando alguns carros para testes
        carPool.add(new Car("BMW Série 3"));
        carPool.add(new Car("Audi A4"));
        carPool.add(new Car("Tesla Model 3"));
    }

    // Exibe a lista de todos os carros e sua disponibilidade
    public void showCarPool() {
        System.out.println("Lista completa de carros:");
        for (Car car : carPool) {
            System.out.println(car);
        }
    }

    // Filtra carros disponíveis para um intervalo de tempo fictício (simulado)
    public void filterAvailableCars() {
        System.out.println("Carros disponíveis:");
        for (Car car : carPool) {
            if (car.isAvailable()) {
                System.out.println(car);
            }
        }
    }

    // Simula a reserva de um carro
    public void bookCar(String model) {
        for (Car car : carPool) {
            if (car.getModel().equalsIgnoreCase(model) && car.isAvailable()) {
                car.setAvailable(false); // Marca o carro como reservado
                System.out.println("O carro " + model + " foi reservado com sucesso!");
                return;
            }
        }
        System.out.println("Carro não disponível ou não encontrado.");
    }

    // Simula a devolução de um carro
    public void returnCar(String model) {
        for (Car car : carPool) {
            if (car.getModel().equalsIgnoreCase(model) && !car.isAvailable()) {
                car.setAvailable(true); // Marca o carro como disponível novamente
                System.out.println("O carro " + model + " foi devolvido com sucesso!");
                return;
            }
        }
        System.out.println("Carro não encontrado ou já está disponível.");
    }

    // Função principal de interação com o usuário
    public static void main(String[] args) {
        CarManagementTool carManagementTool = new CarManagementTool(); // Instancia a ferramenta de gestão
        Scanner scanner = new Scanner(System.in); // Scanner para ler a entrada do usuário
        int option;

        // Exibe um menu simples para o usuário interagir com a aplicação
        do {
            System.out.println("\n--- Ferramenta de Gestão de Carros ---");
            System.out.println("1. Ver todos os carros");
            System.out.println("2. Ver carros disponíveis");
            System.out.println("3. Reservar um carro");
            System.out.println("4. Devolver um carro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a entrada do número

            switch (option) {
                case 1:
                    carManagementTool.showCarPool(); // Mostra todos os carros da frota
                    break;
                case 2:
                    carManagementTool.filterAvailableCars(); // Mostra apenas os carros disponíveis
                    break;
                case 3:
                    System.out.print("Digite o modelo do carro que deseja reservar: ");
                    String modelToBook = scanner.nextLine();
                    carManagementTool.bookCar(modelToBook); // Reserva o carro especificado
                    break;
                case 4:
                    System.out.print("Digite o modelo do carro que deseja devolver: ");
                    String modelToReturn = scanner.nextLine();
                    carManagementTool.returnCar(modelToReturn); // Devolve o carro especificado
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 5); // Continua exibindo o menu até que o usuário escolha sair

        scanner.close(); // Fecha o scanner para evitar vazamentos de memória
    }
}
