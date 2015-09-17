/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citynationalbank;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsBankOfCityNationalBank {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForCityNationalBank onlineTransferFrom;
    private OnlineReversalFromForCityNationalBank onlineTLRFrom;

    public DepositsBankOfCityNationalBank(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForCityNationalBank(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForCityNationalBank(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "^\\s*DEPOSITS","Date\\s+Description\\s+Reference");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*CHECKS\\s+PAID|^\\s*ELECTRONIC\\s+DEBITS|^\\s*OTHER\\s+DEBITS");
        
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

    public OnlineTransferFromForCityNationalBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForCityNationalBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForCityNationalBank getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForCityNationalBank onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
