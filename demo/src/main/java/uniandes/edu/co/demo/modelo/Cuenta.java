package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class Cuenta {

    private static long contadorGlobal = 0; // Contador global

    @Id
    private long id;

    private Integer saldo;
    private String fechaultimaoperacion;
    private String estado;
    private List<Long> operaciones;

    public Cuenta(){
        this.id = ++contadorGlobal; // Incrementar el contador y asignarlo como ID
    }

    public Cuenta (Integer saldo, String fechaultimaoperacion, String estado,List<Long> operaciones){
        this.saldo = saldo;
        this.fechaultimaoperacion = fechaultimaoperacion;
        this.estado = estado;
        this.operaciones = operaciones;
        this.id = ++contadorGlobal; // Incrementar el contador y asignarlo como ID
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setOperaciones(List<Long> operaciones){
        this.operaciones = operaciones;
    }

    public List<Long> getOperaciones(){
        return operaciones;
    }

    // Método estático para obtener el contador global
    public static long getContadorGlobal() {
        return contadorGlobal;
    }
}
