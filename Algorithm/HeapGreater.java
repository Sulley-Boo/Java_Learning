import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆数据结构的设计和实现
 * 除了getAllElements()方法，其余的每一个方法的时间复杂度不超过O(logN)
 * Java内部提供的PriorityQueue中的remove方法时间复杂度O(N)
 *
 * @param <T>
 */
public class HeapGreater<T> {

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int best = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            best = comparator.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = 2 * index + 1;
        }
    }

    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    public boolean contains(T o) {
        return indexMap.containsKey(o);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T o) {
        heap.add(o);
        indexMap.put(o, heapSize);
        heapInsert(heapSize++);
    }

    public T pop() {
        T res = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(res);
        heap.remove(--heapSize);
        heapify(0);
        return res;
    }

    public void remove(T o) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(o);
        indexMap.remove(o);
        heap.remove(--heapSize);
        if (o != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public void resign(T o) {
        heapInsert(indexMap.get(o));
        heapify(indexMap.get(o));
    }

    public List<T> getAllElements() {
        List<T> res = new ArrayList<>();
        for (T o : heap) {
            res.add(o);
        }
        return res;
    }
}
