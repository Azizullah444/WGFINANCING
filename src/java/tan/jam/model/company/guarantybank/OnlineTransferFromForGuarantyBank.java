/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferFromForGuarantyBank {

    private List<String> lines;
    private List<String> selectedRecords;
    private double sum;

    public OnlineTransferFromForGuarantyBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedRecords = captureSelectedRecords();
        this.sum = calculateOnlineTransferFromSum();
    }

    private double calculateOnlineTransferFromSum() {
        double sm = 0;
        for (String s : this.selectedRecords) {
            String[] temp = s.trim().split("\\s+");
            if (s.matches("^\\s*\\d{1,2}/\\d{1,2}.+")) {
                
               sm += FileUtils.convertStringToDouble(temp[1]);
            }
        }
        return sm;
    }

    private List<String> captureSelectedRecords() {
        List<String> list = new ArrayList<>();
        for (String s : this.lines) {
            String result = FileUtils.getResultFromPattern("Internet\\s+Transfer\\s+From", s);
            if (!result.startsWith("NoMatch")) {
                list.add(s);
               
            }
        }
        return list;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
