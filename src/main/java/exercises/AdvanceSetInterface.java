package exercises;

import edu.touro.mcon264.apps.collections.CollectionInterface;

/**
 * Advance Set Interface
 * @param <T>
 */
public interface AdvanceSetInterface <T> extends CollectionInterface<T> {
/*
 Starter code the exercise 6
 Desig signatures for the following methods:
    union()
    intersection()
    difference()
 */
    AdvanceSetInterface<T> union(AdvanceSetInterface<T> other);
    AdvanceSetInterface<T> intersection(AdvanceSetInterface<T> other);
    AdvanceSetInterface<T> difference(AdvanceSetInterface<T> other);

}
