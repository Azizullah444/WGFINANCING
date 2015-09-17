/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bestbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBestBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForBestBank onlineTransferFrom;
    private OnlineReversalFromForBestBank  onlineReversalFrom;
    public DepositsForBestBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForBestBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForBestBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*DAILY\\s+BALANCES");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForBestBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBestBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForBestBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForBestBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
