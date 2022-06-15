/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd201_assignment2_hieuttfx_14194.funix.edu.vn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Lưu vào file console_ouput.txt 
 * @author Admin
 */
public class ConsoleAndLog {
    // lưu thông tin cần in và lưu vào file
    public void consoleAndLog(String a){
        try{
            File file = new File("console_output.txt");
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bf = new BufferedWriter(writer);
            bf.write(a);
            bf.close();
            System.out.print(a);
        }catch(IOException e){
        
        }
    }
    // lưu thông tin vào file
    public void console(String a){
        try{
            File file = new File("console_output.txt");
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bf = new BufferedWriter(writer);
            bf.write(a+"\n");
            bf.close();
        }catch(IOException e){
        
        }
    }
}
