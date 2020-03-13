package myStudy.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BucketSort {
	//Fiset Ver. Slower than mine
	  public static void bucketSort(int[] ar, final int minVal, final int maxVal) {

		    if (ar == null || ar.length == 0 || minVal == maxVal) return;

		    // N is number elements and M is the range of values
		    final int N = ar.length, M = maxVal - minVal, NUM_BUCKETS = M / N + 1;
		    List<List<Integer>> buckets = new ArrayList<>(NUM_BUCKETS);
		    for (int i = 0; i < NUM_BUCKETS; i++) buckets.add(new ArrayList<>());

		    // Place each element in a bucket
		    for (int i = 0; i < N; i++) {
		      int bi = (ar[i] - minVal) / M;
		      List<Integer> bucket = buckets.get(bi);
		      bucket.add(ar[i]);
		    }

		    // Sort buckets and stitch together answer
		    for (int bi = 0, j = 0; bi < NUM_BUCKETS; bi++) {
		      List<Integer> bucket = buckets.get(bi);
		      if (bucket != null) {
		        Collections.sort(bucket);
		        for (int k = 0; k < bucket.size(); k++) {
		          ar[j++] = bucket.get(k);
		        }
		      }
		    }
	  }
	//My Version With ArrayList[], faster
	public static void bucketSort2(int[] arr,int minVal, int maxVal) {
		
		int range = maxVal-minVal + 1;
		int offset = (int) Long.highestOneBit(range);
		int n = arr.length;
		System.out.println("range, offset" + range + ", " + offset);
		//큰자리, 몫
		List<List<Integer>> bucket1= new ArrayList<>(2);
		//작은자리, 나머지
		List<List<Integer>> bucket2 = new ArrayList<>(offset);
		for(int i=0; i<2; i++) bucket1.add(i,new ArrayList<Integer>());
		for(int i=0; i<offset; i++) bucket2.add(i, new ArrayList<Integer>());

		//작은자리 먼저
		for(int i = 0; i<n; i++) {
			int index = (arr[i] -minVal ) % offset;
			bucket2.get(index).add(arr[i]);

		}
		for(int i=0; i<offset; i++) {
			for(int j=0; j<bucket2.get(i).size(); j++) {
				int index = (bucket2.get(i).get(j)-minVal) / offset;
				bucket1.get(index).add(bucket2.get(i).get(j));
			}
		}
		bucket2.clear();
		//Sort!
		for(int i=0,k=0; i<bucket1.size(); i++) {
			for(int value : bucket1.get(i)) {
				arr[k++] = value;
			}

		}
		bucket1.clear();
	}
	//My Version With LinkedList[], fastest
	@SuppressWarnings("unchecked")
	public static void bucketSort3(int[] arr, int minVal, int maxVal) {
		
		int range = maxVal-minVal +1;
		int offset = (int) Long.highestOneBit(range);
		int n = arr.length;
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] bucket1 = new LinkedList[2];
		LinkedList<Integer>[] bucket2 = new LinkedList[offset];

		for(int i = 0; i<2; i++) bucket1[i] = new LinkedList<Integer>();
		for(int i=0; i<offset; i++) bucket2[i] = new LinkedList<Integer>();
		
		
		for(int i=0; i<n; i++) {
			int index = (arr[i] - minVal) % offset;
			bucket2[index].add(arr[i]-minVal);
		}
		
		for(int i=0; i<offset; i++) {
			while(!bucket2[i].isEmpty()) {
				int poll = bucket2[i].poll();
				int index = poll / offset;
				bucket1[index].add(poll);
			}
		}
		
		for(int i=0, k=0; i<2; i++) 
			while(!bucket1[i].isEmpty()) 
				arr[k++] = bucket1[i].poll() + minVal;
		

	}
	  public static void main(String[] args) {
		
		  final int N = 500000;
		  int[] arr = new int[N];
		  for(int i=0; i<N; i++) arr[i] = ranNum(-100000,100000);
		  
		  long runtime = System.currentTimeMillis();
		  bucketSort3(arr,-100000,100000);
		  runtime = System.currentTimeMillis() - runtime;
		  
		  System.out.println(Arrays.toString(arr));
		  //N : 500,000 -> fiset 180   Mine 122 :: 내것이 더 빠르므로 내것으로 쓴다.
		  System.out.println("Running Time for Array Length " + N  +  " : " + runtime + "ms");
	}
	  
	  private static int ranNum(int minVal, int maxVal) {
		  Random ran = new Random();
		  return ran.nextInt(maxVal - minVal) + minVal;
	  }
}
