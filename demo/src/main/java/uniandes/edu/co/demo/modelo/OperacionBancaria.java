package uniandes.edu.co.demo.modelo;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.ToString;


@Document(collection = "operaciones")
@ToString
public class OperacionBancaria {
    @Id
    private ObjectId id;
    
    private ObjectId cuentaafectada;
    private ObjectId cuentadestino;
    private String fechaoperacion;
    private String tipooperacion;
    private float monto;
    

    public OperacionBancaria (){
        ;
    }
    
    

    public OperacionBancaria(ObjectId id, ObjectId cuentaafectada, ObjectId cuentadestino,
            String fechaoperacion, String tipooperacion, float monto) {
        this.id = id;
        this.cuentaafectada = cuentaafectada;
        this.cuentadestino = cuentadestino;
        this.fechaoperacion= fechaoperacion;
        this.tipooperacion = tipooperacion;
        this.monto = monto;
    }



    public ObjectId getId() {
        return id;
    }


    public void setId(ObjectId id) {
        this.id = id;
    }


    public ObjectId getCuentaafectada() {
        return cuentaafectada;
    }


    public void setCuentaafectada(ObjectId cuentaafectada) {
        this.cuentaafectada = cuentaafectada;
    }



    public ObjectId getCuentadestino() {
        return cuentadestino;
    }


    public void setCuentadestino(ObjectId cuentadestino) {
        this.cuentadestino = cuentadestino;
    }


    public String getFechaOperacion() {
        return fechaoperacion;
    }


    public void setFechaOperacion(String fechaoperacion) {
        this.fechaoperacion = fechaoperacion;
    }


    public String getTipooperacion() {
        return tipooperacion;
    }


    public void setTipooperacion(String tipooperacion) {
        this.tipooperacion = tipooperacion;
    }


    public float getMonto() {
        return monto;
    }


    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
}