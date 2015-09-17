/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.americaneagle;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForAmericanEagle {

    private List<String> lines;
    private List<String> selectedBlock;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;
    
    

    public DepositsForAmericanEagle(List<String> lines) {
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
             String[] temp = s.split("\\s+");
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1]));
            sum += d;
        }
        return sum;
    }

    private double findOnlineTransferFromSum() {
        List<String> list = captureOnlineTransferFromRecords();
        double sum = 0;
        for (String s : list) { 
            String[] temp = s.split("\\s+");
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1]));
            sum += d;
        }
        return sum;
    }

    private List<String> captureOnlineReversalFromRecords() {
        List<String> list=new ArrayList<>();
        for(int i=0;i<selectedBlock.size();i++)
        {
            String result = FileUtils.getResultFromPattern("Reversal|Return|Refund|Bounce", selectedBlock.get(i));
            if (!result.startsWith("NoMatch")) { 
                 String result1 = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", selectedBlock.get(i));
                 if (!result1.startsWith("NoMatch"))
                 {
                     list.add(selectedBlock.get(i));
                 }else  if(selectedBlock.get(i+1).matches("^\\s*\\d{1,2}/\\d{1,2}.+")){ list.add(selectedBlock.get(i+1)); }
                
            }
        }       
        return list;
    }

    private List<String> captureOnlineTransferFromRecords() { 
        List<String> list=new ArrayList<>();
        for(int i=0;i<selectedBlock.size();i++)
        {
            String result = FileUtils.getResultFromPattern("Tfr\\s+From", selectedBlock.get(i));
            if (!result.startsWith("NoMatch")) { 
                 String result1 = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", selectedBlock.get(i));
                 if (!result1.startsWith("NoMatch"))
                 {
                     list.add(selectedBlock.get(i));
                 }else if(selectedBlock.get(i+1).matches("^\\s*\\d{1,2}/\\d{1,2}.+")){ list.add(selectedBlock.get(i+1)); }
                
            }
        }       
        return list;
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^Transactions");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Account\\s+Analysis\\s+Statement"); 
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);        
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
