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
import static controller.setData.animalSetData;
import static controller.setData.clientSetData;
import static controller.setData.specieSetData;
import static controller.setData.vetSetData;
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

      public static void setData(String newType, JTable table,
              JTextField jTextField2,JTextField jTextField3,
              JTextField jTextField4,JTextField jTextField5 ){
        switch(newType){
            case "client":
                clientSetData(table,table.getSelectedRow(),
                           jTextField2,jTextField3,jTextField4,jTextField5, listObjects);
                break;
            case "animal":
                animalSetData(table,table.getSelectedRow(),
                           jTextField2,jTextField3,jTextField4,jTextField5, listObjects);
                break;
            case "specie":
                specieSetData(table,table.getSelectedRow(),
                           jTextField2,jTextField3,jTextField4,jTextField5, listObjects);
                break;
            case "vet":
                vetSetData(table,table.getSelectedRow(),
                           jTextField2,jTextField3,jTextField4,jTextField5, listObjects);
                break;
            case "appointment":
                int d;
            default:
                System.out.println("Requisição Inválida!");
        }
    }
       
     
    public static void newRegistration(String newType, JTable table){
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
  
   
}

