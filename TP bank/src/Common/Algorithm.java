
package Common;

//import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Algorithm 
{
    
    
    
    //viết vào file pathname với nội dung có sẵn
    public void writeNewContent(String pathnameOutput, String content) 
    {
        FileWriter fileWriter = null;
        File file = new File(pathnameOutput);
        if (!file.exists()) 
        {
            try 
            {
                file.createNewFile();
            } 
            catch (IOException ex) 
            {
                System.err.println("Can't create file");
            }
        }
        try 
        {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
            System.err.println("Write successful");
        } 
        catch (IOException ex) 
        {
            System.err.println("Can’t write file");
        } 
        finally 
        {
            try 
            {
                fileWriter.close();
            } 
            catch (IOException ex) 
            {
                System.err.println("Can't close file!");
            }
        }
    }
    
    public String calculateBMI (double weight, double height)
    {
        double afterCalculator = weight / (Math.pow(height, 2));
        if (afterCalculator < 19) return "UNDER - STANDARD";
        else if (afterCalculator >= 19 && afterCalculator < 25) return "STANDARD"; 
            else if (afterCalculator >= 25 && afterCalculator < 30) return "OVERWEIGHT"; 
                else if (afterCalculator >= 30 && afterCalculator < 40) return "FAT - SHOULD LOSE WEIGHT"; 
                    //else if (afterCalculator >= 40 ) return "VERY FAT - SHOULD LOSE WEIGHT IMMEDIATELY"; 
                    return "VERY FAT - SHOULD LOSE WEIGHT IMMEDIATELY"; 
    }
    
    public double normalCalculator(double memory, String calc, double number)
    {
        try 
        {
            switch (calc) 
                        {
                            case "+":
                                return memory + number;
                            case "-":
                                return memory - number;
                            case "*":
                                return memory * number;
                            case "/":
                                return memory / number;
                            case "^":
                                return Math.pow(memory, number);
                        }
        
        } 
        catch (ArithmeticException e) 
        {
            System.err.println("Can't divided case 0!");
        }
        return memory;
    }
    
    public Map countWords(String input, int letterCount)
    {
        Map<String, Integer> wordCount = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(input);
        while (tokenizer.hasMoreTokens()) {
            String T = tokenizer.nextToken().toLowerCase();
            if (wordCount.containsKey(T)) {
                wordCount.put(T, wordCount.get(T) + 1);
            } else {
                wordCount.put(T, 1);
            }
            
            for (int i = 0; i < T.length(); i++) {
                if (Character.isLetter(T.charAt(i))) {
                    letterCount++;
                }
            }
        }
        return wordCount;
        
    }
    
    public Set countUniqueCharacter(String input)
    {
        Set<Character> uniqueChars = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char c = Character.toLowerCase(input.charAt(i));
            if (Character.isLetter(c)) {
                uniqueChars.add(c);
            }
        }
        return uniqueChars;
    }
    
    
    public int countWord(String string) 
    {
        StringTokenizer tokenizer = new StringTokenizer(string);
        return tokenizer.countTokens();
    }
    
    public int lengthOfLetter(String str)
    {
        StringTokenizer st = new StringTokenizer(str);
        int count = 0;
        
        for (int i = 0; st.hasMoreTokens(); i++) 
        {
           
            count++;
        }
        return count;
    }
    
    public char charFromDecToHex(int n)
    {
        char a;
        if (n>9) a = (char) (n + 'A' - 10);
        else a = (char) (n + '0');
        return a;
    }
    
    public String stringFromDecToHex(int value)
    {
        String result="";
                while (value>0) 
                {
                    
                    result = Character.toString(charFromDecToHex(value%16)) + result;
                    value/=16;
                }
                return result;
    }
    
    public int fromHexToDec(String s)
    {
        int sum = 0;
        int length = s.length();
        char a;
        for (int i = 0; i < length; i++) 
        {
            a = s.charAt(i);
            if('A' <= a && a <= 'F') sum = sum + ((int) a - 'A' + 10) * ((int) Math.pow(16, length-i-1));
            else sum = sum + ((int) a - '0') * ((int) Math.pow(16, length-i-1));
            //System.out.println(sum);
        }
        
        return sum;
        
    }
    
    public char charFromDecToBin(int n)
    {
        return (char) (n + '0');
    }
    
    public String stringFromDecToBin(int value)
    {
        String result="";
        while (value>0) 
                {
                    result = Character.toString(charFromDecToBin(value%2)) + result;
                    value/=2;
                }
                
                return result;
    }
    
    public int fromBinToDec(String s)
    {
        int sum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) 
        { 
            sum = sum + ((int) s.charAt(i) - '0') * ((int) Math.pow(2, length-i-1));
        }
        return sum;
        
    }
    
    public int linearSearch(int[] arr, int findi)
    {
        for (int i = 0; i < arr.length; i++) 
        {
            if (findi == arr[i]) return i;
            
        }
        return -1;
    }
    
    public int binarySearch(int[] arr, int findi, int mini, int maxi)
    {
        if (mini > maxi) 
        {
            return -1;
        }
        int pivotIndex = mini + (maxi - mini)/2;//arr.length/2;
        int pivot = arr[pivotIndex]; //có thể lược bỏ ở đây
        
        if (findi == pivot) 
        {
            return pivotIndex;
        }
//        else if (!(mini == maxi)) 
//        {
//            return -1;
//        }
        else 
            if (findi < pivot) 
            {
            return binarySearch(arr, findi, mini, pivotIndex - 1);
            }
            else return binarySearch(arr, findi, pivotIndex + 1, maxi);
        
    }
    
    public void bubbleSortAscending(int[] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            for (int j = 0; j < array.length-i-1; j++) 
            {
                if (array[j] > array[j+1]) 
                {
                    swap(array, j, j+1);
                }
                
            }
            
        }
    }
    
    public void bubbleSortDescending(int[] array)
    {
        for (int i = 0; i < array.length; i++) 
        {
            for (int j = 0; j < array.length-i-1; j++) 
            {
                if (array[j] < array[j+1]) 
                {
                    swap(array, j, j+1);
                }
                
            }
            
        }
    }
    
    public void quickSort(int[] arr, int left, int right) 
    {
        if (left < right)
        {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    public int partition(int[] arr, int left, int right)
    {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) 
        {
            if (arr[j] < pivot) 
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    public void swap(int[] arr, int i, int j) 
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
}
