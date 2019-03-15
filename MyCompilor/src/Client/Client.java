//2017.06.21 BeibeiZhang MainGUI()
//functions: new(bf or ook file);open;save;delete;exit;login;logout;signup;version;run;undo;redo
//redo和undo在client完成,只以codeArea为例，用stack保存，push和pop,监听codeArea
//2017.06.22 BeibeiZhang Client
//2017.06.23
//2017.06.24
//2017.06.25
//2017.06.26 write notes
package Client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/*NOTE: you can establish a public class in the Client
//but you can not establish a public class outside Client
 * */
public class Client {
	private int signed=0;
	//set signed bit to show if has signed
	//signed=1:log in; signed=0:log out
	//only when signed=1 can realize the functions 
	//when signed=0, need to log in
	private int saved=0;
	//set saved bit to show if has been saved
	//saved=1:has been saved; saved=0:has not been saved
	//when exit: if saved=0, ask if to save
	//when "Save" or "Run": set saved=1
	private int targetExisted=0;
	//set targetExisted bit to show if there is file currently
	//only when targetExisted==1:codeArea.setVisiabel(true);"version";"Save";"Run"
	//only when new or open: targetExisted=1
	//log out: targetExisted=0
    private int open=0;
    //set open bit to show if get "Open" cmd
    //if "Open", get filenameList from Server, insert them into File-Choosing Menu
    //not show in outputArea
    private int openfile=0;
    //set open bit to show if get "Openfile" cmd
     //if "Openfile", get filecontent from Server, insert them into codeArea
     //not show in outputArea
    private int runed=0;
     //set runned bit to show if runned
     //if runned, means saved, not need to ask if to save
    private int Version=0;
     //set Version bit to show if get "Version" cmd
    //if "Version", get versionfilenameList from Server, insert them into Version-Choosing Menu
    //not show in outputArea
    private int openVersions=0;
    //set openVersions bit to show if get "OpenVersion" cmd
    //if "OpenVersion", get versionfilecontent from Server, insert them into versionArea
    //not show in outputArea
    private int undoing=0;
    //just use codeArea as an example
    //set undoing bit to show if choose undo Item
    //if undoing=1, not to push the insertText of codeArea in undoStack
	private int redoing=0;
	//just use codeArea as an example
    //set redoing bit to show if choose redo Item
    //if redoing=1&&undoing=1, not to push the removeText of codeArea in undoStack
	private Stack undoStack;
	private Stack redoStack;
	//just use codeArea as an example
	//push the state(text) of codeArea into Stcak for undo and redo operation
	//all the states are recorded in undoStack
	//only when choose undo Item, text current state will be pushed into redoStack
	private String currentOpenFile="";
	//to show if the currentFile is a open file
	//only when open a file, currentOpenFile=openFilename
	//when exit：if saved==0, ask if to save
	//if choose unsave, not to delete the file but not save the current version
    private JFrame frame;
	//mainFrame
	private JPanel mainPanel;
	//mainpanel:codePanel&versionPanel
	private JPanel codePanel;
	//codePanel:codeAreaPanel&codeLabel
	private JTextArea codeArea;
	//show and write code
	private JPanel versionPanel;
	//versionPanel: versionAreaPanel&versionLabel&Exitbutton
	private JTextArea versionArea;
	//versionArea in versionAreaPanel
	private JTextArea inputArea;
	private JTextArea outputArea;
	private Font fon=new Font("方正喵呜体",1,30);
	//for fun
	private int framewidth=1000;
	private int frameheight=800;
	private int frameLeft=200;
	private int frameRight=200;
	
	private JFrame log;
	//log in frame: an independent frame
	private JTextField nameField;
	private JPasswordField passwordField;
	
