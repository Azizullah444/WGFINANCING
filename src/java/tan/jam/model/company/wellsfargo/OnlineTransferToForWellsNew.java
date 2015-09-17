/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsfargo;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferToForWellsNew {

    private List<String> initialBlock;
    private List<String> filteredBlock;
    private List<String> onlineTransferToRecords;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicateRemoved;

    public OnlineTransferToForWellsNew(List<String> selectedBlock) {
        this.initialBlock = selectedBlock;
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.amount = new ArrayList<>();
        this.checkNo = new ArrayList<>();

        startExecution();
    }
    private void show(){
        for(int a=0;a < date.size();a++){
            System.out.println(description.get(a)+" " + checkNo.get(a));
        }
    }
    private void startExecution() {
        this.filteredBlock = fileterInitialBlock();
        captureOnlineTransferToRecords();
        this.checkNoDuplicateRemoved = removeDuplicateCheckNo();
        //show();
    }

    private List<String> removeDuplicateCheckNo() {
        return FileUtils.removeDuplicateValues(checkNo);
    }

    private void captureOnlineTransferToRecords() {
        List<String> list = new ArrayList<>();
        List<Integer> index = new ArrayList<>();

        List<String> secondLine = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();

        for (int a = 0; a < initialBlock.size(); a++) {  
            String res = FileUtils.getResultFromPattern("Online\\s+Transfer\\s+to|Online\\s+Transfer\\s+To|Online\\s+transfer\\s+to|Account\\s+Transfer\\s+to", initialBlock.get(a));
         
            String desc = "";
            if (!res.startsWith("NoMatch")) {  
                list.add(this.initialBlock.get(a));
                index.add(a + 1);
                String[] temp = this.initialBlock.get(a).trim().split(" ");
                date.add(temp[0]);
                double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1]));
                amount.add(d);
                for (int b = 2; b < temp.length; b++) {
                    desc += temp[b] + " ";
                }
                firstLine.add(desc);
            }
        }
        for (int a = 0; a < index.size(); a++) {
            String line = initialBlock.get(index.get(a));
            String[] temp = line.split(" ");
            if (temp[0].trim().charAt(2) != '/') {
                secondLine.add(line);
            } else {
                secondLine.add(" ");
            }

        }

        for (int a = 0; a < list.size(); a++) {
            String text = firstLine.get(a) + " " + secondLine.get(a);
            description.add(text);

            String re = FileUtils.getResultFromPattern("XXX+\\d{4,}|xxx+\\d{4,}|to\\s+\\d+", text);
            if (!re.startsWith("NoMatch")) {
                checkNo.add(FileUtils.getFourDigitAccountNumber(re));
            }
            else{
                checkNo.add("N/A");
            }

        }

    }

    private List<String> fileterInitialBlock() {
        List<String> list = new ArrayList<>();
        for (String s : initialBlock) {
            String result = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                list.add(s);
            }
        }
        return list;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public List<String> getFilteredBlock() {
        return filteredBlock;
    }

    public void setFilteredBlock(List<String> filteredBlock) {
        this.filteredBlock = filteredBlock;
    }

    public List<String> getOnlineTransferToRecords() {
        return onlineTransferToRecords;
    }

    public void setOnlineTransferToRecords(List<String> onlineTransferToRecords) {
        this.onlineTransferToRecords = onlineTransferToRecords;
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

    public List<String> getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(List<String> checkNo) {
        this.checkNo = checkNo;
    }

    public List<String> getCheckNoDuplicateRemoved() {
        return checkNoDuplicateRemoved;
    }

    public void setCheckNoDuplicateRemoved(List<String> checkNoDuplicateRemoved) {
        this.checkNoDuplicateRemoved = checkNoDuplicateRemoved;
    }
}
