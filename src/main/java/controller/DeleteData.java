/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Controller.setTableModel;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import model.Veterinario;
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
public class DeleteData {
    public static void removeClient(JTable table, int SelectedRow, HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int clienteId = (int) listObjects.get(rowIndex);
        Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);

        JTextField clientName = new JTextField();
        clientName.setText(cliente.getNome());
        JTextField clientAddress = new JTextField();
        clientAddress.setText(cliente.getEndereco());
        JTextField clientCEP = new JTextField();
        clientCEP.setText(cliente.getCep());
        JTextField clientPhone = new JTextField();
        clientPhone.setText(cliente.getTelefone());
        JTextField clientEmail = new JTextField();
        clientEmail.setText(cliente.getEmail());
        
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
int result = JOptionPane.showConfirmDialog(null, inputs, "EXCLUIR CLIENTE", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
           ClienteDAO.getInstance().delete(cliente);
           setTableModel(table,new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
   
  public static void removeAnimal(JTable table, int SelectedRow, HashMap<Integer, Integer> listObjects){
       int rowIndex = (int)table.getSelectedRow();
       int animalId = (int) listObjects.get(rowIndex);
       Animal animal = AnimalDAO.getInstance().retrieveById(animalId);
       int clienteId = animal.getIdCliente();
       Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
       int especieId = animal.getIdEspecie();
       Especie especie = EspecieDAO.getInstance().retrieveById(especieId);
               
        JTextField animalName = new JTextField();
        animalName.setText(animal.getNome());
        JTextField animalBirthyear = new JTextField();
        animalBirthyear.setText(Integer.toString(animal.getAnoNasc()));
        JTextField animalSex = new JTextField();
        animalSex.setText(animal.getSexo());
        JTextField animalEspecie = new JTextField();
        animalEspecie.setText(especie.getNome());
        JTextField animalClient = new JTextField();
        animalClient.setText(cliente.getNome());
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        animalName,
        new JLabel("Ano de Nascimento:"),
        animalBirthyear,
        new JLabel("Sexo:"),
        animalSex,
        new JLabel("Espécie:"),
        animalEspecie,
        new JLabel("Dono:"),
        animalClient,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {

    AnimalDAO.getInstance().delete(animal);
    setTableModel(table,new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
 public static void removeSpecie(JTable table, int SelectedRow,HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int specieId = (int) listObjects.get(rowIndex);
        Especie especie = EspecieDAO.getInstance().retrieveById(specieId);
        
        JTextField specieName = new JTextField();
        specieName.setText(especie.getNome());
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        specieName,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    EspecieDAO.getInstance().delete(especie);
    setTableModel(table,new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
public static void removeVet(JTable table, int selectedRow,HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int vetId = (int) listObjects.get(rowIndex);
        Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(vetId);
        
        JTextField vetName = new JTextField();
        vetName.setText(veterinario.getNome());
        JTextField vetEmail = new JTextField();
        vetEmail.setText(veterinario.getEmail());
        JTextField vetPhone = new JTextField();
        vetPhone.setText(veterinario.getTelefone());

        
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
    VeterinarioDAO.getInstance().delete(veterinario);
    setTableModel(table,new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
    public static void removeAppointment(JTable table, int selectedRow,HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int appointmentId = (int) listObjects.get(rowIndex);
        Consulta consulta = ConsultaDAO.getInstance().retrieveById(appointmentId);
        

        JTextField appointmentDate = new JTextField();
        appointmentDate.setText(consulta.dataConsulta());
        JTextField appointmentHour = new JTextField();
        appointmentHour.setText(consulta.getStringHour());
        JTextField appointmentComments = new JTextField();
        appointmentComments.setText(consulta.getComentarios());
        JTextField appointmentAnimal = new JTextField();
        appointmentAnimal.setText(Integer.toString(consulta.getIdAnimal()));
        JTextField appointmentVet = new JTextField();
        appointmentVet.setText(Integer.toString(consulta.getIdVet()));
        JTextField appointmentTrat = new JTextField();
        appointmentTrat.setText(Integer.toString(consulta.getIdTratamento()));
        JTextField appointmentStatus = new JTextField();
        appointmentStatus.setText(consulta.terminouTostring());
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Data da Consulta:"),
        appointmentDate,
        new JLabel("Horário da Consulta:"),
        appointmentHour,
        new JLabel("Comentários sobre a Consulta:"),
        appointmentComments,
        new JLabel("Animal que foi atendido:"),
        appointmentAnimal,
        new JLabel("Veterinário que atendeu:"),
        appointmentVet,
        new JLabel("Tratamento:"),
        appointmentTrat,
        new JLabel("Situação do Tratamento:"),
        appointmentStatus

};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE VETERINÁRIO", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    ConsultaDAO.getInstance().delete(consulta);
    setTableModel(table,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
}
