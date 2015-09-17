/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.capitalonebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForCapitalOneBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicatesRemoved;

    public DebitsForCapitalOneBank(List<String> lines) {
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

        for (int a = 0; a < this.selectedBlock.size(); a++) {
            
          String result = FileUtils.getResultFromPattern("transfer\\s+debit\\s+TO|Online\\s+banking\\s+xfer\\s+withdrawal\\s+TO", this.selectedBlock.get(a));
            if (!result.startsWith("NoMatch") && this.selectedBlock.get(a).matches("^\\s*\\d{1,2}/\\d{1,2}.+")) {
                String[] temp = this.selectedBlock.get(a).split("\\s+");
                date.add(temp[0]);
                String value = FileUtils.getDollarSignRemovedValue(FileUtils.getSecondLastElementFromArray(this.selectedBlock.get(a)));
                double d = FileUtils.convertStringToDouble(value);
                amount.add(d);
                String desc = "";
                for (int x = 1; x < temp.length - 2; x++) {
                    desc += temp[x] + " ";
                }
                if (a+1<selectedBlock.size() && !selectedBlock.get(a+1).matches("^\\s*\\d{1,2}/\\d{1,2}.+"))
                {
                    desc +=selectedBlock.get(a+1);
                }
                description.add(desc);
                //if(a+1<selectedBlock.size()){desc=desc+" "+selectedBlock.get(a+1);}
                String ch = FileUtils.getResultFromPattern("TO\\s+\\d+|TO\\s*\\.+\\d+", desc);
                if (!ch.startsWith("NoMatch")) {
                    ch = FileUtils.getFourDigitAccountNumber(ch);
                    checkNo.add(ch);
                }
            else{
                checkNo.add("N/A");
            }
            }
        }

    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "Date\\s+Description\\s+Deposits/Credits\\s+Withdrawals/Debits");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^Total");
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
