
package Controller;

import Common.Algorithm;
import Common.Library;
import View.Menu;
import java.util.Locale;
//import Model.*;


public class EBankManagement extends Menu<String>
{
    static String[] mc = {"Vietnamese",
                            "English",
                                "Exit"};
    protected Library library;
    protected Algorithm algorithm;
    protected EBankProgram eb;
    
    
    public EBankManagement()
    {
        super("LOGIN PROGRAM", mc);
        algorithm = new Algorithm();
        library = new Library();
        eb = new EBankProgram();
    }
    
    @Override
    public void execute(int n)
    {
        Locale vietnamese = new Locale("vi");
        Locale english = Locale.ENGLISH;
        switch (n) 
        {
            case 1://add
            {
                eb.login(vietnamese);
                break;
            }
            
            case 2://display
            {
                eb.login(english);
                break;
            }
           
        }
    }
}