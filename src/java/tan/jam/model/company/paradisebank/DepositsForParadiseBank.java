/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.paradisebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForParadiseBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForParadiseBank onlineTransferFrom;
    private OnlineReversalFromForParadiseBank  onlineReversalFrom;
    public DepositsForParadiseBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForParadiseBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForParadiseBank(this.selectedBlock);
        //show();
    }
    
    private List<String> captureSelectedBlock(){
      
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*DEPOSITS\\s+AND\\s+ADDITIONS");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*CHECKS\\s+AND\\s+WITHDRAWALS|^\\s*CHECKS\\s+IN\\s+SERIAL\\s+NUMBER\\s+ORDER");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForParadiseBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForParadiseBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForParadiseBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForParadiseBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
