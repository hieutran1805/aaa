/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_assignment2_hieuttfx_14194.funix.edu.vn;

/**
 *
 * @author Admin
 * @param <T>
 */
public class MyList<T> {
    private Node head;

    private Node tail;

    public MyList() {
    }

    public MyList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }
    // Kiểm tra Linked List rỗng hay không
    public boolean isEmpty(){
        return this.head == null;
    }
    // Duyệt Linked list và trả về chiều dài
    public int length(){
        Node current = this.head;
        int length = 0;
        while(current != null){
            length++;
            current = current.getNextNode();
        }
        return length;
    }
    // Thêm Node vào cuối Linked List
    public void insertToHead(T item){
        Node newNode = new Node(item);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }else{
            newNode.setNextNode(this.head);
            this.head = newNode;
        }
    }
    // Thêm Node vào đầu Linked List
    public void insertToTail(T item){
        Node newNode = new Node(item);
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }else{
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
    }
    
    @Override
    public String toString() {
        Node current = this.head;
        String result = " ----------------------------------\n"
                + String.format("%-6s%-8s%-8s%-8s", " |ID", "  |Title", "   |Quantity|", "  Price|")+"\n"
                + " ----------------------------------\n";
        while((current != null )){
            result += current.toString();
            current = current.getNextNode();
        }
        return result + "\n";
    }
    
     public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }
}
