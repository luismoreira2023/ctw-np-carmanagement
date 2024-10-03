import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CarReservationSystem {
    
    // Classe que representa uma reserva de carro
    static class Reservation {
        LocalDate pickupDate;  // Data de retirada
        LocalDate dropOffDate; // Data de devolução
        
        public Reservation(LocalDate pickupDate, LocalDate dropOffDate) {
            this.pickupDate = pickupDate;
            this.dropOffDate = dropOffDate;
        }

        @Override
        public String toString() {
            return "Reserva de " + pickupDate + " até " + dropOffDate;
        }
    }

    // Método principal
    public static void main(String[] args) {
        LocalDate selectedPickupDate = LocalDate.now(); // Data de retirada selecionada
        List<Reservation> reservations = new ArrayList<>(); // Lista de reservas

        // Limitar a devolução a 4 dias após a retirada
        LocalDate maxDropOffDate = selectedPickupDate.plusDays(4);
        System.out.println("Data máxima de devolução: " + maxDropOffDate);
        
        // Exemplo de criação de reservas com validação
        for (int i = 1; i <= 4; i++) {
            LocalDate dropOffDate = selectedPickupDate.plusDays(i);
            // Verifica se a data de devolução está dentro do limite
            if (dropOffDate.isBefore(maxDropOffDate.plusDays(1))) {
                reservations.add(new Reservation(selectedPickupDate, dropOffDate));
            }
        }

        // Exibir as reservas permitidas
        System.out.println("Reservas permitidas:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        // Exemplo de reserva para sexta-feira
        LocalDate friday = LocalDate.now().with(java.time.DayOfWeek.FRIDAY);
        if (friday.getDayOfWeek().getValue() == 5) { // Verifica se hoje é sexta-feira
            LocalTime reservationTime = LocalTime.of(20, 0); // Hora da reserva
            System.out.println("Reserva feita para sexta-feira às " + reservationTime);
            // Permite a extensão para o fim de semana
            LocalDate extendedDropOffDate = friday.plusDays(3); // Devolução na segunda-feira
            System.out.println("Data de devolução estendida para segunda-feira: " + extendedDropOffDate);
            reservations.add(new Reservation(friday, extendedDropOffDate));
        }

        // Exibir todas as reservas
        System.out.println("\nTodas as reservas:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}
