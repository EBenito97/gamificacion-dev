package com.gamificacion.gamificacion.models.services.impl;

import com.gamificacion.gamificacion.models.dao.IAlumnoDAO;
import com.gamificacion.gamificacion.models.entity.Alumno;
import com.gamificacion.gamificacion.models.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoService implements IAlumnoService {

    @Autowired
    private IAlumnoDAO alumnoDAO;


    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {
        return (List<Alumno>) alumnoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno findById(Long id) {
        return alumnoDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return alumnoDAO.save(alumno);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alumnoDAO.deleteById(id);
    }
}
