package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.Tratamento;
import model.TratamentoDAO;
import model.VeterinarioDAO;
import model.Veterinario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.Date;

/**
 *
 * @author 
 */
public class Consulta {
    private int id;
    private Calendar data;
    private String hora;
    private String comentarios;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private boolean terminou;    
    private java.sql.Date sqlHour;
    
    public Consulta(int id, Calendar data, String hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou) {
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
    

    public String getHora() {
        return hora;
    }


    public void setSqlHour(java.sql.Date sqlHour) {
        this.sqlHour = sqlHour;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
       
    
    public Date addHours(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    return calendar.getTime();
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
    
    public int getIdCliente() {
        Animal animal = AnimalDAO.getInstance().retrieveById(idAnimal);
        int clienteID = animal.getIdCliente();
      
        return clienteID;
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

    public java.util.Date getHourAsDate() throws ParseException{
        String string = "2016-09-23 " + hora + ":22";
        DateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dataformat.parse(string); 

        return date;
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
      String monthWrong =  Integer.toString(data.get(Calendar.MONTH));
      String month = "";
      String year = Integer.toString(data.get(Calendar.YEAR));
      
      switch(monthWrong){
          case "0":
              month = "01";
              break;
          case "1":
              month = "02";
              break;
          case "2":
              month = "03";
              break;
          case "3":
              month = "04";
              break;
          case "4":
              month = "05";
              break;
          case "5":
              month = "06";
              break;
          case "6":
              month = "07";
              break;
          case "7":
              month = "08";
              break;
          case "8":
              month = "09";
              break;
          case "9":
              month = "10";
              break;
          case "10":
              month = "11";
              break;
          case "11":
              month = "12";
              break;
          default:
              month = "Error";
              break;    
      }
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
    
