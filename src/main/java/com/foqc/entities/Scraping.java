package com.foqc.entities;

import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author foqc
 */
public class Scraping {

    private static final String URL = "https://news.ycombinator.com/news?p=%s";
    private static final int TIMEOUT = 10000;
    private int MAX_ENTRIES = 0;//maximium entries to get
    List<Entry> lstEntries = null;

    public void setMAX_ENTRIES(int MAX_ENTRIES) {
        this.MAX_ENTRIES = MAX_ENTRIES;
    }

    /**
     * This method get the table (HTML) that contains the data of the entries,
     * and return the list of entries already processed from the method
     * scrapingEntries(...).
     *
     * @return list of entries from the url provided
     * @throws Exception
     */
    public List<Entry> getEntriesData() throws Exception {
        List<Entry> lstNewEntries = new ArrayList<>();
        boolean status = true;
        int i = 1, statusResponse = -1;
        while (status && MAX_ENTRIES > 0) {
            String newUrl = String.format(URL, i);
            System.out.println("Getting page ...: " + newUrl);
            statusResponse = HttpRequest.getHttpStatus(newUrl, TIMEOUT);
            if (statusResponse == 200) {
                Document document = HttpRequest.getHtmlGET(newUrl, TIMEOUT);
                Elements entries = document.select("tr.athing, tr td.subtext");//get entries table
                scrapingEntries(entries, MAX_ENTRIES, lstNewEntries);
                if (lstNewEntries.size() == MAX_ENTRIES) {
                    status = false;
                }
            } else {
                System.out.println("Required state is 200 instead: " + status);
                break;
            }
            i++;
        }
        return lstNewEntries;
    }

    /**
     * This method scrap over the elements provided to extract data required
     * (numberOrder, title, amountPoints, amountComments) from de entries and
     * save in an object of type Entry.
     *
     * @param elements of the entries of the table
     * @param maxEntries maximium entries to get
     * @param lstEntries list of entries from the url provided
     */
    private static void scrapingEntries(Elements elements, int maxEntries, List<Entry> lstEntries) {
        int j = 0;
        while (lstEntries.size() < maxEntries && j < elements.size()) {
            Entry objEntry = new Entry();

            String numberOrder = elements.get(j).getElementsByClass("rank").text().replaceAll("\\D", "");
            String title = elements.get(j).getElementsByClass("storylink").text();
            String amountPoints = elements.get(j + 1).getElementsByClass("score").text().replaceAll("\\D", "");
            Elements ent = elements.get(j + 1).select("td.subtext > a[href^=\"item\"]");
            String amountComments = ent.text().replaceAll("\\D", "");

            objEntry.setNumberOrder(Integer.parseInt(numberOrder));
            objEntry.setTitle(title);
            objEntry.setAmountPoints(Integer.parseInt(amountPoints.isEmpty() ? "0" : amountPoints));
            objEntry.setAmountComents(Integer.parseInt(amountComments.isEmpty() ? "0" : amountComments));
            lstEntries.add(objEntry);
            j += 2;
        }
    }

    /**
     *
     * @param minWordsTittle min words or max words on the title to filter
     * @param orderByComments if is true means entries with number of words more
     * than minWordsTittle entry.
     * @return a new list, according to the specified criteria without order
     * @throws Exception
     */
    private List<Entry> filterTitle(int minWordsTittle, boolean orderByComments) throws Exception {
        List<Entry> lstEntriesFilter = new ArrayList<>();
        if (lstEntries == null) {
            lstEntries = getEntriesData();
        }
        for (Entry entry : lstEntries) {
            if (entry.getTitle().split("[\\W\\s\\d]+", minWordsTittle + 1).length > minWordsTittle && orderByComments) {
                lstEntriesFilter.add(entry);
            } else {
                if (entry.getTitle().split("[\\W\\s\\d]+", minWordsTittle + 1).length <= minWordsTittle && !orderByComments) {
                    lstEntriesFilter.add(entry);
                }
            }
        }
        return lstEntriesFilter;
    }

    /**
     *
     * @param lstEntries
     * @param orderByComments if is true order by comments, else order by points
     * @return a list of entries orerdered
     */
    public List<Entry> orderEntries(List<Entry> lstEntries, boolean orderByComments) {
        if (orderByComments) {
            lstEntries.sort((en1, en2) -> en2.getAmountComents() - en1.getAmountComents());
        } else {
            lstEntries.sort((en1, en2) -> en2.getAmountPoints() - en1.getAmountPoints());
        }
        return lstEntries;
    }

    /**
     * Print entries print the entries according to the specified criteria
     *
     * @param minWordsTittle min words or max words on the title to filter
     * @param orderByComments if is true order by amount of comments of each
     * entry.
     * @throws Exception
     */
    public void printEntries(int minWordsTittle, boolean orderByComments) throws Exception {
        List<Entry> filterEntries = filterTitle(minWordsTittle, orderByComments);
        orderEntries(filterEntries, orderByComments)
                .forEach((entry) -> {
                    System.out.println(entry.getNumberOrder() + ".- "
                            + entry.getTitle() + " <<POINTS["
                            + entry.getAmountPoints() + "] COMMENTS["
                            + entry.getAmountComents() + "]>>");
                });
    }
}
