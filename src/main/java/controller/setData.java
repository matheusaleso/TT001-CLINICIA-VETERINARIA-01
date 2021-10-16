/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Especie;
import model.EspecieDAO;

/**
 *
 * @author MATHEUS
 */
public class setData {
      public static void clientSetData(JTable table, int SelectedRow, 
        JTextField  jTextField2, JTextField  jTextField3, 
        JTextField  jTextField4,JTextField  jTextField5, HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int clienteId = (int) listObjects.get(rowIndex);
        Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
        
                   jTextField2.setText(" ");
                    jTextField3.setText(" ");
                    jTextField4.setText(" ");
                    jTextField5.setText(" ");
                    
                    jTextField2.setText(cliente.getNome());
                    jTextField3.setText("-");
                    jTextField4.setText("-");        
    }
    
        public static void animalSetData(JTable table, int SelectedRow, 
        JTextField  jTextField2, JTextField  jTextField3, 
        JTextField  jTextField4,JTextField  jTextField5, HashMap<Integer, Integer> listObjects){
                      jTextField2.setText(" ");
                      jTextField3.setText(" ");
                      jTextField4.setText(" ");
                      jTextField5.setText(" ");
                      
               int rowIndex = (int)table.getSelectedRow();
               int animalId = (int) listObjects.get(rowIndex);
               Animal animal = AnimalDAO.getInstance().retrieveById(animalId);
               int clienteId = animal.getIdCliente();
               Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
               int especieId = animal.getIdEspecie();
               Especie especie = EspecieDAO.getInstance().retrieveById(especieId);
               Consulta consulta = ConsultaDAO.getInstance().retrieveByAnimalId(animalId);
               
               jTextField2.setText(cliente.getNome());
               jTextField3.setText(animal.getNome());
               jTextField4.setText(especie.getNome());
               jTextField5.setText(consulta.toString());
        }
    
          public static void specieSetData(JTable table, int SelectedRow, 
        JTextField  jTextField2, JTextField  jTextField3, 
        JTextField  jTextField4,JTextField  jTextField5, HashMap<Integer, Integer> listObjects){
                    jTextField2.setText(" ");
                    jTextField3.setText(" ");
                    jTextField4.setText(" ");
                    jTextField5.setText(" ");
                      
        int rowIndex = (int)table.getSelectedRow();
        int specieId = (int) listObjects.get(rowIndex);
        Especie especie = EspecieDAO.getInstance().retrieveById(specieId);
        
                    jTextField2.setText("-");
                    jTextField3.setText("-");
                    jTextField4.setText(especie.getNome());
          
          }
        
 public static void vetSetData(JTable table, int SelectedRow, 
        JTextField  jTextField2, JTextField  jTextField3, 
        JTextField  jTextField4,JTextField  jTextField5, HashMap<Integer, Integer> listObjects){
                    jTextField2.setText(" ");
                    jTextField3.setText(" ");
                    jTextField4.setText(" ");
                    jTextField5.setText(" ");
                    
        int rowIndex = (int)table.getSelectedRow();
        int veterinarioId = (int) listObjects.get(rowIndex);
                    jTextField2.setText("-");
                    jTextField3.setText("-");
                    jTextField4.setText("-");
        Consulta consulta = ConsultaDAO.getInstance().retrieveByVeterinarioId(veterinarioId);
        jTextField5.setText(consulta.toString());
                    
                    
 }
}
