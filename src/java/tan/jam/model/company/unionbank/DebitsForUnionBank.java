/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unionbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForUnionBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForUnionBank onlineTransferTo;
    

    public DebitsForUnionBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForUnionBank(initialBlock);
    }
    private List<String> captureInitialBlock() {
        String firstStart="^^\\s*Payments\\s+online\\s+and\\s+electronic\\s+banking";
        String firstEnd="Date\\s+Description/Location\\s+Reference\\s+Amount";
        String end = "^\\s*Total";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, end);
        
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
    public OnlineTransferToForUnionBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForUnionBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
