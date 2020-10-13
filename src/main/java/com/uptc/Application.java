package com.uptc;

import static model.Tables.CONVENIO;

import model.tables.pojos.Convenio;
import model.tables.records.ConvenioRecord;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Application {

    public static void staticSQL(DSLContext create){
        create.insertInto(CONVENIO,
                CONVENIO.ID,CONVENIO.EMPRESA,CONVENIO.NUMERO_CONVENIO ).
                values(3,"brfa",2312).execute();
    }

    public static void dynamicSQL(DSLContext create) throws IOException {
        ConvenioRecord record = new ConvenioRecord(4,"holas",32);
        Loader<ConvenioRecord> loader = create.loadInto(CONVENIO).onDuplicateKeyError().loadRecords(record).fields(CONVENIO.fields()).execute();
        // The errors that may have occurred during loading
        loader.errors().forEach(i -> System.out.println(i.exception()));
    }

    public static void dynamicSQLCSV(DSLContext create) throws IOException {
        Loader<ConvenioRecord> csvLoader =
                create.loadInto(CONVENIO)
                        .onDuplicateKeyError()
                        .onErrorAbort()
                        .commitAll()
                        .loadCSV("7;'Kafka;223'\n" +
                                "8;Frisch;E")
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
    public static void dynamicSQLExportJSON(DSLContext create) throws IOException {
        String json = create.selectFrom(CONVENIO).fetch().formatJSON();
        System.out.println(json);
    }

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.80.1/rodack;schema=transmi", "rodack", "rodack");) {
            DSLContext create = DSL.using(conn, SQLDialect.H2);
            //static sql
            //staticSQL(create);
            //dynamic sql and loader api
            //dynamicSQL(create);
            //dynamic sql and loader api for csv
            //dynamicSQLCSV(create);
            //dynamic sql and loader api for json
            dynamicSQLExportJSON(create);

            Result<Record> result = create.select().from(CONVENIO).fetch();
            for (Record r : result) {
                Integer id = r.getValue(CONVENIO.ID);
                String empresa = r.getValue(CONVENIO.EMPRESA);
                Integer numero = r.getValue(CONVENIO.NUMERO_CONVENIO);

                System.out.println("ID: " + id + " empresa: " + empresa + " numero: " + numero);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
