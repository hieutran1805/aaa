/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_assignment2_hieuttfx_14194.funix.edu.vn;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class AS2_main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        OperationToProduct ope = new OperationToProduct();
        MyList<Product> list = ope.readFile("data");
        MyStack<Product> list1 = new MyStack<>();
        MyQueue<Product> list2 = new MyQueue<>();
        ConsoleAndLog console = new ConsoleAndLog();
        Scanner sc = new Scanner(System.in);
        boolean will = true;
        do{
            // Menu hiển thị chính
            console.consoleAndLog(""
                    + "|---------------------------------------------|\n"
                    + "|---------Choose one of this options----------|\n"
                    + "|---------------PRODUCT LIST------------------|\n"
                    + "|---------------------------------------------|\n"
                    + "|--   1. Load data from file and display    --|\n"
                    + "|--   2. Input & add to the end.            --|\n" 
                    + "|--   3. Display data                       --|\n" 
                    + "|--   4. Save product list to file.         --|\n" 
                    + "|--   5. Search by ID                       --|\n" 
                    + "|--   6. Delete by ID                       --|\n" 
                    + "|--   7. Sort by ID.                        --|\n" 
                    + "|--   8. Convert to Binary                  --|\n" 
                    + "|--   9. Load to stack and display          --|\n" 
                    + "|--   10. Load to queue and display.        --|\n"
                    + "|--   Exit:                                 --|\n" 
                    + "|--   0. Exit                               --|\n"
                    + "|---------------------------------------------|\n"
                    + "Choice = "); 
            int choice = sc.nextInt();
            console.console(String.valueOf(choice));
            switch(choice){
                // đọc file và in ra màn hình
                case 1: 
                    ope.getItemFromFile("data", list);
                    break;
                // thêm vào cuối linked list
                case 2:
                    ope.addLast(list);
                    break;
                // hiển thị dữ liệu ra màn hình
                case 3: 
                    ope.displayAll(list);
                    break;
                // lưu dữ liệu vào file data.txt
                case 4:
                    ope.writeAllItemToFile("data.txt", list);
                    console.consoleAndLog("Successfully!!\n");
                    break;
                // Search Product thông qua ID
                case 5: 
                    ope.searchByCode(list);
                    break;
                // Delete Product thông qua ID
                case 6:
                    ope.deleteByCode(list);
                    break;
                // QuickSort Linked list theo ID và hiển thị ra màn hình
                case 7:
                    ope.quickSort(list);
                    ope.displayAll(list);
                    break;
                // Hiển thị quanity của phần tử đầu tiên của Linked List
                case 8:
                    Node<Product> convert = list.getHead();
                    console.consoleAndLog("Quanity: " + convert.getData().getQuantity()+"=>");
                    ope.convertToBinary(convert.getData().getQuantity());
                    console.consoleAndLog("\n");
                    break;
                // Đọc dữ liệu từ data.txt và hiển thị thông tin trong stack
                case 9: 
                    ope.getItemFromFile("data", list1);
                    break;
                // Đọc dữ liệu từ data.txt và hiển thị thông tin trong queue.
                case 10:
                    ope.getItemFromFile("data", list2);
                    break;
                // thoát khỏi chương trình
                case 0:
                    will = false;
                    break;
            }
        }while(will);
    }
    
    
}
