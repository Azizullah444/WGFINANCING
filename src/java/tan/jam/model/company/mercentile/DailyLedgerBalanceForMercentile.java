/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mercentile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForMercentile {
     private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    DailyLedgerBalanceForMercentile(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.minValue = findMinValue();
        this.averageLedgerBalance = findAverageValues();

    }

    private double findMinValue() {
         String result = "";
         double d = 0;
       for(String s: lines){  
           if(s.contains("MINIMUM BALANCE")){
               result = s;
               break;
           }
       }
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(temp[2]));
            d = FileUtils.convertStringToDouble(dd);
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
    private double findAverageValues() {
         String result = "";
         double d = 0;
       for(String s: lines){
           if(s.contains("AVERAGE BALANCE")){
               result = s;
               break;
           }
       }
       if(result.length() > 1){
           String[] temp = result.split("\\s+");
           String dd = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(temp[2]));
            d = FileUtils.convertStringToDouble(dd);
           }
       return d;
    }

private List<Double> captureSelectedBlock() {
        String start = "- - -\\s*DAILY\\s+BALANCE";
        String end = "END\\s+OF\\s+STATEMENT";
        List<Double> mainList = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) { 
            String result = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", s); 
            if (!result.startsWith("NoMatch")) {    
                temp.add(s);
            }

        }
        for (String s : temp) { 
            String[] result = s.split("\\s+");
            for (int a = 0; a < result.length; a++) {
               String result1 = FileUtils.getResultFromPattern("\\d{2}/\\d{2}", result[a]);
                 if (!result1.startsWith("NoMatch")) {
                    String value = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(result[a+1]));
                    double d = FileUtils.convertStringToDouble(value);
                    mainList.add(d);
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

    public int getNoOfNegativeValues() {
        return noOfNegativeValues;
    }

    public void setNoOfNegativeValues(int noOfNegativeValues) {
        this.noOfNegativeValues = noOfNegativeValues;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public List<Double> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<Double> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }
    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }
}
