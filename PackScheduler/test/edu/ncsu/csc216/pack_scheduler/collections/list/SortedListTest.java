package edu.ncsu.csc216.pack_scheduler.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.collections.list.SortedList;

public class SortedListTest {

	/**
	 * Tests that the list can be made larger by adding elements
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		list.add("pear");
		list.add("kiwi");
		list.add("mango");
		list.add("banana");
		list.add("peach");
		list.add("apple");
		list.add("melon");
		list.add("watermelon");
		list.add("orange");
		list.add("blueberry");
		list.add("strawberry");
		assertFalse(list.size() < 10);
		//Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		
	}

	/**
	 * Tests adding elements to the front, middle and back of the list, adding a null element, and 
	 * adding a duplicate element
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		//Test adding to the front of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		//Test adding to the back of the list
		list.add("watermelon");
		assertEquals(3, list.size());
		assertEquals("watermelon", list.get(2));
		//Test adding to the middle of the list
		list.add("papaya");
		assertEquals(4, list.size());
		assertEquals("papaya", list.get(2));
		//Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		//Test adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
		
	}
	
	/**
	 * Tests getting an element from an empty list, adding some elements, getting an element
	 * of index < 0, and getting an element at index = size of list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		assertEquals("", list.get(0));
		list.add("This");
		list.add("is");
		list.add("Booty");
		try{
			assertEquals(list.get(-1), "");
			fail();
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			assertEquals(list.get(list.size()), "");
			fail();
		} catch (IndexOutOfBoundsException e)  {
			
		}
		
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		
		//Add some elements to the list
		
		//Test getting an element at an index < 0
		
		//Test getting an element at size
		
	}
	
	/**
	 * Tests removing elements from an empty list, from different parts of a list, and from invalid
	 * indexes
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//Add some elements to the list - at least 4
		list.add("bird");
		list.add("cat");
		list.add("dog");
		list.add("hamster");
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//Test removing a middle element
		String removedElement = list.remove(2);
		assertEquals(removedElement, "dog");
		assertEquals("hamster", list.get(2));
		//Test removing the last element
		String removedElement2 = list.remove(2);
		assertEquals(removedElement2, "hamster");
		assertEquals("cat", list.get(1));
		//Test removing the first element
		String removedElement3 = list.remove(0);
		assertEquals(removedElement3, "bird");
		assertEquals("cat", list.get(0));
		//Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests getting the index of an element
	 */
	@Test
	public void testIndexOf() { 
		SortedList<String> list = new SortedList<String>();
		try {
			assertEquals(list.indexOf("cat") , -1);
			list.add("This");
			list.add("is");
			list.add("Booty");
			assertEquals(list.indexOf("This"), 0);
			assertEquals(list.indexOf("Hello"), -1);
			assertEquals(list.indexOf(null), -1);
			fail();
		} catch (NullPointerException e) {
			
		}
		
		//Test indexOf on an empty list
		
		//Add some elements
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		
		//Test checking the index of null
		
	}
	
	/**
	 * Tests clearing the list
	 */
	@Test
	public void testClear() { 
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("hello");
		list.add("hola");
		list.add("bonjour");
		//Clear the list
		list.clear();
		//Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests that the list is empty to begin with
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		//Add at least one element
		list.add("Hello");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests whether the list contains a certain element
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertEquals(list.contains("cat"), false);
		assertEquals(list.contains(""), false);
		//Add some elements
		list.add("carrot");
		list.add("parrot");
		list.add("ferret");
		list.add("merit");
		//Test some true and false cases
		assertEquals(list.contains("carrot"), true);
		assertFalse(list.contains("feret"));
		assertEquals(list.contains("ferret"), true);
	}
	
	/**
	 * Tests if two lists are equal or not
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("These");
		list1.add("two");
		list1.add("are");
		list1.add("the");
		list1.add("same");
		list2.add("These");
		list2.add("two");
		list2.add("are");
		list2.add("the");
		list2.add("same");
		list3.add("But");
		list3.add("this");
		list3.add("one");
		list3.add("is");
		list3.add("different");
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list1.equals(list3));
	}
	
	/**
	 * Tests that two lists that are the same have the same hashcode and that different lists
	 * have different hashcodes
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		 
		//Make two lists the same and one list different
		list1.add("These");
		list1.add("two");
		list1.add("are");
		list1.add("the");
		list1.add("same");
		list2.add("These");
		list2.add("two");
		list2.add("are");
		list2.add("the");
		list2.add("same");
		list3.add("But");
		list3.add("this");
		list3.add("one");
		list3.add("is");
		list3.add("different");
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
 