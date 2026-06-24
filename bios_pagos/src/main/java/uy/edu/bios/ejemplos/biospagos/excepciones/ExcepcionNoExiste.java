package uy.edu.bios.ejemplos.biospagos.excepciones;

public class ExcepcionNoExiste extends ExcepcionBiosPagos {

    public ExcepcionNoExiste() {

    }

    public ExcepcionNoExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionNoExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}