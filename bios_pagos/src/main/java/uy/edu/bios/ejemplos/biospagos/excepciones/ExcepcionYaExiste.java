package uy.edu.bios.ejemplos.biospagos.excepciones;

public class ExcepcionYaExiste extends ExcepcionBiosPagos {

    public ExcepcionYaExiste() {

    }

    public ExcepcionYaExiste(String mensaje) {
        super(mensaje);
    }

    public ExcepcionYaExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}