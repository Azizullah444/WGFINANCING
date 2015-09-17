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
public class DebitsForNBTBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicatesRemoved;

    public DebitsForNBTBank(List<String> lines) {
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
        List<String> firstLine = new ArrayList<>();
        List<String> secondLine = new ArrayList<>();
        List<Integer> indexFinder = new ArrayList<>();
        List<String> mergedLine = new ArrayList<>();

        for (int a = 0; a < this.selectedBlock.size(); a++) {
            String result = FileUtils.getResultFromPattern("ONLINE\\s+BANKING\\s+TRANSFER\\s+TO", this.selectedBlock.get(a));
       
            if (!result.startsWith("NoMatch")) {
                indexFinder.add(a + 1); 
                firstLine.add(this.selectedBlock.get(a));
                String[] temp = this.selectedBlock.get(a).split("\\s+");
                date.add(temp[0]);
                String value = FileUtils.getDollarSignRemovedValue(FileUtils.getLastElementFromArray(this.selectedBlock.get(a)));
                double d = FileUtils.convertStringToDouble(value);
                amount.add(d);
                String desc = "";
                for (int x = 1; x < temp.length - 1; x++) {
                    desc += temp[x] + " ";
                }
                description.add(desc);
            }
        }
        for (int a = 0; a < indexFinder.size(); a++) {
            String val = selectedBlock.get(indexFinder.get(a));
           
            String result = this.description.get(a) + " " + val; 
            String ch = FileUtils.getResultFromPattern("TO\\s+XX+\\d+", result);
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
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*TRANSACTION\\s+DETAIL");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCE\\s+INFORMATION");
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
