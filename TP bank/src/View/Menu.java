
package View;

import Common.Library;
import java.util.ArrayList;
import java.util.Locale;

public abstract class Menu<T>
{
    protected String title;
    protected ArrayList<T> mChon;
    Library l = new Library();
    
    public Menu()
    {
        mChon = new ArrayList<>();
    }

    public Menu(String td, String[] mc) 
    {
        title = td;
        mChon = new ArrayList<>();
        for (String string : mc) 
        {
            mChon.add((T) string);
        }
    }
    
    //-----------------------------------------------------
    public void display()
    {
        System.out.println("\n" + title);
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < mChon.size(); i++) 
        {
            System.out.println((i+1) + ". " + mChon.get(i));
            
        }
        System.out.println("-----------------------------------------------");
    }
    
    //-----------------------------------------------------
    
    public int getSelected()
    {
        display();
        return l.getIntInRange("Enter your choice: ", 1, mChon.size());
    }
    
    public int getSelectedLanguage()
    {
        display();
        return l.getInputIntLimitLanguage("Enter your choice: ", 1, mChon.size(), Locale.ENGLISH);
    }
    
    //-----------------------------------------------------
    
    public abstract void execute(int n);
    
    //-----------------------------------------------------
    
    public void run()
    {
        while (true) 
        {
            int n = getSelected();
            
            //if (n > mChon.size()) //-1?
            if (0 < n && n < mChon.size())   
            {
                execute(n);
            }
            else if(n == mChon.size()) 
            {
                System.out.println("Exiting...");
                break;
            }
            else
            {
                System.err.println("ERROR!! BREAKING PROGRAM...");
                break;
            }
            
        }
    }
    
    public void runLanguage()
    {
        while (true) 
        {
            int n = getSelectedLanguage();
            
            //if (n > mChon.size()) //-1?
            if (0 < n && n < mChon.size())   
            {
                execute(n);
            }
            else if(n == mChon.size()) 
            {
                System.out.println("Exiting...");
                break;
            }
            else
            {
                System.err.println("ERROR!! BREAKING PROGRAM...");
                break;
            }
            
        }
    }
    
}
