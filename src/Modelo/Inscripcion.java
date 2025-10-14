
package Modelo;

import Modelo.Alumno;
import Modelo.Materia;

public class Inscripcion {
   
 private int idInscripto = -1;
 private Alumno idAlumno;
 private Materia idMateria;
 private int nota;

    public Inscripcion(int nota,Alumno alumno,Materia materia){
        this.nota = nota;
        this.idAlumno= alumno;
        this.idMateria= materia;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripto=" + idInscripto + ", idAlumno=" + idAlumno + ", idMateria=" + idMateria + ", nota=" + nota + '}';
    }
 
    
}
