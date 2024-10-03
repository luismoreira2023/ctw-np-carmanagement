import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddAndRemoveCars {
    // Lista para armazenar os carros
    private ArrayList<String> carpoolList = new ArrayList<>();

    public static void main(String[] args) {
        // Criar uma instância da ferramenta
        new AddAndRemoveCars().createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Criar o quadro principal
        JFrame frame = new JFrame("Ferramenta de Gestão de Carros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Criar um painel para a lista de carros
        JPanel panel = new JPanel();
        frame.add(panel);
        updateCarpoolList(panel);

        // Adicionar o painel ao quadro
        frame.setVisible(true);
    }

    private void updateCarpoolList(JPanel panel) {
        panel.removeAll(); // Limpa o painel antes de atualizar
        for (String car : carpoolList) {
            // Criar um botão para remover o carro
            JButton removeCarButton = new JButton("Remover " + car);
            removeCarButton.addActionListener(new RemoveCarAction(car, panel));
            panel.add(removeCarButton);
        }

        // Adicionar um botão para adicionar novo carro
        JButton addCarButton = new JButton("Adicionar Novo Carro");
        addCarButton.addActionListener(new AddCarAction(panel));
        panel.add(addCarButton);

        panel.revalidate(); // Revalidar o painel para mostrar os novos componentes
        panel.repaint(); // Repaint para atualizar a visualização
    }

    private class AddCarAction implements ActionListener {
        private JPanel panel;

        public AddCarAction(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Criar um formulário para adicionar um novo carro
            JTextField carNameField = new JTextField(20);
            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Nome do Carro:"));
            inputPanel.add(carNameField);
            
            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Adicionar Novo Carro", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String carName = carNameField.getText();
                
                // Adicionar o novo carro à lista
                if (addCar(carName)) {
                    // Exibir pop-up de sucesso
                    JOptionPane.showMessageDialog(null, "Carro adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    updateCarpoolList(panel); // Atualizar a lista após adicionar
                } else {
                    // Exibir pop-up de erro
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar carro. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class RemoveCarAction implements ActionListener {
        private String carName;
        private JPanel panel;

        public RemoveCarAction(String carName, JPanel panel) {
            this.carName = carName;
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Tentar remover o carro da lista
            if (removeCar(carName)) {
                // Exibir pop-up de sucesso
                JOptionPane.showMessageDialog(null, "Carro removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                updateCarpoolList(panel); // Atualizar a lista após remover
            } else {
                // Exibir pop-up de erro
                JOptionPane.showMessageDialog(null, "Erro ao remover carro. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean addCar(String carName) {
        // Simular a adição de um carro (poderia incluir validações reais)
        if (carName != null && !carName.trim().isEmpty()) {
            carpoolList.add(carName); // Adiciona carro à lista
            return true; // Retorna verdadeiro se a adição foi bem-sucedida
        }
        return false; // Retorna falso se houve erro
    }

    private boolean removeCar(String carName) {
        // Simular a remoção de um carro da lista
        return carpoolList.remove(carName); // Retorna verdadeiro se a remoção foi bem-sucedida
    }
}
