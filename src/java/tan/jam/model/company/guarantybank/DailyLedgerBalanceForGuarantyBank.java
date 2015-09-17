/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForGuarantyBank {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;
    private double averageLedgerBalance;

    public DailyLedgerBalanceForGuarantyBank(List<String> lines) {
        this.lines = lines;
        
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.averageLedgerBalance=captureAverageLedgerBalance();
        this.noOfNegativeValues = findNoOfNegativeValues();
        this.minValue = findMinValue();

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
    private double captureAverageLedgerBalance(){
        String res = "";
        for(String s:lines){
            if(s.contains("Average Balance")){
                res = s;
                break;
            }
        }
        String[] temp=res.split("\\s+");
        String d=FileUtils.getDollarSignRemovedValue(temp[temp.length-1]);
        return FileUtils.convertStringToDouble(d);
    }
    private List<Double> captureSelectedBlock() {
        int startIndex =FileUtils.findIndexBySingleValue(lines, "- - -\\s+DAILY\\s+BALANCE\\s+SUMMARY\\s+- - -"); 
        int endIndex = lines.size();
        List<Double> mainList = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                String[] arr = s.split("\\s+");
                if (arr.length > 1) {
                    temp.add(s);
                }
            }

        }
        for (int a = 0; a < temp.size(); a++) {
            String[] tempArr = temp.get(a).split("\\s+");
            String L=temp.get(a);
           
              for(int c=0;c<tempArr.length;c++)
              {
                  String v = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", tempArr[c]);
                    if (!v.startsWith("NoMatch"))
                    {
                      mainList.add(FileUtils.convertStringToDouble(FileUtils.getNegativeSignDetectedValue(tempArr[c+1])));
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
