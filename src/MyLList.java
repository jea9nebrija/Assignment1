/*************************************************
 File: MyLList.java
 By: Jeanine Nebrija
 Date: 2/20/24
 Compile: Open directory as IntelliJ project, compile and run.
 System: Windows w/ Java
 Description: Implementation of linked list ADT.
 *************************************************/

public class MyLList<T> implements ListInterface<T> {
    private T[] list;
    private ListNode firstNode;
    private int numEntries;

    public MyLList() {
        list = (T[]) new Object[128];
    }

    /** add an existing entry of this list.
     The list size is increased by 1.
     @param anEntry  The object to be added as a new entry. */
    public void add(T anEntry) {
        ListNode newNode = new ListNode (anEntry);

        if (isEmpty ()) {
            firstNode = newNode;
        }else{
            ListNode lastNode = getNodeAt (numEntries);
            lastNode.next = newNode;
        }
        numEntries++;
    }

    public boolean add(int newPosition, T anEntry) {
        ListNode newNode = new ListNode (anEntry);

        if (isEmpty()) {
            firstNode = newNode;
        }else{
            ListNode insertionNode = getNodeAt (newPosition);
            ListNode continuingNode=getNodeAt (newPosition+1);
            insertionNode.next = newNode;
            newNode.next=continuingNode;
        }
        numEntries++;
        return true;
    }

    public final void clear () {
        firstNode = null;
        numEntries = 0;
    }

    public boolean isEmpty() {
        return (firstNode == null);
    }

    public int getLength() {
        return numEntries;
    }

    public boolean contains(T anEntry) {
        if(firstNode == null) return false;
        else if(firstNode.getData().equals(anEntry)) return true;
        else {
            do {
                firstNode = firstNode.next;
                if(anEntry.equals(firstNode.getData())) return true;
            } while (firstNode.next != null);
        }
        return false;
    }

    private ListNode getNodeAt(int givenPosition) {
        ListNode currentNode = firstNode;

        for (int counter = 1 ; counter < givenPosition ; counter++) {
            currentNode = currentNode.next;
        }

        return currentNode;
    }

    public Comparable getEntry(int i) {
        return (Comparable) getNodeAt(i).data;
    }

    public T replace(int position, T anEntry) {
        boolean success = false;

        if (!((position >= 1) && (position <= numEntries))) {
            return null;
        } else {

            ListNode theNode = this.getNodeAt(position - 1);
            theNode.setData(anEntry);
            success = true;
        }

        return anEntry;
    }

    public Comparable remove(int position) {
        Comparable result = null;

        if ((position >= 1) && (position <= numEntries)) {
            if (position == 1) {
                result = (Comparable) firstNode.data;
                firstNode = firstNode.next;
            } else { // case 2: not first node
                ListNode nodeBefore = getNodeAt(position - 1);
                ListNode nodeToRemove = nodeBefore.next;
                ListNode nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                result = (Comparable) nodeToRemove.getData();
            } // end if

            numEntries--;
        }else{
            System.out.println(position + ": is out of range of the list with size: " + numEntries);
        }

        return result;
    }

    public T pop() {
        return (T) remove(this.getLength());
    }

    public T peek() {
        return (T) this.getEntry(this.getLength());
    }

    /**
     *
     * @author kmehta
     */
    public class ListNode {

        public T data; // entry in queue
        public ListNode next; // link to next node

        public ListNode(T anEntry) {
            this(anEntry, null);
        }

        public ListNode(T anEntry, ListNode n){
            this.data = anEntry;
            this.next = n;
        }

        public T getData() {
            return data;
        }

        public ListNode getNextNode() {
            return this.next;
        }

        public void setNextNode(ListNode anEntry) {
            this.next = anEntry;
        }

        public void setData(T anEntry) {
            this.data = anEntry;
        }
    }
}