package com.uptc.controller;

import model.tables.records.*;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static model.Tables.*;

public class Controller {

    private Connection conn;
    private DSLContext create;

    public Controller(){
        try {
            conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.56.1/rodack;schema=transmi", "rodack", "rodack");
            create = DSL.using(conn, SQLDialect.H2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void loadDataCSV(String typeObject, String routeFile) {
        long TInicio, TFin, tiempo;
        TInicio = System.currentTimeMillis();
        InputStream inputstream = null;
        try {
            inputstream = new FileInputStream(routeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderConvenio;
                break;
            case "estacion":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderEstacion;
                break;
            case "pasajero":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderPasajero;
                break;
            case "ruta":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderRuta;
                break;
            case "tarjeta":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderTarjeta;
                break;
            case "vehiculo":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderVehiculo;
                break;
            case "viaje":
                try {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                loader = csvLoaderViaje;
                break;
            default:
            System.out.println("Ninguna opcion");
        }
        TFin = System.currentTimeMillis();
        tiempo = TFin - TInicio;
        System.out.println("----------------------------------");
        System.out.println(typeObject);
        int processed = loader.processed();
        int stored = loader.stored();
        int ignored = loader.ignored();
        List<LoaderError> errors =loader.errors();

        System.out.println("Procesadas: "+processed);
        System.out.println("Almacenadas: "+stored);
        System.out.println("Ignoradas: "+ignored);
        System.out.println("Errores: ");
        errors.forEach(i -> System.out.println(i.exception().getMessage()));
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
        System.out.println("----------------------------------");
    }


    public void writeDataCSV(String typeObject, String file) {
        long TInicio, TFin, tiempo;
        TInicio = System.currentTimeMillis();
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
        TFin = System.currentTimeMillis();
        tiempo = TFin - TInicio;
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
        System.out.println("----------------------------------");
        System.out.println(typeObject);
    }


    public void readDataCSV(String typeObject) {
        long TInicio, TFin, tiempo;
        TInicio = System.currentTimeMillis();
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
        TFin = System.currentTimeMillis();
        tiempo = TFin - TInicio;
        System.out.println("Tiempo de ejecución en milisegundos: " + tiempo);
        System.out.println("----------------------------------");
        System.out.println(typeObject);
    }



    public void deleteTable(){
        create.deleteFrom(ESTACION).execute();
        create.deleteFrom(RUTA).execute();
        create.deleteFrom(VIAJE).execute();
        create.deleteFrom(TARJETA).execute();
        create.deleteFrom(VEHICULO).execute();
        create.deleteFrom(CONVENIO).execute();
        create.deleteFrom(PASAJERO).execute();
        
        //delete single object
        //ViajeRecord record = create.fetchOne(VIAJE, VIAJE.ID.eq(i));
        //record.delete();
    }


    public static void staticSQL(DSLContext create){
        create.insertInto(CONVENIO,
                CONVENIO.ID,CONVENIO.EMPRESA,CONVENIO.NUMERO_CONVENIO ).
                values(9,"brfa",2312).execute();
    }

    public static void dynamicSQL(DSLContext create) throws IOException {
        ConvenioRecord record = new ConvenioRecord(4,"holas",32);
        Loader<ConvenioRecord> loader = create.loadInto(CONVENIO).onDuplicateKeyError().loadRecords(record).fields(CONVENIO.fields()).execute();
        // The errors that may have occurred during loading
        loader.errors().forEach(i -> System.out.println(i.exception()));
    }

    public static void dynamicSQLExportJSON(DSLContext create) throws IOException {
        String json = create.selectFrom(CONVENIO).fetch().formatJSON();
        System.out.println(json);
    }

}
