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
public class KeywordsForChase {

    private List<String> words;
    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> depositBlock;
    private List<String> debitBlock;
    private double sumForKeywords;
    private int totalKeywordRecords;
    private int noOfValuesLessThan3000;
    private List<String> keywordDate;
    private List<Double> keywordAmount;
    private List<String> keywordDescription;
    private List<Double> DailypaymentkeywordAmount;
    private List<String> DailypaymentkeywordDate;
    private List<String> DailypaymentkeywordDescription;
    private List<String> keyDescription = new ArrayList<>();
    private List<String> dailykeyDescription=new ArrayList<>();
    
    public KeywordsForChase(List<String> lines, List<String> words) {
        this.words = words;
        this.lines = lines;
        this.depositBlock=captureDepositBlock();
        this.debitBlock=captureDebitBlock();
        this.keywordAmount = new ArrayList<>();
        this.keywordDate = new ArrayList<>();
        this.keywordDescription=new ArrayList<>();
        this.DailypaymentkeywordAmount = new ArrayList<>();
        this.DailypaymentkeywordDate = new ArrayList<>();
        this.DailypaymentkeywordDescription=new ArrayList<>();
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlockForDeposit();
        this.sumForKeywords = extractAmountFromKeywordListAndReturnSum();
        this.totalKeywordRecords = returnTotalKeywordRecords();
        captureSelectedBlockForDebit();
        show();
    }

    private void show() {
        for (int a = 0; a < selectedBlock.size(); a++) {
            System.out.println(a + " ] " + this.selectedBlock.get(a));
           
        }
    }

    private int returnTotalKeywordRecords() {
        return this.selectedBlock.size();
    }

    private double extractAmountFromKeywordListAndReturnSum() {
        List<Double> list = new ArrayList<>();
        List<String> datelist = new ArrayList<>();
        int count = 0;
        double sum = 0;
        for (int a = 0; a < this.selectedBlock.size(); a++) {

            String value = FileUtils.getLastElementFromArray(this.selectedBlock.get(a));
            value = FileUtils.getDollarSignRemovedValue(value);
            double d = FileUtils.convertStringToDouble(value);
            String[] temp = this.selectedBlock.get(a).split(" ");
            datelist.add(temp[0]);
            list.add(d);
            
        }
        for (int a = 0; a < list.size(); a++) {
            double d = list.get(a);
            if (d < 3000) {
                
                ++count;
            }
            else{
                keywordAmount.add(d);
                keywordDate.add(datelist.get(a));
                keywordDescription.add(keyDescription.get(a));
                sum += d;
            }
        }
        setNoOfValuesLessThan3000(count);
        return sum;

    }
     private void captureSelectedBlockForDebit() {
        List<String> List = new ArrayList<>();
        List<Double> list = new ArrayList<>();
        List<String> datelist = new ArrayList<>();
        List<Double> CheckDailyPaymentAmount= new ArrayList<>();
            for (int x = 0; x < debitBlock.size(); x++) {
              for (int a = 0; a < words.size(); a++) {
                String wordLine = words.get(a).toLowerCase();
                String result = FileUtils.getResultFromPattern(wordLine, debitBlock.get(x).toLowerCase());
                if (!result.startsWith("NoMatch")) { 
                              
                    String result1=FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", debitBlock.get(x));
                        if(!result1.startsWith("NoMatch"))
                        {  
                            List.add(debitBlock.get(x));
                            dailykeyDescription.add(words.get(a).toLowerCase());
                        }
                    break;
                }
            }
        }
        for (int a = 0; a < List.size(); a++) {
            String value = FileUtils.getLastElementFromArray(List.get(a));
            value = FileUtils.getDollarSignRemovedValue(value);
            double d = FileUtils.convertStringToDouble(value);
            d= Math.abs(d);
            String[] temp = List.get(a).split("\\s+");
            datelist.add(temp[0]);
            list.add(d);
            
        }
        for (int a = 0; a < list.size(); a++) {
            double d = list.get(a);
            if(CheckDailyPaymentAmount.contains(d))
            {   
              //  if (!DailypaymentkeywordDescription.contains(dailykeyDescription.get(a)))
              //  { 
                DailypaymentkeywordAmount.add(d);
                DailypaymentkeywordDate.add(datelist.get(a));
                DailypaymentkeywordDescription.add(dailykeyDescription.get(a));
              //  }
            }else
            {CheckDailyPaymentAmount.add(d);}
            
        }
    }
    private List<String> captureSelectedBlockForDeposit() {
        List<String> list = new ArrayList<>();
            for (int x = 0; x < depositBlock.size(); x++) {
              for (int a = 0; a < words.size(); a++) {
                String wordLine = words.get(a).toLowerCase();
                String result = FileUtils.getResultFromPattern(wordLine, depositBlock.get(x).toLowerCase());
                  if (!result.startsWith("NoMatch")) { 
                    String result1=FileUtils.getResultFromPattern("^\\d{2}/\\d{2}", depositBlock.get(x));
                   
                        if(!result1.startsWith("NoMatch"))
                        {  
                            list.add(depositBlock.get(x));
                            keyDescription.add(words.get(a).toLowerCase());     
                        }
                        break;
                }
            }
        }
        return list;
    }
    private List<String> captureDebitBlock() {
        String start = "An image of this check may be available for you to view on Chase\\.com";
        String end = "DAILY ENDING BALANCE";

        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        return list;
    }
    
