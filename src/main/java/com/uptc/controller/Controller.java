package com.uptc.controller;

import model.tables.records.*;
import org.jooq.DSLContext;
import org.jooq.Loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static model.Tables.*;

public class Controller {


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


}
