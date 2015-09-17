/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.chase;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForChase {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForChase onlineTransferFrom;
    private OnlineReversalFromForChase  onlineReversalFrom;
    public DepositsForChase(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForChase(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForChase(this.selectedBlock);
        //show();
    }
    
    private List<String> captureSelectedBlock(){
      
        int startIndex = FileUtils.findStartIndex(lines,"DEPOSITS\\s+AND\\s+ADDITIONS", "DATE\\s+DESCRIPTION\\s+AMOUNT");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"Total\\s+Deposits\\s+and\\s+Additions\\s+\\$");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForChase getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForChase onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForChase getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForChase onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
