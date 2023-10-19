package model;

public class Sueldo {

    private String dni;
    private double sueldo;

    public Sueldo(String dni, double sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void imprime() {
        System.out.println("Dni: " + getDni() + ", Sueldo: " + getSueldo());
    }
}

