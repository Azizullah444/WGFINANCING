/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citynationalbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DebitsForBankOfCityNationalBank {

    private List<String> lines;
    private List<String> initialBlock;

    private OnlineTransferToForCityNationalBank onlineTransferTo;

    public DebitsForBankOfCityNationalBank(List<String> lines) {
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
        this.onlineTransferTo = new OnlineTransferToForCityNationalBank(initialBlock);

       // show();
    }

    private List<String> captureInitialBlock() {
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*ELECTRONIC\\s+DEBITS|^\\s*OTHER\\s+DEBITS");
        int endIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DAILY\\s+BALANCES");

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

    public OnlineTransferToForCityNationalBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForCityNationalBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
