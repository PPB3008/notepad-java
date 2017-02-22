import java.awt.*;
import java.io.*;
import java.awt.event.*;
class NotePad
{
	public static void main(String[] args)
	{
		new NPmainframe();
	}
}

class NPmainframe
{    
     private Frame f;
	 private TextArea ta;
	 private MenuBar mb;
	 private Menu m1,m2;
	 private MenuItem mi1,mi2,mi3,mb1;
	 private FileDialog fl,fs;
	 private Dialog di1;
	 private Button b;
	 private Label l;
	 
	NPmainframe()
	{
		frame();
		event();
	}
	
	private void frame()
	{
		f=new Frame("NotePad");
		ta=new TextArea(500,500);
		mb=new MenuBar();
		m1=new Menu("File");
		m2=new Menu("Edit");
		mi1=new MenuItem("Save");
		mi2=new MenuItem("Open");
		mi3=new MenuItem("Exit");
		mb1=new MenuItem("Explore rows");
		fl=new FileDialog(f,"Open",FileDialog.LOAD);
		fs=new FileDialog(f,"Save",FileDialog.SAVE);
		di1=new Dialog(f,"Return rows");
		b=new Button("OK");
		l=new Label("");
		
		mb.add(m1);
		mb.add(m2);
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m2.add(mb1);
		f.setLayout(new BorderLayout());
		f.setMenuBar(mb);
		f.setVisible(true);
		f.setSize(1200,900);
		f.setLocation(400,120);
		f.add(ta);
		di1.add(l);
		di1.add(b);
		di1.setSize(300,150);
		di1.setLocation(400,120);
		di1.setLayout(new FlowLayout());
	}
	
	private void event()
	{
		f.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
			    System.exit(0);
		    }		
		});
		mi3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent f)
			{
			    System.exit(0);
			}
		});
		mi2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent g)
			{
				fl.setVisible(true);
				String s1=fl.getDirectory();
				String c1=fl.getFile();
				File f1=new File(s1,c1);
				ta.setText("");
				load(f1);
			}
		});
		mi1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent g)
			{
				fs.setVisible(true);
				String s2=fs.getDirectory();
				String c2=fs.getFile();
				File f2=new File(s2,c2);
				save(f2);				
			}
		});
		mb1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent f)
			{
			    di1.setVisible(true);
			    int a=ta.getRows();
			    l.setText("this notepad has"+a+"rows");
			}
		});
		di1.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
			    di1.setVisible(false);
		    }		
		});
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent h)
			{
			    di1.setVisible(false);
			}
		});
	}
	
	private void load(File f)
	{
		ta.setText("");
		BufferedReader br=null;
		//BufferedWriter bw=null;
		try
		{
		br=new BufferedReader(new FileReader(f));
		//bw=new BufferedWriter(new FileWriter(ta));
		String st=null;
		while((st=br.readLine())!=null)
		{
			ta.append(st+"\r\n");
		}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		try
		{
		br.close();
		//bw.close();
		}
		catch(IOException e)
		{
		    System.out.println(e.toString());	
		}
	}
	
	private void save(File f)
	{
		BufferedWriter bw=null;
		try
		{
		bw=new BufferedWriter(new FileWriter(f));
		String st=ta.getText();
		bw.write(st);
		bw.close();
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		try
		{
		bw.close();
		}
		catch(IOException e)
		{
		    System.out.println(e.toString());	
		}
	}
}