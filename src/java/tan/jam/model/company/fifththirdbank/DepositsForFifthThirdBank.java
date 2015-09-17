/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.fifththirdbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFifthThirdBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForFifthThirdBank onlineTransferFrom;
    private OnlineReversalFromForFifthThirdBank  onlineReversalFrom;
    public DepositsForFifthThirdBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForFifthThirdBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForFifthThirdBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex=FileUtils.findIndexBySingleValue(lines,"^\\s*Deposits\\s*/\\s*Credits");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Daily\\s+Balance\\s+Summary");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForFifthThirdBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFifthThirdBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForFifthThirdBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForFifthThirdBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
