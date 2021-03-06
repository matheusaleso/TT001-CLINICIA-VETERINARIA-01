package model;

/**
 *
 * @author 
 */
public class Veterinario {
    private int id;
    private String nome;
    private String email;
    private String telefone;

    public Veterinario(int id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
      
    public int getId() {
        return id;
    }
      
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {        
        String desc = "id: " + id + " Nome: " + nome + " e-mail: " + email + " telefone: " + telefone;
        return desc + "\n";
    } 
    
}