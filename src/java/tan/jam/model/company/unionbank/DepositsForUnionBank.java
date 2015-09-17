/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.unionbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForUnionBank{
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForUnionBank onlineTransferFrom;
    private OnlineReversalFromForUnionBank onlineReversalFrom;
    public DepositsForUnionBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForUnionBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForUnionBank(this.selectedBlock);
        //show();
    }
    private List<String> captureSelectedBlock(){
        String firstStart="^\\s*Additions";
        String firstEnd="Date\\s+Description/Location\\s+Reference\\s+Amount";
        String end = "^\\s*Total";
        int startIndex = FileUtils.findStartIndex(lines, firstStart, firstEnd);
        int endIndex=FileUtils.findIndexBySingleValue(lines, startIndex, end);
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForUnionBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForUnionBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForUnionBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForUnionBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
