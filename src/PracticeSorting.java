import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class SelectionSort{
	
	long count;
	
	public int[] sorting(int[] list) {
	int len = list.length;
	
	for(int i=0; i<len; i++) {
		
		int minIndex=i;
		
		for(int j=i+1; j<len;j++) {
			count++;
			if(list[j]<list[minIndex]) minIndex=j;
		}
		int temp = list[minIndex];
		
		list[minIndex] = list[i];
		list[i] = temp;
	}
	
	return list;
	}

}

class InsertionSort{
	
	long count;
	
	public int[] sorting(int[] list) {
		int len = list.length;
	
		for(int i=1; i<len; i++) {
	
			int j=i-1;
			
			while( j>=0 && list[j]>list[j+1]) {
				int temp = list[j+1];
				list[j+1] = list[j];
				list[j] = temp;
				count++;
				j--;
			}
			
		}
		return list;
	}
	public String toString() {
		return String.valueOf(count);
	}
}

class BubbleSort{
	
	long count;
	
	public int[] sorting(int[] list) {
		
		int len = list.length;
		int temp;
		for(int i=len-1; i>0; i--) {
			
			for(int j=i; j>0;j--) {
				
				if(list[j]>list[j-1]) {
					temp = list[j-1];
					list[j-1] = list[j];
					list[j] = temp;
				}
				count++;
			}
		}
		return list;
	}
}

class MergeSort{
	
	long count;
	
	public void sorting(int[] arr) {
		int[] tmp = new int[arr.length];
		mergeSort(arr,tmp,0,arr.length-1);
	}
	
	private void mergeSort(int[] arr , int[] tmp, int start, int end) {
	
		if(start < end) {
			int mid = (end - start)/2;
			mergeSort(arr, tmp, start, mid);
			mergeSort(arr, tmp, mid+1, end);
			merge(arr, tmp, start, mid, end);
		}
		
	}
	
	private void merge(int[]arr, int[]tmp, int start, int mid, int end) {
		
		for(int i = start; i<=end; i++) {
			tmp[i]= arr[i];
		}
		
		int i = start;
		int j = mid +1;
		int index = start;
		while(i<=mid && j<=end) {
			
			if(tmp[i]<tmp[j]) {
				arr[index] = tmp[i];
				i++;
			} else {
				arr[index] = tmp[j];
				j++;
			}
			index++;
			count++;
		}
		
		if(i>mid) {
			while(j<=end) {
				arr[index] = tmp[j];
				index++; j++; 
				count++;
			}
		} else {
			while(i<=mid) {
				arr[index] = tmp[i];
		//		System.out.println("index : " + index + ", i : " + i);
				index++; i++;
				count++;
			}
		}

	}
}

class QuickSort{
	int count=0;
	
