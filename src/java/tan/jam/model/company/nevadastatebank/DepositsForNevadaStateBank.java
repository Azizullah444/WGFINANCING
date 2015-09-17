/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nevadastatebank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForNevadaStateBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForNevadaStateBank onlineTransferFrom;
    private OnlineReversalFromForNevadaStateBank  onlineReversalFrom;
    public DepositsForNevadaStateBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForNevadaStateBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForNevadaStateBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\d*\\s+DEPOSITS/CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\d*\\s+CHARGES/DEBITS|^\\s*\\d*\\s+CHECKS\\s+PROCESSED");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForNevadaStateBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForNevadaStateBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForNevadaStateBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForNevadaStateBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
