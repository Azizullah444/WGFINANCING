/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstdakota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForFirstDakota {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    public DailyLedgerBalanceForFirstDakota(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void show() {
        for (double s : this.selectedBlock) {
            System.out.println(s);
        }
    }

    private void startExecution() {
        this.selectedBlock = captureInitialBlock();
        this.minValue = findMinValue();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.averageLedgerBalance = captureDailyLedgerBalance();

        //show();
    }

    private double captureDailyLedgerBalance() {
        double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("Average\\s+Ledger", s);
            if(!result.startsWith("NoMatch")){
                String[] temp = s.split("\\s+");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));                
            }
        }
        return d ;
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

    private double findMinValue() {
       return Collections.min(this.selectedBlock);
    }
    private List<Double> captureInitialBlock() {
        String start = "^\\s*DAILY\\s+BALANCE\\s+INFORMATION";
        String end = "^\\s*Your\\s+Privacy\\s+is\\s+important\\s+to\\s+us";
        List<Double> main = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String res = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if (!res.startsWith("NoMatch")) {
                String[] result = s.split("\\s+");
                for (int a = 0; a < result.length; a++) {
                  String result1 = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", result[a]);
                    if (!result1.startsWith("NoMatch")) {
                        String value = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(result[a+1]));
                        double d = FileUtils.convertStringToDouble(value);
                        main.add(d);   
                }
            }
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
