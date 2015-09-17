/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.rabobank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;


public class DebitsForRaboBank {

    private List<String> lines;
    private List<String> initialBlock;

    private OnlineTransferToForRaboBank onlineTransferTo;

    public DebitsForRaboBank(List<String> lines) {
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
        this.onlineTransferTo = new OnlineTransferToForRaboBank(initialBlock);

       // show();
    }

    private List<String> captureInitialBlock() {
        int startIndex = FileUtils.findStartIndex(lines, "^\\s*DEBITS","Date\\s+Description\\s+Subtractions");
        int endIndex = FileUtils.findStartIndex(lines, "CREDITS", "Date\\s+Description\\s+Additions");

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

    public OnlineTransferToForRaboBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForRaboBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
