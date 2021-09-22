import java.util.Iterator;

public class MySimpleArrayList<E> implements MyList<E>{

    private E[] values;

    public MySimpleArrayList() {
        values = (E[]) new Object[0];
    }

    @Override
    public boolean add(E e) {
        try {
            E[] tmp = values;
            values = (E[]) new Object[tmp.length + 1];
            System.arraycopy(tmp, 0, values, 0, tmp.length);
            values[values.length - 1] = e;
            return true;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int index) {
        try {
            E[] tmp = values;
            values = (E[]) new Object[tmp.length + 1];
            System.arraycopy(tmp, 0, values, 0, index);
            int amountOfElementsAfterIndex = tmp.length - index - 1;
            System.arraycopy(
                    tmp, index + 1,
                    values, index,
                    amountOfElementsAfterIndex);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new MySimpleArrayIterator<>(values);
    }
}
