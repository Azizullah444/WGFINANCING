/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.paradisebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForParadiseBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForParadiseBank onlineTransferTo;
    

    public DebitsForParadiseBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForParadiseBank(initialBlock);
    }
    private List<String> captureInitialBlock() {
        String start = "^\\s*CHECKS\\s+AND\\s+WITHDRAWALS";
        String end = "^\\s*CHECKS\\s+IN\\s+SERIAL\\s+NUMBER\\s+ORDER|^\\s*DAILY\\s+BALANCE\\s+INFORMATION";

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
    public OnlineTransferToForParadiseBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForParadiseBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
