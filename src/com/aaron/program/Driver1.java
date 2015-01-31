package com.aaron.program;

import com.aaron.list.*;

public class Driver1 {

	public static void main(String[] args) {

		LinkedList list1 = new LinkedList();

		list1.prepend("FRFRF");

		list1.prepend("EFJOEFJO");

		list1.prepend("12FLKF");

		list1.getTail().insertAfter(list1.getHead());

		// a test for circularity
		boolean circularFlag = list1.isCircular();
		System.out.println(list1.getTail().getNext().getDatum().toString());
		System.out.println(list1.getHead().getDatum().toString());
	}

}
