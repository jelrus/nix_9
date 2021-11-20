package mathset.structures;

import mathset.interfaces.*;
import mathset.exceptions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathSet<E> implements CollectionBaseOperations<E>, CollectionArrayOperations<E>,
                                   CollectionSortingOperations<E>, CollectionMathOperations<E>,
                                   CollectionSetOperations<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int capacity;

    public MathSet() {
        this.size = size();
        this.capacity = Integer.MAX_VALUE;
    }

    public MathSet(int capacity, boolean expandable) {
        this();
        if (!expandable) {
            this.capacity = capacity;
        }
    }

    public MathSet(E[] elements) {
        this();
        add(elements);
    }

    @SafeVarargs
    public MathSet(E[]... elements) {
        this();
        for (E[] element : elements) {
            add(element);
        }
    }

    public MathSet(MathSet<E> elements) {
        this();
        for (int i = 0; i < elements.size; i++) {
            add(elements.get(i));
        }
    }

    @SafeVarargs
    public MathSet(MathSet<E>... elements) {
        this.size = size();
        this.capacity = Integer.MAX_VALUE;
        for (MathSet<E> element : elements) {
            for (int i = 0; i < element.size; i++) {
                add(element.get(i));
            }
        }
    }

    private void tieFirstNode(E element) {
        Node<E> firstNode = first;
        Node<E> lastNode = new Node<>(null, element, firstNode);
        first = lastNode;
        if (firstNode == null) {
            last = lastNode;
        } else {
            firstNode.prev = lastNode;
        }
        size++;
    }

    private void untieFirstNode(Node<E> firstNode) {
        Node<E> nextNode = firstNode.next;
        firstNode.element = null;
        firstNode.next = null;
        first = nextNode;
        if (nextNode == null) {
            last = null;
        } else {
            nextNode.prev = null;
        }
    }

    private void tieLastNode(E element) {
        Node<E> lastNode = last;
        Node<E> nextNode = new Node<>(lastNode, element, null);
        last = nextNode;
        if (lastNode == null) {
            first = nextNode;
        } else {
            lastNode.next = nextNode;
        }
        size++;
    }

    private void untieLastNode(Node<E> lastNode) {
        Node<E> prev = lastNode.prev;
        lastNode.element = null;
        lastNode.prev = null;
        last = prev;
        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
    }

    private void tieBefore(E element, Node<E> initNode) {
        Node<E> beforeNode = initNode.prev;
        Node<E> newNode = new Node<>(beforeNode, element, initNode);
        initNode.prev = newNode;
        if (beforeNode == null) {
            first = newNode;
        } else {
            beforeNode.next = newNode;
        }
        size++;
    }

    private void tieAfter(E element, Node<E> initNode) {
        Node<E> afterNode = initNode.next;
        Node<E> newNode = new Node<>(initNode, element, afterNode);
        initNode.next = newNode;
        if (afterNode == null) {
            first = newNode;
        } else {
            afterNode.prev = newNode;
        }
        size++;
    }

    private void untie(Node<E> node) {
        Node<E> next = node.next;
        Node<E> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.element = null;
        size--;
    }

    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    @Override
    public void add(E element) {
        if (this.size < this.capacity) {
            tieLastNode(element);
        } else {
            throw new FixedSizeException("MathSet have fixed size!");
        }
    }

    public void add(int index, E element) {
        if (index >= 0 && index < size) {
            if (this.size < this.capacity) {
                tieBefore(element, node(index));
            } else {
                throw new FixedSizeException("This MathSet have fixed size!");
            }
        } else {
            add(element);
        }
    }

    @Override
    public void add(E... elements) {
        for (E element : elements) {
            add(element);
        }
    }

    public void set(int index, E element) {
        if (index >= 0 && index < size) {
            for (int i = index; i <= index; i++) {
                for (Node<E> x = this.node(index); x != null; x = x.next) {
                    node(i).element = element;
                }
            }
        } else {
            throw new NodeIndexOutOfBoundsException("Node Index " + index + " is out of bounds!");
        }
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return node(index).element;
        } else {
            throw new NodeIndexOutOfBoundsException("Node Index " + index + " is out of bounds!");
        }
    }

    public E getLast() {
        return node(size - 1).element;
    }

    public E getFirst() {
        return node(0).element;
    }

    public int indexOf(E element) {
        for (int i = 0; i < size(); i++) {
            if (element.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public void clear(E[] elements) {
        for (Node<E> x = first; x != null; x = x.next) {
            for (E element : elements) {
                removeAllPresent(element);
            }
        }
    }

    public void removeByIndex(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i <= index; i++) {
                for (Node<E> x = this.node(i); x != null; x = x.next) {
                    untie(x);
                }
            }
        } else {
            throw new NodeIndexOutOfBoundsException("Node Index " + index + " is out of bounds!");
        }
    }

    public void removeElement(E element) {
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    untie(x);
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (element.equals(x.element)) {
                    untie(x);
                }
            }
        }
    }

    public void removeFirstPresent(E element) {
        if (contains(element)) {
            if (element == null) {
                for (Node<E> x = first; x != null; x = x.next) {
                    if (x.element == null) {
                        untie(x);
                    }
                }
            } else {
                for (Node<E> x = last; x != null; x = x.prev) {
                    if (x.element.equals(element)) {
                        untie(x);
                    }
                }
            }
        }
    }

    public void removeBetweenIndexes(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && lastIndex < size && lastIndex >= firstIndex) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                for (Node<E> x = this.node(firstIndex); x != null; x = x.next) {
                    untie(x);
                }
            }
            if (firstIndex == lastIndex) {
                removeByIndex(firstIndex);
            }
        } else if (firstIndex < 0) {
            throw new NodeIndexOutOfBoundsException("Node First Index " + firstIndex + " is out of bounds!");
        } else if (lastIndex >= this.size()) {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex + " is out of bounds!");
        } else if (lastIndex < firstIndex) {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex + " less than Node First Index " + firstIndex);
        }
    }

    public void removeLastPresent(E element) {
        if (contains(element)) {
            if (element == null) {
                for (Node<E> x = last; x != null; x = x.prev) {
                    if (x.element == null) {
                        untie(x);
                    }
                }
            } else {
                for (Node<E> x = last; x != null; x = x.prev) {
                    if (x.element.equals(element)) {
                        untie(x);
                    }
                }
            }
        }
    }

    public void removeAllPresent(E element) {
        if (contains(element)) {
            for (Node<E> x = first; x != null; x = x.next) {
                removeElement(element);
            }
            removeAllPresent(element);
        }
    }

    public int count(E element) {
        int count = 0;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    count++;
                }
            }
        } else if (contains(element)) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element.equals(element)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean contains(E element) {
        boolean isContains = false;
        if (element == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null) {
                    isContains = true;
                    break;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element.equals(element)) {
                    isContains = true;
                    break;
                }
            }
        }
        return isContains;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void sortDesc() {
        for (int i = 0; i < this.size; i++) {
            for (Node<E> x = first; x != last; x = x.next) {
                swapDescending(x);
            }
        }
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        for (int i = 0; i < size(); i++) {
            for (Node<E> x = node(firstIndex); x != node(lastIndex); x = x.next) {
                swapDescending(x);
            }
        }
    }

    @Override
    public void sortDesc(E element) {
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (element.equals(get(i))) {
                index = i;
                break;
            }
        }
        for (int i = 0; i < size - index; i++) {
            for (Node<E> x = node(index); x != last; x = x.next) {
                swapDescending(x);
            }
        }
    }

    @Override
    public void sortAsc() {
        for (int i = 0; i < this.size; i++) {
            for (Node<E> x = first; x != last; x = x.next) {
                swapAscending(x);
            }
        }
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        for (int i = 0; i < this.size(); i++) {
            for (Node<E> x = node(firstIndex); x != node(lastIndex); x = x.next) {
                swapAscending(x);
            }
        }
    }

    @Override
    public void sortAsc(E element) {
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (element.equals(get(i))) {
                index = i;
                break;
            }
        }
        for (int i = 0; i < size - index; i++) {
            for (Node<E> x = node(index); x != last; x = x.next) {
                swapAscending(x);
            }
        }
    }

    private void swapAscending(Node<E> target) {
        if (target.compareTo(target.next.element) > 0) {
            E swap = target.element;
            target.element = target.next.element;
            target.next.element = swap;
        }
    }

    private void swapDescending(Node<E> target) {
        if (target.compareTo(target.next.element) < 0) {
            E swap = target.element;
            target.element = target.next.element;
            target.next.element = swap;
        }
    }

    @Override
    public E getMax() {
        removeAllPresent(null);
        if (!isEmpty()) {
            MathSet<E> getSortedForMax = new MathSet<>(this);
            getSortedForMax.sortDesc();
            return getSortedForMax.getFirst();
        } else {
            return (E) new BigDecimal(0);
        }
    }

    @Override
    public E getMin() {
        removeAllPresent(null);
        if (!isEmpty()) {
            MathSet<E> getSortedForMin = new MathSet<>(this);
            getSortedForMin.sortAsc();
            return getSortedForMin.getFirst();
        } else {
            return (E) new BigDecimal(0);
        }
    }

    @Override
    public E getAverage() {
        removeAllPresent(null);
        if (!isEmpty()) {
            BigDecimal size = new BigDecimal(this.size);
            BigDecimal sum = new BigDecimal(getSum().toString());
            BigDecimal average = sum.divide(size, 5, RoundingMode.CEILING);
            return (E) average;
        } else {
            return (E) new BigDecimal(0);
        }
    }

    @Override
    public E getMedian() {
        removeAllPresent(null);
        if (!isEmpty()) {
            if (get(0) instanceof Number) {
                BigDecimal median;
                if (this.size % 2 == 0) {
                    BigDecimal firstMedian = new BigDecimal((get(this.size() / 2 - 1)).toString());
                    BigDecimal secondMedian = new BigDecimal((get(this.size() / 2)).toString());
                    median = firstMedian.add(secondMedian).divide(BigDecimal.valueOf(2), 5, RoundingMode.CEILING);
                } else {
                    median = new BigDecimal((get(this.size() / 2)).toString());
                }
                return (E) median;
            } else if (get(0) instanceof String || get(0) instanceof Character) {
                StringBuilder sumBuilder = new StringBuilder();
                if (this.size % 2 == 0) {
                    sumBuilder.append((get(this.size / 2 - 1).toString()));
                }
                sumBuilder.append((get(this.size / 2).toString()));
                return (E) sumBuilder.toString();
            } else if (!(get(0) instanceof Number) || !(get(0) instanceof String) || !(get(0) instanceof Character)) {
                throw new NotApplicableException("Cannot be applied to " + get(0).getClass());
            } else {
                throw new ObjectsNotComparableException(get(0).getClass() + "is not instance of Number or Comparable!");
            }
        } else {
            return (E) new BigDecimal(0);
        }
    }

    public E getSum() {
        removeAllPresent(null);
        if (!isEmpty()) {
            if (get(0) instanceof Number) {
                BigDecimal sum = new BigDecimal(0);
                for (int i = 0; i < size(); i++) {
                    BigDecimal a = new BigDecimal(get(i).toString());
                    sum = sum.add(a);
                }
                return (E) sum;
            } else if (get(0) instanceof String || get(0) instanceof Character) {
                StringBuilder sumBuilder = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sumBuilder.append(get(i));
                }
                return (E) sumBuilder.toString();
            } else if (!(get(0) instanceof Number) || !(get(0) instanceof String) || !(get(0) instanceof Character)) {
                throw new NotApplicableException("Cannot be applied to " + get(0).getClass());
            } else {
                throw new ObjectsNotComparableException(get(0).getClass() + "is not instance of Number or Comparable!");
            }
        } else {
            return (E) new BigDecimal(0);
        }
    }

    public void transformToSet() {
        removeAllPresent(null);
        for (int i = 0; i <= size(); i++) {
            for (Node<E> x = first; x != null; x = x.next) {
                int count = count(x.element);
                if (count > 1) {
                    removeFirstPresent(x.element);
                }
            }
        }
    }

    public void transformToAscSortedSet() {
        transformToSet();
        sortAsc();
    }

    public void transformToDescSortedSet() {
        transformToSet();
        sortDesc();
    }

    @Override
    public void join(MathSet<E> ms) {
        this.transformToSet();
        ms.transformToSet();
        for (int i = 0; i < ms.size(); i++) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (!this.contains(ms.get(i))) {
                    add(ms.get(i));
                }
            }
        }
    }

    @Override
    public void join(MathSet<E>... ms) {
        for (MathSet<E> m : ms) {
            join(m);
        }
    }

    @Override
    public void intersection(MathSet<E> ms) {
        transformToSet();
        ms.transformToSet();

        for (int i = 0; i < ms.size; i++) {
            if (!contains(ms.get(i))) {
                removeElement(ms.get(i));
            }
        }
        for (int j = 0; j < size; j++) {
            if (!ms.contains(get(j))) {
                set(j, null);
            }
        }
        removeAllPresent(null);
    }

    @Override
    public void intersection(MathSet<E>... ms) {
        for (MathSet<E> m : ms) {
            intersection(m);
        }
    }

    public void copy(MathSet<E> copied) {
        for (int i = 0; i < copied.size; i++) {
            this.add(copied.get(i));
        }
    }

    @Override
    public MathSet<E> cut(int firstIndex, int lastIndex) {
        MathSet<E> cut = new MathSet<>();
        if (firstIndex <= lastIndex && lastIndex <= size()) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                cut.add(get(i));
            }
            return cut;
        } else if (lastIndex >= size) {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex +
                    " greater than size " + size());
        } else {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex +
                    " less than Node First Index " + firstIndex);
        }
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size(); i++) {
            array[i] = get(i);
        }
        return array;
    }

    @Override
    public E[] toArray(int firstIndex, int lastIndex) {
        E[] array = (E[]) new Object[lastIndex - firstIndex + 1];
        int counter = 0;
        if (lastIndex < size()) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                array[counter] = get(i);
                counter++;
            }
        } else if (firstIndex < 0) {
            throw new NodeIndexOutOfBoundsException("Node First Index " + firstIndex + " is out of bounds!");
        } else if (lastIndex >= this.size()) {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex + " is out of bounds!");
        } else if (lastIndex < firstIndex) {
            throw new NodeIndexOutOfBoundsException("Node Last Index " + lastIndex + " less than Node First Index " + firstIndex);
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            sb.append(node(i)).append(", ");
        }
        if (size > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }
}