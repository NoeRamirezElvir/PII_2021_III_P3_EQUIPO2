package gui.Boleto;
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


public class frmBoleto extends JFrame{
    private JPanel jpaPrincipal;
    private JTextField txtID;
    private JTextField txtCodigo;
    private JComboBox cboPasajero;
    private JComboBox cboVuelo;
    private JTextField txtAsiento;
    private JTextField txtPuertaEmbarque;
    private JComboBox cboClase;
    private JTextField txtTotal;
    private JComboBox cboBoletos;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscarID;
    private JButton btnListar;
    private JButton btnLeerCBO;
    private JButton btnLimpiar;
    private JTable tblDatos;
    private JLabel lblImagen1;
    private JLabel lblImagen2;
    private JLabel lblTitulo;
    private JPanel jpaTitulo;
    private JPanel jpaContenido;
    private JPanel jpaBotones;
    private JPanel jpaDatos;
    private JScrollPane sclPanDatos;
    private JLabel lblID;
    private JLabel lblCodigoFactura;
    private JLabel lblPasajero;
    private JLabel lblVuelo;
    private JLabel lblAsiento;
    private JLabel lblPuertaEmbarque;
    private JLabel lblClase;
    private JLabel lblTotal;
    private JLabel lblBoletos;
    private JButton btnBuscarCodigo;
    DefaultTableModel modelo;
    static final String URL = "http://192.168.0.15:8080/api/v1/boletos";
    static final String URL2 = "http://192.168.0.15:8080/api/v1/pasajeros";
    static final String URL3 = "http://192.168.0.15:8080/api/v1/vuelos";

