package myStudy.datastructure.hashtable;

import java.util.*;

public abstract class HashTableOpenAddressingBase<K,V> implements Iterable<K>{

	protected double loadFactor;
	protected int capacity, threshold, modificationCount;
	
	protected int usedBuckets, keyCount;
	
	protected K[] keys;
	protected V[] values;
	
	protected final K TOMBSTONE = (K) new Object();
	
	private static final int DEFAULT_CAPACITY = 7;
	private static final double DEFAULT_LOAD_FACTOR = 0.65;
	
	protected HashTableOpenAddressingBase() {
		this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
	}
	
	protected HashTableOpenAddressingBase(int capacity){
		this(capacity,DEFAULT_LOAD_FACTOR);
	}
	
	protected HashTableOpenAddressingBase(int capacity, double loadFactor) {
		if( capacity <= 0) throw new IllegalArgumentException("invalid capacity ");
		
		if( loadFactor <= 0 || Double.isNaN(loadFactor) || Double.isInfinite(loadFactor))
			throw new IllegalArgumentException("Invalid Load Factor");
		
		this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
		this.loadFactor = loadFactor;
		adjustCapacity();
		threshold = (int) (this.capacity * loadFactor);
		
		keys = (K[]) new Object[this.capacity];
		values = (V[]) new Object[this.capacity];
	}
	
	protected abstract void setupProbing(K key);
	protected abstract int probe(int x);
	
	protected abstract void adjustCapacity();
	
	protected void increaseCapacity() {
		capacity = (capacity * 2) + 1;
	}
	
	public void clear() {
		usedBuckets = keyCount = 0;
		for(int i=0; i<capacity; i++) {
			keys[i]= null;
			values[i] = null;
		}
		modificationCount++;
	}
	
