/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofokiahoma;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class BankOfOkiahoma extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForBankOfOkiahoma credentials;
    private DepositsForBankOfOkiahoma deposits;
    private DebitsForBankOfOkiahoma debits;
    private DailyLedgerBalanceForBankOfOkiahoma dailyLedgerBalance;
    private KeywordsForBankOfOkiahoma keywordsInput;

    public BankOfOkiahoma(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBankOfOkiahoma(pages, lines);
        this.deposits = new DepositsForBankOfOkiahoma(lines);
        this.debits = new DebitsForBankOfOkiahoma(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBankOfOkiahoma(lines);
        this.keywordsInput = new KeywordsForBankOfOkiahoma(lines, words);

        System.out.println("Bank Of Okiahoma In Progess");
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
    private void executeKeywordsSection(){
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }
    private void executeDebitsSection() {
           
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());
    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineTLRFrom().getSum());

    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
   
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

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public CredentialsForBankOfOkiahoma getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForBankOfOkiahoma credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForBankOfOkiahoma getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForBankOfOkiahoma dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsForBankOfOkiahoma getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForBankOfOkiahoma deposits) {
        this.deposits = deposits;
    }

    public DebitsForBankOfOkiahoma getDebits() {
        return debits;
    }

    public void setDebits(DebitsForBankOfOkiahoma debits) {
        this.debits = debits;
    }

    public KeywordsForBankOfOkiahoma getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForBankOfOkiahoma keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
