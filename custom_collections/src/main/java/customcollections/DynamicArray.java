package customcollections;

public class DynamicArray<E> {
    private final int INIT_SIZE = 10;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    public DynamicArray(){}

    public void add(E item) {
        if(pointer == array.length-1)
            resize(array.length*2); // увеличу в 2 раза, если достигли границ
        array[pointer++] = item;
    }

    public E get(int index) {
        return (E) array[index];
    }

    public void remove(int index) {
        for (int i = index; i<pointer; i++)
            array[i] = array[i+1];
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length/2);
    }

    public int size() {
        return pointer;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder dynamicObjectsToString = new StringBuilder();
        for (int i = 0; i<array.length; i++){
            if (array[i] != null){
                dynamicObjectsToString.append(array[i]).append("\n");
            }
        }
        return dynamicObjectsToString.toString();
    }
}
