/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.company;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tan.jam.model.company.bankofamerica.BankOfAmerica;
import tan.jam.model.company.bu.BankUnited;
import tan.jam.model.company.chase.Chase;
import tan.jam.model.company.citibank.CitiBank;
import tan.jam.model.company.citizen.CitizenBank;
import tan.jam.model.company.pncbank.PncBank;
import tan.jam.model.company.regionsbank.RegionsBank;
import tan.jam.model.company.suntrust.SunTrustBank;
import tan.jam.model.company.tdbank.TdBank;
import tan.jam.model.company.wellsfargo.WellsNew;
import tan.jam.model.company.wellsfargo.WellsOld;
import tan.jam.model.company.bbt.BBT;
import tan.jam.model.company.flagstar.FlagStar;
import tan.jam.model.company.usbank.USBank;
import tan.jam.model.company.firstnationalbank.FirstNationalBank;
import tan.jam.model.company.guilfordsavingbank.GuilfordSavingBank;
import tan.jam.model.company.newmexico.NewMexico;
import tan.jam.model.company.santander.Santander;
import tan.jam.model.company.blackhills.BlackHills;
import tan.jam.model.company.bankofthewest.BankOfTheWest;
import tan.jam.model.company.capitalonebank.CapitalOneBank;
import tan.jam.model.company.huntington.Huntington;
import tan.jam.model.company.banklandmark.BankLandmark;
import tan.jam.model.company.unionbank.UnionBank;
import tan.jam.model.company.firstbank.FirstBank;
import tan.jam.model.company.woodforest.WoodForest;
import tan.jam.model.company.peopleunited.PeopleUnited;
import tan.jam.model.company.astoriabank.AstoriaBank;
import tan.jam.model.company.metropolitan.Metropolitan;
import tan.jam.model.company.bankoftheozarks.BankOfTheOzarks;
import tan.jam.model.company.keybank.KeyBank;
import tan.jam.model.company.communitybank.CommunityBank;
import tan.jam.model.company.americaneagle.AmericanEagle;
import tan.jam.model.company.webster.Webster;
import tan.jam.model.company.bbvacompass.BBVACompass;
import tan.jam.model.company.seawaybank.SeawayBank;
import tan.jam.model.company.mtBank.MTBank;
import tan.jam.model.company.unitedbank.UnitedBank;
import tan.jam.model.company.mercentile.Mercentile;
import tan.jam.model.company.mercantilenew.MercantileNew;
import tan.jam.model.company.mechanicsbank.MechanicsBank;
import tan.jam.model.company.nbtbank.NBTBank;
import tan.jam.model.company.centralbank.CentralBank;
import tan.jam.model.company.fsnb.FSNB;
import tan.jam.model.company.academybank.AcademyBank;
import tan.jam.model.company.firstdakota.FirstDakota;
import tan.jam.model.company.valuebank.ValueBank;
import tan.jam.model.company.siliconvalleybank.SiliconValleyBank;
import tan.jam.model.company.guarantybank.GuarantyBank;
import tan.jam.model.company.paradisebank.ParadiseBank;
import tan.jam.model.company.bestbank.BestBank;
import tan.jam.model.company.popularcommunitybank.PopularCommunityBank;
import tan.jam.model.company.bmoharrisbank.BMOHarrisBank;
import tan.jam.model.company.nevadastatebank.NevadaStateBank;
import tan.jam.model.company.citynationalbank.CityNationalBank;
import tan.jam.model.company.wellsriver.WellsRiver;
import tan.jam.model.company.firstcitizensbank.FirstCitizensBank;
import tan.jam.model.company.fifththirdbank.FifthThirdBank;
import tan.jam.model.company.bankofokiahoma.BankOfOkiahoma;
import tan.jam.model.company.sunbank.SunBank;
import tan.jam.model.company.legacytexasbank.LegacyTexasBank;
import tan.jam.model.company.rabobank.RaboBank;
import tan.jam.model.company.vectrabank.VectraBank;
import tan.jam.model.company.santacruzcountrybank.SantaCruzCountryBank;
import tan.jam.model.company.nationalbankofarizona.NationalBankOfArizona;
import tan.jam.model.company.firstmerit.FirstMerit;
import tan.jam.model.company.firstbankandtrust.FirstBankAndTrust;
import tan.jam.model.company.firstbank2.FirstBank2;

public class CompanyFactory {

