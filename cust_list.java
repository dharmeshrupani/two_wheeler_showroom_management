
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;


public class cust_list extends JFrame implements ActionListener 
{

	private JPanel northPanel = new JPanel();

	private JPanel centerPanel = new JPanel();

	private JLabel label = new JLabel("Details");

	private JButton printButton;

	private JTable table;

	private TableColumn column = null;

	private JScrollPane scrollPane;
	
	JButton close;


	private ResultSetTableModel tableModel;

	private static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	private static final String DATABASE_URL = "jdbc:odbc:showroom";
  
	private static final String DEFAULT_QUERY = "SELECT * FROM cust";
	       
	        
	public cust_list() 
	{
		Container cp = getContentPane();
		
		close=new JButton("CLOSE");
		cp.add(close);
	
	
		try {
			tableModel = new ResultSetTableModel(JDBC_DRIVER, DATABASE_URL, DEFAULT_QUERY);
	
			try {
				tableModel.setQuery(DEFAULT_QUERY);
			}
			catch (SQLException sqlException) {
			}
		}
		catch (ClassNotFoundException classNotFound) {
		}
		catch (SQLException sqlException) {
		}
	
		table = new JTable(tableModel);
	
		table.setPreferredScrollableViewportSize(new Dimension(700, 150));
	
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
	
		scrollPane = new JScrollPane(table);

		for (int i = 0; i < 6; i++) 
		{
			column = table.getColumnModel().getColumn(i);
			if (i == 0) 
				column.setPreferredWidth(70);
			if (i == 1) 
				column.setPreferredWidth(20);
			if (i == 2)
				column.setPreferredWidth(150);
			if (i == 3)
				column.setPreferredWidth(50);
			if (i == 4)
				column.setPreferredWidth(50);
			if (i == 5)
				column.setPreferredWidth(40);
		}
		
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		northPanel.add(label);
		
		cp.add("North", northPanel);

		centerPanel.setLayout(new BorderLayout());
		
		printButton = new JButton("Print the Order");
		printButton.setToolTipText("Print");
		printButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		centerPanel.add(printButton, BorderLayout.NORTH);

		centerPanel.add(scrollPane, BorderLayout.CENTER);

		centerPanel.setBorder(BorderFactory.createTitledBorder("Customers:"));

		cp.add("Center", centerPanel);

		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Thread runner = new Thread() {
					public void run() {
						try {
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new print_cust(DEFAULT_QUERY));
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
		});
		
		
		close.addActionListener(this);
		close.setActionCommand("Close");
		close.setBounds(625,2,80,20);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(35,200,300,300);
		setVisible(true);

		pack();
		toFront();
	}
	
	public static void main(String[] args) 
	{
			JDialog.setDefaultLookAndFeelDecorated(true);

			new order_list();
	}
	
	
	
	 public void actionPerformed(ActionEvent ae)
    {
    	String str = ae.getActionCommand();
   
    	if(str.equals("Close"))
    	{
        		//setVisible (false);	
		this.dispose();   
    	}
    }
   
	
	
}