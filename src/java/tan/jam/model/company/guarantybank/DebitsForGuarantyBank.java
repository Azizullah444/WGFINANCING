/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForGuarantyBank {
    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferToForGuarantyBank onlineTransferTo;

    public DebitsForGuarantyBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForGuarantyBank(initialBlock);
    }
    private List<String> captureInitialBlock(){
        String start = " - - -\\s+MISCELLANEOUS DEBITS\\s+- - -| - - -\\s+ATM/DEBIT CARD TRANSACTIONS\\s+- - -";
        String end="- - - -\\s+CHECKS PAID\\s+- - - |- - -\\s+DAILY BALANCE SUMMARY\\s+- - -";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
       
        return list;
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

    public OnlineTransferToForGuarantyBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForGuarantyBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
    
}
