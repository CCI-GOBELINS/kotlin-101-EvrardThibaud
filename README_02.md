# 🧩 Kotlin List Processing

## 🧩 Exercises

## Exercise 0: Test Setup

**Function:** `runtest`
Use/Adapt the function in ```Lesson2.kt``` if needed to run and verify your solutions.`

### **Exercise 1 — Immutable List**

**Function:** `ex1 create ImmutableList`

Create an immutable list of **5 integers** and return it.

---

### **Exercise 2 — Mutable List**

**Function:** `ex2 create MutableList`

Create a **mutable list** of 3 strings, then **add one more element** to it.  
Return the final list.

---

### **Exercise 3 — Filter Even**

**Function:** `ex3 filter EvenNumbers`

Create a list of numbers from **1 to 10**.  
Filter only the even numbers and return the result.
---

### **Exercise 4 — Filter and Map**

**Function:** `ex4 filter AndMapAges`

Given a list of ages:

1. Keep only ages ≥ 18
2. Transform each into a string like `"Adult: 25"`
3. Return the resulting list

---

### **Exercise 5 — Flatten Nested Lists**

**Function:** `ex5 flatten List`

Create a nested list such as `[[1, 2], [3, 4], [5]]` and flatten it.  
Return the flattened list.

---

### **Exercise 6 — FlatMap**

**Function:** `ex6 flat MapWords`

Create a list of phrases like `["Kotlin is fun", "I love lists"]`.  
Use `flatMap` and `split(" ")` to extract and return all words.

---

### **Exercise 7 — Eager Processing**

**Function:** `ex7 eager Processing`

Create a list from `1..1_000_000`.

1. Filter numbers divisible by 3
2. Map them to their squares
3. Return only the first 5 results
4. Measure the execution time

---

### **Exercise 8 — Lazy Processing**

**Function:** `ex8 lazy Processing`

1. Repeat Exercise 7, but use `.asSequence()` for **lazy evaluation**.
2. Return the first 5 results.
3. Measure the execution time.

**Extra:** You can measure performance with:

```kotlin
val start = System.currentTimeMillis()
// your code
val end = System.currentTimeMillis()
println("Time: ${end - start} ms")
```

### 🧠 **Exercise 9 — Chain multiple operations**

**Function:** `ex9 filter AndSortNames`
Given a list of names, filter those starting with 'A', convert to uppercase, sort alphabetically, and return the result.

