/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.santacruzcountrybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DebitsForSantaCruzCountryBank {

    private List<String> lines;
    private List<String> initialBlock;

    private OnlineTransferToForSantaCruzCountryBank onlineTransferTo;

    public DebitsForSantaCruzCountryBank(List<String> lines) {
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
        this.onlineTransferTo = new OnlineTransferToForSantaCruzCountryBank(initialBlock);

       // show();
    }

    private List<String> captureInitialBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "^\\s*Electronic\\s+Debits|^\\s*Other\\s+Debits","Date\\s+Description\\s+Amount");
        int endIndex = FileUtils.findStartIndex(lines, "^\\s*Deposits|^\\s+Electronic\\s+Credits", "Date\\s+Description\\s+Amount");

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

    public OnlineTransferToForSantaCruzCountryBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForSantaCruzCountryBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
