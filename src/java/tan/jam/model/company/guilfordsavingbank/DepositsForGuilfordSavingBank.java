/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guilfordsavingbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForGuilfordSavingBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForGuilfordSavingBank onlineTransferFrom;
    private OnlineReversalFromForGuilfordSavingBank  onlineReversalFrom;
    public DepositsForGuilfordSavingBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForGuilfordSavingBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForGuilfordSavingBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Deposits/Credits");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Total\\s+Deposits/Credits");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForGuilfordSavingBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForGuilfordSavingBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForGuilfordSavingBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForGuilfordSavingBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
