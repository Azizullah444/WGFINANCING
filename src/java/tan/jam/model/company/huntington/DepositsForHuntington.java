/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.huntington;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForHuntington {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForHuntington onlineTransferFrom;
    private OnlineReversalFromForHuntington  onlineReversalFrom;
    public DepositsForHuntington(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForHuntington(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForHuntington(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Deposits\\s+\\(\\+\\)");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Checks\\s+\\(\\-\\)|^\\s*Other\\s+Debits\\s+\\(\\-\\)");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForHuntington getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForHuntington onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForHuntington getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForHuntington onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
