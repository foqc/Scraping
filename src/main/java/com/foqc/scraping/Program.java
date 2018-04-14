package com.foqc.scraping;

import com.foqc.entities.Scraping;

/**
 *
 * @author foqc
 */
public class Program {

    public static void main(String args[]) throws Exception {
        int minWordsTittle = 5, maxEntries = 30;
        Scraping s = new Scraping();
        s.setMAX_ENTRIES(maxEntries);
        System.out.println("    ** Scenario 1 Order by comments **");
        s.printEntries(minWordsTittle, true);
        System.out.println("    ** Scenario 2 Order by points **");
        s.printEntries(minWordsTittle, false);
    }
}
