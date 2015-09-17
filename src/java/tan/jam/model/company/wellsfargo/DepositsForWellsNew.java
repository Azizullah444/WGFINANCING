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
public class DepositsForWellsNew {

    private String[] pages;
    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedBlock;
    private List<String> selectedOnlineTransferFromRecordBlock;
    private List<Double> selectedOnlineTransferFromRecordAmount;
    private List<String> selectedOnlineReversalFromRecordBlock;
    private List<Double> selectedOnlineReversalFromRecordAmount;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;

    public DepositsForWellsNew(String[] pages) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
        startExecution();
    }

    private void startExecution() {
        this.initialBlock = captuteInitialBlock();
        this.selectedBlock = captureSelectedBlock();
        this.selectedOnlineTransferFromRecordBlock = captureSelectedOnlineTransferFromRecordBlock();
        this.selectedOnlineTransferFromRecordAmount = captureSelectedOnlineTransferFromRecordAmount();
        this.selectedOnlineReversalFromRecordBlock = captureSelectedOnlineReversalFromRecordBlock();
        this.selectedOnlineReversalFromRecordAmount  = captureSelectedOnlineReversalFromRecordAmount();
        this.onlineTransferFromSum = onlineTransferFromSum();
        this.onlineReversalFromSum = onlineReversalFromSum();

    }

    private double onlineReversalFromSum() {
        double sum = 0;
        for (double d : this.selectedOnlineReversalFromRecordAmount) {
            sum += d;
        }
        return sum;
    }

    private List<Double> captureSelectedOnlineReversalFromRecordAmount() {
        List<String> list = this.selectedOnlineReversalFromRecordBlock;
        List<Double> main = new ArrayList<>();
        for (String s : list) {
            String[] temp = s.split(" ");
            String t = FileUtils.getDollarSignRemovedValue(temp[1]);
            main.add(FileUtils.convertStringToDouble(t));

        }
        return main;
    }

    private List<String> captureSelectedOnlineReversalFromRecordBlock() {
        List<String> list = new ArrayList<>();
        List<String> main = new ArrayList<>();
        for (String s : this.selectedBlock) {
            String result = FileUtils.getResultFromPattern("Reversal|Refund|Bounce|Return|Rtrn|REVERSAL|REFUND|BOUNCE|RETURN|RTRN", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
            }
        }
        return main;
    }

    private double onlineTransferFromSum() {
        double sum = 0;
        for (double d : this.selectedOnlineTransferFromRecordAmount) {
            sum += d;
        }
        return sum;
    }

    private List<Double> captureSelectedOnlineTransferFromRecordAmount() {
        List<String> list = this.selectedOnlineTransferFromRecordBlock;
        List<Double> main = new ArrayList<>();
        for (String s : list) {
            String[] temp = s.split(" ");
            String t = FileUtils.getDollarSignRemovedValue(temp[1]);
            main.add(FileUtils.convertStringToDouble(t));

        }
        return main;
    }

    private List<String> captureSelectedOnlineTransferFromRecordBlock() {
        List<String> list = new ArrayList<>();
        List<String> main = new ArrayList<>();
        for (String s : this.selectedBlock) {
            String result = FileUtils.getResultFromPattern("Online\\s+Transfer\\s+From|Online\\s+Transfer\\s+from|Online\\s+transfer\\s+from|Account\\s+Transfer\\s+From|Account\\s+transfer\\s+from", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
            }
        }
        return main;
    }

    private List<String> captureSelectedBlock() {
        List<String> list = initialBlock;
        List<String> main = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
                //System.out.println(s);
            }
        }
        return main;

    }

    private List<String> captuteInitialBlock() {
        String start = "Account\\s+summary";
        String end = "\\$?\\d{1,}\\,?\\d{1,}?\\,?\\d{1,}?\\.\\d{1,2}\\s+Total\\s+credits";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);

        return list;
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
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

    public List<String> getSelectedOnlineTransferFromRecordBlock() {
        return selectedOnlineTransferFromRecordBlock;
    }

    public void setSelectedOnlineTransferFromRecordBlock(List<String> selectedOnlineTransferFromRecordBlock) {
        this.selectedOnlineTransferFromRecordBlock = selectedOnlineTransferFromRecordBlock;
    }

    public List<Double> getSelectedOnlineTransferFromRecordAmount() {
        return selectedOnlineTransferFromRecordAmount;
    }

    public void setSelectedOnlineTransferFromRecordAmount(List<Double> selectedOnlineTransferFromRecordAmount) {
        this.selectedOnlineTransferFromRecordAmount = selectedOnlineTransferFromRecordAmount;
    }

    public List<String> getSelectedOnlineReversalFromRecordBlock() {
        return selectedOnlineReversalFromRecordBlock;
    }

    public void setSelectedOnlineReversalFromRecordBlock(List<String> selectedOnlineReversalFromRecordBlock) {
        this.selectedOnlineReversalFromRecordBlock = selectedOnlineReversalFromRecordBlock;
    }

    public List<Double> getSelectedOnlineReversalFromRecordAmount() {
        return selectedOnlineReversalFromRecordAmount;
    }

    public void setSelectedOnlineReversalFromRecordAmount(List<Double> selectedOnlineReversalFromRecordAmount) {
        this.selectedOnlineReversalFromRecordAmount = selectedOnlineReversalFromRecordAmount;
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
