import java.util.*;

public class LinkedListWithIterator<T> extends MyLList<T> implements ListWithIteratorInterface<T> {
    public LinkedListWithIterator()
    {
        super();
    }

    public Iterator<T> getIterator()
    {
        return new IteratorForArrayList();
    }

    public Iterator<T> iterator()
    {
        return getIterator();
    }

    private class IteratorForArrayList implements Iterator<T>{
        private int nextPosition;  /* Position of next entry in the iteration */
        private boolean nextCalled;

        private IteratorForArrayList(){
            nextPosition = 1;
            nextCalled = false;
        }

        public boolean hasNext(){
            return nextPosition <= getLength();
        }

        public T next(){
            if (hasNext()){
                nextCalled = true;
                T nextEntry = (T) getEntry(nextPosition);
                nextPosition++;
                return nextEntry;
            }else{
                throw new NoSuchElementException("Illegal call to next(); Iterator is after end of list.");
            }
        }

        public void remove(){
            if (nextCalled){
                nextPosition--;
                LinkedListWithIterator.this.remove(nextPosition);
                nextCalled = false;
            }else{
                throw new IllegalStateException("Illegal call to remove(); next() was not called.");
            }
        }
    }
}