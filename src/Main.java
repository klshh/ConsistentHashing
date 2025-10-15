//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    void main() {
        ConsistentHash consistentHash = new ConsistentHash(3);
        consistentHash.addNode("Node1");
        consistentHash.addNode("Node2");
        consistentHash.addNode("Node3");

        consistentHash.printRing();

        System.out.println("\nKey 'user123' maps to: " + consistentHash.getNode("user123"));
        System.out.println("Key 'order456' maps to: " + consistentHash.getNode("order456"));

        consistentHash.removeNode("Node2");

        consistentHash.printRing();

        System.out.println("Key 'order456' maps to: " + consistentHash.getNode("order456"));
    }
}
