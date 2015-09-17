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
public class OnlineReversalFromForCitiBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<Double> selectedAmountBlock;
    private double sum;

    public OnlineReversalFromForCitiBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.selectedAmountBlock = captureSelectedAmountBlock();
        this.sum = findSum();
    }

    private double findSum() {
        double d = 0;
        for (double value : this.selectedAmountBlock) {
            d += value;
        }
        return d;
    }

    private List<Double> captureSelectedAmountBlock() {
        List<String> list = selectedBlock;
        List<Double> main = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getSecondLastElementFromArray(s);
            main.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(result)));
        }
        return main;
    }

    private List<String> captureSelectedBlock() {
        List<String> list = lines;
        List<String> main = new ArrayList<>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("Reversal|Refund|Bounce|Return|Rtrn|REVERSAL|REFUND|BOUNCE|RETURN|RTRN", s);
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

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public List<Double> getSelectedAmountBlock() {
        return selectedAmountBlock;
    }

    public void setSelectedAmountBlock(List<Double> selectedAmountBlock) {
        this.selectedAmountBlock = selectedAmountBlock;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
