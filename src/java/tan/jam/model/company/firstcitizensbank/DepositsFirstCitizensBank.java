/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstcitizensbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsFirstCitizensBank {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForFirstCitizensBank onlineTransferFrom;
    private OnlineReversalFromForFirstCitizensBank onlineTLRFrom;

    public DepositsFirstCitizensBank(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForFirstCitizensBank(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForFirstCitizensBank(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Deposits\\s+To\\s+Your\\s+Account|^\\s*Other\\s+Credits\\s+To\\s+Your\\s+Account");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Total\\s+\\d+");
        
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

    public OnlineTransferFromForFirstCitizensBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFirstCitizensBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForFirstCitizensBank getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForFirstCitizensBank onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
