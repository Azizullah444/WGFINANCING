/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForBankUnited {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<Double> amountList;
    private int noOfNegativeValues;
    private double averageLedgerBalance;
    private double minValue;

    public DailyLedgerBalanceForBankUnited(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void show() {
        for (double s : this.amountList) {
            System.out.println(s);
        }
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.amountList = manageSelectedBlock();
        this.minValue  = findMinValue();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.averageLedgerBalance = captureAverageLedgerBalance();
        show();
    }
    private double captureAverageLedgerBalance(){
        double d = 0;
        for(double value: this.amountList){
            d += value;
        }
        return d / this.amountList.size();
    }
    private int findNoOfNegativeValues(){
        int count=0;
        for(double d: this.amountList){
            if(d < 0){
                ++count;
            }
        }
        return count;
    }
    private double findMinValue(){
        String result = "";
         double d = 0;
       for(String s: lines){
           if(s.matches("\\s*Minimum\\s+Balance\\s+this\\s+Statement\\s+Period.+")){
               result = s;
               break;
           }
       }
       
       if(result.length() > 1){
           String[] temp = result.split("\\s+"); 
           String dd = FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
            d = FileUtils.convertStringToDouble(dd);
       }
       return d;
    }
    private List<Double> manageSelectedBlock() {
        List<Double> list = new ArrayList<>();
        for (String s : this.selectedBlock) {
            String[] temp = s.split("\\s+");
            for (int a = 0; a < temp.length; a++) {
                if (temp[a].matches("\\d{1,2}/\\d{1,2}")) {
                    String value = FileUtils.getDollarSignRemovedValue(temp[a+1]);
                    double d = FileUtils.convertStringToDouble(value);
                    list.add(d);

                }
            }
        }
        return list;
    }

    private List<String> captureSelectedBlock() {
        List<String> mainList = new ArrayList<>();
        List<String> list = captureInitialBlock();
        for (String s : list) {
            String rs = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if (!rs.startsWith("NoMatch")) {
                mainList.add(s);
            }
        }
        return mainList;
    }

    private List<String> captureInitialBlock() {
        String start = "^\\s*Balances\\s+by\\s+Date";
        String end = "^\\s*Statement\\s+Messages|^\\s*Other\\s+Balances";

        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public List<Double> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<Double> amountList) {
        this.amountList = amountList;
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