    public frmBoleto() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/addBoleto");
                    Invocation.Builder solicitud = target.request();
                    Boleto boleto = new Boleto();
                    boleto.setCodigoFactura(txtCodigo.getText());
                    boleto.setNombrePasajero(String.valueOf(cboPasajero.getSelectedItem()));
                    boleto.setVuelo(String.valueOf(cboVuelo.getSelectedItem()));
                    boleto.setAsiento(txtAsiento.getText());
                    boleto.setNumeroPuertaEmbarque(txtPuertaEmbarque.getText());
                    boleto.setClase(String.valueOf(cboClase.getSelectedItem()));
                    boleto.setTotal(Double.parseDouble(txtTotal.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(boleto);
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
                    Boleto boleto = new Boleto();
                    boleto.setId(Long.parseLong(txtID.getText()));
                    boleto.setCodigoFactura(txtCodigo.getText());
                    boleto.setNombrePasajero(String.valueOf(cboPasajero.getSelectedItem()));
                    boleto.setVuelo(String.valueOf(cboVuelo.getSelectedItem()));
                    boleto.setAsiento(txtAsiento.getText());
                    boleto.setNumeroPuertaEmbarque(txtPuertaEmbarque.getText());
                    boleto.setClase(String.valueOf(cboClase.getSelectedItem()));
                    boleto.setTotal(Double.parseDouble(txtTotal.getText()));
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(boleto);
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
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    int filaSeleccionada = tblDatos.getSelectedRow();
                    txtID.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                    txtCodigo.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                    cboPasajero.setSelectedItem(modelo.getValueAt(filaSeleccionada,2).toString());
                    cboVuelo.setSelectedItem(modelo.getValueAt(filaSeleccionada,3).toString());
                    txtAsiento.setText(modelo.getValueAt(filaSeleccionada,4).toString());
                    txtPuertaEmbarque.setText(modelo.getValueAt(filaSeleccionada,5).toString());
                    cboClase.setSelectedItem(modelo.getValueAt(filaSeleccionada,6).toString());
                    txtTotal.setText(modelo.getValueAt(filaSeleccionada,7).toString());
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
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
                    Boleto data = new Gson().fromJson(responseJson,Boleto.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigoFactura(),
                                data.getNombrePasajero(),
                                data.getVuelo(),
                                data.getAsiento(),
                                data.getNumeroPuertaEmbarque(),
                                data.getClase(),
                                data.getTotal()
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
        btnBuscarCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = ClientBuilder.newClient();
                try{
                    WebTarget target = client.target(URL+"/codigoFactura/" + txtCodigo.getText());
                    Invocation.Builder solicitud = target.request();
                    Response get = solicitud.get();
                    String responseJson = get.readEntity(String.class);
                    Boleto data = new Gson().fromJson(responseJson,Boleto.class);
                    if(get.getStatus() == 200){
                        modelo.setRowCount(0);
                        Object [] registro ={
                                data.getId(),
                                data.getCodigoFactura(),
                                data.getNombrePasajero(),
                                data.getVuelo(),
                                data.getAsiento(),
                                data.getNumeroPuertaEmbarque(),
                                data.getClase(),
                                data.getTotal()
                        };
                        modelo.addRow(registro);
                        tblDatos.setModel(modelo);
                    }else{
                        throw new Exception("> No se encontró el código");
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
                    Object obj = cboBoletos.getSelectedItem();
                    long item = ((Item)obj).getID();
                    JOptionPane.showMessageDialog(null,"ID del Boleto: " + item,"INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
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
        setTitle("Registro de Boletos");
        setContentPane(this.jpaPrincipal);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        //Icono ventana
        ImageIcon imagen = new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoTicket.png");
        setIconImage(imagen.getImage());
        //Imagenes
        lblImagen1.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenTicketBoleto.png"));
        lblImagen2.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/imagenMaletaBoleto.png"));
        //Botones
        //boton registrar
        btnRegistrar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoRegistrar.png"));
        //boton actualizar
        btnActualizar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoActualizar.png"));
        //boton eliminar
        btnEliminar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoEliminar.png"));
        //boton buscar ID
        btnBuscarID.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton buscar Codigo
        btnBuscarCodigo.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoBuscar.png"));
        //boton listar
        btnListar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoListar.png"));
        //boton leerCBO
        btnLeerCBO.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLeerCBO.png"));
        //boton limpiar
        btnLimpiar.setIcon(new ImageIcon("src/main/java/recursos/imagenes/AVB/iconoLimpiarAVB.png"));
        //-----------------------------------------------------------------------------------
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("ID");
        modelo.addColumn("Código de Factura");
        modelo.addColumn("Nombre de Pasajero");
        modelo.addColumn("Vuelo");
        modelo.addColumn("Asiento");
        modelo.addColumn("Puerta de Embarque");
        modelo.addColumn("Clase");
        modelo.addColumn("Total a Pagar");
        leerDatos();
        llenarCboPasajeros();
        llenarCboVuelos();
        limpiar();
    }
    private void leerDatos(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Boleto> data = new Gson().fromJson(responseJson,new TypeToken<List<Boleto>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                modelo.setRowCount(0);
                for (Boleto boleto: data) {
                    Object [] registro ={
                            boleto.getId(),
                            boleto.getCodigoFactura(),
                            boleto.getNombrePasajero(),
                            boleto.getVuelo(),
                            boleto.getAsiento(),
                            boleto.getNumeroPuertaEmbarque(),
                            boleto.getClase(),
                            boleto.getTotal()
                    };
                    modelo.addRow(registro);
                    //CBO
                    Item item = new Item(boleto.getId(),boleto.getCodigoFactura());
                    modeloCombo.addElement(item);
                }
                tblDatos.setModel(modelo);
                cboBoletos.setModel(modeloCombo);
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
    private void llenarCboPasajeros(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL2+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Pasajero> data = new Gson().fromJson(responseJson,new TypeToken<List<Pasajero>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Pasajero pasajero: data) {
                    modeloCombo.addElement(pasajero.getNombre());
                }
                cboPasajero.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de los pasajeros.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        finally{
            client.close();
        }
    }
    private void llenarCboVuelos(){
        Client client = ClientBuilder.newClient();
        try{
            WebTarget target = client.target(URL3+"");
            Invocation.Builder solicitud = target.request();
            Response get = solicitud.get();
            String responseJson = get.readEntity(String.class);
            List<Vuelos> data = new Gson().fromJson(responseJson,new TypeToken<List<Vuelos>>(){}.getType());
            if(get.getStatus() == 200){
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
                for (Vuelos vuelo: data) {
                    modeloCombo.addElement(vuelo.getDestino());
                }
                cboVuelo.setModel(modeloCombo);
            }else{
                throw new Exception("No se cargaron los datos de los Vuelos.");
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
        txtAsiento.setText("");
        txtPuertaEmbarque.setText("");
        txtTotal.setText("0");
    }


}
