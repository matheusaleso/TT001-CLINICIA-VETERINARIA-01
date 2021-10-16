package model;


import model.Cliente;
import model.ClienteDAO;
import model.Especie;
import model.EspecieDAO;

/**
 *
 * @author Matheus
 */
public class Animal {
    private int id;
    private String nome;
    private int anoNasc;
    private int idEspecie;
    private String sexo; // Macho, Femea
    private int idCliente;

    public Animal(int id, String nome, int idade, String sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = idade;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        Cliente cliente =  ClienteDAO.getInstance().retrieveById(idCliente);
        String dono =  cliente.getNome();
        Especie especie = EspecieDAO.getInstance().retrieveById(idEspecie);
        String especie_animal = especie.getNome();
        String info = "Nome: " + nome + "\n Espécie: " + especie_animal + "\n Dono: " + dono;
        return info;
    }
    


    
}
