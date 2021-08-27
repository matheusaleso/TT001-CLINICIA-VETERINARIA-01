/**
 *
 * @author 
 */
public class Exame {
    private String descricao;
    private int idConsulta;    

    public Exame(String descricao, int idConsulta) {
        this.descricao = descricao;
        this.idConsulta = idConsulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }


    
}
