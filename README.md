# 🧵 Java Threading & Concurrency Simulation Project

This project demonstrates various real-world simulations using Java multithreading and concurrency mechanisms, including:

- Restaurant reservation and statistics collection
- Bank account operations using both `synchronized` and `ReentrantLock`
- Thread-safe logging and flow control using `ExecutorService` and coordination utilities

---

## 📦 Modules & Scenarios

### 🍽️ Restaurant Simulation (With Statistics)

- 5 customer threads reserve tables, eat, and pay.
- 1 statistics thread waits for all customer threads and reports:
  - Total customers served
  - Total revenue collected
  - Total dining time
- Endpoint:
  ```
  GET /threads/executor/restaurant/simulation
  ```

### 🏦 Bank Simulation (With `synchronized`)

- Multiple threads perform `deposit`, `withdraw`, and `balanceCheck` on a shared `Account` using the `synchronized` keyword.
- Package: `task.bankExample`
- Endpoint:
  ```
  GET /threads/bank/start
  ```

### 🔐 Bank Simulation (With `ReentrantLock`)

- Same operations as above but using `Lock` for manual control.
- Package: `task.bankExampleWLock`
- Endpoint:
  ```
  GET /threads/bank/wlock/start
  ```

---

## 🧰 Technologies Used

- Java 17+
- Spring Boot
- `ExecutorService`, `CountDownLatch`
- `ReentrantLock`, `Condition`
- `AtomicInteger`, `AtomicLong`
- `synchronized`

---

## ▶️ How to Run

Start the Spring Boot app:

```bash
./gradlew bootRun
```

Then call any of the following endpoints:

| Scenario                  | Endpoint                                              |
|---------------------------|-------------------------------------------------------|
| Basic Threads             | GET `/threads/start`                                  |
| Bank (synchronized)       | GET `/threads/bank/start`                             |
| Bank (with Lock)          | GET `/threads/bank/wlock/start`                       |
| Restaurant (Executor)     | GET `/threads/executor/start`                         |
| Restaurant (Simulation)   | GET `/threads/executor/restaurant/simulation`         |

---

## 🧪 Sample Logs

```text
[pool-2-thread-1] [Customer-1] Table reserved.
[Customer-1] Food order placed.
[Customer-1] Customer is eating...
[Customer-1] Bill paid. Customer left the restaurant.

🧾 Restaurant Statistics:
👥 Total Customers: 5
💵 Total Revenue: 750 TL
🕒 Total Duration: 10043 ms
```

---

## 🧠 Educational Highlights

| Concept           | Class Example                         |
|------------------|----------------------------------------|
| `synchronized`    | `Account`                             |
| `ReentrantLock`   | `AccountWithLock`                     |
| Thread pools      | `ExecutorService` usage               |
| Coordination      | `CountDownLatch`, `Condition`         |
| Logging & Timing  | `RestaurantSimulationExecutorSerTask` |

---

## 📁 Project Structure

```
com.thread.example
├── controller
├── enums
├── model
│   ├── Account
│   ├── AccountWithLock
│   ├── RestaurantManager
│   ├── RestaurantStats
│   └── RestaurantTable
├── service
├── task
│   ├── bankExample
│   ├── bankExampleWLock
│   └── restaurantExample
```

---

## 📜 License

MIT License – for learning, teaching, and fun 🎓
