package myStudy.datastructure.suffixArray;

public abstract class SuffixArray {
	//Length of the suffix array
	protected final int N;
	//text
	protected int[] T;
	// sorted suffix array values
	protected int[] sa;
	//Longest Common Prefix array
	protected int[] lcp;
	
	private boolean constructedSa;
	private boolean constructedLcpArray;
	
	public SuffixArray(int[] text) {
		if(text == null) throw new IllegalArgumentException("no text input?!");
		this.T = text;
		this.N = text.length;
	}
	
	public int getTextLength() {
		return N;
	}
	
	public int[] getSa() {
		buildSuffixArray();
		return sa;
	}
	
	public int[] getLcpArray() {
		buildLcpArray();
		return lcp;
	}
	
	protected void buildSuffixArray() {
		if(constructedSa) return;
		construct();
		constructedSa = true;
	}
	
	protected void buildLcpArray() {
		if(constructedLcpArray) return;
		buildSuffixArray();
		kasai();
		constructedLcpArray = true;
	}
	
	protected static int[] toIntArray(String s) {
		if( s == null) return null;
		int[] t = new int[s.length()];
		for( int i=0; i<s.length(); i++) t[i] = s.charAt(i);
		
		return t;
	}
	
	//will be implemented slow to fast Version
	protected abstract void construct();
	
	//kasai 이해 완료
	  /*
	   * kasai 에서 중요한건  for(i=0,len=0) 에서 i는 index값이고 k는 그보다 sa rank가 위에 있는 녀석의 index값이다
	   * len은 비교하는 자리
	   * sa[rank] = index <===> inv[index] = rank 관계를 이용해서
	   * 어떤 rank inv[i] 인 index i에서 상위 rank인 inv[i] - 1 을 가지는 index k를 도출해 낸 것이다
	   * 씨벌..
	   */
	private void kasai() {

		lcp = new int[N];
		
		int[] inv = new int[N];
		
		for( int i = 0; i<N ; i++) inv[sa[i]] = i;
		
		for( int i=0, len=0; i<N; i++) {
			
			if(inv[i] > 0) {
				int k =sa[inv[i]-1];
				while(i + len < N && k + len < N && T[i+len] == T[k+len]) len++;
				lcp[inv[i]] = len;

				if(len>0) len--;
			}
		}
		
		inv =null;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" -----i-----SA-----LCP---Suffix\n");
		
		for(int i=0; i<N ; i++) {
			int suffixLen = N - sa[i];
			char[] suffixArray = new char[suffixLen];
			for(int j=sa[i], k=0 ; j<N; j++,k++) suffixArray[k] = (char)T[j];
			String suffix  = new String(suffixArray);
			String formattedStr = 
					String.format("%7d %7d %7d %s\n", i,sa[i],lcp[i],suffix	);
			sb.append(formattedStr);
		}
		
		return sb.toString();
	}
	
	
}
