/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nevadastatebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForNevadaStateBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForNevadaStateBank onlineTransferTo;
    

    public DebitsForNevadaStateBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForNevadaStateBank(initialBlock);
    }
    private List<String> captureInitialBlock() {
        String start = "^\\s*\\d*\\s+CHARGES/DEBITS";
        String end = "^\\s*\\d*\\s+CHECKS\\s+PROCESSED|^\\s*DAILY\\s+BALANCES";
        return FileUtils.getSelectedBlock(lines, start, end);
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

    public List<String> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<String> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
    public OnlineTransferToForNevadaStateBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForNevadaStateBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
