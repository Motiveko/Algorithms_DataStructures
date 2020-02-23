package myStudy;

import java.util.LinkedList;
import java.util.Random;

//n개를 쭉 훑은 후 최소값을 0번에, n-1를 훑은후 최소값을 1번에 ...
class SelectionSort{
	int count = 0;
	public void sorting(int[] arr) {
		int len = arr.length;
		int min;
		int minIndex=0;
		for(int i=0;i<len-1;i++) {
			min=arr[i];
			for(int k=i+1;k<len;k++) {
				count++;
				if(min>arr[k]) {
					min = arr[k];
					minIndex=k;
				}
				
			}
			arr[minIndex] = arr[i];
			arr[i] = min;
		}
	}
	
}

class InsertionSort{
	int count = 0;
	public void sorting(int[] arr) {

		int len = arr.length;
		int tmp=0;
		
		for(int i=0; i<len-1; i++) {
			int j=i+1;
			while(j>0 && arr[j]<arr[j-1]) {
				tmp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = tmp;
				j--;
				count++;
			}
		}
		
	}
	
} 

// 뒤에서부터 출발해서 n n+1 값 비교 후 n+1이 작으면 한칸 앞으로, 0까지. 그다음은 뒤에서 출발해서 1까지... 이런식
class BubbleSort{
	int count = 0;
	public void sorting(int[] arr) {
		int len = arr.length;
		int tmp;
		
		for(int k=0;k<len-1;k++) {
			for(int i = len-1; i>k; i--) {
				count++;
				if(arr[i] < arr[i-1]) {
					tmp = arr[i];
					arr[i] = arr[i-1];
					arr[i-1] = tmp;
				}
			}
		}
	}
}

// binary로 계속 쪼갠 뒤 하나씩 합치면서 정렬, 매 level마다 O(n) , level : logn ->O(nlogn)
class MergeSort{
	int count = 0;
	
	public void sorting(int[] arr) {
		int len = arr.length;
		int[] tmp = new int[len];
		mergeSorting(arr,tmp,0,len-1);
	}
	
	private void mergeSorting(int[] arr, int[] tmp, int start, int end) {
		
		if(start<end) {
			int mid = (end+start)/2;
			mergeSorting(arr,tmp,start,mid);
			mergeSorting(arr,tmp,mid+1,end);
			merge(arr,tmp,start,mid,end);
		}
	}
	
	private void merge(int[] arr, int[] tmp, int left,int mid,int right) {

		for(int i =left; i<=right; i++) {
			tmp[i] =arr[i];
		}
		
		int p1 = left;
		int p2 = mid+1;
		int index = left;
		
		while(p1<=mid && p2<=right) {
			
			if( tmp[p1]< tmp[p2]) {
				arr[index] = tmp[p1];
				p1++;
			} else {
				arr[index] = tmp[p2];
				p2++;
			}
			
			index++;
			count++;
		}
		
		if(p1>mid) {
			while(p2<=right) {
				arr[index] = tmp[p2];
				index++; 
				p2++;
				count++;
			}
		} else {
			while(p1<=mid) {
				arr[index] = tmp[p1];
				index++;
				p1++;
				count++;
			}
		}
		

		
	}
}


class QuickSort{
	
	int count=0;
	int tmp;
	
	public void sorting(int[] arr) {
		sorting(arr,0,arr.length-1);
	}
	
	private void sorting(int[] arr, int p, int end) {
		
		if(p>=end) return;
		int pivot = p;
		int i = p+1;
		int j = end;
		
		while( j>i) {
			
			while(i<end && arr[i]<arr[pivot]) i++;
			while(j>pivot+1 && arr[j]>=arr[pivot]) j--;
			
			if( j>i) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}
		
		if(arr[pivot]>arr[j]) {
			tmp = arr[pivot];
			arr[pivot] = arr[j];
			arr[j] = tmp;
			
			sorting(arr,p,j-1);
			sorting(arr,j+1,end);
		} else {
			//arr[pivot] < arr[j], privot위로는 전부다 pivot의 참조값보다 큰값들만 있다
			sorting(arr,j,end);
		}
		
	}
	
}

// gap 으로 나눈 후 삽입정렬을 이용, 어느정도 정렬이 잘 되있으면 삽입정렬은 빠른것에 착안.
class ShellSort{
	
	int[] h = {701,301,132,57,23,10,4,1};
	int count=0;
	
