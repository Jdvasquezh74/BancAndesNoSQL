package uniandes.edu.co.demo.modelo;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class Cuenta {

    private ObjectId id;
    private Integer saldo;
    private String fechaultimaoperacion;
    private String estado;
    private List<ObjectId> operaciones;  // Cambiar aquí

    public Cuenta() {
    }

    public Cuenta(Integer saldo) {
        this.saldo = saldo;
        this.fechaultimaoperacion = null;
        this.estado = "activada";
        this.operaciones = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getFechaultimaoperacion() {
        return fechaultimaoperacion;
    }

    public void setFechaultimaoperacion(String fechaultimaoperacion) {
        this.fechaultimaoperacion = fechaultimaoperacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ObjectId> getOperaciones() {  // Cambiar aquí
        return operaciones;
    }

    public void setOperaciones(List<ObjectId> operaciones) {  // Cambiar aquí
        this.operaciones = operaciones;
    }
}
