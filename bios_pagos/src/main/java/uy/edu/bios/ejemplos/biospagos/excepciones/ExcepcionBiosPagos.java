package uy.edu.bios.ejemplos.biospagos.excepciones;

public class ExcepcionBiosPagos extends Exception {

    public ExcepcionBiosPagos() {

    }

    public ExcepcionBiosPagos(String mensaje) {
        super(mensaje);
    }

    public ExcepcionBiosPagos(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}