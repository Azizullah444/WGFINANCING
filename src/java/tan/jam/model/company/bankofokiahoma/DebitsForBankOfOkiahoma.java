/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofokiahoma;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DebitsForBankOfOkiahoma {

    private List<String> lines;
    private List<String> initialBlock;

    private OnlineTransferToForBankOfOkiahoma onlineTransferTo;

    public DebitsForBankOfOkiahoma(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void show() {
        for (String s : initialBlock) {
            System.out.println(s);
        }
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForBankOfOkiahoma(initialBlock);

       // show();
    }

    private List<String> captureInitialBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*WITHDRAWALS");
        int endIndex = FileUtils.findIndexBySingleValue(lines, startIndex, "^\\s*CHECKS|^\\s*DAILY\\s+ACCOUNT\\s+BALANCE");

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

    public OnlineTransferToForBankOfOkiahoma getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBankOfOkiahoma onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
