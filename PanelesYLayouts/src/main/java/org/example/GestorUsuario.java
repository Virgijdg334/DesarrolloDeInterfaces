package org.example;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class GestorUsuario extends JFrame {

    // Campos de formulario como atributos para acceder desde los botones
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JComboBox<String> comboRol;
    private JCheckBox chkActivo;
    private JTextArea txtNotas;
    private JTextArea areaResumen;
    private JTextArea areaLogs;

    public GestorUsuario() {
        setTitle("Gestor de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        // === HEADER ===
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new MatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        JLabel titulo = new JLabel("Gestor de usuarios", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(new Color(40, 40, 40));
        titulo.setBorder(new EmptyBorder(15, 0, 15, 0));

        header.add(titulo, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // === NAVEGACION LATERAL ===
        JPanel nav = new JPanel(new GridLayout(5, 1, 15, 15));
        nav.setBackground(new Color(31, 78, 121));
        nav.setBorder(new EmptyBorder(40, 15, 40, 15));

        nav.add(crearBotonNav("Dashboard"));
        nav.add(crearBotonNav("Usuarios"));
        nav.add(crearBotonNav("Informes"));
        nav.add(crearBotonNav("Ajustes"));
        nav.add(crearBotonNav("Ayuda"));

        add(nav, BorderLayout.WEST);

        // === FORMULARIO (CENTER) ===
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Nombre"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = crearCampoTexto();
        form.add(txtNombre, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        form.add(new JLabel("Email"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtEmail = crearCampoTexto();
        form.add(txtEmail, gbc);

        // Rol
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Rol"), gbc);
        gbc.gridx = 1;
        comboRol = new JComboBox<>(new String[]{"Admin", "Editor", "Invitado"});
        comboRol.setBorder(new RoundedBorder(10));
        form.add(comboRol, gbc);

        // Activo
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Activo"), gbc);
        gbc.gridx = 1;
        chkActivo = new JCheckBox();
        form.add(chkActivo, gbc);

        // Notas
        gbc.gridx = 0; gbc.gridy = 4;
        form.add(new JLabel("Notas"), gbc);
        gbc.gridx = 1; gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        txtNotas = new JTextArea(5, 15);
        txtNotas.setBorder(new RoundedBorder(10));
        form.add(new JScrollPane(txtNotas), gbc);

        add(form, BorderLayout.CENTER);

        // === PREVISUALIZACION (EAST) ===
        JPanel preview = new JPanel(new BorderLayout());
        preview.setPreferredSize(new Dimension(260, 0));
        preview.setBackground(Color.WHITE);

        JTabbedPane tabs = new JTabbedPane();
        areaResumen = new JTextArea("Aquí se mostrará el resumen...");
        areaResumen.setEditable(false);
        areaLogs = new JTextArea("Aquí se mostrarán los logs...");
        areaLogs.setEditable(false);

        tabs.addTab("Resumen", new JScrollPane(areaResumen));
        tabs.addTab("Logs", new JScrollPane(areaLogs));
        preview.add(tabs, BorderLayout.CENTER);

        add(preview, BorderLayout.EAST);

        // === BOTONERA (SOUTH) ===
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        botones.setBackground(Color.WHITE);

        JButton btnCancelar = crearBotonAccion("Cancelar", new Color(200, 200, 200), Color.BLACK);
        JButton btnLimpiar = crearBotonAccion("Limpiar", new Color(120, 120, 120), Color.WHITE);
        JButton btnGuardar = crearBotonAccion("Guardar", new Color(31, 78, 121), Color.WHITE);

        // Añadir acciones
        btnCancelar.addActionListener(e -> System.exit(0));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> mostrarDialogoConfirmacion());

        botones.add(btnCancelar);
        botones.add(btnLimpiar);
        botones.add(btnGuardar);

        add(botones, BorderLayout.SOUTH);
    }

    // === Helpers ===
    private JTextField crearCampoTexto() {
        JTextField txt = new JTextField(15);
        txt.setBorder(new RoundedBorder(10));
        return txt;
    }

    private JButton crearBotonNav(String texto) {
        JButton btn = new JButton(texto);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(31, 78, 121));
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(15));
        btn.setHorizontalAlignment(SwingConstants.CENTER);

        // Acción básica de ejemplo
        btn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Has pulsado: " + texto,
                "Navegación",
                JOptionPane.INFORMATION_MESSAGE));

        return btn;
    }

    private JButton crearBotonAccion(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(20));
        return btn;
    }

    // === Lógica simple ===
    private void limpiarCampos() {
        txtNombre.setText("");
        txtEmail.setText("");
        comboRol.setSelectedIndex(0);
        chkActivo.setSelected(false);
        txtNotas.setText("");
    }

    private void mostrarDialogoConfirmacion() {
        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Guardar cambios?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            String resumen = "Nombre: " + txtNombre.getText() + "\n" +
                    "Email: " + txtEmail.getText() + "\n" +
                    "Rol: " + comboRol.getSelectedItem() + "\n" +
                    "Activo: " + (chkActivo.isSelected() ? "Sí" : "No") + "\n" +
                    "Notas: " + txtNotas.getText();
            areaResumen.setText(resumen);
            areaLogs.append("Guardado: " + txtNombre.getText() + "\n");
        }
    }

    // === Borde redondeado ===
    class RoundedBorder extends LineBorder {
        private int radius;
        RoundedBorder(int radius) {
            super(new Color(180, 180, 180), 1, true);
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.thickness + radius, this.thickness + radius,
                    this.thickness + radius, this.thickness + radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestorUsuario().setVisible(true);
        });
    }
}
