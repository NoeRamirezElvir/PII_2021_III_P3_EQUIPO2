package gui.Principal;

import gui.Aerolinea.frmAerolinea;
import gui.Equipaje.frmEquipaje;
import gui.LocalComercial.frmLocalComercial;
import gui.NuevoUsuario.frmNuevoUsuario;
import gui.Persona.frmPersona;
import gui.Pasajero.frmPasajero;
import gui.Empleado.frmEmpleado;
import gui.Aeronave.frmAeronave;
import gui.VehiculoAeroportuario.frmVehiculoAeroportuario;
import gui.Boleto.frmBoleto;
import gui.Vuelos.frmVuelos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmMenuPrincipal extends JFrame{
    private JLabel lblFondo;

    private JPanel jpaPrincipal;

    public frmMenuPrincipal() {
        this.setTitle("Menú Principal");
        this.setSize(1190,730);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icono = new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoPrincipal.png");
        this.setIconImage(icono.getImage());
        //Fondo y Componentes
        //Fondo
        lblFondo = new JLabel(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/principal2.png"));
        lblFondo.setSize(900,500);
        //Titulo
        JLabel lblTitulo = new JLabel("International Airport UJCV");
        lblTitulo.setForeground(new Color(16,65,59));
        lblTitulo.setFont(new Font("Berlin Sans FB Demi",Font.BOLD,48));
        lblTitulo.setBounds(330,50,600,80);
        lblFondo.add(lblTitulo);
        //-----
        String nombre = "Admin";
        JLabel lblBienvenida = new JLabel("Bienvenido: "+nombre);
        lblBienvenida.setForeground(new Color(255,255,255));
        lblBienvenida.setFont(new Font("Berlin Sans FB Demi",Font.BOLD,24));
        lblBienvenida.setBounds(40,10,400,80);
        lblFondo.add(lblBienvenida);
        //Botones
        //Persona-----------------------
        JButton btnPersona = new JButton("Persona");
        btnPersona.setBounds(200,250,225,40);
        btnPersona.setForeground(new Color(255,255,255));
        btnPersona.setBackground(new Color(16, 65, 59));
        btnPersona.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoP.png"));
        lblFondo.add(btnPersona);

        //Pasajero------------------------
        JButton btnPasajero = new JButton("Pasajero");
        btnPasajero.setBounds(200,310,225,40);
        btnPasajero.setForeground(new Color(255,255,255));
        btnPasajero.setBackground(new Color(16, 65, 59));
        btnPasajero.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoPa.png"));
        lblFondo.add(btnPasajero);

        //Empleado-------------------------
        JButton btnEmpleado = new JButton("Empleado");
        btnEmpleado.setBounds(200,370,225,40);
        btnEmpleado.setForeground(new Color(255,255,255));
        btnEmpleado.setBackground(new Color(16, 65, 59));
        btnEmpleado.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoE.png"));
        lblFondo.add(btnEmpleado);

        //Equipaje-------------------------
        JButton btnEquipaje = new JButton("Equipajes");
        btnEquipaje.setBounds(200,430,225,40);
        btnEquipaje.setForeground(new Color(255,255,255));
        btnEquipaje.setBackground(new Color(16, 65, 59));
        btnEquipaje.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoEquipaje.png"));
        lblFondo.add(btnEquipaje);

        //Aerolinea-------------------------
        JButton btnAerolinea = new JButton("Aerolíneas");
        btnAerolinea.setBounds(500,250,225,40);
        btnAerolinea.setForeground(new Color(255,255,255));
        btnAerolinea.setBackground(new Color(16, 65, 59));
        btnAerolinea.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoAerolineas.png"));
        lblFondo.add(btnAerolinea);

        //Vuelo-------------------------
        JButton btnVuelo = new JButton("Vuelos");
        btnVuelo.setBounds(500,310,225,40);
        btnVuelo.setForeground(new Color(255,255,255));
        btnVuelo.setBackground(new Color(16, 65, 59));
        btnVuelo.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoVuelos.png"));
        lblFondo.add(btnVuelo);

        //Aeronave-------------------------
        JButton btnAeronave = new JButton("Aeronaves");
        btnAeronave.setBounds(500,370,225,40);
        btnAeronave.setForeground(new Color(255,255,255));
        btnAeronave.setBackground(new Color(16, 65, 59));
        btnAeronave.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoAeronave.png"));
        lblFondo.add(btnAeronave);

        //Vehiculo-------------------------
        JButton btnVehiculo = new JButton("Vehículo Aeroportuario");
        btnVehiculo.setBounds(500,430,225,40);
        btnVehiculo.setForeground(new Color(255,255,255));
        btnVehiculo.setBackground(new Color(16, 65, 59));
        btnVehiculo.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoVehiculo.png"));
        lblFondo.add(btnVehiculo);

        //Locales-------------------------
        JButton btnLocales = new JButton("Locales Comerciales");
        btnLocales.setBounds(800,250,225,40);
        btnLocales.setForeground(new Color(255,255,255));
        btnLocales.setBackground(new Color(16, 65, 59));
        btnLocales.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoLocales.png"));
        lblFondo.add(btnLocales);

        //Boleto-------------------------
        JButton btnBoleto = new JButton("Boletos");
        btnBoleto.setBounds(800,310,225,40);
        btnBoleto.setForeground(new Color(255,255,255));
        btnBoleto.setBackground(new Color(16, 65, 59));
        btnBoleto.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoT.png"));
        lblFondo.add(btnBoleto);

        //Usuario-------------------------
        JButton btnUsuario = new JButton("Usuarios");
        btnUsuario.setBounds(800,370,225,40);
        btnUsuario.setForeground(new Color(255,255,255));
        btnUsuario.setBackground(new Color(16, 65, 59));
        btnUsuario.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoUsuarios.png"));
        lblFondo.add(btnUsuario);
        //
        //salir-------------------------
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(915,635,110,40);
        btnSalir.setForeground(new Color(255,255,255));
        btnSalir.setBackground(new Color(16, 65, 59));
        btnSalir.setIcon(new ImageIcon("src/main/java/Recursos/imagenes/loginPrincipal/iconoSalir.png"));
        lblFondo.add(btnSalir);
        //
        this.setContentPane(lblFondo);

        //LISTENERS
        btnPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPersona frame = new frmPersona();
                frame.setVisible(true);
            }
        });
        btnPasajero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPasajero frame = new frmPasajero();
                frame.setVisible(true);
            }
        });
        btnEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmEmpleado frame = new frmEmpleado();
                frame.setVisible(true);
            }
        });
        btnEquipaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmEquipaje frame = new frmEquipaje();
                frame.setVisible(true);
            }
        });
        btnAerolinea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAerolinea frame = new frmAerolinea();
                frame.setVisible(true);
            }
        });
        btnVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmVuelos frame = new frmVuelos();
                frame.setVisible(true);
            }
        });
        btnAeronave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmAeronave frame = new frmAeronave();
                frame.setVisible(true);
            }
        });
        btnVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmVehiculoAeroportuario frame = new frmVehiculoAeroportuario();
                frame.setVisible(true);
            }
        });
        btnLocales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmLocalComercial frame = new frmLocalComercial();
                frame.setVisible(true);
            }
        });
        btnBoleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmBoleto frame = new frmBoleto();
                frame.setVisible(true);
            }
        });
        btnUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmNuevoUsuario frame = new frmNuevoUsuario();
                frame.setVisible(true);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}