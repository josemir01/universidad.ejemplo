package Modelo;

import java.util.HashSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Alumno {
private int legajo;
private String apellido;
private String nombre;
private HashSet<Materia>lista;

    public Alumno(int legajo, String apellido, String nombre) {
        this.legajo = legajo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.lista=new HashSet<>();
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
   public void agregarMaterias(Materia m){
    if(!lista.contains(m)){
      lista.add(m);  
      }
    }
   public int cantidadMaterias(){
       int contador=0;
       for (Materia aux : lista) {
          if(lista.contains(aux));
          contador++;
       }
 
       
    return contador;
     
       
   } 

    @Override
    public String toString() {
        return legajo + " " + nombre + " " + apellido;
    }
}
