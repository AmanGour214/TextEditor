import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;

public class TextEditer implements ActionListener {

   //we are just making a frame or window ;
    JFrame frame;// inbilt class in java i.e JFrame 1)
    JMenuBar menubar ;//it is Meanu bar class 2)
    JMenu file,edit; // creating meanus name 3);
    JMenuItem newfile,openfile,savefile;
    JMenuItem cuttext,copytext,pastetext,selecttext;

    // Area for writing text;
    JTextArea textArea;

    TextEditer(){
        frame=new JFrame();//crating an object of JFrame class 1);

        textArea=new JTextArea();
        frame.add(textArea);


        menubar =new JMenuBar(); //initialize JmanuBar 2)

        file =new JMenu("file");// creating file meanu 3)
        edit=new JMenu("edit");// craating file meanu 3)

        newfile=new JMenuItem("New");
        newfile.addActionListener(this);
        openfile=new JMenuItem("open");
        openfile.addActionListener(this);
        savefile=new JMenuItem("save");
        savefile.addActionListener(this);


        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        cuttext=new JMenuItem("Cut");
        copytext=new JMenuItem("Copy");
        pastetext=new JMenuItem("Paste");
        selecttext=new JMenuItem("Select All");
        edit.add(cuttext);
        cuttext.addActionListener(this);

        edit.add(copytext);
        copytext.addActionListener(this);

        edit.add(pastetext);
        pastetext.addActionListener(this);

        edit.add(selecttext);
        selecttext.addActionListener(this);

        menubar.add(file);// adding menu to menubar 3)
        menubar.add(edit);// adding menu to menubar 3)
        // we have to add menubar to frame ;
        frame.setJMenuBar(menubar); // setting meanu bar into frame ;2) and 3);

        frame.setBounds(100,100,500,400);//setting the dimension of window 1);
        frame .setVisible(true);// this is for window visiable 1);



    }
    public static void main(String[] args) {
       TextEditer textEditer=new TextEditer();//1)

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if(s.equals("Cut")){
            textArea.cut();
        }
        if(s.equals("Copy")){
            textArea.copy();
        }
        if(s.equals("Paste")){
            textArea.paste();
        }
        if(s.equals("Select All")){
            textArea.selectAll();
        }

        if(s.equals("New")){
            TextEditer newWindow=new TextEditer();
        }
        if(s.equals("open")){
            // JfileChooser for chosing the file ;
            JFileChooser fileChooser=new JFileChooser("C:");
            // it give the value o or 1 of opening or canceling the fileopening ;
            int chooseOption =fileChooser.showOpenDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                // selecting the file
                File file =fileChooser.getSelectedFile();
                // selecting the path of the file ;
                String filePath= file.getPath();
                try {
                    // bufferreader for getting the contain of File and we are giving the path of that file;
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    // creating string intermidiate which contain data line by line  by using buffereader;
                    String intermediate="",output="";

                    // reading contain line by line;
                    while ((intermediate=bufferedReader.readLine())!=null){
                        output=output+intermediate+"\n";
                    }
                    // we are just pring all contains of output in our file;
                    textArea.setText(output);

                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        }
        if(s.equals("save")){
            //for save file

            JFileChooser fileChooser=new JFileChooser("C:");

            int chooseOption = fileChooser.showSaveDialog(null);

            if(chooseOption==JFileChooser.APPROVE_OPTION){

                // we have to creat the file for saving the perticular file;
                // it will creat new file first then select file where  we have to save it and the avsoloutfile to save it;
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    // we are using the buferWriter for writing the contain of the file to the newly created file
                    BufferedWriter outfile=new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }


        }
    }
}