	public int size() {
		return keyCount;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public boolean isEmpty() {
		return keyCount ==0;
	}
	
	public V put(K key, V value) {
		return insert(key, value);
	 }
	

	public boolean containsKey(K key) {
		return hasKey(key);
	}
	
	public List<K> keys(){
		List<K> hashtableKeys = new ArrayList<>(size());
	    for (int i = 0; i < capacity; i++)
	    	if (keys[i] != null && keys[i] != TOMBSTONE) hashtableKeys.add(keys[i]);
	    return hashtableKeys;
	}
	
	public List<V> values() {
		List<V> hashtableValues = new ArrayList<>(size());
		for (int i = 0; i < capacity; i++)
		    if (keys[i] != null && keys[i] != TOMBSTONE) hashtableValues.add(values[i]);
		return hashtableValues;
	}
	
	public void resizeTable() {
		increaseCapacity();
		adjustCapacity();
		
		threshold = (int) (capacity * loadFactor);
		
		K[] oldKeyTable = (K[]) new Object[capacity];
		V[] oldValueTable = (V[]) new Object[capacity];
		
		K[] keyTableTmp = keys;
		keys = oldKeyTable;
		oldKeyTable=keyTableTmp;
		
		V[] valueTableTmp = values;
		values = oldValueTable;
		oldValueTable = valueTableTmp;
		
		keyCount = usedBuckets = 0;
		
		for( int i=0; i < oldKeyTable.length; i++) {
			
			if( oldKeyTable[i]!=null && oldKeyTable[i]!=TOMBSTONE) {
				insert(oldKeyTable[i],oldValueTable[i]);
			}
			
			oldValueTable[i] =null;
			oldKeyTable[i] = null;
		}
		
		//이거 안해줘도 되는건가?
		oldKeyTable = null;
		oldValueTable = null;
		keyTableTmp = null;
		valueTableTmp = null;
		//
	}

	protected final int normalizeIndex(int keyHash) {
		return (keyHash & 0x7FFFFFFF) % capacity;
	}
	
	protected static final int gcd(int a, int b) {
		//a=fx, b=fy일 때( f는 최대공약수 , a > b) f?
		//a=fx = f(y+z),
		if(b==0) return a;
		return gcd(b,a%b);
	}
	
	public V insert (K key, V val) {
		if( key==null) throw new IllegalArgumentException("NUllKEY");
		if( usedBuckets >= threshold) resizeTable();
		
		setupProbing(key);
		int offset = normalizeIndex(key.hashCode());
		
		for( int i= offset, j=-1, x=1; ; i = probe(normalizeIndex(offset + probe(x++)))) {
			
			if( keys[i] == TOMBSTONE) {
				if(j==-1) j=i;
				
			} else if(keys[i] != null) {
				
				//key값이 같다면 값을 갱신 아니면 지나간다.
				if( keys[i].equals(key)) {
					
					V oldVal = values[i];
					
					if( j!=-1 ) {
						
						keys[j] = key;
						values[j] = val;
						keys[i] = TOMBSTONE;
						values[i] = null;
						
					} else {
						
						values[i] = val;
					
					}
					modificationCount++;
					return oldVal;
				}
				
			} else {
					
				if( j == -1) {
					usedBuckets++;
					keys[i] = key;
					values[i] = val;
					
				} else {
					keys[j] = key;
					values[j] = val;	
				}
				
				keyCount++;
				modificationCount++;
				return null;
				
			}
		}
	}
	
	public boolean hasKey(K key) {
		
		if (key == null) throw new IllegalArgumentException("Null key");
		
		setupProbing(key);
		final int offset = normalizeIndex(key.hashCode());
		
		for( int i=offset, j=-1, x=1 ; ; i=normalizeIndex(offset + probe(x++)) ) {
			
			if( keys[i] == TOMBSTONE) {
				if( j==-1) j=i;
				
			} else if( keys[i] != null) {
				
				if(keys[i].equals(key)) {
					
					if( j!=-1) {
						keys[j] = key;
						values[j] = values[i];
						keys[i] = TOMBSTONE;
						values[i] = null;
					}
					
					return true;
					
				} else return false;
			}

		}
	}
	   
	public V get(K key) {
		
		if (key == null) throw new IllegalArgumentException("Null key");
		setupProbing(key);
		
		int offset = normalizeIndex(key.hashCode());
		
		for( int i=offset, x=1, j=-1; ; i = normalizeIndex(offset + probe(x++))) {
			
			if (keys[i] == TOMBSTONE) {
				if(j==-1) j = i;
				
			} else if (keys[i]!=null) {
				
				if( keys[i].equals(key)) {
					
					V oldVal = values[i];
					
					if( j!=-1) {
						keys[j] = keys[i];
						values[j] = values[i];
						keys[i] = TOMBSTONE;
						values[i] = null;
						modificationCount++;
					}
					
					return oldVal;
					
				} else return null;
				
			}
		}

	}
	
	public V remove(K key) {
		
		if (key == null) throw new IllegalArgumentException("Null key");
		
		setupProbing(key);
		int offset = normalizeIndex(key.hashCode());
		
		for( int i=offset, x=1; ; i = normalizeIndex(offset + probe(x++))) {
			
			if(keys[i]==TOMBSTONE) continue;
			if(keys[i]==null) return null;
			if( keys[i].equals(key)) {
				
				modificationCount++;
				keyCount--;
				
				V oldVal = values[i];
				keys[i] = TOMBSTONE;
				values[i] = null;
				return oldVal;
			}
		}
	
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for( int i=0; i<capacity; i++) 
			if( keys[i] != null && keys[i] != TOMBSTONE) sb.append(keys[i] + "->" + values[i]);
		sb.append("}");
		
		return sb.toString();
	}
	
	public Iterator<K> iterator(){
		
		
		return new Iterator<K>() {
			
			int count = modificationCount;
			int at = 0;
			@Override
			public boolean hasNext() {
				
				if( count != modificationCount) throw new ConcurrentModificationException("Hash Table Has Been Changed!");
				
				while( at < capacity ) {
					
					if( keys[at]!=null && keys[at]!=TOMBSTONE) return true;
					else at++;
				}
				
				return false;
			}

			@Override
			public K next() {
				return keys[at];
			}
			
		};
	}
	
	
}
	