	public void sorting(int[] arr) {
		int len = arr.length;
		int gapIndex=0;
		int gap = h[gapIndex];
		//gap의 시작 크기를 length의 1/3 이하로 하겠다.
		while(gap*2 > len) gap=h[++gapIndex];
		
		sorting(arr,gapIndex);
	}
	
	
	private void sorting(int[] arr, int gapIndex) {
		
		if( gapIndex == h.length) return;
		int len = arr.length;
		int gap = h[gapIndex];
		
		int size = len/gap;

		
		//group i
		for( int i = 0; i< gap; i++	) {
			
			int[] tmp = new int[size];
			int[] index = new int[size];
			
			if(len % gap  > i) {
				tmp = new int[size + 1];
				index = new int[size +1];
			}
			
			for( int j=i,k=0; j<len; k++,j+=gap) {
				tmp[k] = arr[j];
				index[k] = j;
			}
			
			//insertion sorting for tmp, (group i)
			for( int j=1; j<tmp.length; j++) {
				
				int k=j;
				
				while( k>0	&& tmp[k]<=tmp[k-1] ) {
					int x = tmp[k];
					tmp[k] = tmp[k-1];
					tmp[k-1] = x;
					k--;
				}
			}
			
			//sorted tmp => arr
			for( int k=0; k<tmp.length; k++) {
				arr[index[k]] = tmp[k];
			}
		}
		
		sorting(arr, ++gapIndex);
	}
	
}

//Max binary heap을 이용, 배열을 힙으로 만든 다음 가장 큰값인 root를 추출해 arr의 끝에서부터 채우는방식
class HeapSort{
	
	LinkedList<Integer> heap = new LinkedList<>();
	int size =0;
	
	
	public void sorting(int[] arr) {
		construct(arr);
		heapSorting(arr);
	}
	
	private void construct(int[] arr) {
	
		size = arr.length;
		for(int i =0; i<size ; i++) heap.addLast(arr[i]);
		
		//heapify
		for(int i = (size/2) -1; i>=0; i--) sink(i);
		
	}
	
	private void sink(int i) {

		
		int left = 2*i + 1;
		int right = left +1;
		int bigger = left;
		
		if(right<size && cmp(right,left) == 1) bigger = right;
		if(bigger<size && cmp(i,bigger) < 0 ) {
			swap(i,bigger);
			sink(bigger);
		}
		
	}

	private int cmp(int i, int j) {
		
		if(i>=size || i<0 || j>=size || j<0) throw new IndexOutOfBoundsException("invalid index for cmp");
		
		int a = heap.get(i);
		int b = heap.get(j);
		
		if( a > b) return 1;
		if( a < b ) return -1;
		return 0;
	}
	
	private void swap(int i, int j) {
		
		if(i>=size || i<0 || j>=size || j<0) throw new IndexOutOfBoundsException("invalid index for swap");
		
		int a = heap.get(i);
		int b = heap.get(j);
		
		heap.set(i, b); heap.set(j, a);
	}
	
	private boolean isMaxHeap(int i) {
		
		if( i>size ) return true;
		
		int left = 2*i +1;
		int right = left + 1;
		
		if( left<size && cmp(i,left)<0 ) return false;
		if( right<size && cmp(i,right)<0) return false;
		
		return isMaxHeap(left) && isMaxHeap(right);
	}
	
	//binary heap to array
	private void heapSorting(int[] arr) {
		
		for(int i = size-1; i>=0; i--) {
			
			arr[i] = heap.peek();
			
			swap(0,i);
			heap.remove(--size);
			sink(0);
		}
	}
	
}

//자릿수 마다 값을 비교 후 버킷 생성
//10으로 나누면 값이 커질 때 여러번 돌려야하므로 딱 두번만 정렬할 수 있게 n의 square값을 이용한다. 
//정수가 안나오고 연산이 힘드므로 square값보다 큰 값중 2의 제곱근을 사용
class RadixSort{
	
	public void sorting(int[] arr) {
		
		int len = arr.length;
		int bucketSize=1;
		int i=0;
		//적절한 버킷값 설정, 2^n
		while( bucketSize  <= Math.sqrt(len)) {
			i++;
			bucketSize = (int) Math.pow(2, i);
		}
		
		sorting(arr, bucketSize, i);
	}
	
