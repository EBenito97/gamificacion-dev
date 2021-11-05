package com.gamificacion.gamificacion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Alumno implements Serializable {

    public static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private Integer edad;

    private String alias;

    private String sobreMi;

    private String avatar;

    private Double puntaje;

    private Integer puesto;

    @JsonIgnoreProperties({"id","hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alumno", cascade = CascadeType.ALL)
    private List<AlumnoInsignia> insignias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }

    public List<AlumnoInsignia> getInsignias() {
        return insignias;
    }

    public void setInsignias(List<AlumnoInsignia> insignias) {
        this.insignias = insignias;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
