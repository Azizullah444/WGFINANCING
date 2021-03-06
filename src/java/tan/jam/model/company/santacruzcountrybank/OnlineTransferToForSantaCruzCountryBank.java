/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.santacruzcountrybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferToForSantaCruzCountryBank {

    private List<String> initialBlock;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<String> checkNo;
    private List<Double> amount;
    private List<String> checkNoDuplicateRemoved;

    public OnlineTransferToForSantaCruzCountryBank(List<String> initialBlock) {
        this.initialBlock = initialBlock;
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        this.amount = new ArrayList<>();

        startExecution();
    }

    private void show() {
        for (int a = 0; a < date.size(); a++) {
            System.out.println(date.get(a) + " " + amount.get(a) + " " + description.get(a) + " " + checkNo.get(a));
        }
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedRecordsBlock();
        captureDateDescriptionAmount();
        //show();
    }

    private void captureDateDescriptionAmount() {
        List<String> list = this.selectedBlock;
        for (int a = 0; a < list.size(); a++) {
            String[] temp = list.get(a).split("\\s+");
            date.add(temp[0]);
            amount.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 1])));

            String desc = list.get(a);
            if (a + 1 < list.size()) {
                desc = list.get(a + 1);
            }
            description.add(desc);
            String res = FileUtils.getResultFromPattern("Online\\s+Transfer\\s+To\\s+XX+\\d+", desc);
            if (!res.startsWith("NoMatch")) {
                checkNo.add(FileUtils.getFourDigitAccountNumber(res));
            } else {
                checkNo.add("N/A");
            }
        }
        this.checkNoDuplicateRemoved = FileUtils.removeDuplicateValues(checkNo);
    }

    private List<String> captureSelectedRecordsBlock() {
        List<String> list = initialBlock;
        List<String> main = new ArrayList<String>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("IB\\s+Transfer\\s+W/D", s);
            if (!result.startsWith("NoMatch") && s.startsWith("\\d{1,2}/\\d{1,2}/\\d{2,4}")) {
                main.add(s);
            }

        }
        return main;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
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

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<String> getCheckNoDuplicateRemoved() {
        return checkNoDuplicateRemoved;
    }

    public void setCheckNoDuplicateRemoved(List<String> checkNoDuplicateRemoved) {
        this.checkNoDuplicateRemoved = checkNoDuplicateRemoved;
    }
}
