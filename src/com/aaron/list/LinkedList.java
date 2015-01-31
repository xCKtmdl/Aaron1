package com.aaron.list;

import com.aaron.exceptions.*;
import com.aaron.exceptions.IllegalArgumentException;

public class LinkedList {
	protected Element head;
	protected Element tail;
	protected int size;

	public LinkedList() {
		size = 0;
	}

	public void purge() {
		head = null;
		tail = null;
	}

	public int getSize() {
		return size;
	}

	public Element getHead() {
		return head;
	}

	public Element getTail() {
		return tail;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public Object getFirst() throws ContainerEmptyException {
		if (head == null)
			throw new ContainerEmptyException();
		return head.datum;
	}

	public Object getLast() throws ContainerEmptyException {
		if (tail == null)
			throw new ContainerEmptyException();
		return tail.datum;
	}

	// Want to implement a bottom remove and a top remove
	// bottomRemove()
	// topRemove()

	public void prepend(Object item) {
		size = size + 1;
		Element tmp = new Element(item, head, size);
		if (head == null)
			tail = tmp;
		head = tmp;
	}

	public void append(Object item) {
		size = size + 1;
		Element tmp = new Element(item, null, size);
		if (head == null)
			head = tmp;
		else
			tail.next = tmp;
		tail = tmp;
	}

	public void assign(LinkedList list) {
		if (list != this) {
			purge();
			for (Element ptr = list.head; ptr != null; ptr = ptr.next) {
				append(ptr.datum);
			}
		}
	}

	public void extract(Object item) throws IllegalArgumentException {
		Element ptr = head;
		Element prevPtr = null;
		while (ptr != null && ptr.datum != item) {
			prevPtr = ptr;
			ptr = ptr.next;
		}
		if (ptr == null)
			throw new IllegalArgumentException("item not found");
		if (ptr == head)
			head = ptr.next;
		else
			prevPtr.next = ptr.next;
		if (ptr == tail)
			tail = prevPtr;
	}

	public boolean isCircular() {
		Element elementIterator = getHead();
		int currentId = elementIterator.getId();
		while (elementIterator != null) {
			int nextId = elementIterator.getNext().getId();
			if (nextId > currentId)
				return true;
			elementIterator = elementIterator.getNext();
			currentId = elementIterator.getId();
		}
		return false;
	}

	public final class Element {
		Object datum;
		Element next;
		int id;

		Element(Object datum, Element next) {
			this.datum = datum;
			this.next = next;
		}

		Element(Object datum, Element next, int id) {
			this.datum = datum;
			this.next = next;
			this.id = id;
		}

		public void insertAfter(Object item) {
			next = new Element(item, next);
			if (tail == this)
				tail = next;
		}

		public void insertAfter(Element element) {
			next = element;
			if (tail == this)
				tail = next;
		}

		public void insertBefore(Object item) {
			Element tmp = new Element(item, this);
			if (this == head)
				head = tmp;
			else {
				Element prevPtr = head;
				while (prevPtr != null && prevPtr.next != this)
					prevPtr = prevPtr.next;
				prevPtr.next = tmp;
			}
		}

		public Object getDatum() {
			return datum;
		}

		public Element getNext() {
			return next;
		}

		public int getId() {
			return id;

		}

	}

}
