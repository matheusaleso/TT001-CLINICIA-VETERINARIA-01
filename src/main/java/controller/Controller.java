package controller;

import static controller.AddData.addAnimal;
import static controller.AddData.addAppointment;
import static controller.AddData.addClient;
import static controller.AddData.addSpecie;
import static controller.AddData.addVet;
import static controller.DeleteData.removeAnimal;
import static controller.DeleteData.removeAppointment;
import static controller.DeleteData.removeClient;
import static controller.DeleteData.removeSpecie;
import static controller.DeleteData.removeVet;
import static controller.DisplayData.displayAnimal;
import static controller.DisplayData.displayAppointment;
import static controller.DisplayData.displayClient;
import static controller.DisplayData.displaySpecie;
import static controller.DisplayData.displayVet;
import static controller.Search.searchAnimal;
import static controller.Search.searchClient;
import static controller.Search.searchConsulta;
import static controller.Search.searchEspecie;
import static controller.Search.searchVeterinario;
import static controller.UpdateData.updateAnimal;
import static controller.UpdateData.updateAppointment;
import static controller.UpdateData.updateClient;
import static controller.UpdateData.updateSpecie;
import static controller.UpdateData.updateVet;
import static controller.setData.animalSetData;
import static controller.setData.appointmentSetData;
import static controller.setData.clientSetData;
import static controller.setData.specieSetData;
import static controller.setData.vetSetData;
import java.text.ParseException;
import javax.swing.JTable;
import javax.swing.JTextField;
import view.GenericTableModel;
import model.Cliente;
import model.ClienteDAO;
import model.Animal;
import model.AnimalDAO;
import model.Especie;
import model.EspecieDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Veterinario;
import model.VeterinarioDAO;

import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import view.AnimalTableModel;
import view.ClienteTableModel;
import view.EspecieTableModel;
import view.VeterinarioTableModel;
import view.principal;
        
public class Controller {
   
   private static HashMap<Integer, Integer> listObjects = new HashMap<>();
            
    public static void setTableModel(JTable table, GenericTableModel tableModel){
       table.setModel(tableModel);
   }
   
   
    public static void addListObject(int rowIndex, int objId){
        listObjects.put(rowIndex,objId);
    }
    
    public static HashMap retrieveListObject(){
        return listObjects;
    }

      public static void setData(String newType, JTable table, JTable table2,
              JComboBox jComboBox1,
              JTextField jTextField2,
              JTextField jTextField4){
        switch(newType){
            case "client":
                clientSetData(table,table2,table.getSelectedRow(),jComboBox1,
                           jTextField2,jTextField4, listObjects);
                break;
            case "animal":
                animalSetData(table,table2,table.getSelectedRow(),jComboBox1,
                           jTextField2,jTextField4, listObjects);
                break;
            case "specie":
                specieSetData(table,table2,table.getSelectedRow(),jComboBox1,
                           jTextField2,jTextField4, listObjects);
                break;
            case "vet":
                vetSetData(table,table2,table.getSelectedRow(),jComboBox1,
                           jTextField2,jTextField4, listObjects);
                break;
            case "appointment":
                appointmentSetData(table,table2,table.getSelectedRow(),jComboBox1,
                           jTextField2,jTextField4, listObjects);
            default:
                System.out.println("Requisição Inválida!");
        }
    }
       
     
    public static void newRegistration(String newType, JTable table) throws ParseException{
        switch(newType){
            case "client":
                addClient(table);
                break;
            case "animal":
                addAnimal(table);
                break;
            case "specie":
                addSpecie(table);
                break;
            case "vet":
                addVet(table);
                break;
            case "appointment":
                addAppointment(table);
                break;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
       
  public static void delete(String newType, JTable table, int SelectedRow){
        switch(newType){
            case "client":
                removeClient(table, SelectedRow, listObjects);
                break;
            case "animal":
                removeAnimal(table, SelectedRow,listObjects);
                break;
            case "specie":
                removeSpecie(table,SelectedRow,listObjects);
                break;
            case "vet":
                removeVet(table,SelectedRow,listObjects);
                break;
            case "appointment":
                removeAppointment(table,SelectedRow,listObjects);
                break;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
  
  public static void update(String newType, JTable table, int SelectedRow) throws ParseException{
        switch(newType){
            case "client":
                updateClient(table, SelectedRow, listObjects);
                break;
            case "animal":
                updateAnimal(table, SelectedRow,listObjects);
                break;
            case "specie":
                updateSpecie(table,SelectedRow,listObjects);
                break;
            case "vet":
                updateVet(table,SelectedRow,listObjects);
                break;
            case "appointment":
                updateAppointment(table,SelectedRow,listObjects);
                break;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
   
    public static void search(String newType, JTable table, JTextField jTextField1) throws ParseException{
        switch(newType){
            case "client":
                searchClient(table, jTextField1);
                break;
            case "animal":
                searchAnimal(table, jTextField1);
                break;
            case "specie":
                searchEspecie(table, jTextField1);
                break;
            case "vet":
                searchVeterinario(table, jTextField1);
                break;
            case "appointment":
                searchConsulta(table, jTextField1);
                break;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
   
    public static void display(String newType, JTable table, int SelectedRow){
        switch(newType){
            case "client":
                displayClient(table, SelectedRow, listObjects);
                break;
            case "animal":
                displayAnimal(table, SelectedRow,listObjects);
                break;
            case "specie":
                displaySpecie(table,SelectedRow,listObjects);
                break;
            case "vet":
                displayVet(table,SelectedRow,listObjects);
                break;
            case "appointment":
                displayVet(table,SelectedRow,listObjects);
                break;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
}

