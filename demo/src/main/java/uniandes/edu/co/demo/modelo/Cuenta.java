package uniandes.edu.co.demo.modelo;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection="cuentas")
@ToString
public class Cuenta {

    @Id
    private long id;

    private String tipo;
    private Integer id_cliente;
    private Integer saldo;
    private Timestamp fecha_ultima_transaccion;
    private String estado;
    private Long oficina;

    public Cuenta(){
        ;
    }

    public Cuenta (String tipo, Integer id_cliente, Integer saldo, Timestamp fecha_ultima_transaccion, String estado,Long oficina){
        this.tipo = tipo;
        this.id_cliente = id_cliente;
        this.saldo = saldo;
        this.fecha_ultima_transaccion = fecha_ultima_transaccion;
        this.estado = estado;
        this.oficina = oficina;
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

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Timestamp getFecha_ultima_transaccion() {
        return fecha_ultima_transaccion;
    }

    public void setFecha_ultima_transaccion(Timestamp fecha_ultima_transaccion) {
        this.fecha_ultima_transaccion = fecha_ultima_transaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setOficina(Long oficina){
        this.oficina = oficina;
    }

    public Long getOficina(){
        return oficina;
    }
}
