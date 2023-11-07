package Hibernate.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "palmares")
public class Palmares implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vueltasganadas")
    private int vueltasGanadas;

    @Column(name = "numeromaillots")
    private int numeroMaillots;

    //Relacion 1 a 1
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Ciclista ciclista;


    //Constructores
    public Palmares() {
    }

    public Palmares(int id, int vueltasGanadas, int numeroMaillots, Ciclista ciclista) {
        this.id = id;
        this.vueltasGanadas = vueltasGanadas;
        this.numeroMaillots = numeroMaillots;
        this.ciclista = ciclista;
    }

    public Palmares(int vueltasGanadas, int numeroMaillots, Ciclista ciclista) {
        this.vueltasGanadas = vueltasGanadas;
        this.numeroMaillots = numeroMaillots;
        this.ciclista = ciclista;
    }

    public Palmares(int numVueltas, int numMaillots) {
        this.vueltasGanadas = numVueltas;
        this.numeroMaillots = numMaillots;
    }

    //Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVueltasGanadas() {
        return vueltasGanadas;
    }

    public void setVueltasGanadas(int vueltasGanadas) {
        this.vueltasGanadas = vueltasGanadas;
    }

    public int getNumeroMaillots() {
        return numeroMaillots;
    }

    public void setNumeroMaillots(int numeroMaillots) {
        this.numeroMaillots = numeroMaillots;
    }

    public Ciclista getCiclista() {
        return ciclista;
    }

    public void setCiclista(Ciclista ciclista) {
        this.ciclista = ciclista;
    }


    //toString

    @Override
    public String toString() {
        return "Palmares{" +
                "id=" + id +
                ", vueltasGanadas=" + vueltasGanadas +
                ", numeroMaillots=" + numeroMaillots +
                '}';
    }
}
