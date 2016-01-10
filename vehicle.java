import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class vehicle extends JFrame implements ActionListener,ItemListener
{
	JLabel message,mname,cap,cc,eng,stroke,bhp,volt,rpm,weight,kg,gear,color;
	JTextField tfcap,tfeng,tfbhp,tfvolt,tfrpm,tfweight,tfgear;
	JButton ok,can;
	JComboBox jcmname,jccolor;
	
	public vehicle()
	{
		message = new JLabel("VEHICLE  DETAILS");
		mname = new JLabel("Model Name");
		cap = new JLabel("Capacity");
		cc = new JLabel("CC");
		eng = new JLabel("Engine");
		stroke = new JLabel("Stroke"); 
		bhp = new JLabel("BHP");
		volt = new JLabel("Volts");
		rpm = new JLabel("RPM");
		weight = new JLabel("Weight");
		kg = new JLabel("Kg");
		gear = new JLabel("Gears");
		color = new JLabel("Color");
				
		jcmname = new JComboBox();
		
		tfcap = new JTextField(30);
		tfeng = new JTextField(20);
		tfbhp = new JTextField(10);
		tfvolt = new JTextField(20);
		tfrpm = new JTextField(20);
		tfweight = new JTextField(20);
		tfgear = new JTextField(30);
		
		jccolor = new JComboBox();
		
		ok = new JButton("OK");
		can = new JButton("CANCEL");
		
		can.addActionListener(this);
		can.setActionCommand("can");
		ok.addActionListener(this);
		ok.setActionCommand("Ok");
		
		Container c = getContentPane();
		
		setSize(800,600);
		setTitle("Vehicle Details");
		c.setLayout(null);
		setVisible(true);
		
		c.add(message);
		c.add(mname);
		c.add(cap);
		c.add(cc);
		c.add(eng);
		c.add(stroke);
		c.add(bhp);
		c.add(volt);
		c.add(rpm);
		c.add(weight);
		c.add(kg);
		c.add(gear);
		c.add(color);
		
		c.add(jcmname);
				
		c.add(tfcap);
		c.add(tfeng);
		c.add(tfbhp);
		c.add(tfvolt);
		c.add(tfrpm);
		c.add(tfweight);
		c.add(tfgear);
		
		c.add(jccolor);
				
		c.add(ok);
		c.add(can);
		c.setBackground( Color.pink);
			
		message.setBounds(300,40,200,50);
		mname.setBounds(50,100,100,30);
		cap.setBounds(50,150,100,30);
		cc.setBounds(275,150,50,30);
		eng.setBounds(50,200,50,30);
		stroke.setBounds(275,200,100,30);
		bhp.setBounds(50,250,100,30);
		volt.setBounds(50,300,100,30);
		rpm.setBounds(50,350,100,30);
		weight.setBounds(50,400,50,30);
		kg.setBounds(275,400,100,30);
		gear.setBounds(450,150,150,30);
		color.setBounds(450,200,100,30);
		
		jcmname.setBounds(200,100,100,30);
		jcmname.addItem(" -- Select --");
		jcmname.addItem("Pulsar");
		jcmname.addItem("CT 100");
		jcmname.addItem("Discover DTS-i");
		jcmname.addItem("Wave DTS-i");
		
		tfcap.setBounds(200,150,70,30);
		tfeng.setBounds(200,200,70,30);
		tfbhp.setBounds(200,250,70,30);
		tfvolt.setBounds(200,300,70,30);
		tfrpm.setBounds(200,350,70,30);
		tfweight.setBounds(200,400,70,30);
		tfgear.setBounds(500,150,70,30);
		
		jccolor.setBounds(500,200,100,30);
		jccolor.addItem("Black");
		jccolor.addItem("Blue");
		jccolor.addItem("Red");
		jccolor.addItem("Grey");
			
		ok.setBounds(270,480,85,30);
		can.setBounds(370,480,85,30);
		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str =ae.getActionCommand();
		if(str.equals("Ok"))
		{
			String str1 = (String)jcmname.getSelectedItem();
	
	
		Connection con = null;
		Statement stat = null;
		
		
		if(str1.equals("Pulsar") || str1.equals("CT 100") || str1.equals("Discover DTS-i") || str1.equals("Wave DTS-i"))
		{
			try 
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				System.out.println ("Got Connection :"+con);
				stat = con.createStatement();				
			    ResultSet rs = stat.executeQuery("select * from vehicle");	
			    System.out.println ("chk1");
			    while(rs.next())
			    {
			       if(str1.equals(rs.getString(1)))
                	{
                	
                		tfcap.setText(""+rs.getInt(2));
                		tfeng.setText(""+rs.getInt(3));
                		tfbhp.setText(""+rs.getInt(4));
                		tfvolt.setText(""+rs.getInt(5));
                		tfrpm.setText(""+rs.getInt(6));
                		tfweight.setText(""+rs.getInt(7));
                		tfgear.setText(""+rs.getInt(8));
                	
		            }
		        }    
                
                          stat.close();
                          con.close();
               		    
		    }
		    catch (Exception ex) 
		    {
		    }
			
		 }
		 else
		 {
		 	JOptionPane.showMessageDialog(null,"Please Choose Model Name","Error",JOptionPane.ERROR_MESSAGE);
		 }
		}
		
		if(str.equals("can"))
		{
			this.dispose();
			//new menu(1);
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		
	}
	
	public static void main(String args[])
	{
		new vehicle();
	}
}