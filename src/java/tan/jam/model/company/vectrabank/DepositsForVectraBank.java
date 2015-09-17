/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.vectrabank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForVectraBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForVectraBank onlineTransferFrom;
    private OnlineReversalFromForVectraBank  onlineReversalFrom;
    public DepositsForVectraBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForVectraBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForVectraBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex=FileUtils.findStartIndex(lines,"^\\s*\\d*\\s+DEPOSITS/CREDITS","Date\\s+Amount\\s+Description");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\d*\\s+CHARGES/DEBITS");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForVectraBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForVectraBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForVectraBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForVectraBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
