import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import bank.model.CustomerAccount;
import bank.model.Customer;
import bank.command.CommandInvoker;
import bank.repository.CustomerRepository;
import bank.template.LodgementTransaction;
import bank.template.WithdrawalTransaction;
import bank.facade.Authentication;
import bank.facade.AdminOperations;

public class Main extends JFrame{

	private final CommandInvoker invoker = new CommandInvoker();
	private final CustomerRepository repository = CustomerRepository.getInstance();
    private int position = 0;
	private String password;
	private Customer customer = null;
	private CustomerAccount acc = new CustomerAccount();
	JFrame f, f1;
	 JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
	 JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
		JLabel customerIDLabel, passwordLabel;
		JTextField customerIDTextField, passwordTextField;
	Container content;
		Customer e;


	 JPanel panel2;
		JButton add;
		String 	PPS,firstName,surname,DOB,CustomerID;
	
	public static void main(String[] args)
	{
		Main driver = new Main();
		driver.menuStart();
	}
	
	public void menuStart()
	{
		   /*The menuStart method asks the user if they are a new customer, an existing customer or an admin. It will then start the create customer process
		  if they are a new customer, or will ask them to log in if they are an existing customer or admin.*/
		
			
		
			
			f = new JFrame("User Type");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			f.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) { System.exit(0); }
			});

			JPanel userTypePanel = new JPanel();
			final ButtonGroup userType = new ButtonGroup();
			JRadioButton radioButton;
			userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
			radioButton.setActionCommand("Customer");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("Administrator"));
			radioButton.setActionCommand("Administrator");
			userType.add(radioButton);
			
			userTypePanel.add(radioButton = new JRadioButton("New Customer"));
			radioButton.setActionCommand("New Customer");
			userType.add(radioButton);

			JPanel continuePanel = new JPanel();
			JButton continueButton = new JButton("Continue");
			continuePanel.add(continueButton);

			Container content = f.getContentPane();
			content.setLayout(new GridLayout(2, 1));
			content.add(userTypePanel);
			content.add(continuePanel);



			continueButton.addActionListener(new ActionListener(  ) {
				public void actionPerformed(ActionEvent ae) {
					String user = userType.getSelection().getActionCommand(  );
					
					//if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
					if(user.equals("New Customer"))
					{
						f.dispose();		
						f1 = new JFrame("Create New Customer");
						f1.setSize(400, 300);
						f1.setLocation(200, 200);
						f1.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent we) { System.exit(0); }
						});
							Container content = f1.getContentPane();
							content.setLayout(new BorderLayout());
							
							firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
							surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
							pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
							dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
							firstNameTextField = new JTextField(20);
							surnameTextField = new JTextField(20);
							pPSTextField = new JTextField(20);
							dOBTextField = new JTextField(20);
							JPanel panel = new JPanel(new GridLayout(6, 2));
							panel.add(firstNameLabel);
							panel.add(firstNameTextField);
							panel.add(surnameLabel);
							panel.add(surnameTextField);
							panel.add(pPPSLabel);
							panel.add(pPSTextField);
							panel.add(dOBLabel);
							panel.add(dOBTextField);
								
							panel2 = new JPanel();
							add = new JButton("Add");
							
							 add.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {


						PPS = pPSTextField.getText();
						firstName = firstNameTextField.getText();
						surname = surnameTextField.getText();
						DOB = dOBTextField.getText();
						password = "";

						CustomerID = "ID"+PPS ;

								f1.dispose();

								boolean loop = true;
								while(loop){
								 password = JOptionPane.showInputDialog(null, "Enter 7 character Password;");

								 if(password.length() != 7)//Making sure password is 7 characters
								    {
								    	JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
								    }
								 else
								 {
									 loop = false;
								 }
								}

							    ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount> ();
										Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);

										repository.addCustomer(customer);

										JOptionPane.showMessageDialog(null, "CustomerID = " + CustomerID +"\n Password = " + password  ,"Customer created.",  JOptionPane.INFORMATION_MESSAGE);
										menuStart();
								}
							});						
							JButton cancel = new JButton("Cancel");					
							cancel.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									f1.dispose();
									menuStart();
								}
							});	
							
							panel2.add(add);
							panel2.add(cancel);
							
							content.add(panel, BorderLayout.CENTER);
							content.add(panel2, BorderLayout.SOUTH);
					
							f1.setVisible(true);		
						
					}
					
					
					//------------------------------------------------------------------------------------------------------------------
					
					//if user select ADMIN----------------------------------------------------------------------------------------------
					if(user.equals("Administrator")	)
					{
					Authentication auth = new Authentication();
					if(auth.authenticateAdmin(f)) {
						f.dispose();
						admin();
					} else {
						f.dispose();
						menuStart();
					}
					}
					//----------------------------------------------------------------------------------------------------------------
					
					
					
					//if user selects CUSTOMER ---------------------------------------------------------------------------------------- 
					if(user.equals("Customer")	)
					{
					Authentication auth = new Authentication();
					Customer c = auth.authenticateCustomer(f);
					if(c != null) {
						f.dispose();
						customer(c);
					} else {
						f.dispose();
						menuStart();
					}
					}
					//-----------------------------------------------------------------------------------------------------------------------
				}
			});f.setVisible(true);	
	}
	

	
	public void admin()
	{
		new AdminOperations(this, this::admin, this::menuStart).show();
	}
	
	public void customer(Customer e1)
	{	
		f = new JFrame("Customer Menu");
		e = e1;
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		if(e.getAccounts().isEmpty())
		{
			JOptionPane.showMessageDialog(f, "This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. " ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
			f.dispose();				
			menuStart();
		}
		else
		{
		JPanel buttonPanel = new JPanel();
		JPanel boxPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		
		JLabel label = new JLabel("Select Account:");
		labelPanel.add(label);
		
		JButton returnButton = new JButton("Return");
		buttonPanel.add(returnButton);
		JButton continueButton = new JButton("Continue");
		buttonPanel.add(continueButton);
		
		JComboBox<String> box = new JComboBox<String>();
	    for (int i =0; i < e.getAccounts().size(); i++)
	    {
	     box.addItem(e.getAccounts().get(i).getNumber());
	    }
		
	    
	   
	    for(int i = 0; i<e.getAccounts().size(); i++)
	    {
	    	if(e.getAccounts().get(i).getNumber().equals(box.getSelectedItem()) )
	    	{
	    		acc = e.getAccounts().get(i);
	    	}
	    }
	    
	    
	    
	
	    
		boxPanel.add(box);
		content = f.getContentPane();
		content.setLayout(new GridLayout(3, 1));
		content.add(labelPanel);
		content.add(boxPanel);
		content.add(buttonPanel);
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
			f.dispose();				
			menuStart();				
			}		
	     });
		
		continueButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				
		f.dispose();
		
		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});          
		f.setVisible(true);
		
		JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton statementButton = new JButton("Display Bank Statement");
		statementButton.setPreferredSize(new Dimension(250, 20));
		
		statementPanel.add(statementButton);
		
		JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton lodgementButton = new JButton("Lodge money into account");
		lodgementPanel.add(lodgementButton);
		lodgementButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton withdrawButton = new JButton("Withdraw money from account");
		withdrawalPanel.add(withdrawButton);
		withdrawButton.setPreferredSize(new Dimension(250, 20));
		
		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Customer Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");
		
		content = f.getContentPane();
		content.setLayout(new GridLayout(5, 1));
		content.add(label1);
		content.add(statementPanel);
		content.add(lodgementPanel);
		content.add(withdrawalPanel);
		content.add(returnPanel);
		
		statementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				f = new JFrame("Customer Menu");
				f.setSize(400, 600);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});          
				f.setVisible(true);
				
				JLabel label1 = new JLabel("Summary of account transactions: ");
				
				JPanel returnPanel = new JPanel();
				JButton returnButton = new JButton("Return");
				returnPanel.add(returnButton);
				
				JPanel textPanel = new JPanel();
				
				textPanel.setLayout( new BorderLayout() );
				JTextArea textArea = new JTextArea(40, 20);
				textArea.setEditable(false);
				textPanel.add(label1, BorderLayout.NORTH);
				textPanel.add(textArea, BorderLayout.CENTER);
				textPanel.add(returnButton, BorderLayout.SOUTH);
				
				JScrollPane scrollPane = new JScrollPane(textArea);
				textPanel.add(scrollPane);
				
				for (int i = 0; i < acc.getTransactionList().size(); i ++)
				{
					textArea.append(acc.getTransactionList().get(i).toString());
					
				}
				
				textPanel.add(textArea);
				content.removeAll();
				
				
				Container content = f.getContentPane();
				content.setLayout(new GridLayout(1, 1));
			//	content.add(label1);
				content.add(textPanel);
				//content.add(returnPanel);
				
				returnButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();			
					customer(e);				
					}		
			     });										
			}	
	     });
		
		lodgementButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				new LodgementTransaction(f, acc, invoker, () -> customer(e)).execute();
			}
	     });
		
		withdrawButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				new WithdrawalTransaction(f, acc, invoker, () -> customer(e)).execute();
			}
	     });
		
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();		
				menuStart();				
			}
	     });		}		
	     });
	}
	}
}


