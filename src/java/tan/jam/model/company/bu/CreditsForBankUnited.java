/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class CreditsForBankUnited {
    private List<String> lines;
    private List<String> selectedBlock;
    private OnlineTransferToForBankUnited onlineTransferTo;

    public CreditsForBankUnited(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
   
    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferTo = new OnlineTransferToForBankUnited(selectedBlock);
        
        
    }
   
   private List<String> captureSelectedBlock() {
        String start = "Date\\s+Description\\s+Withdrawals\\s+Deposits\\s+Balance";
        String end = "^\\s*Check\\s+Transactions|^\\s*Balances\\s+by\\s+Date";

        List<String> list = FileUtils.getSelectedBlock(lines, start, end);

        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public OnlineTransferToForBankUnited getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBankUnited onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
