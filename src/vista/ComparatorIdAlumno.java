package vista;

import Modelo.Alumno;
import java.util.Comparator;

public class ComparatorIdAlumno implements Comparator<Alumno> {
    @Override
    public int compare (Alumno a1, Alumno a2) {
        return Integer.compare(a1.getId_alumno(), a2.getId_alumno());
    }
}
