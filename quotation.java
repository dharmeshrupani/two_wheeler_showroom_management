import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;


public class quotation extends JFrame implements ActionListener
{
	
	JLabel quotationno,enquiryno,modelname,rtotax,insurance,onroadprice,exprice,esntlacc,veh,date,message;	
	
	JTextField tfquotationno,tfenquiryno,tfrtotax,tfinsurance,tfonroadprice,tfexprice,tfesntlacc,tfdate;
	
	JButton save,print,can,ok;
	
		
	JComboBox combo;
	
	
	public quotation()
	{
	    message = new JLabel("QUOTATION DETAILS");
		quotationno = new JLabel("QUOTATION NO");
		enquiryno = new JLabel("ENQUIRY NO");
		modelname = new JLabel("MODEL NAME");
		rtotax = new JLabel("RTO Tax");
		insurance = new JLabel("INSURANCE");
		onroadprice = new JLabel("Total on road PRICE");
		exprice = new JLabel("Ex-ShowRoom PRICE");
		esntlacc = new JLabel("ESSENTIAL ACCESORIES");
		veh= new JLabel("VEHICLE");
		date= new JLabel("DATE");
		
		save = new JButton("SAVE");
		print = new JButton("PRINT");
		
		can = new JButton("CANCEL");
		ok = new JButton("OK");
		can.addActionListener(this);
		can.setActionCommand("can");
		save.addActionListener(this);
		save.setActionCommand("save");
		print.addActionListener(this);
		print.setActionCommand("pri");
		
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		
		
		tfquotationno = new JTextField(10);
		tfenquiryno = new JTextField(10);
		tfrtotax = new JTextField(10);
		tfinsurance = new JTextField(10);
		tfonroadprice = new JTextField(10);
		tfexprice = new JTextField(10);
		tfesntlacc = new JTextField(10);
		tfdate = new JTextField(10);
		
		
		combo = new JComboBox();
		
		combo.addItem("-- Select An Option -- ");
		combo.addItem("Pulsar");
		combo.addItem("CT 100");
		combo.addItem("Discover DTS-i");
		combo.addItem("Wave DTS-i");
		
		
		Container container = getContentPane();
		
		
		/*setSize(1024,768);
		setVisible(true);
		
		*/
		container.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("QUOTATION");
		
        container.add(message);
		container.add(quotationno);
		container.add(enquiryno);
		container.add(rtotax);
		container.add(insurance);
		container.add(onroadprice);
		container.add(modelname);
		container.add(exprice);
		container.add(esntlacc);
		container.add(veh);
		container.add(date);
					
		container.add(tfexprice);
		container.add(tfenquiryno);
		container.add(tfquotationno);
		container.add(tfrtotax);
		container.add(tfinsurance);
		container.add(tfonroadprice);
		container.add(tfesntlacc);
		container.add(tfdate);
		
		container.add(combo);
		
		container.add(save);
		container.add(print);
		container.add(can);
		container.add(ok);
                                container.setBackground( Color.pink);
		
		
	    message.setBounds(300,40,200,50);
		quotationno.setBounds(50,100,100,30);
		tfquotationno.setBounds(200,100,100,30);
		
		date.setBounds(400,100,50,30);
		tfdate.setBounds(450,100,150,30);
		
		enquiryno.setBounds(50,150,100,30);
		tfenquiryno.setBounds(200,150,100,30);
		
		veh.setBounds(50,200,100,30);
		combo.setBounds(200,200,150,30);
		
		
		exprice.setBounds(50,250,150,30);
		tfexprice.setBounds(200,250,150,30);

		rtotax.setBounds(50,300,100,30);
		tfrtotax.setBounds(200,300,150,30);
		
		insurance.setBounds(50,350,100,30);
		tfinsurance.setBounds(200,350,150,30);


		esntlacc.setBounds(50,400,150,30);
		tfesntlacc.setBounds(200,400,150,30);
		
		onroadprice.setBounds(50,450,150,30);
		tfonroadprice.setBounds(200,450,150,30);	
		
		save.setBounds(150,500,100,30);
		print.setBounds(260,500,100,30);
		can.setBounds(370,500,100,30);
		ok.setBounds(400,200,70,30);
		
		setSize(800,570);
		setVisible(true);
		
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(str.equals("can"))
		{
			this.dispose();
			//new menu();
		}
		if(str.equals("save"))
		{
			Connection con = null;
			Statement stat1 = null;
			int intinsert=0;
		    Boolean flag = false;
		    if(tfquotationno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the quotation number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{	
						
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				stat1 = con.createStatement();
				ResultSet rs = stat1.executeQuery("select * from quotation");
				System.out.println ("chk1");
				while(rs.next())
				{
					if(rs.getInt(1) == Integer.parseInt(tfquotationno.getText()))
					{
						flag = true;
					}
				}
				if(flag == false)
				{
					PreparedStatement pstmt = con.prepareStatement("insert into quotation values (?,?,?,?,?,?,?,?,?)");
			    	pstmt.setInt(1,Integer.parseInt(tfquotationno.getText()));
			    	pstmt.setInt(3,Integer.parseInt(tfenquiryno.getText()));
			    	pstmt.setString(2,tfdate.getText());
			    	pstmt.setString(4,(String)combo.getSelectedItem());			    	
			    	pstmt.setInt(5,Integer.parseInt(tfexprice.getText()));
			    	pstmt.setInt(6,Integer.parseInt(tfrtotax.getText()));
			    	pstmt.setInt(7,Integer.parseInt(tfinsurance.getText()));
			    	pstmt.setInt(8,Integer.parseInt(tfesntlacc.getText()));
			    	pstmt.setInt(9,Integer.parseInt(tfonroadprice.getText()));
			    	
			    	intinsert = pstmt.executeUpdate();
			    	pstmt.close();
			    	con.close();

				}
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null,"Quotation Already Exists","Error",JOptionPane.ERROR_MESSAGE);
					tfquotationno.setText(null);
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
		    	tfquotationno.setText(null);
		    	tfenquiryno.setText(null);
		    	tfrtotax.setText(null);
		    	tfinsurance.setText(null);
		    	tfonroadprice.setText(null);
		    	tfexprice.setText(null);
		    	tfesntlacc.setText(null);
		    	tfdate.setText(null);	    	
		    }
			
			
		}
		if(str.equals("pri"))
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

		if(str.equals("ok"))
		{
			Connection con;
			Statement stat;
			Boolean flag1 = false;
			String str3 = (String)combo.getSelectedItem();
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select * from priceinfo");
				while(rs.next())
				{
					if(str3.equals(rs.getString(1)))
					{
						System.out.println ("chk1");

						flag1 = true;
						tfexprice.setText(""+rs.getInt(2));
						tfinsurance.setText(""+rs.getInt(4));
						tfrtotax.setText(""+rs.getInt(3));
						tfonroadprice.setText(""+rs.getInt(6));
						tfesntlacc.setText(""+rs.getInt(5));
						
					}
				}
				stat.close();
				con.close();
			}
		    catch (Exception ex) 
		    {
		    }
		    if(flag1 == false)
		    {
		    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
		    }
		    
		}

		
	}

public static void main(String args[])
{
	new quotation();
}
	
}	