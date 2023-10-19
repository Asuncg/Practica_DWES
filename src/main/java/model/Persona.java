package model;

/**
 * @Author: Asun C.G
 * La clase Persona representa a una persona con nombre, DNI y sexo.
 */
public class Persona {
    /** Nombre de la persona */
    public String nombre;

    /** Dni de la persona */
    public String dni;

    /** Sexo de la persona */
    public Character sexo;

    /**
     * Constructor para crear una instancia de Persona con nombre, DNI y sexo.
     * @param nombre  nombre de la persona.
     * @param dni     DNI de la persona.
     * @param sexo    sexo de la persona ('M' para masculino, 'F' para femenino).
     */
    public Persona(String nombre, String dni, Character sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor para crear una instancia de Persona con nombre y sexo.     *
     * @param nombre
     * @param sexo
     */
    public Persona(String nombre, Character sexo) {
        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Setter del dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

/**
 * Método para emitir mensaje en consola con los datos de la Persona.
 */
    public void imprime() {
        System.out.println("Nombre: " + nombre + ", Dni: " + dni + ", Sexo: " + sexo);
    }
}
