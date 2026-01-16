package exercises;

import edu.touro.mcon264.apps.collections.ArrayCollection;
import edu.touro.mcon264.apps.collections.BagInterface;

import java.util.Random;

public class BasicBag<T> extends ArrayCollection<T> implements BagInterface<T> {
    private Random rand;

    /**
     * Constructs an empty bag with default capacity.
     */
    public BasicBag() {
        super();
        rand = new Random();
    }

    /**
     * Removes and returns a randomly selected element from this bag.
     * The selection is uniformly random across all elements currently in the bag.
     * Each element has an equal probability of being selected and removed.
     *
     * @return a randomly selected element that has been removed from the bag,
     *         or null if the bag is empty
     */
    @Override
    public T grab() {
        if (numElements == 0) {
            return null;
        }

        // Select a random index
        int randomIndex = rand.nextInt(numElements);

        // Get the element at that index
        T grabbedElement = elements[randomIndex];

        // Remove it by replacing it with the last element (efficient removal)
        elements[randomIndex] = elements[numElements - 1];

        // Clear the last position and decrement count
        elements[numElements - 1] = null;
        numElements--;

        return grabbedElement;
    }

    /**
     * Counts the number of occurrences of the specified target element in this bag.
     * Uses the equals method to determine equality between elements.
     * Handles null elements correctly by using reference equality for nulls.
     *
     * @param target the element whose occurrences are to be counted
     * @return the number of times target appears in the bag (0 if not present)
     */
    @Override
    public int count(T target) {
        int occurrences = 0;

        for (int i = 0; i < numElements; i++) {
            if ((target == null && elements[i] == null) ||
                    (target != null && target.equals(elements[i]))) {
                occurrences++;
            }
        }

        return occurrences;
    }

    /**
     * Removes all occurrences of the specified target element from this bag.
     * Uses the equals method to determine equality between elements.
     * Compacts the array by shifting remaining elements to fill gaps.
     * Updates the element count to reflect the removals.
     *
     * @param target the element whose occurrences are to be removed
     * @return the number of elements actually removed from the bag
     */
    @Override
    public int removeAll(T target) {
        int removedCount = 0;
        int writeIndex = 0;

        // Two-pointer approach: read through array, write only non-matching elements
        for (int readIndex = 0; readIndex < numElements; readIndex++) {
            if ((target == null && elements[readIndex] == null) ||
                    (target != null && target.equals(elements[readIndex]))) {
                // Found a match - skip it (increment removed count)
                removedCount++;
            } else {
                // Not a match - keep it by copying to write position
                elements[writeIndex] = elements[readIndex];
                writeIndex++;
            }
        }

        // Clear remaining references to prevent memory leaks
        for (int i = writeIndex; i < numElements; i++) {
            elements[i] = null;
        }

        // Update element count
        numElements -= removedCount;
        return removedCount;
    }

    /**
     * Removes all elements from this bag, leaving it empty.
     * Clears all element references to allow garbage collection
     * and resets the element count to zero.
     */
    @Override
    public void clear() {
        // Clear all references to allow garbage collection
        for (int i = 0; i < numElements; i++) {
            elements[i] = null;
        }
        // Reset element count
        numElements = 0;
    }
}

/*
 * SOLID PRINCIPLES APPLIED:
 *
 * 1. Single Responsibility Principle (SRP):
 *    - BasicBag has one responsibility: implementing bag-specific operations
 *    - ArrayCollection handles the responsibility of managing the underlying array
 *    - Each class focuses on a single aspect of functionality
 *
 * 2. Open/Closed Principle (OCP):
 *    - BasicBag extends ArrayCollection, adding new behavior without modifying existing code
 *    - The class is open for extension (can add more bag operations) but closed for
 *      modification (doesn't change ArrayCollection's implementation)
 *
 * 3. Liskov Substitution Principle (LSP):
 *    - BasicBag can be used anywhere ArrayCollection or BagInterface is expected
 *    - It properly implements all contracts from both parent class and interface
 *    - No behavioral surprises when substituting BasicBag for its supertypes
 *
 * 4. Interface Segregation Principle (ISP):
 *    - BagInterface contains only bag-specific operations (grab, count, removeAll, clear)
 *    - Clients depending on BagInterface aren't forced to know about unrelated operations
 *    - The interface is focused and cohesive
 *
 * 5. Dependency Inversion Principle (DIP):
 *    - Code using BasicBag can depend on the BagInterface abstraction
 *    - High-level code doesn't need to depend on the concrete BasicBag implementation
 *    - This allows for flexibility, testing with mocks, and easier substitution
 */



