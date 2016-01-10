import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class invoice extends JFrame implements ActionListener
{
	JLabel message,rno,idate,ono,rcd,amt,pmode,mname,lblvname,cname;
	JTextField tfrno,tfidate,tfono,tfrcd,tfamt,tfpmode,tfmname,tfcname;
	JButton ok,can,print;
	JComboBox jcmname,jmode;
	public invoice()
	{
		message = new JLabel("INVOICE");
		rno = new JLabel("Invoice No");
		idate = new JLabel("Date");
		ono = new JLabel("Order No");
		rcd = new JLabel("Customer Name");
		amt = new JLabel("Engine No");
		pmode = new JLabel("Payment Mode");
		mname = new JLabel("Amount");
		lblvname =new JLabel("Vehicle Name");
		cname = new JLabel("Chasis No");
		
		tfrno = new JTextField(30);
		tfidate = new JTextField(30);
		tfono = new JTextField(30);
		tfrcd = new JTextField(30);
		tfamt = new JTextField(30);
		tfpmode = new JTextField(30);
		tfmname = new JTextField(30);
		tfcname = new JTextField(30);
		jcmname = new JComboBox();
		jmode = new JComboBox();
		
		ok = new JButton("ADD");
		can = new JButton("CANCEL"); 
		print = new JButton("PRINT");
		
		can.addActionListener(this);
		can.setActionCommand("can");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		print.addActionListener(this);
		print.setActionCommand("print");
		
		
		Container c = getContentPane();
	
		setSize(800,600);
		setTitle("INVOICE");
		c.setLayout(null);
		setVisible(true);
		
		c.add(message);
		c.add(rno);
		c.add(idate);
		c.add(ono);
		c.add(rcd);
		c.add(amt);
		c.add(pmode);
		c.add(mname);
		c.add(cname);
		
		c.add(tfrno);
		c.add(tfidate);
		c.add(tfono);
		c.add(tfrcd);
		c.add(tfamt);
		c.add(tfpmode);
		c.add(tfmname);
		c.add(tfcname);
		
		c.add(ok);
		c.add(can);
		c.add(print);
		c.add(jcmname);
		c.add(jmode);
		c.add(lblvname);
                                c.setBackground( Color.pink);
		
		
		
		message.setBounds(250,30,200,50);
		rno.setBounds(50,100,100,30);
		idate.setBounds(530,100,80,30);
		ono.setBounds(300,100,100,30);
		rcd.setBounds(50,150,150,30);
		amt.setBounds(50,300,100,30);
		pmode.setBounds(300,350,100,30);
		mname.setBounds(50,350,100,30);
		cname.setBounds(50,250,150,30);
		
		tfrno.setBounds(150,100,100,30);
		tfidate.setBounds(600,100,120,30);
		tfono.setBounds(380,100,100,30);
		tfrcd.setBounds(150,150,240,30);
		tfamt.setBounds(150,300,100,30);		
		tfmname.setBounds(150,350,100,30);
		tfcname.setBounds(150,250,100,30);
		lblvname.setBounds(50,200,150,30);
		jcmname.setBounds(150,200,100,30);
		jcmname.addItem(" -- Select --");
		jcmname.addItem("Pulsar");
		jcmname.addItem("CT 100");
		jcmname.addItem("Discover DTS-i");
		jcmname.addItem("Wave DTS-i");
		jmode.addItem(" -- Select --");
		jmode.addItem("Cash");
		jmode.addItem("Cheque");
		jmode.setBounds(400,350,100,30);
		
		ok.setBounds(200,450,85,30);
		can.setBounds(300,450,85,30);
		print.setBounds(400,450,85,30);
		
	}
	
	
		public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
	    Connection con = null;
	    Statement stat = null;
	    
	    
	    if(str.equals("ok"))
		{
			
			int intinsert=0;
		    Boolean flag = false;
		    if(tfrno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the Invoice number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				Statement stat1 = con.createStatement();
				ResultSet rs = stat1.executeQuery("select * from bill");
				while(rs.next())
				{
					if(rs.getInt(1) == Integer.parseInt(tfrno.getText()))
					{
						flag = true;
					}
				}
				if(flag == false)
				{
					PreparedStatement pstmt = con.prepareStatement("insert into bill values (?,?,?,?,?,?,?,?,?)");
					
					
			    	pstmt.setInt(1,Integer.parseInt(tfrno.getText()));
			    	pstmt.setInt(2,Integer.parseInt(tfono.getText()));
			    	pstmt.setString(3,tfidate.getText());
			    	pstmt.setString(4,tfrcd.getText());
			    	pstmt.setString(5,(String)jcmname.getSelectedItem());
			    	pstmt.setString(6,tfcname.getText());
			    	pstmt.setString(7,tfamt.getText());
			    	pstmt.setInt(8,Integer.parseInt(tfmname.getText()));
			    	pstmt.setString(9,(String)jmode.getSelectedItem());
			    	
			    	
			    	
			    	intinsert = pstmt.executeUpdate();
			    	
			    	pstmt.close();
			    	con.close();

				}
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null,"Record Already Exists","Error",JOptionPane.ERROR_MESSAGE);
					tfrno.setText(null);
				}
			}
		    catch (SQLException e4) 
		    {
		    }
		    catch (Exception ex3) 
		    {
		    	
		    }
		    if(intinsert == 1)
		    {
		    	JOptionPane.showMessageDialog(null,"Record Inserted Successfully");
		    	
		    	
		    	tfrno.setText(null);
		    	tfmname.setText(null);
		    	tfcname.setText(null);
		    	tfidate.setText(null);
		    	tfono.setText(null);
		    	tfrcd.setText(null);
		    	tfamt.setText(null);
		    	
		    	    	
		    }
		}
	    
	    if(str.equals("print"))
		{
				Thread runner = new Thread() {
					public void run() {
						try {
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new print_cust("Select * from quotation"));
							if (!prnJob.printDialog())
								return;
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							prnJob.print();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						catch (PrinterException ex) {
							System.out.println("Printing error: " + ex.toString());
						}
					}
				};
				runner.start();
		}
	    
	    if(str.equals("can"))
		{
			this.dispose();
			//new menu();
		}
	}
   public static void main(String args[])
	{
		new invoice();
	}
}

