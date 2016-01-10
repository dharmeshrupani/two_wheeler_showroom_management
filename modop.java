import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class modop extends JFrame implements ActionListener 
{
	JButton mod,can;
	JComboBox mb;
	JLabel lbl1,lbl2;
	JTextField tf;
	JPanel pan;
	
	public modop()
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
		mb.addItem(" Update customer Record");
		mb.addItem(" Update Order Record");	
		mb.setBounds(170,80,150,25);
		
		mod =  new JButton("Update");
		mod.setBounds(40,150,80,35);
		can = new JButton("Cancel");
		can.setBounds(160,150,80,35);
		
		can.addActionListener(this);
		can.setActionCommand("can");
		mod.addActionListener(this);
		mod.setActionCommand("mod");
		pan.add(lbl1);
		pan.add(tf);
		pan.add(lbl2);
		pan.add(mb);
		pan.add(mod);
		pan.add(can);
                                pan.setBackground( new Color(225,225,255));
		
		getContentPane().add(pan);
		this.setSize(350,260);
		this.setLocation(200,150);		
		this.setTitle("Modify Option");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(str.equals("can"))
		{
			this.dispose();			
		}	
		if(str.equals("mod"))
		{
			Connection con =null;
			Statement stat = null;
			Boolean flag1 = false;
			
			//search
			String str2 = (String)mb.getSelectedItem();
			if(str2.startsWith("-- Select"))
			{
				JOptionPane.showMessageDialog(null,"Please Choose Proper Option","InValid Option",JOptionPane.ERROR_MESSAGE);
			}
			if(str2.startsWith(" Update customer"))
			{
								
				System.out.println ("In search");
				if(tf.getText().equals(""))
				{
					System.out.println ("chk1");
					JOptionPane.showMessageDialog(null,"Please enter the customer no","Error",JOptionPane.ERROR_MESSAGE);
					
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
				    	
				    	int num = Integer.parseInt(tf.getText());
				    	if(rs.getInt(1) == num)
				    	{
				    				    			 
			    			flag1 = true;
			    			this.dispose();
			    			cust obj = new cust();
			    			obj.tfno.setVisible(true);
			    			
			    	
			    			obj.tfno.setText(tf.getText());	
			    			obj.tfname.setText(rs.getString(2));
			    			obj.tfadd.setText(rs.getString(3));		    		
			    			obj.tfage.setText(""+rs.getInt(4));			    		
			    			obj.tfr.setText(""+rs.getInt(5));			    		
			    			obj.tfo.setText(""+rs.getInt(6));
			    			obj.tfm.setText(""+rs.getInt(7));
			    			obj.tfemail.setText(rs.getString(8));    		
			    			
			    		}
			    	}
			    	if(flag1 == false)
			    	{
				    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
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
			
			if(str2.startsWith(" Update Order"))
			{
				
		
				
				System.out.println ("In search");
				if(tf.getText().equals(""))
				{
					System.out.println ("chk1");
					JOptionPane.showMessageDialog(null,"Please enter the order no","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				
				try 
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("Jdbc:Odbc:showroom");
					System.out.println ("Got Connection :"+con);
					stat = con.createStatement();
					
			    	ResultSet rs = stat.executeQuery("select * from order1");
			    	while(rs.next())
			    	{
				    	
				    	
				    	if(rs.getInt(1) == Integer.parseInt(tf.getText()))
				    	{
			    			this.dispose();
							order obj = new order();
			    			flag1 = true;
			    			obj.tfono.setText(tf.getText());
			    			obj.tfqno.setText(""+rs.getInt(2));
			    			obj.tfdate.setText(rs.getString(3));		    		
			    			obj.tfcno.setText(""+rs.getInt(4));			    		
			    			obj.tfcname.setText(rs.getString(5));			    		
			    			obj.tfage.setText(""+rs.getInt(6));
			    			obj.tfadd.setText(rs.getString(7));
			    			obj.tftno.setText(""+rs.getInt(8));    		
			    			obj.tfeddate.setText(rs.getString(13));
			    			obj.tfvprc.setText(""+rs.getInt(11));
			    			
			    		}
			    	}
			    	if(flag1 == false)
			    	{
				    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
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
		}		
	}
	
	public static void main(String args[])
	{
		new modop();
	}
}	