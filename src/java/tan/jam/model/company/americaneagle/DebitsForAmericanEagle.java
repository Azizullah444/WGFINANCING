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
public class DebitsForAmericanEagle {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicatesRemoved;

    public DebitsForAmericanEagle(List<String> lines) {
        this.lines = lines;
        date = new ArrayList<>();
        description = new ArrayList<>();
        amount = new ArrayList<>();
        checkNo = new ArrayList<>();

        startExecution();
    }
    private void show(){
        for(String s: this.checkNoDuplicatesRemoved){
            System.out.println("Duplicate : " +s);
        }
    }
    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        captureOnlineTransferToRecords();
        checkNoDuplicatesRemoved = removeDuplicateRecords();
        //show();
    }

    private List<String> removeDuplicateRecords() {
        return FileUtils.removeDuplicateValues(this.checkNo);
    }

    private void captureOnlineTransferToRecords() {
       
        List<Integer> indexFinder = new ArrayList<>();
        for (int a = 0; a < this.selectedBlock.size(); a++) {
            String result = FileUtils.getResultFromPattern("Tfr\\s+to", this.selectedBlock.get(a));
       
            if (!result.startsWith("NoMatch")) {
                indexFinder.add(a); 
            }
        }
        for (int a = 0; a < indexFinder.size(); a++) {
            double d=0;
            description.add(selectedBlock.get(indexFinder.get(a)));
            String val = selectedBlock.get(indexFinder.get(a));
            String val2 = selectedBlock.get(indexFinder.get(a)+1);
                 String result1 = FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", val);
                 if (!result1.startsWith("NoMatch"))
                 {
                     String[] temp = val.split("\\s+");
                     date.add(temp[0]);
                     d = FileUtils.convertStringToDouble(temp[1]);
                 }else if(val2.matches("^\\s*\\d{1,2}/\\d{1,2}.+")){ String[] temp = val2.split("\\s+");
                        date.add(temp[0]);
                        d = FileUtils.convertStringToDouble(temp[1]); }
                 amount.add(d);
            String result = val+ " "+val2;
            String ch = FileUtils.getResultFromPattern("Shares\\s+\\d+-\\d+", result);
            if (!ch.startsWith("NoMatch")) {
                ch = FileUtils.getFourDigitAccountNumber(ch);
                checkNo.add(ch);
            }
            else{
                checkNo.add("N/A");
            }

        }

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

    public List<String> getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(List<String> checkNo) {
        this.checkNo = checkNo;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<String> getCheckNoDuplicatesRemoved() {
        return checkNoDuplicatesRemoved;
    }

    public void setCheckNoDuplicatesRemoved(List<String> checkNoDuplicatesRemoved) {
        this.checkNoDuplicatesRemoved = checkNoDuplicatesRemoved;
    }
}
