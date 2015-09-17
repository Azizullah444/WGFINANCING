/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.usbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForUSBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForUSBank onlineTransferFrom;
    private OnlineReversalFromForUSBank  onlineReversalFrom;
    public DepositsForUSBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForUSBank(this.selectedBlock);
        this.onlineReversalFrom=  new OnlineReversalFromForUSBank(this.selectedBlock);
        //show();
    }
    private List<String> captureSelectedBlock(){
        
        int startIndex = FileUtils.findStartIndex(lines, "^\\s*Other\\s+Deposits", "Date\\s+Description\\s+of\\s+Transaction");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Total\\s+Other\\s+Deposits");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForUSBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForUSBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForUSBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForUSBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
