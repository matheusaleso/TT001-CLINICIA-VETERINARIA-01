/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.toedter.calendar.JDateChooser;
import static controller.Controller.setTableModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import model.Animal;
import model.AnimalDAO;
import model.Cliente;
import model.ClienteDAO;
import model.Consulta;
import model.ConsultaDAO;
import model.Especie;
import model.EspecieDAO;
import model.Tratamento;
import model.TratamentoDAO;
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
public class UpdateData {
    public static void updateClient(JTable table, int SelectedRow, HashMap<Integer, Integer> listObjects){
        int rowIndex = (int)table.getSelectedRow();
        int clienteId = (int) listObjects.get(rowIndex);
        Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
        
        JTextField clientName = new JTextField();
        clientName.setText(cliente.getNome());
        
        JTextField clientEmail = new JTextField();
        clientEmail.setText(cliente.getEmail());
        
        JTextField clientPhone = new JTextField();
        clientPhone.setText(cliente.getTelefone());
        
        JTextField clientAddress = new JTextField();
        clientAddress.setText(cliente.getEndereco());
        
        JTextField clientCEP = new JTextField();
        clientCEP.setText(cliente.getCep());
        
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
            cliente.setNome(clientName.getText());
            cliente.setEmail(clientEmail.getText());
            cliente.setTelefone(clientPhone.getText());
            cliente.setEndereco(clientAddress.getText());
            cliente.setCep(clientCEP.getText());
            System.out.println("Nome: " + cliente.getNome() + "\nE-mail: " + cliente.getEmail() +
                    "\nTelefone: " + cliente.getTelefone() + "\nEndereço: " + cliente.getEndereco() +
                    "\nCEP: " + cliente.getCep());
            ClienteDAO.getInstance().update(cliente); 
           setTableModel(table,new ClienteTableModel(ClienteDAO.getInstance().retrieveAll()));
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
   
  public static void updateAnimal(JTable table, int SelectedRow, HashMap<Integer, Integer> listObjects){
       int rowIndex = (int)table.getSelectedRow();
       int animalId = (int) listObjects.get(rowIndex);
       Animal animal = AnimalDAO.getInstance().retrieveById(animalId);
       int clienteId = animal.getIdCliente();
       Cliente cliente = ClienteDAO.getInstance().retrieveById(clienteId);
       int especieId = animal.getIdEspecie();
        Especie especieAnimal = EspecieDAO.getInstance().retrieveById(especieId);
        
        HashMap<String, Integer> especiesByName = new HashMap<String, Integer>();
        List <Especie> especies = EspecieDAO.getInstance().retrieveAll();
        List <String> especiesName = new ArrayList();
        especies.forEach(especie -> {
            especiesName.add(especie.getNome());
            especiesByName.put(especie.getNome(), especie.getId());
        });
        String[] specieArray = especiesName.toArray(new String[especiesName.size()]);
        JComboBox especieComboBox = new JComboBox(specieArray);
        especieComboBox.setSelectedItem(especieAnimal.getNome());
        
         String[] sexArray = {"Fêmea", "Macho"};
        JComboBox sexComboBox = new JComboBox(sexArray);
        sexComboBox.setSelectedItem(animal.getSexo());
        
       HashMap<String, Integer> clientByName = new HashMap<String, Integer>();
        List <Cliente> clientes = ClienteDAO.getInstance().retrieveAll();
        List <String> clientesName = new ArrayList();
       clientes.forEach(cliente1 -> {
            clientesName.add(cliente1.getNome());
            clientByName.put(cliente1.getNome(), cliente1.getId());
        });
        String[] clientArray = clientesName.toArray(new String[clientesName.size()]);
        JComboBox clienteComboBox = new JComboBox(clientArray); 
        clienteComboBox.setSelectedItem(cliente.getNome());
        
        JTextField animalName = new JTextField();
        animalName.setText(animal.getNome());
        JTextField animalBirthyear = new JTextField();
        animalBirthyear.setText(Integer.toString(animal.getAnoNasc()));
        JTextField animalSex = new JTextField();
        animalSex.setText(animal.getSexo());
        JTextField animalClient = new JTextField();
        animalClient.setText(cliente.getNome());
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Nome:"),
        animalName,
        new JLabel("Ano de Nascimento:"),
        animalBirthyear,
        new JLabel("Sexo:"),
        sexComboBox,
        new JLabel("Espécie:"),
        especieComboBox,
        new JLabel("Dono:"),
        clienteComboBox,
};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE ANIMAL", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    animal.setNome(animalName.getText());
    animal.setAnoNasc(Integer.parseInt(animalBirthyear.getText()));
    animal.setSexo(sexComboBox.getSelectedItem().toString());
    animal.setIdEspecie(especiesByName.get(especieComboBox.getSelectedItem()));
    animal.setIdCliente(clientByName.get(clienteComboBox.getSelectedItem()));
    AnimalDAO.getInstance().update(animal);
    setTableModel(table,new AnimalTableModel(AnimalDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
 public static void updateSpecie(JTable table, int SelectedRow,HashMap<Integer, Integer> listObjects){
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
    especie.setNome(specieName.getText());
    EspecieDAO.getInstance().update(especie);
    setTableModel(table,new EspecieTableModel(EspecieDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
  
public static void updateVet(JTable table, int selectedRow,HashMap<Integer, Integer> listObjects){
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
    veterinario.setNome(vetName.getText());
    veterinario.setEmail(vetEmail.getText());
    veterinario.setTelefone(vetPhone.getText());
    VeterinarioDAO.getInstance().update(veterinario);
    setTableModel(table,new VeterinarioTableModel(VeterinarioDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
    public static void updateAppointment(JTable table, int selectedRow,HashMap<Integer, Integer> listObjects) throws ParseException{
        int rowIndex = (int)table.getSelectedRow();
        int appointmentId = (int) listObjects.get(rowIndex);
        Consulta consulta = ConsultaDAO.getInstance().retrieveById(appointmentId);
        Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
        Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(consulta.getIdVet());
        Tratamento tratamento = TratamentoDAO.getInstance().retrieveById(consulta.getIdTratamento());
                
        JTextField appointmentDate = new JTextField();
        appointmentDate.setText(consulta.dataConsulta());
        JTextField appointmentHour = new JTextField();
        appointmentHour.setText(consulta.getHora());
        JTextField appointmentComments = new JTextField();
        appointmentComments.setText(consulta.getComentarios());
        JTextField appointmentAnimal = new JTextField();
        appointmentAnimal.setText(animal.getNome());
        JTextField appointmentVet = new JTextField();
        appointmentVet.setText(veterinario.getNome());
        JTextField appointmentTrat = new JTextField();
        appointmentTrat.setText(tratamento.getNome());
        JTextField appointmentStatus = new JTextField();
        appointmentStatus.setText(consulta.terminouTostring());
        
        JDateChooser appointmentData = new JDateChooser();
        appointmentData.setCalendar(consulta.getData());
        
        Date date = new Date();
        SpinnerDateModel sm = 
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        JSpinner hourAppointment = new JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(hourAppointment, "hh:mm");
        hourAppointment.setEditor(de);
        hourAppointment.setValue(consulta.getHourAsDate());
        
        HashMap<String, Integer> animalByName = new HashMap<String, Integer>();
        List <Animal> animais = AnimalDAO.getInstance().retrieveAll();
        List <String> animaisName = new ArrayList();
       animais.forEach(animal1 -> {
            animaisName.add(animal1.getNome());
            animalByName.put(animal1.getNome(), animal1.getId());
        });
        String[] animalArray = animaisName.toArray(new String[animaisName.size()]);
        JComboBox animalComboBox = new JComboBox(animalArray);
        animalComboBox.setSelectedItem(animal.getNome());
        
        HashMap<String, Integer> vetByName = new HashMap<String, Integer>();
        List <Veterinario> veterinarios = VeterinarioDAO.getInstance().retrieveAll();
        List <String> veterinariosName = new ArrayList();
       veterinarios.forEach(veterinario1 -> {
            veterinariosName.add(veterinario1.getNome());
            vetByName.put(veterinario1.getNome(), veterinario1.getId());
        });
        String[] veterinarioArray = veterinariosName.toArray(new String[veterinariosName.size()]);
        JComboBox veterinarioComboBox = new JComboBox(veterinarioArray);
        veterinarioComboBox.setSelectedItem(veterinario.getNome());
        
        HashMap<String, Integer> tratamentoByName = new HashMap<String, Integer>();
        List <Tratamento> tratamentos = TratamentoDAO.getInstance().retrieveAll();
        List <String> tratamentosName = new ArrayList();
       tratamentos.forEach(tratamento1 -> {
            tratamentosName.add(tratamento1.getNome());
            tratamentoByName.put(tratamento1.getNome(), tratamento1.getId());
        });
        String[] tratamentoArray = tratamentosName.toArray(new String[tratamentosName.size()]);
        JComboBox tratamentoComboBox = new JComboBox(tratamentoArray);
        tratamentoComboBox.setSelectedItem(tratamento.getNome());
        
        JCheckBox status = new JCheckBox("Tratamento Concluído");
        status.setSelected(consulta.isTerminou());
        
        final JComponent[] inputs = new JComponent[] {
        new JLabel("Data da Consulta:"),
        appointmentData,
        new JLabel("Horário da Consulta:"),
        hourAppointment,
        new JLabel("Comentários sobre a Consulta:"),
        appointmentComments,
        new JLabel("Animal que foi atendido:"),
        animalComboBox,
        new JLabel("Veterinário que atendeu:"),
        veterinarioComboBox,
        new JLabel("Tratamento:"),
        tratamentoComboBox,
        status

};


int result = JOptionPane.showConfirmDialog(null, inputs, "CADASTRO DE VETERINÁRIO", JOptionPane.PLAIN_MESSAGE);
if (result == JOptionPane.OK_OPTION) {
    Calendar dataAppointment = appointmentData.getCalendar();
    consulta.setData(dataAppointment);
    
    String hora  = de.getFormat().format(hourAppointment.getValue());
    consulta.setHora(hora);
    consulta.setComentarios(appointmentComments.getText());
    
    int animalId = animalByName.get(animalComboBox.getSelectedItem());
    int vetId = vetByName.get(veterinarioComboBox.getSelectedItem());
    int tratamentoId = tratamentoByName.get(tratamentoComboBox.getSelectedItem());
    consulta.setIdAnimal(animalId);
    consulta.setIdVet(vetId);
    consulta.setIdTratamento(tratamentoId);
    
    ConsultaDAO.getInstance().update(consulta);
    setTableModel(table,new ConsultaTableModel(ConsultaDAO.getInstance().retrieveAll()));
            
} else {
    System.out.println("User canceled / closed the dialog, result = " + result);
}
}
}