	private Socket clientsocket;
	private BufferedReader reader;
	//help GUI get message from server directly
	private PrintWriter writer;
	//help GUI send message to server directly
    
    
	public void MainGUI() {
		// initial GUI
		frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
       
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File(F)");
		fileMenu.setMnemonic(KeyEvent.VK_F);
	
		fileMenu.setFont(fon);
		menuBar.add(fileMenu);
	
		JMenu newMenu = new JMenu("New(N)");
		newMenu.setMnemonic(KeyEvent.VK_N);
		newMenu.setFont(fon);
		fileMenu.add(newMenu);
		JMenuItem bfMenuItem = new JMenuItem("BF file(B)",KeyEvent.VK_B);
		bfMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
		bfMenuItem.setFont(fon);
		newMenu.add(bfMenuItem);
		JMenuItem ookMenuItem = new JMenuItem("OOK file(O)",KeyEvent.VK_O);
		ookMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		
		ookMenuItem.setFont(fon);
		newMenu.add(ookMenuItem);
		//insert subMenu
		
		JMenuItem openMenuItem = new JMenuItem("Open(P)",KeyEvent.VK_P);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		
		openMenuItem.setFont(fon);
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save(A)",KeyEvent.VK_A);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		
		saveMenuItem.setFont(fon);
		fileMenu.add(saveMenuItem);
		JMenuItem deleteMenuItem=new JMenuItem("Delete(D)",KeyEvent.VK_D);
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		
		deleteMenuItem.setFont(fon);
		fileMenu.add(deleteMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit(X)",KeyEvent.VK_X);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		
		exitMenuItem.setFont(fon);
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu=new JMenu("Edit(E)");
		editMenu.setMnemonic(KeyEvent.VK_E);
		editMenu.setFont(fon);
		menuBar.add(editMenu);
		JMenuItem undoMenuItem = new JMenuItem("Undo");
		undoMenuItem.setFont(fon);
		editMenu.add(undoMenuItem);
		JMenuItem redoMenuItem = new JMenuItem("Redo");
		redoMenuItem.setFont(fon);
		editMenu.add(redoMenuItem);
		
		JMenu runMenu = new JMenu("Run(R)");
		runMenu.setMnemonic(KeyEvent.VK_R);
		runMenu.setFont(fon);
		menuBar.add(runMenu);
		JMenuItem runMenuItem = new JMenuItem("Execute");
		runMenuItem.setFont(fon);
		runMenu.add(runMenuItem);
		
		JMenu signMenu = new JMenu("Sign in(S)");
		signMenu.setMnemonic(KeyEvent.VK_S);
		signMenu.setFont(fon);
		menuBar.add(signMenu);
		JMenuItem loginMenuItem = new JMenuItem("Log in");
		loginMenuItem.setFont(fon);
		signMenu.add(loginMenuItem);
		JMenuItem signupMenuItem = new JMenuItem("Sign up");
		signupMenuItem.setFont(fon);
		signMenu.add(signupMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Log out");
		logoutMenuItem.setFont(fon);
		signMenu.add(logoutMenuItem);
		
		JMenu versionMenu=new JMenu("Version(V)");
		versionMenu.setMnemonic(KeyEvent.VK_V);
		versionMenu.setFont(fon);
		menuBar.add(versionMenu);
		JMenuItem versionsMenuItem=new JMenuItem("Versions");
		versionsMenuItem.setFont(fon);
		versionMenu.add(versionsMenuItem);
	
		
		frame.setJMenuBar(menuBar);

		
		frame.addWindowListener(new WindowActionListener());
		bfMenuItem.addActionListener(new BFActionListener());
		ookMenuItem.addActionListener(new OOKActionListener());
		openMenuItem.addActionListener(new OpenActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		deleteMenuItem.addActionListener(new DeleteActionListener());
		exitMenuItem.addActionListener(new ExitActionListener());
		undoMenuItem.addActionListener(new UndoActionListener());
		redoMenuItem.addActionListener(new RedoActionListener());
		runMenuItem.addActionListener(new RunActionListener());
		loginMenuItem.addActionListener(new LoginActionListener());
		signupMenuItem.addActionListener(new SignupActionListener());
		logoutMenuItem.addActionListener(new LogoutActionListener());
		versionsMenuItem.addActionListener(new VersionActionListener());
		
		
		
	    mainPanel=new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout());
		
		codePanel=new JPanel();
		codePanel.setLayout(new BorderLayout());
		codePanel.setBorder(new LineBorder(Color.gray,2));
		
		JLabel codelabel=new JLabel("Code Area");
		codelabel.setFont(fon);
		codelabel.setBackground(Color.gray);
		codePanel.add(codelabel, BorderLayout.NORTH);
		
		JScrollPane codeAreaPanel=new JScrollPane();
		//set scrollPanel for textArea
		codeArea = new JTextArea();
        codeArea.setLineWrap(true);
        //change line automatically
  	    codeArea.setWrapStyleWord(true);
        //change the line automatically but not cut the content
		codeArea.setFont(fon);
		codeArea.setMargin(new Insets(10, 10, 10, 10));
		codeArea.setBackground(Color.white);
    	codeArea.getDocument().addDocumentListener(new CodeValueChange());
    	codeAreaPanel.add(codeArea);
    	codeAreaPanel.setViewportView(codeArea);
        codePanel.add(codeAreaPanel, BorderLayout.CENTER);
        codeArea.setVisible(false);
		
		mainPanel.add(codePanel, BorderLayout.CENTER);
		
		

		versionPanel=new JPanel();
		versionPanel.setLayout(new BorderLayout());
		versionPanel.setBorder(new LineBorder(Color.pink,2));
		
		JPanel versionlabelandButton=new JPanel();
		versionlabelandButton.setLayout(new GridLayout(1,2));
		JLabel versionlabel=new JLabel("Version Area");
		versionlabel.setFont(fon);
		versionlabel.setBackground(Color.gray);
		versionlabel.setBorder(new LineBorder(Color.GRAY,2));
		versionlabelandButton.add(versionlabel);
		JButton versionExit=new JButton("Exit");
		versionExit.setFont(fon);
		versionExit.setBackground(Color.LIGHT_GRAY);
		versionExit.addActionListener(new VersionExitButtonActionLiatener());
		versionExit.setBorder(new LineBorder(Color.GRAY,2));
		versionlabelandButton.add(versionExit);
		
		versionPanel.add(versionlabelandButton,BorderLayout.NORTH);
		
		JScrollPane versionAreaPanel=new JScrollPane();
		//set scrollPanel for textArea
		versionArea=new JTextArea();
		versionArea.setLineWrap(true);       
		versionArea.setWrapStyleWord(true);          
		versionArea.setFont(fon);
		versionArea.setMargin(new Insets(10, 10, 10, 10));
		versionArea.setBackground(Color.white);
		versionPanel.add(versionAreaPanel, BorderLayout.CENTER);
		versionAreaPanel.add(versionArea);
    	versionAreaPanel.setViewportView(versionArea);
        versionPanel.add(versionAreaPanel, BorderLayout.CENTER);
		
		
		
       
		JPanel showPanel=new JPanel();
        frame.getContentPane().add(showPanel, BorderLayout.SOUTH);
        showPanel.setPreferredSize(new Dimension(framewidth,frameheight/4));
        /*NOTE:
         * setSize,setLocation,setBounds
                    方法需要在不使用布局管理器的时候使用，也就是setLayout(null)的时候可以使用这三个方法控制布局
          setPreferredSize需要在使用布局管理器的时候使用，布局管理器会获取空间的preferredsize，因而可以生效。
                     例如borderlayout在north中放入一个panel，panel的高度可以通过这样实现：
          panel.setPreferredSize(new Dimension(0, 100));这样就设置了一个高度为100的panel，宽度随窗口变化
         */     
        showPanel.setLayout(new GridLayout(1,2));
        
        JPanel input=new JPanel();
        input.setLayout(new BorderLayout());
        input.setBorder(new LineBorder(Color.gray,2));
        showPanel.add(input);
        
        JPanel LabelAndButton=new JPanel();
        LabelAndButton.setLayout(new GridLayout(1,2));
        LabelAndButton.setBorder(new LineBorder(Color.gray,2));
        
        JLabel inputlabel=new JLabel("Input Area");
        inputlabel.setFont(fon);
        inputlabel.setBackground(Color.gray);
        
        JButton confirmInput=new JButton("OK");
        confirmInput.setBackground(Color.lightGray);
        confirmInput.setBorder(new LineBorder(Color.GRAY,2));
        confirmInput.setFont(fon);
        confirmInput.addActionListener(new ConfirmInputButtonActionListener());
        
        LabelAndButton.add(inputlabel);
        LabelAndButton.add(confirmInput);
        
		input.add(LabelAndButton,BorderLayout.NORTH);
		inputArea=new JTextArea();
		inputArea.setLineWrap(true);  
		inputArea.setWrapStyleWord(true);
		inputArea.setFont(fon);
		input.add(inputArea,BorderLayout.CENTER);

		JPanel output=new JPanel();
        output.setLayout(new BorderLayout());
        output.setBorder(new LineBorder(Color.gray,2));
        showPanel.add(output);
        JLabel outputlabel=new JLabel("Output Area");
        outputlabel.setFont(fon);
        outputlabel.setBackground(Color.gray);
		output.add(outputlabel,BorderLayout.NORTH);
		outputArea=new JTextArea();
		outputArea.setLineWrap(true);        
	    outputArea.setWrapStyleWord(true);          
		outputArea.setFont(fon);
		output.add(outputArea,BorderLayout.CENTER);
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(framewidth, frameheight);
		frame.setLocation(frameLeft, frameRight);
		frame.setVisible(true);
		try{
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		Log();
	}
	
	class WindowActionListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			try{
				if(signed==1){
				  if(targetExisted==1){
					  //only when currentFile has exited can we save it  
					if(saved==1){
					try{     
						     frame.dispose();
						     //close the frame
							 writer.println("WindowClose;");
							 //use ";" as separator between command and content 
							 writer.flush();
							 //close the server
							 reader.close();
							 writer.close();
							
						}catch(Exception ee){
							ee.printStackTrace();
						}
					
					}
					else{//ask if to save
						if(runed==1){
							//if it has been runed, save it automatically in "Run"
			        		//so donnot need to ask
						}else{
							if(currentOpenFile.equals("")){
								DeleteORsave();
								//if current file is a new file
								//ask if to save or unsave(delete)
								}else{
								UnsaveORSave();
								//if currentfile is a open file
								//ask if to save or unsave
							}
						}
						frame.dispose();
						writer.println("WindowClose;");
						writer.flush();
						reader.close();
						writer.close();
						
					}
				}else{//targetExisted==0
						try{
							 frame.dispose();
							 writer.println("WindowClose;");
							 writer.flush();
							 reader.close();
							 writer.close();
						   	
							
						}catch(Exception ee){
							ee.printStackTrace();
						}
					}
			   
				}else{
					try{
						 frame.dispose();
						 writer.println("WindowClose;");
						 //关闭server和client
						 writer.flush();
						 reader.close();
						 writer.close();
					   
						
					}catch(Exception ee){
						ee.printStackTrace();
					}
		}
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
		}
	class BFActionListener implements ActionListener {
		//set filename and sent it with "BF;" to Server 
		//found the file of the filename which saves the ook file and all the version files
				
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(signed==1){
			    String BFfileName=(String)JOptionPane.showInputDialog(frame.getContentPane(),"请输入文件名：\n","新建BF文件",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入");
			    if(BFfileName==null||BFfileName.equals("")){
			    	
			    }else{
			    writer.println("BF;"+BFfileName);
			    writer.flush();
			    }
				}else{
					JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
					
				}
				}catch(Exception ee){
					ee.printStackTrace();
				}
	
		}
		
	}
	class OOKActionListener implements ActionListener {
		//set filename and send it with "OOK;"to Server 
		//found the file of the filename which saves the ook file and all the version files
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
			if(signed==1){
				//弹框设置文件名
				String OOKfileName=(String)JOptionPane.showInputDialog(frame.getContentPane(),"请输入文件名：\n","新建OOK文件",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入");
				if(OOKfileName==null||OOKfileName.equals("")){
					
				}else{
				writer.println("OOK;"+OOKfileName);
		        writer.flush();
				}
			}else{
				JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
				
			}
			}catch(Exception ee){
				ee.printStackTrace();
			}
	
		}
	}
	class OpenActionListener implements ActionListener {
		//send "Open;" to Server to get fileNameList from server and set "open"bit=1
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try{
				if(signed==1){
				open=1;
			    writer.println("Open;");
			    writer.flush();
			    
				}else{
					JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
					
				}
				}catch(Exception ee){
					ee.printStackTrace();
				}
		}
	}
	
