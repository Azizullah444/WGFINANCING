/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBankUnited {

    private List<String> lines;
    private List<String> keywords;
    private List<String> selectedBlock;
    private List<Double> onlineTransferFromBlock;
    private double onlineTransferFromSum;
    List<Double> onlineReversalFromBlock;
    private double onlineReversalFromSum;

    public DepositsForBankUnited(List<String> lines, List<String> keywords) {
        this.lines = lines;
        this.keywords = keywords;
        startExecution();
    }

    private void show() {
        int count=1;
        for (double s : this.onlineTransferFromBlock) {
            System.out.println(count++ +" "+s);
        }
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFromBlock = captureOnlineTransferFromBlock();
        this.onlineTransferFromSum = findOnlineTransferFromSum();
        this.onlineReversalFromBlock = captureOnlineReversalFromBlock();
        this.onlineReversalFromSum = findOnlineReversalFromSum();
        show();
    }
    
     private double findOnlineReversalFromSum(){
        double sum = 0;
        for(double d: this.onlineReversalFromBlock){
            sum += d;
        }
        return sum;
    }
    private List<Double> captureOnlineReversalFromBlock() {
        List<Double> dValue = new ArrayList<>();
        List<String> list = FileUtils.getResultFromBlock("REVERSAL|RETRUN|RTRN|BOUNCE", selectedBlock);
        
       
       for(String s: list){
           String lastValue = FileUtils.getSecondLastElementFromArray(s);
           if(!lastValue.startsWith("NoMatch")){
               String val = FileUtils.getDollarSignRemovedValue(lastValue);
               double d = FileUtils.convertStringToDouble(val);
               dValue.add(d);
           }
       }
       return dValue;
    }

    private double findOnlineTransferFromSum(){
        double sum = 0;
        for(double d: this.onlineTransferFromBlock){
            sum += d;
        }
        return sum;
    }
    private List<Double> captureOnlineTransferFromBlock() {
        List<Double> dValue = new ArrayList<>();
       List<String> list = FileUtils.getResultFromBlock("Online Transfer From", selectedBlock);
        
      
       
       for(String s: list){
           System.out.println(s);
           String lastValue = FileUtils.getSecondLastElementFromArray(s);
           if(!lastValue.startsWith("NoMatch")){
               String val = FileUtils.getDollarSignRemovedValue(lastValue);
               double d = FileUtils.convertStringToDouble(val);
               dValue.add(d);
           }
       }
       return dValue;
    }

    private List<String> captureSelectedBlock() {
        List<String> mainList = new ArrayList<>();
        String start = "Date\\s+Description\\s+Withdrawals\\s+Deposits\\s+Balance";
        String end = "^\\s*Check\\s+Transactions|^\\s*Balances\\s+by\\s+Date";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}/\\d{4}", s);
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

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public List<Double> getOnlineTransferFromBlock() {
        return onlineTransferFromBlock;
    }

    public void setOnlineTransferFromBlock(List<Double> onlineTransferFromBlock) {
        this.onlineTransferFromBlock = onlineTransferFromBlock;
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
