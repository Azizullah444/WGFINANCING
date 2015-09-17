/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.santacruzcountrybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DepositsForSantaCruzCountryBank {

    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferFromForSantaCruzCountryBank onlineTransferFrom;
    private OnlineReversalFromForSantaCruzCountryBank onlineTLRFrom;

    public DepositsForSantaCruzCountryBank(List<String> lines) {
        this.lines = lines;

        this.initialBlock = captureSelectedBlock();
        startExecution();
    }

    private void startExecution() {
        executeOnlineTransferFrom();
        executeOnlineTLRFrom();
    }

    private void executeOnlineTransferFrom() {
        this.onlineTransferFrom = new OnlineTransferFromForSantaCruzCountryBank(this.initialBlock);
    }

    private void executeOnlineTLRFrom() {
        this.onlineTLRFrom = new OnlineReversalFromForSantaCruzCountryBank(this.initialBlock);
    }

    private List<String> captureSelectedBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "^\\s*Deposits|^\\s+Electronic\\s+Credits|^\\s*Other\\s+Credits", "Date\\s+Description\\s+Amount");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Daily\\s+Balances");
        
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

    public OnlineTransferFromForSantaCruzCountryBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForSantaCruzCountryBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }

    public OnlineReversalFromForSantaCruzCountryBank getOnlineTLRFrom() {
        return onlineTLRFrom;
    }

    public void setOnlineTLRFrom(OnlineReversalFromForSantaCruzCountryBank onlineTLRFrom) {
        this.onlineTLRFrom = onlineTLRFrom;
    }

}
