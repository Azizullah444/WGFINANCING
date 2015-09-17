/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.astoriabank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForAstoriaBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForAstoriaBank onlineTransferFrom;
    private OnlineReversalFromForAstoriaBank  onlineReversalFrom;
    public DepositsForAstoriaBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForAstoriaBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForAstoriaBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*CREDITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*DAILY\\s+BALANCES");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForAstoriaBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForAstoriaBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForAstoriaBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForAstoriaBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
