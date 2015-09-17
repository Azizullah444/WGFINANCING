/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstdakota;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitForFirstDakota {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> date;
    private List<Double> amount;
    private List<String> description;
    private List<String> checkNo;
    private List<String> checkNoDuplicateRemoved;

    public DebitForFirstDakota(List<String> lines) {
        this.lines = lines;
        this.date = new ArrayList<>();
        this.amount = new ArrayList<>();
        this.description = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        this.checkNoDuplicateRemoved = new ArrayList<>();
        
        startExecution();
    }

    private void show() {
        int count = 1;
        for (String s : this.selectedBlock) {
            System.out.println(s);
        }
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        //show();
        captureDateDescriptionAmount();
        this.checkNoDuplicateRemoved = findCheckNoDuplicateRemoved();
    }
    private List<String>  findCheckNoDuplicateRemoved(){
        return FileUtils.removeDuplicateValues(this.checkNo);
    }
    private void captureDateDescriptionAmount() {
        List<String> list = captureOnlineTransferToRecords();
        for (int a = 0; a < list.size(); a++) {
            System.out.println(list.get(a));
        }

    }

    private List<String> captureOnlineTransferToRecords() {
        List<String> list = FileUtils.getResultFromBlock("EB\\s+to", this.selectedBlock);
        for (int a = 0; a < list.size(); a++) {
            String[] temp = list.get(a).split("\\s+");
            date.add(temp[1]);
            String value = FileUtils.getDollarSignRemovedValue(FileUtils.getNegativeSignDetectedValue(temp[temp.length - 1]));
            double d = FileUtils.convertStringToDouble(value);
            amount.add(d);
             
            String desc = "";
            for (int z = 1; z < temp.length - 1; z++) {
                desc += temp[z] + " ";
            }
            this.description.add(desc);
            String v = FileUtils.getResultFromPattern("\\d{4}", desc);
            if (!v.startsWith("NoMatch")) {
                String str = FileUtils.getFourDigitAccountNumber(v);
                checkNo.add(str);
            }
            else{
                checkNo.add("N/A");
            }
           
        }
        return list;
    }

    private List<String> captureSelectedBlock() {
        List<String> list = captureInitialBlock();
        List<String> mainList = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                mainList.add(s);
            }

        }
        return mainList;
    }

    private List<String> captureInitialBlock() {
        String start = "^\\s*CHECKS\\s+AND\\s+WITHDRAWALS";
        String end = "^\\s*DAILY\\s+BALANCE\\s+INFORMATION";

        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
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

    public List<String> getCheckNoDuplicateRemoved() {
        return checkNoDuplicateRemoved;
    }

    public void setCheckNoDuplicateRemoved(List<String> checkNoDuplicateRemoved) {
        this.checkNoDuplicateRemoved = checkNoDuplicateRemoved;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(List<String> checkNo) {
        this.checkNo = checkNo;
    }

}
