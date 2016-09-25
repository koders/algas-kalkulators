package lv.bi.salary.service;

import lv.bi.salary.model.Salary;
import lv.bi.salary.util.Constants;
import org.apache.log4j.Logger;

/**
 *  Salary service functions
 *
 *  @author Rihards
 */
public class SalaryService {

    private static final Logger log = Logger.getLogger(SalaryService.class.getName());

    /**
     * Fast, ugly solution for calculating neto salary
     * @param bruto bruto salary
     * @param dependents dependent count
     * @return calculated neto salary
     */
    public static Double calculateNetoValue(Double bruto, Integer dependents) {
        Double social = bruto * 10.5 / 100;
        Double IIN = (bruto - social - 75 - dependents * 165) * 23 / 100;
        IIN = Math.max(IIN, 0);
        return bruto - social - IIN;
    }

    /**
     *  Calculates neto salary and taxes
     * @param salary salary object with provided bruto salary and dependents
     */
    public static void calculateNetoValue(Salary salary) {
        log.debug("Calculating salary...");
        log.debug("Bruto: " + salary.getBruto());
        log.debug("Dependents: " + salary.getBruto());

        if(!validateSalary(salary)) {
            return;
        }

        salary.setTakerSocial(salary.getBruto() * Constants.VSAOI_TAKER);
        log.debug("Calculated taker social: " + salary.getTakerSocial());
        salary.setGiverSocial(salary.getBruto() * Constants.VSAOI_GIVER);
        log.debug("Calculated giver social: " + salary.getGiverSocial());
        salary.setTotalSocial(salary.getGiverSocial() + salary.getTakerSocial());
        log.debug("Calculated total social: " + salary.getTotalSocial());

        salary.setTotalPayout(salary.getBruto() + salary.getGiverSocial() + Constants.RISK);
        log.debug("Calculated total payout: " + salary.getTotalPayout());

        double calculatedSocial = (salary.getBruto() - salary.getTakerSocial() - Constants.TAXABLE_MINIMUM - salary.getDependents() * Constants.EASEMENT) * Constants.IIN;
        salary.setSocial(Math.max(calculatedSocial, 0));
        log.debug("Calculated social tax: " + salary.getSocial());

        salary.setTotalTax(salary.getTotalSocial() + Constants.RISK + salary.getSocial());
        log.debug("Calculated total taxes: " + salary.getTotalTax());

        salary.setNeto(salary.getBruto() - salary.getTakerSocial() - salary.getSocial());
        log.debug("Calculated neto salary: " + salary.getNeto());
    }

    /**
     * Salary validation method
     * @param salary salary object
     * @return true if salary is valid, false otherwise
     */
    public static boolean validateSalary(Salary salary) {
        boolean result = !(salary == null || salary.getBruto() <= 0 || salary.getDependents() < 0);
        log.debug("Salary valid: " + result);
        return result;
    }
}
