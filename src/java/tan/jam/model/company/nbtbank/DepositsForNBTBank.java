/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nbtbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForNBTBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;
    
    

    public DepositsForNBTBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFromSum = findOnlineTransferFromSum();
        this.onlineReversalFromSum = findOnlineReversalFromSum();
    }

    private double findOnlineReversalFromSum() {
        List<String> list = captureOnlineReversalFromRecords();
        double sum = 0;
        for (String s : list) {
            String[] temp=s.split("\\s+");
            String value = temp[temp.length-1];
            value = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(value));
            double d = FileUtils.convertStringToDouble(value);
            if (d>0)
            { sum += d; }
        }
        return sum;
    }

    private double findOnlineTransferFromSum() {
        List<String> list = captureOnlineTransferFromRecords();
       double sum = 0;
        for (String s : list) {
            String[] temp=s.split("\\s+");
             String value = temp[temp.length-1];
             value = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(value));
            double d = FileUtils.convertStringToDouble(value);
            if (d>0)
            { sum += d; }
        }
        return sum;
    }

    private List<String> captureOnlineReversalFromRecords() {

        List<String> list = FileUtils.getSelectedRecord(this.selectedBlock, "Reversal|RETURN|Bounce");
       
        return list;
    }

    private List<String> captureOnlineTransferFromRecords() {

        List<String> list = FileUtils.getSelectedRecord(this.selectedBlock, "ONLINE\\s+BANKING\\s+TRANSFER\\s+FROM");
       
        return list;
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*TRANSACTION\\s+DETAIL");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCE\\s+INFORMATION");
        List<String> mainList = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}-\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) { 
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

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public double getOnlineTransferFromSum() {
        return onlineTransferFromSum;
    }

    public void setOnlineTransferFromSum(double onlineTransferFromSum) {
        this.onlineTransferFromSum = onlineTransferFromSum;
    }

    public double getOnlineReversalFromSum() {
        return onlineReversalFromSum;
    }

    public void setOnlineReversalFromSum(double onlineReversalFromSum) {
        this.onlineReversalFromSum = onlineReversalFromSum;
    }

}
