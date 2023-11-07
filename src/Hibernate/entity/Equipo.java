package Hibernate.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "equipo")
public class Equipo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "patrocinador")
    private String patrocinador;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)//Si borramos equipo se borran ciclistas
    private Set<Ciclista> ciclistas;

    //Constructores
    public Equipo() {
    }

    public Equipo(int id, String nombre, String patrocinador, Set<Ciclista> ciclistas) {
        this.id = id;
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.ciclistas = ciclistas;
    }

    public Equipo(String nombre, String patrocinador) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
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

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public Set<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void setCiclistas(Set<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }


    //toString


    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", patrocinador='" + patrocinador + '\'' +
                ", ciclistas=" + ciclistas +
                '}';
    }
}
