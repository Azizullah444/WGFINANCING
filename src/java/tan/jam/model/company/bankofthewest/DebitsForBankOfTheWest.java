/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofthewest;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForBankOfTheWest {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForBankOfTheWest onlineTransferTo;
    

    public DebitsForBankOfTheWest(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForBankOfTheWest(initialBlock);
    }
    private List<String> captureInitialBlock() {
        
          int StartIndex = FileUtils.findStartIndex(lines,"^\\s*Withdrawals", "Date\\s+Amount\\s+Description");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"\\d+\\s+withdrawals\\s+for\\s+a\\s+total\\s+of");
          
        return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
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
    public OnlineTransferToForBankOfTheWest getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBankOfTheWest onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
