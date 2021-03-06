/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForFirstBank {
     private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    DailyLedgerBalanceForFirstBank(List<String> lines) {
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
        return Collections.min(this.selectedBlock);
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
        double sum = 0;
        for (double d : this.selectedBlock) {
                sum += d;
        }
        double average = ((double)sum) / this.selectedBlock.size();
        return average;
    }
    
private List<Double> captureSelectedBlock() {
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"DAILY\\s+BALANCE\\s+INFORMATION");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"Statement\\s+Date\\s+Page");  
        List<Double> mainList = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
        for (String s : list) { 
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}\\s+", s); 
            if (!result.startsWith("NoMatch")) {    
                temp.add(s);
            }

        }
        for (String s : temp) { 
            String[] result = s.split("\\s+");
            for (int a = 0; a < result.length; a++) {
               String result1 = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", result[a]);
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
