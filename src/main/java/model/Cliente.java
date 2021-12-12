package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author 
 */
public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cep;
    
    
    private List<Animal> animais;

    public Cliente(int id, String nome, String email, String telefone, String endereco, String cep) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.animais = new ArrayList<Animal>();
    }
    
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getTelefone() {
        return telefone;
    }
        
    public String getEndereco() {
        return endereco;
    }

    public String getCep() {
        return cep;
    }


   public void setNome(String nome) {
        this.nome = nome;
    }
        
    public void setEmail(String email){
        if(!email.equals("")){
            this.email = email;
        }
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean addAnimal(Animal animal){
        if(!animal.getNome().isEmpty()){
            animais.add(animal);
            return true;
        }
        return false;
    }
    
    public List<Animal> getAnimais(){
        List<Animal> copia = new ArrayList<Animal>(animais);        
        return copia;
    }
    
    @Override
    public String toString() {        
        String desc = "Cliente{" + "nome=" + nome + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }    
}
