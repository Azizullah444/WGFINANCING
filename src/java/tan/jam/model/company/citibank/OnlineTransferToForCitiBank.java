/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferToForCitiBank {

    private List<String> lines;
    private List<String> date;
    private List<String> description;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicateRemoved;
    private List<String> mergedRecords ;

    public OnlineTransferToForCitiBank(List<String> lines) {
        this.lines = lines;
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.amount = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        startExecution();
    }

    private void show() {
        int count = 0;
        for (String s : captureSelectedRecords()) {
            System.out.println(++count + " " + s);
        }
    }
    private List<String> removeDuplicateRecords(){
        return FileUtils.removeDuplicateValues(this.checkNo);
    }
    private void startExecution() {
        this.mergedRecords = captureSelectedRecords();
        this.checkNoDuplicateRemoved = removeDuplicateRecords();
    }
    
    private List<String> captureSelectedRecords() {
        List<String> firstLine = new ArrayList<>();
        List<Integer> secondLineIndex = new ArrayList<>();
        List<String> secondLine = new ArrayList<>();
        List<String> thirdLine = new ArrayList<>();
        List<Integer> thirdLineIndex = new ArrayList<>();

        List<String> merged = new ArrayList<>();

        for (int a = 0; a < lines.size(); a++) {
              String result = FileUtils.getResultFromPattern("TRANSFER\\s+DEBIT", lines.get(a));
            //String result = FileUtils.getResultFromPattern("ATM\\s+WITHDRAWAL", lines.get(a));

            if (!result.startsWith("NoMatch")) {
                firstLine.add(lines.get(a));
                secondLineIndex.add(a + 1);
                if (a + 2 < lines.size()) {
                    thirdLineIndex.add(a + 2);
                }
            }
        }
        for (int a = 0; a < firstLine.size(); a++) {
            String check = lines.get(secondLineIndex.get(a));
            String[] temp = check.split(" ");
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", temp[0].trim());
            if (!result.startsWith("NoMatch")) {
                secondLine.add("-");
            } else {
                secondLine.add(check);
            }
        }
        for (int a = 0; a < firstLine.size(); a++) {
            String check = lines.get(thirdLineIndex.get(a));
            String[] temp = check.split(" ");
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", temp[0].trim());
            if (!result.startsWith("NoMatch")) {
                thirdLine.add("-");
            } else {
                thirdLine.add(check);
            }
        }
        for (int a = 0; a < firstLine.size(); a++) {
            merged.add(firstLine.get(a) + " " + secondLine.get(a) + " " + thirdLine.get(a));
            String[] temp = firstLine.get(a).split(" ");
            date.add(temp[0]);
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 2]));
            amount.add(d);
            String desc = "";
            for (int aa = 1; aa < temp.length - 2; aa++) {
                desc += temp[aa] + " ";
            }
            String descFor = desc + " " + secondLine.get(a) + " " + thirdLine.get(a);
            String descResult = FileUtils.getResultFromPattern("\\d{4,} ", descFor);
            if (!descResult.startsWith("NoMatch")) {
                descResult=descResult.substring(0, descResult.length()-1);
                checkNo.add(FileUtils.getFourDigitAccountNumber(descResult));
            }else{
                checkNo.add("N/A");
            }
            description.add(descFor);
        }
        return merged;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
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

    public List<String> getMergedRecords() {
        return mergedRecords;
    }

    public void setMergedRecords(List<String> mergedRecords) {
        this.mergedRecords = mergedRecords;
    }
}
