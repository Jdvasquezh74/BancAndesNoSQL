package uniandes.edu.co.demo.modelo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "oficinas")
@ToString
public class Oficina {
    @Id
    private Integer id;
    private String nombre;
    private String direccion;
    private Integer cantidadpuntosatencion;
    private long idgerenteoficina;
    private String horaapertura; 
    private String horacierre;   

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Oficina() {
        ;
    }

    public Oficina(String nombre, String direccion, Integer cantidadpuntosatencion, long idgerenteoficina,
                   LocalTime horaapertura, LocalTime horacierre) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadpuntosatencion = cantidadpuntosatencion;
        this.idgerenteoficina = idgerenteoficina;
        this.horaapertura = horaapertura.format(TIME_FORMATTER);
        this.horacierre = horacierre.format(TIME_FORMATTER);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public long getIdgerenteoficina() {
        return idgerenteoficina;
    }

    public void setIdgerenteoficina(long idgerenteoficina) {
        this.idgerenteoficina = idgerenteoficina;
    }

    public LocalTime getHoraapertura() {
        return LocalTime.parse(horaapertura, TIME_FORMATTER);
    }

    public void setHoraapertura(LocalTime horaapertura) {
        this.horaapertura = horaapertura.format(TIME_FORMATTER);
    }

    public LocalTime getHoracierre() {
        return LocalTime.parse(horacierre, TIME_FORMATTER);
    }

    public void setHoracierre(LocalTime horacierre) {
        this.horacierre = horacierre.format(TIME_FORMATTER);
    }
}
