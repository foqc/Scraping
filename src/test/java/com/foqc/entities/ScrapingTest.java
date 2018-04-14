/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foqc.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author foqc
 */
public class ScrapingTest {

    /**
     * Test of getEntriesData method, of class Scraping.
     * Test if the number of entries is the same as the requested 
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetEntriesData() throws Exception {
        System.out.println("getEntriesData");
        int maxEntries = 30;
        Scraping s = new Scraping();
        s.setMAX_ENTRIES(maxEntries);
        List<Entry> result = s.getEntriesData();
        assertEquals(maxEntries, result.size());
    }

    /**
     * test if a list of entries is sorted descending by amount comments
     *
     * @throws Exception
     */
    @Test
    public void testSortingByComment() throws Exception {
        System.out.println("testSortingByComment");
        List<Entry> lstEntries = new ArrayList<>();
        lstEntries.add(new Entry(2, "my title C", 15, 5));
        lstEntries.add(new Entry(3, "my title N", 5, 15));
        lstEntries.add(new Entry(5, "my title U", 55, 0));
        lstEntries.add(new Entry(7, "my title P", 0, 3));

        Scraping s = new Scraping();
        List<Entry> sortedEntries = s.orderEntries(lstEntries, true);

        assertEquals(55, sortedEntries.get(0).getAmountComents());
        assertEquals(15, sortedEntries.get(1).getAmountComents());
        assertEquals(5, sortedEntries.get(2).getAmountComents());
        assertEquals(0, sortedEntries.get(3).getAmountComents());
    }

    /**
     * test if a list of entries is sorted descending by amount points
     *
     * @throws Exception
     */
    @Test
    public void testSortingByPoints() throws Exception {
        System.out.println("testSortingByComment");
        List<Entry> lstEntries = new ArrayList<>();
        lstEntries.add(new Entry(2, "my title C", 15, 5));
        lstEntries.add(new Entry(3, "my title N", 5, 15));
        lstEntries.add(new Entry(5, "my title U", 55, 0));
        lstEntries.add(new Entry(7, "my title P", 0, 3));

        Scraping s = new Scraping();
        List<Entry> sortedEntries = s.orderEntries(lstEntries, false);

        assertEquals(15, sortedEntries.get(0).getAmountPoints());
        assertEquals(5, sortedEntries.get(1).getAmountPoints());
        assertEquals(3, sortedEntries.get(2).getAmountPoints());
        assertEquals(0, sortedEntries.get(3).getAmountPoints());
    }

}
