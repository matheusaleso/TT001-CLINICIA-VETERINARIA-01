/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.toedter.calendar.JDateChooser;
import static controller.Controller.setTableModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.EspecieDAO;
import model.VeterinarioDAO;
import view.AnimalTableModel;
import view.ClienteTableModel;
import view.EspecieTableModel;
import view.VeterinarioTableModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import model.ConsultaDAO;
import view.ConsultaTableModel;
import java.awt.event.*;  

/**
 *
 * @author MATHEUS
 */
public class AddData {
        public static void addClient(JTable table){
        JTextField clientName = new JTextField();
        JTextField clientAddress = new JTextField();
        JTextField clientCEP = new JTextField();
        JTextField clientPhone = new JTextField();
        JTextField clientEmail = new JTextField();
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        clientName,
        new JLabel("Endereço:"),
        clientAddress,
        new JLabel("CEP:"),
        clientCEP,
        new JLabel("Telefone:"),
        clientPhone,
        new JLabel("E-mail:"),
        clientEmail,
};
int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE CLIENTE", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
            ClienteDAO.getInstance().create(clientName.getText(),
                clientAddress.getText(),clientCEP.getText(),clientPhone.getText(),clientEmail.getText());
            
           setTableModel(table,new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
        

public static void addAnimal(JTable table){
        JTextField animalName = new JTextField();
        JTextField animalBirthyear = new JTextField();
        JTextField animalSex = new JTextField();
        JTextField animalEspecieID = new JTextField();
        JTextField animalClientID = new JTextField();
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        animalName,
        new JLabel("Ano de Nascimento:"),
        animalBirthyear,
        new JLabel("Sexo:"),
        animalSex,
        new JLabel("ID da Espécie:"),
        animalEspecieID,
        new JLabel("ID do Cliente:"),
        animalClientID,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    
    int animalBirthyearInteger = Integer.parseInt(animalBirthyear.getText());
    int especieId = Integer.parseInt(animalEspecieID.getText());
    int clientId = Integer.parseInt(animalClientID.getText()); 
    Cliente cliente = ClienteDAO.getInstance().retrieveById(clientId);
   
    AnimalDAO.getInstance().create(animalName.getText(),
                animalBirthyearInteger,
                animalSex.getText(),
                especieId,
                cliente);
    
    setTableModel(table,new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}

      public static void addSpecie(JTable table){
        JTextField specieName = new JTextField();
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        specieName,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    EspecieDAO.getInstance().create(specieName.getText());
    setTableModel(table,new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
      
        public static void addVet(JTable table){
        JTextField vetName = new JTextField();
        JTextField vetEmail = new JTextField();
        JTextField vetPhone = new JTextField();

        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        vetName,
        new JLabel("E-mail:"),
        vetEmail,
        new JLabel("Telefone:"),
        vetPhone,

};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE VETERINÁRIO", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    VeterinarioDAO.getInstance().create(vetName.getText(),
                vetEmail.getText(),
                vetPhone.getText());
    setTableModel(table,new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
             
  public static void addAppointment(JTable table){

        JDateChooser appointmentData = new JDateChooser();
        JTextField comentarios = new JTextField();
        JTextField animalID = new JTextField();
        JTextField vetID = new JTextField();
        JTextField tratamentoID = new JTextField();
        JCheckBox status = new JCheckBox("Tratamento Concluído"); 
 
        Date date = new Date();
        SpinnerDateModel sm = 
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        JSpinner hourAppointment = new JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(hourAppointment, "hh:mm");
        hourAppointment.setEditor(de);
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Data da Consulta:"),
        appointmentData,
        new JLabel("Horário da Consulta:"),
        hourAppointment,
        new JLabel("Comentários:"),
        comentarios,
        new JLabel("ID do Animal:"),
        animalID,
        new JLabel("ID do Veterinario:"),
        tratamentoID,
        new JLabel("ID do Tratamento:"),
        vetID,
        status,
        
        
};
        
status.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {  
                 
             }    
          });
        
int result = JOptionPane.showConfirmDialog(null, inputs, "NOVA CONSULTA", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    
Calendar dataAppointment = appointmentData.getCalendar();
java.sql.Date sqlHour = new java.sql.Date(date.getTime());

ConsultaDAO.getInstance().create(dataAppointment, sqlHour,
        comentarios.getText(),Integer.parseInt(animalID.getText()),
        Integer.parseInt(tratamentoID.getText()),Integer.parseInt(vetID.getText()),status.isSelected());
   
    setTableModel(table,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
 
}

