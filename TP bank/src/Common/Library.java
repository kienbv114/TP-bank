
package Common;

//import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Locale;
import java.util.ResourceBundle;
//import Model.Contact;


public class Library 
{
    
    private final Scanner kb = new Scanner(System.in);
    
    private static final char[] chars = {'1', 'A', 'a', 'B', 'b', 'C',
        'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
        'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
        'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
        '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
        'Y', 'y', 'Z', 'z', '9'};
    
    
    
    
    public int getInputIntLimitLanguage(String promit, int min, int max, Locale language) 
    {
        while (true) 
        {
            try 
            {
                int result = Integer.parseInt(kb.nextLine());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } 
            catch (NumberFormatException ex) 
            {
                getWordLanguage(language, "errorCheckInputIntLimit");
                System.out.println();
            }
        }
    }

    public String getInputStringLanguage(Locale language) 
     {
        while (true) 
        {
            String result = kb.nextLine();
            if (result.length() == 0) 
            {
                getWordLanguage(language, "errCheckInputIntLimit");
                System.out.println();
            } 
            else 
            {
                return result;
            }
        }
    }

    public String getInputPasswordLanguage(Locale language) 
    {
        String result;
        while (true) 
        {
            result = getInputStringLanguage(language);
            if (isValidPasswordLanguage(result, language)) 
            {
                return result;
            }
        }
    }

