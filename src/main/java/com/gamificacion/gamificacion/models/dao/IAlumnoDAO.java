package com.gamificacion.gamificacion.models.dao;

import com.gamificacion.gamificacion.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface IAlumnoDAO extends CrudRepository<Alumno, Long> {
}
