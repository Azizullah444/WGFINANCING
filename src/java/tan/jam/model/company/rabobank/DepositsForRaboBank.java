/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.rabobank;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsForRaboBank {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForRaboBank onlineTransferFrom;
    private OnlineReversalFromForRaboBank onlineTLRFrom;

    public DepositsForRaboBank(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForRaboBank(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForRaboBank(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "CREDITS", "Date\\s+Description\\s+Additions");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCES");
        
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

    public OnlineTransferFromForRaboBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForRaboBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForRaboBank getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForRaboBank onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
