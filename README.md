# 🧩 Consistent Hashing in Java

This project implements a **thread-safe Consistent Hashing mechanism** in Java, useful for scalable systems such as distributed caches, databases, and sharded storage systems.

---

## 📌 Part 1: Basic Thread-Safe Consistent Hashing

### 🧠 What Is Consistent Hashing?

Consistent Hashing is a technique to **distribute keys across a dynamic set of nodes (servers)** such that:

- Adding/removing nodes doesn't require rehashing all keys.
- Keys are balanced fairly among nodes.
- It enables **horizontal scalability** with minimal disruption.

---

### 📦 Real-Life Analogy

> **Think of it like a parcel delivery system.**

You have:
- Warehouses (`Node1`, `Node2`, etc.)
- Parcels (`user123`, `order456`, etc.)
- A circular road (hash ring)
- A rule to drop each parcel at the closest warehouse in a clockwise direction.

Add/remove warehouses anytime — parcels automatically reroute with minimal reshuffling.

---

### 🧱 Features

- Thread-safe using `ReentrantReadWriteLock`
- Virtual nodes for even key distribution
- Supports adding/removing nodes dynamically
- Key-to-node mapping using consistent hashing
- Simulated per-node storage (`Map<String, Map<String, String>>`)

---

### 🛠️ Technologies Used

- Java 8+
- Java Concurrency (`ExecutorService`, `ReentrantReadWriteLock`)
- MD5 for hashing

---

### 🚀 How to Run

#### 1. Clone and Compile
```bash
git clone https://github.com/your-username/consistent-hashing-java.git
cd consistent-hashing-java
javac Main.java ConsistentHash.java
