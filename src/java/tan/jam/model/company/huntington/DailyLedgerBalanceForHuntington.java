/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.huntington;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForHuntington {
     private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;

    DailyLedgerBalanceForHuntington(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
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
    
private List<Double> captureSelectedBlock() {
        String start = "^\\s*Balance\\s+Activity";
        String end = "^\\s*Business\\s+Overdraft\\s+Protection\\s+Account|^\\s*In\\s+the\\s+Event\\s+of\\s+Errors\\s+or\\s+Questions";
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
                 if (!result1.startsWith("NoMatch") && a+1<result.length) {     
                    String value = FileUtils.getDollarSignRemovedValue(FileUtils.getODRemovedValue(FileUtils.getNegativeSignDetectedValue(result[a+1])));
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
   
}
