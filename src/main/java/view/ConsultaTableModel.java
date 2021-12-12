/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Calendar;
import model.Consulta;
import model.ConsultaDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Animal;
import model.AnimalDAO;
import model.Tratamento;
import model.TratamentoDAO;
import model.Veterinario;
import model.VeterinarioDAO;


/**
 *
 * @author MATHEUS
 */
public class ConsultaTableModel  extends GenericTableModel {
    public ConsultaTableModel(List vDados){
        super(vDados, new String[]{"Data","Hora","Comentários","Animal","Veterinário","Tratamento","Estado"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
            case 5:
                return Integer.class;
            case 6:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
        Veterinario veterinario = VeterinarioDAO.getInstance().retrieveById(consulta.getIdVet());
        Tratamento tratamento = TratamentoDAO.getInstance().retrieveById(consulta.getIdTratamento());
        Controller.addListObject(rowIndex,consulta.getId());
        switch (columnIndex) {
            case 0:
                return consulta.dataConsulta();
            case 1:
                return consulta.getHora();
            case 2:
                return consulta.getComentarios();
            case 3:
                return animal.getNome();
            case 4:
                return veterinario.getNome();
            case 5:
                return tratamento.getNome();
            case 6:
                return consulta.isTerminou();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Consulta consulta = (Consulta) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	consulta.setData((Calendar)aValue);
                break;
            case 1:
            	consulta.setHora((String)aValue);
                break;
            case 2:
            	consulta.setComentarios((String)aValue);
                break;
            case 3:
                consulta.setIdAnimal((Integer)aValue);
                break;
            case 4:
                consulta.setIdVet((Integer)aValue);
                break;
            case 5:
                consulta.setIdTratamento((Integer)aValue);
                break;
            case 6:
                consulta.isTerminou();
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ConsultaDAO.getInstance().update(consulta);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
}
