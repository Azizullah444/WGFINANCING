/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstcitizensbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DailyLedgerBalanceForFirstCitizensBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<Double> dailyLedgerBalancesBlock;
    private int noOfNegativeValues;
    private double minValue;

    public DailyLedgerBalanceForFirstCitizensBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
    private void show(){
        int count=1;
        for(double d : this.dailyLedgerBalancesBlock){
            System.out.println(count++ +" " +d);
        }
        System.out.println("min value = " + this.minValue);
        System.out.println("no negative " + this.noOfNegativeValues);
    }
    private void startExecution() {
     this.selectedBlock = captureSelectedBlock();
     this.dailyLedgerBalancesBlock = captureDailyLedgerBalanceBlock();
     this.noOfNegativeValues = findNoOfNegativeValues();
     this.minValue = findMinValue();
     //show();
    }
    private double findMinValue(){
        return Collections.min(this.dailyLedgerBalancesBlock);
    }
   private int findNoOfNegativeValues(){
       int count = 0;
       for(double d: this.dailyLedgerBalancesBlock){
           if(d < 0){
               ++count;
           }
       }
       return count;
   }
    private List<Double> captureDailyLedgerBalanceBlock() {
        List<Double> list = new ArrayList<>();
        for(String s: this.selectedBlock){
            //System.out.println(s);
            String[] temp = s.split("\\s+");
            for(int a=0;a < temp.length;a++){
                if(temp[a].matches("\\d{1,2}-\\d{1,2}") && a+1<temp.length){
                    double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(temp[a+1])));
                    list.add(d);
                }
            }
        }
        
        return list;
    }
    private List<String> captureSelectedBlock(){
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Daily\\s+Balance\\s+Summary");
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, "^\\s*FIRST\\s+CITIZENS\\s+BANK\\s+DEPOSIT\\s+ACCOUNT\\s+AGREEMENT|^\\s*FOLLOW\\s+THESE\\s+EASY\\s+STEPS\\s+TO\\s+BALANCE");
        if (endIndex==0){ endIndex=lines.size(); }
        List<String> mainList = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for(String s: list){
            String res = FileUtils.getResultFromPattern("^\\d{1,2}-\\d{1,2}", s);
            if(!res.startsWith("NoMatch")){
                mainList.add(s);
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

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public List<Double> getDailyLedgerBalancesBlock() {
        return dailyLedgerBalancesBlock;
    }

    public void setDailyLedgerBalancesBlock(List<Double> dailyLedgerBalancesBlock) {
        this.dailyLedgerBalancesBlock = dailyLedgerBalancesBlock;
    }

}
