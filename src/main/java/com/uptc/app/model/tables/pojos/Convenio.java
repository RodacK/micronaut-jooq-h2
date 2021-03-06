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
@Table(name = "CONVENIO", schema = "TRANSMI", uniqueConstraints = {
    @UniqueConstraint(name = "CONSTRAINT_C", columnNames = {"ID"})
})
public class Convenio implements Serializable {

    private static final long serialVersionUID = -44246880;

    private Integer id;
    private String  empresa;
    private Integer numeroConvenio;

    public Convenio() {}

    public Convenio(Convenio value) {
        this.id = value.id;
        this.empresa = value.empresa;
        this.numeroConvenio = value.numeroConvenio;
    }

    public Convenio(
        Integer id,
        String  empresa,
        Integer numeroConvenio
    ) {
        this.id = id;
        this.empresa = empresa;
        this.numeroConvenio = numeroConvenio;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 10)
    @NotNull
    public Integer getId() {
        return this.id;
    }

    public Convenio setId(Integer id) {
        this.id = id;
        return this;
    }

    @Column(name = "EMPRESA", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getEmpresa() {
        return this.empresa;
    }

    public Convenio setEmpresa(String empresa) {
        this.empresa = empresa;
        return this;
    }

    @Column(name = "NUMERO_CONVENIO", nullable = false, precision = 10)
    @NotNull
    public Integer getNumeroConvenio() {
        return this.numeroConvenio;
    }

    public Convenio setNumeroConvenio(Integer numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Convenio (");

        sb.append(id);
        sb.append(", ").append(empresa);
        sb.append(", ").append(numeroConvenio);

        sb.append(")");
        return sb.toString();
    }
}
