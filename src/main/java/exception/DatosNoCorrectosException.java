package exception;

/**
 * @Author: Asun C.G
 * La clase DatosNoCorrectosException extiende de la clase Exception y controla que los datos
 * introducidos sean correctos.
 */

public class DatosNoCorrectosException extends Exception{

    /** Lanzamiento del mensaje en caso de excepción
     *
     * @param mensaje
     */
    public DatosNoCorrectosException(String mensaje) {
        super(mensaje);
    }
}
