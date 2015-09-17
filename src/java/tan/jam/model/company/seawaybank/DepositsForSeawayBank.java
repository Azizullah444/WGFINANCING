/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.seawaybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForSeawayBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForSeawayBank onlineTransferFrom;
    private OnlineReversalFromForSeawayBank  onlineReversalFrom;
    public DepositsForSeawayBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForSeawayBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForSeawayBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*DAILY\\s+BALANCES");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForSeawayBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForSeawayBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForSeawayBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForSeawayBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
