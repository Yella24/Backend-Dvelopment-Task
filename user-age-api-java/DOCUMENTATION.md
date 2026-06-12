# Python — Beginner-Friendly Documentation

Each topic has the same three parts:

1. **What it is (simple words)** — explained like you're new to programming, with everyday analogies.
2. **Small example** — the smallest piece of code that shows the idea.
3. **Real-world example** — how it's used in a real project.

Tested on Python 3.10+. Companion runnable scripts live in `examples/`.

---

## Table of contents

1. [Setup & running code](#1-setup--running-code)
2. [Variables & data types](#2-variables--data-types)
3. [Numbers & math](#3-numbers--math)
4. [Strings & f-strings](#4-strings--f-strings)
5. [Booleans & truthiness](#5-booleans--truthiness)
6. [None & sentinels](#6-none--sentinels)
7. [Operators](#7-operators)
8. [Conditionals (`if/elif/else`)](#8-conditionals)
9. [Loops (`for`, `while`)](#9-loops)
10. [Lists](#10-lists)
11. [Tuples](#11-tuples)
12. [Sets](#12-sets)
13. [Dictionaries](#13-dictionaries)
14. [Comprehensions](#14-comprehensions)
15. [Functions](#15-functions)
16. [`*args` & `**kwargs`](#16-args--kwargs)
17. [Lambda & higher-order functions](#17-lambda--higher-order-functions)
18. [Modules & packages](#18-modules--packages)
19. [File I/O](#19-file-io)
20. [Pathlib](#20-pathlib)
21. [Exceptions](#21-exceptions)
22. [Classes & OOP](#22-classes--oop)
23. [Inheritance, `super`, MRO](#23-inheritance-super-mro)
24. [Encapsulation & properties](#24-encapsulation--properties)
25. [Polymorphism & duck typing](#25-polymorphism--duck-typing)
26. [Abstract base classes](#26-abstract-base-classes)
27. [Dunder methods](#27-dunder-methods)
28. [Dataclasses](#28-dataclasses)
29. [Enums](#29-enums)
30. [Type hints](#30-type-hints)
31. [Iterators](#31-iterators)
32. [Generators & `yield`](#32-generators--yield)
33. [Decorators](#33-decorators)
34. [Context managers](#34-context-managers)
35. [Pattern matching (3.10+)](#35-pattern-matching-310)
36. [Regular expressions](#36-regular-expressions)
37. [Date & time](#37-date--time)
38. [JSON](#38-json)
39. [CSV](#39-csv)
40. [HTTP requests](#40-http-requests)
41. [SQLite](#41-sqlite)
42. [SQLAlchemy ORM](#42-sqlalchemy-orm)
43. [Pydantic models](#43-pydantic-models)
44. [Subprocess](#44-subprocess)
45. [Threads](#45-threads)
46. [Asyncio](#46-asyncio)
47. [Multiprocessing](#47-multiprocessing)
48. [Logging](#48-logging)
49. [argparse CLIs](#49-argparse-clis)
50. [Environment variables](#50-environment-variables)
51. [Caching](#51-caching)
52. [Profiling](#52-profiling)
53. [Testing with pytest](#53-testing-with-pytest)
54. [Virtual environments](#54-virtual-environments)
55. [Linting & formatting](#55-linting--formatting)
56. [Pandas](#56-pandas)
57. [NumPy](#57-numpy)
58. [Plotting](#58-plotting)
59. [Web scraping](#59-web-scraping)
60. [Email sending](#60-email-sending)
61. [Scheduling jobs](#61-scheduling-jobs)
62. [Common pitfalls](#62-common-pitfalls)

---

## 1. Setup & running code

### What it is (simple words)

Python is a language. To run a Python program, you need an app called the **Python interpreter**. Think of it like a translator: you write English-like instructions in a `.py` file, and the interpreter translates and runs them on your computer.

When you type `python myfile.py` in a terminal, you're saying: "Hey Python, please read this file and do what it says."

### Small example

```bash
python --version           # check if Python is installed
python myscript.py         # run a script
python -c "print(1 + 1)"   # run a one-line program
```

### Real-world example

You want to share files in your `Downloads` folder with a colleague on the same Wi-Fi. Python has a built-in mini web server. Open a terminal in that folder and run:

```bash
python -m http.server 8000
```

Now your colleague can open `http://your-ip:8000` in a browser and download files. No app to install, no setup.

---

## 2. Variables & data types

### What it is (simple words)

A **variable** is just a name (label) you give to a value, so you can use it later.

```
name = "Mahesh"
```

Here `name` is the variable (the label), and `"Mahesh"` is the value (what's written on the box). Later when you say `print(name)`, Python looks up the box called `name` and prints what's inside.

A **data type** tells Python what *kind* of value is in the box:

- **`int`** — whole numbers like `5`, `100`, `-3`.
- **`float`** — decimal numbers like `3.14`, `1.5`.
- **`str`** — text like `"Hello"`, `"Mahesh"`.
- **`bool`** — only `True` or `False`.
- **`list`** — many values in order, like `[10, 20, 30]`.
- **`dict`** — labelled pairs, like `{"name": "Mahesh", "age": 25}`.
- **`None`** — means "nothing here yet".

You don't have to write the type. Python figures it out from the value. But you *can* hint at it:

```python
age: int = 25      # the ": int" is a hint that this is a whole number
```

### Small example

```python
name = "Mahesh"          # variable: name, value: "Mahesh", type: str
age = 25                 # type: int
height = 5.9             # type: float
is_student = True        # type: bool
hobbies = ["chess", "tennis"]  # type: list
profile = {"city": "Hyderabad", "pincode": 500001}  # type: dict
nothing_yet = None       # type: NoneType

print(name, age, hobbies)   # Mahesh 25 ['chess', 'tennis']
```

You can change what's inside the box anytime:

```python
score = 10
score = 20         # same label, new value
score = "twenty"   # type can even change in Python
```

### Real-world example

Storing a customer record in a shop's app:

```python
customer_id = 1001
customer_name = "Mahesh"
balance = 4500.50
is_premium = True
favourite_items = ["soap", "shampoo", "biscuits"]

print(f"{customer_name} (ID {customer_id}) has ₹{balance}")
# Mahesh (ID 1001) has ₹4500.5
```

Each variable holds one piece of information about the customer. When the customer buys something, you update `balance`. When they upgrade their plan, you change `is_premium` to `False`. The variables let you *remember* the customer's data while the program runs.

---

## 3. Numbers & math

### What it is (simple words)

Python is basically a fancy calculator. **Numbers** come in two main flavours:

- **`int`** — whole numbers (1, 2, 100, -50). No size limit; you can do `10 ** 100` and Python won't blink.
- **`float`** — decimals (3.14, 0.5, -2.7).

You use the normal symbols you learned in school: `+ - * /`. Python adds a few more:

| Symbol | Meaning            | Example       |
| ------ | ------------------ | ------------- |
| `+`    | add                | `2 + 3 → 5`   |
| `-`    | subtract           | `5 - 1 → 4`   |
| `*`    | multiply           | `4 * 2 → 8`   |
| `/`    | divide (decimal)   | `10 / 3 → 3.33` |
| `//`   | divide (whole part)| `10 // 3 → 3` |
| `%`    | remainder          | `10 % 3 → 1`  |
| `**`   | power              | `2 ** 10 → 1024` |

If you need square roots, π, log, etc., import the **`math`** module:

```python
import math
math.sqrt(25)   # 5.0
math.pi         # 3.14159...
```

⚠️ For money, use `Decimal` instead of `float`. Floats can have tiny rounding errors (`0.1 + 0.2 == 0.30000000000000004`), which matters in banking.

### Small example

```python
total = 250 + 75
print(total)         # 325

discount = total * 0.10
print(discount)      # 32.5

print(total - discount)   # 292.5

print(17 // 5, 17 % 5)    # 3 2  →  17 = 5×3 + 2
```

### Real-world example

Calculating a customer's bill at a shop, including 18% GST:

```python
def bill_total(items, gst_rate=0.18):
    subtotal = 0
    for item in items:
        subtotal = subtotal + item["price"] * item["qty"]
    gst = subtotal * gst_rate
    grand_total = subtotal + gst
    return round(grand_total, 2)

cart = [
    {"name": "Soap",     "price": 50, "qty": 3},
    {"name": "Shampoo",  "price": 200, "qty": 1},
]

print(bill_total(cart))   # 413.0
```

For real money, banks use:

```python
from decimal import Decimal
amount = Decimal("100.50") + Decimal("0.20")   # exact, no rounding errors
```

---

## 4. Strings & f-strings

### What it is (simple words)

A **string** is text. Anything you put inside quotes — `"hi"`, `'Mahesh'`, `"500"` — is a string. Even `"500"` is a string, not the number 500.

Strings are like a string of beads — each character is a bead. You can:

- glue strings together with `+`: `"Hello " + "Mahesh"` → `"Hello Mahesh"`.
- ask the length: `len("Mahesh")` → `6`.
- pick parts: `"Mahesh"[0]` → `"M"` (zero-based, like seat numbers starting from 0).

An **f-string** is a string starting with `f"..."` that lets you drop variables and expressions inside `{ }`. It's the easiest way to build a sentence with values mixed in:

```python
name = "Mahesh"
age = 25
print(f"{name} is {age} years old")   # Mahesh is 25 years old
```

You can also format the value:

| Spec       | Meaning                                  |
| ---------- | ---------------------------------------- |
| `:.2f`     | float, 2 decimals → `3.14`               |
| `:,`       | thousands separator → `1,000,000`        |
| `:>10`     | right-aligned, width 10                  |
| `:<10`     | left-aligned, width 10                   |
| `:0>5`     | pad with zeros, width 5 → `00042`        |

### Small example

```python
name = "Mahesh"
age = 25
balance = 4567.5

print(f"{name} is {age}, balance is ₹{balance:,.2f}")
# Mahesh is 25, balance is ₹4,567.50

# Useful methods
print("  hi  ".strip())          # 'hi' (removes spaces)
print("apple,banana".split(","))  # ['apple', 'banana']
print("-".join(["a", "b", "c"]))  # 'a-b-c'
print("hello".upper())             # 'HELLO'
print("HELLO".lower())             # 'hello'
print("Hello".replace("l", "L"))   # 'HeLLo'
```

### Real-world example

Generating a printable invoice line for each item:

```python
def invoice_line(item_no, name, qty, price):
    line_total = qty * price
    return f"{item_no:>3}  {name:<20}  {qty:>3}  ₹{price:>8,.2f}  ₹{line_total:>10,.2f}"

print(invoice_line(1, "Soap", 3, 50))
print(invoice_line(2, "Premium Shampoo", 1, 200))

#   1  Soap                    3  ₹   50.00  ₹     150.00
#   2  Premium Shampoo         1  ₹  200.00  ₹     200.00
```

The `:<20` keeps names left-aligned in a 20-character column so everything lines up neatly.

---

## 5. Booleans & truthiness

### What it is (simple words)

A **boolean** is a yes/no value. In Python it's written `True` or `False` (with a capital letter).

You get booleans whenever you ask Python a question:

```python
5 > 3        # True   (is 5 greater than 3? yes)
"a" == "b"   # False  (is "a" equal to "b"? no)
```

**Truthiness** is a shortcut: Python treats certain "empty" values as if they were `False`:

- `0`, `0.0` → falsy
- `""` (empty string) → falsy
- `[]`, `{}`, `set()` (empty collections) → falsy
- `None` → falsy
- everything else → truthy

This means you can write:

```python
items = []
if items:
    print("got items")
else:
    print("empty list")     # this runs
```

instead of `if len(items) > 0:`. Shorter, reads like English.

### Small example

```python
is_logged_in = True
is_admin = False

if is_logged_in:
    print("Welcome!")

# Truthiness
name = ""
if name:
    print(f"hi {name}")
else:
    print("Please enter your name")    # this runs
```

### Real-world example

Show a friendly fallback when a customer hasn't set a nickname yet:

```python
def display_name(customer):
    return customer.get("nickname") or customer.get("name") or "Guest"

print(display_name({"name": "Mahesh"}))                       # Mahesh
print(display_name({"nickname": "M-ji", "name": "Mahesh"}))   # M-ji
print(display_name({}))                                       # Guest
```

The trick: `a or b` returns `a` if `a` is truthy, otherwise `b`. So if `nickname` is missing or empty, Python tries `name`. If both are empty, it falls back to `"Guest"`.

---

## 6. None & sentinels

### What it is (simple words)

`None` is Python's way of saying "nothing here". It's not zero, not an empty string — it's the absence of a value.

You see it in three common situations:

1. A function that doesn't `return` anything actually returns `None`.
2. A variable you've created but haven't filled yet.
3. A "no value yet" placeholder for a future answer.

To check for `None`, always use `is None` (not `== None`) — it's the standard way:

```python
if user is None:
    print("no user")
```

A **sentinel** is a unique custom marker for "missing" — useful when `None` itself is a valid value and you need to tell apart "user passed None on purpose" from "user passed nothing".

### Small example

```python
result = None      # we don't know yet
print(result)      # None

def find_user(uid):
    if uid == 1:
        return {"name": "Mahesh"}
    return None    # not found

user = find_user(99)
if user is None:
    print("User not found")
else:
    print(user["name"])
```

### Real-world example

A profile screen where "no email set" is different from "user explicitly cleared their email":

```python
profile_a = {"email": None}                  # they cleared it
profile_b = {}                                # never set
profile_c = {"email": "mahesh@example.com"}  # has a real value

def email_status(profile):
    if "email" not in profile:
        return "Email never provided"
    if profile["email"] is None:
        return "Email was cleared by user"
    return f"Email is {profile['email']}"

print(email_status(profile_a))  # Email was cleared by user
print(email_status(profile_b))  # Email never provided
print(email_status(profile_c))  # Email is mahesh@example.com
```

---

## 7. Operators

### What it is (simple words)

**Operators** are the symbols Python uses to compare, combine, or transform values. You already know `+ - * /`. There are three more groups worth knowing:

- **Comparison**: `==` (equal), `!=` (not equal), `<`, `>`, `<=`, `>=`. They return `True` or `False`.
- **Logical**: `and`, `or`, `not`. They combine yes/no questions.
- **Membership**: `in`, `not in`. Check if something is inside a list/string/dict.

```python
3 == 3            # True
3 != 4            # True
"py" in "python"  # True
True and False    # False
True or False     # True
not True          # False
```

A handy Python feature: you can chain comparisons:

```python
0 < age < 100   # same as: 0 < age and age < 100
```

### Small example

```python
age = 25
is_adult = age >= 18              # True
is_senior = age >= 60             # False

is_working_age = 18 <= age < 60   # True (chained)

city = "Hyderabad"
in_telangana = city in ["Hyderabad", "Warangal", "Khammam"]  # True
```

### Real-world example

Eligibility check for a movie ticket discount:

```python
def discount_pct(age, has_membership):
    if age < 12 or age >= 60:
        return 50          # kids and seniors: 50% off
    if has_membership:
        return 20          # members: 20% off
    return 0

print(discount_pct(8, False))    # 50
print(discount_pct(35, True))    # 20
print(discount_pct(35, False))   # 0
```

---

## 8. Conditionals

### What it is (simple words)

A **conditional** is a "if this, do that" statement. In Python:

```python
if condition:
    do_something()
elif another_condition:
    do_something_else()
else:
    do_default()
```

- `if` is the first question.
- `elif` (= "else if") is a follow-up question if the first was false.
- `else` is the catch-all.

Indentation matters! The lines under `if` must be indented (4 spaces is standard). That's how Python knows what belongs to the `if`.

You can also write a tiny one-liner:

```python
status = "adult" if age >= 18 else "minor"
```

### Small example

```python
score = 78

if score >= 90:
    grade = "A"
elif score >= 75:
    grade = "B"
elif score >= 50:
    grade = "C"
else:
    grade = "F"

print(grade)   # B
```

### Real-world example

Send different messages to a customer based on their order status:

```python
def order_message(status):
    if status == "placed":
        return "We received your order, preparing it now."
    elif status == "shipped":
        return "Your order is on the way!"
    elif status == "delivered":
        return "Hope you love your purchase!"
    elif status == "cancelled":
        return "Your order has been cancelled and refund is processed."
    else:
        return "Order status unknown. Contact support."

print(order_message("shipped"))
# Your order is on the way!
```

---

## 9. Loops

### What it is (simple words)

A **loop** repeats some code many times. Python has two kinds:

- **`for`** — "for each item in this group, do this". Use when you have a list/string/range.
- **`while`** — "keep doing this until the condition is false". Use when you don't know how many times yet.

Two helper words:

- **`break`** — exit the loop right now.
- **`continue`** — skip the rest of this round and go to the next item.

`range(n)` gives you `0, 1, 2, ..., n-1` — great for "do this N times".

### Small example

```python
# for loop over a list
fruits = ["apple", "banana", "mango"]
for fruit in fruits:
    print(fruit)

# for loop with range
for i in range(5):
    print(i)        # 0,1,2,3,4

# while loop
count = 3
while count > 0:
    print(count)
    count = count - 1     # 3, 2, 1

# break and continue
for n in [1, 2, 3, 4, 5]:
    if n == 3:
        continue   # skip 3
    if n == 5:
        break      # stop at 5
    print(n)       # prints 1, 2, 4
```

### Real-world example

Sending invoice emails to a list of customers, but skipping anyone without an email:

```python
customers = [
    {"name": "Mahesh", "email": "mahesh@example.com"},
    {"name": "Anita",  "email": ""},                    # missing
    {"name": "Ravi",   "email": "ravi@example.com"},
]

for c in customers:
    if not c["email"]:
        print(f"skipping {c['name']} — no email")
        continue
    print(f"sending invoice to {c['name']} at {c['email']}")
```

Output:

```
sending invoice to Mahesh at mahesh@example.com
skipping Anita — no email
sending invoice to Ravi at ravi@example.com
```

---

## 10. Lists

### What it is (simple words)

A **list** is an ordered collection of items, like a shopping list:

```python
shopping = ["milk", "bread", "eggs"]
```

Three things to know:

1. **Order matters.** `shopping[0]` is `"milk"` (Python counts from 0).
2. **You can change a list** — add, remove, replace items.
3. **Mixed types are allowed**, but usually you'll have one kind.

Common things you do with a list:

```python
shopping.append("butter")    # add to the end
shopping.insert(0, "salt")   # add at the start
shopping.remove("bread")     # remove first match
shopping[0] = "rice"         # replace the first item
len(shopping)                # how many items
"milk" in shopping           # is milk in the list?
shopping.sort()              # sort in place
```

A **slice** picks a range: `shopping[1:3]` means "items at index 1 and 2" (the end is exclusive).

### Small example

```python
marks = [82, 75, 90, 67]

print(marks[0])         # 82  (first)
print(marks[-1])        # 67  (last; negative counts from end)
print(marks[1:3])       # [75, 90]
print(len(marks))       # 4
print(sum(marks) / len(marks))   # average: 78.5

marks.append(88)
print(marks)            # [82, 75, 90, 67, 88]
```

### Real-world example

Pulling the top 3 highest-spending customers from a list:

```python
customers = [
    {"name": "Mahesh", "spend": 1200},
    {"name": "Anita",  "spend": 4500},
    {"name": "Ravi",   "spend": 800},
    {"name": "Sita",   "spend": 3000},
]

# sort by spend, biggest first, take first 3
top3 = sorted(customers, key=lambda c: c["spend"], reverse=True)[:3]

for c in top3:
    print(f"{c['name']:<10} ₹{c['spend']:,}")

# Anita      ₹4,500
# Sita       ₹3,000
# Mahesh     ₹1,200
```

---

## 11. Tuples

### What it is (simple words)

A **tuple** is like a list, but **you can't change it** once created. Think of it as a sealed envelope: you can read what's inside, but not add or remove items.

```python
coords = (10, 20)
```

Why use tuples?

1. **Safety** — if a value should never change (a date, a coordinate, an ID pair), use a tuple.
2. **Speed** — slightly faster than lists.
3. **Dict keys** — tuples can be dictionary keys; lists can't.

A common Python trick: a function can "return multiple values" using a tuple:

```python
def divide(a, b):
    return a // b, a % b      # returns a tuple (quotient, remainder)

q, r = divide(17, 5)          # q=3, r=2
```

This is called **tuple unpacking**.

### Small example

```python
point = (5, 10)
print(point[0], point[1])    # 5 10

# point[0] = 99   # ERROR: tuples can't be changed

# Unpacking
name, age, city = ("Mahesh", 25, "Hyderabad")
print(name)   # Mahesh
print(age)    # 25
```

### Real-world example

Returning multiple stats about a class's marks in one function:

```python
def stats(marks):
    total = sum(marks)
    average = total / len(marks)
    highest = max(marks)
    lowest = min(marks)
    return total, average, highest, lowest

total, avg, hi, lo = stats([82, 75, 90, 67, 88])
print(f"Total={total}, Avg={avg:.1f}, High={hi}, Low={lo}")
# Total=402, Avg=80.4, High=90, Low=67
```

---

## 12. Sets

### What it is (simple words)

A **set** is a collection where:

1. **Duplicates are removed automatically.**
2. **Order doesn't matter.**

If you put the same value in twice, only one stays. It's like a guest list where you don't care about order, but you don't want anyone listed twice.

```python
emails = {"a@x.com", "b@x.com", "a@x.com"}
print(emails)   # {'a@x.com', 'b@x.com'}  ← only 2 items
```

Sets are super fast for one question: **"is X in here?"**

```python
"a@x.com" in emails    # almost instant, even for millions of items
```

You can do math with sets:

| Operation | Symbol | Meaning                |
| --------- | ------ | ---------------------- |
| Union     | `|`    | items in either set    |
| Intersect | `&`    | items in both sets     |
| Difference| `-`    | in first, not in second|

### Small example

```python
fruits = {"apple", "banana", "apple", "mango"}
print(fruits)               # {'apple', 'banana', 'mango'}  -- duplicates gone

fruits.add("orange")
fruits.discard("banana")
print(fruits)

a = {1, 2, 3}
b = {2, 3, 4}
print(a & b)    # {2, 3}     (in both)
print(a | b)    # {1,2,3,4}  (in either)
print(a - b)    # {1}        (in a, not in b)
```

### Real-world example

Find customers in the VIP list **and** the Hyderabad list (people who are both):

```python
vip_customers = {"mahesh@x.com", "anita@x.com", "ravi@x.com"}
hyd_customers = {"anita@x.com", "ravi@x.com", "sita@x.com"}

vip_in_hyd = vip_customers & hyd_customers
print(vip_in_hyd)          # {'anita@x.com', 'ravi@x.com'}

# VIPs we should target who aren't in Hyderabad
vips_outside_hyd = vip_customers - hyd_customers
print(vips_outside_hyd)    # {'mahesh@x.com'}
```

Same problem with two big lists would take many seconds; with sets it's instant.

---

## 13. Dictionaries

### What it is (simple words)

A **dictionary** (or **dict**) is a collection of **key → value** pairs. Like a real dictionary: each word (key) maps to a meaning (value).

```python
person = {
    "name": "Mahesh",
    "age": 25,
    "city": "Hyderabad",
}
```

To get a value, you use the key:

```python
person["name"]       # 'Mahesh'
```

The safe way (no error if key is missing):

```python
person.get("phone")             # None
person.get("phone", "unknown")  # 'unknown'
```

You can add, change, delete entries:

```python
person["email"] = "m@x.com"   # add
person["age"] = 26            # update
del person["city"]            # remove
```

Dicts are Python's most-used data structure. Almost every JSON response, config file, or database row becomes a dict.

### Small example

```python
student = {"name": "Mahesh", "marks": 88}

print(student["name"])         # Mahesh
print(student.get("grade"))    # None  (no error)

student["grade"] = "A"
student["marks"] = 92

for key, value in student.items():
    print(f"{key}: {value}")
# name: Mahesh
# marks: 92
# grade: A
```

### Real-world example

Counting how many orders each customer placed:

```python
orders = [
    {"customer": "Mahesh", "amount": 200},
    {"customer": "Anita",  "amount": 100},
    {"customer": "Mahesh", "amount": 300},
    {"customer": "Ravi",   "amount": 50},
    {"customer": "Anita",  "amount": 250},
]

order_counts = {}
total_spent = {}

for o in orders:
    name = o["customer"]
    order_counts[name] = order_counts.get(name, 0) + 1
    total_spent[name]  = total_spent.get(name, 0) + o["amount"]

print(order_counts)   # {'Mahesh': 2, 'Anita': 2, 'Ravi': 1}
print(total_spent)    # {'Mahesh': 500, 'Anita': 350, 'Ravi': 50}
```

---

## 14. Comprehensions

### What it is (simple words)

A **comprehension** is a one-liner that builds a new list, set, or dict from another collection. It's a shortcut for the pattern:

```python
result = []
for item in source:
    if condition:
        result.append(transformed)
```

In comprehension form:

```python
result = [transformed for item in source if condition]
```

Read it left-to-right as: "give me `transformed`, for each `item` in `source`, where `condition` is true".

You can also build a dict or set the same way:

```python
{x: x*x for x in range(5)}    # dict comprehension
{c for c in "hello"}          # set comprehension
```

### Small example

```python
numbers = [1, 2, 3, 4, 5, 6]

# squares
squares = [n * n for n in numbers]
print(squares)               # [1, 4, 9, 16, 25, 36]

# only the even ones
evens = [n for n in numbers if n % 2 == 0]
print(evens)                 # [2, 4, 6]

# squared evens
squared_evens = [n*n for n in numbers if n % 2 == 0]
print(squared_evens)         # [4, 16, 36]

# dict
square_map = {n: n*n for n in numbers}
print(square_map)            # {1:1, 2:4, 3:9, 4:16, 5:25, 6:36}
```

### Real-world example

From a list of customers, get just the emails of the active ones:

```python
customers = [
    {"name": "Mahesh", "email": "m@x.com",  "active": True},
    {"name": "Anita",  "email": "a@x.com",  "active": False},
    {"name": "Ravi",   "email": "r@x.com",  "active": True},
]

active_emails = [c["email"] for c in customers if c["active"]]
print(active_emails)    # ['m@x.com', 'r@x.com']

# Same data, indexed by email for quick lookup
by_email = {c["email"]: c for c in customers}
print(by_email["m@x.com"]["name"])     # 'Mahesh'
```

---

## 15. Functions

### What it is (simple words)

A **function** is a named block of code that does a job. You give it some inputs (called **parameters**), it does work, and it gives back a **return value**.

Why bother? So you don't repeat the same code in many places. Write it once, give it a name, and call it whenever needed.

```python
def greet(name):
    return f"Hello, {name}!"

print(greet("Mahesh"))   # Hello, Mahesh!
print(greet("Anita"))    # Hello, Anita!
```

- `def` starts a function definition.
- `greet` is the name.
- `name` is the parameter.
- `return` sends the result back.

You can give parameters default values too:

```python
def greet(name, greeting="Hello"):
    return f"{greeting}, {name}!"

greet("Mahesh")              # 'Hello, Mahesh!'
greet("Mahesh", "Welcome")   # 'Welcome, Mahesh!'
```

### Small example

```python
def square(n):
    return n * n

print(square(5))   # 25

def is_even(n):
    return n % 2 == 0

print(is_even(4))   # True
print(is_even(7))   # False
```

### Real-world example

A reusable price calculator used throughout an e-commerce app:

```python
def total_with_tax(price, qty, tax_pct=18):
    """Return the total price including tax (rounded to 2 decimals)."""
    subtotal = price * qty
    tax_amount = subtotal * (tax_pct / 100)
    return round(subtotal + tax_amount, 2)


# In the cart:
print(total_with_tax(100, 3))         # 354.0
print(total_with_tax(50, 2, tax_pct=5))  # 105.0  (lower tax for some items)
```

You can now call `total_with_tax(...)` anywhere — checkout, refund, invoice — without rewriting the formula.

---

## 16. `*args` & `**kwargs`

### What it is (simple words)

Sometimes you don't know how many arguments someone will pass to your function. Python has two special tricks:

- **`*args`** — collects extra positional arguments into a **tuple**.
- **`**kwargs`** — collects extra keyword arguments into a **dict**.

The `*` and `**` are what matter. The names `args` and `kwargs` are just convention — you could call them `*things` and `**options`.

```python
def show(*args, **kwargs):
    print("positional:", args)     # tuple
    print("keyword:   ", kwargs)   # dict

show(1, 2, 3, name="Mahesh", age=25)
# positional: (1, 2, 3)
# keyword:    {'name': 'Mahesh', 'age': 25}
```

You can also "spread" a list into a function call:

```python
nums = [1, 2, 3]
print(*nums)   # same as print(1, 2, 3)
```

### Small example

```python
def sum_all(*numbers):
    total = 0
    for n in numbers:
        total += n
    return total

print(sum_all(1, 2, 3))           # 6
print(sum_all(10, 20, 30, 40))    # 100

def make_user(**fields):
    return fields

user = make_user(name="Mahesh", age=25, city="Hyderabad")
print(user)
# {'name': 'Mahesh', 'age': 25, 'city': 'Hyderabad'}
```

### Real-world example

A logger helper that lets callers attach any extra fields:

```python
import logging
logging.basicConfig(level=logging.INFO)

def log_event(event_type, **fields):
    logging.info(f"event={event_type} fields={fields}")

log_event("login", user_id=42, ip="10.0.0.1")
log_event("purchase", order_id=99, amount=4500, currency="INR")
```

Whoever uses `log_event` doesn't have to fit a fixed parameter list. They just attach whatever extra data they want, and `**fields` captures it all.

---

## 17. Lambda & higher-order functions

### What it is (simple words)

A **lambda** is a tiny one-line function with no name. It's useful when you need a quick function just once.

```python
double = lambda x: x * 2
print(double(5))    # 10
```

It's the same as:

```python
def double(x):
    return x * 2
```

A **higher-order function** is a function that takes another function as an argument. The most common ones in real code are:

- `sorted(items, key=fn)` — sort using `fn(item)` as the comparison key.
- `max(items, key=fn)`, `min(items, key=fn)` — same idea.
- `map(fn, items)` — apply `fn` to every item (returns an iterator).
- `filter(fn, items)` — keep items where `fn(item)` is true.

### Small example

```python
# Sort words by length, shortest first
words = ["banana", "fig", "apple"]
sorted_by_len = sorted(words, key=lambda w: len(w))
print(sorted_by_len)   # ['fig', 'apple', 'banana']

# Transform a list with map
nums = [1, 2, 3, 4]
doubled = list(map(lambda n: n * 2, nums))
print(doubled)         # [2, 4, 6, 8]

# Filter with filter
evens = list(filter(lambda n: n % 2 == 0, nums))
print(evens)           # [2, 4]
```

### Real-world example

Sort orders by total (highest first), then by date (oldest first) when totals tie:

```python
from datetime import date

orders = [
    {"id": 1, "total": 500, "date": date(2025, 1, 5)},
    {"id": 2, "total": 800, "date": date(2025, 1, 3)},
    {"id": 3, "total": 500, "date": date(2025, 1, 1)},
]

orders.sort(key=lambda o: (-o["total"], o["date"]))
for o in orders:
    print(o)
# {'id': 2, 'total': 800, 'date': date(2025, 1, 3)}
# {'id': 3, 'total': 500, 'date': date(2025, 1, 1)}
# {'id': 1, 'total': 500, 'date': date(2025, 1, 5)}
```

The lambda returns a tuple `(-total, date)`. Python sorts tuples element-by-element, so totals come first (negated to flip the order), then date.

---

## 18. Modules & packages

### What it is (simple words)

Once your code gets bigger than a single file, you split it into many files for sanity.

- A **module** is a single `.py` file. Anything you `def` or assign in it becomes available when you `import` that file.
- A **package** is a folder of related modules. To make a folder a package, just put an empty file called `__init__.py` in it.

To use code from another file:

```python
# in math_utils.py
def add(a, b):
    return a + b

# in main.py
from math_utils import add
print(add(2, 3))    # 5
```

Python comes with hundreds of useful modules built in (`math`, `datetime`, `json`, `os`, ...). You can also install thousands more from the internet using `pip install`.

### Small example

Folder layout:
```
project/
├── helpers.py
└── main.py
```

```python
# helpers.py
def shout(text):
    return text.upper() + "!!"

# main.py
from helpers import shout
print(shout("hello"))   # HELLO!!
```

### Real-world example

Splitting a small e-commerce app:

```
shop/
├── __init__.py
├── cart.py        # Cart class, add_item, remove_item, total
├── pricing.py     # discounts and taxes
└── checkout.py    # uses cart + pricing
```

```python
# shop/checkout.py
from .cart import Cart
from .pricing import apply_discount

def checkout(cart, coupon=None):
    total = cart.total()
    if coupon:
        total = apply_discount(total, coupon)
    return total
```

Now `cart.py` has only cart-related code, `pricing.py` has only pricing code, and `checkout.py` brings them together. If a bug happens in pricing, you know exactly which file to open.

---

## 19. File I/O

### What it is (simple words)

To read or write a file from disk, you **open** it, do something with it, then **close** it. Python has a clean way to do this with the `with` keyword — it auto-closes the file even if something goes wrong.

```python
with open("note.txt", "w") as f:
    f.write("Hello, file!")

with open("note.txt", "r") as f:
    contents = f.read()
print(contents)   # Hello, file!
```

The second argument is the **mode**:

- `"r"` — read (default)
- `"w"` — write (overwrites!)
- `"a"` — append (adds to end)
- `"b"` — binary (for images, PDFs etc.)

Always pass `encoding="utf-8"` for text files. Without it, Windows uses an old encoding that breaks on Hindi/Telugu/emojis.

### Small example

```python
# Write
with open("greetings.txt", "w", encoding="utf-8") as f:
    f.write("Namaste!\n")
    f.write("Vanakkam!\n")

# Read all at once
with open("greetings.txt", encoding="utf-8") as f:
    print(f.read())

# Read line by line (good for big files)
with open("greetings.txt", encoding="utf-8") as f:
    for line in f:
        print(line.strip())
```

### Real-world example

A simple audit logger that adds one line every time a user does something important:

```python
from datetime import datetime

def log_action(user, action):
    now = datetime.now().isoformat(timespec="seconds")
    line = f"{now}  {user}  {action}\n"
    with open("audit.log", "a", encoding="utf-8") as f:
        f.write(line)

log_action("mahesh", "logged_in")
log_action("mahesh", "viewed_invoice id=42")
log_action("anita",  "changed_password")
```

The file `audit.log` will accumulate one line per action, useful when investigating who did what.

---

## 20. Pathlib

### What it is (simple words)

`pathlib.Path` is the modern, easy way to work with files and folders. Instead of building paths with strings, you build them with `Path` objects, which know how to:

- join paths with `/` (no more worrying about `\` vs `/`).
- check if a file exists.
- read or write a whole file in one line.
- find files matching a pattern.

```python
from pathlib import Path

p = Path("data") / "users.csv"
p.parent.mkdir(parents=True, exist_ok=True)   # create "data" folder if needed
p.write_text("name,age\nMahesh,25\n", encoding="utf-8")

print(p.read_text(encoding="utf-8"))
print(p.exists())     # True
print(p.suffix)       # '.csv'
print(p.name)         # 'users.csv'
```

It works the same on Windows, macOS, and Linux.

### Small example

```python
from pathlib import Path

# Make a folder structure
Path("reports/2025").mkdir(parents=True, exist_ok=True)

# Write a file inside it
Path("reports/2025/jan.txt").write_text("January report", encoding="utf-8")

# List all .txt files inside reports/, including subfolders
for f in Path("reports").rglob("*.txt"):
    print(f)
```

### Real-world example

Find all PDF invoices created in the last 7 days, ready to be attached to an email:

```python
from pathlib import Path
from datetime import datetime, timedelta

cutoff = datetime.now() - timedelta(days=7)

recent_pdfs = []
for pdf in Path("invoices").rglob("*.pdf"):
    modified = datetime.fromtimestamp(pdf.stat().st_mtime)
    if modified >= cutoff:
        recent_pdfs.append(pdf)

print(f"Found {len(recent_pdfs)} new invoices to send")
for pdf in recent_pdfs:
    print(" -", pdf)
```

---

That's 1–20 covered in beginner style. The file got long, so I'll continue topics 21–62 in another batch. Let me know if this style works and I'll keep going with the same shape (plain words → small example → real example).