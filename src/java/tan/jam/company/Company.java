/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.company;

import java.util.List;


public abstract class Company {

    private String companyName;
    private String accountNumber;
    private String date;
    private double totalDeposits;
    private double averageLedgerBalance;

    private double sumOfOnlineTransferFrom;
    private double sumOfReversalFrom;
    
    private int noOfNegativeValues;
    private double minValue;
    
    private List<String> onlineTransferToDate;
    private List<String> onlineTransferToDescription;
    private List<String> onlineTransferToCheckNo;
    private List<String> onlineTransferToCheckNoDuplicateRemoved;
    private List<Double> onlineTransferToAmount;
    
    private double keywordSum;
    private List<Double> keywordAmount;
    private List<String> keywordDate;
    private List<String> keywordDescription;

    private List<Double> DailypaymentkeywordAmount;
    private List<String> DailypaymentkeywordDate;
    private List<String> DailypaymentkeywordDescription;
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalDeposits() {
        return totalDeposits;
    }

    public void setTotalDeposits(double totalDeposits) {
        this.totalDeposits = totalDeposits;
    }

    public double getSumOfOnlineTransferFrom() {
        return sumOfOnlineTransferFrom;
    }

    public void setSumOfOnlineTransferFrom(double sumOfOnlineTransferFrom) {
        this.sumOfOnlineTransferFrom = sumOfOnlineTransferFrom;
    }

    public double getSumOfReversalFrom() {
        return sumOfReversalFrom;
    }

    public void setSumOfReversalFrom(double sumOfReversalFrom) {
        this.sumOfReversalFrom = sumOfReversalFrom;
    }

    public int getNoOfNegativeValues() {
        return noOfNegativeValues;
    }

    public void setNoOfNegativeValues(int noOfNegativeValues) {
        this.noOfNegativeValues = noOfNegativeValues;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public List<String> getOnlineTransferToDate() {
        return onlineTransferToDate;
    }

    public void setOnlineTransferToDate(List<String> onlineTransferToDate) {
        this.onlineTransferToDate = onlineTransferToDate;
    }

    public List<String> getOnlineTransferToDescription() {
        return onlineTransferToDescription;
    }

    public void setOnlineTransferToDescription(List<String> onlineTransferToDescription) {
        this.onlineTransferToDescription = onlineTransferToDescription;
    }

    public List<String> getOnlineTransferToCheckNo() {
        return onlineTransferToCheckNo;
    }

    public void setOnlineTransferToCheckNo(List<String> onlineTransferToCheckNo) {
        this.onlineTransferToCheckNo = onlineTransferToCheckNo;
    }

    public List<String> getOnlineTransferToCheckNoDuplicateRemoved() {
        return onlineTransferToCheckNoDuplicateRemoved;
    }

    public void setOnlineTransferToCheckNoDuplicateRemoved(List<String> onlineTransferToCheckNoDuplicateRemoved) {
        this.onlineTransferToCheckNoDuplicateRemoved = onlineTransferToCheckNoDuplicateRemoved;
    }

    public List<Double> getOnlineTransferToAmount() {
        return onlineTransferToAmount;
    }

    public void setOnlineTransferToAmount(List<Double> onlineTransferToAmount) {
        this.onlineTransferToAmount = onlineTransferToAmount;
    }

    public double getKeywordSum() {
        return keywordSum;
    }

    public void setKeywordSum(double keywordSum) {
        this.keywordSum = keywordSum;
    }

    public double getAverageLedgerBalance() {
        return averageLedgerBalance;
    }

    public void setAverageLedgerBalance(double averageLedgerBalance) {
        this.averageLedgerBalance = averageLedgerBalance;
    }

    public List<Double> getKeywordAmount() {
        return keywordAmount;
    }

    public void setKeywordAmount(List<Double> keywordAmount) {
        this.keywordAmount = keywordAmount;
    }

    public List<String> getKeywordDate() {
        return keywordDate;
    }

    public void setKeywordDate(List<String> keywordDate) {
        this.keywordDate = keywordDate;
    }
      public List<String> getKeywordDescription() {
        return keywordDescription;
    }

    public void setKeywordDescription(List<String> keywordDescription) {
        this.keywordDescription = keywordDescription;
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
