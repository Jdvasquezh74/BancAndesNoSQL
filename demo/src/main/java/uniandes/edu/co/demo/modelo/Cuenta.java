package uniandes.edu.co.demo.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class Cuenta {

    @Id
    private ObjectId id;
    private String tipo;
    private Usuario clienteasociado;
    private float saldo;
    private String fechaultimaoperacion;
    private String fechacreacioncuenta;
    private String estado;
    private List<ObjectId> operaciones; 
    private Oficina oficinaCreacion;


    public Cuenta() {
        ;
    }

    public Cuenta(Integer saldo, Usuario clienteasociado, Oficina oficinacreacion, String tipo) {
        this.saldo = saldo;
        this.clienteasociado = clienteasociado;
        //Fecha de hoy
        LocalDateTime now = LocalDateTime.now();
        // Definir el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        // Formatear la fecha y hora
        String formattedDateTime = now.format(formatter);
        this.fechaultimaoperacion = formattedDateTime.toString();
        this.fechacreacioncuenta = formattedDateTime.toString();
        this.estado = "activada";
        this.operaciones = new ArrayList<>();
        this.oficinaCreacion = oficinacreacion;
        this.tipo = tipo;
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

    public Usuario getClienteasociado() {
        return clienteasociado;
    }

    public void setClienteasociado(Usuario clienteasociado) {
        this.clienteasociado = clienteasociado;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getFechaultimaoperacion() {
        return fechaultimaoperacion;
    }

    public void setFechaultimaoperacion(String fechaultimaoperacion) {
        this.fechaultimaoperacion = fechaultimaoperacion;
    }

    public String getFechacreacioncuenta() {
        return fechacreacioncuenta;
    }

    public void setFechacreacioncuenta(String fechacreacioncuenta) {
        this.fechacreacioncuenta = fechacreacioncuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ObjectId> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<ObjectId> operaciones) {
        this.operaciones = operaciones;
    }

    public Oficina getOficinaCreacion() {
        return oficinaCreacion;
    }

    public void setOficinaCreacion(Oficina oficinaCreacion) {
        this.oficinaCreacion = oficinaCreacion;
    }

    
   
}