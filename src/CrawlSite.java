import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Created by Dino Cajic on 1/26/17.
 *
 * Copyright 2017 Dino Cajic
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons
 * to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Crawls the website and saves the links to the specified outputTextFile
 */
public class CrawlSite {

    // Stores the website links and the visited pages
    private Pages pages = new Pages();

    // The website address to crawl
    private String websiteAddress ="https://firstchoicewheelsandtires.com";

    // The output text file name. Where your results will be saved to.
    private String outputTextFile = "output.txt";

    // If you want to stop the execution after so many cycles
    private int stopAfter = 0;

    // Counts the number of traversals: for stopAfter
    private int count = 0;

    /**
     * Starts the link retrieval and storage. Creates the text file and clears the content: done if
     * text file already exists so that new links can be stored. Text file will be saved in src folder.
     */
    public CrawlSite() {
        try {
            File outputFile = new File(this.outputTextFile);
            outputFile.createNewFile();

            PrintWriter pw = new PrintWriter(this.outputTextFile);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.storeLinksFromPage(this.websiteAddress);
    }

    /**
     * Crawls the website and stores the links into a text file. Once it retrieves the links from the current
     * page, it will cycle through each of the links and open each one. It then recursively calls this function
     * and cycles through all of the links within that link. The output is stored in the specified text file.
     *
     * @param url The website url
     */
    private void storeLinksFromPage(String url) {
        this.count++;

        // Exits the program once the set number of traversals have been executed
        if (this.stopAfter != 0) {
            this.count++;

            if (this.count == this.stopAfter) {
                System.exit(-1);
            }
        }

        // Writes the URL to the text file specified in the absolutePathToFile property
        try(FileWriter fw     = new FileWriter(this.outputTextFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out   = new PrintWriter(bw))
        {
            out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Adds the link to the already visited pages
        this.pages.addVisitedLink(url);

        try {
            // Connects to the url and grabs all of the a tags on the page. Stores them in the links variable
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a");

            // Cycles through each of the links
            for (Element link: links) {

                // Removes all of the problematic content from the link: space, #, %20
                String cleanLink =link.attr("abs:href").replace(" ", "").replace("#", "").replace("%20", "");

                // Check to see if link exists in pages
                // If it doesn't, insert it
                if (!this.pages.searchLinks(cleanLink) &&
                        cleanLink.contains(this.websiteAddress) &&
                        !cleanLink.contains(".jpg") &&
                        !cleanLink.contains(".png") &&
                        !cleanLink.contains(".pdf") &&
                        !cleanLink.contains(".gif") &&
                        !cleanLink.contains(".js") &&
                        !cleanLink.contains(".css") &&
                        !cleanLink.contains("mailto") &&
                        !cleanLink.contains("page=inquire")) {
                            this.pages.insertPage(cleanLink);

                            // Check to see if link has been visited.
                            // If not, recursively call the function and add it to the visited links
                            if (!this.pages.searchVisitedLinks(cleanLink)) {
                                storeLinksFromPage(cleanLink);
                            }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}