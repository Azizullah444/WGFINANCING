/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofamerica;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DebitsForBankOfAmerica {

    private List<String> lines;
    private List<String> initialBlock;

    private OnlineTransferToForBankOfAmerca onlineTransferTo;

    public DebitsForBankOfAmerica(List<String> lines) {
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
        this.onlineTransferTo = new OnlineTransferToForBankOfAmerca(initialBlock);

       // show();
    }

    private List<String> captureInitialBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "Withdrawals\\s+and\\s+other\\s+debits","Date\\s+Description\\s+Amount");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "Total\\s+withdrawals\\s+and\\s+other\\s+debits");

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

    public OnlineTransferToForBankOfAmerca getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForBankOfAmerca onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
