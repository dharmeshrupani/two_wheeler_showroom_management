import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class enquiry extends JFrame implements ActionListener
{
	JLabel message,eno,cno,cname,add,phno,r,o,m,date,sex,eveh;
	JTextField tfeno,tfno,tfdate,veh;
	JRadioButton male,female;
	JComboBox cbveh;
	JButton ok,can,del,ok1;
	int updateenqno= 0;
	int searchrec = 0;
	
	public enquiry()
	{
		doupdate();
	    message = new JLabel("ENQUIRY DETAILS");
	    eno = new JLabel("Enquiry No");
		cno = new JLabel("Customer No");		
		date = new JLabel("Date");		
		eveh = new JLabel("Enquiry Vehicle");		
		tfeno = new JTextField(30);
		tfno = new JTextField(30);		
		tfdate = new JTextField(10);
                                			
		
		//male = new JRadioButton("Male");
		//female = new JRadioButton("Female");
		
		cbveh = new JComboBox();
				
		ok = new JButton("ADD");
		can = new JButton("CANCEL");
		del = new JButton("DELETE");
	                ok1 = new JButton("OK");
		
		
		
		
		tfeno.setText(""+updateenqno );
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		ok1.addActionListener(this);
		ok1.setActionCommand("show");
		can.addActionListener(this);
		can.setActionCommand("can");
		del.addActionListener(this);
		del.setActionCommand("del");
		
		
		Container c = getContentPane();
		
		setSize(600,500);
		setTitle("Enquiry");
		c.setLayout(null);
		setVisible(true);
		
		c.add(message);
		c.add(eno);
		c.add(cno);		
		c.add(date);		
		c.add(eveh);		
		c.add(tfeno);
		c.add(tfno);		
		c.add(tfdate);		
		c.add(cbveh);	
		c.add(ok);
		c.add(ok1);
		c.add(can);
		c.add(del);
	                c.setBackground( Color.pink);
		
		message.setBounds(200,40,200,50);
		eno.setBounds(50,100,100,30);
		cno.setBounds(50,150,100,30);
		date.setBounds(50,200,50,30);			
		
		eveh.setBounds(50,250,100,30);
		cbveh.setBounds(200,250,100,30);
		cbveh.addItem("Pulsar");
		cbveh.addItem("CT 100");
		cbveh.addItem("Discover DTS-i");
		cbveh.addItem("Wave DTS-i");

		tfeno.setBounds(200,100,100,30);
		tfno.setBounds(200,150,100,30);
		tfdate.setBounds(200,200,100,30);		
		ok1.setBounds(100,350,85,30);
		ok.setBounds(200,350,85,30);
		can.setBounds(400,350,85,30);
		del.setBounds(300,350,85,30);
		
		
	}
	
	
	
	public void doupdate()
	{
		Connection con = null;	    		
		try 
		{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");				
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select * from enquiry");
				while(rs.next())
				{
				  updateenqno = rs.getInt(1);				   		
				}
				updateenqno = updateenqno +1;
				
		}catch(SQLException se)
		{
			System.out.println ("Ex 1:"+se);
		}
		catch(Exception eg)
		{
			
			System.out.println ("Ex2:"+eg);
		}
	}				
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		Connection con = null;
		Statement stat4 = null;
		Boolean flag4 = false;
		
		//Statement stat = null;
		if(str.equals("can"))
		{
			this.dispose();
			//new menu();
		}
		
		
		if(str.equals("show"))
		{			
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                con = DriverManager.getConnection("Jdbc:Odbc:showroom");				
                System.out.println ("Got Connection "+con);
                stat4 = con.createStatement();
                System.out.println ("In Show");
				ResultSet rs4 = stat4.executeQuery("select * from enquiry");
				while(rs4.next())
				{
					if(rs4.getInt(1) == Integer.parseInt(tfeno.getText()))
					{   Container c = getContentPane();									  
					    veh= new JTextField();
                                                                                    //System.out.println ("cust id:"+rs4.getInt(2));					 
					    tfno.setText(""+rs4.getInt(2));
					    //System.out.println ("Date:"+rs4.getString(3));
					    tfdate.setText(rs4.getString(3));
                                                                                    //cbveh.setValue(rs4.getString(4));
                                                                                    veh.setText(rs4.getString(4)	);
                                                                                    c.add(veh);
                                                                                    veh.setBounds(200,250,100,30);
                                                                                       	
					}					
                }
                
                stat4.close();
                rs4.close();                
                con.close();
 		    } 		     
 		    catch(SQLException e10)
 		    {
 		    	//System.out.println ("Exception Generated:"+e10);
 		    	//JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
 		    }
		    catch (Exception ex10) 
		    {
		    }
		}
		if(str.equals("ok"))
		{
			int intinsert = 0;
			System.out.println ("in OK");
			Boolean flag =false;
			if(tfno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				System.out.println ("Got Connection:"+con);
				Statement stat3 = con.createStatement();
				ResultSet rs3 = stat3.executeQuery("select * from cust");
				while(rs3.next())
				{
					int nm = Integer.parseInt(tfno.getText());
					if(nm == rs3.getInt(1))
					{						
						flag = true;
                                                                                                
					}						
				}				
				if(flag == true)
		    
		    
		    	
		    
		        { 
		    	    PreparedStatement psmt= con.prepareStatement("insert into enquiry values (?,?,?,?)");
					psmt.setInt(1,Integer.parseInt(tfeno.getText()));
					psmt.setString(3,tfdate.getText());
					psmt.setInt(2,Integer.parseInt(tfno.getText()));					
					psmt.setString(4,(String)cbveh.getSelectedItem());
					intinsert = psmt.executeUpdate();
					psmt.close();
			    	con.close();		
			    	
		       }
			
		    }
		    catch (SQLException e) 
		    {
		    	
		    }
		    catch (Exception ex) 
		    {
		    }
		    
		    if(intinsert == 1  && flag == true)
		    {
		    	JOptionPane.showMessageDialog(null,"Record Inserted Successfully");
		    	tfeno.setText(null);
		    	tfdate.setText(null);
		    	tfno.setText(null);		    	
		    	doupdate();		    		
		    	tfeno.setText(""+updateenqno);
		    }
		    if(flag == false)
		    {
		    	JOptionPane.showMessageDialog(null,"Customer Does not Exists","Error",JOptionPane.ERROR_MESSAGE);
		    }
		}
		if(str.equals("del"))
		{
			if(tfeno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Enter the Enquiry Number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con= DriverManager.getConnection("Jdbc:Odbc:showroom");
				Statement stat3 = con.createStatement();
				stat3.executeQuery("delete from enquiry where enqno = "+Integer.parseInt(tfeno.getText())+"");
				stat3.close();
				con.close();
		    }
		    catch (SQLException e) 
		    {
		    	JOptionPane.showMessageDialog(null,"Record Deleted Sucessfully");
		    	tfeno.setText(null);
		    	tfno.setText(null);
		    	tfdate.setText(null);
		    	doupdate();
		    	tfeno.setText(""+updateenqno);
		    	
		    }
		    catch (Exception ex2) 
		    {
		    }
		}
	}
	
	
	
	public static void main(String args[])
	{
		new enquiry();
	}
	
}
