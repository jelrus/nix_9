package mathset.structures;

import mathset.exceptions.ObjectsNotComparableException;

import java.math.BigDecimal;
import java.util.Objects;

public class Node<E> implements Comparable<E> {

    protected E element;
    protected Node<E> prev;
    protected Node<E> next;

    protected Node(Node<E> prev, E element, Node<E> next) {
        this.prev = prev;
        this.element = element;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(element, node.element) && Objects.equals(prev, node.prev) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, prev, next);
    }

    public int compare(E initial, E compared) {
        if (initial instanceof Number && compared instanceof Number) {
            return new BigDecimal(initial.toString()).compareTo(new BigDecimal(compared.toString()));
        } else if (initial instanceof Comparable && compared instanceof Comparable) {
            return ((Comparable<E>) initial).compareTo(compared);
        } else {
            throw new ObjectsNotComparableException("Objects are not instance of Number or Comparable!");
        }
    }

    @Override
    public int compareTo(E element) {
        return compare(this.element, element);
    }
}