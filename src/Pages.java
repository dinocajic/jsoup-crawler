import java.util.LinkedList;

/**
 * Created by Dino Cajic on 1/25/17.
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
 * Stores a list of pages
 */
public class Pages {

    // Stores the links to a file
    private LinkedList<String> links = new LinkedList<>();

    // Stores the visited links so that the crawler doesn't have to visit them again
    private LinkedList<String> visitedLinks = new LinkedList<>();

    /**
     * Adds the link to the current links
     * @param link The link of the page
     */
    public void insertPage(String link) {
        this.links.add(link);
    }

    /**
     * Checks to see if the link is already in the list of links
     * @param link The current link of a page
     * @return boolean
     */
    public boolean searchLinks(String link) {
        return this.links.contains(link);
    }

    /**
     * Search for visited links
     * @param link The current link of the page
     * @return True if link has been visited; false otherwise
     */
    public boolean searchVisitedLinks(String link) {
        return this.visitedLinks.contains(link);
    }

    /***
     * Adds a link to the visited linked list
     * @param link The visited URL
     */
    public void addVisitedLink(String link) {
        this.visitedLinks.add(link);
    }
}