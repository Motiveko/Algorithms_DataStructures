package myStudy.datastructure.hashtable;

/*
 * Quadratic Probing에서는 cycle을 없애는 방법이 통상적으로 사용되는 방법을 쓴다.
 * 수학적으로 생각하려면 넘 어려운가보다.
 * 1. P(x) = x^2 , table size N is PrimeNumber && N>3
 * 2. P(x) = (x^2 + x)/2, table size power of two
 * 3. P(x) = (-1)*x^2, table size N is PrimeNumber && N ~~ 3 mod 4 (뭔지잘모르겠다이거는)
 * 
 * 여기서는 2번으로 구현한다
 */
public class HashTableQuadraticProbing<K,V> extends HashTableOpenAddressingBase<K, V> {

	
	public HashTableQuadraticProbing() {
		super();
	}
	
	public HashTableQuadraticProbing(int capacity) {
		super(capacity);
	}
	
	public HashTableQuadraticProbing(int capacity, double loadFactor) {
		super(capacity, loadFactor);
	}
	
	
	//only for Double Hashing
	@Override
	protected void setupProbing(K key){}

	@Override
	protected int probe(int x) {
		return (x*x + x) >> 1;
	}

	@Override
	protected void adjustCapacity() {
		//fiset코드는 쓸데없이 복잡하게 되있으므로 간소화시켰다.
		int pow = Integer.highestOneBit(capacity) * 2;
		if( pow == capacity) return;
		else capacity = pow << 1;
		
	}

}