	public void OpenMenu(String filenames){
		//open filechoosing window and get targetFile to send the name to Server
		//set “Open”file bit 0, set opentargetFile bit 1 
			open=0;
			openfile=1;
			String[] files=filenames.split(";");
			String targetfile= (String) JOptionPane.showInputDialog(frame.getContentPane(),"文件名:\n", "选择文件", JOptionPane.PLAIN_MESSAGE,null,files,"选择文件");
			if(targetfile==null||targetfile.equals("")){
				openfile=0;
			}else{
			currentOpenFile="";
			currentOpenFile=targetfile;
			writer.println("OpenFile;"+targetfile);
			writer.flush();
			}
	
	}
	
	class SaveActionListener implements ActionListener {
		//save currentFile
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(signed==1){
					if(targetExisted==1){
						//if there has been currentFile, save File
						 String code=codeArea.getText();
						   if(code.equals("")){
							   
							   JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot write any codes.");
								
							 
						   }else{
								  writer.println("Save;"+code);
							      writer.flush();
						   }
						
			      }else{
			    	  JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot have a file. Please build or open a file firstly.");
						
			       }
				}else{
					JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
					
				}
				}catch(Exception ee){
					ee.printStackTrace();
				}
		}
	}
	class DeleteActionListener implements ActionListener{
		//Delete current file
		public void actionPerformed(ActionEvent e){
			 writer.println("Delete;");
		     writer.flush();
		     codeArea.setText("");
		}
	}
	public void UnsaveORSave(){
		int choice=JOptionPane.showConfirmDialog(frame.getContentPane(),"Do you want to save the file?","Notice",JOptionPane.YES_NO_OPTION);//i=0/1 
		if(choice==0){//choose to save 
			 String code=codeArea.getText();
			   if(code.equals("")){
				   
				   JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot write any codes.");
					
				 
			   }else{
					  writer.println("Save;"+code);
				      writer.flush();
			   }
			
		}else{//choose to unsave(do nothing)
		 
		}
		
		
	}
	
	public void DeleteORsave(){
		int choice=JOptionPane.showConfirmDialog(frame.getContentPane(),"Do you want to save the file?","Notice",JOptionPane.YES_NO_OPTION);//i=0/1 
		if(choice==0){//choose to save
			 String code=codeArea.getText();
			   if(code.equals("")){
				   
				   JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot write any codes.");
					
				 
			   }else{
					  writer.println("Save;"+code);
				      writer.flush();
			   }
			
		}else{//choose to unsave(delete)
			 writer.println("Delete;");
		     writer.flush();
		}
		
	}
	
	class ExitActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if(signed==1){
					if(targetExisted==1){
					if(saved==1){
					try{     
						frame.dispose();
						//close the frame firstly or when the server close, client can do nothing
						writer.println("WindowClose;");
						 //server
						writer.flush();
						reader.close();
						writer.close();
							
						}catch(Exception ee){
							ee.printStackTrace();
						}
					
					}
					else{//ask if to seve
						if(runed==1){
							//if it has been runed, save it automatically in "Run"
			        		//so donnot need to ask
						}else{
							if(currentOpenFile.equals("")){
								DeleteORsave();
								//if current file is a new file
								//ask if to save or unsave(delete)
								}else{
								UnsaveORSave();
								//if currentfile is a open file
								//ask if to save or unsave
							}
						}
						frame.dispose();
						//close the frame firstly or when the server close, client can do nothing
						writer.println("WindowClose;");
						 //server
						writer.flush();
						reader.close();
						writer.close();
						
						
					}
					}else{
						try{
							frame.dispose();
							//close the frame 
							writer.println("WindowClose;");
							 writer.flush();
							//close server 
							 reader.close();
							 writer.close();
						   
							
						}catch(Exception ee){
							ee.printStackTrace();
						}
					}
			   
				}else{
					try{
						 frame.dispose();
						//close the frame
						 writer.println("WindowClose;");
						 writer.flush();
						//close server 
						 reader.close();
						 writer.close();
					   
					}catch(Exception ee){
						ee.printStackTrace();
					}
					
				}
				}catch(Exception ee){
					ee.printStackTrace();
				}
		}
	}
	
	
     public void PushStack(String content){
  	       //push text of codeArea into undoStack 
  		   undoStack.push(content);
  	
     } 
     
	
	 class CodeValueChange implements DocumentListener { 
		 //get current state(text) in codeArea and push into stack 
		 //because every undo or redo operation is remove all in codeArea firstly
		 //and then add text of the last state into codeArea
		 //so the codeArea.getText() of removeUpdate is always "" because remove all while ondo and redo
		 //so we just get codeArea.getText when undo and redo is 0
  	   public void changedUpdate(DocumentEvent e){
  		   System.out.println("Attribute Changed"+e);
  	   }
       public void insertUpdate(DocumentEvent e){
  			if(undoing==0){
              	PushStack(codeArea.getText());
              	}else{
              	
              	}
              }
        public void removeUpdate(DocumentEvent e){
        	if((undoing==0)&&(redoing==0)){
        	    PushStack(codeArea.getText());
            }else{
        	   
           }
          }
     }
     class UndoActionListener implements ActionListener {
		//undo the last operation in codeArea 
		@Override
		public void actionPerformed(ActionEvent e) {
			 try{
				 if(signed==1){
					 undoing=1;
			  		   String current=(String)undoStack.pop();
			  		   redoStack.push(current);
			  		   if(undoStack.isEmpty()){
			  			   JOptionPane.showMessageDialog(frame.getContentPane(),"You have undone all the operations.");
			  			   codeArea.setText("");
			  			   undoing=0;
			  		   }else{
			  		   String before=(String)undoStack.peek();
			  		   codeArea.setText(before);
			  		   undoing=0;
			  		   }
				 }else{
					 JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
					 }
				    }catch(Exception ee){
						ee.printStackTrace();
					}	
		}
	}
	class RedoActionListener implements ActionListener {
		//Redo the undo in codeArea
		@Override
		public void actionPerformed(ActionEvent e) {
			 try{
				 if(signed==1){
					 redoing=1;
					  if(redoStack.isEmpty()){
					   JOptionPane.showMessageDialog(frame.getContentPane(),"You have redone all the operations.");
					   redoing=0;
			  		   }else{
			  		   String last=(String)redoStack.pop();
			  		   codeArea.setText(last);
			  		   redoing=0;
			  		   }
				 }else{
					 JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
			     }
				    }catch(Exception ee){
						ee.printStackTrace();
					}	
		}
	}
	
	class ConfirmInputButtonActionListener implements ActionListener{
		//input area has a confirm button to confirm already input 
		//then send the input to Server to run code
		public void actionPerformed(ActionEvent e){
			String input=inputArea.getText();
			if(input.equals("")){
				 JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot input anything.");
					
			}
			else{
				try{
					writer.println("Input;"+input);
					writer.flush();
				}catch(Exception ee){
					ee.printStackTrace();
				}
			}
		}
	}
	
	class RunActionListener implements ActionListener {
		//execute current file and save file, version
		@Override
		public void actionPerformed(ActionEvent e) {
			 try{
				 if(signed==1){
					   if(targetExisted==1){
						   String code=codeArea.getText();
						   if(code.equals("")){
							   
							   JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot write any codes.");
								
							 
						   }else{
							      inputArea.setText("");
							      //for input
							      writer.println("Save;"+code);
							      writer.flush();
							      saved=1;
							      //firstly save the file
								  writer.println("Run;"+code);
							      writer.flush();
							      runed=1;
							      //then run the file and save the version
						   }
						   }else{
							   JOptionPane.showMessageDialog(frame.getContentPane(),"You donnot have a file. Please build or open a file firstly.");
								
						   }
					
				 }else{
					 JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
				 }
				    }catch(Exception ee){
						ee.printStackTrace();
					}	
		}
	}
	
	public void Log(){
		//log frame: password textField, name textField, signup button, login button
		//only when log in successfully, log.dispose()
		//when sign up successfully, will log in again
	    log=new JFrame("Sign in");
		int width=framewidth/2;
		int height=frameheight/3;
		log.setBackground(Color.gray);
		log.setLayout(new GridLayout(3,2));
		
		
		JLabel name=new JLabel("name: ");
		name.setFont(fon);
		name.setBackground(Color.white);
		name.setBorder(new LineBorder(Color.gray,2));
		
		JLabel password=new JLabel("password: ");
		password.setFont(fon);
		password.setBackground(Color.white);
		password.setBorder(new LineBorder(Color.gray,2));
	    
		nameField=new JTextField();
	    nameField.setText("");
		nameField.setFont(fon);
		nameField.setBorder(new LineBorder(Color.gray,2));
	    
		passwordField = new JPasswordField();
	    passwordField.setText("");
		passwordField.setEchoChar('*');
    	passwordField.setFont(fon);
		passwordField.setBorder(new LineBorder(Color.gray,2));
	    
		JButton login=new JButton("Log in");
		login.setFont(fon);
		login.setBackground(Color.LIGHT_GRAY);
		login.addActionListener(new LoginButtonActionListener());
	
		JButton signup=new JButton("Sign up");
		signup.setFont(fon);
		signup.setBackground(Color.LIGHT_GRAY);
		signup.addActionListener(new SignupButtonActionListener());
		
		log.add(name);
		log.add(nameField);
		log.add(password);
		log.add(passwordField);
		log.add(login);
		log.add(signup);
		
		
        log.setBounds(250+frameLeft,200+frameLeft,width,height);
        log.setVisible(true);
	}
	
	class LoginButtonActionListener implements ActionListener {
	  //write name and password in log frame and press logIn button to send login message to Server
		//if name is wrong, we will get wrong message from server then rewrite name
		//the same with password
		//when log in successfully, signed bit will be set 1
		@Override
		public void actionPerformed(ActionEvent e) {
			  String name=nameField.getText();
			  char[] passwords=passwordField.getPassword();
			  String password=new String(passwords);
	         
		  try{
			      writer.println("Login"+";"+name+";"+password);
			      writer.flush();
				}catch(Exception ee){
					ee.printStackTrace();
				}
		  
		}
	}
	class SignupButtonActionListener implements ActionListener {
		//write name and password in log frame
		//and then press signUp button
		@Override
		public void actionPerformed(ActionEvent e) {
			 String name=nameField.getText();
				char[] passwords=passwordField.getPassword();
				String password=new String(passwords);
				//new String(char[] )to turn char[] into string
			try{
				 writer.println("Signup"+";"+name+";"+password);
				 //send Signup command, name and password altogether to Server
				 //因为后期将Signup与参数一起送到server，故用“+”作为分隔符（并且避免与OOK混淆）
				 writer.flush();
				 //when sign up successfully, Server will send "Sign up successfully"
				 //then we can log in again
				    }catch(Exception ee){
						ee.printStackTrace();
					}
			
		}
	}
	
	class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(signed==0){
				//if have not signed in(logged in), show log window
	          	Log();
	          	}
			else{
				JOptionPane.showMessageDialog(frame.getContentPane(),"You have signed in. If you want to log in again, please log out.");
				//if have logged and want to log in, send message
			}
		}
	}
	class SignupActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		if(signed==0){
			//if have not signed in(logged in), show log window
			Log();
			}
		else{
			JOptionPane.showMessageDialog(frame.getContentPane(),"You have signed in. If you want to sign up, please log out.");
			//if have logged and want to sign up, send message
			
		}
		
		  }
	}
	class LogoutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 try{
				 if(signed==1){
					 if(targetExisted==1){
				        if(saved==0){
					   //ask if to save 
				        	if(runed==1){
								//if it has been runed, save it automatically in "Run"
				        		//so donnot need to ask
							}else{
								if(currentOpenFile.equals("")){
									DeleteORsave();
									//if current file is a new file
									//ask if to save or unsave(delete)
									}else{
									UnsaveORSave();
									//if currentfile is a open file
									//ask if to save or unsave
									
								}
							}
				        	saved=0;
						    targetExisted=0;
						    signed=0;
						    open=0;
						    openfile=0;
							codeArea.setVisible(false);
							outputArea.setText("");
							inputArea.setText("");
							versionArea.setText("");
				        	//logout means initial everything
				        }
				       else{
				    	   saved=0;
						   targetExisted=0;
						   signed=0;
						   open=0;
						   openfile=0;
						   codeArea.setVisible(false);
						   outputArea.setText("");
							inputArea.setText("");
							versionArea.setText("");
				        }
				 }else{
					 saved=0;
					 targetExisted=0;
					 signed=0;
					 open=0;
					 openfile=0;
					 codeArea.setVisible(false);
					 outputArea.setText("");
					 inputArea.setText("");
					 versionArea.setText("");
				 }
				
				 }else{
					 JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in.");
					 //if have not logged in, send message
				 }
				    }catch(Exception ee){
						ee.printStackTrace();
					}	
		
		}
	}
	
	
	
	class VersionActionListener implements ActionListener {
		//send "Version" command to Server and get versionFileList
		@Override
		public void actionPerformed(ActionEvent e) {
			 try{
				 if(signed==1){
					 if(targetExisted==1){
				 Version=1;
				 writer.println("Version;");
				 writer.flush();
					 }
					 else{
			    JOptionPane.showMessageDialog(frame.getContentPane(),"There is no file now.");
							 
					 }
				 }else{
					 JOptionPane.showMessageDialog(frame.getContentPane(),"You have not signed in. Please log in firstly.");
			     }
				    }catch(Exception ee){
						ee.printStackTrace();
					}	
		}
	}
	
	public void VersionMenu(String versionnames){
		//show the choose version window, get the targetFile name
		//and set "Open"version bit--"Version" 0
		//set open targetFile bit--"openVersion" 1
		    Version=0;
			openVersions=1;
			String[] versionfiles=versionnames.split(";");
			String targetversion= (String) JOptionPane.showInputDialog(frame.getContentPane(),"版本号:\n", "选择版本", JOptionPane.PLAIN_MESSAGE,null,versionfiles,"选择版本");
			if(targetversion==null||targetversion.equals("")){
				openVersions=0;
			}else{
			writer.println("OpenVersion;"+targetversion);
			writer.flush();
			}
		
	}
   public void VersionUI(){
	   //show version file
	     //add versionPanel in mainpanel
			  mainPanel.removeAll();
			  mainPanel.setLayout(new GridLayout(1,2));
			
			  mainPanel.add(codePanel);
			  mainPanel.add(versionPanel);
			  mainPanel.updateUI();
	} 
	class VersionExitButtonActionLiatener implements ActionListener{
		//versionExitButton:remove versionPanel
		//mainPanel removeAll, then add only codePanel
		//note:mainPanel.updateUI()
		public void actionPerformed(ActionEvent e){
			mainPanel.removeAll();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(codePanel,BorderLayout.CENTER);
		    mainPanel.updateUI();
		}
	}
   class HandleMessageThread implements Runnable{
	//open new thread to execute message
	//and ensure that swing-awt thread will not be blocked
	//so that we can show infor in frame
	private String message;
	public  HandleMessageThread(String message){
		//package
		this.message=message;
	}
	public void run(){
	//	System.out.println(message);
		if(message.equals("sign up successfully, you can log in")){
			//sign up successfully, means file has been founded, then can log in
			nameField.setText("");
			passwordField.setText("");
		}
		if(message.equals("log in successfully")){
			//log in successfully, set signed=1, means nave logged
			signed=1;
			log.dispose();
		}
		if(message.equals("build successfully")){
			//when we have a new file, initial state 
			targetExisted=1;
			undoStack.clear();
			redoStack.clear();
			runed=0;
			currentOpenFile="";
			codeArea.setVisible(true);
			codeArea.setText("");
		}
		if(message.equals("save successfully")){
			saved=1;
		}
		
		if((open==0)&&(openfile==0)&&(Version==0)&&(openVersions==0)){
			//when these state bits are 1, means that the message from server is not result
			//but same content need to show in other places rather than outputArea
			if(message.length()==0){
			//if message is "",not show it because it will override former useful message	
			}else{
			outputArea.setText("");
			outputArea.setText(message);
			}
		}else{
			if(open==1){
				//if open==1
				//message is filenamelist
			OpenMenu(message);
			
			}
			else{
				if(openfile==1){
				//if openfile==1
				//message is targetFileContent
				//when we open a new file, initial state 
			openfile=0;
			targetExisted=1;
			undoStack.clear();
			redoStack.clear();
			runed=0;
			codeArea.setText("");
			codeArea.setText(message);
			codeArea.setVisible(true);
			
			}
			}
			if(Version==1){
				//if Version==1
				//message is versionFileNamelist
				VersionMenu(message);
				}
			else{
				if(openVersions==1){
				openVersions=0;
				versionArea.setText("");
			    versionArea.setText(message);
			    VersionUI();
				
				}	
			}
		}
	
	}
}

