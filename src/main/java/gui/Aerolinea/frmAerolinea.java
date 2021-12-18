package gui.Aerolinea;

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

public class frmAerolinea extends JFrame {

    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JPanel jpaContain;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JLabel lblTitulo;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblCodigo;
    private JLabel lblTelefonoC;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JTextField txtTelC;
    private JTextField txtId;
    private JLabel lblDireccion;
    private JLabel lblPagWeb;
    private JLabel lblUbicacion;
    private JLabel lblTelefonoA;
    private JTextField txtDirec;
    private JTextField txtPagWeb;
    private JTextField txtUbicacion;
    private JTextField txtTelA;
    private JLabel lblAerolinea;
    private JComboBox cboAerolinea;
    private JLabel lblImagen2;
    private JLabel lblImagen1;
    private JButton btnBuscarId;
    private JButton btnbuscarNombre;
    DefaultTableModel modelo = new DefaultTableModel();
    static final String URL = "http://192.168.1.3:8080/api/v1/aerolineas";
    public frmAerolinea (){
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addAerolinea");
                    Invocation.Builder solicitud = target.request();
                    Aerolinea aerolinea = new Aerolinea();
                    aerolinea.setNombre(txtNombre.getText());
                    aerolinea.setCodigo(txtCodigo.getText());
                    aerolinea.setTelefonoCarga(Long.parseLong(txtTelC.getText()));
                    aerolinea.setDireccion(txtDirec.getText());
                    aerolinea.setPaginaWeb(txtPagWeb.getText());
                    aerolinea.setUbicacionAeropuerto(txtUbicacion.getText());
                    aerolinea.setTelefonoAtencion(Long.parseLong(txtTelA.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(aerolinea);
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
                    Aerolinea aerolinea = new Aerolinea();
                    aerolinea.setId(Long.parseLong(txtId.getText()));
                    aerolinea.setNombre(txtNombre.getText());
                    aerolinea.setCodigo(txtCodigo.getText());
                    aerolinea.setTelefonoCarga(Long.parseLong(txtTelC.getText()));
                    aerolinea.setDireccion(txtDirec.getText());
                    aerolinea.setPaginaWeb(txtPagWeb.getText());
                    aerolinea.setUbicacionAeropuerto(txtUbicacion.getText());
                    aerolinea.setTelefonoAtencion(Long.parseLong(txtTelA.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(aerolinea);
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
                    Object obj = cboAerolinea.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID de la Aerolínea : " + item,"Detalles de la Aerolínea",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtCodigo.setText("");
                txtTelA.setText("");
                txtNombre.setText("");
                txtDirec.setText("");
                txtPagWeb.setText("");
                txtUbicacion.setText("");
                txtTelC.setText("");
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtCodigo.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtTelC.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtDirec.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtPagWeb.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    txtUbicacion.setText(modelo.getValueAt(filaSeleccionada,6).toString());
                    txtTelA.setText(modelo.getValueAt(filaSeleccionada,7).toString());
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
                    String id = JOptionPane.showInputDialog(null,"Ingrese el ID de la Aerolínea a buscar","Búsqueda",JOptionPane.QUESTION_MESSAGE);
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Aerolinea data = new Gson().fromJson(responseJson,Aerolinea.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getCodigo(),
                                data.getTelefonoCarga(),
                                data.getDireccion(),
                                data.getPaginaWeb(),
                                data.getUbicacionAeropuerto(),
                                data.getTelefonoAtencion()
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
        btnbuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/nombre/" + txtNombre.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Aerolinea data = new Gson().fromJson(responseJson,Aerolinea.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getNombre(),
                                data.getCodigo(),
                                data.getTelefonoCarga(),
                                data.getDireccion(),
                                data.getPaginaWeb(),
                                data.getUbicacionAeropuerto(),
                                data.getTelefonoAtencion()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Nombre de la Aerolínea");
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
        setTitle("Aerolineas");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoAerolineas.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/aerolinea.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/aerolinea2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        btnbuscarNombre.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
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
        modelo.addColumn("Código");
        modelo.addColumn("Teléfono de Carga");
        modelo.addColumn("Dirección");
        modelo.addColumn("Página Web");
        modelo.addColumn("Ubicación Aeropuerto");
        modelo.addColumn("Teléfono  de Atención");
        leerDatos();
    }

    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Aerolinea> data = new Gson().fromJson(responseJson,new TypeToken<List<Aerolinea>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Aerolinea aerolinea: data) {
                    Object [] registro ={
                            aerolinea.getId(),
                            aerolinea.getNombre(),
                            aerolinea.getCodigo(),
                            aerolinea.getTelefonoCarga(),
                            aerolinea.getDireccion(),
                            aerolinea.getPaginaWeb(),
                            aerolinea.getUbicacionAeropuerto(),
                            aerolinea.getTelefonoAtencion()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(aerolinea.getId(),aerolinea.getNombre());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboAerolinea.setModel(modeloCombo);
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

