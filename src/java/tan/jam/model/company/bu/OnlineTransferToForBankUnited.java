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
public class OnlineTransferToForBankUnited {

    private List<String> selectedBlock;
    private DescriptionRecord record;
    private List<String> description;
    private List<String> date;
    private List<Double> amount;
    private List<String> checkNo;
    private List<String> checkNoDuplicateRemoved;

    public OnlineTransferToForBankUnited(List<String> selectedBlock) { //NOTE: working on date amount description checkno still in progress
        this.selectedBlock = selectedBlock;
        this.description = new ArrayList<>();
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        this.checkNoDuplicateRemoved = new ArrayList<>();
        this.amount = new ArrayList<>();
        startExecution();
    }

    private void startExecution() {
        captureOnlineTransferToRecords();
    }

    private void captureOnlineTransferToRecords() {
        List<String> list = new ArrayList<>();
        List<Integer> currentRow = new ArrayList<>();
        List<Integer> secondRow = new ArrayList<>();
        List<Integer> thirdRow = new ArrayList<>();
        for (int a = 0; a < this.selectedBlock.size(); a++) {
            String result = FileUtils.getResultFromPattern("Online\\s+Transfer\\s+To", selectedBlock.get(a));
            if (!result.startsWith("NoMatch")) {
                currentRow.add(a);
                secondRow.add(a + 1);
                thirdRow.add(a + 2);

            }
        }

        System.out.println("********************************************");
        for (int a = 0; a < currentRow.size(); a++) {
            int firstLine = currentRow.get(a);
            int secondLine = secondRow.get(a);
            int thirdLine = thirdRow.get(a);
            String rowOne = selectedBlock.get(firstLine);

            System.out.println(rowOne);
            String rowTwo = selectedBlock.get(secondLine);
            System.out.println(rowTwo);
            String rowThree = selectedBlock.get(thirdLine);
            System.out.println(rowThree);
            String[] temp = rowOne.split(" ");

            if (temp[0].contains("/")) {
                System.out.println("temp length " + temp.length);
                System.out.println(temp[0] + "   " + temp[temp.length - 2]);
                date.add(temp[0]);
                amount.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length-2])));
                String desc = "";
                for (int x = 1; x < temp.length - 2; x++) {
                    desc += temp[x] + " ";
                }
                record = new DescriptionRecord(desc, rowTwo, rowThree);
                String ch = record.getFinalDescriptionString();
                ch = FileUtils.getResultFromPattern("\\d{3}-\\d{3}", ch);
                checkNo.add(ch);
                description.add(record.getFinalDescriptionString());
            }
            checkNoDuplicateRemoved = FileUtils.removeDuplicateValues(checkNo);

            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        }

    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public DescriptionRecord getRecord() {
        return record;
    }

    public void setRecord(DescriptionRecord record) {
        this.record = record;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
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
