package mathset.interfaces;

public interface CollectionBaseOperations<E> {

    void add(E element);

    void add(E ... elements);

    E get(int index);

    void clear();

    void clear(E[] elements);
}