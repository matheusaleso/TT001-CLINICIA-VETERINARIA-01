package model;


import java.util.Calendar;
//import java.util.Date;

/**
 *
 * @author 
 */
public class Tratamento {
    private int id;
    private String nome;
    private Calendar dtInicio;
    private Calendar dtFim;
    private int idAnimal;
    private boolean terminou;    

    public Tratamento(int id, String nome, Calendar dtInicio, Calendar dtFim, int idAnimal, boolean terminou) {
        this.id = id;
        this.nome = nome;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Calendar dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Calendar getDtFim() {
        return dtFim;
    }

    public void setDtFim(Calendar dtFim) {
        this.dtFim = dtFim;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
    
    
    @Override
    public String toString() {     
        int diaInicio = dtInicio.get(Calendar.DAY_OF_MONTH);
        int mesInicio = dtInicio.get(Calendar.MONTH);
        int anoInicio = dtInicio.get(Calendar.YEAR);
        String dataInicio = diaInicio + "-" + mesInicio + "-" + anoInicio;
        
        int diaFim = dtFim.get(Calendar.DAY_OF_MONTH);
        int mesFim = dtFim.get(Calendar.MONTH);
        int anoFim = dtFim.get(Calendar.YEAR);
        String dataFim = diaFim + "-" + mesFim + "-" + anoFim;
        
        String situacao;
        if(terminou==false){
            situacao = "Não";
        }else{
            situacao = "Sim";
        }
        
        String desc =  "Nome: " + nome + "\n Início: " + dataInicio + "\n Fim: " + dataFim 
                + "\n Animal : " + idAnimal + "\n Finalizado: " + situacao;
        return desc + "\n";
    } 
}
