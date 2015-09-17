/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.woodforest;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForWoodForest {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForWoodForest onlineTransferFrom;
    private OnlineReversalFromForWoodForest  onlineReversalFrom;
    public DepositsForWoodForest(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForWoodForest(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForWoodForest(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Deposits\\s+and\\s+Other\\s+Credits");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Withdrawals\\s+and\\s+Other\\s+Debits");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForWoodForest getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForWoodForest onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForWoodForest getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForWoodForest onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
