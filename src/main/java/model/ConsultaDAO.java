package model;

import java.sql.Date;
import model.Consulta;
import model.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
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
    public Consulta create(Calendar data, Date hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou ) {
        long dataMilliSeconds = data.getTimeInMillis();
        String dataString = String.valueOf(dataMilliSeconds);
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, hora, comentarios, idAnimal, idVet, idTratamento, terminou) VALUES (?,?,?,?,?,?,?)");
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
            consulta = new Consulta(rs.getInt("id"), data, rs.getDate("hora"), rs.getString("comentarios"), rs.getInt("idAnimal"), rs.getInt("idVet"), rs.getInt("idTratamento"),rs.getBoolean("terminou") );
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
        return this.retrieve("SELECT * FROM consulta");
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
    public Consulta retrieveByAnimalId(int animalId) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idAnimal = " + animalId);
        return (consultas.isEmpty()?null:consultas.get(0));
    }
    
        public Consulta retrieveByVeterinarioId(int veterinarioId) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE idVet = " + veterinarioId);
        return (consultas.isEmpty()?null:consultas.get(0));
    }

    // RetrieveBySimilarComentarios
    public List retrieveBySimilarName(String comentarios) {
        return this.retrieve("SELECT * FROM consulta WHERE nome LIKE '%" + comentarios + "%'");
    }    
        
    // Updade
    public void update(Consulta consulta) {
        long dataMilliSeconds = consulta.getData().getTimeInMillis();
        String dataString = String.valueOf(dataMilliSeconds);
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, hora=?, comentarios=?, idAnimal=?, idVet=?, idTratamento=?, terminou? WHERE id=?");
            stmt.setString(1,dataString);
            stmt.setDate(2, (Date) consulta.getHora());
            stmt.setString(3, consulta.getComentarios());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setBoolean(7, consulta.isTerminou());
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
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

