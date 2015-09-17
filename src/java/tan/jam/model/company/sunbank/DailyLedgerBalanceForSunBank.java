/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.sunbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForSunBank {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double averageLedgerBalance;
    private double minValue;
    
    public DailyLedgerBalanceForSunBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.averageLedgerBalance = findAverageValues();
        this.minValue = findMinValue();

    }
    private double findMinValue(){
        String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Minimum Balance")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+"); 
            d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
       }
       return d;
    }
    
    private double findAverageValues() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("Average Balance")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+"); 
            d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));
       }
       return d;
    }
   
    
    private int findNoOfNegativeValues() {
        int count = 0;
        for (double d : this.selectedBlock) {
            if (d < 0) {
                ++count;
            }
        }
        return count;
    }
 private List<Double> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Daily\\s+Balances");
        int endIndex = FileUtils.findIndexBySingleValue(lines, startIndex, "^\\s*Overdraft\\s+and\\s+Returned\\s+Item\\s+Fees");
        if (endIndex<startIndex){ endIndex=lines.size(); }
        
        List<Double> mainList = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex); 
        for (String s : list) {
           String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}/\\d{2,4}", s);
            if (!result.startsWith("NoMatch")) {
                temp.add(s);
               // System.out.println(s);
            }

        }
        for (String s : temp) {
            String[] result = s.split(" ");
            for (int a = 0; a < result.length; a++) {
                if (result[a].matches("\\d{1,2}/\\d{1,2}/\\d{2,4}") && a+1<result.length) {
                    String value = FileUtils.getDollarSignRemovedValue(result[a+1]);
                    double d = FileUtils.convertStringToDouble(value);
                    mainList.add(d);
                   // System.out.println(d);
                }
            }
        }
        return mainList;
            
        }
        

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<Double> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<Double> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public int getNoOfNegativeValues() {
        return noOfNegativeValues;
    }

    public void setNoOfNegativeValues(int noOfNegativeValues) {
        this.noOfNegativeValues = noOfNegativeValues;
    }
    
    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }
    
    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

}
