import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class menu extends JFrame implements ActionListener
{
	JMenuBar mb;
	JMenu master,tran,report,opt;
	JMenuItem cust,veh,enq,quot,ord,cheq,exit,del,mod,sea,tcust,tord,tenq,tquot,bill;
	JLabel lbl;
	ImageIcon img;
               
	public menu(int  a )
	{
		
		img = new ImageIcon("img1.jpg");
		lbl = new JLabel(img);
		lbl.setBounds(0,0,1000,1000);
		add(lbl);
	    //this.setBackground(Color.white);
		mb = new JMenuBar();
		//Menu Master
		master = new JMenu("Master");
		master.setMnemonic('m');
		mb.add(master);
		
		//Menu Items  of Master
		cust = new JMenuItem("Customer Details");
		master.add(cust);
		cust.setMnemonic('c');
		cust.addActionListener(this);
		cust.setActionCommand("cust");
		
		veh = new JMenuItem("Vehicle Details");
		master.add(veh);
		veh.setMnemonic('v');
		veh.addActionListener(this);
		veh.setActionCommand("veh");
		
		enq = new JMenuItem("Enquiry Details");
		master.add(enq);
		enq.setMnemonic('e');
		enq.addActionListener(this);
		enq.setActionCommand("enq");
		
		exit = new JMenuItem("Exit");
		master.add(exit);
		exit.setMnemonic('x');
		exit.addActionListener(this);
		exit.setActionCommand("exit");
		
		//Menu Transaction
		tran = new JMenu("Transaction");
		mb.add(tran);
		tran.setMnemonic('t');
		
		//Menu item of Tran
		quot = new JMenuItem("Quotation");
		tran.add(quot);
		quot.setMnemonic('q');
		quot.addActionListener(this);
		quot.setActionCommand("quot");
		
		ord = new JMenuItem("Order");
		tran.add(ord);
		ord.setMnemonic('o');
		ord.addActionListener(this);
		ord.setActionCommand("ord");
		
		/*cheq = new JMenuItem("Cheque Detail");
		tran.add(cheq);
		cheq.setMnemonic('c');
		cheq.addActionListener(this);
		cheq.setActionCommand("cheq");*/
		
		bill = new JMenuItem("Invoice");
		tran.add(bill);
		bill.setMnemonic('i');
		bill.addActionListener(this);
		bill.setActionCommand("bill");
		
		//Menu Option
		opt = new JMenu("Option");
		mb.add(opt);
		opt.setMnemonic('o');
		
		//Menu item of option
		del = new JMenuItem("Delete Record");
		opt.add(del);
		del.setMnemonic('d');
		del.addActionListener(this);
		del.setActionCommand("del");
		
		sea = new JMenuItem("Search Record");
		opt.add(sea);
		sea.setMnemonic('s');
		sea.addActionListener(this);
		sea.setActionCommand("sea");
		
		mod = new JMenuItem("Modify Record");
		opt.add(mod);
		mod.setMnemonic('m');
		mod.addActionListener(this);
		mod.setActionCommand("mod1");
		
		//Menu Report
		report= new JMenu("Report");
		mb.add(report);
		report.setMnemonic('r');
		
		//menu item of report
		tcust = new JMenuItem("Customer Report");
		report.add(tcust);
		tcust.setMnemonic('c');
		tcust.addActionListener(this);
		tcust.setActionCommand("tabc");
		
		tenq = new JMenuItem("Enquiry Report");
		report.add(tenq);
		tenq.setMnemonic('e');
		tenq.addActionListener(this);
		tenq.setActionCommand("tabe");
		
		tquot = new JMenuItem("Quotation Report");
		report.add(tquot);
		tquot.setMnemonic('q');
		tquot.addActionListener(this);
		tquot.setActionCommand("tabq");
		
		tord = new JMenuItem("Order Report");
		report.add(tord);
		tord.setMnemonic('o');
		tord.addActionListener(this);
		tord.setActionCommand("tabo");
		
		
                                
    	                setJMenuBar(mb);
                                if(a==0)
                                {
                                     mb.setVisible(false);
	                     this.setTitle(" Welcome At Showroom ");
                                }
                                else
                                       this.setTitle("Two Wheeler Showroom Management");
		this.setSize(800,570);
                                this.setBackground(Color.white);
                                this.setVisible(true);
                               
	}
	
	public static void main(String args[])
	{
		menu m=new menu(0);
                                new login(m);
                                //m.dispose();
	                //new menu(1);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(str.equals("del"))
		{
			new delop();
		}
		if(str.equals("sea"))
		{
			new seaop();
		}
		if(str.equals("mod1"))
		{
			new modop();
		}
		if(str.equals("cust"))
		{
			//this.dispose();
			new cust();
		}
		if(str.equals("veh"))
		{
			//this.dispose();
			new vehicle();
		}
		if(str.equals("enq"))
		{
			//this.dispose();
			new enquiry();
		}
		if(str.equals("quot"))
		{
			//this.dispose();
			new quotation();
		}
		if(str.equals("ord"))
		{
			//this.dispose();
			new order();
		}
		if(str.equals("cheq"))
		{
			//this.dispose();
			new cheque();
		}
		if(str.equals("bill"))
		{
			//this.dispose();
			new invoice();
		}
		if(str.equals("exit"))
		{
			System.exit(0);
		}
		if(str.equals("tabc"))
		{
			//this.dispose();
			new cust_list();
		}
		if(str.equals("tabe"))
		{
			//this.dispose();
			new enquiry_list();
		}
		if(str.equals("tabq"))
		{
			//this.dispose();
			new quotation_list();
		}
		if(str.equals("tabo"))
		{
			//this.dispose();
			new order_list();
		}
		
	}
}