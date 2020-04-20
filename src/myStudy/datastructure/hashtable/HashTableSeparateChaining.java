package myStudy.datastructure.hashtable;

import java.util.*;

// K.hashCode() 한 값을 normalize 하면 bucket의 Index가 된다.(실질적인 hash값)
class Entry<K, V>{
		
		int hash;
		K key;
		V value;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
			this.hash = key.hashCode();
		}
		
		public boolean equals(Entry<K,V> other) {
			if( hash!=other.hash) return false;
			return key.equals(other.key);
		}
		
		@Override
		public String toString() {
			return key + " => " + value;
		}
}


public class HashTableSeparateChaining<K,V> implements Iterable<K> {

	private static final int DEFAULT_CAPACITY = 3;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	
	private double maxLoadFactor;
	private int capacity, threshold, size = 0;
	private LinkedList<Entry<K,V>>[] table;
	
	public HashTableSeparateChaining() {
		this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableSeparateChaining(int capacity) {
		this(capacity,DEFAULT_LOAD_FACTOR);
	}
	
	public HashTableSeparateChaining(int capacity, double maxloadFactor) {
		if( capacity < 0 ) throw new IllegalArgumentException("Invalid capacity");
		if( maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxloadFactor))
			throw new IllegalArgumentException("Invalid maxLoadFactor");
		this.maxLoadFactor = maxLoadFactor;
		this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
		threshold = (int) (this.capacity * maxLoadFactor);
		table = new LinkedList[this.capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}

	public int normalizeIndex(int keyHash) {
		//keyHash의 1인 지수만 남기고 나머지 0으로 해서 mod Capacity 하는 것
		//0x7FFFFFFF :: 2^31 -1, java에서 정수 최대값, 111111.. 
		return (keyHash & 0x7FFFFFFF) % capacity;
	}

	public void clear() {
		Arrays.fill(table, null);
		size=0;
	}
	
	public boolean containsKey(K key) {
		return hasKey(key);
	}
	
	private boolean hasKey(K key) {
		int bucketIndex = normalizeIndex(key.hashCode());
		return bucketSeekEntry(bucketIndex, key) != null;
	}
	
	public V put( K key, V value) {
		return insert(key, value);
	}
	
	private V insert(K key, V value) {
		if(key==null) throw new IllegalArgumentException("Null key");
		
		int bucketIndex = normalizeIndex(key.hashCode());
		Entry<K,V> newEntry = new Entry(key,value);
		return bucketInsertEntry(bucketIndex, newEntry);
	}
	
	public V get(K key) {
		if( key== null) return null;
		int bucketIndex = normalizeIndex(key.hashCode());
		Entry<K,V> entry = bucketSeekEntry(bucketIndex, key);
		if( entry!=null ) return entry.value;
		return null;
	}
	
	public V remove(K key) {
		if( key==null ) return null;
		int bucketIndex = normalizeIndex(key.hashCode());
		return bucketRemoveEntry(bucketIndex,key);
	}
	
	private V bucketInsertEntry(int bucketIndex, Entry<K,V> entry) {
		
		LinkedList<Entry<K,V>> bucket = table[bucketIndex];
		if(bucket ==null) table[bucketIndex] = bucket = new LinkedList<>();
		
		Entry<K,V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
		if( existentEntry==null) {
			bucket.add(entry);
			if(++size > threshold) resizeTable();
			return null; // no previous entry
			
		}else {
			//만약 해당 키값의 entry가 이미 존재하면 value는 바꿔주고 원래 value를 return;
			V oldVal = existentEntry.value;
			existentEntry.value = entry.value;
			return oldVal;
		}
	}
	
	private V bucketRemoveEntry(int bucketIndex, K key) {
		
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		if( entry == null) return null;
		else {
			LinkedList<Entry<K,V>> bucket = table[bucketIndex];
			bucket.remove(entry);
			size--;
			return entry.value;
		}
		
	}
	
	
	private Entry<K, V> bucketSeekEntry( int bucketIndex, K key){
		
		if(key == null) return null;
		
		List<Entry<K,V>> bucket = table[bucketIndex];
		for(Entry<K,V> entry : bucket) if(entry.key.equals(key)) return entry;
		return null;
	}
	
	
	
	private void resizeTable() {
		
		capacity *= 2;
		threshold = (int) (capacity * maxLoadFactor);
		
		LinkedList<Entry<K,V>>[] newTable = new LinkedList[capacity];
		
		for( int i=0; i<table.length; i++) {
			
			if( table[i] != null) {
				for( Entry entry : table[i]) {
					int bucketIndex = normalizeIndex(entry.key.hashCode());
					LinkedList<Entry<K,V>> bucket = newTable[bucketIndex];
					if( bucket==null) newTable[bucketIndex] = bucket = new LinkedList<>();
					bucket.add(entry);
				}
			}
			
			//LinkedList의 내용물 먼저 비워주고 array를 null로 바꾼다.
			table[i].clear();
			table[i] = null;
		}
		
		table = newTable;
	}
	


	public List<K> keys(){
		
		List<K> keys = new ArrayList<>(size());
		for( LinkedList<Entry<K,V>> bucket : table) {
			if( bucket !=null ) for( Entry<K,V> entry : bucket) keys.add(entry.key);
		}
		return keys;
	}
	
	public List<V> values(){
		
		List<V> values = new ArrayList<>(size());
		for( LinkedList<Entry<K,V>> bucket : table) {
			if( bucket!=null) for( Entry<K,V> entry : bucket) values.add(entry.value);
		}
		
		return values;
	}
	
	
	@Override
	public Iterator<K> iterator() {
		final int elementCount = size();
		
		return new Iterator<K>() {

			int bucketIndex = 0;
			Iterator<Entry<K,V>> bucketItr = (table[0] == null) ? null : table[0].iterator();
			
			@Override
			public boolean hasNext() {
				if( elementCount != size) throw new ConcurrentModificationException("table has changed");
				
				if( bucketItr ==null || !bucketItr.hasNext()) {
					
					while(++bucketIndex<capacity) {
						if(table[bucketIndex] !=null) {
							
							Iterator<Entry<K,V>> nextItr = table[bucketIndex].iterator();
							if(nextItr.hasNext()) {
								bucketItr = nextItr;
								break;
							}
						}						
					}
				}
				return bucketIndex < capacity;
			}

			@Override
			public K next() {
				return bucketItr.next().key;
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for( int i=0; i < capacity; i++) {
			if(table[i] == null) continue;			
			for(Entry<K,V> entry : table[i]) sb.append(entry + ", ");
												   // = endtry.toString()
		}
		sb.append("}");
		return sb.toString();
		
	}
	
}
