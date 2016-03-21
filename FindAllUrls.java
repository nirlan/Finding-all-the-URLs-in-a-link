
/**
 * This code finds all the URLs in a choosen link and
 * processes the data.
 * @author Nirlan Neckir Zamprogno de Souza 
 * @version 20/03/2016
 */

import edu.duke.*;
import java.io.*;

public class FindAllUrls {
    public StorageResource findURLs(String url) {
        StorageResource sr = new StorageResource();
        URLResource ur = new URLResource(url);
        for (String wordx : ur.words()) {
            String word = wordx.toLowerCase();
            if (word.indexOf("href=\"http") != -1) {
               int link = word.indexOf("href=\"http");
               int start = word.indexOf("\"", link);
               int stop = word.indexOf("\"", start +1);
               if (start +1 > 0 && stop > 0) {
               String found = wordx.substring(start +1, stop);            
               sr.add(found);
               } 
            }
        }        
        return sr;
    }
    public void testURLWithStorage() {
        String url = "http://www.dukelearntoprogram.com/course2/data/newyorktimes.html";
        StorageResource store = new StorageResource();
        StorageResource secure = new StorageResource();
        StorageResource link1 = new StorageResource();
        StorageResource link2 = new StorageResource();
        StorageResource link3 = new StorageResource();       
        store = findURLs(url);
        System.out.println("URLs found: ");
        for (String urlstr : store.data()) {
        System.out.println(urlstr);
        if (urlstr.startsWith("https")) {
            secure.add(urlstr);            
        }
        if (urlstr.indexOf(".com") != -1) {
            link1.add(urlstr);
        }
        if (urlstr.endsWith(".com") || urlstr.endsWith(".com/")) {
            link2.add(urlstr);   
        }        
        //total number of "." in all the links
        int start = 0;
        int index = 0;
        int count = 0;
        while (true) {
            index = urlstr.indexOf(".", start);
            if (index == -1) {
                break;
            }
            start = index +1;
            link3.add(urlstr.substring(index));
        }
        }
        System.out.println("Number of URLs found: " + store.size());
        System.out.println("Number of secure links found: " + secure.size());
        System.out.println("Number of links with \".com\": " + link1.size());
        System.out.println("Number of links that end with \".com\" or \".com/\": " + link2.size());
        System.out.println("Total number of \".\" in all links: " + link3.size());
    }
}
    
