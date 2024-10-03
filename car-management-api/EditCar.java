import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe principal que representa a aplicação
public class EditCar extends JFrame {
    
    private JTextField carNameField;  // Campo para inserir o nome do carro
    private JTextField carModelField;  // Campo para inserir o modelo do carro
    private JButton editButton;         // Botão para editar informações do carro
    private JButton saveButton;         // Botão para salvar as edições
    private JLabel messageLabel;        // Rótulo para exibir mensagens de sucesso ou erro
    
    // Construtor da classe
    public EditCar() {
        // Configurações da janela
        setTitle("Car Management Tool");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Inicializando os componentes da interface
        carNameField = new JTextField(20);
        carModelField = new JTextField(20);
        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        messageLabel = new JLabel("");
        
        // Adicionando componentes à janela
        add(new JLabel("Car Name:"));
        add(carNameField);
        add(new JLabel("Car Model:"));
        add(carModelField);
        add(editButton);
        add(saveButton);
        add(messageLabel);
        
        // Ação do botão "Edit"
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode carregar as informações do carro, por enquanto, vamos simular
                carNameField.setText("Hyundai");  // Simulando o nome do carro
                carModelField.setText("JC-19-85");  // Simulando o modelo do carro
                messageLabel.setText("");  // Limpa mensagens anteriores
            }
        });
        
        // Ação do botão "Save"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulando a operação de salvar as informações
                String carName = carNameField.getText();
                String carModel = carModelField.getText();
                
                // Verifica se os campos estão vazios
                if (carName.isEmpty() || carModel.isEmpty()) {
                    messageLabel.setText("Error: All fields must be filled.");
                } else {
                    // Simulando um sucesso ao salvar
                    messageLabel.setText("Success: Car information saved!");
                    // Aqui você pode adicionar lógica para atualizar a lista de caronas
                }
            }
        });
    }
    
    // Método principal para executar a aplicação
    public static void main(String[] args) {
        // Cria e exibe a interface gráfica
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditCar().setVisible(true);
            }
        });
    }
}
