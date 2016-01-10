import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class seaop extends JFrame implements ActionListener 
{
	JButton sea,can;
	JComboBox mb;
	JLabel lbl1,lbl2;
	JTextField tf;
	JPanel pan;
	public seaop()
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
		mb.addItem(" Search customer Record");
		mb.addItem(" Search Order Record");		
		mb.addItem(" Search Enquiry Record");
		mb.addItem(" Search Quotation Record");
		mb.setBounds(170,80,150,25);
		
		sea =  new JButton("Search");
		sea.setBounds(40,150,80,35);
		can = new JButton("Cancel");
		can.setBounds(160,150,80,35);
		
		can.addActionListener(this);
		can.setActionCommand("can");
		sea.addActionListener(this);
		sea.setActionCommand("sea");
		pan.add(lbl1);
		pan.add(tf);
		pan.add(lbl2);
		pan.add(mb);
		pan.add(sea);
		pan.add(can);
                                pan.setBackground( new Color(225,225,255));
		
		getContentPane().add(pan);
		this.setSize(350,260);
		this.setLocation(200,150);		
		this.setTitle("Search Option");
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(str.equals("can"))
		{
			this.dispose();			
		}	
		if(str.equals("sea"))
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
			if(str2.startsWith(" Search customer"))
			{
								
				System.out.println ("In search");
				if(tf.getText().equals(""))
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
				    	
				    	
				    	if(rs.getInt(1) == Integer.parseInt(tf.getText()))
				    	{
			    			this.dispose();
							cust obj = new cust();
			    			flag1 = true;
			    			obj.tfno.setVisible(false);
			    			obj.tfname.setText(rs.getString(2));
			    			obj.tfadd.setText(rs.getString(3));		    		
			    			obj.tfage.setText(""+rs.getInt(4));			    		
			    			obj.tfr.setText(""+rs.getInt(5));			    		
			    			obj.tfo.setText(""+rs.getInt(6));
			    			obj.tfm.setText(""+rs.getInt(7));
			    			obj.tfemail.setText(rs.getString(8)); 
			    			
			    			obj.tfname.setEditable(false);
			    			obj.tfadd.setEditable(false);
			    			obj.tfage.setEditable(false);
			    			obj.tfr.setEditable(false);
			    			obj.tfo.setEditable(false);
			    			obj.tfm.setEditable(false);
			    			obj.tfemail.setEditable(false);
			    			obj.update.setEnabled(false);
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
				//search
			}
			
			if(str2.startsWith(" Search Order"))
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
			    			obj.tfono.setVisible(false);
			    			obj.tfqno.setText(""+rs.getInt(2));
			    			obj.tfdate.setText(rs.getString(3));		    		
			    			obj.tfcno.setText(""+rs.getInt(4));			    		
			    			obj.tfcname.setText(rs.getString(5));			    		
			    			obj.tfage.setText(""+rs.getInt(6));
			    			obj.tfadd.setText(rs.getString(7));
			    			obj.tftno.setText(""+rs.getInt(8));    		
			    			obj.tfeddate.setText(rs.getString(13));
			    			obj.tfvprc.setText(""+rs.getInt(11));
			    			obj.up.setEnabled(false);
			    			obj.jcvname.setSelectedItem(rs.getString(9));
			    			 
			    			obj.jccolor.setSelectedItem(rs.getString(10));
			    			obj.jcadd.setSelectedItem(rs.getString(12));
			    			obj.tfqno.setEditable(false);
			    			obj.tfdate.setEditable(false);		    		
			    			obj.tfcno.setEditable(false);		    		
			    			obj.tfcname.setEditable(false);			    		
			    			obj.tfage.setEditable(false);
			    			obj.tfadd.setEditable(false);
			    			obj.tftno.setEditable(false);    		
			    			obj.tfeddate.setEditable(false);
			    			obj.tfvprc.setEditable(false);
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
			
			if(str2.startsWith(" Search Enquiry"))
			{
				System.out.println ("In search");
				if(tf.getText().equals(""))
				{
					System.out.println ("chk1");
					JOptionPane.showMessageDialog(null,"Please enter the enquiry no","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				
				try 
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("Jdbc:Odbc:showroom");
					System.out.println ("Got Connection :"+con);
					stat = con.createStatement();
					
			    	ResultSet rs = stat.executeQuery("select * from enquiry");
			    	while(rs.next())
			    	{
				    	
				    	
				    	if(rs.getInt(1) == Integer.parseInt(tf.getText()))
				    	{
			    			this.dispose();
							enquiry obj = new enquiry();
			    			flag1 = true;
			    			obj.tfeno.setVisible(false);
			    			obj.tfno.setText(""+rs.getInt(2));
			    			obj.tfdate.setText(rs.getString(3));			    		
			    			obj.tfno.setEditable(false);
			    			obj.tfdate.setEditable(false);
			    			
			    		}
			    		if(flag1 == false)
			    		{
					    	JOptionPane.showMessageDialog(null,"Record Not Found","Error",JOptionPane.ERROR_MESSAGE);
					    }
				    
				    	con.close();
			    		stat.close();			    
		    		}
		    	}
		    	catch (SQLException e1) 
		    	{
				   	System.out.println ("Exception Generated1:"+e1);
				   	e1.printStackTrace();
				}
		    	catch (Exception ex) 
		    	{
				    	
				}			
				//search
			}
			
			if(str2.startsWith(" Search Quotation"))
			{
				System.out.println ("In search");
				if(tf.getText().equals(""))
				{
					System.out.println ("chk1");
					JOptionPane.showMessageDialog(null,"Please enter the quotation no","Error",JOptionPane.ERROR_MESSAGE);
					
				}
				
				try 
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("Jdbc:Odbc:showroom");
					System.out.println ("Got Connection :"+con);
					stat = con.createStatement();
					
			    	ResultSet rs = stat.executeQuery("select * from quotation");
			    	while(rs.next())
			    	{
				    	
				    	
				    	if(rs.getInt(1) == Integer.parseInt(tf.getText()))
				    	{
			    			this.dispose();
							quotation obj = new quotation();
			    			flag1 = true;
			    			obj.tfquotationno.setVisible(false);
			    			obj.tfenquiryno.setText(""+rs.getInt(3));			    					    		
			    			obj.tfrtotax.setText(""+rs.getInt(6));   		
			    			obj.tfinsurance.setText(""+rs.getInt(7));    		
			    			obj.tfonroadprice.setText(""+rs.getInt(9));			    			
			    			obj.tfexprice.setText(""+rs.getInt(5)); 
			    			obj.tfesntlacc.setText(""+rs.getInt(8));   		
			    			obj.tfdate.setText(rs.getString(2));
			    			obj.tfenquiryno.setEditable(false);			    					    		
			    			obj.tfrtotax.setEditable(false); 		
			    			obj.tfinsurance.setEditable(false);  		
			    			obj.tfonroadprice.setEditable(false);		    			
			    			obj.tfexprice.setEditable(false);
			    			obj.tfesntlacc.setEditable(false);  		
			    			obj.tfdate.setEditable(false);
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
		new seaop();
	}
}