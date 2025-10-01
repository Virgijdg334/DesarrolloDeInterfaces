package PanelesYLayouts;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GestorUsuario extends JFrame {

    // Campos de formulario
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JComboBox<String> comboRol;
    private JCheckBox chkActivo;
    private JTextArea txtNotas;
    private JTextArea areaResumen;
    private JTextArea areaLogs;

    // Contenedor central con CardLayout
    private JPanel panelCentral;
    private CardLayout cardLayout;

    public GestorUsuario() {
        setTitle("Gestor de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
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

        JButton btnDashboard = crearBotonNav("Dashboard");
        JButton btnUsuarios = crearBotonNav("Usuarios");
        JButton btnInformes = crearBotonNav("Informes");
        JButton btnAjustes = crearBotonNav("Ajustes");
        JButton btnAyuda = crearBotonNav("Ayuda");

        nav.add(btnDashboard);
        nav.add(btnUsuarios);
        nav.add(btnInformes);
        nav.add(btnAjustes);
        nav.add(btnAyuda);

        add(nav, BorderLayout.WEST);

        // === CARDLAYOUT CENTRAL ===
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        // Panel Dashboard
        JPanel panelDashboard = new JPanel(new GridLayout(2, 2, 20, 20));
        panelDashboard.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelDashboard.setBackground(Color.WHITE);
        panelDashboard.add(crearCard("👥 Usuarios Totales", "154"));
        panelDashboard.add(crearCard("✅ Activos", "120"));
        panelDashboard.add(crearCard("🛠 Roles", "3"));
        panelDashboard.add(crearCard("📋 Último registro", "Hace 2 días"));

        // Panel Usuarios
        JPanel panelUsuarios = crearFormularioUsuarios();

        // Panel Informes
        String[] columnas = {"ID", "Nombre", "Email", "Rol", "Activo"};
        Object[][] datos = {
                {1, "Ana Torres", "ana@mail.com", "Admin", "Sí"},
                {2, "Luis Pérez", "luis@mail.com", "Editor", "No"},
                {3, "Marta Ruiz", "marta@mail.com", "Invitado", "Sí"}
        };
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        JPanel panelInformes = new JPanel(new BorderLayout());
        panelInformes.add(new JLabel("📑 Informes de usuarios", JLabel.CENTER), BorderLayout.NORTH);
        panelInformes.add(scrollTabla, BorderLayout.CENTER);

        // Panel Ajustes
        JPanel panelAjustes = new JPanel(new GridLayout(2, 1, 15, 15));
        panelAjustes.setBackground(Color.WHITE);
        panelAjustes.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelGeneral = new JPanel(new GridLayout(3, 1));
        panelGeneral.setBorder(BorderFactory.createTitledBorder("Preferencias generales"));
        panelGeneral.add(new JCheckBox("Notificaciones activadas"));
        panelGeneral.add(new JCheckBox("Modo oscuro"));
        panelGeneral.add(new JCheckBox("Guardar automáticamente"));

        JPanel panelAvanzado = new JPanel(new GridLayout(2, 1));
        panelAvanzado.setBorder(BorderFactory.createTitledBorder("Opciones avanzadas"));
        panelAvanzado.add(new JCheckBox("Habilitar logs de depuración"));
        panelAvanzado.add(new JCheckBox("Permitir edición sin conexión"));

        panelAjustes.add(panelGeneral);
        panelAjustes.add(panelAvanzado);

        // Panel Ayuda
        JPanel panelAyuda = new JPanel(new BorderLayout());
        panelAyuda.setBackground(Color.WHITE);

        JTextArea txtAyuda = new JTextArea(
                "Bienvenido a la sección de Ayuda.\n\n" +
                        "• Para registrar un nuevo usuario, dirígete a 'Usuarios'.\n" +
                        "• Los informes están en 'Informes'.\n" +
                        "• Configura las preferencias en 'Ajustes'.\n\n"
        );
        txtAyuda.setEditable(false);
        txtAyuda.setLineWrap(true);
        txtAyuda.setWrapStyleWord(true);
        txtAyuda.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel linkDoc = crearLink("📘 Documentación oficial");
        JLabel linkSoporte = crearLink("💬 Soporte técnico");

        JPanel links = new JPanel(new GridLayout(2, 1, 5, 5));
        links.add(linkDoc);
        links.add(linkSoporte);

        panelAyuda.add(new JScrollPane(txtAyuda), BorderLayout.CENTER);
        panelAyuda.add(links, BorderLayout.SOUTH);

        // Añadir a CardLayout
        panelCentral.add(panelDashboard, "Dashboard");
        panelCentral.add(panelUsuarios, "Usuarios");
        panelCentral.add(panelInformes, "Informes");
        panelCentral.add(panelAjustes, "Ajustes");
        panelCentral.add(panelAyuda, "Ayuda");

        add(panelCentral, BorderLayout.CENTER);

        // Mostrar Dashboard por defecto
        cardLayout.show(panelCentral, "Dashboard");

        // === ACCIONES DE NAVEGACION ===
        btnDashboard.addActionListener(e -> cardLayout.show(panelCentral, "Dashboard"));
        btnUsuarios.addActionListener(e -> cardLayout.show(panelCentral, "Usuarios"));
        btnInformes.addActionListener(e -> cardLayout.show(panelCentral, "Informes"));
        btnAjustes.addActionListener(e -> cardLayout.show(panelCentral, "Ajustes"));
        btnAyuda.addActionListener(e -> cardLayout.show(panelCentral, "Ayuda"));
    }

    // === Formulario de usuarios ===
    private JPanel crearFormularioUsuarios() {
        JPanel panel = new JPanel(new BorderLayout());

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

        // === BOTONERA (SOUTH) ===
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        botones.setBackground(Color.WHITE);

        JButton btnCancelar = crearBotonAccion("Cancelar", new Color(200, 200, 200), Color.BLACK);
        JButton btnLimpiar = crearBotonAccion("Limpiar", new Color(120, 120, 120), Color.WHITE);
        JButton btnGuardar = crearBotonAccion("Guardar", new Color(31, 78, 121), Color.WHITE);

        // Acciones
        btnCancelar.addActionListener(e -> System.exit(0));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> mostrarDialogoConfirmacion());

        botones.add(btnCancelar);
        botones.add(btnLimpiar);
        botones.add(btnGuardar);

        panel.add(form, BorderLayout.CENTER);
        panel.add(preview, BorderLayout.EAST);
        panel.add(botones, BorderLayout.SOUTH);

        return panel;
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
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        Color normal = new Color(31, 78, 121);
        Color hover = new Color(21, 58, 101);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(normal); }
        });

        return btn;
    }

    private JButton crearBotonAccion(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(new RoundedBorder(20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover
        Color normal = bg;
        Color hover = bg.darker();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(normal); }
        });

        return btn;
    }

    // Tarjeta para Dashboard
    private JPanel crearCard(String titulo, String valor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(new RoundedBorder(15));
        JLabel lblTitulo = new JLabel(titulo, JLabel.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel lblValor = new JLabel(valor, JLabel.CENTER);
        lblValor.setFont(new Font("SansSerif", Font.BOLD, 22));
        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);
        return card;
    }

    // Crear links simulados
    private JLabel crearLink(String texto) {
        JLabel lbl = new JLabel("<html><u>" + texto + "</u></html>");
        lbl.setForeground(Color.BLUE);
        lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(GestorUsuario.this,
                        "Abriría: " + texto,
                        "Enlace",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return lbl;
    }

    // === Lógica ===
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
            return new Insets(5, 10, 5, 10);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorUsuario().setVisible(true));
    }
}
