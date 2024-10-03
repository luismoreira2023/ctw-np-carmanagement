import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Car que vai armazenar as informações do carro
class Car {
    // Atributos de cada carro
    int id;
    String brand;
    String model;

    // Construtor do carro
    public Car(int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    // Método para mostrar os detalhes do carro
    public void viewCar() {
        System.out.println("Car ID: " + id + ", Brand: " + brand + ", Model: " + model);
    }

    // Método para editar as informações do carro
    public void editCar(String newBrand, String newModel) {
        this.brand = newBrand;
        this.model = newModel;
        System.out.println("Car updated successfully.");
    }

    // Método para mostrar o ID do carro (usado para exclusão)
    public int getId() {
        return id;
    }
}

// Classe principal que simula o gerenciamento de carros
public class CarpoolManagement {

    // Lista para armazenar os carros da frota
    private List<Car> carpool = new ArrayList<>();

    // Método para adicionar um carro à lista
    public void addCar(Car car) {
        carpool.add(car);
    }

    // Método para exibir todos os carros na lista
    public void showCarpool() {
        if (carpool.isEmpty()) {
            System.out.println("The carpool is empty.");
        } else {
            System.out.println("List of all cars in the carpool:");
            for (Car car : carpool) {
                car.viewCar(); // Mostra os detalhes de cada carro
            }
        }
    }

    // Método para excluir um carro da lista pelo ID
    public void deleteCar(int carId) {
        for (Car car : carpool) {
            if (car.getId() == carId) {
                carpool.remove(car);
                System.out.println("Car with ID " + carId + " has been removed.");
                return;
            }
        }
        System.out.println("Car with ID " + carId + " not found.");
    }

    // Simula a interface de botões (exibir, editar, excluir)
    public void carMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCar Management Menu:");
            System.out.println("1. View All Cars");
            System.out.println("2. Edit a Car");
            System.out.println("3. Delete a Car");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showCarpool();
                    break;
                case 2:
                    System.out.print("Enter the ID of the car to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    System.out.print("Enter new brand: ");
                    String newBrand = scanner.nextLine();
                    System.out.print("Enter new model: ");
                    String newModel = scanner.nextLine();
                    for (Car car : carpool) {
                        if (car.getId() == editId) {
                            car.editCar(newBrand, newModel);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the car to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteCar(deleteId);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Método main para execução do programa
    public static void main(String[] args) {
        CarpoolManagement manager = new CarpoolManagement();

        // Adicionando carros de exemplo à lista
        manager.addCar(new Car(1, "Toyota", "Corolla"));
        manager.addCar(new Car(2, "Honda", "Civic"));
        manager.addCar(new Car(3, "Ford", "Focus"));

        // Inicia o menu de gerenciamento de carros
        manager.carMenu();
    }
}
