/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.company.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class FileUtils {

    public static List<String> fillLines(String[] pages) {
        List<String> list = new ArrayList<>();
        for (int a = 0; a < pages.length; a++) {
            String[] temp = pages[a].split("\n");
            for (int z = 0; z < temp.length; z++) {
                if (temp[z].length() > 1) {
                    list.add(temp[z]);
                }
            }
        }
        return list;
    }

    public static List<String> getSelectedBlockByIndex(List<String> lines, int start, int end) {

        return lines.subList(start, end);
    }
    

    public static int findEndIndex(List<String> lines, String firstLine, String secondLine, int start) {

        int end = 0;
        List<Integer> index = new ArrayList<>();
        int startIndex = 0;
        for (int a = start; a < lines.size(); a++) {
            String result = FileUtils.getResultFromPattern(firstLine, lines.get(a));
            if (!result.startsWith("NoMatch")) {
              //  System.out.println(a + "  " + lines.get(a));
               // System.out.println("index = " + a);
                index.add(a + 1);
                index.add(a+2);
            }
        }
        for (int a = 0; a < index.size(); a++) {
            String line = lines.get(index.get(a));
          //  System.out.println("Line "+lines.get(index.get(a)));
            String result = FileUtils.getResultFromPattern(secondLine, line);
            if (!result.startsWith("NoMatch")) {
                startIndex = index.get(a);
            //    System.out.println(startIndex + "  " + line);
                break;
            }

        }
        return startIndex;
    }

    public static int findStartIndex(List<String> lines, String first, String second) {

        List<Integer> index = new ArrayList<>();
        int startIndex = 0;
        for (int a = 0; a < lines.size(); a++) {
            String result = FileUtils.getResultFromPattern(first, lines.get(a));
            if (!result.startsWith("NoMatch")) {
                //System.out.println(a + "  " + lines.get(a));
                //System.out.println("index = " + a);
                index.add(a + 1);
            }
        }
        for (int a = 0; a < index.size(); a++) {
            String line = lines.get(index.get(a));
            String result = FileUtils.getResultFromPattern(second, line);
            if (!result.startsWith("NoMatch")) {
                startIndex = index.get(a);
              //  System.out.println(startIndex + "  " + line);
                break;
            }

        }
        return startIndex;
    }

    public static int findIndexBySingleValue(List<String> lines, String value) {
        int index = 0;
        for (int a = 0; a < lines.size(); a++) {
            String result = FileUtils.getResultFromPattern(value, lines.get(a));
            if (!result.startsWith("NoMatch")) {
                index = a;
                break;
            }
        }
        return index;
    }
    public static int findIndexBySingleValueAppeartwotime(List<String> lines, String value) {
        int index = 0;
        for (int a = 0; a < lines.size(); a++) {
            String result = FileUtils.getResultFromPattern(value, lines.get(a));
            if (!result.startsWith("NoMatch")) {
                index = a;
            }
        }
        return index;
    }
    public static int findIndexBySingleValue(List<String> lines,int indx,String value){
         int index = 0;
        for (int a = indx; a < lines.size(); a++) { 
            String result = FileUtils.getResultFromPattern(value, lines.get(a));
            if (!result.startsWith("NoMatch")) {
                index = a;
                break;
            }
        }
        return index;
    }
    
    public static int findIndexByByDateValue(List<String> lines,int indx,String value){
         int index = 0;
        for (int a = indx; a < lines.size(); a++) { 
            String result = FileUtils.getResultFromPattern(value, lines.get(a));
            if (!result.startsWith("NoMatch")) {
                index = a;
                break;
            }
        }
        return index;
    }


    public static List<String> getResultFromBlock(String pattern, List<String> block) {
        List<String> list = new ArrayList<>();
        for (String s : block) {
            String result = FileUtils.getResultFromPattern(pattern, s);
            if (!result.startsWith("NoMatch")) {
                list.add(s);
            }
        }
        return list;
    }

    public static String getTargetByMatch(String page, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(page);
        if (m.find()) {
            return m.group();
        } else {
            return "NoMatchFound";
        }
    }

    public static String getSecondLastElementFromArray(String value) {
        String[] temp = value.split("\\s+");
        String last = temp[temp.length - 1];
        String before = temp[temp.length - 2];

        String result = "";

        if (Character.isDigit(before.charAt(0))& before.contains(".")||before.startsWith("$")||before.startsWith("-$")||before.length()>1&&before.startsWith("-")&Character.isDigit(before.charAt(1))& before.contains(".")) {
            result = before;
            return before;
        } else {
            return last;
        }
    }

    public static double convertStringToDouble(String value) {
        double d = 0;
        try {
            NumberFormat nf = NumberFormat.getInstance(Locale.US);

            Number number = nf.parse(value);

            d = number.doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public static List<String> getSelectedRecord(List<String> lines, String pattern) {
        List<String> temp = new ArrayList<String>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = null;
        for (int a = 0; a < lines.size(); a++) {
            m = p.matcher(lines.get(a));
            if (m.find()) {
                temp.add(lines.get(a));
            }
        }
        return temp;
    }

    public static String getResultFromArray(String line) {
        String[] temp = line.split(" ");
        String last = temp[temp.length - 1];
        String second = temp[temp.length - 2];
        String result = FileUtils.getResultFromPattern("\\d{1,3}?\\,?\\d{1,3}?\\.\\d{2}", second);
        if (!result.startsWith("NoMatch")) {

            return temp[temp.length - 1];
        } else {
            return "NoMatchFound";
        }

    }

    public static List<String> getFilterdBlock(List<String> selectedBlock, String pattern) {
        List<String> list = new ArrayList<>();
        for (int a = 0; a < selectedBlock.size(); a++) {
            String result = FileUtils.getResultFromPattern(pattern, selectedBlock.get(a));
            if (!result.startsWith("NoMatch")) {
                list.add(selectedBlock.get(a));
            }

        }
        return list;
    }

    public static String checkForTwoAmountValuesInArray(String[] arr) {
        String first = arr[arr.length - 2];
        String last = arr[arr.length - 1];

        String temp1 = FileUtils.getResultFromPattern("\\d{1,}?\\,?\\d{1,}\\.\\d{2}", first);
        String temp2 = FileUtils.getResultFromPattern("\\d{1,}?\\,?\\d{1,}\\.\\d{2}", last);
        if (!temp1.startsWith("NoMatch") && !temp2.startsWith("NoMatch")) {
          //  System.out.println("value " + first + "  " + last);
        }
        return last;
    }

    public static List<String> removeDuplicateValues(List<String> list) {
        List<String> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();

        for (String item : list) {
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    public static List<String> getSelectedBlock(List<String> lines, String startText, String endText) {
        int startIndex = 0;
        for (int a = 0; a < lines.size(); a++) {
            String first = FileUtils.getResultFromPattern(startText, lines.get(a));
            if (!first.startsWith("No")) {
                startIndex = a;
                break;
            }
        }int lastIndex=0; if (startIndex!=0){lastIndex= startIndex+9;}
        for (int a = startIndex; a < lines.size(); a++) {
            String last = FileUtils.getResultFromPattern(endText, lines.get(a));
            if (!last.startsWith("No")) {
                lastIndex = a;
                break;
            }
        }

        return lines.subList(startIndex, lastIndex);

    }

    public static String getFourDigitAccountNumber(String number) {
        return number.substring(number.length() - 4, number.length());
    }

    public static String getLastElementFromArraySeparatorDash(String line) {
        String[] temp = line.split("-");
        return temp[temp.length - 1];
    }

    public static String getLastElementFromArraySeparatorTo(String line) {
        String[] temp = line.split("to");
        return temp[temp.length - 1];
    }
    
    public static String getAmountForLenderFromArray(String line) {
        String[] temp = line.split(" ");
        if (temp.length>2)
        {
            if(temp[temp.length-2].matches("-?\\d+(\\,?\\d+)(\\.\\d+)?"))
        {
           return temp[temp.length-2];
        }
        }
        return temp[temp.length-1];
    }
    public static String getSecondtElementFromArray(String line) {
        String[] temp = line.split("\\s+");
        return temp[1];
    }
     public static String getLastElementFromArray(String line) {
        String[] temp = line.split("\\s+");
        return temp[temp.length-1];
    }
    public static String getSecondElementFromArray(String line) {
        String[] temp = line.split("\\s+");
        return temp[1];
    }

    public static String getResultFromPattern(String pattern, String target) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(target);
        if (m.find()) {
            return m.group();
        } else {
            return "NoMatchFound";
        }
    }

    public static String getResultFromString(String value, String target) {
        if (target.contains(value)) {
            return target;
        } else {
            return "NoMatchFound";
        }
    }

    public static String getDollarSignRemovedValue(String value) {
        String v = "";
        if (value.startsWith("$")) {
            return value.substring(1, value.length());
        } else if (value.startsWith("-$")) {
            String minusSign = value.substring(2, value.length());
            return "-" + minusSign;
        } else if (value.startsWith("+$")) {
            String minusSign = value.substring(2, value.length());
            return  minusSign;
        } else {
            return value;
        }
    }
    
    public static String getODRemovedValue(String value) {
        if (value.endsWith("D")) {
            return "-"+ value.substring(0, value.length()-2);
        } else {
            return value;
        }
    }
    
    public static String getCRRemovedValue(String value,String s) {
        if (value.startsWith("CR")) {
            String[] temp=s.trim().split("\\s+"); if (temp.length<3){ return value;}
            return temp[temp.length-3];
        } else if(value.endsWith("-Y") && value.length()>2){
         return  "-"+ value.substring(0, value.length()-2); }else {
            return value;
        }
    }
    
    public static String getBracketRemovedValue(String value) {
        if (value.endsWith(")")) {
            return "-"+ value.substring(1, value.length()-1);
        } else {
            return value;
        }
    }
    public static String getNegativeSignDetectedValue(String value) {
        String v = ""; 
        if (value.endsWith("-")) {
            String minusSign = value.substring(0, value.length()-1);
            return "-" + minusSign;
        } else if (value.startsWith("$-")) {
            String minusSign = value.substring(2, value.length());
            return "-" + minusSign;
        } else if (value.endsWith("–")) {
            String minusSign = value.substring(0, value.length()-1);
            return "-" + minusSign;
        } else if (value.endsWith("−")) {
            String minusSign = value.substring(0, value.length()-1);
            return "-" + minusSign;
        } else { 
            return value;
        }
    }

    public static List<String> getResultFromSpecializedPattern(List<String> lines, String start, String end) {

        int startIndex = 0;
        int endIndex = 0;
        List<String> selectedBlock = new ArrayList<>();
        for (int a = 0; a < lines.size(); a++) {
            if (lines.get(a).trim().equals(start)) {
                startIndex = a;
              //  System.out.println(lines.get(a));
                break;
            }
        }
        for (int a = 0; a < lines.size(); a++) {
            if (lines.get(a).trim().equals(end)) {
                endIndex = a;
               // System.out.println(lines.get(a));
                break;
            }
        }
        for (int a = startIndex; a < endIndex; a++) {
            selectedBlock.add(lines.get(a));

        }
        return selectedBlock;
    }
}
