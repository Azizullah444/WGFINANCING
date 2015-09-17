/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.jsf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tan.jam.company.Company;
import tan.jam.company.CompanyFactory;
import tan.jam.company.model.entity.KeywordsDynamic;
import tan.jam.company.model.jsf.KeywordsDynamicController;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class OrignalFileUploadBean implements Serializable {

    private UploadedFile file;
    private List<UploadedFile> uploadedFiles;
    private List<String[]> orignalFiles;
    @ManagedProperty(value = "#{keywordsDynamicController}")
    private KeywordsDynamicController keywordsDynamicController;
    private List<String> words;
    private List<Company> companies;

    public OrignalFileUploadBean() {
        uploadedFiles = new ArrayList<>();
        orignalFiles = new ArrayList<>();
        words = new ArrayList<>();
        companies = new ArrayList<>();

    }

    private void populateKeywords() {
        for (KeywordsDynamic kw : keywordsDynamicController.getItems()) {
            words.add(kw.getTitle());
        }
    }

    public void endSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFiles.add(event.getFile());
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void startProcess(ActionEvent e) {
        parseFiles();
        findTarget();

        System.out.println("size " + uploadedFiles.size());
        System.out.println("size " + orignalFiles.size());
        System.out.println("size " + companies.size());

    }

    private void findTarget() {

        populateKeywords();
        try {
            for (String[] pages : orignalFiles) {
                String result = FileUtils.getTargetByMatch(pages[0], "Wells Fargo|Chase|Bank of America|Citibank|SUNTRUST|PNC Bank|BankUnited|TD Business|www.tdbank.com|ACCOUNT\\s+NUMNER\\s+ACCOUNT\\s+TITLE\\s+CURRENT\\s+BALANCE\\s+ENCLOSURES|Regions Bank|XXXXX.\\d{4}\\s+SMALL\\s+BUSINESS|BBT.com|SimplyBusiness Checking|usbank.com|First National Bank|FREEDOMBUSINESS CHECKING|WWW.NMB-T.COM|santanderbank|Santander Bank|bhfcu@bhfcu.net|bankofthewest|capitalone|huntington|banklandmark|unionbank.com|TYPE OF ACCOUNT - |woodforest|People's United Bank|Peoples United Bank|Astoria Bank|Metropolitan|Citizens|\\*+ CHECKING \\*+|KeyBank|Community Bank|\\*\\s+\\*\\s+\\*\\s+SUMMARY\\s+OF\\s+ACTIVITY\\s+DURING\\s+STATEMENT\\s+PERIOD\\s+\\*\\s+\\*\\s+\\*|www.ameaglecu.org|WebsterOnline.com|bbvacompass.com|SEAWAY BANK|M&T|S U M M A R Y  O F  A C C O U N T S|merc.firstbankmi.com|Last Statement Previous Balance This Statement Current Balance Total Credits Total Debits|www.mechanicsbank.com|800.628.2265|A M E R I C A N  F O R K|FTSNB|www.AcademyBankCo.com|First Dakota|www.valuebanktexas.com|www.svb.com|www.GuarantyBankCO.com|Paradise|www.bestbank.com|Popular Community Bank|BMO HARRIS BANK|471-5800|cnb.com|802-757-2361|Central Bank|FIRST CITIZENS|877-534-2264|www.bok.com|www.sunbankaz.com|LegacyTexas|RABOBANK|Vectra Bank|www.sccountybank.com|WWW.NBARIZONA.COM|FirstMerit Bank|FIRST BANK & TRUST|www.firstbanks.com");

                if (!result.equals("NoMatchFound")) {

                    Company c = CompanyFactory.createCompany(result, pages, words);
                    if (c != null) {
                        companies.add(c);

                    } else {
                        System.out.println("No Suitable match found for company");
                    }
                }

            }//

        } catch (Exception ed) {
            System.out.println(ed);
        } finally {

        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    private void parseFiles() {
        for (UploadedFile f : uploadedFiles) {
            try {
                PdfReader reader = new PdfReader(f.getInputstream());
                String[] pages = new String[reader.getNumberOfPages()];
                for (int a = 0; a < pages.length; a++) {
                    pages[a] = PdfTextExtractor.getTextFromPage(reader, a + 1);
                }
                if (pages[0].length() > 1) {
                    orignalFiles.add(pages);
                } else {
                    System.out.println("File is SCANNED");

                }
            } catch (IOException ex) {

                FacesMessage message = new FacesMessage("Error Parsing File ... ");
                FacesContext.getCurrentInstance().addMessage(null, message);
                Logger.getLogger(OrignalFileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public KeywordsDynamicController getKeywordsDynamicController() {
        return keywordsDynamicController;
    }

    public void setKeywordsDynamicController(KeywordsDynamicController keywordsDynamicController) {
        this.keywordsDynamicController = keywordsDynamicController;
    }

    public List<String[]> getOrignalFiles() {
        return orignalFiles;
    }

    public void setOrignalFiles(List<String[]> orignalFiles) {
        this.orignalFiles = orignalFiles;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

}
