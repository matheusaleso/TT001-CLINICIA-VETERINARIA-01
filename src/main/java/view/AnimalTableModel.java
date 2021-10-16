package view;

import controller.Controller;
import java.util.List;

import model.Animal;
import model.AnimalDAO;

public class AnimalTableModel extends GenericTableModel {
int n = 1;
    public AnimalTableModel(List vDados){
        super(vDados, new String[]{"Nome","Sexo","Ano Nascimento"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = (Animal) vDados.get(rowIndex);
        Controller.addListObject(rowIndex,animal.getId());
        switch (columnIndex) {
            case 0:
                return animal.getNome();
            case 1:
                return animal.getSexo();
            case 2:
                return animal.getAnoNasc();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Animal animal = (Animal) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	animal.setNome((String)aValue);
                break;
            case 1:
            	animal.setSexo((String)aValue);
                break;
            case 2:
            	animal.setAnoNasc((Integer)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        AnimalDAO.getInstance().update(animal);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}