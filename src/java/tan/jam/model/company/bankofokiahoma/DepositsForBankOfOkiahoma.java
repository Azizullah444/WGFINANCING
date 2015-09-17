/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofokiahoma;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsForBankOfOkiahoma {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForBankOfOkiahoma onlineTransferFrom;
    private OnlineReversalFromForBankOfOkiahoma onlineTLRFrom;

    public DepositsForBankOfOkiahoma(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForBankOfOkiahoma(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForBankOfOkiahoma(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DEPOSITS");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*WITHDRAWALS");
        
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

    public OnlineTransferFromForBankOfOkiahoma getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBankOfOkiahoma onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForBankOfOkiahoma getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForBankOfOkiahoma onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
