'''
array-indexed n-ary tree question

Initial tree:

                A
        F               B
    G       H       C       D
W

format: (node, parentIndex) (the parentIndex of the root node is itself)

tree = [('A', 0), ('F', 0), ('B', 0), ('G', 1), ('H', 1), ('C', 2), ('D', 2), ('W', 3)]

---

First part, implement an algorithm to remove a node from the tree.

Example removing F:

        A
    G   H      B
W            C   D

---

Second part, implement an algorithm to remove an entire subtree.

Example removing B and its subtree:

        A
    G   H
W

'''

# first part


def remove_node(tree, node_index):
    for i in range(len(tree)):
        if tree[i][1] > node_index:
            tree[i] = (tree[i][0], tree[i][1] - 1)
        elif tree[i][1] == node_index:
            tree[i] = (tree[i][0], tree[node_index][1])
    tree.pop(node_index)


tree = [('A', 0), ('F', 0), ('B', 0), ('G', 1),
        ('H', 1), ('C', 2), ('D', 2), ('W', 3)]
node_index = 1
output = [('A', 0), ('B', 0), ('G', 0), ('H', 0), ('C', 1), ('D', 1), ('W', 2)]
remove_node(tree, node_index)
assert tree == output


# second part

def get_first_child(tree, node):
    for i in range(node + 1, len(tree)):
        if tree[i][1] == node:
            return i
    return -1


def remove_subtree(tree, root_index):
    def dfs(node):
        child = get_first_child(tree, node)
        while child != -1:
            dfs(child)
            child = get_first_child(tree, node)
        remove_node(tree, node)
    dfs(root_index)


tree = [('A', 0), ('F', 0), ('B', 0), ('G', 1),
        ('H', 1), ('C', 2), ('D', 2), ('W', 3)]
node_index = 1
output = [('A', 0), ('B', 0), ('C', 1), ('D', 1)]
remove_subtree(tree, node_index)
assert tree == output