public class GetMessage implements Runnable{
		//help GUI show the message which is from server directly
		//begin a new thread so we can block here safely
		public void run(){
			String message;
			try{
				while((message=reader.readLine())!=null){
					
					try{
						Thread.sleep(200);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					//ensure execute message in order 
					new Thread(new HandleMessageThread(message)).start();
					//because the current thread will block
					//start another thread to handle message
					
				}
			}catch(IOException ex){
				System.out.println("client getMessageThread blocks because readline()");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

private void SetUpNetworking(){
	//write setupNetworking function dependently, initial reader and writer
	try{
		clientsocket=new Socket("127.0.0.1",2020);
		InputStreamReader streamReader=new InputStreamReader(clientsocket.getInputStream());
	    reader=new BufferedReader(streamReader);
        writer=new PrintWriter(clientsocket.getOutputStream());
     //   System.out.println("networking established...");
	}catch(IOException e){
		e.printStackTrace();
	}
}



	public void go(){
		//initial Stack，UI，open client socket，start getmessThread to get infor from server
		undoStack=new Stack();
		redoStack=new Stack();
		MainGUI();
		SetUpNetworking();
		GetMessage getmessThread=new GetMessage();
		Thread getMess=new Thread(getmessThread);
		getMess.start();
	   
		
	}
	
	public static void main(String[] args){
		Client myclient=new Client();
		myclient.go();
		 
	}
}
