/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsriver;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForWellsRiver {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForWellsRiver onlineTransferFrom;
    private OnlineReversalFromForWellsRiver  onlineReversalFrom;
    public DepositsForWellsRiver(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForWellsRiver(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForWellsRiver(this.selectedBlock);
        //show();
    }
    
    private List<String> captureSelectedBlock(){
      
        int startIndex = FileUtils.findStartIndex(lines,"Deposits\\s+and\\s+Other\\s+Credits", "Date\\s+Amount\\s+Description");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Other\\s+Debits|^\\s*Checks\\s+in\\s+Order\\s+by\\s+Check\\s+Number");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForWellsRiver getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForWellsRiver onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForWellsRiver getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForWellsRiver onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
