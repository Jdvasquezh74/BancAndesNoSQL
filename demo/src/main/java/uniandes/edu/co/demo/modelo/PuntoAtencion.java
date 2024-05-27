package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.ToString;


@ToString
public class PuntoAtencion {

    @Id
    private ObjectId id;

    private String tipo;

    private Float latitud;

    private Float longitud;

    private ObjectId oficinaasociada;


    private List<String> operaciones;


    public PuntoAtencion(){
        ;
    }


    public PuntoAtencion(ObjectId id, String tipo, Float latitud, Float longitud, List<String> operaciones,ObjectId oficinaasociada) {
        this.id = id;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.operaciones = operaciones;
        this.oficinaasociada = oficinaasociada;
    }


    public ObjectId getOficinaAsociada() {
        return oficinaasociada;
    }


    public void setOficinaAsociada(ObjectId oficinaasociada) {
        this.oficinaasociada = oficinaasociada;
    }


    public ObjectId getId() {
        return id;
    }


    public void setId(ObjectId id) {
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


    public List<String> getOperaciones() {
        return operaciones;
    }


    public void setOperaciones(List<String> operaciones) {
        this.operaciones = operaciones;
    }


    

    
}
