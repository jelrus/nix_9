package mathset.interfaces;

public interface CollectionSortingOperations<E> {

    void sortDesc();

    void sortDesc(int firstIndex, int lastIndex);

    void sortDesc(E element);

    void sortAsc();

    void sortAsc(int firstIndex, int lastIndex);

    void sortAsc(E element);
}