package model;

import java.text.SimpleDateFormat;
import model.Tratamento;
import model.TratamentoDAO;
import model.VeterinarioDAO;
import model.Veterinario;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
//import java.util.Date;

/**
 *
 * @author 
 */
public class Consulta {
    private int id;
    private Calendar data;
    private Date hora;
    private String comentarios;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private boolean terminou;    

    public Consulta(int id, Calendar data, Date hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.comentarios = comentarios;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
        this.idTratamento = idTratamento;
        this.terminou = terminou;
    }

    public int getId(){
        return id;
    }
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    public String getStringHour(){
        /*
       String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(hora),
            TimeUnit.MILLISECONDS.toMinutes(hora) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(hora)),
            TimeUnit.MILLISECONDS.toSeconds(hora) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(hora)));*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String sCertDate = dateFormat.format(hora);
       
       return sCertDate;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public boolean isTerminou() {
        return terminou;
    }
    public String terminouTostring(){
        if(terminou==true){
            return "Terminou";
        }
        else if (terminou==false){
            return "Em andamento";
        }
        else{
            return "error";
        }
    }
    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
    
    
    public String dataConsulta(){
      String day =  Integer.toString(data.get(Calendar.DAY_OF_MONTH));
      String month =  Integer.toString(data.get(Calendar.MONTH));
      String year = Integer.toString(data.get(Calendar.YEAR));
      
      String dataC = day + "/" + month + "/" + year;
        return dataC;
    }

     @Override
    public String toString() { 
        Animal animal =  AnimalDAO.getInstance().retrieveById(idAnimal);
        String nome_animal = animal.getNome() ;
        Veterinario veterinario =  VeterinarioDAO.getInstance().retrieveById(idVet);
        //String nome_veterinario = veterinario.getNome();
        
        int dia = data.get(Calendar.DAY_OF_MONTH);
        int mes = data.get(Calendar.MONTH);
        int ano = data.get(Calendar.YEAR);
        
        Tratamento tratamento = TratamentoDAO.getInstance().retrieveById(idTratamento);
        String nome_tratamento = tratamento.getNome();
        
        String situacao = terminouTostring();
       
        String data_consulta = dia + "-" + mes + "-" + ano + "\n";
        String horario = hora+":00";
        String desc = "Id: " + id + "\n" + "Data da Consulta: " + data_consulta + "\n" +
                "Horário: " + horario + "\n" + "Comentários: " + comentarios + "\n" +
                "Animal de Estimação: " + nome_animal + "\n" + "Veterinário: " +
                "" +  "\n" + "Tratamento: " + nome_tratamento + "\n" +
                "situação: " + situacao;
        return desc + "\n";
    } 
    
}
    
