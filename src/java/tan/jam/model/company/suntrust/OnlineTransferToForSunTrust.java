/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.suntrust;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferToForSunTrust {

    private List<String> lines;
    private List<String> selectedRecords;
    private List<String> date;
    private List<Double> amount;
    private List<String> description;
    private List<String> checkNo;
    private List<String> checkNoDuplicate;

    public OnlineTransferToForSunTrust(List<String> lines) {
        this.lines = lines;
        this.amount = new ArrayList<>();
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.checkNoDuplicate = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        startExecution();
    }

    private void startExecution() {
        this.selectedRecords = captureSelectedRecords();
        captureDateDescriptionAmount();
    }

    private void captureDateDescriptionAmount() {
        for (String s : this.selectedRecords) {
            String[] temp = s.split("\\s+");
            String res = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if (!res.startsWith("NoMatch")) {
                date.add(temp[0]);
                amount.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[1])));
                String desc = "";
                for (int a = 2; a < temp.length; a++) {
                    desc += temp[a] + " ";

                }
                description.add(desc);
                String ch = FileUtils.getResultFromPattern("TO\\s+\\d+", desc);
                if (!ch.startsWith("NoMatch")) {
                    checkNo.add(FileUtils.getFourDigitAccountNumber(ch));
                } else {
                    checkNo.add("N/A");
                }
            }
        }
        checkNoDuplicate = FileUtils.removeDuplicateValues(checkNo);
    }

    private List<String> captureSelectedRecords() {

        List<String> list = this.lines;
        List<String> main = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("ONLINE\\s+BANKING\\s+TRANSFER\\s+TO", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
            }
        }
        return main;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<String> selectedRecords) {
        this.selectedRecords = selectedRecords;
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

    public List<String> getCheckNoDuplicate() {
        return checkNoDuplicate;
    }

    public void setCheckNoDuplicate(List<String> checkNoDuplicate) {
        this.checkNoDuplicate = checkNoDuplicate;
    }

}
