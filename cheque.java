import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class cheque extends JFrame implements ActionListener
{
	JLabel message,cno,idate,bank,amt;
	JTextField tfcno,tfidate,tfbank,tfamt;
	JButton ok,can;
	public cheque()
	{
		message = new JLabel("CHEQUE  DETAILS");
		cno = new JLabel("Cheque No");
		idate = new JLabel("Issue Date");
		bank = new JLabel("Bank Name");
		amt = new JLabel("Amount");
		
		tfcno = new JTextField(30);
		tfidate = new JTextField(30);
		tfbank = new JTextField(30);
		tfamt = new JTextField(30);
		
		ok = new JButton("OK");
		can = new JButton("CANCEL"); 
		
		can.addActionListener(this);
		can.setActionCommand("can");
		ok.addActionListener(this);
		ok.setActionCommand("ok");
		
		Container c = getContentPane();
	
		setSize(600,400);
		setTitle("cheque");
		c.setLayout(null);
		setVisible(true);
		
		c.add(message);
		c.add(cno);
		c.add(idate);
		c.add(bank);
		c.add(amt);
		
		c.add(tfcno);
		c.add(tfidate);
		c.add(tfbank);
		c.add(tfamt);
		
		c.add(ok);
		c.add(can);
                                c.setBackground( new Color(225,225,255));
		
		message.setBounds(250,30,200,50);
		cno.setBounds(50,100,100,30);
		idate.setBounds(300,100,100,30);
		bank.setBounds(50,150,100,30);
		amt.setBounds(50,200,50,30);
		
		tfcno.setBounds(150,100,100,30);
		tfidate.setBounds(400,100,100,30);
		tfbank.setBounds(150,150,100,30);
		tfamt.setBounds(150,200,100,30);
		
		ok.setBounds(200,300,85,30);
		can.setBounds(300,300,85,30);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		Connection con= null;
		Statement stat = null;
		if(str.equals("can"))
		{
			this.dispose();
			//new menu();
		}
		if(str.equals("ok"))
		{
			System.out.println ("in ok");
			int intinsert = 0;
			Boolean flag = false;
			if(tfcno.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please enter the customer number","Error",JOptionPane.ERROR_MESSAGE);
				
			}
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("Jdbc:Odbc:showroom");
				System.out.println ("Got Connection:"+con);
				Statement stat1 = con.createStatement();
				System.out.println ("statement"+stat1)				;
				
				ResultSet rs = stat1.executeQuery("select * from cheque");
		
				while(rs.next())
				{
					System.out.println ("chk1");
					int nm = Integer.parseInt(tfcno.getText());
					if(nm == rs.getInt(1))
					{
						
						flag = true;
					}
				}
				System.out.println ("flaf"+flag);
				if(flag == false)
				{
					
					PreparedStatement pstmt = con.prepareStatement("insert into cheque values(?,?,?,?)");
					pstmt.setInt(1,Integer.parseInt(tfcno.getText()));
					pstmt.setString(2,tfidate.getText());
					pstmt.setString(3,tfbank.getText());
					pstmt.setInt(4,Integer.parseInt(tfamt.getText()));
					intinsert = pstmt.executeUpdate();
					pstmt.close();
					con.close();
				}
				/*if(intinsert==1)
				{
					JOptionPane.showMessageDialog(null,"Record inserted sucessfully");
				                 
                                                                }*/
				if(flag == true)
				{
					JOptionPane.showMessageDialog(null,"Cheque no. Already Exists","Error",JOptionPane.ERROR_MESSAGE);
					tfcno.setText(null);
				}
			}
			catch(SQLException se)
			{
				
			}
			catch(Exception ex)
			{
				
			}
			if(intinsert == 1)
		    {
		    	JOptionPane.showMessageDialog(null,"Record Inserted Successfully");
                                                this.dispose();
		    	tfcno.setText(null);
		    	tfidate.setText(null);
		    	tfbank.setText(null);
		    	tfamt.setText(null);
		    }
		}
	}
	public static void main(String args[])
	{
		new cheque();
	}
}

