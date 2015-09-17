/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFirstBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForFirstBank onlineTransferFrom;
    private OnlineReversalFromForFirstBank  onlineReversalFrom;
    public DepositsForFirstBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForFirstBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForFirstBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*DEPOSITS/CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*OTHER\\s+DEBITS|CHECKS/WITHDRAWALS");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForFirstBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFirstBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForFirstBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForFirstBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
