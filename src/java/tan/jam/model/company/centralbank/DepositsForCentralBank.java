/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.centralbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForCentralBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForCentralBank onlineTransferFrom;
    private OnlineReversalFromForCentralBank  onlineReversalFrom;
    public DepositsForCentralBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForCentralBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForCentralBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
        
          int StartIndex = FileUtils.findIndexBySingleValue(lines, "Deposits\\s+and\\s+Other\\s+Credits");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"checks\\s+checks|Withdrawals\\s+and\\s+Other\\s+Debits");
          
          return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForCentralBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForCentralBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForCentralBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForCentralBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
