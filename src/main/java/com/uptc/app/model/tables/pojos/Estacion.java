/*
 * This file is generated by jOOQ.
 */
package model.tables.pojos;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "ESTACION", schema = "TRANSMI", uniqueConstraints = {
    @UniqueConstraint(name = "CONSTRAINT_B", columnNames = {"ID"})
})
public class Estacion implements Serializable {

    private static final long serialVersionUID = -1479342009;

    private Integer id;
    private String  nombre;
    private String  tieneTaquilla;
    private String  ubicacion;
    private String  sector;
    private Integer viajeId;
    private Long    viajeDistancia;
    private Integer rutaId;

    public Estacion() {}

    public Estacion(Estacion value) {
        this.id = value.id;
        this.nombre = value.nombre;
        this.tieneTaquilla = value.tieneTaquilla;
        this.ubicacion = value.ubicacion;
        this.sector = value.sector;
        this.viajeId = value.viajeId;
        this.viajeDistancia = value.viajeDistancia;
        this.rutaId = value.rutaId;
    }

    public Estacion(
        Integer id,
        String  nombre,
        String  tieneTaquilla,
        String  ubicacion,
        String  sector,
        Integer viajeId,
        Long    viajeDistancia,
        Integer rutaId
    ) {
        this.id = id;
        this.nombre = nombre;
        this.tieneTaquilla = tieneTaquilla;
        this.ubicacion = ubicacion;
        this.sector = sector;
        this.viajeId = viajeId;
        this.viajeDistancia = viajeDistancia;
        this.rutaId = rutaId;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 10)
    @NotNull
    public Integer getId() {
        return this.id;
    }

    public Estacion setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "NOMBRE", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getNombre() {
        return this.nombre;
    }

    public Estacion setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Column(name = "TIENE_TAQUILLA", nullable = false, length = 1)
    @NotNull
    @Size(max = 1)
    public String getTieneTaquilla() {
        return this.tieneTaquilla;
    }

    public Estacion setTieneTaquilla(String tieneTaquilla) {
        this.tieneTaquilla = tieneTaquilla;
        return this;
    }

    @Column(name = "UBICACION", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getUbicacion() {
        return this.ubicacion;
    }

    public Estacion setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
        return this;
    }

    @Column(name = "SECTOR", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getSector() {
        return this.sector;
    }

    public Estacion setSector(String sector) {
        this.sector = sector;
        return this;
    }

    @Column(name = "VIAJE_ID", nullable = false, precision = 10)
    @NotNull
    public Integer getViajeId() {
        return this.viajeId;
    }

    public Estacion setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
        return this;
    }

    @Column(name = "VIAJE_DISTANCIA", nullable = false, precision = 10)
    @NotNull
    public Long getViajeDistancia() {
        return this.viajeDistancia;
    }

    public Estacion setViajeDistancia(Long viajeDistancia) {
        this.viajeDistancia = viajeDistancia;
        return this;
    }

    @Column(name = "RUTA_ID", nullable = false, precision = 10)
    @NotNull
    public Integer getRutaId() {
        return this.rutaId;
    }

    public Estacion setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Estacion (");

        sb.append(id);
        sb.append(", ").append(nombre);
        sb.append(", ").append(tieneTaquilla);
        sb.append(", ").append(ubicacion);
        sb.append(", ").append(sector);
        sb.append(", ").append(viajeId);
        sb.append(", ").append(viajeDistancia);
        sb.append(", ").append(rutaId);

        sb.append(")");
        return sb.toString();
    }
}
