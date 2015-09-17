/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unionbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferFromForUnionBank {

    private List<String> lines;
    private List<String> filteredBlock;
    private List<Double> amountBlock;
    private double sum;

    public OnlineTransferFromForUnionBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.filteredBlock = captureFilteredBlock();
        this.amountBlock = captureAmountFromSelectedRecords();
        this.sum = findSum();
        
    }

    private double findSum() {
        double d = 0;
        for (double value : this.amountBlock) {
            d += value;
        }
        return d;
    }

    private List<Double> captureAmountFromSelectedRecords() {
        List<Double> main = new ArrayList<>();
        for (String s : this.filteredBlock) {
            String result = FileUtils.getResultFromPattern("CHECKING\\s+TRANSFER", s);
            if (!result.startsWith("NoMatch")) {
                String[] temp = s.split("\\s+");
                main.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 1])));
            }
        }
        return main;
    }
    private List<String> captureFilteredBlock() {
        List<String> list = lines;
        List<String> main = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
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

    public List<String> getFilteredBlock() {
        return filteredBlock;
    }

    public void setFilteredBlock(List<String> filteredBlock) {
        this.filteredBlock = filteredBlock;
    }

    public List<Double> getAmountBlock() {
        return amountBlock;
    }

    public void setAmountBlock(List<Double> amountBlock) {
        this.amountBlock = amountBlock;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
