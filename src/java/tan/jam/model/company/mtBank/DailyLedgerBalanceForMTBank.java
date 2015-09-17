/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mtBank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForMTBank {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double averageLedgerBalance;
    private double minValue;
    
    public DailyLedgerBalanceForMTBank(List<String> lines) {
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
        return Collections.min(this.selectedBlock);
    }
    
    private double findAverageValues() {
         double sum = 0;
        for (double d : this.selectedBlock) {
                sum += d;
        }
        double average = ((double)sum) / this.selectedBlock.size();
        return average;
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
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*ACCOUNT\\s+ACTIVITY");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "NUMBER\\s+OF\\s+DEPOSITS/CHECKS\\s+PAID|CHECKS\\s+PAID\\s+SUMMARY");
        
        List<Double> mainList = new ArrayList<>();
        List<String> selected = new ArrayList<>();
        
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex); 
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{2}/\\d{2}/\\d{2}", s); 
            if (!result.startsWith("NoMatch")) {
                if(selected.size()>0){
                if(selected.get(selected.size()-1).startsWith(result))
                {
                    selected.set(selected.size()-1, s); 
                }else
                {
                    selected.add(s);
                }}else{selected.add(s);}
            }
        }
        for(int a=0;a< selected.size();a++){
            String[] line = selected.get(a).split(" ");
            if(line.length > 1){
                    mainList.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(FileUtils.getBracketRemovedValue(line[line.length-1]))));
                
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
