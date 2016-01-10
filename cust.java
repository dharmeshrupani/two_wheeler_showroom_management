import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class cust extends JFrame implements ActionListener
{
	JLabel message,cno,cname,add,age,phno,r,o,m,email;
	JTextField tfno,tfname,tfadd,tfage,tfr,tfo,tfm,tfemail;
	JButton ok,can,update,search,del,view;
	int updateenqno = 0;
	
	public cust()
	{               
		doupdate();
		message = new JLabel("CUSTOMER  DETAILS");
		cno = new JLabel("Customer No");
		cname = new JLabel("Customer Name");
		add = new JLabel("Address");
		age = new JLabel("Age");
		phno = new JLabel("Telephone No"); 
		r = new JLabel("R");
		o = new JLabel("O");
		m = new JLabel("M");
		email = new JLabel("Email");
		
		tfno = new JTextField(30);
		tfname = new JTextField(30);
		tfadd = new JTextField(20);
		tfage = new JTextField(10);
		tfr = new JTextField(20);
		tfo = new JTextField(20);
		tfm = new JTextField(20);
		tfemail = new JTextField(30);
		
		ok = new JButton("ADD");
		can = new JButton("CANCEL");
		update = new JButton("UPDATE");
		del = new JButton("DELETE");
		search = new JButton("VIEW");
		view = new JButton("SEARCH");
		
		
		tfno.setText(""+updateenqno);		
		can.addActionListener(this);
		can.setActionCommand("can");
		ok.addActionListener(this);
		ok.setActionCommand("Ok");
		update.addActionListener(this);
		update.setActionCommand("up");
		del.addActionListener(this);
		del.setActionCommand("del");
		search.addActionListener(this);
		search.setActionCommand("sea");
		view.addActionListener(this);
		view.setActionCommand("vi");
         	
		Container container = getContentPane();
		
		setSize(800,600);
		setTitle("Customer Details");
		container.setLayout(null);
                                
		setVisible(true);
		
		container.add(message);
		container.add(cno);
		container.add(cname);
		container.add(add);
		container.add(age);
		container.add(phno);
		container.add(r);
		container.add(o);
		container.add(m);
		container.add(email);
		container.add(tfno);
		container.add(tfname);
		container.add(tfadd);
		container.add(tfage);
		container.add(tfr);
		container.add(tfo);
		container.add(tfm);
		container.add(tfemail);
		container.add(ok);
		container.add(can);
		container.add(update);
		container.add(search);
		container.add(del);
		container.add(view);
                                container.setBackground( Color.pink);
		
		
		message.setBounds(300,40,200,50);
		cno.setBounds(50,100,100,30);
		cname.setBounds(50,150,100,30);
		add.setBounds(50,200,50,30);
		age.setBounds(50,250,50,30);
		phno.setBounds(50,300,100,30);
		r.setBounds(180,300,100,30);
		o.setBounds(330,300,100,30);
		m.setBounds(480,300,100,30);
		email.setBounds(50,350,50,30);

		tfno.setBounds(200,100,100,30);
		tfname.setBounds(200,150,150,30);
		tfadd.setBounds(200,200,150,30);
		tfage.setBounds(200,250,50,30);
		tfr.setBounds(200,300,100,30);
		tfo.setBounds(350,300,100,30);
		tfm.setBounds(500,300,100,30);
		tfemail.setBounds(200,350,300,30);
		ok.setBounds(100,450,85,30);
		search.setBounds(200,450,85,30);
		update.setBounds(300,450,85,30);
		del.setBounds(400,450,85,30);
		can.setBounds(500,450,85,30);
		view.setBounds(600,450,85,30);
	}
	public void doupdate()
	{
		Connection con = null;
		try 
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("Jdbc:Odbc:showroom");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select * from cust");
			while(rs.next())
			{
				updateenqno = rs.getInt(1);				
			}
			updateenqno = updateenqno +1;
	    }
	    catch (SQLException se) 
	    {
	    	System.out.println ("Ex1:"+se);
	    }
	    catch (Exception eg) 
	    {
	    	System.out.println ("Ex2:"+eg);
	    }
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
	    Connection con = null;
	    Statement stat = null;
	    
		if(str.equals("del"))
		{	 
			if(tfno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{
			   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			   con = DriverManager.getConnection("Jdbc:Odbc:showroom");
			 	System.out.println ("In delete Got Connection:"+con);
				
					Statement stmt3 = con.createStatement(); 
					stmt3.executeQuery("delete from cust where custid = "+Integer.parseInt(tfno.getText())+"");
					stmt3.close();
					con.close();
					
			  }
			  catch (SQLException e8) 
			  {
			  		JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
			    	tfno.setText(null);
			    	tfname.setText(null);
			    	tfage.setText(null);
			    	tfadd.setText(null);
			    	tfr.setText(null);
			    	tfo.setText(null);
			    	tfemail.setText(null);
			    	tfm.setText(null);
			    	
			    }
			    catch(Exception ex8)
			    {
			    }
			
		}
		
		
		if(str.equals("up"))
		{
			int intupdate = 0;
			if(tfno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please Enter the Customer Number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				try 
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("Jdbc:Odbc:showroom");
					System.out.println ("In Update Got Connection:"+con);
					PreparedStatement pstmt3 = con.prepareStatement("update cust set cname = '"+tfname.getText()+"' , address = '"+tfadd.getText()+"' , age = "+Integer.parseInt(tfage.getText())+" ,telr = "+Integer.parseInt(tfr.getText())+" ,telo = "+Integer.parseInt(tfo.getText())+" ,telmo = "+Double.parseDouble(tfm.getText())+",email = '"+tfemail.getText()+"' where custid = "+Integer.parseInt(tfno.getText()));
					intupdate = pstmt3.executeUpdate();				
				    pstmt3.close();
					con.close();
			    }
			    catch (SQLException e8) 
			    {
			    }
			    catch(Exception ex8)
			    {
			    }
			}
			if(intupdate == 1)
			{
				JOptionPane.showMessageDialog(null,"Record Updated SuccessFully");
				//tfno.setText(null);
			    tfname.setText(null);
			    tfage.setText(null);
			    tfadd.setText(null);
			    tfr.setText(null);
			    tfo.setText(null);
			    tfemail.setText(null);
			    tfm.setText(null);
			}			
		}
		
		
		
		if(str.equals("can"))
		{
			this.dispose();
			//new menu(1);
		}
		
		
		if(str.equals("vi"))
		{
			if(tfno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				System.out.println ("Got Connection :"+con);
				stat = con.createStatement();
				
			    ResultSet rs = stat.executeQuery("select * from cust");
			    while(rs.next())
			    {
			    	
			    	if(rs.getInt(1) == Integer.parseInt(tfno.getText()))
			    	{
			    		tfname.setEditable(true);
			    		tfadd.setEditable(true);
			    		tfage.setEditable(true);
			    		tfr.setEditable(true);
			    		tfo.setEditable(true);
			    		tfm.setEditable(true);
			    		tfemail.setEditable(true);
			    	
			    					    		
			    		
			    		tfname.setText(rs.getString(2));
			    		tfadd.setText(rs.getString(3));		    		
			    		tfage.setText(""+rs.getInt(4));			    		
			    		tfr.setText(""+rs.getInt(5));			    		
			    		tfo.setText(""+rs.getInt(6));
			    		tfm.setText(""+rs.getDouble(7));
			    		tfemail.setText(rs.getString(8));    		
			    	}
			    }			    
			    con.close();
			    stat.close();			    
			 }  
			catch (SQLException e7) 
		    {
		    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
		    	tfno.setText(null);
			    	tfname.setText(null);
			    	tfage.setText(null);
			    	tfadd.setText(null);
			    	tfr.setText(null);
			    	tfo.setText(null);
			    	tfemail.setText(null);
			    	tfm.setText(null);
		    }
		    catch (Exception ex5) 
		    {
		    	
		    }
		}
		
		if(str.equals("sea"))
		{
			Boolean flag1 = false;
			System.out.println ("In search");
			if(tfno.getText().equals(""))
			{
				System.out.println ("chk1");
				JOptionPane.showMessageDialog(null,"Please enter the custer no","Error",JOptionPane.ERROR_MESSAGE);
			}
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				System.out.println ("Got Connection :"+con);
				stat = con.createStatement();
				
			    ResultSet rs = stat.executeQuery("select * from cust");
			    while(rs.next())
			    {
			    	
			    	if(rs.getInt(1) == Integer.parseInt(tfno.getText()))
			    	{
			    		flag1 = true;
			    		tfname.setText(rs.getString(2));
			    		tfadd.setText(rs.getString(3));		    		
			    		tfage.setText(""+rs.getInt(4));			    		
			    		tfr.setText(""+rs.getInt(5));			    		
			    		tfo.setText(""+rs.getInt(6));
			    		tfm.setText(""+rs.getDouble(7));
			    		tfemail.setText(rs.getString(8));    		
			    		tfname.setEditable(false);
			    		tfadd.setEditable(false);
			    		tfage.setEditable(false);
			    		tfr.setEditable(false);
			    		tfo.setEditable(false);
			    		tfm.setEditable(false);
			    		tfemail.setEditable(false);
			    	}
			    }
			    if(flag1 == false)
			    {
			    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
			    	tfno.setText(null);
			    	tfname.setText(null);
			    	tfage.setText(null);
			    	tfadd.setText(null);
			    	tfr.setText(null);
			    	tfo.setText(null);
			    	tfemail.setText(null);
			    	tfm.setText(null);
			    }
			    
			    con.close();
			    stat.close();			    
		    }
		    catch (SQLException e1) 
		    {
		    	System.out.println ("Exception Generated1:"+e1);
		    	e1.printStackTrace();
		    }
		    catch (Exception ex) 
		    {
		    	
		    }
		}
		

		if(str.equals("Ok"))
		{
			doupdate();
			int intinsert=0;
		    Boolean flag = false;
		    if(tfno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				Statement stat1 = con.createStatement();
				ResultSet rs = stat1.executeQuery("select * from cust");
				while(rs.next())
				{
					if(rs.getInt(1) == Integer.parseInt(tfno.getText()))
					{
						flag = true;
					}
				}
				if(flag == false)
				{
					PreparedStatement pstmt = con.prepareStatement("insert into cust values (?,?,?,?,?,?,?,?)");
					
					
			    	pstmt.setInt(1,Integer.parseInt(tfno.getText()));
			    	pstmt.setString(2,tfname.getText());
			    	pstmt.setString(3,tfadd.getText());
			    	pstmt.setInt(4,Integer.parseInt(tfage.getText()));
			    	
			    	if(tfr.getText().equals(""))
			    	{			    		
			    		tfr.setText("0");
			    	}
			    	
			    	int rph = Integer.parseInt(tfr.getText());			    				    	
			    	pstmt.setInt(5,rph);
			    	
			    	
			    	if(tfo.getText().equals(""))
			    	{
			    		tfo.setText("0");
			    	}			    	
			    	int oph = Integer.parseInt(tfo.getText());
			    	pstmt.setInt(6,oph);			    	
			    	
			    	
			    	if(tfm.getText().equals(""))
			    	{
			    		tfm.setText("0");
			    	}
			    	double mph =  Double.parseDouble(tfm.getText());
			    	pstmt.setDouble(7,mph);
			    	
			    	pstmt.setString(8,tfemail.getText());
			    	
			    	intinsert = pstmt.executeUpdate();
			    	
			    	pstmt.close();
			    	con.close();

				}
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null,"Customer Already Exists","Error",JOptionPane.ERROR_MESSAGE);
					tfno.setText(null);
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
		    	tfno.setText(null);
		    	tfname.setText(null);
		    	tfadd.setText(null);
		    	tfage.setText(null);
		    	tfr.setText(null);
		    	tfo.setText(null);
		    	tfm.setText(null);
		    	tfemail.setText(null);	    	
		    }
                                    else
                                         JOptionPane.showMessageDialog(null,"Input value(s) are wrowng.","Error",JOptionPane.ERROR_MESSAGE);
		}	
		
	}
	
	public static void main(String args[])
	{
		new cust();
	}
}