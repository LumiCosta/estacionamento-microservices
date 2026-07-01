package br.com.estacionamento.vagas.model;
import jakarta.persistence.*;
@Entity public class Vaga{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    public String codigo;
    public String setor;
    public boolean ocupada;
    public boolean reservada;
    public Vaga(){
            }
    public Vaga(String codigo,String setor,boolean ocupada,boolean reservada){
        this.codigo=codigo;this.setor=setor;this.ocupada=ocupada;this.reservada=reservada;
    }
}
