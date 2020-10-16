package com.uptc.view;

import com.uptc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameView extends JFrame {

    private Button buttonGetDataCSV;
    private Button buttonDeleteDataBase;
    private Button buttonLoadToCSV;
    private Button buttonShowDataDB;

    private Controller controller;

    public JFrameView() {
        super("Carga de Datos");
        getContentPane().setLayout(new FlowLayout());
        controller = new Controller();
        buttonGetDataCSV = new Button("Poblar Base de Datos desde CVS");
        add(buttonGetDataCSV);
        buttonGetDataCSV.addActionListener(new ButtonGetDataCSV());

        buttonDeleteDataBase = new Button("Eliminar datos de base de datos");
        add(buttonDeleteDataBase);
        buttonDeleteDataBase.addActionListener(new ButtonDeleteDataBase());

        buttonLoadToCSV = new Button("Poblar CSV desde base de datos");
        add(buttonLoadToCSV);
        buttonLoadToCSV.addActionListener(new ButtonLoadToCSV());

        buttonShowDataDB = new Button("Mostrar datos de Base de datos");
        add(buttonShowDataDB);
        buttonShowDataDB.addActionListener(new ButtonShowDataDB());

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonGetDataCSV implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.loadDataCSV("convenio", "src/main/resources/import/convenios.csv");
            controller.loadDataCSV("pasajero", "src/main/resources/import/pasajeros.csv");
            controller.loadDataCSV("tarjeta", "src/main/resources/import/tarjetas.csv");
            controller.loadDataCSV("vehiculo", "src/main/resources/import/vehiculos.csv");
            controller.loadDataCSV("viaje", "src/main/resources/import/viajes.csv");
            controller.loadDataCSV("ruta", "src/main/resources/import/rutas.csv");
            controller.loadDataCSV("estacion", "src/main/resources/import/estaciones.csv");
        }
    }

    private class ButtonDeleteDataBase implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.deleteTable();
        }
    }

    private class ButtonLoadToCSV implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.writeDataCSV("convenio","src/main/resources/export/convenios.csv");
            controller.writeDataCSV("pasajero","src/main/resources/export/pasajeros.csv");
            controller.writeDataCSV("tarjeta","src/main/resources/export/tarjetas.csv");
            controller.writeDataCSV("vehiculo","src/main/resources/export/vehiculos.csv");
            controller.writeDataCSV("viaje","src/main/resources/export/viajes.csv");
            controller.writeDataCSV("ruta","src/main/resources/export/rutas.csv");
            controller.writeDataCSV("estacion","src/main/resources/export/estaciones.csv");
        }
    }

    private class ButtonShowDataDB implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.readDataCSV("convenio");
            controller.readDataCSV("pasajero");
            controller.readDataCSV("tarjeta");
            controller.readDataCSV("vehiculo");
            controller.readDataCSV("viaje");
            controller.readDataCSV("ruta");
            controller.readDataCSV("estacion");
        }
    }



}
