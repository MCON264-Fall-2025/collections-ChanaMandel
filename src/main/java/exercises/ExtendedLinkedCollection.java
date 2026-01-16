package exercises;

import edu.touro.mcon264.apps.collections.LinkedCollection;
import edu.touro.mcon264.support.LLNode;
import org.w3c.dom.Node;

public class ExtendedLinkedCollection<T> extends LinkedCollection<T> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        // Use the inherited find method or iterate through elements
        // Since we can't access head directly, we'll use a workaround
        Object[] tempArray = new Object[numElements];
        int index = 0;

        // We need to access elements - if there's an iterator, use it
        // Otherwise, we need a different approach
        // For now, let's assume we can make head and Node protected

        LLNode current = head;

        while (current != null) {
            sb.append(current.getInfo());
            if (current.getLink() != null) {
                sb.append(", ");
            }
            current = current.getLink();
        }

        sb.append("]");
        return sb.toString();

    }


    public int count(T target) {
        int occurrences = 0;
        LLNode current = head;

        while (current != null) {
            if ((target == null && current.getInfo() == null) ||
                    (target != null && target.equals(current.getInfo()))) {
                occurrences++;
            }
            current = current.getLink();
        }

        return occurrences;
    }

    public void removeAll(T target) {
        while (head != null &&
                ((target == null && head.getInfo() == null) ||
                        (target != null && target.equals(head.getInfo())))) {
            head = head.getLink();
            numElements--;
        }

        // Remove all occurrences in the rest of the list
        if (head != null) {
            LLNode current = head;

            while (current.getLink() != null) {
                if ((target == null && current.getLink().getInfo() == null) ||
                        (target != null && target.equals(current.getLink().getInfo()))) {
                    current.setLink(current.getLink().getLink());
                    numElements--;
                } else {
                    current = current.getLink();
                }
            }
        }
    }
}