	public void sorting(int[] arr) {
		sorting(arr,0,(arr.length-1));
	}
	public void sorting(int[] arr, int left, int right) {
				
		if(left>=right) return;
		
		int pivot = left;
		int tmp;
		int i = left+1;
		int j = right;
		
		//배열이 2칸짜리까지 왔다 : 단순비교 후 return
		if(i==j) {
			if(arr[pivot]>arr[i]) {
				tmp = arr[pivot];
				arr[pivot] = arr[i];
				arr[i] = tmp;
			}
			return;
		}
		
		while(i<j) {
			
			while(arr[pivot]>arr[i] && i<right) {
				i++; count++;
			}
			while(arr[pivot]<=arr[j] && j>pivot+1) {
				j--; count++;
			}
			
			if(i<j) {
				count++;
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		if(arr[pivot]>=arr[j]) {
			count++;
			tmp = arr[pivot];
			arr[pivot] = arr[j];
			arr[j] = tmp;
		}
		
		sorting(arr, pivot, j-1 );
		sorting(arr, j, right);
		
	}
}

class ShellSort{
	int count = 0;
	//best gap array
	int[] h = {701,301,132,57,23,10,4,1};
	
	public void sorting(int[] arr) {
		int len = arr.length;
		//arr의 길이에 따라 sorting gap 차등적용
		int i =0;
		int gap = h[i];
		while(len<gap) i++;
		
		sorting(arr,i);
	}
	
	private void sorting(int[] arr, int gapIndex) {
		
		int len = arr.length;
		int gap = h[gapIndex];

		//group offset , group 갯수는 gap만큼, offset = i;
		for(int offset =0; offset<gap; offset++) {
			
			// offset에 따라 tmp  길이를 다르게 설정...
			int length;
			if( (len/gap)*gap + offset < len) {
				length = len/gap +1;
			} else length = len/gap;
		
			int[] tmp = new int[length];
			int[] index = new int[length];
			count++;
			//arr에서 group i 추출 후 tmp에 복사
			for(int i= offset,k=0; i<len; i+=gap,k++) {
				tmp[k] = arr[i];
				index[k] = i;
				count++;
			}
			//tmp를 insertion sort 
			for(int i=1; i<tmp.length; i++) {
				int j=i-1;
				
				while(j>=0 && tmp[j]>tmp[j+1]) {
					int var = tmp[j];
					tmp[j] = tmp[j+1];
					tmp[j+1] = var;
					j--;
					count++;
				}
			}
			//sorting한 tmp를 arr에 인덱스에 맞춰 다시 넣기
			for(int i=0; i<tmp.length; i++) {
				arr[index[i]] = tmp[i];
				count++;
			}
		}
		if(gap==1) return;
		else sorting(arr,++gapIndex);
	}
}
/*
	HeapSort 과정
	int[] arr => binaryheap 생성 :: O(N/2 * logN)
	
	root는 가장 큰 값이므로 root와 맨 마지막 element를 바꿔주고 root의 값을 arr의 맨 마지막에 대입
	root에 있는 값은 sink :: O(logN)
	N-1번 반복
	
	-> O(NlogN)
*/
class HeapSort {

	List<Integer> heap;
	int size, count;
	
	
	public void sorting(int[] arr) {
		buildHeap(arr);

		heapSorting(arr);
	}
	
	//sorting method
	private void heapSorting(int[] array) {
		int len = array.length;
		int[] arr = new int[len];
		
		for(int j=len-1 ; j>=0;j--) {
			arr[j] = heap.get(0);
			swap(0,j);
			heap.remove(j);
			size--;
			if(size!=0) sink(0);
			count++;
		}
		
		for(int i = 0;i<len;i++) {
			array[i] = arr[i];
			count++;
		}
		

	}

	//MaxHeap Build
	private void buildHeap(int[] arr) {
		heap = new ArrayList<Integer>(arr.length);
		size = arr.length;
		
		for(int i=0; i<size; i++) heap.add(arr[i]);
		
		//heapify process
		for(int i =size/2-1;i>=0;i--) sink(i);
	}
	
	private void sink(int i) {
		if(i<0 || i>=size) throw new IndexOutOfBoundsException("invalid index in sink, i : " + i + "size : "+ size);
		int left = 2*i +1;
		int right = left +1;
		int bigger = left; // default
		
		if(right<size && cmp(left,right)<0) bigger = right;
		if(bigger<size && cmp(i,bigger)<=0) {
			swap(i,bigger);
			sink(bigger);
		}
		count++;

	}
	private void swim(int i) {
		if(i<=0 || i>=size) throw new IndexOutOfBoundsException("invalid index in swim");
		int parent = (i-1)/2;
		
		if(parent>=0 && cmp(i,parent) >= 0) swap(i,parent);
		if(parent>0) swim(parent);
		count++;
	}
	
	private void swap(int i , int j) {
		
		if(i>=size || j>=size || i<0 || j<0) throw new IndexOutOfBoundsException("invalid index in swap");
		int a = heap.get(i);
		int b = heap.get(j);
		
		heap.set(i, b);
		heap.set(j, a);
	}
	
	private int cmp(int i, int j) {
		int a = heap.get(i);
		int b = heap.get(j);
		if(a>b) return 1;
		if(a==b) return 0;
		return -1;
	}
	public boolean isMaxHeap(int i) {
		
		if(i>=size-1) return true;
		
		int left = 2*i +1;
		int right = left +1;
		
		if(right<size && cmp(i,right) <0 ) return false;
		if(left<size && cmp(i,left) <0 ) return false;
		
		return isMaxHeap(left) && isMaxHeap(right);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Integer> itr = heap.iterator();
		while(itr.hasNext()) sb.append("'" + itr.next() + "', ");
		
		return sb.toString();
	}
	
}

// https://blog.naver.com/chogahui05/221352531601
class RadixSort{
	
	int count= 0;
	
	LinkedList<Integer>[] bucket1;
	LinkedList<Integer>[] bucket2;
	
	public void sorting(int[] arr) {
		int k=0;
		int len = arr.length;

		//2^0
		int bucketSize=1;
		int sq=0;
		for(int i=1; (int)Math.pow(bucketSize,2)<len ; i++) {
			bucketSize=(int) Math.pow(2, i);
			sq=i;
		}
		System.out.println("bucketSize : " + bucketSize);
		
		LinkedList<Integer> list = new LinkedList<>();	
		bucket1 = new LinkedList[bucketSize];
		bucket2 = new LinkedList[bucketSize];
		
		//Level 1
		for(int i=0; i<len; i++) {
			int index = arr[i]%bucketSize;
			LinkedList<Integer>bucket =  bucket1[index];
			if(bucket ==null) {
				bucket = new LinkedList<>();
				bucket1[index] = bucket; 
			}
			bucket.add(arr[i]);
			count++;
		}
			// 첫째자리 분류된 순서대로 list에 push
		for(int i=0; i<bucketSize; i++) {
			LinkedList<Integer>bucket =  bucket1[i];
			while(bucket!=null && !bucket.isEmpty()) 
				list.addLast(bucket.poll());
			count++;
		}
		
		//Level 2
		for(int i=0; i<len; i++) {
			int index = list.peek() >> sq;
			LinkedList<Integer>bucket =  bucket2[index];
			if(bucket ==null) bucket = new LinkedList<>();
			bucket.add(list.poll());
			bucket2[index] = bucket; 
			count++;
		}
		for(int i=0; i<bucketSize; i++) {
			LinkedList<Integer>bucket =  bucket2[i];
			while(bucket !=null && !bucket.isEmpty()) {
				arr[k] = bucket.poll();
				k++;
			}
		}
	}	
}

public class PracticeSorting{

	//sorting이 잘 되었는지 검사
	public static boolean isValidArr(int[] arr) {
		int len = arr.length;
		
		for(int i=0; i<len-1; i++) 
			if(arr[i]>arr[i+1]) return false;
		
		return true;	
	}
	
	public static void main(String[] args) {
		//배열사이즈	
		int N=100000; 
//
//		//SelectionSort
//		Random ran1 = new Random();
//		int[] num1 = new int[1000];
//		for(int i =0; i<1000; i++) {
//			num1[i] =ran1.nextInt(1000);
//		}
//		
//		System.out.println("=====SelectionSort=====");
//		
//		SelectionSort ss = new SelectionSort();
//		int[] ssTable = ss.sorting(num1);
//		System.out.print("{ ");
//		for(int i =0; i<1000; i++) {
//			System.out.print("'" + ssTable[i] + "', ");
//		}
//		System.out.print("}");	
//		System.out.println();
//		System.out.println(ss.count);
//
//		
//		
//		
//		
//		//InsertionSort
//		Random ran2 = new Random();
//		int[] num2 = new int[1000];
//		for(int i =0; i<1000; i++) {
//			num2[i] =ran2.nextInt(1000);
//		}
//		
//
//		System.out.println("=====InsertionSort=====");
//		InsertionSort is = new InsertionSort();
//		
//		int[] isTable	= is.sorting(num2);
//		
//		System.out.print("{ ");
//		for(int i =0; i<1000; i++) {
//			System.out.print("'" + isTable[i] + "', ");
//		}
//		System.out.print("}");	
//		System.out.println();
//		System.out.println(is.count);
//	
//
//		//BubbleSort
//		Random ran3 = new Random();
//		int[] num3 = new int[1000];
//		for(int i =0; i<1000; i++) {
//			num3[i] =ran3.nextInt(1000);
//		}
//		
//
//		System.out.println("=====InsertionSort=====");
//		BubbleSort bs = new BubbleSort();		
//		bs.sorting(num3);
//		System.out.print("{ ");
//		for(int i =0; i<1000; i++) {
//			System.out.print("'" + num3[i] + "', ");
//		}
//		System.out.print("}");	
//		System.out.println();
//		System.out.println(bs.count);
//		
//		System.out.println(isValid(num3))
//		
//
//		//MergeSort
//		Random ran4 = new Random();
//		int[] num4 = new int[N];
//		
//		System.out.print("{ ");
//		for(int i =0; i<N; i++) {
//			num4[i] =ran4.nextInt(N);
//			System.out.print("'" + num4[i] + "', ");
//		}
//		System.out.print("}");	
//
//		System.out.println();
//		System.out.println("=====MergeSort=====");
//		MergeSort ms = new MergeSort();		
//		ms.sorting(num4);
//		
//		
//		System.out.print("{ ");
//		for(int i =0; i<N; i++) {
//			System.out.print("'" + num4[i] + "', ");
//		}
//		System.out.print("}");	
//		System.out.println();
//		System.out.println(ms.count);
//		
//		System.out.println(isValidArr(num4));
	
//
//		//QuickSort
//		Random ran5 = new Random();
//		int[] num5 = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			num5[i] = ran5.nextInt(N);
//			System.out.print("'"+num5[i]+"', ");
//		}
//		System.out.print("}");
//		
//		System.out.println();
//		System.out.println("=====QuickSort=====");
//		QuickSort qs = new QuickSort();
// 		qs.sorting(num5);
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			System.out.print("'"+num5[i]+"', ");
//		}
//		System.out.print("}");
//		System.out.println();
//		System.out.println(qs.count);
//		System.out.println(isValidArr(num5));
//
//		
		//ShellSort
//		Random ran6 = new Random();
//		int[] num6 = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			num6[i] = ran6.nextInt(N);
//			System.out.print("'"+num6[i]+"', ");
//		}
//		System.out.print("}");
//		
//		System.out.println();
//		System.out.println("=====ShellSort=====");
//		ShellSort ss = new ShellSort();
//		ss.sorting(num6);
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			System.out.print("'"+num6[i]+"', ");
//		}
//		System.out.print("}");
//		System.out.println();
//		System.out.println(ss.count);
//		System.out.println(isValidArr(num6));
//		
//		//HeapSort
//		Random ran7 = new Random();
//		int[] num7 = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			num7[i] = ran7.nextInt(N);
//			System.out.print("'"+num7[i]+"', ");
//		}
//		System.out.print("}");
//		System.out.println();
//		
//		System.out.println("=====HeapSort=====");
//		HeapSort hs = new HeapSort();
//		hs.sorting(num7);
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			System.out.print("'"+num7[i]+"', ");
//		}
//		System.out.print("}");
//		System.out.println();
//		System.out.println(hs.count);
//		System.out.println(isValidArr(num7));
//		
		
		
		//Radix Sort
		Random ran8 = new Random();
		int[] num8 = new int[N];
		
		System.out.print("{");
		for(int i=0 ; i<N ; i++) {
			num8[i] = ran8.nextInt(N);
			System.out.print("'" + num8[i] + "', ");
		}
		System.out.print("}");
		System.out.println();
		
		System.out.println("=====RadixSort=====");
		RadixSort rs = new RadixSort();
		rs.sorting(num8);
		
		System.out.print("{");
		for(int i=0 ; i<N ; i++) {
			System.out.print("'" + num8[i] + "', ");
		}
		System.out.print("}");
		System.out.println();
		System.out.println(rs.count);
		System.out.println(isValidArr(num8));
	}
}
