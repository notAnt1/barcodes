# Barcodes

## About

*  Lab 1, which includes Barcode code and Java Exploration (FizzBuzz and Strings)

### Authors

*  Oscar To and Valeriia Kosse

### Resources

*   _(TODO: list your resources here!)_
*   ...

## Write-up

### Part 2.1: Old Hat, New Hat

1. With your partner, brainstorm a list of (a) useful functions over strings you’ve used in other languages and (b) operations, i.e., common programming patterns, over strings.

Useful functions include length functions, string comparison functions, string copy functions, parsing functions, iterating through strings. 

For each entry in your list, peruse the String class documentation and try to find an equivalent String method that performs the function/operation in question. If you can’t find such a method, write a code snippet that performs the same effect.

String comparison is string.compareTo(), string copy is string.copyValueOf(), parsing from string to int is Integer.parseInt(n), and iterating through strings is to use charAt(i). 

### Part 2.2: Iceberg, Right Head!
 
2. You may have noticed that there is an equals(obj) method of the string class! Try replacing all the uses of == with appropriate invocations of the equals method (does it matter which of the two strings you call equals on?). Do you obtain the results that you were expecting now? Based on your results, complete the following sentence:

To compare two strings for equality, we must use the equals(obj) method of the string class because they'll compare the actual content of the strings and not simply their locations in memory (like the == method).

3. But why is this the case? Let’s focus first on the equality checks marked B and C above. I’ll appeal to your knowledge of C and state a subtlety about Java values not apparent until now:

Values of object type (of which String is one) are actually pointers (or more specifically, references) to those values in memory.

With this in mind, in a sentence or two, explain why == does not work for the B and C string examples listed above.

B won't work because the substring method is creating 2 separate strings that are different locations in memory, while C won't work because an array of chars is different than a memory pointer to a string literal. 

4. Now, let’s revisit the equality check marked A. You might observe that your answer to the previous part doesn’t account for the results in this case. It turns out that Java does something special that our previous languages do not do!

Search the String class documentation for the intern() method and read about it. Based on this, can you explain the results of this equality check?

Intern() adds strings to the String Constant Pool, which checks to see if an existing string exists already, and if it does, instead returns a pointer to that memory block. This explains the behaviour of the previous equality check because by assigning the string literal, java instead assigns a pointer to the first string literal to s2, which makes the equality check valid. 
