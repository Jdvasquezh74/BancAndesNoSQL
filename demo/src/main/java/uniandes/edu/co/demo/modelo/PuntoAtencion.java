package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.ToString;


@Document(collection = "puntosAtencion")
@ToString
public class PuntoAtencion {

    @Id
    private long id;

    private String tipo;

    private Float latitud;

    private Float longitud;

    private Oficina oficinaasociada;

    private List<String> operaciones;


    public PuntoAtencion(){
        ;
    }


    public PuntoAtencion(long id, String tipo, Float latitud, Float longitud, List<String> operaciones) {
        this.id = id;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.operaciones = operaciones;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public Float getLatitud() {
        return latitud;
    }


    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }


    public Float getLongitud() {
        return longitud;
    }


    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }


    public Oficina getOficinaasociada() {
        return oficinaasociada;
    }


    public void setOficinaasociada(Oficina oficinaasociada) {
        this.oficinaasociada = oficinaasociada;
    }


    public List<String> getOperaciones() {
        return operaciones;
    }


    public void setOperaciones(List<String> operaciones) {
        this.operaciones = operaciones;
    }


    

    
}
