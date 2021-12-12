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
import model.TratamentoDAO;
import model.Especie;
import model.VeterinarioDAO;
import view.AnimalTableModel;
import view.ClienteTableModel;
import view.EspecieTableModel;
import view.VeterinarioTableModel;
import java.util.HashMap;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import model.Animal;
import model.Tratamento;
import model.Veterinario;

/**
 *
 * @author MATHEUS
 */
public class AddData {
        public static void addClient(JTable table){
        JTextField clientName = new JTextField();
        JTextField clientEmail = new JTextField();
        JTextField clientPhone = new JTextField();
        JTextField clientAddress = new JTextField();
        JTextField clientCEP = new JTextField();
        
        
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        clientName,
        new JLabel("E-mail:"),
        clientEmail,
        new JLabel("Telefone:"),
        clientPhone,
        new JLabel("Endereço:"),
        clientAddress,
        new JLabel("CEP:"),
        clientCEP,

        
};
int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE CLIENTE", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
            ClienteDAO.getInstance().create(clientName.getText(),clientEmail.getText(),clientPhone.getText(),
                clientAddress.getText(),clientCEP.getText());
            
           setTableModel(table,new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
        

public static void addAnimal(JTable table){
        JTextField animalName = new JTextField();
        JTextField animalBirthyear = new JTextField();
        
        HashMap<String, Integer> especiesByName = new HashMap<String, Integer>();
        List <Especie> especies = EspecieDAO.getInstance().retrieveAll();
        List <String> especiesName = new ArrayList();
        especies.forEach(especie -> {
            especiesName.add(especie.getNome());
            especiesByName.put(especie.getNome(), especie.getId());
        });
        String[] specieArray = especiesName.toArray(new String[especiesName.size()]);
        JComboBox especieComboBox = new JComboBox(specieArray);
        
         String[] sexArray = {"Fêmea", "Macho"};
        JComboBox sexComboBox = new JComboBox(sexArray);
        
        HashMap<String, Integer> clientByName = new HashMap<String, Integer>();
        List <Cliente> clientes = ClienteDAO.getInstance().retrieveAll();
        List <String> clientesName = new ArrayList();
       clientes.forEach(cliente -> {
            clientesName.add(cliente.getNome());
            clientByName.put(cliente.getNome(), cliente.getId());
        });
        String[] clientArray = clientesName.toArray(new String[clientesName.size()]);
        JComboBox clienteComboBox = new JComboBox(clientArray); 
       
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        animalName,
        new JLabel("Ano de Nascimento:"),
        animalBirthyear,
        new JLabel("Sexo:"),
        sexComboBox,
        new JLabel("Espécie:"),
        especieComboBox,
        //animalEspecieID,
        new JLabel("Cliente:"),
        clienteComboBox,
        //animalClientID,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    
    int animalBirthyearInteger = Integer.parseInt(animalBirthyear.getText());
   // int especieId = Integer.parseInt(animalEspecieID.getText());
    int especieId = especiesByName.get(especieComboBox.getSelectedItem());
   // int clientId = Integer.parseInt(animalClientID.getText()); 
    int clientId = clientByName.get(clienteComboBox.getSelectedItem()); 
    Cliente cliente = ClienteDAO.getInstance().retrieveById(clientId);
   
    AnimalDAO.getInstance().create(animalName.getText(),
                animalBirthyearInteger, (String) sexComboBox.getSelectedItem(),
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
             
  public static void addAppointment(JTable table) throws ParseException{

        JDateChooser appointmentData = new JDateChooser();
        JTextField comentarios = new JTextField();
        JCheckBox status = new JCheckBox("Tratamento Concluído"); 
 
        HashMap<String, Integer> animalByName = new HashMap<String, Integer>();
        List <Animal> animais = AnimalDAO.getInstance().retrieveAll();
        List <String> animaisName = new ArrayList();
       animais.forEach(animal -> {
            animaisName.add(animal.getNome());
            animalByName.put(animal.getNome(), animal.getId());
        });
        String[] animalArray = animaisName.toArray(new String[animaisName.size()]);
        JComboBox animalComboBox = new JComboBox(animalArray);
        
        HashMap<String, Integer> vetByName = new HashMap<String, Integer>();
        List <Veterinario> veterinarios = VeterinarioDAO.getInstance().retrieveAll();
        List <String> veterinariosName = new ArrayList();
       veterinarios.forEach(veterinario -> {
            veterinariosName.add(veterinario.getNome());
            vetByName.put(veterinario.getNome(), veterinario.getId());
        });
        String[] veterinarioArray = veterinariosName.toArray(new String[veterinariosName.size()]);
        JComboBox veterinarioComboBox = new JComboBox(veterinarioArray);
        
        HashMap<String, Integer> tratamentoByName = new HashMap<String, Integer>();
        List <Tratamento> tratamentos = TratamentoDAO.getInstance().retrieveAll();
        List <String> tratamentosName = new ArrayList();
       tratamentos.forEach(tratamento -> {
            tratamentosName.add(tratamento.getNome());
            tratamentoByName.put(tratamento.getNome(), tratamento.getId());
        });
        String[] tratamentoArray = tratamentosName.toArray(new String[tratamentosName.size()]);
        JComboBox tratamentoComboBox = new JComboBox(tratamentoArray);
        
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
        new JLabel("Animal:"),
        animalComboBox,
        //animalID,
        new JLabel("Veterinario:"),
        veterinarioComboBox,
        new JLabel("Tratamento:"),
        tratamentoComboBox,
        //tratamentoID,
        status,
        
        
};
        
status.addItemListener(new ItemListener() {    
             public void itemStateChanged(ItemEvent e) {  
                 
             }    
          });
        
int result = JOptionPane.showConfirmDialog(null, inputs, "NOVA CONSULTA", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {


String hora  = de.getFormat().format(hourAppointment.getValue());
// Date date1 = new SimpleDateFormat("hh:mm").parse(hourAppointment.getValue().toString());
Calendar dataAppointment = appointmentData.getCalendar();
System.out.println("Hora:" + hora);
int animalId = animalByName.get(animalComboBox.getSelectedItem());
int vetId = vetByName.get(veterinarioComboBox.getSelectedItem());
int tratamentoId = tratamentoByName.get(tratamentoComboBox.getSelectedItem());

ConsultaDAO.getInstance().create(dataAppointment, hora,
        comentarios.getText(),animalId,vetId,
        tratamentoId,status.isSelected());
   
    setTableModel(table,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
 
}

