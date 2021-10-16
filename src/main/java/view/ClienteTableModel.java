package view;

import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ClienteDAO;
import controller.Controller;

public class ClienteTableModel extends GenericTableModel{
    
	public ClienteTableModel(List vDados){
        super(vDados, new String[]{"Nome","Endereco","Telefone","Cep"});
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
            	return String.class;
            case 4:
            	return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = (Cliente) vDados.get(rowIndex);
        Controller.addListObject(rowIndex,cliente.getId());
        switch (columnIndex) {
            case 0:
                return cliente.getNome();
            case 1:
                return cliente.getEndereco();
            case 2:
                return cliente.getTelefone();
            case 3:
            	return cliente.getCep();
            case 4:
            	return cliente.getEmail();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }    
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Cliente cliente = (Cliente) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
            	cliente.setNome((String)aValue);
                break;
            case 1:
            	cliente.setEndereco((String)aValue);
                break;
            case 2:
            	cliente.setTelefone((String)aValue);
                break;
            case 3:
            	cliente.setCep((String) aValue);
            	break;
            case 4:
            	cliente.setEmail((String) aValue);
            	break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
        ClienteDAO.getInstance().update(cliente);
    }    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }      
}