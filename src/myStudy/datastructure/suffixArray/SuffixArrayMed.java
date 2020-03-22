package myStudy.datastructure.suffixArray;

import java.util.*;
/*
 * Manber Myers Algorithm! O(nlogn) 조낸어렵다
 * 첫 인덱스를 비교하고 p= 1 일때 첫번째 자리를 비교해서 sa를 그룹화 (rank num)  시킨다
 * 그 뒤 첫번째 랭크 비교 후 p= 2로 자신의 2번째 다음 녀석의 rank를 비교해 또 그룹화 시킨다. 없으면 -1을 넣고 최고순서로 올려준다.
 * p=4 , 8 , 16으로 늘려가며 rank 번호가 n-1까지 (n개) 비교를 계속한다.
 * p가 2의 지수승으로 증가하며 이빨빠지게 검사하는것은 이미 1,2 를 하고 나면 3번째까지 다 검증을 한 상태, 4다음 567도 검증이 된상태다.
 * 이것은 이해가 잘안되니 자꾸생각해보자
 */
public class SuffixArrayMed extends SuffixArray {



	static class SuffixRankTuple implements Comparable<SuffixRankTuple>{
		int firstHalf, secondHalf, originalIndex;
		
		@Override
		public int compareTo(SuffixRankTuple other) {
			int cmp = Integer.compare(firstHalf, other.firstHalf);
			if(cmp==0) return Integer.compare(secondHalf, other.secondHalf);
			return cmp;
		}

		@Override
		public String toString() {
			return originalIndex + " -> (" + firstHalf + ", " + secondHalf + ")";
		}
	}
	
	public SuffixArrayMed(String text) {
		super(toIntArray(text));
	}
	
	public SuffixArrayMed(int[] text) {
		super(text);
	}

	@Override
	protected void construct() {
		sa = new int[N];
		
		int[][] suffixRank = new int[2][N];
		SuffixRankTuple[] ranks = new SuffixRankTuple[N];
		
		for(int i=0; i<N; i++) {
			suffixRank[0][i] = T[i];
			ranks[i] = new SuffixRankTuple();
		}
		
		for(int pos=1; pos<N; pos *= 2) {
			
			for(int i=0; i<N; i++) {
				ranks[i].firstHalf = suffixRank[0][i];
				ranks[i].secondHalf = (i+pos) < N ? suffixRank[0][i+pos] : -1;
				ranks[i].originalIndex = i;
			}
			
			Arrays.sort(ranks);
			
			int newRank = 0;
			suffixRank[1][ranks[0].originalIndex] = 0;
			
			for(int i=1; i<N; i++) {
				
				SuffixRankTuple lastRank = ranks[i-1];
				SuffixRankTuple currRank = ranks[i];
				
				if( lastRank.firstHalf != currRank.firstHalf 
						|| lastRank.secondHalf != currRank.secondHalf) newRank++;
				
				suffixRank[1][ranks[i].originalIndex] = newRank;
			}
			
			suffixRank[0] = suffixRank[1];
			if(newRank == N-1) break;
		}
		
		for(int i=0; i<N; i++) {
			sa[i] = ranks[i].originalIndex;
			ranks[i] =null;
		}
		
		suffixRank[0] = suffixRank[1] = null;
		suffixRank = null;
		ranks =null;
		
		
	}
	
	public static void main(String[] args) {
		
		SuffixArrayMed sa = new SuffixArrayMed("ananabc");
		System.out.println(Arrays.toString(sa.getLcpArray()));
		System.out.println(sa);
	}
	
}
