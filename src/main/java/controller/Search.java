/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.ConsultaDAO;
import model.EspecieDAO;
import model.ExameDAO;
import model.VeterinarioDAO;
import view.AnimalTableModel;
import view.ClienteTableModel;
import view.ConsultaTableModel;
import view.EspecieTableModel;
import view.VeterinarioTableModel;

/**
 *
 * @author MATHEUS
 */
public class Search {
    public static void searchClient(JTable table, JTextField jTextField1){
        String nome = jTextField1.getText();
        Controller.setTableModel(table,new ClienteTableModel(ClienteDAO.getInstance().retrieveBySimilarName(nome)));
    }
    
    public static void searchAnimal(JTable table, JTextField jTextField1){
        String nome = jTextField1.getText();
        Controller.setTableModel(table,new AnimalTableModel(AnimalDAO.getInstance().retrieveBySimilarName(nome)));
    }
    
    public static void searchConsulta(JTable table, JTextField jTextField1){
        String nome = jTextField1.getText();
        Controller.setTableModel(table,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveBySimilarName(nome)));
    }
    
    public static void searchEspecie(JTable table, JTextField jTextField1){
        String nome = jTextField1.getText();
        Controller.setTableModel(table,new EspecieTableModel(EspecieDAO.getInstance().retrieveBySimilarName(nome)));
    }
    
    public static void searchVeterinario(JTable table, JTextField jTextField1){
        String nome = jTextField1.getText();
        Controller.setTableModel(table,new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveBySimilarName(nome)));
    }
    
    
}
