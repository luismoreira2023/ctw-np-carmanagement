import java.util.ArrayList;
import java.util.List;

class Carro {
    private String modelo;
    private int ano;
    private String tipoMotor;

    public Carro(String modelo, int ano, String tipoMotor) {
        this.modelo = modelo;
        this.ano = ano;
        this.tipoMotor = tipoMotor;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    @Override
    public String toString() {
        return "Modelo: " + modelo + ", Ano: " + ano + ", Tipo de Motor: " + tipoMotor;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Carro> listaDeCarros = new ArrayList<>();

        // Adicionando alguns carros à lista
        listaDeCarros.add(new Carro("Fusca", 1975, "Gasolina"));
        listaDeCarros.add(new Carro("Civic", 2020, "Flex"));
        listaDeCarros.add(new Carro("Tesla Model S", 2022, "Elétrico"));

        // Exibindo a lista de carros com tipo de motor
        System.out.println("Lista de Carros:");
        for (Carro carro : listaDeCarros) {
            System.out.println(carro);
        }
    }
}
