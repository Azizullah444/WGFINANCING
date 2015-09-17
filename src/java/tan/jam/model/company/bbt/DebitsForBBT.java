/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bbt;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForBBT {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForBBT onlineTransferTo;
    

    public DebitsForBBT(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForBBT(initialBlock);
    }
    private List<String> captureInitialBlock() {
        String firstStart="Other\\s+withdrawals,\\s+debits\\s+and\\s+service\\s+charges";
        String firstEnd="DATE\\s+DESCRIPTION";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex = FileUtils.findIndexBySingleValue(lines,"Total\\s+other\\s+withdrawals,\\s+debits\\s+and\\s+service\\s+charges");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);

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
    public OnlineTransferToForBBT getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBBT onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
