import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FleetReservationSystem {

    // Classe para representar uma reserva de carro
    static class Reservation {
        private LocalDateTime pickupDate;
        private LocalDateTime dropOffDate;

        public Reservation(LocalDateTime pickupDate, LocalDateTime dropOffDate) {
            this.pickupDate = pickupDate;
            this.dropOffDate = dropOffDate;
        }

        public LocalDateTime getPickupDate() {
            return pickupDate;
        }

        public LocalDateTime getDropOffDate() {
            return dropOffDate;
        }
    }

    // Lista de todas as reservas existentes
    private List<Reservation> reservations = new ArrayList<>();
    private int minimumInterval;  // Campo para o intervalo mínimo em horas entre as reservas

    // Método para configurar o intervalo mínimo
    public void setMinimumInterval(int hours) {
        this.minimumInterval = hours;
    }

    // Método para adicionar uma reserva
    public String addReservation(LocalDateTime pickupDate, LocalDateTime dropOffDate) {
        // Verifica se a reserva é válida
        if (isReservationValid(pickupDate, dropOffDate)) {
            reservations.add(new Reservation(pickupDate, dropOffDate));
            return "Reserva adicionada com sucesso!";
        } else {
            return "Reserva inválida. Verifique as restrições.";
        }
    }

    // Método que verifica se uma reserva é válida
    private boolean isReservationValid(LocalDateTime pickupDate, LocalDateTime dropOffDate) {
        // Restrição 1: A reserva não pode ser maior que 4 dias
        if (ChronoUnit.DAYS.between(pickupDate, dropOffDate) > 4) {
            System.out.println("Erro: A reserva não pode ser superior a 4 dias.");
            return false;
        }

        // Restrição 2: Verifica se há pelo menos o intervalo definido pelo utilizador entre reservas
        for (Reservation existingReservation : reservations) {
            // A data de recolha da nova reserva deve ser pelo menos "minimumInterval" horas após a devolução da anterior
            if (ChronoUnit.HOURS.between(existingReservation.getDropOffDate(), pickupDate) < minimumInterval) {
                System.out.println("Erro: Deve haver pelo menos " + minimumInterval + " horas de intervalo entre as reservas.");
                return false;
            }

            // A data de devolução da nova reserva deve ser pelo menos "minimumInterval" horas antes da próxima recolha
            if (ChronoUnit.HOURS.between(dropOffDate, existingReservation.getPickupDate()) < minimumInterval) {
                System.out.println("Erro: Deve haver pelo menos " + minimumInterval + " horas antes da próxima recolha.");
                return false;
            }
        }

        // Restrição 3: Reservas de fim de semana
        if (pickupDate.getDayOfWeek().getValue() == 5 && pickupDate.getHour() < 20) { // Sexta antes das 20:00
            System.out.println("Erro: Reservas de fim de semana só são permitidas após as 20:00 de sexta-feira.");
            return false;
        }
        if (dropOffDate.getDayOfWeek().getValue() == 1 && dropOffDate.getHour() > 8) { // Segunda depois das 08:00
            System.out.println("Erro: Reservas de fim de semana devem terminar até às 08:00 de segunda-feira.");
            return false;
        }

        return true; // Se passar por todas as restrições, a reserva é válida
    }

    // Método main para iniciar o sistema de reservas
    public static void main(String[] args) {
        FleetReservationSystem system = new FleetReservationSystem();
        Scanner scanner = new Scanner(System.in);

        // Pergunta ao utilizador qual o intervalo mínimo entre reservas em horas
        System.out.print("Insira o intervalo mínimo entre reservas (em horas): ");
        int interval = scanner.nextInt();
        system.setMinimumInterval(interval);  // Define o intervalo baseado na entrada do utilizador

        // Adicionar uma reserva com input do utilizador
        System.out.println("Insira a data de recolha (formato: ano mês dia hora minuto): ");
        int ano1 = scanner.nextInt();
        int mes1 = scanner.nextInt();
        int dia1 = scanner.nextInt();
        int hora1 = scanner.nextInt();
        int minuto1 = scanner.nextInt();
        LocalDateTime pickupDate = LocalDateTime.of(ano1, mes1, dia1, hora1, minuto1);

        System.out.println("Insira a data de devolução (formato: ano mês dia hora minuto): ");
        int ano2 = scanner.nextInt();
        int mes2 = scanner.nextInt();
        int dia2 = scanner.nextInt();
        int hora2 = scanner.nextInt();
        int minuto2 = scanner.nextInt();
        LocalDateTime dropOffDate = LocalDateTime.of(ano2, mes2, dia2, hora2, minuto2);

        // Tenta adicionar a reserva e mostra o resultado
        System.out.println(system.addReservation(pickupDate, dropOffDate));

        scanner.close();  // Fecha o scanner
    }
}
