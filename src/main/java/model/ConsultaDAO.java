package model;

import java.sql.Date;
import model.Consulta;
import model.DAO;
import model.Animal;
import model.AnimalDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.Comparator;
//import java.util.Date;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }
 
// CRUD    
    public Consulta create(Calendar data, String hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou ) {
        long dataMilliSeconds = data.getTimeInMillis();
        String dataString = String.valueOf(dataMilliSeconds);
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, hora, comentarios, idAnimal, idVet, idTratamento, terminou) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, dataString);
            stmt.setString(2, hora);
            stmt.setString(3, comentarios);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVet);
            stmt.setInt(6, idTratamento);
            stmt.setBoolean(7, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    }
    
    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Consulta lastConsulta = this.retrieveById(lastId("consulta","id"));
        if (lastConsulta != null) {
            return lastConsulta.getComentarios().isEmpty();
        }
        return false;
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            String dataString = rs.getString("data");
            long dataMilliSeconds =  Long.parseLong(dataString);
            Calendar data = Calendar.getInstance();
            data.setTimeInMillis(dataMilliSeconds);
            consulta = new Consulta(rs.getInt("id"), data, rs.getString("hora"), rs.getString("comentarios"), rs.getInt("idAnimal"), rs.getInt("idVet"), rs.getInt("idTratamento"),rs.getBoolean("terminou") );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        List <Consulta> consultas = this.retrieve("SELECT * FROM consulta");
        return retrieveAllSorted(consultas);
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta","id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty()?null:consultas.get(0));
    }
    
        // RetrieveById
    public List retrieveByAnimalId(int animalId) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idAnimal = " + animalId);
         return retrieveAllSorted(consultas);
    }

    
    public List retrieveAllSorted(List <Consulta> consultas) {        
        for (int i = 0; i < consultas.size(); ++i) 
        {
            for (int j = i + 1; j < consultas.size(); ++j)
            {
            Calendar c1 = consultas.get(i).getData();
            Calendar c2 = consultas.get(j).getData();
            int result = c1.compareTo(c2);
                if (result < 0) 
                {
 
                    Consulta a =  consultas.get(i);
                    consultas.set(i, consultas.get(j));
                    consultas.set(j,a);
                }
 
            }
        }
        return consultas;
    }
   
    public List retrieveAllByVet(List <Consulta> consultas) {        
        for (int i = 0; i < consultas.size(); ++i) 
        {
            for (int j = i + 1; j < consultas.size(); ++j)
            {
            int vetID1 = consultas.get(i).getIdVet();
            Veterinario vet1 = VeterinarioDAO.getInstance().retrieveById(vetID1);
            String v1 = vet1.getNome();
            
            int vetID2 = consultas.get(j).getIdVet();
            Veterinario vet2 = VeterinarioDAO.getInstance().retrieveById(vetID2);
            String v2 = vet2.getNome();

            int result = v1.compareTo(v2);
                if (result > 0) 
                {
 
                    Consulta a =  consultas.get(i);
                    consultas.set(i, consultas.get(j));
                    consultas.set(j,a);
                }
 
            }
        }
        return consultas;
    }
    
        public List retrieveAllByIstermined(List <Consulta> consultas) {  
          List <Consulta>  finishedConsultas = new ArrayList();
        for (int i = 0; i < consultas.size(); ++i) 
        {
            if(consultas.get(i).isTerminou()==true){
                finishedConsultas.add(consultas.get(i));
            }
        }
        return retrieveAllSorted(finishedConsultas);
    }
        
    public List retrieveAllByIsnotTermined(List <Consulta> consultas) {  
          List <Consulta>  notFinishedConsultas = new ArrayList();
        for (int i = 0; i < consultas.size(); ++i) 
        {
            if(consultas.get(i).isTerminou()==false){
                notFinishedConsultas.add(consultas.get(i));
            }
        }
        return retrieveAllSorted(notFinishedConsultas);
    }
        public List retrieveAllByClient(List <Consulta> consultas) {        
        for (int i = 0; i < consultas.size(); ++i) 
        {
            for (int j = i + 1; j < consultas.size(); ++j)
            {
            int clienteID1 = consultas.get(i).getIdCliente();
            Cliente cliente1 = ClienteDAO.getInstance().retrieveById(clienteID1);
            String c1 = cliente1.getNome();
            
            int clienteID2 = consultas.get(j).getIdCliente();
            Cliente cliente2 =  ClienteDAO.getInstance().retrieveById(clienteID2);
            String c2 = cliente2.getNome();

            int result = c1.compareTo(c2);
                if (result > 0) 
                {
 
                    Consulta a =  consultas.get(i);
                    consultas.set(i, consultas.get(j));
                    consultas.set(j,a);
                }
 
            }
        }
        return consultas;
    }
        
    public List retrieveToday() {
        List <Consulta> consultas = this.retrieve("SELECT * FROM consulta");
        List <Consulta> consultasToday = new ArrayList();
        
        java.util.Date todayDateDate = Calendar.getInstance().getTime();
        Calendar todayDate = Calendar.getInstance();
        todayDate.setTime(todayDateDate);
        System.out.println(todayDate);
        for (int i = 0; i < consultas.size(); ++i){
            Calendar currentDate = consultas.get(i).getData();
            if( (currentDate.get(Calendar.DAY_OF_MONTH)==todayDate.get(Calendar.DAY_OF_MONTH)) &&  
                (currentDate.get(Calendar.MONTH)==todayDate.get(Calendar.MONTH)) &&
                (currentDate.get(Calendar.YEAR)==todayDate.get(Calendar.YEAR))   
                    ){
                consultasToday.add(consultas.get(i));
            }
        }
        return (consultas.isEmpty()?null:retrieveAllSorted(consultasToday));
    }
        public List retrieveByClienteId(int clienteId) {
        //List <Animal> animais = this.retrieve("SELECT * FROM animal WHERE id_cliente = " + clienteId);
        List <Animal> animais = AnimalDAO.getInstance().retrieveByIdCliente(clienteId);
        List animaisId = new ArrayList();
        animais.forEach(animal -> {
            animaisId.add(animal.getId());
        });
        List<Consulta> consultas = new ArrayList();
        animaisId.forEach(animalId ->{
           List<Consulta> consultasAtuais = this.retrieve("SELECT * FROM consulta WHERE idAnimal = " + animalId);
           consultas.addAll(consultasAtuais);

        });
         
        //List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idAnimal = " + clienteId);
        
      return retrieveAllSorted(consultas);
    }
        
    public List retrieveByVeterinarioId(int veterinarioId) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idVet = " + veterinarioId);
        return retrieveAllSorted(consultas);
    }

   public List retrieveByEspecieId(int veterinarioId) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idVet = " + veterinarioId);
        return retrieveAllSorted(consultas);
    }
    // RetrieveBySimilarComentarios
    public List retrieveBySimilarName(String comentarios) {
         List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE nome LIKE '%" + comentarios + "%'");
         return retrieveAllSorted(consultas);
    }    
        
    // Updade
    public void update(Consulta consulta) {
        long dataMilliSeconds = consulta.getData().getTimeInMillis();
        String dataString = String.valueOf(dataMilliSeconds);

        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, hora=?, comentarios=?, idAnimal=?, idVet=?, idTratamento=?, terminou=? WHERE id=?");
            stmt.setString(1,dataString);
            stmt.setString(2, consulta.getHora());
            stmt.setString(3, consulta.getComentarios());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setBoolean(7, consulta.isTerminou());
            stmt.setInt(8, consulta.getId());
            
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage()+ consulta.getHora());
        }
    }
    
    /*
     public Consulta update(Calendar data, Date hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou ) {
        long dataMilliSeconds = data.getTimeInMillis();
        String dataString = String.valueOf(dataMilliSeconds);
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, hora=?, comentarios=?, idAnimal=?, idVet=?, idTratamento=?, terminou=? VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, dataString);
            stmt.setDate(2, hora);
            stmt.setString(3, comentarios);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVet);
            stmt.setInt(6, idTratamento);
            stmt.setBoolean(7, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    } */
        // Delete   
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
      public void deleteAll() {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta");
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}

