/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_assignment2_hieuttfx_14194.funix.edu.vn;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class OperationToProduct {
    public ConsoleAndLog console = new ConsoleAndLog();

    // Nhập thông tin sản phẩm ID, name, quantity, price
    public Product createItem(){
        console.consoleAndLog("Input new ID: ");
        Scanner sc = new Scanner(System.in);
        String bCode = sc.nextLine();
        console.console(bCode);
        console.consoleAndLog("Input Product's Name: ");
        String name = sc.nextLine();
        console.console(name);
        console.consoleAndLog("Input Product's quantity: ");
        int quantity = sc.nextInt();
        console.console(String.valueOf(quantity));
        console.consoleAndLog("Input Product's price: ");
        int price = sc.nextInt();
        console.console(String.valueOf(price));
        Product product = new Product(bCode, name, quantity, price);
        return product;
    }
    // đọc file data.txt trả về Linked List
    public MyList<Product> readFile(String fileName) throws FileNotFoundException, IOException{
        MyList<Product> list = new MyList<>();
        String bcode;
        String title;
        int quantity;
        double price;
        // đọc dữ liệu tử file
        FileInputStream fileInput = new FileInputStream(fileName+".txt");
        Scanner sc = new Scanner(fileInput);
        String word ="";
        try{
            while(sc.hasNextLine()){
                word += sc.nextLine()+'\n';
            }
        } finally{
            try{
                sc.close();
                fileInput.close();
            }catch(IOException e){
            }
        }
        String[] words = word.split("\n");
        int n = words.length;
        // tạo Product và add vào Linked List
        for(int i =0; i< n; i++){
            String[] products = words[i].split("\\s");
            bcode = products[0];
            title = products[1];
            quantity = Integer.parseInt(products[2]);
            price = Double.parseDouble(products[3]);
            Product product = new Product(bcode, title, quantity, price); 
            list.insertToTail(product);
        }
        return list;
    }
    // Hiển thị thông tin được đọc ra màn hình
    public void getItemFromFile(String fileName, MyList<Product> list) throws IOException{
        list = readFile(fileName);
        console.consoleAndLog(list.toString());
    }

    // thêm phần tử vào cuối list
    public void addLast(MyList<Product> list){
        list.insertToTail(createItem());
    }
    // hiển thị Linked List
    public void displayAll(MyList<Product> list){
        console.consoleAndLog(list.toString());
    }
    // Lưu Linked List vào file data.txt
    public void writeAllItemToFile(String fileName, MyList<Product> list) throws IOException{
        try (FileWriter wri = new FileWriter(fileName)) { 
            Node<Product> current = list.getHead();
            String a= "";
            // thông tin cần in vào file data.txt
            while(current != null){
                a += current.getData().getBcode() + " " + current.getData().getTitle()
                        + " " + current.getData().getQuantity() +" " + current.getData().getPrice()+'\n';
                current = current.getNextNode();
            }
            wri.write(a);
            wri.close();
        }  
    }
    // tìm kiếm phần tử trong Linked List qua ID
    public void searchByCode(MyList<Product> list){
        console.consoleAndLog("Input ID to Search: ");
        Scanner sc = new Scanner(System.in);
        String searchID = sc.next(); // nhập ID cần tìm kiếm
        console.console(searchID);
        Node<Product> current = list.getHead();
        // tìm kiếm phần tử chứa ID trong list và in kết quả
        while(current!= null){
            //Hiển thị thông tin của Product ra màn hình
            if(current.getData().getBcode().equals(searchID)){
                console.consoleAndLog("Result:\nID: "+ current.getData().getBcode()+"| Title: "
                    + current.getData().getTitle()+ "| Quantity: " +current.getData().getQuantity()
                    + "| Price: " + current.getData().getPrice() +'\n');
                break;
            }
            current = current.getNextNode();
            // Không tìm ra Product cần tìm
            if(current == null){
                console.consoleAndLog("Can't found: " + searchID + '\n');
            }
        }
    }
    // xóa element theo ID
    public void deleteByCode(MyList<Product> list){
        console.consoleAndLog("Input the Bcode to delete: ");
        Scanner sc = new Scanner(System.in);
        String deleteID = sc.next(); // nhập ID cần xóa
        console.console(deleteID);
        Node<Product> found = list.getHead();
        // tìm kiếm phần tử chứ ID có trong Linked List
        while(found!= null){
            if(found.getData().getBcode().equals(deleteID)){
                console.consoleAndLog("Delete:\nID: "+ found.getData().getBcode()+"| Title: "
                    + found.getData().getTitle()+ "| Quantity: " +found.getData().getQuantity()
                    + "| Price: " + found.getData().getPrice() +'\n');
                break;
            }  
            found = found.getNextNode();
            // Không tìm ra Product cần xóa
            if(found == null){
                console.consoleAndLog("Can't found: " + deleteID + '\n');
            }  
        }
        // xóa phần tử chứa ID trong Linked List
        Node<Product> current = list.getHead();
        while(current != null && found != null){
            // xóa nếu phần tử tìm thấy tại head
            if(found == list.getHead()){ 
                list.setHead(list.getHead().getNextNode());
                console.consoleAndLog("Successfully!!\n");
                break;
            }
            // xóa nếu phần tử tìm thấy tại tail
            else if(found == list.getTail()&&current.getNextNode() ==found){
                list.setTail(current.getNextNode().getNextNode());
                console.consoleAndLog("Successfully!!\n");
                break;
            }
            // xóa nếu tìm thấy tại vị trí bất kỳ trong linkerlist
            else if(current.getNextNode() == found){
                current.setNextNode(found.getNextNode());
                console.consoleAndLog("Successfully!!\n");
                break;
            }
            current = current.getNextNode();
        }
    }
    // Sử dụng quickSort sắp xếp theo ID
    public void quickSort(MyList<Product> list){
        MyList<Product> l1 = new MyList<>();
        MyList<Product> l2 = new MyList<>();
        Node<Product> tag, p;
        if(list.getHead() == list.getTail()){
            return;
        }
        // cô lập tag trong Linked List
        tag = list.getHead();
        list.setHead(list.getHead().getNextNode());
        tag.setNextNode(null);
        while(list.getHead() != null){
            // cô lập p và tiến hành so sánh với tag
            p = list.getHead();
            list.setHead(list.getHead().getNextNode());
            p.setNextNode(null);
            // so sánh ID của p và tag, chèn vào 2 Linked List l1, l2
            if(p.getData().getBcode().compareTo(tag.getData().getBcode()) <= 0){
                l1.insertToHead(p.getData());
            }else{
                l2.insertToHead(p.getData());
            }
        }
        // đệ quy sắp xếp 2 linkerlist l1, l2;
        quickSort(l1);
        quickSort(l2);
        // nối l1 và l2
        if(l1.getHead() != null){ // kiểm tra điều kiện và nối l1
            list.setHead(l1.getHead());
            l1.getTail().setNextNode(tag);
        }else{
            list.setHead(tag);
        }
        tag.setNextNode(l2.getHead());
        if(l2.getHead() != null){// kiểm tra điều kiện và nối l2
            list.setTail(l2.getTail());
        }else{
            list.setTail(tag);
        }
    }
    // Conver Quantity từ hệ thập phân sang nhị phân
    public void convertToBinary(int x){
        if(x ==0 ){
        }else{
            int r = x%2;
            convertToBinary(x/2);
            console.consoleAndLog(String.valueOf(r));
        }
    } 
    // đọc file data.txt và trả về Linked List
    public MyStack<Product> readFileToStack(String fileName) throws FileNotFoundException, IOException{
        MyStack<Product> list = new MyStack<>();
        String bcode;
        String title;
        int quantity;
        double price;
        // đọc dữ liệu trong file
        FileInputStream fileInput = new FileInputStream(fileName+".txt");
        Scanner sc = new Scanner(fileInput);
        String word ="";
        try{
            while(sc.hasNextLine()){
                word += sc.nextLine()+'\n';
            }
        } finally{
            try{
                sc.close();
                fileInput.close();
            }catch(IOException e){
            }
        }
        // thêm Product vào Stack
        String[] words = word.split("\n");
        int n = words.length;
        for(int i =0; i< n; i++){
            String[] products = words[i].split("\\s");
            bcode = products[0];
            title = products[1];
            quantity = Integer.parseInt(products[2]);
            price = Double.parseDouble(products[3]);
            Product product = new Product(bcode, title, quantity, price); 
            list.push(product);
        }
        return list;
    }
    // Hiển thị thông tin trong Queue ra màn hình
    public void getItemFromFile(String fileName, MyStack<Product> list) throws FileNotFoundException, IOException{
        list = readFileToStack(fileName);
        console.consoleAndLog(list.toString());
    }
    // Đọc từ file data.txt và trả về Linked List
    public MyQueue<Product> readFileToQueue(String fileName) throws FileNotFoundException, IOException{
        MyQueue<Product> list = new MyQueue<>();
        String bcode;
        String title;
        int quantity;
        double price;
        // đọc dữ liệu từ file data.txt
        FileInputStream fileInput = new FileInputStream(fileName+".txt");
        Scanner sc = new Scanner(fileInput);
        String word ="";
        try{
            while(sc.hasNextLine()){
                word += sc.nextLine()+'\n';
            }
        } finally{
            try{
                sc.close();
                fileInput.close();
            }catch(IOException e){
            }
        }
        String[] words = word.split("\n");
        int n = words.length;
        // thêm Product vào Queue
        for(int i =0; i< n; i++){
            String[] products = words[i].split("\\s");
            bcode = products[0];
            title = products[1];
            quantity = Integer.parseInt(products[2]);
            price = Double.parseDouble(products[3]);
            Product product = new Product(bcode, title, quantity, price); 
            list.enqueue(product);
        }
        return list;
    }
    // hiển thị Queue ra màn hình.
    public void getItemFromFile(String fileName, MyQueue<Product> list) throws FileNotFoundException, IOException{
        list = readFileToQueue(fileName);
        console.consoleAndLog(list.toString());
    }
}
  
