/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.valuebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForValueBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForValueBank onlineTransferFrom;
    private OnlineReversalFromForValueBank  onlineReversalFrom;
    public DepositsForValueBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForValueBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForValueBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         
          int StartIndex = FileUtils.findIndexBySingleValue(lines, "-------------\\s*Deposits/Other\\s+Credits\\s*-----------");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"Checks\\s+listed\\s+in\\s+numerical\\s+order|--------\\s*Other\\s+Debits\\s*-------");
          
          return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForValueBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForValueBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForValueBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForValueBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
