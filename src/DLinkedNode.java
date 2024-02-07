public class DLinkedNode<T> {
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    // Constructor to create a node with given data and priority
    public DLinkedNode(T dataItem, double priority) {
        this.dataItem = dataItem;
        this.priority = priority;
        this.next = null;
        this.prev = null;
    }

    // Empty constructor to create a node with null dataItem and zero priority
    public DLinkedNode() {
    	 this.dataItem = null;
         this.priority = 0;
         this.next = null;
         this.prev = null;
    }

    // Getter and setter methods
    public T getDataItem() {
        return dataItem;
    }

    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public DLinkedNode<T> getNext() {
        return next;
    }

    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    public DLinkedNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }
}
