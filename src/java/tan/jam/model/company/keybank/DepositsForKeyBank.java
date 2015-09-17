/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.keybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForKeyBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForKeyBank onlineTransferFrom;
    private OnlineReversalFromForKeyBank  onlineReversalFrom;
    public DepositsForKeyBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForKeyBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForKeyBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
        
          String firstStart="^\\s*Additions";
          String firstEnd="^\\s*Deposits";
          int StartIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Total\\s+additions");
          
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForKeyBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForKeyBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForKeyBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForKeyBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
