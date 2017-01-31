package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

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
		//TODO Test that the list grows by adding at least 11 elements
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
		
		//TODO Test adding to the front, middle and back of the list
		
		//TODO Test adding a null element
		
		//TODO Test adding a duplicate element
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
			assertFalse(list.get(-1));
			fail();
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			assertFalse(list.get(list.size()));
			fail();
		} catch (IndexOutOfBoundsException e)  {
			
		}
		
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		
		//TODO Add some elements to the list
		
		//TODO Test getting an element at an index < 0
		
		//TODO Test getting an element at size
		
	}
	
	/**
	 * Tests removing elements from an empty list, from different parts of a list, and from invalid
	 * indexes
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		
		//TODO Add some elements to the list - at least 4
		
		//TODO Test removing an element at an index < 0
		
		//TODO Test removing an element at size
		
		//TODO Test removing a middle element
		
		//TODO Test removing the last element
		
		//TODO Test removing the first element
		
		//TODO Test removing the last element
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
			assertFalse(list.indexOf(null));
			fail();
		} catch (NullPointerException e) {
			
		}
		
		//TODO Test indexOf on an empty list
		
		//TODO Add some elements
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		
		//TODO Test checking the index of null
		
	}
	
	/**
	 * Tests clearing the list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		
		//TODO Clear the list
		
		//TODO Test that the list is empty
	}

	/**
	 * Tests that the list is empty to begin with
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		assertTrue(list.isEmpty());
		list.add("Hello");
		assertFalse(list.isEmpty());
		//TODO Test that the list starts empty
		
		//TODO Add at least one element
		
		//TODO Check that the list is no longer empty
	}

	/**
	 * Tests whether the list contains a certain element
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		
		//TODO Add some elements
		
		//TODO Test some true and false cases
	}
	
	/**
	 * Tests if two lists are equal or not
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for equality and non-equality
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
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for the same and different hashCodes
	}

}
 