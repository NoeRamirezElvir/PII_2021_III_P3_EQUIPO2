package gui.Pasajero;
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

public class frmPasajero extends JFrame{
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblTitulo;
    private JPanel jpaContenido;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JTextField txttelefono;
    private JComboBox cboCategoria;
    private JComboBox cboTipoDoc;
    private JTextField txtNacionalidad;
    private JComboBox cboPasajeros;
    private JLabel lblID;
    private JLabel lblCodigo;
    private JLabel lblCategoria;
    private JLabel lblTipoDoc;
    private JLabel lblTelefono;
    private JLabel lblIDDoc;
    private JLabel lblNacionalidad;
    private JLabel lblPasajeros;
    private JLabel lblNombre;
    private JComboBox cboNombre;
    private JTextField txtIdDoc;
    private JPanel jpaDatos;
    private JLabel lblImagen2;
    private JLabel lblImagen;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    private JPanel jpaBotones;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarID;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JButton btnBuscarNombre;
    DefaultTableModel modelo;
    static final String URL = "http://192.168.0.15:8080/api/v1/pasajeros";
    static final String URL2 = "http://192.168.0.15:8080/api/v1/personas";


    public frmPasajero(){
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addPasajero");
                    Invocation.Builder solicitud = target.request();
                    Pasajero pasajero = new Pasajero();
                    pasajero.setNombre(String.valueOf(cboNombre.getSelectedItem()));
                    pasajero.setCodigo(txtCodigo.getText());
                    pasajero.setCategoria(String.valueOf(cboCategoria.getSelectedItem()));
                    pasajero.setTelefono(Long.parseLong(txttelefono.getText()));
                    pasajero.setTipoDocumento(String.valueOf(cboTipoDoc.getSelectedItem()));
                    pasajero.setIdDocumento(txtIdDoc.getText());
                    pasajero.setNacionalidad(txtNacionalidad.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(pasajero);
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
                    Pasajero pasajero = new Pasajero();
                    pasajero.setId(Long.parseLong(txtID.getText()));
                    pasajero.setNombre(String.valueOf(cboNombre.getSelectedItem()));
                    pasajero.setCodigo(txtCodigo.getText());
                    pasajero.setCategoria(String.valueOf(cboCategoria.getSelectedItem()));
                    pasajero.setTelefono(Long.parseLong(txttelefono.getText()));
                    pasajero.setTipoDocumento(String.valueOf(cboTipoDoc.getSelectedItem()));
                    pasajero.setIdDocumento(txtIdDoc.getText());
                    pasajero.setNacionalidad(txtNacionalidad.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(pasajero);
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
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog("¿Cuál es el ID a buscar?");
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Pasajero data = new Gson().fromJson(responseJson,Pasajero.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getCodigo(),
                                data.getCategoria(),
                                data.getTelefono(),
                                data.getTipoDocumento(),
                                data.getIdDocumento(),
                                data.getNacionalidad()
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
                    WebTarget target = client.target(URL+"/nombre/" + cboNombre.getSelectedItem().toString());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Pasajero data = new Gson().fromJson(responseJson,Pasajero.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getCodigo(),
                                data.getCategoria(),
                                data.getTelefono(),
                                data.getTipoDocumento(),
                                data.getIdDocumento(),
                                data.getNacionalidad()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Nombre");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
        btnLeerCBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object obj = cboPasajeros.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del Pasajero: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    cboNombre.setSelectedItem(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtCodigo.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    cboCategoria.setSelectedItem(modelo.getValueAt(filaSeleccionada,3).toString());
                    txttelefono.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    cboTipoDoc.setSelectedItem(modelo.getValueAt(filaSeleccionada,5).toString());
                    txtIdDoc.setText(modelo.getValueAt(filaSeleccionada,6).toString());
                    txtNacionalidad.setText(modelo.getValueAt(filaSeleccionada,7).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void iniciar(){
        setTitle("Registro de Pasajeros");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoRegistrarPersonas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenCliente1.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/imagenCliente2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoRegistrarPersonas.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoActualizarPersonas.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoEliminarPersonas.png"));
        //boton buscar ID
        btnBuscarID.setIcon(new ImageIcon("src/main/java/recursos/imagenes/ClienteEmpleado/iconoBuscarPersonas.png"));
        //boton buscar Nombre
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Tipo de Documento");
        modelo.addColumn("ID del Documento");
        modelo.addColumn("Nacionalidad");
        leerDatos();
        llenarCboNombre();
        limpiar();
    }
    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget target = client.target(URL + "");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Pasajero> data = new Gson().fromJson(responseJson, new TypeToken<List<Pasajero>>() {
            }.getType());
            if (get.getStatus() == 200) {
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Pasajero pasajero : data) {
                    Object[] registro = {
                            pasajero.getId(),
                            pasajero.getNombre(),
                            pasajero.getCodigo(),
                            pasajero.getCategoria(),
                            pasajero.getTelefono(),
                            pasajero.getTipoDocumento(),
                            pasajero.getIdDocumento(),
                            pasajero.getNacionalidad()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(pasajero.getId(), pasajero.getNombre());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboPasajeros.setModel(modeloCombo);
            } else {
                throw new Exception("> No se cargaron los datos.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            client.close();
        }
    }
    private void llenarCboNombre(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Persona> data = new Gson().fromJson(responseJson,new TypeToken<List<Persona>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Persona persona: data) {
                   if(persona.getTipo().equals("Cliente")){
                        modeloCombo.addElement(persona.getNombre());
                   }
                }
                cboNombre.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de personas.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }
    private void limpiar(){
        txtID.setText("0");
        txtCodigo.setText("");
        txttelefono.setText("0");
        txtIdDoc.setText("");
        txtNacionalidad.setText("");
    }
}
