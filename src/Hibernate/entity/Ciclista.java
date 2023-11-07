package Hibernate.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name = "ciclista")
public class Ciclista implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

     @Column(name = "apellidos")
    private String apellidos;

     //Relacion N a 1
    @ManyToOne
    @JoinColumn(name = "idEquipo")
    private Equipo equipo;

    //Relacion 1 a 1
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Palmares palmares;


    //Constructores


    public Ciclista() {
    }

    public Ciclista(String nombre, String apellidos, Palmares palmares) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.palmares = palmares;
    }

    public Ciclista(String nombre, String apellidos, Equipo equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.equipo = equipo;
    }

    public Ciclista(int id, String nombre, String apellidos, Equipo equipo, Palmares palmares) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.equipo = equipo;
        this.palmares = palmares;
    }

    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Palmares getPalmares() {
        return palmares;
    }

    public void setPalmares(Palmares palmares) {
        this.palmares = palmares;
    }

    //toString

    @Override
    public String toString() {
        return "Ciclista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", palmares=" + palmares +
                '}';
    }
}
