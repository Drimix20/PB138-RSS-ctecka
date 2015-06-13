/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb138.rss.reader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.feed.RssFeedItem;
import pb138.rss.file.RssFileReader;
import pb138.rss.file.RssFileWriter;
import pb138.rss.reader.downloader.RssFeedDownloader;
import pb138.rss.reader.downloader.RssFeedReader;
import pb138.rss.reader.downloader.RssFeedReaderTask;
import pb138.rss.templates.XSLTProcesor;

/**
 *
 * @author Marek
 */
public class XSLTDemo {
    public static void main(String[] args) throws FileNotFoundException, TransformerConfigurationException, TransformerException {
        String rssPath = "src/main/java/pb138/rss/";
        XSLTProcesor procesor = new XSLTProcesor(rssPath + "templates/sources.xsl");
        procesor.transform("skuska.xml", "export.html");
    }
}
