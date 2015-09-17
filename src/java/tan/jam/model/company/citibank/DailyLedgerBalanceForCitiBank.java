/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForCitiBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<Double> selectedAmountBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    public DailyLedgerBalanceForCitiBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void show() {
        int count=0;
        for (double s : selectedAmountBlock) {
           // System.out.println(++count + " " +s);
        }
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.selectedAmountBlock = captureSelectedAmountBlock();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.minValue = findMinValue();
        this.averageLedgerBalance = captureAverageLedgerBalance();
        show();
    }
    private double captureAverageLedgerBalance(){
        double value  = 0;
        for(double d: this.selectedAmountBlock){
            value += d;
        }
        return value/this.selectedAmountBlock.size();
    }
    private double findMinValue(){
        return Collections.min(this.selectedAmountBlock);
    }
    private int findNoOfNegativeValues(){
        int count=0;
        for(double d: this.selectedAmountBlock){
            if(d < 0){
                ++count;
            }
        }
        return count;
    }
    private List<Double> captureSelectedAmountBlock(){
        List<String> list = this.initialBlock;
        List<Double> main= new ArrayList<>();
        for(String s: list){
            String lastElement = FileUtils.getLastElementFromArray(s);
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if(!result.startsWith("NoMatch"))
            {
               main.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(lastElement))));
            }
        }
        return main;
    }
    private List<String> captureInitialBlock() {
        String start = "^\\s*CHECKING\\s+ACTIVITY";
        String end = "^\\s*Total\\s+Debits/Credits";
        List<String> main = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
               if(main.size()>0){
                if(main.get(main.size()-1).startsWith(result))
                {
                    main.set(main.size()-1, s); 
                }else
                {
                    main.add(s);
                }}else{main.add(s);}
            }
        }
        return main;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public List<Double> getSelectedAmountBlock() {
        return selectedAmountBlock;
    }

    public void setSelectedAmountBlock(List<Double> selectedAmountBlock) {
        this.selectedAmountBlock = selectedAmountBlock;
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

    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }

}
