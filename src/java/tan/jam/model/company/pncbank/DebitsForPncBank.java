/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.pncbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForPncBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private OnlineTransferToForPncBank onlineTransferTo;
    private List<String> date;
    private List<String> description;
    private List<String> checkNo;
    private List<String> checkNoDuplicateRemoved;
    private List<Double> amount;
    private List<String> onlineTransferToRecords;

    public DebitsForPncBank(List<String> lines) {
        this.lines = lines;
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.amount = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        this.checkNoDuplicateRemoved = new ArrayList<>();

        startExecution();
    }

    private void startExecution() {

        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferToRecords = captureOnlineTransferToRecords();
        captureAmountDateDescriptionFromBlock();

    }

    private void captureAmountDateDescriptionFromBlock() {

        for (String s : this.onlineTransferToRecords) {
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                String ch = FileUtils.getResultFromPattern("\\d{12,}", s);
                if (!ch.startsWith("NoMatch")) {
                    String check = FileUtils.getFourDigitAccountNumber(ch);
                    checkNo.add(check);
                } else {
                    checkNo.add("N/A");
                }
                String[] temp = s.split("\\s+");
                double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1]));
                amount.add(d);
                date.add(temp[0]);
                String ds = "";
                for (int a = 2; a < temp.length; a++) {
                    ds += temp[a] + " ";
                }
                description.add(ds);

            }
        }
        this.checkNoDuplicateRemoved = FileUtils.removeDuplicateValues(this.checkNo);
    }

    private List<String> captureOnlineTransferToRecords() {
        List<String> list = new ArrayList<>();
        for (String s : this.lines) {
            String result = FileUtils.getResultFromPattern("Account\\s+Transfer\\s+To", s);
            if (!result.startsWith("NoMatch")) {
                list.add(s);
            }
        }
        return list;
    }

    private List<String> captureSelectedBlock() {
        int start = FileUtils.findStartIndex(lines, "^\\s*ACH\\s+Deductions", "^\\s*Date\\s+Transaction\\s+Reference");
        int end = FileUtils.findIndexBySingleValue(lines, "Detail\\s+of\\s+Services\\s+Used\\s+During\\s+Current\\s+Period");

        List<String> list = FileUtils.getSelectedBlockByIndex(lines, start, end);

        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public OnlineTransferToForPncBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForPncBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
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

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<String> getOnlineTransferToRecords() {
        return onlineTransferToRecords;
    }

    public void setOnlineTransferToRecords(List<String> onlineTransferToRecords) {
        this.onlineTransferToRecords = onlineTransferToRecords;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

}
