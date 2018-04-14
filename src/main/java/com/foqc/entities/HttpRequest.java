package com.foqc.entities;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author foqc
 */
public class HttpRequest {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";

    /**
     * @param url of the web to analyze
     * @param timeout maximum waiting time
     * @return HTTP status code [200=OK, 404=NOT FOUND ...]
     * @throws java.lang.Exception
     */
    public static int getHttpStatus(String url, int timeout) throws Exception{
        int status = -1;
        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent(USER_AGENT)
                    .timeout(timeout).
                    ignoreHttpErrors(true)
                    .execute();

            status = response.statusCode();
        } catch (IOException ex) {
            throw ex;
        }
        return status;
    }

    /**
     * @param url of the web to analyze
     * @param timeout maximum waiting time
     * @return an HTML document from the scraped page
     * @throws java.lang.Exception
     */
    public static Document getHtmlGET(String url, int timeout) throws Exception  {
        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent(USER_AGENT)
                    .timeout(timeout)
                    .get();
        } catch (IOException ex) {
            throw ex;
        }

        return doc;
    }
}
