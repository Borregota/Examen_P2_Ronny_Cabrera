import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;  // <-- Esta es la línea que necesitas añadir

public class SpiderverseUI extends JFrame {
    private SpiderverseManager manager;
    private JTabbedPane tabbedPane;

    // Componentes para Registro
    private JTextField txtCodigo, txtNombre;
    private JComboBox<String> cmbPoder, cmbUniverso, cmbNivel;
    private JTable tablaHeroes;
    private DefaultTableModel modeloTabla;

    // Componentes para Búsqueda
    private JTextField txtBuscarCodigo;
    private JTextField txtNombreEncontrado;
    private JComboBox<String> cmbPoderEncontrado, cmbUniversoEncontrado, cmbNivelEncontrado;

    // Componentes para Filtrado
    private JComboBox<String> cmbPoderFiltrar;
    private JTable tablaFiltrados;
    private DefaultTableModel modeloFiltrados;

    public SpiderverseUI() {
        manager = new SpiderverseManager();
        setTitle("Gestión de Héroes del Spiderverse");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        crearPestanaRegistro();
        crearPestanaBusqueda();
        crearPestanaFiltrado();

        add(tabbedPane);
    }

    private void crearPestanaRegistro() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(5, 2, 5, 5));

        panelEntrada.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelEntrada.add(txtCodigo);

        panelEntrada.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Poder Especial:"));
        String[] poderes = {"Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"};
        cmbPoder = new JComboBox<>(poderes);
        panelEntrada.add(cmbPoder);

        panelEntrada.add(new JLabel("Universo:"));
        String[] universos = {"Tierra-616", "Tierra-1610", "Tierra-12041", "Tierra-90214", "Tierra-138"};
        cmbUniverso = new JComboBox<>(universos);
        panelEntrada.add(cmbUniverso);

        panelEntrada.add(new JLabel("Nivel de Experiencia:"));
        String[] niveles = {"1", "2", "3", "4", "5"};
        cmbNivel = new JComboBox<>(niveles);
        panelEntrada.add(cmbNivel);

        // Botón de registro
        JButton btnRegistrar = new JButton("Registrar Héroe");
        btnRegistrar.addActionListener(e -> registrarHeroe());

        // Tabla de héroes
        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Poder", "Universo", "Nivel"}, 0);
        tablaHeroes = new JTable(modeloTabla);

        // Agregar componentes
        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(btnRegistrar, BorderLayout.CENTER);
        panel.add(new JScrollPane(tablaHeroes), BorderLayout.SOUTH);

        tabbedPane.addTab("Registro", panel);
    }

    private void crearPestanaBusqueda() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Buscar por código:"));
        txtBuscarCodigo = new JTextField(10);
        panelBusqueda.add(txtBuscarCodigo);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarHeroe());
        panelBusqueda.add(btnBuscar);

        // Panel de resultados
        JPanel panelResultado = new JPanel(new GridLayout(4, 2, 5, 5));
        panelResultado.add(new JLabel("Nombre:"));
        txtNombreEncontrado = new JTextField();
        panelResultado.add(txtNombreEncontrado);

        panelResultado.add(new JLabel("Poder Especial:"));
        String[] poderes = {"Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"};
        cmbPoderEncontrado = new JComboBox<>(poderes);
        panelResultado.add(cmbPoderEncontrado);

        panelResultado.add(new JLabel("Universo:"));
        String[] universos = {"Tierra-616", "Tierra-1610", "Tierra-12041", "Tierra-90214", "Tierra-138"};
        cmbUniversoEncontrado = new JComboBox<>(universos);
        panelResultado.add(cmbUniversoEncontrado);

        panelResultado.add(new JLabel("Nivel de Experiencia:"));
        String[] niveles = {"1", "2", "3", "4", "5"};
        cmbNivelEncontrado = new JComboBox<>(niveles);
        panelResultado.add(cmbNivelEncontrado);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> actualizarHeroe());

        // Agregar componentes
        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(panelResultado, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);

        tabbedPane.addTab("Búsqueda", panel);
    }

    private void crearPestanaFiltrado() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de filtrado
        JPanel panelFiltrado = new JPanel();
        panelFiltrado.add(new JLabel("Filtrar héroes que NO tengan el poder:"));

        String[] poderes = {"Sentido Arácnido", "Trepa Muros", "Fuerza Sobrehumana", "Agilidad Mejorada", "Tejido de Telaraña"};
        cmbPoderFiltrar = new JComboBox<>(poderes);
        panelFiltrado.add(cmbPoderFiltrar);

        JButton btnFiltrar = new JButton("Filtrar y Ordenar");
        btnFiltrar.addActionListener(e -> filtrarYOrdenar());
        panelFiltrado.add(btnFiltrar);

        // Tabla de resultados
        modeloFiltrados = new DefaultTableModel(new Object[]{"Código", "Nombre", "Poder", "Universo", "Nivel"}, 0);
        tablaFiltrados = new JTable(modeloFiltrados);

        // Agregar componentes
        panel.add(panelFiltrado, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaFiltrados), BorderLayout.CENTER);

        tabbedPane.addTab("Filtrado", panel);
    }

    private void registrarHeroe() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            String poder = (String) cmbPoder.getSelectedItem();
            String universo = (String) cmbUniverso.getSelectedItem();
            int nivel = Integer.parseInt((String) cmbNivel.getSelectedItem());

            if(nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SpiderverseHero nuevo = new SpiderverseHero(codigo, nombre, poder, universo, nivel);
            if(manager.agregarHeroe(nuevo)) {
                modeloTabla.addRow(new Object[]{codigo, nombre, poder, universo, nivel});
                limpiarCamposRegistro();
                JOptionPane.showMessageDialog(this, "Héroe registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El código ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código y nivel deben ser números válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarHeroe() {
        try {
            int codigo = Integer.parseInt(txtBuscarCodigo.getText());
            SpiderverseHero heroe = manager.buscarHeroe(codigo);

            if(heroe != null) {
                txtNombreEncontrado.setText(heroe.getNombre());
                cmbPoderEncontrado.setSelectedItem(heroe.getPoderEspecial());
                cmbUniversoEncontrado.setSelectedItem(heroe.getUniverso());
                cmbNivelEncontrado.setSelectedItem(String.valueOf(heroe.getNivelExperiencia()));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un héroe con ese código", "No encontrado", JOptionPane.WARNING_MESSAGE);
                limpiarCamposBusqueda();
            }
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarHeroe() {
        try {
            int codigo = Integer.parseInt(txtBuscarCodigo.getText());
            String nombre = txtNombreEncontrado.getText();
            String poder = (String) cmbPoderEncontrado.getSelectedItem();
            String universo = (String) cmbUniversoEncontrado.getSelectedItem();
            int nivel = Integer.parseInt((String) cmbNivelEncontrado.getSelectedItem());

            if(manager.actualizarHeroe(codigo, nombre, poder, universo, nivel)) {
                actualizarTablaPrincipal();
                JOptionPane.showMessageDialog(this, "Héroe actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el héroe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nivel debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filtrarYOrdenar() {
        String poderExcluir = (String) cmbPoderFiltrar.getSelectedItem();
        LinkedList<SpiderverseHero> filtrados = manager.filtrarYOrdenarHeroes(poderExcluir);

        modeloFiltrados.setRowCount(0);
        for(SpiderverseHero heroe : filtrados) {
            modeloFiltrados.addRow(new Object[]{
                    heroe.getCodigo(),
                    heroe.getNombre(),
                    heroe.getPoderEspecial(),
                    heroe.getUniverso(),
                    heroe.getNivelExperiencia()
            });
        }
    }

    private void limpiarCamposRegistro() {
        txtCodigo.setText("");
        txtNombre.setText("");
        cmbPoder.setSelectedIndex(0);
        cmbUniverso.setSelectedIndex(0);
        cmbNivel.setSelectedIndex(0);
    }

    private void limpiarCamposBusqueda() {
        txtNombreEncontrado.setText("");
        cmbPoderEncontrado.setSelectedIndex(0);
        cmbUniversoEncontrado.setSelectedIndex(0);
        cmbNivelEncontrado.setSelectedIndex(0);
    }

    private void actualizarTablaPrincipal() {
        modeloTabla.setRowCount(0);
        for(SpiderverseHero heroe : manager.getHeroes()) {
            modeloTabla.addRow(new Object[]{
                    heroe.getCodigo(),
                    heroe.getNombre(),
                    heroe.getPoderEspecial(),
                    heroe.getUniverso(),
                    heroe.getNivelExperiencia()
            });
        }
    }
}