	private void sorting(int[] arr, int bucketSize, int bucketIndex) {
		
		int len = arr.length;
		
		LinkedList<Integer>[] bucket1 = new LinkedList[bucketSize];
		LinkedList<Integer>[] bucket2 = new LinkedList[bucketSize];
		
		//1의자리 분류해서 버킷1에 추가	
		for(int i=0; i<len; i++) {
			int index = arr[i] % bucketSize;
			if(bucket1[index]!=null) bucket1[index].addLast(arr[i]);
			else {
				bucket1[index] = new LinkedList<>();
				bucket1[index].addLast(arr[i]);
			}
		}
		//차례대로 버킷1에서 꺼내서 2의자리 분류해서 버킷2에 추가
		for(int i=0; i<bucketSize; i++) {
			while(bucket1[i] != null && !bucket1[i].isEmpty()) {
				int index = bucket1[i].peek() >> bucketIndex;
				if(bucket2[index]!=null) bucket2[index].add(bucket1[i].poll());
				else {
					bucket2[index] = new LinkedList<>();
					bucket2[index].add(bucket1[i].poll());
				}
			}
		}
		
		//bucket2에서 차례대로 arr로 값 넣기
		int k=0;
		for( int i = 0; i<bucketSize; i++) {
			while(bucket2[i] !=null && !bucket2[i].isEmpty()) 
				arr[k++] = bucket2[i].poll();
		}
		
		
		
	}
	
	
}

public class PracticeSorting2 {

	public static boolean isValidArr(int arr[]) {
		int len = arr.length;
		
		for(int i=0 ; i<len-1; i++) {
			if(arr[i]>arr[i+1]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		int N = 1000;
		Random ran = new Random();
		
//		BubbleSort bs = new BubbleSort();
//		int[] bsArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			bsArr[i]=ran.nextInt(N);
//			System.out.print("'" + bsArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		bs.sorting(bsArr);
//		System.out.println("============================BubbleSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) System.out.print("'" + bsArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(bsArr));
//		
		
		
		
//		SelectionSort ss = new SelectionSort();
//		int[] ssArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			ssArr[i]=ran.nextInt(N);
//			System.out.print("'" + ssArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		ss.sorting(ssArr);
//		System.out.println("============================SelectionSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) System.out.print("'" + ssArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(ssArr));
//		
//		
//		InsertionSort is = new InsertionSort();
//		int[] isArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			isArr[i]=ran.nextInt(N);
//			System.out.print("'" + isArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		is.sorting(isArr);
//		System.out.println("============================InsertionSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) 
//			System.out.print("'" + isArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(isArr));
//		System.out.println(is.count);
		
//		MergeSort ms = new MergeSort();
//		int[] msArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			msArr[i]=ran.nextInt(N);
//			System.out.print("'" + msArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		ms.sorting(msArr);
//		System.out.println("============================MergeSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) 
//			System.out.print("'" + msArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(msArr));
//		System.out.println(ms.count);
		
//		QuickSort qs = new QuickSort();
//		int[] qsArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			qsArr[i]=ran.nextInt(N);
//			System.out.print("'" + qsArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		qs.sorting(qsArr);
//		System.out.println("============================QuickSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) 
//			System.out.print("'" + qsArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(qsArr));
//		System.out.println(qs.count);
//	
//		ShellSort sh = new ShellSort();
//		int[] shArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			shArr[i]=ran.nextInt(N);
//			System.out.print("'" + shArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		sh.sorting(shArr);
//		System.out.println("============================ShellSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) 
//			System.out.print("'" + shArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(shArr));
//		System.out.println(sh.count);
		
//		HeapSort hh = new HeapSort();
//		int[] hhArr = new int[N];
//		
//		System.out.print("{");
//		for(int i=0; i<N; i++) {
//			hhArr[i]=ran.nextInt(N);
//			System.out.print("'" + hhArr[i] + "', ");
//		}
//		System.out.println("}");
//		
//		System.out.println();
//		hh.sorting(hhArr);
//		System.out.println("============================HeapSort============================");
//		System.out.print("{");
//		for(int i=0; i<N; i++) 
//			System.out.print("'" + hhArr[i] + "', ");
//		System.out.println("}");
//		System.out.println(isValidArr(hhArr));

		RadixSort rs = new RadixSort();
		int[] rsArr = new int[N];
		
		System.out.print("{");
		for(int i=0; i<N; i++) {
			rsArr[i]=ran.nextInt(N);
			System.out.print("'" + rsArr[i] + "', ");
		}
		System.out.println("}");
		
		System.out.println();
		rs.sorting(rsArr);
		System.out.println("============================RadixSort============================");
		System.out.print("{");
		for(int i=0; i<N; i++) 
			System.out.print("'" + rsArr[i] + "', ");
		System.out.println("}");
		System.out.println(isValidArr(rsArr));
		
	}

}



