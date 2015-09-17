/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.banklandmark;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForBankLandmark {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForBankLandmark onlineTransferTo;
    

    public DebitsForBankLandmark(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForBankLandmark(initialBlock);
    }
    private List<String> captureInitialBlock() {
        
        int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\*+\\s*Other\\s+Debits");
        int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Total\\s+Other\\s+Debits");
          
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
    public OnlineTransferToForBankLandmark getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBankLandmark onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
