/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.mercantilenew;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForMercantileNew {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForMercantileNew onlineTransferFrom;
    private OnlineReversalFromForMercantileNew  onlineReversalFrom;
    public DepositsForMercantileNew(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForMercantileNew(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForMercantileNew(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s+CHECKS|^\\s*DEBITS");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForMercantileNew getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForMercantileNew onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForMercantileNew getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForMercantileNew onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
