/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofthewest;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBankOfTheWest {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForBankOfTheWest onlineTransferFrom;
    private OnlineReversalFromForBankOfTheWest  onlineReversalFrom;
    public DepositsForBankOfTheWest(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
    private void show(){
        for(String s: this.selectedBlock){
            System.out.println(s);
        }
    }
    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFrom = new OnlineTransferFromForBankOfTheWest(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForBankOfTheWest(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
        
          int StartIndex = FileUtils.findStartIndex(lines, "^\\s*Credits", "Date\\s+Amount\\s+Description");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"\\d+\\s+credits\\s+for\\s+a\\s+total\\s+of");
          
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForBankOfTheWest getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBankOfTheWest onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForBankOfTheWest getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForBankOfTheWest onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
