/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.pncbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class PncBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    private CredentialsForPncBank credentials;
    private DepositsForPncBank deposits;
    private DailyLedgerBalanceForPncBank dailyLedgerBalance;
    private DebitsForPncBank debits;
    private SuspiciousWireForPncBank suspiciousWire;
    private KeywordsForPnc keywordsInput;

    public PncBank(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForPncBank(pages);
        this.deposits = new DepositsForPncBank(lines, keywords);
        this.dailyLedgerBalance = new DailyLedgerBalanceForPncBank(lines);
        this.debits = new DebitsForPncBank(lines);
        this.keywordsInput = new KeywordsForPnc(lines, keywords);
        System.out.println("PNC In Progress");
        startExecution();
    }

    private void startExecution() {
     executeCredentialsSection();
     executeDailyLedgerBalanceSection();
     executeDebitsSection();
     executeDepositsSection();
     executeKeywordsSection();
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
         setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());

    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }

    private void executeDebitsSection() {
        setOnlineTransferToAmount(this.debits.getAmount());
        setOnlineTransferToCheckNo(this.debits.getCheckNo());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getCheckNoDuplicateRemoved());
        setOnlineTransferToDate(this.debits.getDate());
        setOnlineTransferToDescription(this.debits.getDescription());
    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
    }

    private void executeKeywordsSection() {
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }

    public String[] getPages() {
        return pages;
    }

    public void setPages(String[] pages) {
        this.pages = pages;
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

    public CredentialsForPncBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForPncBank credentials) {
        this.credentials = credentials;
    }

    public DepositsForPncBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForPncBank deposits) {
        this.deposits = deposits;
    }

    public DailyLedgerBalanceForPncBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForPncBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DebitsForPncBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForPncBank debits) {
        this.debits = debits;
    }

}
