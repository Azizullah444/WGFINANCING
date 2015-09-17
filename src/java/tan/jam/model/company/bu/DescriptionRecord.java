/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bu;

import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DescriptionRecord {

    private String one;
    private String two;
    private String three;

    public DescriptionRecord(String one, String two, String three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public String getFinalDescriptionString() {
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{1,4}";
        String finalResult = one;
        String resultOne = FileUtils.getResultFromPattern(pattern, two);
        if (resultOne.startsWith("NoMatch")) {
            finalResult += " " + two;
            String s = FileUtils.getResultFromPattern(pattern, three);
            if (s.startsWith("NoMatch")) {
                finalResult += " " + three;
            }
        }
        return finalResult;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

}
