import java.security.MessageDigest;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConsistentHash {
//    will use treemap as hash ring to store node and data
    private final TreeMap<Integer, String> hashRing = new TreeMap<>();
    private final int noOfReplicas;
    private final MessageDigest md5;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public ConsistentHash(int noOfReplicas) {
        this.noOfReplicas = noOfReplicas;
        try{
            this.md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            throw new RuntimeException("Invalid Algorithm");

        }
    }

//    generate hash value

    private int generateHash(String key){
        md5.reset();
        md5.update(key.getBytes());

        byte[] digest = md5.digest();
// Take 4 bytes and convert to int (0 to 2^32-1) ~ 4.3 billon values sufficient
        return ((digest[0] & 0xFF) << 24) | ((digest[1] & 0xFF) << 16) |
                ((digest[2] & 0xFF) << 8) | (digest[3] & 0xFF);
    }

//    add node in ring

    public void addNode(String node){
        for(int i = 0; i < noOfReplicas; i++){
            int hashNumber = generateHash(node);
            hashRing.put(hashNumber, node);
        }
    }

//    delete node in ring

//    current rebalancing logic is not written left for future enhancement

    public void removeNode(String node){
        for(int i = 0;i < noOfReplicas; i++){
            int hashNumber = generateHash(node);
            hashRing.remove(hashNumber);
        }
    }


//    get node for specific hashKey

    public String getNode(String key){
        if(hashRing.isEmpty()){
            return null;
        }

        int hashKey = generateHash(key);
        SortedMap<Integer, String> tailMap = hashRing.tailMap(hashKey);
        int nodeHash = tailMap.isEmpty() ? hashRing.firstKey() : tailMap.firstKey();
        return hashRing.get(nodeHash);

    }


    public void printRing() {
        for (Map.Entry<Integer, String> entry : hashRing.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
