package gui.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmPersona extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JLabel lblTitulo;
    private JTextField txtID;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtDireccion;
    private JComboBox cboGenero;
    private JComboBox cboTipo;
    private JLabel lblID;
    private JLabel lblDNI;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblDireccion;
    private JLabel lblGenero;
    private JLabel lblTipo;
    private JLabel lblPersonas;
    private JComboBox cboPersonas;
    private JPanel jpaDatos;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JPanel jpaBotones;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarId;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JButton btnBuscarNombre;
    DefaultTableModel modelo;
    static final String URL = "http://192.168.1.3:8080/api/v1/personas";

    public frmPersona() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addPersona");
                    Invocation.Builder solicitud = target.request();
                    Persona persona = new Persona();
                    persona.setDni(Long.parseLong(txtDNI.getText()));
                    persona.setNombre(txtNombre.getText());
                    persona.setEdad(Integer.parseInt(txtEdad.getText()));
                    persona.setDireccion(txtDireccion.getText());
                    persona.setGenero(String.valueOf(cboGenero.getSelectedItem()));
                    persona.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(persona);
                    Response post = solicitud.post(Entity.json(jsonString));
                    String responseJson = post.readEntity(String.class);
                    if(post.getStatus() == 201){
                        JOptionPane.showMessageDialog(null,"Agregado correctamente","GUARDADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else if(post.getStatus() == 500){
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    Persona persona = new Persona();
                    persona.setId(Long.parseLong(txtID.getText()));
                    persona.setDni(Long.parseLong(txtDNI.getText()));
                    persona.setNombre(txtNombre.getText());
                    persona.setEdad(Integer.parseInt(txtEdad.getText()));
                    persona.setDireccion(txtDireccion.getText());
                    persona.setGenero(String.valueOf(cboGenero.getSelectedItem()));
                    persona.setTipo(String.valueOf(cboTipo.getSelectedItem()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(persona);
                    Response put = solicitud.put(Entity.json(jsonString));
                    String responseJson = put.readEntity(String.class);
                    if(put.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Actualizado correctamente","ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        RestApiError apiError = new Gson().fromJson(responseJson, RestApiError.class);
                        throw new Exception(apiError.getErrorDetails());
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/delete/" + txtID.getText());
                    Invocation.Builder solicitud = target.request();
                    Response post = solicitud.delete();
                    if(post.getStatus() == 200){
                        JOptionPane.showMessageDialog(null,"Eliminado correctamente","ELIMINADO",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                    }else{
                        throw new Exception("> No se encontró el objeto");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog("¿Cuál es el ID a buscar?");
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Persona data = new Gson().fromJson(responseJson,Persona.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getDni(),
                                data.getNombre(),
                                data.getEdad(),
                                data.getDireccion(),
                                data.getGenero(),
                                data.getTipo()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el ID");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/nombre/" + txtNombre.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Persona data = new Gson().fromJson(responseJson,Persona.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getDni(),
                                data.getNombre(),
                                data.getEdad(),
                                data.getDireccion(),
                                data.getGenero(),
                                data.getTipo()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el nombre");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtDNI.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtNombre.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtEdad.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtDireccion.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    cboGenero.setSelectedItem(modelo.getValueAt(filaSeleccionada,5).toString());
                    cboTipo.setSelectedItem(modelo.getValueAt(filaSeleccionada,6).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLeerCBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object obj = cboPersonas.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID de la persona: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de Personas");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoActualizarPersonas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenPersona2.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenPersona.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoRegistrarPersonas.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoActualizarPersonas.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoEliminarPersonas.png"));
        //boton buscar id
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
        //boton buscar nombre
        btnBuscarNombre.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoLeerCBOPersonas.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoLimpiarPersonas.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Dirección");
        modelo.addColumn("Género");
        modelo.addColumn("Tipo");
        leerDatos();
        limpiar();
    }
    private void leerDatos(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Persona> data = new Gson().fromJson(responseJson,new TypeToken<List<Persona>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Persona persona: data) {
                    Object [] registro ={
                            persona.getId(),
                            persona.getDni(),
                            persona.getNombre(),
                            persona.getEdad(),
                            persona.getDireccion(),
                            persona.getGenero(),
                            persona.getTipo()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(persona.getId(),persona.getNombre());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboPersonas.setModel(modeloCombo);
            }else{
                throw new Exception("> No se cargaron los datos.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally {
            client.close();
        }
    }
    private void limpiar(){
        txtID.setText("0");
        txtDNI.setText("");
        txtNombre.setText("");
        txtEdad.setText("0");
        txtDireccion.setText("");
    }
}
