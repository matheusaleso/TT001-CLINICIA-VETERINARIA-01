
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
    private String endereco;
    private String telefone;
    private String cep;
    private String email;
    
    private List<Animal> animais;

    public Cliente(int id, String nome, String endereco, String telefone, String cep, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.animais = new ArrayList<Animal>();
    }
    
    public int getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email){
        if(!email.equals("")){
            this.email = email;
        }
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
        String desc = "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }    
}
