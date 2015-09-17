/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForTdBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForTdBank onlineTransferFrom;
    private OnlineReversalFromForTdBank  onlineReversalFrom;
    public DepositsForTdBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForTdBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForTdBank(this.selectedBlock);
        //show();
    }
    
    private List<String> captureSelectedBlock(){
        
        int startIndex = FileUtils.findStartIndex(lines, "DAILY\\s+ACCOUNT\\s+ACTIVITY","Deposits|Electronic\\s+Deposits|Other\\s+Credits");
        int endIndex = FileUtils.findEndIndex(lines, "Electronic\\s+Payments|Other\\s+Withdrawals", "DESCRIPTION", startIndex);
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForTdBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForTdBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForTdBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForTdBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
