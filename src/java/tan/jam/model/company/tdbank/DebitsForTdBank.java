/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForTdBank {
    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
   // List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForTdBank onlineTransferTo;
    public DebitsForTdBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForTdBank(initialBlock);
    }
    private List<String>  captureInitialBlock(){
       
         int startIndex = FileUtils.findStartIndex(lines, "Electronic\\s+Payments|Other\\s+Withdrawals", "DESCRIPTION");
         int endIndex=FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCE\\s+SUMMARY");
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
        for(String s: list){
            //System.out.println(s);
        }
        
        return list = FileUtilsForTD.getSelectedBlockDebits(lines);
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
    public OnlineTransferToForTdBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForTdBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
