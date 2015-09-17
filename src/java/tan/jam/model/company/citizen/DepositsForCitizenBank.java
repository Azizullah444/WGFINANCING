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
public class DepositsForCitizenBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForCitizenBank onlineTransferFrom;
    private OnlineReversalFromForCitizenBank  onlineReversalFrom;
    public DepositsForCitizenBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForCitizenBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForCitizenBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex=FileUtils.findStartIndex(lines,"^\\s*Deposits\\s+&\\s+Credits","Date\\s+Amount\\s+Description");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Daily\\s+Balance");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForCitizenBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForCitizenBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForCitizenBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForCitizenBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
