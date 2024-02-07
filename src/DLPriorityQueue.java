public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
	// We are creating private instance variables front, rear, and count.
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;
	 //initialising the front, rear, and count variables.
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count= 0;
	}
	// Add a data item with a given priority to the priority queue.
	@Override
	public void add(T dataItem, double priority) {
		// Create a new node to hold the data item and priority.
		DLinkedNode<T> newNode = new DLinkedNode<>(dataItem, priority);
		// If the queue is empty, make the new node the front and rear of the queue.
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
         // If the new node's priority is less than or equal to the front node's priority,
    		// make the new node the new front of the queue.
        } else if (newNode.getPriority() <= front.getPriority()) {
            newNode.setNext(front);
            front.setPrev(newNode);
            front = newNode;
        } else if (newNode.getPriority() >= rear.getPriority()) {
            rear.setNext(newNode);
            newNode.setPrev(rear);
            rear = newNode;
        }// If the new node's priority is somewhere in the middle of the queue,
		// traverse the queue until finding the first node with a lower priority.
		// Insert the new node before that node.
        else {
            DLinkedNode<T> currentNode = front;
            while (currentNode.getPriority() <= newNode.getPriority()) {
                currentNode = currentNode.getNext();
            }
            newNode.setPrev(currentNode.getPrev());
            newNode.setNext(currentNode);
            currentNode.getPrev().setNext(newNode);
            currentNode.setPrev(newNode);
        }
        count++;
}
	// Update the priority of a node in the queue with a new priority.
	@Override
	
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		// throw an exception, if the queue is empty 

	    if (isEmpty()) {
	        throw new InvalidElementException("Priority Queue is empty.");
	    }
		// Traverse the queue to find the node
	    DLinkedNode<T> current = front;
	    while (current != null && !current.getDataItem().equals(dataItem)) {
	        current = current.getNext();
	    }
	    if (current == null) {
	        throw new InvalidElementException("Data item not found in Priority Queue.");
	    }
	 // Update the node's priority to the new priority.
	    current.setPriority(newPriority);
	    if (current.getPrev() != null && current.getPrev().getPriority() > newPriority) {
	        promoteNode(current);
	    } else if (current.getNext() != null && current.getNext().getPriority() < newPriority) {
	        demoteNode(current);
	    }
	}
	// promote the node up the queue.if the node's priority is now higher than the previous node,

	private void promoteNode(DLinkedNode<T> node) {
	    node.getPrev().setNext(node.getNext());
	    if (node.getNext() != null) {
	        node.getNext().setPrev(node.getPrev());
	    } else {
	        rear = node.getPrev();
	    }
	    add(node.getDataItem(), node.getPriority());
	}
	//This is a private helper method that takes a node as input and moves it down in priority in the priority queue.
	private void demoteNode(DLinkedNode<T> node) {
	    node.getNext().setPrev(node.getPrev());
	    if (node.getPrev() != null) {
	        node.getPrev().setNext(node.getNext());
	    } else {
	        front = node.getNext();
	    }
	    add(node.getDataItem(), node.getPriority());
	}
	//This method removes and returns the element with the lowest priority in the priority queue. If the queue is empty, it throws an exception
	@Override
	public T removeMin() throws EmptyPriorityQueueException {
		 if (isEmpty()) {
	            throw new EmptyPriorityQueueException("The priority queue is empty.");
	        }
	        T removedData = front.getDataItem();
	        if (front == rear) {
	            front = null;
	            rear = null;
	        } else {
	            front = front.getNext();
	            front.setPrev(null);
	        }
	        count--;
	        return removedData;
		}
	@Override
	//This method checks if the priority queue is empty and returns a boolean value.
	public boolean isEmpty() {
        return count == 0;
    }
	//This method returns the size of the priority queue.
	@Override
	public int size() {
        return count;
    }
	//This method returns a string representation of the priority queue.
	@Override
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    DLinkedNode<T> current = front;

	    while (current != null) {
	        sb.append(current.getDataItem().toString());
	        current = current.getNext();
	    }

	    return sb.toString();
	}
	//This method returns the rear node of the priority queue.
	 public DLinkedNode<T> getRear() {
		 return rear;
	 }
}