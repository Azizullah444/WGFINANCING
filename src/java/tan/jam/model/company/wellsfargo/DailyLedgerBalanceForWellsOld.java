/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsfargo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DailyLedgerBalanceForWellsOld {

    private List<String> lines;
    private List<Double> selectedBlock;
    private int noOfNegativeValues;
    private double minValue;

    public DailyLedgerBalanceForWellsOld(List<String> lines) {
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
        String start = "Transaction\\s+history";
        int startIndex = FileUtils.findIndexBySingleValue(lines, start);
        String end = "Ending\\s+balance\\s+on\\s+\\d{1,2}/\\d{1,2}\\s+-?\\d{1,}";
        int endIndex = FileUtils.findIndexBySingleValue(lines, end);
        
        List<Double> mainList = new ArrayList<>();
        List<String> selected = new ArrayList<>();
        
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {      
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
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
                
                String one = line[line.length - 1];
                String two = line[line.length - 2];
                
                if(Character.isDigit(two.charAt(0)) && two.contains(".")){ 
                    mainList.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(one)));
                }
            }
            
            
        }
        
        return mainList;
    }
//    private List<Double> captureSelectedBlock() {
//        String start = "Transaction\\s+history";
//        String end = "Ending\\s+balance\\s+on\\s+\\d{1,2}/\\d{1,2}";
//        List<Double> mainList = new ArrayList<>();
//        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
//        for (String s : list) {
//            String res = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
//            if (!res.startsWith("NoMatch")) {
//                //System.out.println(s);
//                String[] temp = s.split(" ");
//                String secondLast = temp[temp.length - 2];
//                String r = FileUtils.getResultFromPattern("\\$?\\d{1,}\\,?\\d{1,}?\\,?\\d{1,}?\\.\\d{2}|\\.", secondLast);
//                if (!r.startsWith("NoMatch")) {
//                    double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 1]));
//
//                    //System.out.println(d);
//                    mainList.add(d);
//                }
//            }
//
//        }
//        return mainList;
//    }

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

}
