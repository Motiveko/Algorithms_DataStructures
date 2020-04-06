package myStudy.datastructure.suffixArray;


public class SuffixArraySlow extends SuffixArray {

	
	private static class Suffix implements Comparable<Suffix>{
		
		final int index, len;
		final int[] text;
		
		public Suffix(int[] text, int index){
			this.len = text.length -index;
			this.index = index;
			this.text = text;
		}

		@Override
		public int compareTo(Suffix other) {
			if( this == other) return 0;
			int minLen = Math.min(len, other.len);
			
			for(int i=0; i<minLen; i++) {
				int cmp = text[index + i] - other.text[other.index+i];
				if(cmp !=0) return cmp;
			}
			// 긴놈이 밑으로
			return len - other.len;
		}
		
		public String toString() {
			return new String(text, index, len);
		}
	}
	
	Suffix[] suffixes;
	
	public SuffixArraySlow(String text) {
		super(toIntArray(text));
	}
	public SuffixArraySlow(int[] text) {
		super(text);	
	}

	@Override
	protected void construct() {
		
		sa = new int[N];
		suffixes = new Suffix[N];
		
		for(int i=0; i<N; i++) suffixes[i] = new Suffix(T,i);
		java.util.Arrays.sort(suffixes);
		
		for(int i=0; i<N; i++) {
			sa[i] = suffixes[i].index;
			suffixes[i] = null;
		}
		suffixes = null;
	}
	
	public static void main(String[] args) {
		SuffixArraySlow sa = new SuffixArraySlow("banana");
		sa.getLcpArray(); 
		System.out.println(sa);
	}
	
	
}
