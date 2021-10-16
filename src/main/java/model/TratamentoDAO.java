package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.util.Date;

public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance==null?(instance = new TratamentoDAO()):instance);
    }

// CRUD    
    public Tratamento create(String nome, Calendar dtInicio, Calendar dtFim, int idAnimal, boolean terminou) {
        long dataInicioMilliSeconds = dtInicio.getTimeInMillis();
        String dataInicioString = String.valueOf(dataInicioMilliSeconds); 
        
        long dataFimMilliSeconds = dtFim.getTimeInMillis();
        String dataFimString = String.valueOf(dataFimMilliSeconds);
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO tratamento (nome, dtInicio, dtFim, idAnimal, terminou) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setString(2, dataInicioString);
            stmt.setString(3,dataFimString);
            stmt.setInt(4, idAnimal);
            stmt.setBoolean(5, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento","id"));
    }
    
    // Uma pequena gambiarra, depois explico...
    public boolean isLastEmpty(){
        Tratamento lastClient = this.retrieveById(lastId("tratamento","id"));
        if (lastClient != null) {
            return lastClient.getNome().isEmpty();
        }
        return false;
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        try {
            String dataInicioString = rs.getString("dtInicio");
            long dataInicioMilliSeconds =  Long.parseLong(dataInicioString);
            Calendar dtInicio = Calendar.getInstance();
            dtInicio.setTimeInMillis(dataInicioMilliSeconds);
            
            String dataFimString = rs.getString("dtFim");
            long dataFimMilliSeconds =  Long.parseLong(dataFimString);
            Calendar dtFim = Calendar.getInstance();
            dtFim.setTimeInMillis(dataFimMilliSeconds);
            
            tratamento = new Tratamento(rs.getInt("id"), rs.getString("nome"),dtInicio,dtFim, rs.getInt("idAnimal"), rs.getBoolean("terminou"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM tratamento");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("cliente","id"));
    }

    // RetrieveById
    public Tratamento retrieveById(int id) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (tratamentos.isEmpty()?null:tratamentos.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Updade
    public void update(Tratamento tratamento) {

            long dataInicioMilliSeconds = tratamento.getDtInicio().getTimeInMillis();
            String dataInicioString = String.valueOf(dataInicioMilliSeconds);
            
            long dataFimMilliSeconds = tratamento.getDtFim().getTimeInMillis();
            String dataFimString = String.valueOf(dataFimMilliSeconds);
            
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET nome=?, dtInicio=?, dtFim=?, idAnimal=?, terminou=? WHERE id=?");
            stmt.setString(1, tratamento.getNome());
            stmt.setString(2,dataInicioString);
            stmt.setString(3,dataFimString);
            stmt.setInt(4, tratamento.getIdAnimal());
            stmt.setBoolean(5, tratamento.isTerminou());
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
        // Delete   
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
      public void deleteAll() {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento");
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

}
