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
public class DepositsForPncBank {

    private List<String> lines;
    private List<String> keywords;
    private List<String> selectedBlock;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;

    public DepositsForPncBank(List<String> lines, List<String> keywords) {
        this.lines = lines;
        this.keywords = keywords;
        startExecution();
    }

    private void show() {
        int count = 1;
        for (String s : this.selectedBlock) {
            System.out.println(count++ + " " + s);
        }
        System.out.println("sum " + this.onlineTransferFromSum);
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFromSum = captureOnlineTransferFromSum();
        this.onlineReversalFromSum = captureOnlineReversalFromSum();
        //show();
    }

    private double captureOnlineReversalFromSum() {
        List<Double> mainList = new ArrayList<>();
        double sum = 0;
        List<String> list = FileUtils.getResultFromBlock("Reversal|Return|Rtrn|Bounce|Reverse", this.selectedBlock);
        for (String s : list) {
                String[] line = s.split("\\s+");
                String value = line[1];
                value = FileUtils.getDollarSignRemovedValue(value);
                double d = FileUtils.convertStringToDouble(value);
                sum += d;
                mainList.add(d);
        }
        return sum;
    }
    private double captureOnlineTransferFromSum() {
        List<Double> mainList = new ArrayList<>();
        double sum = 0;
        List<String> list = FileUtils.getResultFromBlock("Online\\s+Transfer\\s+From|tlr\\s+Transfer|Account\\s+Transfer\\s+From", this.selectedBlock);
        for (String s : list) {
                String[] line = s.split("\\s+");
                String value = line[1];
                value = FileUtils.getDollarSignRemovedValue(value);
                double d = FileUtils.convertStringToDouble(value);
                sum += d;
                mainList.add(d);
        }
        return sum;
    }

    private List<String> captureSelectedBlock() {
        int start = FileUtils.findIndexBySingleValue(lines, "^\\s*Activity\\s+Detail");
        int end = FileUtils.findIndexBySingleValue(lines, start, "^\\s*Checks\\s+and\\s+Other\\s+Deductions|^\\s*ACH\\s+Deductions");
        List<String> mainList = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                mainList.add(s);
            }
        }
        return mainList;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
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

    public double getOnlineTransferFromSum() {
        return onlineTransferFromSum;
    }

    public void setOnlineTransferFromSum(double onlineTransferFromSum) {
        this.onlineTransferFromSum = onlineTransferFromSum;
    }

    public double getOnlineReversalFromSum() {
        return onlineReversalFromSum;
    }

    public void setOnlineReversalFromSum(double onlineReversalFromSum) {
        this.onlineReversalFromSum = onlineReversalFromSum;
    }

}
