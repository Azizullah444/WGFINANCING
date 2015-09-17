/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.blackhills;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class KeywordsForBlackHills{

    private List<String> words;
    private List<String> lines;
    private List<String> selectedBlock;
    private double sumForKeywords;
    private int totalKeywordRecords;
    private int noOfValuesLessThan3000;
    private List<String> keywordDate;
    private List<Double> keywordAmount;
    private List<String> keywordDescription;
    private List<String> keyDescription = new ArrayList<>();

    public KeywordsForBlackHills(List<String> lines, List<String> words) {
        this.words = words;
        this.lines = lines;
        this.keywordAmount = new ArrayList<>();
        this.keywordDate = new ArrayList<>();
        this.keywordDescription=new ArrayList<>();
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.sumForKeywords = extractAmountFromKeywordListAndReturnSum();
        this.totalKeywordRecords = returnTotalKeywordRecords();
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

            String value = FileUtils.getNegativeSignDetectedValue(FileUtils.getSecondLastElementFromArray(this.selectedBlock.get(a)));
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

    private List<String> captureSelectedBlock() {
        List<String> list = new ArrayList<>();
        for (int a = 0; a < words.size(); a++) {
            String wordLine = words.get(a);
            for (int x = 0; x < lines.size(); x++) {
                String result = FileUtils.getResultFromPattern(wordLine, lines.get(x));
                if (!result.startsWith("NoMatch")) {
                    if(result.startsWith("^\\D{3}\\d{2}")){
                    list.add(lines.get(x));}else {list.add(lines.get(x-1));}
                    keyDescription.add(words.get(a));
                }
            }
        }
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
}
