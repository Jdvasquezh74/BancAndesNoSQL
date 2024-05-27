package uniandes.edu.co.demo.modelo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bson.types.ObjectId; 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "oficinas")
@ToString
public class Oficina {
    @Id
    private ObjectId id; 
    private String nombre;
    private String direccion;
    private Integer cantidadpuntosatencion;
    private ObjectId idgerenteoficina;
    private String horaapertura;
    private String horacierre;
    


    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private List<PuntoAtencion> puntosatencion;
    
    public Oficina() {
        ;
    }

    public Oficina(String nombre, String direccion, Integer cantidadpuntosatencion, ObjectId idgerenteoficina,
                   LocalTime horaapertura, LocalTime horacierre) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadpuntosatencion = cantidadpuntosatencion;
        this.idgerenteoficina = idgerenteoficina;
        this.horaapertura = horaapertura.format(TIME_FORMATTER);
        this.horacierre = horacierre.format(TIME_FORMATTER);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCantidadpuntosatencion() {
        return cantidadpuntosatencion;
    }

    public void setCantidadpuntosatencion(Integer cantidadpuntosatencion) {
        this.cantidadpuntosatencion = cantidadpuntosatencion;
    }

    public ObjectId getIdgerenteoficina() {
        return idgerenteoficina;
    }

    public void setIdgerenteoficina(ObjectId idgerenteoficina) {
        this.idgerenteoficina = idgerenteoficina;
    }

    public String getHoraapertura() {
        return horaapertura;
    }

    public void setHoraapertura(String horaapertura) {
        this.horaapertura = horaapertura;
    }

    public String getHoracierre() {
        return horacierre;
    }

    public void setHoracierre(String horacierre) {
    this.horacierre = horacierre;
}

    public static DateTimeFormatter getTimeFormatter() {
        return TIME_FORMATTER;
    }

    public List<PuntoAtencion> getPuntosatencion() {
        return puntosatencion;
    }

    public void setPuntosatencion(List<PuntoAtencion> puntosatencion) {
        this.puntosatencion = puntosatencion;
    }
}
