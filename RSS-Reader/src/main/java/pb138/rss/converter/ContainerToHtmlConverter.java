/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pb138.rss.converter;

import javax.xml.transform.TransformerException;
import pb138.rss.feed.RssFeedContainer;
import pb138.rss.file.RssFileWriter;
import pb138.rss.templates.XSLTProcesor;

/**
 *
 * @author Marek
 */
public class ContainerToHtmlConverter {
    
    private String filePath;
    private RssFileWriter writer;
    private XSLTProcesor procesor;
    
    public ContainerToHtmlConverter(RssFileWriter writer, XSLTProcesor procesor,String filePath) {
        this.filePath = filePath;
        this.writer = writer;
        this.procesor = procesor;
    }
    
    public String getString() throws TransformerException {
        writer.run();
        return procesor.transform(filePath);  
    }
}
