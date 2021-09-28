
import javax.swing.*;

/**
 * This class is used to create JFrame with embedded functions and parameters.
 */
public class MenuFrame extends JFrame {                
    /**
	 * The serializable class Menu does not declare a static final serialVersionUID field of type long
	 */
    private static final long serialVersionUID = 1L;
                        
     /**
     * private parameters for MenuBar.
     */
    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuBar menuBar;
    private MyPanel panel;
    private String type = "";
    
    /**
     * INFO is used to show information about program.
     */
    final String INFO = "1. This program is called <Binary Tree>. \n"+
                        "2. Program was created by Vadym Liss.\n" +
                        "3. This program is used to create binary tree with the chosen type of values.\n" +
                        "4. There are possibilities to delete|insert|search the values and show the tree.\n"+
                        "5. There is documantation created with javadoc command and Doxygen for this program.\n";
  
    /**
     * INSTRUCTION is used to show instruction to the program.
     */
    final String INSTRUCTION =  "1.1 User needs to chose the type of values for the binary tree.\n"+
                                "1.2 Otherless the program wont do any task and it will be closed.\n"+
                                "1.3 The program provides just 3 types of values : int,double,string.\n"+
                                "2.1 User can delete,search and insert the vaalues for the given tree.\n"+
                                "2.3 User should provide correct values. Otherless there will be an error message.\n"+
                                "2.4 The tree always is repainted automatically when there are used new values.";
                        
    public MenuFrame()            
    {
        super("Binary Tree"); 
        
        /**
         * possibleValues is the list of types used to create the binary tree with it.
         * ret is the list of possibilities available to provide by program.
         */
        Object[] possibleValues = { "Integer", "Double", "String" };
        Object ret =  JOptionPane.showInputDialog(this,
                    "Choose type", "Type of tree",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    possibleValues, possibleValues[0]);

        /*
         * This loop is used to for providing the oparations with the binary tree.
         * Also there is included that values might not be found and another things.
         */
        if(ret != null)
        {
            type = ret.toString().substring(0, 1);
            panel = new MyPanel(type); 

            menu = new JMenu("Menu"); 
            
            /*
             * There is a point to insert given value.
             */
            menuItem = new JMenuItem("Insert"); 

            /*
             * Add action listener for the "Insert".
             */
            menuItem.addActionListener(e -> 
                {
                    String val = JOptionPane.showInputDialog(this, "Give value to insert");
                    if(val != null)
                    {
                        try {
                            panel.insert(cast(val));
                        } catch (Exception exp) {
                            JOptionPane.showMessageDialog(this, "Value is not valid type");
                        }
                    }

                }
            );



            menu.add(menuItem); 

            /*
             * There is a point to delete given value.
             */
            menuItem = new JMenuItem("Delete");
            /*
             * Add action listener for the "Delete".
             */
            menuItem.addActionListener(e ->
                {
                    String val = JOptionPane.showInputDialog(this, "Give value to insert");

                    if(val != null)
                    {
                        try {
                            panel.delete(cast(val));
                        } catch (Exception exp) {
                            JOptionPane.showMessageDialog(this, "Value is not valid type");
                        }
                    }
                        
                }
            );
            menu.add(menuItem); 


            /*
             * There is a point to search given value.
             */
            menuItem = new JMenuItem("Search");
            /*
             * Add action listener for the "Search".
             */
            menuItem.addActionListener(e ->
                {
                    String val = JOptionPane.showInputDialog(this, "Give value to search");
                    if(val != null)
                    {
                        try {
                            if(panel.search(cast(val)))
                                JOptionPane.showMessageDialog(this, "Value found");
                            else
                                JOptionPane.showMessageDialog(this, "Value not found");
                        } catch (Exception exp) {
                            JOptionPane.showMessageDialog(this, "Value is not valid type");
                        }
                    }
                }
            );
            menu.add(menuItem);

    
            
            menuBar = new JMenuBar(); 

            menuBar.add(menu);  
            

            menu = new JMenu("Info");

            menuItem = new JMenuItem("O autorze");


            /*
             * Add action listener for "O autorze" to provide the user with information
             * about the program and it's usage.
             */
            menuItem.addActionListener(e-> {
                JOptionPane.showMessageDialog(this, INFO);
            }
            );

            menu.add(menuItem);

            /*
             * menuItem adds submenu with instruction to the program.
             */
            menuItem = new JMenuItem("Instrukcja");
            menuItem.addActionListener(e-> {
            JOptionPane.showMessageDialog(this, INSTRUCTION);
            }
            );

            menu.add(menuItem);

            menuBar.add(menu);

            this.setJMenuBar(menuBar); 
            this.setContentPane(panel);
            this.setSize(400,400);    
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
            this.setVisible(true);
        }
    } 

    /**
     * This method uses the given value form the user for the operations provided in the binary tree.
     * @param s is the string parameter used as a save of given value.
     * @return is the changed value of comparable type, where comparable types are int, string and double.
     * @throws NumberFormatException if the string does not contain a parsable needed type.
     * @throws IllegalStateException if type of tree isn't recognize.
     */
    @SuppressWarnings("rawtypes")
    Comparable cast(String s) throws NumberFormatException, IllegalStateException
    {
        Comparable ret = null;

        switch(type)
        {
            case "I":
                ret = Integer.parseInt(s);
                break;
            case "D":
                ret = Double.parseDouble(s);
                break;
            case "S":
                ret = s;
                break;
            default:
                throw new IllegalStateException();
        }
        return ret;
    }
} 