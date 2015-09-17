/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.regionsbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForRegionsBank {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    public DailyLedgerBalanceForRegionsBank(List<String> lines) {
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
            String result = FileUtils.getResultFromPattern("Average Monthly Statement Balance|Average\\s+Balance", s);
            if(!result.startsWith("NoMatch")){
                String dv = s.trim();
                String[] temp = dv.split(" ");
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
       double d = 0;
        for(String s: lines){
            String result = FileUtils.getResultFromPattern("Minimum\\s+Daily\\s+Balance|Minimum\\s+Balance", s);
            if(!result.startsWith("NoMatch")){
               
                String[] temp = s.split("\\s+");
                d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-1]));                
            }
        }
        return d ;
    }
    private List<Double> captureInitialBlock() {
       int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCE\\s+SUMMARY");
       int endIndex = FileUtils.findIndexBySingleValue(lines, startIndex, "^\\s*Easy\\s+Steps\\s+to\\s+Balance\\s+Your\\s+Account|^\\s*You\\s+may\\s+request\\s+account\\s+disclosures");
       if (startIndex==0){ endIndex=0; }
        List<Double> main = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String res = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if (!res.startsWith("NoMatch")) {
                String[] temp = s.split("\\s+");
                for (int a=0;a<temp.length;a++) {
                    String v = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", temp[a]);
                    if (!v.startsWith("NoMatch") && a+1<temp.length) {
                        String re = FileUtils.getDollarSignRemovedValue(temp[a+1]);
                        double d = FileUtils.convertStringToDouble(re);
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
