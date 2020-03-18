import java.util.LinkedList;

/**
 * Created by dino on 1/25/17.
 *
 * Stores a list of pages that contain broken images
 */
public class BrokenImages {

    LinkedList<String> pagesWithBrokenImages = new LinkedList<>();

    /**
     * Inserts a link containing broken image
     * @param link The link with the broken images
     */
    public void addPage(String link) {
        this.pagesWithBrokenImages.add(link);
    }

    /**
     * Returns the LinkedList of broken image links
     * @return LinkedList
     */
    public LinkedList<String> getPagesWithBrokenImages() {
        return this.pagesWithBrokenImages;
    }
}
