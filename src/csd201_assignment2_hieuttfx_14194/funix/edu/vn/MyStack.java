/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_assignment2_hieuttfx_14194.funix.edu.vn;

/**
 *
 * @author Admin
 */
public class MyStack<T> {
    private Node head;
    public ConsoleAndLog console;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
    
    public boolean isEmpty(){
        if(this.head != null){
            return false;
        }
        return true;
    }
    // Thêm node vào Stack
    public void push(T item){
        Node newNode = new Node(item);
        newNode.setNextNode(this.head);
        this.head = newNode;
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
