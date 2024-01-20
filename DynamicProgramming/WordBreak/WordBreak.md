# WordBreak

Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. 

## Examples:

Consider the following dictionary
{ i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}

Input:  ilike
Output: Yes

The string can be segmented as "i like".

Input:  ilikesamsung
Output: Yes

The string can be segmented as "i like samsung" or "i like sam sung".


## Recursion Concept

The idea is simple, we consider each prefix and search for it in dictionary. If the prefix is present in dictionary, we recur for rest of the string (or suffix).