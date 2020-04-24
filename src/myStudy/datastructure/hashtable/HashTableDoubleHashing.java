package myStudy.datastructure.hashtable;

/*
 * Linear Probing과 비슷한 부분이 있다
 * 
 * Index = (k.hashcode() + P(k, x++)) mod N
 * where P(k, x) = x*k.hashcode2()
 * Linear와 다른 점은 지수가 constant이기는 하나 값을 알 수 없다는 것이다.
 * 
 * cycle을 피하기 위해..
 * 
 * d = H2(k) mod N , where N is prime number -> gcd(d,N) =1
 * (N이Prime Number이고 d는 N보다 작은 수이므로 둘은 서로소간된다)
 * 
 * H2(k) -> 직접 만들지는 않고 system 내의 universal hash functions를 이용한다.
 * 			( 우리가 해싱할 데이터 타입이 String,int.. 자바 기본데이터타입이라는 전제하에)
 * 
 * 
 */

public class HashTableDoubleHashing < K extends SecondaryHash, V> 
			extends HashTableOpenAddressingBase<K, V> {

	public HashTableDoubleHashing() {
		super();
	}
	
	public HashTableDoubleHashing(int capacity) {
		super(capacity);
	}
	
	public HashTableDoubleHashing(int capacity, double loadFactor) {
		super(capacity,loadFactor);
	}
	
	
	@Override
	protected void setupProbing(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int probe(int x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void adjustCapacity() {
		// TODO Auto-generated method stub
		
	}

}
