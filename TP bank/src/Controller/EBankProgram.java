
package Controller;

import Common.Algorithm;
import Common.Library;
import java.util.Locale;




public class EBankProgram 
{
    protected Library library;
    protected Algorithm algorithm;

    
    public EBankProgram()
    {
        library = new Library();
        algorithm = new Algorithm();


    }
        
    
    public void login(Locale language) 
    {
        library.getWordLanguage(language, "enterAccountNumber");
        int accountNumber = library.getInputAccountLanguage(language, "^\\d{10}$");
        library.getWordLanguage(language, "enterPassword");
        String passString = library.getInputPasswordLanguage(language);
        String captchaGenerated = library.generateCaptchaText();
        while (true) 
        {
            if (library.checkInputCaptchaLanguage(captchaGenerated, language)) 
            {
                library.getWordLanguage(language, "loginSuccess");
                System.out.println();
                return;
            } 
            else 
            {
                library.getWordLanguage(language, "errCaptchaIncorrect");
                System.out.println();
            }
        }
    }
    
    
    
}
