package customcollections;

public class DynamicArray<E> {
    private final int INIT_SIZE = 10;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public DynamicArray() {
    }

    public void add(E item) {
        if (pointer == array.length)
            resize(array.length * 2);
        array[pointer++] = item;
    }

    public E get(int index) {
        return (E) array[index];
    }

    public void remove(int index) {
        if (pointer - index >= 0) System.arraycopy(array, index + 1, array, index, pointer - index);
        array[pointer] = null;
        pointer--;
        int CUT_RATE = 4;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length / 2);
    }

    public int size() {
        return pointer;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    public boolean contains(E item) {
        for (Object o : array) {
            if (o == item) {
                return true;
            }
        }
        return false;
    }

    public boolean containsOnlyNullObjects() {
        int counter = 0;
        for (Object o : array) {
            if (o == null) {
                counter++;
            }
        }
        return counter == array.length;
    }

    @Override
    public String toString() {
        StringBuilder dynamicObjectsToString = new StringBuilder();
        for (Object o : array) {
            if (o != null) {
                dynamicObjectsToString.append(o).append("\n");
            }
        }
        return dynamicObjectsToString.toString();
    }
}