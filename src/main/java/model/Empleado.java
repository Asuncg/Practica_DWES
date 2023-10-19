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

    /**
     * Constructor para crear una instancia de Empleado con nombre, DNI, sexo, categoría y
     * años donde controlamos el rango de la categoría y de la edad.
     *
     * @param nombre
     * @param dni
     * @param sexo
     * @param categoria
     * @param anyos
     */
    public Empleado(String nombre, String dni, Character sexo, int categoria, double anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);

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
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Empleado(String nombre, String dni, Character sexo) {
        super(nombre, dni, sexo);
        this.anyos = 0;
        this.categoria = 1;
    }

    /**
     * Getter de la categoría
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Setter de la categoría
     */
    public void setCategoria(int categoria) {
        if (categoria >= 1 && categoria <= 10) {
            this.categoria = categoria;
        } else {
            this.categoria = 1;
        }
    }

    /**
     *
     * Incrementa en uno los años trabajados
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     *
     * Imprime los datos del Empleado en consola.
     */
    @Override
    public void imprime() {
        super.imprime();
        System.out.println("Categoria: " + categoria + ",Años: " + anyos);
    }
}
