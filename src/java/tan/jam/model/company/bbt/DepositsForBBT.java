/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bbt;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBBT{
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForBBT onlineTransferFrom;
    private OnlineReversalFromForBBT  onlineReversalFrom;
    public DepositsForBBT(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForBBT(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForBBT(this.selectedBlock);
        //show();
    }
    private List<String> captureSelectedBlock(){
        String firstStart="Deposits,\\s+credits\\s+and\\s+interest";
        String firstEnd="DATE\\s+DESCRIPTION";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex = FileUtils.findIndexBySingleValue(lines,"Total\\s+deposits,\\s+credits\\s+and\\s+interest");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForBBT getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBBT onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForBBT getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForBBT onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
