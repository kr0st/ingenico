package lv.javaguru.courses.ingenico.lecture2.hometask.solution;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

public interface Queue<E> extends Iterable<E> {

    /**
     * TODO :
     * 1. fix methods
     * 2. create implementation class
     * 3. create implementation of Iterator<E>
     *
     * - all classes which implements interface Iterable<E> can be used in loop for
     * */

    /**
     * Inserts the specified element into this queue
     *
     * @param element the element to add
     */
    void add(E element);

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue.
     * This method throws an exception if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element();

    /**
     * TODO : fix method signature.
     * Currently method can accept only E types
     * Fix it to accept E and all subclasses of E
     *
     * Inserts all elements from queue into this queue
     *
     * @param queue the queue with elements to add
     * */
//    void addAll(Queue<? extends E> queue);

    /**
     * TODO : fix method signature.
     * Currently method can accept only E types
     * Fix it to accept E and all superclasses of E
     *
     * Inserts all elements from queue into this queue
     *
     * @param predicate the Predicate for element search
     * */
    boolean contains(Predicate<? super E> predicate);

}
