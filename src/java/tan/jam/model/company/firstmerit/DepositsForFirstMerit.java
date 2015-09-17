/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstmerit;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFirstMerit {

    private List<String> lines;
    private List<String> selectedBlock;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;
    
    

    public DepositsForFirstMerit(List<String> lines) {
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
             String value = FileUtils.getLastElementFromArray(s);
            value = FileUtils.getDollarSignRemovedValue(value);
            double d = FileUtils.convertStringToDouble(value);
            sum += d;
        }
        return sum;
    }

    private double findOnlineTransferFromSum() {
        List<String> list = captureOnlineTransferFromRecords();
        double sum = 0;
        for (String s : list) { 
            String value = FileUtils.getLastElementFromArray(s);
            value = FileUtils.getDollarSignRemovedValue(value);
            double d = FileUtils.convertStringToDouble(value);
            sum += d;
        }
        return sum;
    }

    private List<String> captureOnlineReversalFromRecords() {

        List<String> list = FileUtils.getSelectedRecord(this.selectedBlock, "REVERSAL|RETURN|BOUNCE");
       
        return list;
    }

    private List<String> captureOnlineTransferFromRecords() {

        List<String> list = FileUtils.getSelectedRecord(this.selectedBlock, "ONLINE\\s+BANKING\\s+TRANSFER\\s+FROM");
       
        return list;
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Other\\s+Transactions");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Daily\\s+Balance\\s+Information");
        List<String> mainList = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\D{3}\\s+\\s+\\d{1,2}", s);
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
