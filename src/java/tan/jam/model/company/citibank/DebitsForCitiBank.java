/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForCitiBank {
    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferToForCitiBank onlineTransferTo;

    public DebitsForCitiBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
       this.initialBlock = captureSelectedBlock();
       this.onlineTransferTo = new OnlineTransferToForCitiBank(this.initialBlock);
               
    }
   private List<String> captureSelectedBlock(){
         String start = "^\\s*CHECKING\\s+ACTIVITY";
        String end = "^\\s*Total\\s+Debits/Credits";
        
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

    public OnlineTransferToForCitiBank getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForCitiBank onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
    
}
