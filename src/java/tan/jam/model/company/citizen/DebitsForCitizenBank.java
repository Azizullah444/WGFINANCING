/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citizen;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForCitizenBank {

    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedRecords;
    List<String> mergedLines = new ArrayList<>();
    private OnlineTransferToForCitizenBank onlineTransferTo;
    

    public DebitsForCitizenBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }


    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForCitizenBank(initialBlock);
    }
    private List<String> captureInitialBlock() {
        int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*T\\s*R\\s*A\\s*N\\s*S\\s*A\\s*C\\s*T\\s*I\\s*O\\s*N\\s+D\\s*E\\s*T\\s*A\\s*I\\s*L\\s*S");
        int EndIndex = FileUtils.findIndexBySingleValue(lines,StartIndex,"^\\s*Deposits\\s*&\\s*Credits");
        return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
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

    public List<String> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<String> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
    public OnlineTransferToForCitizenBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForCitizenBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }

}
