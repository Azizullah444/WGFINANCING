/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mechanicsbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForMechanicsBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForMechanicsBank onlineTransferFrom;
    private OnlineReversalFromForMechanicsBank  onlineReversalFrom;
    public DepositsForMechanicsBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
    private void show(){
        for(String s: this.selectedBlock){
           // System.out.println(s);
        }
    }
    private void startExecution() {
       this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFrom = new OnlineTransferFromForMechanicsBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForMechanicsBank(this.selectedBlock);
        //show();
    }
    
    private List<String> captureSelectedBlock(){
      
        int startIndex = FileUtils.findStartIndex(lines,"^\\s*Other\\s+Credits", "DATE\\s+DESCRIPTION\\s+AMOUNT");
        int endIndex = FileUtils.findStartIndex(lines,"^\\s*Other\\s+Debits", "DATE\\s+DESCRIPTION");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForMechanicsBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForMechanicsBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForMechanicsBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForMechanicsBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
