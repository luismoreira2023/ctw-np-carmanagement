import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; // Importação da classe Scanner para leitura de entrada do usuário

public class Reservation {
    private LocalDateTime pickupTime; // Data/hora de retirada
    private LocalDateTime dropOffTime; // Data/hora de devolução

    // Construtor para criar uma nova reserva
    public Reservation(LocalDateTime pickupTime, LocalDateTime dropOffTime) {
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
    }

    // Métodos para obter as datas e horas
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public LocalDateTime getDropOffTime() {
        return dropOffTime;
    }

    // Método para verificar se a nova reserva é válida em relação à reserva existente
    public static boolean isValidReservation(Reservation existingReservation, Reservation newReservation) {
        // Verifica se a nova retirada é pelo menos uma hora após a retirada existente
        boolean isPickupValid = newReservation.getPickupTime().isAfter(existingReservation.getPickupTime().plusHours(1));
        // Verifica se a nova devolução é pelo menos uma hora antes da devolução existente
        boolean isDropOffValid = newReservation.getDropOffTime().isBefore(existingReservation.getDropOffTime().minusHours(1));
        
        return isPickupValid && isDropOffValid; // Retorna verdadeiro se ambas as condições forem atendidas
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para ler a entrada do usuário
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Formato para data/hora

        // Cria uma reserva existente
        System.out.println("Insira a data/hora de retirada da reserva existente (formato: yyyy-MM-dd HH:mm):");
        String existingPickupInput = scanner.nextLine();
        LocalDateTime existingPickupTime = LocalDateTime.parse(existingPickupInput, formatter);
        
        System.out.println("Insira a data/hora de devolução da reserva existente (formato: yyyy-MM-dd HH:mm):");
        String existingDropOffInput = scanner.nextLine();
        LocalDateTime existingDropOffTime = LocalDateTime.parse(existingDropOffInput, formatter);
        Reservation existingReservation = new Reservation(existingPickupTime, existingDropOffTime);

        // Cria uma nova reserva
        System.out.println("Insira a data/hora de retirada da nova reserva (formato: yyyy-MM-dd HH:mm):");
        String newPickupInput = scanner.nextLine();
        LocalDateTime newPickupTime = LocalDateTime.parse(newPickupInput, formatter);

        System.out.println("Insira a data/hora de devolução da nova reserva (formato: yyyy-MM-dd HH:mm):");
        String newDropOffInput = scanner.nextLine();
        LocalDateTime newDropOffTime = LocalDateTime.parse(newDropOffInput, formatter);
        Reservation newReservation = new Reservation(newPickupTime, newDropOffTime);

        // Verifica se a nova reserva é válida
        if (isValidReservation(existingReservation, newReservation)) {
            System.out.println("A nova reserva é válida!");
        } else {
            System.out.println("A nova reserva não é válida!");
        }

        scanner.close(); // Fecha o scanner
    }
}
