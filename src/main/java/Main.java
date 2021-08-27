import java.util.List;

/**
 *
 * @author vilela
 */

/*
public class Main {
    public static void main(String[] args) {        
        //Cliente c1 = new Cliente(1,"Plinio","Rua dos Bobos","70707070","00234-900","prvilela@unicamp.br");

       // ClienteDAO.getInstance().create("Plinio","Rua dos Bobos","70707070","00234-900","prvilela@unicamp.br");
        
        //Animal a1 = new Animal(1,"Toto",2,0);
        //Animal a2 = new Animal(2,"Mimi",3,1);
        
     // Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        
        //c1.addAnimal(a1);
        //c1.addAnimal(a2);
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        
        AnimalDAO.getInstance().create("Octupus",2010,"Macho",1,c1);
        
        //System.out.println(ClienteDAO.getInstance().retrieveAll());
        //System.out.println(AnimalDAO.getInstance().retrieveAll());

        System.out.println(AnimalDAO.getInstance().retrieveByIdCliente(c1.getId()));
        /*SS
        List<Animal> listaExterna = c1.getAnimais();
        Animal a3 = new Animal(3,"    ",5,0);
        listaExterna.add(a3);
        
        System.out.println(c1);
        
        System.out.println(listaExterna);
        */
  //  }
//} 


public class Main {
    public static void main(String[] args) {       
        ClienteDAO.getInstance().create("Matheus Sales",
                "Rua doois","123456789","00234-905","m203577@dac.unicamp.br");
        Cliente c2 = ClienteDAO.getInstance().retrieveById(2);
        AnimalDAO.getInstance().create("Mel",2015,"Femea",2,c2);
        AnimalDAO.getInstance().create("Quito",2002,"Macho",3,c2);
        
       ClienteDAO.getInstance().create("Tiago Carvalho",
                "Rua doois","123456789","00234-905","m203577@dac.unicamp.br");
        Cliente c3 = ClienteDAO.getInstance().retrieveById(3);
        AnimalDAO.getInstance().create("Tutu",2017,"Macho",4,c3);
        AnimalDAO.getInstance().create("Louro José",2005,"Macho",5,c3);
        AnimalDAO.getInstance().create("Lola",2016,"Femea",5,c3);

        System.out.println("Dados de c2 e de c3"+ClienteDAO.getInstance().retrieveAll());
        
        System.out.println("Animais de c2:"+AnimalDAO.getInstance().retrieveByIdCliente(c2.getId()));
        System.out.println("Animais de c3:"+AnimalDAO.getInstance().retrieveByIdCliente(c3.getId()));

    }
}
