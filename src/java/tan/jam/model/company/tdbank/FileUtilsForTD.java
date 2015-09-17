/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class FileUtilsForTD {
    static List<String> getSelectedBlockDebits(List<String> lines){
        String one = "DAILY ACCOUNT ACTIVITY";
        String two="Deposits";
        String three="Electronic Deposits";
        String four="Electronic Deposits (continued)";
        String five="Other Credits";
        String six = "Electronic Payments";
        String seven="Electronic Payments (continued)";
        String eight = "Other Withdrawals";
        String nine="DAILY BALANCE SUMMARY";
        List<String> main = new ArrayList<String>();
        List<Integer> index = new ArrayList<>();
        int count=0;
        for(int a=0;a< lines.size();a++){
            
            if(lines.get(a).startsWith(one)){
                index.add(a);
                count=a;
               // System.out.println("A = " +a);
                break;
            }
            
            
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(two));
            {
                index.add(a);
                count=a;
               // System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(three));
            {
                index.add(a);
                count=a;
            //    System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(four));
            {
                index.add(a);
                count=a;
              //  System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(five));
            {
                index.add(a);
                count=a;
              //  System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(six));
            {
                index.add(a);
                count=a;
              //  System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(seven));
            {
                index.add(a);
                count=a;
           //     System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(eight));
            {
                index.add(a);
                count=a;
               // System.out.println("A = " +a);
                break;
            }
        }
        for(int a=count;a < lines.size();a++){
            if(lines.get(a).startsWith(nine));
            {
                index.add(a);
                count=a;
              //  System.out.println("A = " +a);
                break;
            }
        }
        for(int a=0;a < index.size();a++){
          //  System.out.println(index.get(a));
          //  System.out.println(lines.get(index.get(a)));
        }
        return lines;
    }
}
