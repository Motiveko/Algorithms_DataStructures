package myStudy.datastructure.hashtable;

/*
 * Linear Probing에서 probe(x)는 ax+b 이고 
 * HashTable의 index = (key.hashcode() + probe(x)) mod N 에서 N은 capacity 이다.
 * probe을 계속 해 나갈 때 index값에 cycle이 발생하지 않고 N가지의 경우의 수가 모두 나올 수 있게 하려면
 * a와 N 은 서로소여야 한다. a=1로 하는 방식이 geeksforgeeks방식이고 여기서는 a=17이라는 소수로 설정함
 * gcd 함수를 이용해서 최대공약수가 1이 될 때 까지 capacity를 증가시키는 방식으로 a와 N을 서로소로 만드는게 내용이다.
 */


public class HashTableLinearProbing<K,V> extends HashTableOpenAddressingBase<K, V> {

	
	private static final int LINEAR_CONSTANT=17;
	
	public HashTableLinearProbing() {
		super();
	}
	
	public HashTableLinearProbing (int capacity) {
		super(capacity); 
	}
	
	public HashTableLinearProbing(int capacity, double loadFactor) {
		super( capacity, loadFactor );
	}
	
	//Only for Double Hashing
	@Override
	protected void setupProbing(K key) {}

	@Override
	protected int probe(int x) {
		return LINEAR_CONSTANT * x;
	}

	@Override
	protected void adjustCapacity() {
		while( gcd(LINEAR_CONSTANT, capacity)!=1) capacity++;
	}

	
		
}
