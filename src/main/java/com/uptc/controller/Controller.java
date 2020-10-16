package com.uptc.controller;

import model.tables.Vehiculo;
import model.tables.records.*;
import org.jooq.DSLContext;
import org.jooq.Loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static model.Tables.*;

public class Controller {

    public Controller(){

    }

    public void loadDataCSV(DSLContext create, String routeFile, String typeObject) throws IOException {
        InputStream inputstream = new FileInputStream(routeFile);

        switch(typeObject){
            case "convenio":

                break;
            case "estacion":

                break;
            case "pasajero":

                break;
            case "ruta":

                break;
            case "tareta":

                break;
            case "vehiculo":

                break;
            case "viaje":

                break;
            default:
            System.out.println("Ninguna opcion");
        }


        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }

    public void loadDataConvenioCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/convenios.csv");

        Loader<ConvenioRecord> csvLoader =
                create.loadInto(CONVENIO)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(CONVENIO.ID,CONVENIO.EMPRESA,CONVENIO.NUMERO_CONVENIO)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }


    public void loadDataPasajeroCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/pasajeros.csv");

        Loader<PasajeroRecord> csvLoader =
                create.loadInto(PASAJERO)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(PASAJERO.ID,PASAJERO.NOMBRES,PASAJERO.EDAD,PASAJERO.GENERO)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }

    public void loadDataTarjetaCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/tarjetas.csv");

        Loader<TarjetaRecord> csvLoader =
                create.loadInto(TARJETA)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(TARJETA.ID, TARJETA.PERSONALIZADO, TARJETA.SALDO, TARJETA.PASAJERO_ID, TARJETA.CONVENIO_ID)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }


    public void loadDataVehiculoCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/vehiculos.csv");

        Loader<VehiculoRecord> csvLoader =
                create.loadInto(VEHICULO)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(VEHICULO.ID, VEHICULO.PALCA, VEHICULO.CAPACIDAD, VEHICULO.COLOR)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }



    public void loadDataViajeCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/viajes.csv");

        Loader<ViajeRecord> csvLoader =
                create.loadInto(VIAJE)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(VIAJE.ID, VIAJE.DISTANCIA, VIAJE.TARJETA_ID, VIAJE.VEHICULO_ID)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }



    public void loadDataRutasCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/rutas.csv");

        Loader<RutaRecord> csvLoader =
                create.loadInto(RUTA)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(RUTA.ID, RUTA.NOMBRE, RUTA.NUMERO, RUTA.INICIO, RUTA.FIN)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }




    public void loadDataEstacionCSV(DSLContext create) throws IOException {
        InputStream inputstream = new FileInputStream("src/main/resources/estaciones.csv");

        Loader<EstacionRecord> csvLoader =
                create.loadInto(ESTACION)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV(inputstream)
                        .fields(ESTACION.ID, ESTACION.NOMBRE, ESTACION.TIENE_TAQUILLA, ESTACION.UBICACION, ESTACION.SECTOR, ESTACION.VIAJE_ID, ESTACION.VIAJE_DISTANCIA, ESTACION.RUTA_ID)
                        .quote('\'')
                        .separator(';')
                        .ignoreRows(0)
                        .execute();

        int processed = csvLoader.processed();
        int stored = csvLoader.stored();
        int ignored = csvLoader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
    }

    public void deleteTable(DSLContext create){
        for (int i = 1; i <= 200000; i++) {
            RutaRecord record = create.fetchOne(RUTA, RUTA.ID.eq(i));
            record.delete();
        }

//        for (int i = 1; i <= 200000; i++) {
//            EstacionRecord record = create.fetchOne(ESTACION, ESTACION.ID.eq(i));
//            record.delete();
//        }

        for (int i = 1; i <= 200000; i++) {
            ViajeRecord record = create.fetchOne(VIAJE, VIAJE.ID.eq(i));
            record.delete();
        }

        for (int i = 1; i <= 200000; i++) {
            VehiculoRecord record = create.fetchOne(VEHICULO, VEHICULO.ID.eq(i));
            record.delete();
        }

        for (int i = 1; i <= 200000; i++) {
            TarjetaRecord record = create.fetchOne(TARJETA, TARJETA.ID.eq(i));
            record.delete();
        }
        for (int i = 1; i <= 200000; i++) {
            ConvenioRecord record = create.fetchOne(CONVENIO, CONVENIO.ID.eq(i));
            record.delete();
        }
        for (int i = 1; i <= 200000; i++) {
            PasajeroRecord record = create.fetchOne(PASAJERO, PASAJERO.ID.eq(i));
            record.delete();
        }
    }

}
