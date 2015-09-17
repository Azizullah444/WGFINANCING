/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mercentile;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForMercentile {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForMercentile onlineTransferFrom;
    private OnlineReversalFromForMercentile  onlineReversalFrom;
    public DepositsForMercentile(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForMercentile(this.selectedBlock); 
        this.onlineReversalFrom=new OnlineReversalFromForMercentile(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"- - -\\s*OTHER CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"- - -\\s*CHECKS|- - -\\s*OTHER\\s+DEBITS"); 
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForMercentile getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForMercentile onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForMercentile getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForMercentile onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
