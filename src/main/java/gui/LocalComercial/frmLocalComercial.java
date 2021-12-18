package gui.LocalComercial;

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

public class frmLocalComercial extends JFrame {
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContain;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JLabel lblTitulo;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblTelefono;
    private JLabel lblFechaI;
    private JLabel lblLocal;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtFecha;
    private JComboBox cboLocales;
    private JLabel lblCorreo;
    private JLabel lblPaginaWeb;
    private JLabel lblTipoServicio;
    private JLabel lblDescripcion;
    private JTextField txtCorreo;
    private JTextField txtPagWeb;
    private JTextField txtDescripcion;
    private JComboBox cboTipoServicio;
    private JButton btnBuscarId;
    private JButton btnBuscarNombre;
    DefaultTableModel modelo = new DefaultTableModel();
    static final String URL = "http://192.168.1.3:8080/api/v1/locales";

    public frmLocalComercial (){
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addLocal");
                    Invocation.Builder solicitud = target.request();
                    LocalComercial localComercial = new LocalComercial();
                    localComercial.setNombre(txtNombre.getText());
                    localComercial.setTelefono(Long.parseLong(txtTelefono.getText()));
                    localComercial.setFechaIngreso(txtFecha.getText());
                    localComercial.setCorreoElectronico(txtCorreo.getText());
                    localComercial.setPaginaWeb(txtPagWeb.getText());
                    localComercial.setTipoServicio(cboTipoServicio.getSelectedItem().toString());
                    localComercial.setDescripcion(txtDescripcion.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(localComercial);
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
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"");
                    Invocation.Builder solicitud = target.request();
                    LocalComercial localComercial = new LocalComercial();
                    localComercial.setId(Long.parseLong(txtId.getText()));
                    localComercial.setNombre(txtNombre.getText());
                    localComercial.setTelefono(Long.parseLong(txtTelefono.getText()));
                    localComercial.setFechaIngreso(txtFecha.getText());
                    localComercial.setCorreoElectronico(txtCorreo.getText());
                    localComercial.setPaginaWeb(txtPagWeb.getText());
                    localComercial.setTipoServicio(cboTipoServicio.getSelectedItem().toString());
                    localComercial.setDescripcion(txtDescripcion.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(localComercial);
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
                    WebTarget target = client.target(URL+"/delete/" + txtId.getText());
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
        btnLeerCBO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Object obj = cboLocales.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del Local Comercial : " + item,"Detalles del Local",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtNombre.setText("");
                txtTelefono.setText("");
                txtFecha.setText("");
                txtCorreo.setText("");
                txtPagWeb.setText("");
                txtDescripcion.setText("");
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtTelefono.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtFecha.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtCorreo.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtPagWeb.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    cboTipoServicio.setSelectedItem(modelo.getValueAt(filaSeleccionada,6).toString());
                    txtDescripcion.setText(modelo.getValueAt(filaSeleccionada,7).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBuscarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    String id = JOptionPane.showInputDialog(null,"Ingrese el ID del Local a buscar","Búsqueda",JOptionPane.QUESTION_MESSAGE);
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    LocalComercial data = new Gson().fromJson(responseJson,LocalComercial.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getTelefono(),
                                data.getFechaIngreso(),
                                data.getCorreoElectronico(),
                                data.getPaginaWeb(),
                                data.getTipoServicio(),
                                data.getDescripcion()
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
                    LocalComercial data = new Gson().fromJson(responseJson,LocalComercial.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getTelefono(),
                                data.getFechaIngreso(),
                                data.getCorreoElectronico(),
                                data.getPaginaWeb(),
                                data.getTipoServicio(),
                                data.getDescripcion()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Nombre del Local Comercial");
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
                finally{
                    client.close();
                }
            }
        });
    }

    private void iniciar(){
        setTitle("Locales Comerciales");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //place holders
        TextPrompt titulo = new TextPrompt("yyyy-MM-dd",txtFecha);
        titulo.changeAlpha(0.75f);
        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoLocales.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/local.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/local2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        btnBuscarNombre.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Fecha de Ingreso");
        modelo.addColumn("Correo Electrónico");
        modelo.addColumn("Página Web");
        modelo.addColumn("Tipo de Servicio");
        modelo.addColumn("Descripción");
        leerDatos();
    }

    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<LocalComercial> data = new Gson().fromJson(responseJson,new TypeToken<List<LocalComercial>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (LocalComercial localComercial: data) {
                    Object [] registro ={
                            localComercial.getId(),
                            localComercial.getNombre(),
                            localComercial.getTelefono(),
                            localComercial.getFechaIngreso(),
                            localComercial.getCorreoElectronico(),
                            localComercial.getPaginaWeb(),
                            localComercial.getTipoServicio(),
                            localComercial.getDescripcion()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(localComercial.getId(),localComercial.getNombre());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboLocales.setModel(modeloCombo);
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
}
