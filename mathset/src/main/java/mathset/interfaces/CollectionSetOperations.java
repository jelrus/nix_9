package mathset.interfaces;

import mathset.structures.MathSet;

public interface CollectionSetOperations<E> {

    void join(MathSet<E> ms);

    void join(MathSet<E> ... ms);

    void intersection(MathSet<E> ms);

    void intersection(MathSet<E> ... ms);

    MathSet<E> cut(int firstIndex, int lastIndex);
}