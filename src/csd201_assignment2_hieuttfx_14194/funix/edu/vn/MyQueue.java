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
public class MyQueue<T> {
    private Node head;
    private Node tail;
    // Thêm Node vào Queue
    public void enqueue(T item){
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
}
