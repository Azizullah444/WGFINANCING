/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstnationalbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForFirstNationalBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForFirstNationalBank onlineTransferTo;
    

    public DebitsForFirstNationalBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForFirstNationalBank(initialBlock);
    }
    private List<String> captureInitialBlock() {
        String start = "O T H E R  D E B I T  T R A N S A C T I O N S";
        String end = "C H E C K S";
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
    public OnlineTransferToForFirstNationalBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForFirstNationalBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