    public static Company createCompany(String name, String[] pages, List<String> words) {
        Company company = null;

        if (name.equalsIgnoreCase("Wells Fargo")) {
            Matcher m1=null;
            Matcher m2=null;
            Pattern p = Pattern.compile("Deposits/Credits|Deposits/Additions");
            Matcher m = p.matcher(pages[0]);
            if(pages.length>1){
            m1 = p.matcher(pages[1]);
            }
            if(pages.length>2){
            m2 = p.matcher(pages[2]);
            }
            if (m.find()) {
                return new WellsOld(pages, words);
            } else if (pages.length>1&&m1.find()) {
                return new WellsOld(pages, words);
            }  else if (pages.length>2&&m2.find()) {
                return new WellsOld(pages, words);
            } else {
                return new WellsNew(pages, words);
            }
        } else if (name.equalsIgnoreCase("Bank of America")) {
            return new BankOfAmerica(pages, words);

        } else if (name.equalsIgnoreCase("Chase")) {
            return new Chase(pages, words);
        } else if (name.equalsIgnoreCase("Citibank")) {
            return new CitiBank(pages, words);
        } else if (name.equalsIgnoreCase("SUNTRUST")) {
            return new SunTrustBank(pages, words);
        } else if (name.equalsIgnoreCase("PNC Bank")) {
            return new PncBank(pages, words);
        } else if (name.equalsIgnoreCase("BankUnited")) {
            return new BankUnited(pages, words);
        } else if (name.equalsIgnoreCase("TD Business")||name.equalsIgnoreCase("www.tdbank.com")) {
            return new TdBank(pages, words);
        } else if (name.equalsIgnoreCase("ACCOUNT\\s+NUMNER\\s+ACCOUNT\\s+TITLE\\s+CURRENT\\s+BALANCE\\s+ENCLOSURES")) {
            return new CitizenBank(pages, words);
        } else if (name.equalsIgnoreCase("Regions Bank")) {
            return new RegionsBank(pages, words);
        } else if (name.equalsIgnoreCase("BBT.com")) {
            return new BBT(pages, words);
        } else if (name.equalsIgnoreCase("SimplyBusiness Checking")) {
            return new FlagStar(pages, words);
        } else if (name.equalsIgnoreCase("usbank.com")) {
            return new USBank(pages, words);
        } else if (name.equalsIgnoreCase("First National Bank")) {
            return new FirstNationalBank(pages, words);
        } else if (name.equalsIgnoreCase("FREEDOMBUSINESS CHECKING")) {
            return new GuilfordSavingBank(pages, words);
        } else if (name.equalsIgnoreCase("WWW.NMB-T.COM")) {
            return new NewMexico(pages, words);
        } else if (name.equalsIgnoreCase("santanderbank")||name.equalsIgnoreCase("Santander Bank")) {
            return new Santander(pages, words);
        } else if (name.equalsIgnoreCase("bhfcu@bhfcu.net")) {
            return new BlackHills(pages, words);
        } else if (name.equalsIgnoreCase("bankofthewest")) {
            return new BankOfTheWest(pages, words);
        } else if (name.equalsIgnoreCase("capitalone")) {
            return new CapitalOneBank(pages, words);
        } else if (name.equalsIgnoreCase("huntington")) {
            return new Huntington(pages, words);
        } else if (name.equalsIgnoreCase("banklandmark")) {
            return new BankLandmark(pages, words);
        } else if (name.equalsIgnoreCase("unionbank.com")) {
            return new UnionBank(pages, words);
        } else if (name.equalsIgnoreCase("TYPE OF ACCOUNT - ")) {
            return new FirstBank(pages, words);
        } else if (name.equalsIgnoreCase("woodforest")) {
            return new WoodForest(pages, words);
        } else if (name.equalsIgnoreCase("People's United Bank")||name.equalsIgnoreCase("Peoples United Bank")) {
            return new PeopleUnited(pages, words);
        } else if (name.equalsIgnoreCase("Astoria Bank")) {
            return new AstoriaBank(pages, words);
        } else if (name.equalsIgnoreCase("Metropolitan")) {
            return new Metropolitan(pages, words);
        } else if (name.equalsIgnoreCase("Citizens")) {
            return new CitizenBank(pages, words);
        } else if (name.equalsIgnoreCase("*** CHECKING ***")) {
            return new BankOfTheOzarks(pages, words);
        } else if (name.equalsIgnoreCase("KeyBank")) {
            return new KeyBank(pages, words);
        } else if (name.equalsIgnoreCase("* * *  SUMMARY OF ACTIVITY DURING STATEMENT PERIOD * * *")|name.equalsIgnoreCase("Community Bank")) { 
           return  new CommunityBank(pages, words);
        }  else if (name.equalsIgnoreCase("www.ameaglecu.org")) { 
           return  new AmericanEagle(pages, words);
        }  else if (name.equalsIgnoreCase("WebsterOnline.com")) { 
           return  new Webster(pages, words);
        }  else if (name.equalsIgnoreCase("bbvacompass.com")) { 
           return  new BBVACompass(pages, words);
        }  else if (name.equalsIgnoreCase("SEAWAY BANK")) { 
           return  new SeawayBank(pages, words);
        }  else if (name.equalsIgnoreCase("M&T")) { 
           return  new MTBank(pages, words);
        }  else if (name.equalsIgnoreCase("S U M M A R Y  O F  A C C O U N T S")) { 
           return  new UnitedBank(pages, words);
        }  else if (name.equalsIgnoreCase("merc.firstbankmi.com")) { 
           return  new Mercentile(pages, words);
        }  else if (name.equalsIgnoreCase("Last Statement Previous Balance This Statement Current Balance Total Credits Total Debits")) { 
           return  new MercantileNew(pages, words);
        }  else if (name.equalsIgnoreCase("www.mechanicsbank.com")) { 
           return  new MechanicsBank(pages, words);
        }  else if (name.equalsIgnoreCase("800.628.2265")) { 
           return  new NBTBank(pages, words);
        }  else if (name.equalsIgnoreCase("A M E R I C A N  F O R K")) { 
           return  new CentralBank(pages, words);
        }  else if (name.equalsIgnoreCase("FTSNB")) { 
           return  new FSNB(pages, words);
        }  else if (name.equalsIgnoreCase("www.AcademyBankCo.com")) { 
           return  new AcademyBank(pages, words);
        }  else if (name.equalsIgnoreCase("First Dakota")) { 
           return  new FirstDakota(pages, words);
        }  else if (name.equalsIgnoreCase("www.valuebanktexas.com")) { 
           return  new ValueBank(pages, words);
        }  else if (name.equalsIgnoreCase("www.svb.com")) { 
           return  new SiliconValleyBank(pages, words);
        }  else if (name.equalsIgnoreCase("www.GuarantyBankCO.com")) { 
           return  new GuarantyBank(pages, words);
        }  else if (name.equalsIgnoreCase("Paradise")) { 
           return  new ParadiseBank(pages, words);
        }  else if (name.equalsIgnoreCase("www.bestbank.com")) { 
           return  new BestBank(pages, words);
        }  else if (name.equalsIgnoreCase("Popular Community Bank")) { 
           return  new PopularCommunityBank(pages, words);
        }  else if (name.equalsIgnoreCase("BMO HARRIS BANK")) { 
           return  new BMOHarrisBank(pages, words);
        }  else if (name.equalsIgnoreCase("471-5800")) { 
           return  new NevadaStateBank(pages, words);
        }  else if (name.equalsIgnoreCase("cnb.com")) { 
           return  new CityNationalBank(pages, words);
        }  else if (name.equalsIgnoreCase("802-757-2361")) { 
           return  new WellsRiver(pages, words);
        }  else if (name.equalsIgnoreCase("FIRST CITIZENS")|name.equalsIgnoreCase("Central Bank")) {
                return new FirstCitizensBank(pages, words);
        } else if (name.equalsIgnoreCase("877-534-2264")) {
                return new FifthThirdBank(pages, words);
        } else if (name.equalsIgnoreCase("www.bok.com")) {
                return new BankOfOkiahoma(pages, words);
        } else if (name.equalsIgnoreCase("www.sunbankaz.com")) {
                return new SunBank(pages, words);
        } else if (name.equalsIgnoreCase("LegacyTexas")) {
                return new LegacyTexasBank(pages, words);
        } else if (name.equalsIgnoreCase("RABOBANK")) {
                return new RaboBank(pages, words);
        } else if (name.equalsIgnoreCase("Vectra Bank")) {
                return new VectraBank(pages, words);
        } else if (name.equalsIgnoreCase("www.sccountybank.com")) {
                return new SantaCruzCountryBank(pages, words);
        } else if (name.equalsIgnoreCase("WWW.NBARIZONA.COM")) {
                return new NationalBankOfArizona(pages, words);
        } else if (name.equalsIgnoreCase("FirstMerit Bank")) {
                return new FirstMerit(pages, words);
        } else if (name.equalsIgnoreCase("FIRST BANK & TRUST")) {
                return new FirstBankAndTrust(pages, words);
        } else if (name.equalsIgnoreCase("www.firstbanks.com")) {
                return new FirstBank2(pages, words);
        } else{
            return null;
        }
    }
}
