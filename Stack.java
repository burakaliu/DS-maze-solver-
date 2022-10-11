public class Stack {
    Node top;

    public void push(int p, int p2) {
        Node n = new Node(p, p2);
        n.next = top;
        top = n;
    }

    /**
     * Integer is a java wrapper class
     */
    public int[] peek() {
        if (top == null) return null;
        return top.value;
    }

    public int[] pop() {
        if (top == null) return null;
        Node oldtop = top;
        top = top.next;
        oldtop.next = null;
        return oldtop.value;
    }

    public class Node
    {
        int[] value = {0, 0};
        Node next;
        public Node(int x, int x2) {
            value[0] = x;
            value[1] = x2;
        }
    }
}
