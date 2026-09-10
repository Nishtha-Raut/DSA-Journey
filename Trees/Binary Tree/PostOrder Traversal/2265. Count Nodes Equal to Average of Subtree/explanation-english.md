# Explanation

## Intuition

For every node, we need to calculate the average of all the values present in its subtree.

To calculate the average, we need two things:

1. Sum of all nodes in the subtree
2. Number of nodes in the subtree

We can calculate these values using **postorder traversal** because we need the information from the left and right subtrees before calculating the information for the current node.

In this solution, the `solve()` function returns three values:

- `arr[0]` → sum of the subtree
- `arr[1]` → number of nodes in the subtree
- `arr[2]` → number of nodes that satisfy the average condition

## Approach

We use recursive postorder traversal:

`Left → Right → Root`

For every node:

1. Recursively calculate the information of the left subtree.
2. Recursively calculate the information of the right subtree.
3. Calculate the sum of the current subtree.
4. Calculate the number of nodes in the current subtree.
5. Add the valid-node counts from the left and right subtrees.
6. Calculate the average using:
   `sum / count`
7. If the average is equal to `root.val`, increment the valid-node count.
8. Return all three values to the parent.

## Meaning of the Returned Array

The array returned by `solve()` has three positions:

`arr[0]` = subtree sum

`arr[1]` = subtree node count

`arr[2]` = number of valid nodes in the subtree

For example, if a subtree contains values `0, 1, 8`:

Sum = `9`

Count = `3`

Valid nodes = `2`

So the returned array will be:

`{9, 3, 2}`

## Special Case: Leaf Node

If a node has no left and right child, it is a leaf.

Its subtree contains only itself.

Therefore:

`average = root.val / 1 = root.val`

So every leaf node is always valid.

That is why the code directly returns:

`{root.val, 1, 1}`

for a leaf node.

## Dry Run

Consider the following tree:

        4
       / \
      8   5
     / \   \
    0   1   6

We process the tree using postorder traversal.

### Step 1: Node 0

Node 0 is a leaf.

Sum = `0`

Count = `1`

Average = `0 / 1 = 0`

Since average equals node value, it is valid.

Return:

`{0, 1, 1}`

### Step 2: Node 1

Node 1 is a leaf.

Sum = `1`

Count = `1`

Average = `1 / 1 = 1`

It is valid.

Return:

`{1, 1, 1}`

### Step 3: Node 8

Left subtree result:

`{0, 1, 1}`

Right subtree result:

`{1, 1, 1}`

Now calculate the subtree of node 8.

Sum:

`0 + 1 + 8 = 9`

Count:

`1 + 1 + 1 = 3`

Valid nodes from children:

`1 + 1 = 2`

Average:

`9 / 3 = 3`

Since:

`3 != 8`

Node 8 is not valid.

Return:

`{9, 3, 2}`

### Step 4: Node 6

Node 6 is a leaf.

Sum = `6`

Count = `1`

Average = `6 / 1 = 6`

It is valid.

Return:

`{6, 1, 1}`

### Step 5: Node 5

Node 5 has only a right child.

Left subtree result:

`{0, 0, 0}`

Right subtree result:

`{6, 1, 1}`

Sum:

`0 + 6 + 5 = 11`

Count:

`0 + 1 + 1 = 2`

Valid nodes:

`0 + 1 = 1`

Average:

`11 / 2 = 5`

Java integer division gives `5`.

Since:

`5 == 5`

Node 5 is valid.

So the result is:

`{11, 2, 2}`

### Step 6: Node 4

Left subtree result:

`{9, 3, 2}`

Right subtree result:

`{11, 2, 2}`

Now calculate the complete subtree.

Sum:

`9 + 11 + 4 = 24`

Count:

`3 + 2 + 1 = 6`

Valid nodes from children:

`2 + 2 = 4`

Average:

`24 / 6 = 4`

Since:

`4 == 4`

Node 4 is also valid.

So the final result is:

`{24, 6, 5}`

The third value is the answer.

Therefore:

`Answer = 5`

## Why It Works

For every node, the left and right recursive calls first calculate the complete information of their subtrees.

Using these results, we can calculate:

`current sum = left sum + right sum + current value`

and

`current count = left count + right count + 1`

Then we calculate the average of the current subtree and check whether it is equal to the current node's value.

Because every node is processed exactly once, every node is checked correctly.

## Time Complexity

Every node is visited exactly once.

Therefore:

**Time Complexity: `O(n)`**

where `n` is the number of nodes.

## Space Complexity

The recursion stack depends on the height of the tree.

Therefore:

**Space Complexity: `O(h)`**

where `h` is the height of the tree.

In the worst case, the tree can be skewed, so `h = n`.

Worst-case space:

`O(n)`

## Edge Cases

### 1. Single Node

If the tree contains only one node:

`average = value / 1 = value`

So the node is always counted.

### 2. Leaf Nodes

Every leaf is always valid because its subtree contains only itself.

### 3. Non-integer Average

The problem requires the average to be rounded down.

For example:

`11 / 2 = 5`

Since Java integer division removes the decimal part, `11 / 2` gives `5`.

## Interview Takeaway

"I use postorder traversal because I need the left and right subtree information before processing the current node. For every subtree, I return its sum, node count, and the number of valid nodes. Then I calculate the current subtree average and check whether it is equal to the current node's value."
