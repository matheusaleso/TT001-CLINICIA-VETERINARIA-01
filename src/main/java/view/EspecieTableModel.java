package view;

import controller.Controller;
import java.util.List;

import model.Especie;
import model.EspecieDAO;

public class EspecieTableModel extends GenericTableModel {

    public EspecieTableModel(List vDados){
        super(vDados, new String[]{"Id","Nome"});
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Especie especie = (Especie) vDados.get(rowIndex);
        Controller.addListObject(rowIndex,especie.getId());
        switch (columnIndex) {
            case 0:
                return especie.getId();
            case 1:
                return especie.getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Especie especie = (Especie) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	especie.setId((Integer)aValue);
                break;
            case 1:
            	especie.setNome((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        EspecieDAO.getInstance().update(especie);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
    
}
