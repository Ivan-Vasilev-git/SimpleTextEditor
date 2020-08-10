package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
  LLNode<E> head;
  LLNode<E> tail;
  int size;

  /**
   * Create a new empty LinkedList
   */
  public MyLinkedList() {
    size = 0;
    head = new LLNode<>(null);
    tail = new LLNode<>(null);
    head.next = tail;
    tail.prev = head;
  }

  /**
   * Appends an element to the end of the list
   *
   * @param element The element to add
   */
  public boolean add(E element) {
    if (element == null) {
      throw new NullPointerException("Last object cannot store null.");
    }
    LLNode<E> newNode = new LLNode<>(element);
    tail.prev.next = newNode;
    newNode.next = tail;
    newNode.prev = tail.prev;
    tail.prev = newNode;
    newNode.index = size;
    size++;
    return true;
  }

  /**
   * Get the element at position index
   *
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E get(int index) {
    LLNode<E> nextNode = head.next;
    if (index >= 0 && index < size) {
      while (nextNode.next != null) {
        if (nextNode.getIndex() == index) {
          return nextNode.data;
        }
        nextNode = nextNode.next;
      }
    }
    throw new IndexOutOfBoundsException();
  }


  /**
   * Add an element to the list at the specified index
   *
   * @param index   The index where the element should be added
   * @param element The element to add
   */
  public void add(int index, E element) {
    if (element == null) {
      throw new NullPointerException("Object cannot store null.");
    }
    if (index == this.size) {
      add(element);
      return;
    }
    LLNode<E> newNode = new LLNode<>(element);
    LLNode<E> nodeAtInd = getNode(index);
    newNode.prev = nodeAtInd.prev;
    nodeAtInd.prev.next = newNode;
    nodeAtInd.prev = newNode;
    newNode.next = nodeAtInd;
    newNode.setIndex(index);
    size++;
    while (nodeAtInd.next != null) {
      nodeAtInd.index++;
      nodeAtInd = nodeAtInd.next;
    }
  }


  /**
   * Return the size of the list
   */
  public int size() {
    return size;
  }

  /**
   * Remove a node at the specified index and return its data element.
   *
   * @param index The index of the element to remove
   * @return The data element removed
   * @throws IndexOutOfBoundsException If index is outside the bounds of the list
   */
  public E remove(int index) {
    LLNode<E> removedNode = getNode(index);
    removedNode.prev.next = removedNode.next;
    removedNode.next.prev = removedNode.prev;
    LLNode<E> nextNode = removedNode.next;
    while (nextNode != null) {
      nextNode.index--;
      nextNode = nextNode.next;
    }
    size--;
    return removedNode.data;
  }

  /**
   * Set an index position in the list to a new element
   *
   * @param index   The index of the element to change
   * @param element The new element
   * @return The element that was replaced
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public E set(int index, E element) {
    if (element == null) {
      throw new NullPointerException("Object cannot store null.");
    }
    if (index == size) {
      throw new IndexOutOfBoundsException();
    }
    E prevElement = getNode(index).data;
    getNode(index).data = element;
    return prevElement;
  }

  public LLNode<E> getNode(int index) {
    LLNode<E> nextNode = head.next;
    if (index >= 0 && index < size) {
      while (nextNode.next != null) {
        if (nextNode.getIndex() == index) {
          return nextNode;
        }
        nextNode = nextNode.next;
      }
    }
    throw new IndexOutOfBoundsException();
  }
}

class LLNode<E> {
  LLNode<E> prev;
  LLNode<E> next;
  E data;
  int index;

  // Add any other methods you think are useful here
  // E.g. you might want to add another constructor

  public LLNode(E e) {
    this.data = e;
    this.prev = null;
    this.next = null;
  }

  int getIndex() {
    return index;
  }

  void setIndex(int index) {
    this.index = index;
  }

  @Override
  public String toString() {
    return data.toString();
  }
}
