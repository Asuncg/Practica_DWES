package model;

import exception.DatosNoCorrectosException;

/**
 * @Author: Asun C.G
 * La clase Empleado extiende de la clase Persona añadiendo categoría y anyos.
 */

public class Empleado extends Persona {

    /**
     * Categoría de la persona
     */
    private int categoria;

    /**
     * Anyos de la persona
     */
    public double anyos;

    public int id;
    private int alta;

    /**
     * Constructor para crear una instancia de Empleado con nombre, DNI, sexo, categoría y
     * años donde controlamos el rango de la categoría y de la edad.
     *
     * @param id
     * @param nombre
     * @param dni
     * @param sexo
     * @param categoria
     * @param anyos
     */
    public Empleado(int id, String nombre, String dni, Character sexo, int categoria, double anyos, int alta) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);

        this.alta = alta;
        this.id = id;

        if (categoria >= 1 && categoria <= 10) {
            this.categoria = categoria;
        } else {
            throw new DatosNoCorrectosException("La categoría no es válida");

        }

        if (anyos >= 0) {
            this.anyos = anyos;
        } else {
            throw new DatosNoCorrectosException("La edad no es válida");

        }

    }

    /**
     * Constructor para crear una instancia de Empleado con nombre, DNI, sexo,
     * donde asignamos un valor por defecto a la categoría y a la edad.
     *
     * @param id
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Empleado(int id, String nombre, String dni, Character sexo) {
        super(nombre, dni, sexo);
        this.anyos = 0;
        this.categoria = 1;
        this.id = id;
    }

    /**
     * Getter de la categoría
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Getter de la id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter de anyos
     */
    public double getAnyos() {
        return anyos;
    }

    /**
     * Getter de nombre
     */
    public String getNombre() {
        return super.getNombre();
    }

    /**
     * Getter de dni
     */
    public String getDni() {
        return super.getDni();
    }

    /**
     * Getter de sexo
     */
    public Character getSexo() {
        return super.getSexo();
    }

    /**
     * Getter de alta
     */
    public int getAlta() {
        return alta;
    }
}