    private List<String> captureDepositBlock() {
        int startIndex = FileUtils.findStartIndex(lines,"DEPOSITS\\s+AND\\s+ADDITIONS", "DATE\\s+DESCRIPTION\\s+AMOUNT");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"Total\\s+Deposits\\s+and\\s+Additions\\s+\\$");
        List<String> list = FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);

        return list;
    }
    
    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public double getSumForKeywords() {
        return sumForKeywords;
    }

    public void setSumForKeywords(double sumForKeywords) {
        this.sumForKeywords = sumForKeywords;
    }

    public int getTotalKeywordRecords() {
        return totalKeywordRecords;
    }

    public void setTotalKeywordRecords(int totalKeywordRecords) {
        this.totalKeywordRecords = totalKeywordRecords;
    }

    public int getNoOfValuesLessThan3000() {
        return noOfValuesLessThan3000;
    }

    public void setNoOfValuesLessThan3000(int noOfValuesLessThan3000) {
        this.noOfValuesLessThan3000 = noOfValuesLessThan3000;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getKeywordDate() {
        return keywordDate;
    }

    public void setKeywordDate(List<String> keywordDate) {
        this.keywordDate = keywordDate;
    }

    public List<Double> getKeywordAmount() {
        return keywordAmount;
    }

    public void setKeywordAmount(List<Double> keywordAmount) {
        this.keywordAmount = keywordAmount;
    }
      public List<String> getKeywordDescription() {
        return keywordDescription;
    }

    public void setKeywordDescription(List<String> keywordDescription) {
        this.keywordDescription = keywordDescription;
    }
   public List<String> getDepositBlock() {
        return depositBlock;
    }

    public void setDepositBlock(List<String> depositBlock) {
        this.depositBlock = depositBlock;
    }
    public List<String> getDebitBlock() {
        return debitBlock;
    }

    public void setDebitBlock(List<String> debitBlock) {
        this.debitBlock = debitBlock;
    }
    public List<Double> getDailypaymentKeywordAmount() {
        return DailypaymentkeywordAmount;
    }

    public void setDailypaymentKeywordAmount(List<Double> DailypaymentkeywordAmount) {
        this.DailypaymentkeywordAmount = DailypaymentkeywordAmount;
    }

    public List<String> getDailypaymentKeywordDate() {
        return DailypaymentkeywordDate;
    }

    public void setDailypaymentKeywordDate(List<String> DailypaymentkeywordDate) {
        this.DailypaymentkeywordDate = DailypaymentkeywordDate;
    }
      public List<String> getDailypaymentKeywordDescription() {
        return DailypaymentkeywordDescription;
    }

    public void setDailypaymentKeywordDescription(List<String> DailypaymentkeywordDescription) {
        this.DailypaymentkeywordDescription = DailypaymentkeywordDescription;
    }
}
