
import model.ConsultaDAO;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.EspecieDAO;
import model.ExameDAO;
import model.TratamentoDAO;
import model.VeterinarioDAO;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.SpinnerDateModel;

public class Main {
    public static void main(String[] args) {   
       
        ClienteDAO.getInstance().create("Matheus Sales Oliveira",
               "m203577@dac.unicamp.br",
               "123456789", 
               "Rua Três",
               "00234-905");
        
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        ClienteDAO.getInstance().create(
                "Matilda Santana Antunes",
                "m223444@dac.unicamp.br",
                "8339383",
                "Rua Quatro",
                "01234-905");
        Cliente c2 = ClienteDAO.getInstance().retrieveById(2);
        
        ClienteDAO.getInstance().create("Lucas Almeida Ferreira",
                "202347@dac.unicamp.br",
                "829483",
                "Rua Cinco",
                "13534-385");
        Cliente c3 = ClienteDAO.getInstance().retrieveById(3);
        
        EspecieDAO.getInstance().create("Gato");
        EspecieDAO.getInstance().create("Peixe");
        EspecieDAO.getInstance().create("Papagaio");
        EspecieDAO.getInstance().create("Passarinho");
        EspecieDAO.getInstance().create("Calopsita");
        EspecieDAO.getInstance().create("Hamster");
        EspecieDAO.getInstance().create("Cobra");
        EspecieDAO.getInstance().create("Aranha");
        EspecieDAO.getInstance().create("Outra");

        AnimalDAO.getInstance().create("Mel",2015,"Fêmea",1,c1);
        AnimalDAO.getInstance().create("Bichano",2002,"Macho",2,c1);
        AnimalDAO.getInstance().create("Quito",2002,"Macho",4,c1);        
        AnimalDAO.getInstance().create("Tutu",2017,"Fêmea",2,c2);
        AnimalDAO.getInstance().create("Luna",2019,"Fêmea",1,c3);
        AnimalDAO.getInstance().create("Max",2019,"Macho",3,c3);
        
        VeterinarioDAO.getInstance().create("Ana Maria Braga","anaM@gmail.com","189987");
        VeterinarioDAO.getInstance().create("Marcio Gomez Ferreira","ferreiramarcio@gmail.com","20984");
        VeterinarioDAO.getInstance().create("Daniel Santos","santod@gmail.com","183466");
        
        Calendar data1 = new GregorianCalendar(2013,1,28,13,24,56);
        Calendar data2 = new GregorianCalendar(2013,1,28,13,24,56);
        Calendar data3 = new GregorianCalendar(2013,1,28,13,24,56);
        Calendar data4 = new GregorianCalendar(2013,1,28,13,24,56);
        TratamentoDAO.getInstance().create("Cinomose",data1,data2,01,true);
        TratamentoDAO.getInstance().create("Rotina",data3,data4,02,false);
        TratamentoDAO.getInstance().create("Dor",data3,data4,03,false); 
        
        Date date = new Date();
        SpinnerDateModel sm = 
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        java.sql.Date sqlHour = new java.sql.Date(date.getTime());
        ConsultaDAO.getInstance().create(data1, "10:02","Consulta de rotina",01,02,01,true);
        ConsultaDAO.getInstance().create(data1, "10:02","Consulta de avaliação",04,02,01,false);
 
        ExameDAO.getInstance().create("Exame de Sangue",01);
        ExameDAO.getInstance().create("Teste de Urina",02);
        
        System.out.println("Clientes: "+ClienteDAO.getInstance().retrieveAll());
        System.out.println("Espécies: "+EspecieDAO.getInstance().retrieveAll());
        System.out.println("Animais: "+AnimalDAO.getInstance().retrieveAll());
        System.out.println("Veterinários: "+VeterinarioDAO.getInstance().retrieveAll());
        System.out.println("Tratamentos: "+TratamentoDAO.getInstance().retrieveAll());
        System.out.println("Consultas: "+ConsultaDAO.getInstance().retrieveAll());
        System.out.println("Exames: "+ExameDAO.getInstance().retrieveAll());
        
    }
} 


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
