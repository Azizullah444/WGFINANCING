/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.wellsfargo;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForWellsNew {

    private List<String> lines;
    private List<String> keywords;
    private List<String> initialBlock;
   
    private OnlineTransferToForWellsNew onlineTransferTo;

    DebitsForWellsNew(String[] pages, List<String> lines, List<String> words) {
        this.lines = lines;
        this.keywords = words;
        startExecution();
        this.onlineTransferTo = new OnlineTransferToForWellsNew(initialBlock);
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
       
    }


    private List<String> captureInitialBlock() {
        String start = "^\\s*Debits";
        String end = "\\$?\\d{1,}\\,?\\d{1,}?\\,?\\d{1,}?\\.\\d{1,2}\\s+Total\\s+debits";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public OnlineTransferToForWellsNew getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForWellsNew onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
}
