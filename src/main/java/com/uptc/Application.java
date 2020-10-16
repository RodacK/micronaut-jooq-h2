package com.uptc;

import static model.Tables.CONVENIO;

import com.uptc.controller.Controller;
import model.tables.records.ConvenioRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;


public class Application {

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

    public static void main(String[] args) {
        Controller controller = new Controller();
        try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://127.0.1.1/rodack;schema=transmi", "rodack", "rodack");) {
            DSLContext create = DSL.using(conn, SQLDialect.H2);
            //static sql
            //staticSQL(create);
            //dynamic sql and loader api
            //dynamicSQL(create);


            //dynamic sql and loader api for csv
//            controller.loadDataCSV(create, "convenio", "src/main/resources/import/convenios.csv");
//            controller.loadDataCSV(create, "pasajero", "src/main/resources/import/pasajeros.csv");
//            controller.loadDataCSV(create, "tarjeta", "src/main/resources/import/tarjetas.csv");
//            controller.loadDataCSV(create, "vehiculo", "src/main/resources/import/vehiculos.csv");
//            controller.loadDataCSV(create, "viaje", "src/main/resources/import/viajes.csv");
//            controller.loadDataCSV(create, "ruta", "src/main/resources/import/rutas.csv");
//            controller.loadDataCSV(create, "estacion", "src/main/resources/import/estaciones.csv");

//            controller.readDataCSV(create, "convenio");
//            controller.readDataCSV(create, "pasajero");
//            controller.readDataCSV(create, "tarjeta");
//            controller.readDataCSV(create, "vehiculo");
//            controller.readDataCSV(create, "viaje");
//            controller.readDataCSV(create, "ruta");
//            controller.readDataCSV(create, "estacion");

            controller.writeDataCSV(create, "convenio","src/main/resources/export/convenios.csv");
            controller.writeDataCSV(create, "pasajero","src/main/resources/export/pasajeros.csv");
            controller.writeDataCSV(create, "tarjeta","src/main/resources/export/tarjetas.csv");
            controller.writeDataCSV(create, "vehiculo","src/main/resources/export/vehiculos.csv");
            controller.writeDataCSV(create, "viaje","src/main/resources/export/viajes.csv");
            controller.writeDataCSV(create, "ruta","src/main/resources/export/rutas.csv");
            controller.writeDataCSV(create, "estacion","src/main/resources/export/estacions.csv");



            //controller.deleteTable(create);


            //dynamic sql and loader api for json
            //dynamicSQLExportJSON(create);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
