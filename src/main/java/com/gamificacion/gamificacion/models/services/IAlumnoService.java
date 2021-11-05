package com.gamificacion.gamificacion.models.services;

import com.gamificacion.gamificacion.models.entity.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAlumnoService {


    public List<Alumno> findAll();

    public Alumno findById(Long id);

    public Alumno save(Alumno alumno);

    public void delete(Long id);
}
