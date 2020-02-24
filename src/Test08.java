class mergeSorts{
	
	public void sorting(int[] arr) {
		int[] tmp = new int[arr.length];
		mergeSorting(arr,tmp, 0, arr.length -1);
	}
	
	public void mergeSorting(int[] arr, int[] tmp, int start, int end) {
		
		if( start != end) {
			int mid = (end + start) / 2;
			mergeSorting(arr, tmp, start, mid);
			mergeSorting(arr, tmp, mid+1, end);
			merge(arr, tmp, start, mid, end);
		}
	}
	
	public void merge(int[] arr,int[] tmp, int start, int mid, int end) {
		
		for(int i = start ; i<=end; i++) tmp[i] = arr[i];
		
		int p1 = start;
		int p2 = mid + 1;
		int index = start;
		
		while(p1<=mid && p2<=end) {
			
			if(tmp[p1]<tmp[p2]) {
				arr[index] = tmp[p1];
				index++;
				p1++;
			} else{
				arr[index] = tmp[p2];
				index++;
				p2++;
			}
		}
		
		if( p1> mid) {
			while(p2<=end) {
				arr[index] = tmp[p2];
				index++; p2++;
			}
		} else if( p2>end) {
			while(p1<=mid) {
				arr[index] = tmp[p1];
				index++; p1++;
			}
		}
		
	}	
}


public class Test08 {
	public boolean isValidArr(int[] arr) {
		for(int i = 0; i<arr.length -1; i++) {
			if(arr[i]>arr[i+1]) return false;
		}
		return true;
	}
	public static void main(String[] args) {
				//0 1 2 3
//		int[] a = {2,4,6,8};
//		
//		System.out.println(a[1]);
//		
//		
//		a[0] = 7;
//		System.out.println(a[0]);
//		System.out.println(a.length);
//		
//		for(int i=0; i<a.length; i++)
//			System.out.println(a[i]);

		//실습
		int[] arr = {5,7,3};
		
//		//오름차순정렬
//		
//		//=======================기본기=======================
//		int tmp= 0;
//		if(arr[0] > arr[1]) {
//			tmp = arr[0]; arr[0] = arr[1]; arr[1] = tmp;
//		}
//		if(arr[0] > arr[2]) {
//			tmp = arr[0]; arr[0] = arr[2]; arr[2] = tmp;
//		}
//		
//		if(arr[1] > arr[2]) {
//			tmp = arr[1]; arr[1] = arr[2]; arr[2] = tmp;
//		}
		
		//=======================Selection Sort=======================
//		for(int i=0; i<arr.length; i++) {
//			int min = arr[i];
//			int minIndex=i;
//			for(int j = i+1; j<arr.length; j++) {
//				if(min > arr[j]) {
//					min = arr[j];
//					minIndex = j;
//				}
//			}
//			arr[minIndex] = arr[i];
//			arr[i] = min;
//		}
		
		// =======================Insertion Sort=======================
		
//		for(int i=0; i<arr.length -1 ; i++) {
//			
//			int j=i+1;
//			
//			while( j>0 && arr[j]<arr[j-1]) {
//				int tmp = arr[j];
//				arr[j] = arr[j-1];
//				arr[j-1] = tmp;
//				j--;
//			}
//		}
//		
		//=======================Bubble Sort=======================
		
//		for(int i=0; i<arr.length; i++) {
//			
//			for(int j=arr.length-1;j>0;j--) {
//				if(arr[j] < arr[j-1]) {
//					int tmp = arr[j];
//					arr[j] = arr[j-1];
//					arr[j-1] = tmp;
//				}
//			}
//		}
		
		mergeSorts ms = new mergeSorts();
		ms.sorting(arr);
		
		
		
		// 3,5,7
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}

}