    public boolean isValidPasswordLanguage(String password, Locale language) 
    {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) 
        {
            getWordLanguage(language, "errCheckLengthPassword");
            System.out.println();
            return false;
        } 
        else 
        {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword - 1; i++) 
            {
                if (Character.isDigit(password.charAt(i))) 
                {
                    countDigit++;
                } 
                else if (Character.isLetter(password.charAt(i))) 
                {
                    countLetter++;
                }
            }
            if (countDigit < 1 || countLetter < 1) 
            {
                getWordLanguage(language, "errCheckAlphanumericPassword");
                System.out.println();
                return false;
            }
        }
        return true;
    }

    public boolean checkInputCaptchaLanguage(String captchaGenerated, Locale language) 
    {
        System.out.println(captchaGenerated);
        getWordLanguage(language, "enterCaptcha");
        String captchaInput = getInputStringLanguage(language);
        for (int i = 0; i < captchaInput.length(); i++) 
        {
            if (!captchaGenerated.contains(Character.toString(captchaInput.charAt(i)))) 
            {
                return false;
            }
        }
        return true;
    }

    public String generateCaptchaText() 
     {
        //String randomStrValue = "";
        final int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index;
        for (int i = 0; i < LENGTH; i++) 
        {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }
    
    public void getWordLanguage(Locale curLocate, String key) 
    {
        ResourceBundle words = ResourceBundle.getBundle("Language/" + curLocate, curLocate);
        String value = words.getString(key);
        System.out.printf(value);
    }
    
    public int getInputAccountLanguage(Locale language, String regex) 
    {
        while (true) 
        {
            String result = kb.nextLine();
            if (result.matches(regex)) 
            {
                return Integer.parseInt(result);
            }
            getWordLanguage(language, "errCheckInputAccount");
            System.out.println();
        }
    }
    
    public String getDateMonthYearFormat(String promit)
    {
        while (true) 
        {
            String dateString = getString(promit);
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
            //SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            try 
            {
                Date date = inputFormat.parse(dateString); //cho về dạng "Sat Apr 11 00:00:00 GMT 2009"
                //String formattedDate = outputFormat.format(date);
                //System.out.println("Formatted date: " + formattedDate);
                return dateString;
            } 
            catch (ParseException e) 
            {
                System.err.println("Invalid date format");
                
            }
        }


    }
    
    //lấy nội dung từ file
    public String getNewContent(String pathFileInput) {
        HashSet<String> newContent = new HashSet<>();
        File file = new File(pathFileInput);
        try {
            Scanner input = new Scanner(file);
            
            while (input.hasNext()) 
            {
                String word = input.next();
                newContent.add(word + " ");
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.err.println("Can’t read file");
        }
        String content = "";
        for (String line : newContent) 
        {
            content += line;
        }
        return content;
    }
    
    //check đã có pathname này chưa
    public boolean checkFileExist(String pathname) 
    {
        File file = new File(pathname);
        return !(!file.exists() || !file.isFile());
    }
    
    public boolean checkFileExistWithMessenger(String pathname) 
    {
        File file = new File(pathname);
        if (!file.exists() || !file.isFile()) 
        {
            System.err.println("Path does not exist!!!"); //ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean checkFileExistThenCreate(String pathname) 
    {
        File file = new File(pathname);
        if (!file.exists() || !file.isFile()) 
        {
            try 
            {
                System.err.println("File not exist!!!");
                file.createNewFile();
                System.err.println("File created.");
                return false;
            } 
            catch (IOException ex) 
            {
                System.err.println(ex);
                //ex.printStackTrace();
            }
        }
        return true;
    }
    
    public String getMatchRegexWithRedLine(String promit, String regex, String redLine)
    {
        while (true) 
        {
            try 
            {
                String codeRegex = getString(promit);
                boolean matched = codeRegex.matches(regex);
                if (!matched)
                    throw new Exception();
                return codeRegex;
            } 
            catch (Exception e)
            {
                System.err.println(redLine);
            }
        }
        
    }
    
    public String getMatchRegex(String promit, String regex)
    {
        while (true) 
        {
            try 
            {
                String codeRegex = getString(promit);
                boolean matched = codeRegex.matches(regex);
                if (!matched)
                    throw new Exception();
                return codeRegex;
            } 
            catch (Exception e)
            {
                System.err.println("Doesn't match the pattern!");
            }
        }
        
    }
    
    public String getPhoneNumber(String promit)
    {
        while (true) 
        {
            System.out.print(promit);
            String phone = kb.nextLine().trim();
            String regex = "^(\\([0-9]{3}\\)|[0-9]{3})[-\\. ]?[0-9]{3}[-\\. ]?[0-9]{4}( x[0-9]+)?$";
            // Biểu thức chính quy này sẽ phù hợp với các định dạng sau:
            // 1234567890
            // 123-456-7890
            // 123-456-7890 x1234
            // 123-456-7890 ext1234
            // (123)-456-7890
            // 123.456.7890
            // 123 456 7890
            if (Pattern.matches(regex, phone)) 
            {
            return phone;
            } 
            else 
            {
            System.out.println("""
                               Invalid phone number 
                               Please input Phone flow
                               \u2022 1234567890
                               \u2022 123-456-7890 
                               \u2022 123-456-7890 x1234
                               \u2022 123-456-7890 ext1234
                               \u2022 (123)-456-7890
                               \u2022 123.456.7890
                               \u2022 123 456 7890""");
            }
            
        }
        
    }
    
    public boolean checkInputYN(String promit)
    {
        while (true)
        {
            String result = getString(promit);
            
            if (result.equalsIgnoreCase("Y"))
            {
                return true;
            }
            
            if (result.equalsIgnoreCase("N"))
            {
                return false;
            }
            System.err.println("Please input Y or N!");
            
        }
    }
    
    public boolean checkInputYNRedLine(String promit)
    {
        while (true)
        {
            String result = getStringRedLine(promit);
            
            if (result.equalsIgnoreCase("Y"))
            {
                return true;
            }
            
            if (result.equalsIgnoreCase("N"))
            {
                return false;
            }
            System.err.println("Please input Y or N!");
            
        }
    }
    
    public String getStringRedLine(String promit)
    {
        
        while (true)
        {
            System.err.print(promit);
            //Scanner kb = new Scanner(System.in);
            String result = kb.nextLine().trim();
            if (result.isEmpty())
            {
                System.err.println("Not empty!");
                System.out.print("Enter again: ");
            }
            else
            {
                return result;
            }
        }
    }
    
    public char checkInputUD(String promit)
    {
        while (true)
        {
            String result = getString(promit);
            
            if (result.equalsIgnoreCase("U"))
            {
                return 'U';
            }
            
            if (result.equalsIgnoreCase("D"))
            {
                return 'D';
            }
            System.err.println("Please input U or D!");
            
        }
    }
    
    public String getCalculatorChar(String promit)
    {
        while (true)
        {
            try 
            {
                System.out.print(promit);
                String result = kb.nextLine().trim();
                if (result.isEmpty())
                {
                    System.err.println("Not empty!");
                }
                else
                    if (result.equals("+") ||
                            result.equals("-") ||
                                result.equals("*") ||
                                    result.equals("/") ||
                                        result.equals("^") ||
                                            result.equals("="))
                    {
                        return result;
                    }
                    else
                    {
                        throw new Exception();
                    }
            } 
            catch (NullPointerException e)
            {
                System.err.println("ERROR! Please input (+, -, *, /, ^, =)");
            }
            catch (Exception e) 
            {
                System.err.println("ERROR! Please input (+, -, *, /, ^, =)");
            }
        }
    }
    
    public double getDoubleInRangeHasMinMax(String promit, double mini, double maxi) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                double result = Double.parseDouble(kb.nextLine());
                if (!(mini <= result && result <= maxi)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input double");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be in range [" + mini + ", " + maxi + "]");
            }

        }
    }
    
    public float getFloatInRangeHasMinMax(String promit, double mini, double maxi) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                float result = Float.parseFloat(kb.nextLine());
                if (!(mini <= result && result <= maxi)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input float");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be in range [" + mini + ", " + maxi + "]");
            }

        }
    }
    
    public double getDoubleInRange(String promit, double mini, double maxi) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                double result = Double.parseDouble(kb.nextLine());
                if (!(mini < result && result < maxi)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input double");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be in range (" + mini + ", " + maxi + ")");
            }

        }
    }
    
    public float getFloatInRange(String promit, float mini, float maxi) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                float result = Float.parseFloat(kb.nextLine());
                if (!(mini < result && result < maxi)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input float");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be in range (" + mini + ", " + maxi + ")");
            }

        }
    }
    
    public double getPositiveDouble(String promit) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                double result = Double.parseDouble(kb.nextLine());
                if (!(result > 0)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input double");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be positive double");
            }

        }
    }
    
    public float getPositiveFloat(String promit) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                float result = Float.parseFloat(kb.nextLine());
                if (!(result > 0)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input float");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be positive float");
            }

        }
    }
    
    public int getPositiveInt(String promit) 
    {
        
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                int result = Integer.parseInt(kb.nextLine());
                if (!(result > 0)) 
                {
                    throw new Exception();
                }
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input integer!");
                
            }
            catch (Exception e) 
            {
                System.err.println("Must be positive integer!");
            }

        }
    }
    
    public double getDouble(String promit) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                double result = Double.parseDouble(kb.nextLine());
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input double");
                
            }

        }
    }
    
    public int getInt(String promit) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                int result = Integer.parseInt(kb.nextLine());
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input int");
                
            }

        }
    }
    
    public float getFloat(String promit) 
    {
        //Scanner kb = new Scanner(System.in);
        while (true) 
        {
            try 
            {
                System.out.print(promit);
                float result = Float.parseFloat(kb.nextLine());
                return result;
            } 
            catch (NumberFormatException e) 
            {
                System.err.println("Must be input float");
                
            }

        }
    }
    
    public int getIntInRange(String promit, int m, int n)
    {
        int a = -1;
        //Scanner kb = new Scanner(System.in);
        while (true)
        {
            System.out.print(promit + "");
            try 
            {
                String s = kb.nextLine();
                a = Integer.parseInt(s);
                if (a >= m && a <= n) 
                {
                    break;
                }
                
            } 
            catch (NumberFormatException e) 
            {      
                System.err.println("Please enter a number between " + m + " and " + n);
            }
        }
        return a;
    }
    
    public int[] createRandomArray(int size_Array)
    {
        int[] array = new int[size_Array];
        Random rd = new Random();
        for (int i = 0; i < size_Array; i++) 
        {
            array[i] = rd.nextInt(100);
            
        }
        return array;
    }
    
    public int[] createInputArray(int size_Array)
    {
        int[] array = new int[size_Array];
        for (int i = 0; i < size_Array; i++) 
        {
            //String promit = 
            array[i] = getPositiveInt("Enter Number " + (i+1) + ": ");
            
        }
        return array;
    }
    
    public void displayIntArray(int[] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            if (i < array.length - 1)
            {
                System.out.print("[" + array[i] + "], ");
            } 
            else
            {
                System.out.print("[" + array[i] + "]");
            }
            
        }
        
    }
    
    public String getString(String promit)
    {
        
        while (true)
        {
            System.out.print(promit);
            String result = kb.nextLine().trim();
            if (result.isEmpty())
            {
                System.err.println("Not empty!");
                System.out.print("Enter again: ");
            }
            else
            {
                return result;
            }
        }
    }
    
}
