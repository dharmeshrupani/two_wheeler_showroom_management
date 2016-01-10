import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class delop extends JFrame implements ActionListener 
{
	JButton del,can;
	JComboBox mb;
	JLabel lbl1,lbl2;
	JTextField tf;
	JPanel pan;
	public delop()
	{
		pan = new JPanel();
		pan.setLayout(null);
		
		lbl1 = new JLabel("Enter the Number");
		lbl1.setBounds(10,20,120,30);
		tf = new JTextField();
		tf.setBounds(170,25,80,25);
		
		lbl2 = new JLabel("Please Choose Option");
		lbl2.setBounds(10,80,160,30);
				
		mb = new JComboBox();
		mb.addItem("-- Select An Option -- ");
		mb.addItem(" Delete customer Record");
		mb.addItem(" Delete Order Record");		
		mb.setBounds(170,80,150,25);
		
		del =  new JButton("Delete");
		del.setBounds(40,150,80,35);
		can = new JButton("Cancel");
		can.setBounds(160,150,80,35);
		
		can.addActionListener(this);
		can.setActionCommand("can");
		del.addActionListener(this);
		del.setActionCommand("del");
		pan.add(lbl1);
		pan.add(tf);
		pan.add(lbl2);
		pan.add(mb);
		pan.add(del);
		pan.add(can);
                                pan.setBackground( new Color(225,225,255));
		
		getContentPane().add(pan);
		this.setSize(350,260);
		this.setLocation(200,150);		
		this.setTitle("Delete Option");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(str.equals("can"))
		{
			this.dispose();			
		}
		if(str.equals("del"))
		{
			System.out.println ("In Delete");
			String str2 = (String)mb.getSelectedItem();
			System.out.println (str2);
			if(str2.startsWith("-- Select"))
			{
				JOptionPane.showMessageDialog(null,"Please Choose Proper Option","InValid Option",JOptionPane.ERROR_MESSAGE);
			}
			if(str2.startsWith(" Delete customer"))
			{
				 Connection con = null;
	    		 PreparedStatement ptmt = null;
	    		 int intdel = 0;
	    
					if(tf.getText().equals(""))
				 	{	
						JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
					}
					try 
					{
			   			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  			con = DriverManager.getConnection("Jdbc:Odbc:showroom");
			 			System.out.println ("In delete Got Connection:"+con);
				
						ptmt = con.prepareStatement("delete from cust where custid = "+Integer.parseInt(tf.getText())+"");
						intdel = ptmt.executeUpdate();
						ptmt.close();
						con.close();
						
						if(intdel ==1)
						{
							JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
							tf.setText(null);
						}
						if(intdel == 0)
						{
							JOptionPane.showMessageDialog(null,"Record Does Not Found","Record",JOptionPane.ERROR_MESSAGE);
							tf.setText(null);
						}
					
			  		}
			  		catch (SQLException e8) 
			  		{				  		
			  			
				    	tf.setText(null);		    	
			    	
			    	}
			    	catch(Exception ex8)
			    	{
			    	}
			   
			}
			if(str2.startsWith(" Delete Order"))
			{
				Connection con1 = null;
	    		 PreparedStatement ptmt1 = null;
	    		 int intdel = 0;
	    
					if(tf.getText().equals(""))
				 	{	
						JOptionPane.showMessageDialog(null,"Please enter the order 	number","Error",JOptionPane.ERROR_MESSAGE);
				
					}
					try 
					{
			   			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			  			con1 = DriverManager.getConnection("Jdbc:Odbc:showroom");
			 			System.out.println ("In delete Got Connection:"+con1);
				
						ptmt1 = con1.prepareStatement("delete from order1 where odno = "+Integer.parseInt(tf.getText())+"");
						intdel = ptmt1.executeUpdate();
						ptmt1.close();
						con1.close();
						
						if(intdel ==1)
						{
							JOptionPane.showMessageDialog(null,"Record Deleted Successfully");
							tf.setText(null);
						}
						if(intdel == 0)
						{
							JOptionPane.showMessageDialog(null,"Record Does Not Found","Record",JOptionPane.ERROR_MESSAGE);
							tf.setText(null);
						}
					
			  		}
			  		catch (SQLException e8) 
			  		{				  		
			  			
				    	tf.setText(null);		    	
			    	
			    	}
			    	catch(Exception ex8)
			    	{
			    	}
			}
		}
	}
	
	public static void main(String args[])
	{
		new delop();
	}
}