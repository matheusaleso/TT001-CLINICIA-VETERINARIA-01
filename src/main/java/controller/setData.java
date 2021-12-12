/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Controller.setTableModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Especie;
import model.EspecieDAO;
import view.ClienteTableModel;
import view.ConsultaTableModel;
import view.GenericTableModel;

/**
 *
 * @author MATHEUS
 */
public class setData {
      public static void clientSetData(JTable table, JTable table2, int SelectedRow,
        JComboBox jComboBox1,
        JTextField  jTextField2, 
        JTextField  jTextField4, HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int clienteId = (int) listObjects.get(rowIndex);
        Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
                   setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));
                   jTextField2.setText(" ");
                    jTextField4.setText(" ");
                    
                    jTextField2.setText(cliente.getNome());
                    jTextField4.setText("-");   
        HashMap<String, Integer> animaisHash = new HashMap<String, Integer>();            
        List <Animal> animais = AnimalDAO.getInstance().retrieveByIdCliente(clienteId);
        List <String> animaisName = new ArrayList();
        animais.forEach(animal -> {
            animaisName.add(animal.getNome());
            animaisHash.put(animal.getNome(), animal.getId());
        });
        String[] animalArray = animaisName.toArray(new String[animaisName.size()]);
       jComboBox1.setModel(new DefaultComboBoxModel(animalArray));
       int selectAnimalFirst = animaisHash.get(jComboBox1.getSelectedItem());
      setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(selectAnimalFirst)));

      
       jComboBox1.addActionListener (new ActionListener () {
    public void actionPerformed(ActionEvent e) {
        int selectAnimal = animaisHash.get(jComboBox1.getSelectedItem());
       setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(selectAnimal)));
    }
});
       
                  
    }
    
        public static void animalSetData(JTable table, JTable table2, int SelectedRow, 
        JComboBox jComboBox1,JTextField  jTextField2, 
        JTextField  jTextField4, HashMap<Integer, Integer> listObjects){
                      jTextField2.setText(" ");
                      jTextField4.setText(" ");
                      
               List <String> defaultArray = new ArrayList();
               defaultArray.add("-");
               String[] nullArray = defaultArray.toArray(new String[defaultArray.size()]);
               jComboBox1.setModel(new DefaultComboBoxModel(nullArray));
               setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));
               int rowIndex = (int)table.getSelectedRow();
               int animalId = (int) listObjects.get(rowIndex);
               Animal animal = AnimalDAO.getInstance().retrieveById(animalId);
               int clienteId = animal.getIdCliente();
               System.out.println("Id do cliente: " + clienteId);
               Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
               int especieId = animal.getIdEspecie();
               Especie especie = EspecieDAO.getInstance().retrieveById(especieId);
               //Consulta consulta = ConsultaDAO.getInstance().retrieveByAnimalId(animalId);
               List <String> animalArray = new ArrayList();
              animalArray.add(animal.getNome());
              String[] animalFinalArray = animalArray.toArray(new String[animalArray.size()]);
                jComboBox1.setModel(new DefaultComboBoxModel(animalFinalArray));
               // consultas = (consultas.isEmpty()?null:consultas);
               jTextField2.setText(cliente.getNome());
               jTextField4.setText(especie.getNome());
               setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(animalId)));

        }
    
          public static void specieSetData(JTable table, JTable table2, int SelectedRow, 
       JComboBox jComboBox1,JTextField  jTextField2, 
        JTextField  jTextField4, HashMap<Integer, Integer> listObjects){
                    jTextField2.setText(" ");
                    jTextField4.setText(" ");
                 List <String> defaultArray = new ArrayList();
                 defaultArray.add("-");
                 String[] nullArray = defaultArray.toArray(new String[defaultArray.size()]);
                jComboBox1.setModel(new DefaultComboBoxModel(nullArray));
                    setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));  
        int rowIndex = (int)table.getSelectedRow();
        int specieId = (int) listObjects.get(rowIndex);
        Especie especie = EspecieDAO.getInstance().retrieveById(specieId);
        
                    jTextField2.setText("-");
                    jTextField4.setText(especie.getNome());
          
          }
        
 public static void vetSetData(JTable table, JTable table2, int SelectedRow, 
       JComboBox jComboBox1, JTextField  jTextField2,  
        JTextField  jTextField4, HashMap<Integer, Integer> listObjects){
                    jTextField2.setText(" ");
                    jTextField4.setText(" ");
                  List <String> defaultArray = new ArrayList();
                 defaultArray.add("-");
                 String[] nullArray = defaultArray.toArray(new String[defaultArray.size()]);
                jComboBox1.setModel(new DefaultComboBoxModel(nullArray));
                    setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));

        int rowIndex = (int)table.getSelectedRow();
        int veterinarioId = (int) listObjects.get(rowIndex);
                    jTextField2.setText("-");
                    jTextField4.setText("-");
       setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByVeterinarioId(veterinarioId)));
                    
                    
 }
 
  public static void appointmentSetData(JTable table, JTable table2, int SelectedRow, 
        JComboBox jComboBox1,JTextField  jTextField2, 
        JTextField  jTextField4, HashMap<Integer, Integer> listObjects){
                    jTextField2.setText(" ");
                    jTextField4.setText(" ");
                 List <String> defaultArray = new ArrayList();
                 defaultArray.add("-");
                 String[] nullArray = defaultArray.toArray(new String[defaultArray.size()]);
                jComboBox1.setModel(new DefaultComboBoxModel(nullArray));
                    setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));

        int rowIndex = (int)table.getSelectedRow();
        int veterinarioId = (int) listObjects.get(rowIndex);
                    jTextField2.setText("-");
                    jTextField4.setText("-");
                    setTableModel(table2,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveByAnimalId(-1)));

                    
                    
 }
}
