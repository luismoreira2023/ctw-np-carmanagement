import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Carro {
    private String marca;
    private String modelo;
    private int numAssentos;
    private String placa;
    private String tipoMotor;
    private int autonomia; // em km
    private String cor;
    private String imagem; // Caminho para a imagem

    public Carro(String marca, String modelo, int numAssentos, String placa, String tipoMotor, int autonomia, String cor, String imagem) {
        this.marca = marca;
        this.modelo = modelo;
        this.numAssentos = numAssentos;
        this.placa = placa;
        this.tipoMotor = tipoMotor;
        this.autonomia = autonomia;
        this.cor = cor;
        this.imagem = imagem;
    }

      // Adicionando o método getModelo
      public String getModelo() {
        return modelo;
    }


    public void mostrarDetalhes() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Número de Assentos: " + numAssentos);
        System.out.println("Placa: " + placa);
        System.out.println("Tipo de Motor: " + tipoMotor);
        System.out.println("Autonomia: " + autonomia + " km");
        System.out.println("Cor: " + cor);
        System.out.println("Imagem: " + imagem);
        System.out.println(); // Linha em branco para separar os detalhes dos carros
    }
}

public class checkCarDetails {
    public static void main(String[] args) {
        List<Carro> listaDeCarros = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Adicionando alguns carros à lista
        listaDeCarros.add(new Carro("Toyota", "Corolla", 5, "ABC-1234", "Combustão", 600, "Prata", "imagem_corolla.png"));
        listaDeCarros.add(new Carro("Tesla", "Model 3", 5, "DEF-5678", "Elétrico", 500, "Preto", "imagem_model3.png"));
        listaDeCarros.add(new Carro("Toyota", "Prius", 5, "GHI-9012", "Híbrido", 800, "Verde", "imagem_prius.png"));

        // Simulação de clique no botão "Ver"
        System.out.println("Escolha um carro para ver os detalhes (0 a " + (listaDeCarros.size() - 1) + "): ");
        for (int i = 0; i < listaDeCarros.size(); i++) {
            System.out.println(i + ": " + listaDeCarros.get(i).getModelo());
        }

        int escolha = scanner.nextInt();

        // Verificando se a escolha é válida
        if (escolha >= 0 && escolha < listaDeCarros.size()) {
            System.out.println("Detalhes do Carro:");
            listaDeCarros.get(escolha).mostrarDetalhes();
        } else {
            System.out.println("Escolha inválida.");
        }

        scanner.close();
    }
}
