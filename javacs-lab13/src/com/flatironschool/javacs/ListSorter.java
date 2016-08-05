/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		if (list.size() > 1){
	        int mid = list.size() / 2;
	        List<T> left = list.subList(0, mid);
	        List<T> right = list.subList(mid, list.size());
	        left = mergeSort(left, comparator);
	        right = mergeSort(right, comparator);
	        list = merge(left, right, comparator);
	    } 
	    return list;
	}

	private List<T> merge(List<T> listOne, List<T> listTwo, Comparator<T> comparator){
		int indexOne = 0;
		int indexTwo = 0;
		List<T> merged = new ArrayList<T>();
		while (indexOne < listOne.size() && indexTwo < listTwo.size()){
			T one = listOne.get(indexOne);
			T two = listTwo.get(indexTwo);
			if (comparator.compare(one, two) < 0){
				merged.add(one);
				indexOne++;
			} else {
				merged.add(two);
				indexTwo++;
			}
		}
		while (indexOne < listOne.size()){
			merged.add(listOne.get(indexOne));
			indexOne++;
		}
		while (indexTwo < listTwo.size()){
			merged.add(listTwo.get(indexTwo));
			indexTwo++;
		}
		return merged;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
		int size = list.size();
		PriorityQueue<T> pq = new PriorityQueue<T>(size, comparator);
        for (T elem: list){
        	pq.offer(elem);
        	System.out.println(elem);
        }
        list.clear();
        for (int i = 0; i < size; i++){
        	list.add(pq.poll());
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> pq = new PriorityQueue<T>(k, comparator);
        for (T elem: list){
        	if (pq.size() < k){
        		pq.offer(elem);
        	} else {
        		T top = pq.peek();
        		if (top != null){
        			if (comparator.compare(elem, top) > 0){
        				pq.poll();
        				pq.offer(elem);
        			}
        		}
        	}
       	}
        list.clear();
        for (int i = 0; i < k; i++){
        	list.add(pq.poll());
        }
        return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
