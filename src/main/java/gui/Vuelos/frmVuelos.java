package gui.Vuelos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.awt.event.*;
import java.util.List;

public class frmVuelos extends JFrame{
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
    private JButton btnBuscar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JLabel lblId;
    private JLabel lblFecha;
    private JLabel lblPuntoP;
    private JLabel lblHoraS;
    private JTextField txtId;
    private JTextField txtFecha;
    private JTextField txtLugarP;
    private JLabel lblDestino;
    private JLabel lblAerolinea;
    private JLabel lblTiempoE;
    private JTextField txtDestino;
    private JLabel lblDescrpcion;
    private JTextField txtDescripcion;
    private JLabel lblVuelo;
    private JComboBox cboVuelo;
    private JComboBox cboAerolinea;
    private JButton btnBuscarId;
    private JButton btnBuscarDestino;
    private JTextField txtHoraP;
    private JTextField txtTiempoE;
    DefaultTableModel modelo = new DefaultTableModel();
    static final String URL = "http://192.168.1.3:8080/api/v1/vuelos";
    static final String URL2 = "http://192.168.1.3:8080/api/v1/aerolineas";
    public frmVuelos (){
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addVuelo");
                    Invocation.Builder solicitud = target.request();
                    Vuelos vuelos = new Vuelos();
                    vuelos.setFecha(txtFecha.getText());
                    vuelos.setLugarPartida(txtLugarP.getText());
                    vuelos.setHoraP(txtHoraP.getText());
                    vuelos.setDestino(txtDestino.getText());
                    vuelos.setAerolinea(cboAerolinea.getSelectedItem().toString());
                    vuelos.setTiempoE(txtTiempoE.getText());
                    vuelos.setDescripcion(txtDescripcion.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vuelos);
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
                    Vuelos vuelos = new Vuelos();
                    vuelos.setId(Long.parseLong(txtId.getText()));
                    vuelos.setFecha(txtFecha.getText());
                    vuelos.setLugarPartida(txtLugarP.getText());
                    vuelos.setHoraP(txtHoraP.getText());
                    vuelos.setDestino(txtDestino.getText());
                    vuelos.setAerolinea(cboAerolinea.getSelectedItem().toString());
                    vuelos.setTiempoE(txtTiempoE.getText());
                    vuelos.setDescripcion(txtDescripcion.getText());
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(vuelos);
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
                    Object obj = cboVuelo.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del Vuelo : " + item,"Detalles del Vuelo",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setText("");
                txtFecha.setText("");
                txtLugarP.setText("");
                txtHoraP.setText("");
                txtDestino.setText("");
                txtTiempoE.setText("");
                txtDescripcion.setText("");
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtId.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtFecha.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    txtLugarP.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                    txtHoraP.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtDestino.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    cboAerolinea.setSelectedItem(modelo.getValueAt(filaSeleccionada,5).toString());
                    txtTiempoE.setText(modelo.getValueAt(filaSeleccionada,6).toString());
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
                    String id = JOptionPane.showInputDialog(null,"Ingrese el ID del Vuelo a buscar","Búsqueda",JOptionPane.QUESTION_MESSAGE);
                    WebTarget target = client.target(URL+"/id/" + id);
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Vuelos data = new Gson().fromJson(responseJson,Vuelos.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getFecha(),
                                data.getLugarPartida(),
                                data.getHoraP(),
                                data.getDestino(),
                                data.getAerolinea(),
                                data.getTiempoE(),
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
        btnBuscarDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/destino/" + txtDestino.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Vuelos data = new Gson().fromJson(responseJson,Vuelos.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getFecha(),
                                data.getLugarPartida(),
                                data.getHoraP(),
                                data.getDestino(),
                                data.getAerolinea(),
                                data.getTiempoE(),
                                data.getDescripcion()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el Destino de el Vuelo");
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
        setTitle("Vuelos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //place holders
        TextPrompt titulos = new TextPrompt("00:00:00",txtHoraP);
        TextPrompt titulo = new TextPrompt("yyyy-MM-dd.",txtFecha);
        TextPrompt titulos2 = new TextPrompt("00:00:00",txtTiempoE);
        titulos.changeAlpha(0.75f);
        titulo.changeAlpha(0.75f);
        titulos2.changeAlpha(0.75f);
        //Icono Ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/loginPrincipal/iconoVuelos.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/vuelos.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/vuelos2.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar
        btnBuscarDestino.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        btnBuscarId.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Lugar de Partida");
        modelo.addColumn("Hora Programada");
        modelo.addColumn("Destino");
        modelo.addColumn("Aerolínea");
        modelo.addColumn("Tiempo Estimado");
        modelo.addColumn("Descripción");
        leerDatos();
        llenarCboAerolinea();
    }

    private void llenarCboAerolinea() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Aerolinea> data = new Gson().fromJson(responseJson,new TypeToken<List<Aerolinea>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Aerolinea aerolinea: data) {
                    //CBO
                    Item item = new Item(aerolinea.getId(),aerolinea.getNombre());
                    modeloCombo.addElement(item);
                }
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

    private void leerDatos() {
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Vuelos> data = new Gson().fromJson(responseJson,new TypeToken<List<Vuelos>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Vuelos vuelos: data) {
                    Object [] registro ={
                            vuelos.getId(),
                            vuelos.getFecha(),
                            vuelos.getLugarPartida(),
                            vuelos.getHoraP(),
                            vuelos.getDestino(),
                            vuelos.getAerolinea(),
                            vuelos.getTiempoE(),
                            vuelos.getDescripcion()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(vuelos.getId(),vuelos.getDestino());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboVuelo.setModel(modeloCombo);
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
