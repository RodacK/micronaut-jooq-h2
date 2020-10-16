package com.uptc.controller;

import model.tables.Vehiculo;
import model.tables.records.*;
import org.jooq.DSLContext;
import org.jooq.Loader;
import org.jooq.Record;
import org.jooq.Result;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;

import static model.Tables.*;

public class Controller {

    public Controller(){

    }




    public void loadDataCSV(DSLContext create, String typeObject, String routeFile) throws IOException {
        InputStream inputstream = new FileInputStream(routeFile);
        Loader<ConvenioRecord> csvLoaderConvenio = null;
        Loader<EstacionRecord> csvLoaderEstacion = null;
        Loader<PasajeroRecord> csvLoaderPasajero = null;
        Loader<TarjetaRecord> csvLoaderTarjeta = null;
        Loader<RutaRecord> csvLoaderRuta = null;
        Loader<VehiculoRecord> csvLoaderVehiculo = null;
        Loader<ViajeRecord> csvLoaderViaje = null;
        Loader loader = null;
        switch(typeObject){
            case "convenio":
                csvLoaderConvenio =
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
                loader = csvLoaderConvenio;
                break;
            case "estacion":
                csvLoaderEstacion =
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
                loader = csvLoaderEstacion;
                break;
            case "pasajero":
                csvLoaderPasajero =
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
                loader = csvLoaderPasajero;
                break;
            case "ruta":
                csvLoaderRuta =
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
                loader = csvLoaderRuta;
                break;
            case "tarjeta":
                csvLoaderTarjeta =
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
                loader = csvLoaderTarjeta;
                break;
            case "vehiculo":
                csvLoaderVehiculo =
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
                loader = csvLoaderVehiculo;
                break;
            case "viaje":
                csvLoaderViaje =
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
                loader = csvLoaderViaje;
                break;
            default:
            System.out.println("Ninguna opcion");
        }

        System.out.println("----------------------------------");
        System.out.println(typeObject);
        int processed = loader.processed();
        int stored = loader.stored();
        int ignored = loader.ignored();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
        System.out.println("----------------------------------");
    }


    public void writeDataCSV(DSLContext create, String typeObject, String file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String cvs = "";
        switch (typeObject) {
            case "convenio":
                cvs = create.selectFrom(CONVENIO).fetch().formatCSV(';');
                break;
            case "pasajero":
                cvs = create.selectFrom(PASAJERO).fetch().formatCSV(';');
                break;
            case "tarjeta":
                cvs = create.selectFrom(TARJETA).fetch().formatCSV(';');
                break;
            case "viaje":
                cvs = create.selectFrom(VIAJE).fetch().formatCSV(';');
                break;
            case "ruta":
                cvs = create.selectFrom(RUTA).fetch().formatCSV(';');
                break;
            case "estacion":
                cvs = create.selectFrom(ESTACION).fetch().formatCSV(';');
                break;
            case "vehiculo":
                cvs = create.selectFrom(VEHICULO).fetch().formatCSV(';');
                break;
            default:
                System.out.println("No encontrado");
        }
        writer.write(cvs);
    }


    public void readDataCSV(DSLContext create, String typeObject) throws IOException {
        switch(typeObject){
            case "convenio":
                Result<Record> resultConvenio = create.select().from(CONVENIO).fetch();
                for (Record r : resultConvenio) {
                    Integer id = r.getValue(CONVENIO.ID);
                    String empresa = r.getValue(CONVENIO.EMPRESA);
                    Integer numero = r.getValue(CONVENIO.NUMERO_CONVENIO);
                    System.out.println("ID: " + id + " empresa: " + empresa + " numero: " + numero);
                }
                break;
            case "estacion":
                Result<Record> resultEstacion = create.select().from(ESTACION).fetch();
                for (Record r : resultEstacion) {
                    Integer id = r.getValue(ESTACION.ID);
                    String nombre = r.getValue(ESTACION.NOMBRE);
                    String tiene_taquilla = r.getValue(ESTACION.TIENE_TAQUILLA);
                    String ubicacion = r.getValue(ESTACION.UBICACION);
                    String sector = r.getValue(ESTACION.SECTOR);
                    Integer viaje = r.getValue(ESTACION.VIAJE_ID);
                    Long distancia = r.getValue(ESTACION.VIAJE_DISTANCIA);
                    Integer ruta = r.getValue(ESTACION.RUTA_ID);
                    System.out.println("ID: " + id + " nombre: " + nombre + " tiene taquilla: " +tiene_taquilla+" ubicación "+ubicacion+" sector "+sector+" viaje "+viaje+" distancia "+distancia+" ruta "+ruta);
                }
                break;
            case "pasajero":
                Result<Record> result = create.select().from(PASAJERO).fetch();
                for (Record r : result) {
                    Integer id = r.getValue(PASAJERO.ID);
                    String nombre = r.getValue(PASAJERO.NOMBRES);
                    String edad = r.getValue(PASAJERO.EDAD);
                    String genero = r.getValue(PASAJERO.GENERO);
                    System.out.println("ID: " + id + " nombre: " + nombre + " edad: " + edad + " genero "+genero);
                }
                break;
            case "ruta":
                Result<Record> resultRuta = create.select().from(RUTA).fetch();
                for (Record r : resultRuta) {
                    Integer id = r.getValue(RUTA.ID);
                    String nombre = r.getValue(RUTA.NOMBRE);
                    String numero = r.getValue(RUTA.NUMERO);
                    LocalDate inicio = r.getValue(RUTA.INICIO);
                    LocalDate fin = r.getValue(RUTA.FIN);

                    System.out.println("ID: " + id + " nombre: " + nombre + " numero: " + numero+" inicio "+inicio+" fin "+fin);
                }
                break;
            case "tarjeta":
                Result<Record> resultTarjeta = create.select().from(TARJETA).fetch();
                for (Record r : resultTarjeta) {
                    Integer id = r.getValue(TARJETA.ID);
                    String personalizado = r.getValue(TARJETA.PERSONALIZADO);
                    Long saldo = r.getValue(TARJETA.SALDO);
                    Integer pasajero = r.getValue(TARJETA.PASAJERO_ID);
                    Integer convenio = r.getValue(TARJETA.CONVENIO_ID);
                    System.out.println("ID: " + id + " personalizado: " + personalizado + " saldo: " + saldo+" pasajero "+pasajero+" convenio "+convenio);
                }
                break;
            case "vehiculo":
                Result<Record> resultVehiculo = create.select().from(VEHICULO).fetch();
                for (Record r : resultVehiculo) {
                    Integer id = r.getValue(VEHICULO.ID);
                    String placa = r.getValue(VEHICULO.PALCA);
                    Integer capacidad = r.getValue(VEHICULO.CAPACIDAD);
                    String color = r.getValue(VEHICULO.COLOR);
                    System.out.println("ID: " + id + " plaza: " + placa + " capacidad: " + capacidad+" color "+color);
                }
                break;
            case "viaje":
                Result<Record> resultViaje = create.select().from(VIAJE).fetch();
                for (Record r : resultViaje) {
                    Integer id = r.getValue(VIAJE.ID);
                    Long distancia = r.getValue(VIAJE.DISTANCIA);
                    Integer tarjeta = r.getValue(VIAJE.TARJETA_ID);
                    Integer vehiculo = r.getValue(VIAJE.VEHICULO_ID);

                    System.out.println("ID: " + id + " distancia: " + distancia + " tarjeta: " + tarjeta+" vehiculo "+vehiculo);
                }
                break;
            default:
                System.out.println("Ninguna opción");
        }
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
