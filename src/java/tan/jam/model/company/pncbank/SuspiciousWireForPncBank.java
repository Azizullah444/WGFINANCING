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
public class SuspiciousWireForPncBank {

    private List<String> selectedBlockForDebits;
    private List<String> lines;
    private List<String> selectedBlockForDeposits;
    private List<String> suspiciousWireFromDebits;
    private List<String> suspiciousWireFromDeposits;

    public SuspiciousWireForPncBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlockForDebits = captureSelectedBlockForDebits();
        this.selectedBlockForDeposits = captureSelectedBlockForDeposits();
    }

    private List<String> captureSelectedBlockForDeposits() {
        String start = "Activity\\s+Detail";
        String end = "Checks\\s+and\\s+Other\\s+Deductions";
        List<String> mainList = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                mainList.add(s);
            }
        }
        return mainList;
    }

    private List<String> captureSelectedBlockForDebits() {
        String start = "ACH\\s+Deductions";
        String end = "Detail\\s+of\\s+Services\\s+Used\\s+During\\s+Current\\s+Period";

        List<String> mainList = new ArrayList<>();
        List<String> index = FileUtils.getSelectedBlock(lines, start, end);

        return mainList;
    }

    public List<String> getSelectedBlockForDebits() {
        return selectedBlockForDebits;
    }

    public void setSelectedBlockForDebits(List<String> selectedBlockForDebits) {
        this.selectedBlockForDebits = selectedBlockForDebits;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getSelectedBlockForDeposits() {
        return selectedBlockForDeposits;
    }

    public void setSelectedBlockForDeposits(List<String> selectedBlockForDeposits) {
        this.selectedBlockForDeposits = selectedBlockForDeposits;
    }

    public List<String> getSuspiciousWireFromDebits() {
        return suspiciousWireFromDebits;
    }

    public void setSuspiciousWireFromDebits(List<String> suspiciousWireFromDebits) {
        this.suspiciousWireFromDebits = suspiciousWireFromDebits;
    }

    public List<String> getSuspiciousWireFromDeposits() {
        return suspiciousWireFromDeposits;
    }

    public void setSuspiciousWireFromDeposits(List<String> suspiciousWireFromDeposits) {
        this.suspiciousWireFromDeposits = suspiciousWireFromDeposits;
    }
}
