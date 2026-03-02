package bank.facade;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import bank.model.Customer;
import bank.model.CustomerAccount;
import bank.command.CommandInvoker;
import bank.command.admin.ApplyChargesCommand;
import bank.command.admin.ApplyInterestCommand;
import bank.command.admin.CreateAccountCommand;
import bank.command.admin.DeleteCustomerCommand;
import bank.factory.AccountFactory;
import bank.factory.CurrentAccountFactory;
import bank.factory.DepositAccountFactory;
import bank.repository.CustomerRepository;
import bank.template.CustomerLookup;

public class AdminOperations {
    private final Runnable goToAdmin;
    private final Runnable goToMenuStart;
    private final JFrame mainFrame;
    private final CommandInvoker invoker;
    private final CustomerRepository repository;
    private int position = 0;
    private Customer customer;
    private CustomerAccount acc;
    private JFrame f;
    private Container content;

    private JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel;
    private JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField;
    private JLabel customerIDLabel, passwordLabel;
    private JTextField customerIDTextField, passwordTextField;

    public AdminOperations(JFrame mainFrame, Runnable goToAdmin, Runnable goToMenuStart) {
        this.mainFrame = mainFrame;
        this.goToAdmin = goToAdmin;
        this.goToMenuStart = goToMenuStart;
        this.invoker = new CommandInvoker();
        this.repository = CustomerRepository.getInstance();
    }

