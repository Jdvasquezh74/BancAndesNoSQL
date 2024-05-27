package uniandes.edu.co.demo.modelo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.ToString;

@Document(collection = "usuarios")
@ToString
public class Usuario {
    @Id
    private ObjectId id;
    
    private String login;

    private String clave;

    private String rol;
    
    private String numeroidentificacion;

    private String tipoidentificacion;

    private String nombre;

    private String nacionalidad;

    private String direccionfisica;

    private String direccionelectronica;

    private Long telefono;

    private String ciudad;

    private String departamento;

    private String codigopostal;

    private List<ObjectId> cuentas;

    public Usuario(){
        ;
    }

    public Usuario(ObjectId id, String login, String clave, String rol, String numeroidentificacion,
            String tipoidentificacion, String nombre, String nacionalidad, String direccionfisica,
            String direccionelectronica, Long telefono, String ciudad, String departamento, String codigopostal) {
        this.id = id;
        this.login = login;
        this.clave = clave;
        this.rol = rol;
        this.numeroidentificacion = numeroidentificacion;
        this.tipoidentificacion = tipoidentificacion;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.direccionfisica = direccionfisica;
        this.direccionelectronica = direccionelectronica;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigopostal = codigopostal;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNumeroidentificacion() {
        return numeroidentificacion;
    }

    public void setNumeroidentificacion(String numeroidentificacion) {
        this.numeroidentificacion = numeroidentificacion;
    }

    public String getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(String tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccionfisica() {
        return direccionfisica;
    }

    public void setDireccionfisica(String direccionfisica) {
        this.direccionfisica = direccionfisica;
    }

    public String getDireccionelectronica() {
        return direccionelectronica;
    }

    public void setDireccionelectronica(String direccionelectronica) {
        this.direccionelectronica = direccionelectronica;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public List<ObjectId> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<ObjectId> cuentas) {
        this.cuentas = cuentas;
    }

    

   
}