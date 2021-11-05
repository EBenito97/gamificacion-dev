package com.gamificacion.gamificacion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class AlumnoInsignia implements Serializable {

    public static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"id","hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Alumno alumno;


    @JsonIgnoreProperties({"id","hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Insignia insignia;

    private Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Insignia getInsignia() {
        return insignia;
    }

    public void setInsignia(Insignia insignia) {
        this.insignia = insignia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
