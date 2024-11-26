using System.Collections;

namespace CSharpFeatures;

public class MyLinkedList<T> : IEquatable<MyLinkedList<T>>, IEnumerable<T>
{
    private Node? _head;
    public int Size { get; private set; }
    
    // private int _size;
    // public int Size => _size;
    // private int Size { get { return _size; } set => _size = value; }

    // private const int Water = 0;
    // private const int Island = 1;
    //
    // private static readonly int[,] Directions = new int[,] {
    //     {0, 1}, // RIGHT
    //     {1, 0}, // DOWN
    //     {0, -1}, // LEFT
    //     {-1, 0}, // UP
    // };

    public void AddFront(T data)
    {
        var node = new Node(data) { Next = _head }; // Object initializer syntax
        _head = node;
        Size++;
    }

    public Node Head()
    {
        if (_head is Node node) return node;
        if (_head == null) throw new ArgumentNullException(nameof(_head), "can't be null.");
        throw new Exception("invalid state");
    }

    public class Node(T data) // Primary constructor
    {
        public T Data { get; set; } = data; // Assign constructor argument to property
        public Node? Next { get; set; }
    }

    public IEnumerator<T> GetEnumerator()
    {
        Node? current = _head;
        while (current is not null)
        {
            yield return current.Data; // "yield return" is equivalent to "yield" in python or javascript
            current = current.Next;
        }
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    public bool Equals(MyLinkedList<T>? other) // From System.IEquatable[T]
    {
        if (other is null) return false;
        if (ReferenceEquals(this, other)) return true;
        return Equals(_head, other._head);
    }

    public override bool Equals(object? obj)
    {
        if (obj is null) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != GetType()) return false;
        return Equals((MyLinkedList<T>)obj);
    }

    public override int GetHashCode()
    {
        return (_head != null ? _head.GetHashCode() : 0);
    }
}