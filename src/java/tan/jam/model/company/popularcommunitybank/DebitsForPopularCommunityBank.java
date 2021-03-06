/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.popularcommunitybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForPopularCommunityBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicatesRemoved;

    public DebitsForPopularCommunityBank(List<String> lines) {
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
            String result = FileUtils.getResultFromPattern("Trsfr\\s+Dr", this.selectedBlock.get(a));
       
            if (!result.startsWith("NoMatch")) {
                indexFinder.add(a + 1); 
                firstLine.add(this.selectedBlock.get(a));
                String[] temp = this.selectedBlock.get(a).trim().split("\\s+");
                if (temp[0].matches("\\d{1,2}-\\d{1,2}")){ }else{ temp=selectedBlock.get(a+1).trim().split("\\s+");}
                date.add(temp[0]);
                String value = FileUtils.getDollarSignRemovedValue(temp[temp.length-2]);
                 double d=0;
                if (!value.contains(".")) {   } else {
                    d = FileUtils.convertStringToDouble(value); }
                amount.add(d);
                String desc = "";
                for (int x = 1; x < temp.length - 2; x++) {
                    desc += temp[x] + " ";
                }
                description.add(desc);
            }
        }
        for (int a = 0; a < indexFinder.size(); a++) {
            String val3="";
            String val = selectedBlock.get(indexFinder.get(a));
            if (!this.description.get(a).matches("^\\s*\\d{1,2}-\\d{1,2}.+")){ val3=selectedBlock.get(indexFinder.get(a)+2);}
            String val2 = selectedBlock.get(indexFinder.get(a)+1);
            String result = this.description.get(a) + " " + val+ " "+val2+" "+val3; 
            String ch = FileUtils.getResultFromPattern("DEP\\s+\\d+", result);
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
        int startIndex = FileUtils.findIndexBySingleValue(lines, "Date\\s+Description\\s+Additions\\s+Subtractions\\s+Balance");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*\\d{2}-\\d{2}\\s+Ending\\s+Totals");
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
