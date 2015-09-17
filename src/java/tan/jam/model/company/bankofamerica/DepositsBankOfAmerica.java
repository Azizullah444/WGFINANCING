/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofamerica;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsBankOfAmerica {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForBankOfAmerica onlineTransferFrom;
    private OnlineReversalFromForBankOfAmerica onlineTLRFrom;

    public DepositsBankOfAmerica(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForBankOfAmerica(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForBankOfAmerica(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "Deposits\\s+and\\s+other\\s+credits","Date\\s+Description\\s+Amount");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "Total\\s+deposits\\s+and\\s+other\\s+credits");
        
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

    public OnlineTransferFromForBankOfAmerica getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBankOfAmerica onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForBankOfAmerica getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForBankOfAmerica onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
