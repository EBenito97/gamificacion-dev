package com.gamificacion.gamificacion.controllers;

import com.gamificacion.gamificacion.models.entity.Alumno;
import com.gamificacion.gamificacion.models.services.IAlumnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class AlumnoRestController {

    @Autowired
    private IAlumnoService alumnoService;

    private final Logger log = LoggerFactory.getLogger(AlumnoRestController.class);

    @GetMapping("/alumnos")
    public List<Alumno> index() {
        log.info("... called method AlumnoRestController.index ...");
        return alumnoService.findAll();
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Alumno alumno = null;
        Map<String, Object> response = new HashMap<>();

        try {
            alumno = alumnoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (alumno == null) {
            response.put("mensaje", "El alumno ID: ".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }

    @PostMapping("/alumnos")
    public ResponseEntity<?> create(@Valid @RequestBody Alumno alumno, BindingResult result) {

        Alumno alumnoNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            alumnoNew = alumnoService.save(alumno);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El alumno ha sido creado con éxito!");
        response.put("alumno", alumnoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {

        Alumno alumnoActual = alumnoService.findById(id);

        Alumno alumnoUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (alumnoActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el alumno ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            alumnoActual.setApellido(alumno.getApellido());
            alumnoActual.setNombre(alumno.getNombre());
            alumnoActual.setApellido(alumno.getApellido());
            alumnoActual.setAlias(alumno.getAlias());
            alumnoActual.setSobreMi(alumno.getSobreMi());
            alumnoActual.setAvatar(alumno.getAvatar());
            alumnoActual.setPuntaje(alumno.getPuntaje());
            alumnoActual.setPuesto(alumno.getPuesto());
            //alumnoActual.setInsignias(alumno.getInsignias());

            alumnoUpdated = alumnoService.save(alumnoActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el alumno en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El alumno ha sido actualizado con éxito!");
        response.put("alumno", alumnoUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            alumnoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar al alumno de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El alumno a sido eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