    public void show() {
		mainFrame.dispose();

		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) { System.exit(0); }
		});
		f.setVisible(true);

		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);

		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));
		deleteAccountPanel.add(deleteAccount);

		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));
		bankChargesPanel.add(bankChargesButton);

		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));

		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));

		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));

		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));

		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));

		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(10, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);
		content.add(deleteCustomerPanel);
		content.add(deleteAccountPanel);
		content.add(returnPanel);


		bankChargesButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					goToAdmin.run();

				}
				else
				{
			    Customer result = new CustomerLookup(f, "Customer ID of Customer You Wish to Apply Charges to:") {
			    	protected void onCancel() {
						f.dispose();
						goToAdmin.run();
					}
			    }.lookup();

				if(result != null) {
			    	customer = result;
			    	f.dispose();
			    	f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});
					f.setVisible(true);


				    JComboBox<String> box = new JComboBox<String>();
				    for (int i =0; i < customer.getAccounts().size(); i++)
				    {


				     box.addItem(customer.getAccounts().get(i).getNumber());
				    }


				    box.getSelectedItem();

				    JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);


						if(customer.getAccounts().isEmpty())
						{
							JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							goToAdmin.run();
						}
						else
						{

					for(int i = 0; i < customer.getAccounts().size(); i++)
				    {
				    	if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
				    	{
				    		acc = customer.getAccounts().get(i);
				    	}
				    }

					continueButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							String euro = "\u20ac";

							ApplyChargesCommand chargesCmd = new ApplyChargesCommand(acc);
							invoker.executeCommand(chargesCmd);

							JOptionPane.showMessageDialog(f, chargesCmd.getFeeApplied() + euro + " account fee applied."  ,"",  JOptionPane.INFORMATION_MESSAGE);
							JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() ,"Success!",  JOptionPane.INFORMATION_MESSAGE);


							f.dispose();
						goToAdmin.run();
						}
				     });

					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							goToMenuStart.run();
						}
				     });

						}
			    }
			    }


			}
	     });

		interestButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					goToAdmin.run();

				}
				else
				{
			    Customer result = new CustomerLookup(f, "Customer ID of Customer You Wish to Apply Interest to:") {
			    	protected void onCancel() {
						f.dispose();
						goToAdmin.run();
					}
			    }.lookup();

			    if(result != null) {
			    	customer = result;
			    	f.dispose();
			    	f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) { System.exit(0); }
					});
					f.setVisible(true);


				    JComboBox<String> box = new JComboBox<String>();
				    for (int i =0; i < customer.getAccounts().size(); i++)
				    {


				     box.addItem(customer.getAccounts().get(i).getNumber());
				    }


				    box.getSelectedItem();

				    JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);


						if(customer.getAccounts().isEmpty())
						{
							JOptionPane.showMessageDialog(f, "This customer has no accounts! \n The admin must add acounts to this customer."   ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							f.dispose();
							goToAdmin.run();
						}
						else
						{

					for(int i = 0; i < customer.getAccounts().size(); i++)
				    {
				    	if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem() )
				    	{
				    		acc = customer.getAccounts().get(i);
				    	}
				    }

					continueButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							String euro = "\u20ac";
						 	double interest = 0;
						 	boolean loop = true;

						 	while(loop)
						 	{
							String interestString = JOptionPane.showInputDialog(f, "Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
							if(bank.template.AuthenticatedTransaction.isNumeric(interestString))
							{

								interest = Double.parseDouble(interestString);
								loop = false;

								ApplyInterestCommand interestCmd = new ApplyInterestCommand(acc, interest);
								invoker.executeCommand(interestCmd);

								JOptionPane.showMessageDialog(f, interest + "% interest applied. \n new balance = " + acc.getBalance() + euro ,"Success!",  JOptionPane.INFORMATION_MESSAGE);
							}


							else
							{
								JOptionPane.showMessageDialog(f, "You must enter a numerical value!" ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
							}


						 	}

							f.dispose();
						goToAdmin.run();
						}
				     });

					returnButton.addActionListener(new ActionListener(  ) {
						public void actionPerformed(ActionEvent ae) {
							f.dispose();
							goToMenuStart.run();
						}
				     });

						}
			    }
			    }

			}
	     });

		editCustomerButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					goToAdmin.run();

				}
				else
				{

			    Customer result = new CustomerLookup(f, "Enter Customer ID:") {
			    	protected void onCancel() {
						f.dispose();
						goToAdmin.run();
					}
			    }.lookup();

			    if(result != null) {
				customer = result;

				f.dispose();

				f.dispose();
				f = new JFrame("Administrator Menu");
				f.setSize(400, 300);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});

				firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
				surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
				pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
				dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
				customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
				passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
				firstNameTextField = new JTextField(20);
				surnameTextField = new JTextField(20);
				pPSTextField = new JTextField(20);
				dOBTextField = new JTextField(20);
				customerIDTextField = new JTextField(20);
				passwordTextField = new JTextField(20);

				JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

				JPanel cancelPanel = new JPanel();

				textPanel.add(firstNameLabel);
				textPanel.add(firstNameTextField);
				textPanel.add(surnameLabel);
				textPanel.add(surnameTextField);
				textPanel.add(pPPSLabel);
				textPanel.add(pPSTextField);
				textPanel.add(dOBLabel);
				textPanel.add(dOBTextField);
				textPanel.add(customerIDLabel);
				textPanel.add(customerIDTextField);
				textPanel.add(passwordLabel);
				textPanel.add(passwordTextField);

				firstNameTextField.setText(customer.getFirstName());
				surnameTextField.setText(customer.getSurname());
				pPSTextField.setText(customer.getPPS());
				dOBTextField.setText(customer.getDOB());
				customerIDTextField.setText(customer.getCustomerID());
				passwordTextField.setText(customer.getPassword());



				JButton saveButton = new JButton("Save");
				JButton cancelButton = new JButton("Exit");

				cancelPanel.add(cancelButton, BorderLayout.SOUTH);
				cancelPanel.add(saveButton, BorderLayout.SOUTH);
				Container content = f.getContentPane();
				content.setLayout(new GridLayout(2, 1));
				content.add(textPanel, BorderLayout.NORTH);
				content.add(cancelPanel, BorderLayout.SOUTH);

				f.setContentPane(content);
				f.setSize(340, 350);
				f.setLocation(200, 200);
				f.setVisible(true);
				f.setResizable(false);

				saveButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {

						customer.setFirstName(firstNameTextField.getText());
						customer.setSurname(surnameTextField.getText());
						customer.setPPS(pPSTextField.getText());
						customer.setDOB(dOBTextField.getText());
						customer.setCustomerID(customerIDTextField.getText());
						customer.setPassword(passwordTextField.getText());

						JOptionPane.showMessageDialog(null, "Changes Saved.");
							}
					     });

				cancelButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();
						goToAdmin.run();
					}
			     });
				}}}
	     });

		summaryButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();


				f = new JFrame("Summary of Transactions");
				f.setSize(400, 700);
				f.setLocation(200, 200);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) { System.exit(0); }
				});
				f.setVisible(true);

				JLabel label1 = new JLabel("Summary of all transactions: ");

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

				double runningTotal = 0;
			for (int a = 0; a < repository.size(); a++)
				{
					for (int b = 0; b < repository.getCustomers().get(a).getAccounts().size(); b ++ )
					{
						acc = repository.getCustomers().get(a).getAccounts().get(b);
						for (int c = 0; c < repository.getCustomers().get(a).getAccounts().get(b).getTransactionList().size(); c++)
						{
							bank.model.AccountTransaction t = (bank.model.AccountTransaction) acc.getTransactionList().get(c);
							textArea.append(t.toString());
							if(t.getType().equals("Lodgement") || t.getType().equals("Interest")) {
								runningTotal += t.getAmount();
							} else {
								runningTotal -= t.getAmount();
							}
						}
					}
				}
				textArea.append("\n--- Running Total: " + String.format("%.2f", runningTotal) + "\u20ac ---\n");




				textPanel.add(textArea);
				content.removeAll();


				Container content = f.getContentPane();
				content.setLayout(new GridLayout(1, 1));
				content.add(textPanel);

				returnButton.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						f.dispose();
					goToAdmin.run();
					}
			     });
			}
	     });

		navigateButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					goToAdmin.run();
				}
				else
				{
				position = 0;

				JButton first, previous, next, last, cancel;
				JPanel gridPanel, buttonPanel, cancelPanel;

				Container content = mainFrame.getContentPane();

				content.setLayout(new BorderLayout());

				buttonPanel = new JPanel();
				gridPanel = new JPanel(new GridLayout(8, 2));
				cancelPanel = new JPanel();

				firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
				surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
				pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
				dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
				customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
				passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
				firstNameTextField = new JTextField(20);
				surnameTextField = new JTextField(20);
				pPSTextField = new JTextField(20);
				dOBTextField = new JTextField(20);
				customerIDTextField = new JTextField(20);
				passwordTextField = new JTextField(20);

				first = new JButton("First");
				previous = new JButton("Previous");
				next = new JButton("Next");
				last = new JButton("Last");
				cancel = new JButton("Cancel");

				firstNameTextField.setText(repository.getCustomers().getFirst().getFirstName());
				surnameTextField.setText(repository.getCustomers().getFirst().getSurname());
				pPSTextField.setText(repository.getCustomers().getFirst().getPPS());
				dOBTextField.setText(repository.getCustomers().getFirst().getDOB());
				customerIDTextField.setText(repository.getCustomers().getFirst().getCustomerID());
				passwordTextField.setText(repository.getCustomers().getFirst().getPassword());

				firstNameTextField.setEditable(false);
				surnameTextField.setEditable(false);
				pPSTextField.setEditable(false);
				dOBTextField.setEditable(false);
				customerIDTextField.setEditable(false);
				passwordTextField.setEditable(false);

				gridPanel.add(firstNameLabel);
				gridPanel.add(firstNameTextField);
				gridPanel.add(surnameLabel);
				gridPanel.add(surnameTextField);
				gridPanel.add(pPPSLabel);
				gridPanel.add(pPSTextField);
				gridPanel.add(dOBLabel);
				gridPanel.add(dOBTextField);
				gridPanel.add(customerIDLabel);
				gridPanel.add(customerIDTextField);
				gridPanel.add(passwordLabel);
				gridPanel.add(passwordTextField);

				buttonPanel.add(first);
				buttonPanel.add(previous);
				buttonPanel.add(next);
				buttonPanel.add(last);

				cancelPanel.add(cancel);

				content.add(gridPanel, BorderLayout.NORTH);
				content.add(buttonPanel, BorderLayout.CENTER);
				content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
				first.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						position = 0;
						firstNameTextField.setText(repository.getCustomers().getFirst().getFirstName());
						surnameTextField.setText(repository.getCustomers().getFirst().getSurname());
						pPSTextField.setText(repository.getCustomers().getFirst().getPPS());
						dOBTextField.setText(repository.getCustomers().getFirst().getDOB());
						customerIDTextField.setText(repository.getCustomers().getFirst().getCustomerID());
						passwordTextField.setText(repository.getCustomers().getFirst().getPassword());
							}
					     });

				previous.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {

						if(position < 1 || position >= repository.size()) {}
						else {
							position = position - 1;

							firstNameTextField.setText(repository.getCustomers().get(position).getFirstName());
							surnameTextField.setText(repository.getCustomers().get(position).getSurname());
							pPSTextField.setText(repository.getCustomers().get(position).getPPS());
							dOBTextField.setText(repository.getCustomers().get(position).getDOB());
							customerIDTextField.setText(repository.getCustomers().get(position).getCustomerID());
							passwordTextField.setText(repository.getCustomers().get(position).getPassword());
						}
							}
					     });

				next.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {

						if(position >= repository.size()-1) {}
						else {
							position = position + 1;

						firstNameTextField.setText(repository.getCustomers().get(position).getFirstName());
						surnameTextField.setText(repository.getCustomers().get(position).getSurname());
						pPSTextField.setText(repository.getCustomers().get(position).getPPS());
						dOBTextField.setText(repository.getCustomers().get(position).getDOB());
						customerIDTextField.setText(repository.getCustomers().get(position).getCustomerID());
						passwordTextField.setText(repository.getCustomers().get(position).getPassword());
						}



							}
					     });

				last.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {

						position = repository.size() - 1;

						firstNameTextField.setText(repository.getCustomers().get(position).getFirstName());
						surnameTextField.setText(repository.getCustomers().get(position).getSurname());
						pPSTextField.setText(repository.getCustomers().get(position).getPPS());
						dOBTextField.setText(repository.getCustomers().get(position).getDOB());
						customerIDTextField.setText(repository.getCustomers().get(position).getCustomerID());
						passwordTextField.setText(repository.getCustomers().get(position).getPassword());
							}
					     });

				cancel.addActionListener(new ActionListener(  ) {
					public void actionPerformed(ActionEvent ae) {
						mainFrame.dispose();
						goToAdmin.run();
							}
					     });
				mainFrame.setContentPane(content);
				mainFrame.setSize(400, 300);
			       mainFrame.setVisible(true);
					}
			}
		});

		accountButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(f, "There are no customers yet!"  ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
					f.dispose();
					goToAdmin.run();
				}
				else
				{

			    Customer result = new CustomerLookup(f, "Customer ID of Customer You Wish to Add an Account to:") {
			    	protected void onCancel() {
						f.dispose();
						goToAdmin.run();
					}
			    }.lookup();

			    if(result != null) {
			    	customer = result;
				    String[] choices = { "Current Account", "Deposit Account" };
				    String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
				        "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

				    AccountFactory factory;
				    switch(account) {
				        case "Current Account":
				            factory = new CurrentAccountFactory();
				            break;
				        case "Deposit Account":
				            factory = new DepositAccountFactory();
				            break;
				        default:
				            factory = null;
				            break;
				    }

				    CreateAccountCommand createCmd = new CreateAccountCommand(customer, factory);
				    invoker.executeCommand(createCmd);

				    if(factory instanceof CurrentAccountFactory)
				    {
				    	JOptionPane.showMessageDialog(f, "Account number = " + createCmd.getAccountNumber() +"\n PIN = " + createCmd.getPin()  ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
				    	f.dispose();
				    	goToAdmin.run();
				    }

				    if(factory instanceof DepositAccountFactory)
				    {
				    	JOptionPane.showMessageDialog(f, "Account number = " + createCmd.getAccountNumber() ,"Account created.",  JOptionPane.INFORMATION_MESSAGE);
				    	f.dispose();
				    	goToAdmin.run();
				    }

			    }
				}
			}
	     });

		deleteCustomer.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					mainFrame.dispose();
					goToAdmin.run();
				}
				else
				{
					Customer result = new CustomerLookup(f, "Customer ID of Customer You Wish to Delete:") {
						protected void onCancel() {
							f.dispose();
							goToAdmin.run();
						}
					}.lookup();
					if(result != null) {
						customer = result;
						    	DeleteCustomerCommand deleteCmd = new DeleteCustomerCommand(customer);
						    	invoker.executeCommand(deleteCmd);
						    	if(deleteCmd.isSuccess()) {
						    		JOptionPane.showMessageDialog(f, deleteCmd.getResultMessage() ,"Success.",  JOptionPane.INFORMATION_MESSAGE);
						    	}
						    	else {
						    		JOptionPane.showMessageDialog(f, deleteCmd.getResultMessage() ,"Oops!",  JOptionPane.INFORMATION_MESSAGE);
						    	}
						    }


				}
			}
	     });

		deleteAccount.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {

				if(repository.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
					goToAdmin.run();
				}
				else
				{
				Customer result = new CustomerLookup(f, "Customer ID of Customer from which you wish to delete an account") {
					protected void onCancel() {
						f.dispose();
						goToAdmin.run();
					}
				}.lookup();

				if(result != null) {
					customer = result;

					if(customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f, "This customer has no accounts to delete.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
						goToAdmin.run();
					} else {
						f.dispose();
						f = new JFrame("Delete Account");
						f.setSize(400, 300);
						f.setLocation(200, 200);
						f.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent we) { System.exit(0); }
						});
						f.setVisible(true);

						JComboBox<String> box = new JComboBox<String>();
						for (int i = 0; i < customer.getAccounts().size(); i++) {
							box.addItem(customer.getAccounts().get(i).getNumber());
						}

						JPanel boxPanel = new JPanel();
						JLabel label = new JLabel("Select an account to delete:");
						boxPanel.add(label);
						boxPanel.add(box);

						JPanel buttonPanel = new JPanel();
						JButton deleteButton = new JButton("Delete Account");
						JButton cancelButton = new JButton("Cancel");
						buttonPanel.add(deleteButton);
						buttonPanel.add(cancelButton);

						Container content = f.getContentPane();
						content.setLayout(new GridLayout(2, 1));
						content.add(boxPanel);
						content.add(buttonPanel);

						deleteButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae2) {
								CustomerAccount selectedAcc = null;
								for (int i = 0; i < customer.getAccounts().size(); i++) {
									if(customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
										selectedAcc = customer.getAccounts().get(i);
									}
								}

								if(selectedAcc != null && selectedAcc.getBalance() == 0) {
									customer.getAccounts().remove(selectedAcc);
									JOptionPane.showMessageDialog(f, "Account deleted.", "Success.", JOptionPane.INFORMATION_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(f, "Account balance must be 0 before it can be deleted.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
								}
								f.dispose();
								goToAdmin.run();
							}
						});

						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae2) {
								f.dispose();
								goToAdmin.run();
							}
						});
					}
				}
				}
			}
	     });
		returnButton.addActionListener(new ActionListener(  ) {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				goToMenuStart.run();
			}
	     });
	}
}
