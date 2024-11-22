package br.edu.fatecpg.view;

import br.edu.fatecpg.model.ValidacaoEmailException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteCadastroApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextField emailField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClienteCadastroApp frame = new ClienteCadastroApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ClienteCadastroApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridLayout(4, 2, 10, 10));
        setContentPane(contentPane);

        // Adiciona os componentes da interface
        JLabel nomeLabel = new JLabel("Nome do Cliente:");
        nomeField = new JTextField();
        contentPane.add(nomeLabel);
        contentPane.add(nomeField);

        JLabel idadeLabel = new JLabel("Idade:");
        idadeField = new JTextField();
        contentPane.add(idadeLabel);
        contentPane.add(idadeField);

        JLabel emailLabel = new JLabel("E-mail:");
        emailField = new JTextField();
        contentPane.add(emailLabel);
        contentPane.add(emailField);

        JButton cadastrarButton = new JButton("Cadastrar");
        contentPane.add(new JLabel()); // Espaço vazio
        contentPane.add(cadastrarButton);

        // Adiciona ação ao botão "Cadastrar"
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastrarCliente();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ClienteCadastroApp.this,
                            "Erro: " + ex.getMessage(),
                            "Erro de Cadastro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Lógica de cadastro de cliente com validação.
     */
    private void cadastrarCliente() throws ValidacaoEmailException {
        String nome = nomeField.getText().trim();
        String idadeText = idadeField.getText().trim();
        String email = emailField.getText().trim();

        // Validação do nome
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome do Cliente' não pode estar vazio.");
        }

        // Validação da idade
        int idade;
        try {
            idade = Integer.parseInt(idadeText);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("O campo 'Idade' deve conter um número inteiro válido.");
        }

        // Validação do e-mail
        if (!email.contains("@")) {
            throw new ValidacaoEmailException("O e-mail informado é inválido. Deve conter '@'.");
        }

        // Mensagem de sucesso
        JOptionPane.showMessageDialog(this,
                "Cadastro realizado com sucesso!\n" +
                        "Nome: " + nome + "\n" +
                        "Idade: " + idade + "\n" +
                        "E-mail: " + email,
                "Cadastro Concluído",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
