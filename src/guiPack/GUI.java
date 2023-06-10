package guiPack;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import jdbcPack.DataProvider;

public class GUI  extends JFrame{
	Users u;
	CardLayout cl;
	Container c;
	Area a;
	Restaurant r;
	Menu m;
	Order o;
	//Constructor
	public GUI(){
		initializeGUI();
	}

	private void initializeGUI() {
		c = this.getContentPane();
		CardLayout cl = new CardLayout();
		c.setLayout(cl);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("Icon.PNG"));
		this.setIconImage(icon.getImage());
		
		JPanel loginPanel = new JPanel();
		c.add(loginPanel,"loginPanel");
		
		JPanel foodPanel = new JPanel();
		c.add(foodPanel,"foodPanel");
		
		JPanel userPanel = new JPanel();
		c.add(userPanel,"userPanel");
		
		JPanel signUpPanel = new JPanel();
		c.add(signUpPanel,"signUpPanel");
		
		JPanel menuPanel = new JPanel();
		c.add(menuPanel,"menuPanel");
		
		JPanel orderPanel = new JPanel();
		c.add(orderPanel,"orderPanel");
		
		JPanel donatePanel = new JPanel();
		c.add(donatePanel,"donatePanel");
		
		JPanel applyVolunterrPanel = new JPanel();
		c.add(applyVolunterrPanel,"applyVolunterrPanel");
		
		JPanel reviewPanel = new JPanel();
		c.add(reviewPanel,"reviewPanel");
		
		cl.show(c, "loginPanel");
		
		JLabel uIDLabel = new JLabel("User ID: ");
		loginPanel.add(uIDLabel);
		
		JTextField uIDText = new JTextField(20);
		uIDText.setHorizontalAlignment(JTextField.CENTER);
		loginPanel.add(uIDText);
		
		JLabel uPassLabel = new JLabel("Password: "); 
		loginPanel.add(uPassLabel);
		
		JPasswordField uPass = new JPasswordField(20);
		uPass.setHorizontalAlignment(JPasswordField.CENTER);
		loginPanel.add(uPass);
		
		JLabel incorrect = new JLabel("");
		loginPanel.add(incorrect);
		
		JButton login = new JButton("Log in");
		login.addActionListener(new ActionListener()  { //LOGIN BUTTON WORK
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s = new String(uPass.getPassword());
				
				try {
					if(s.equals(DataProvider.getPassword(uIDText.getText()))){
						System.out.println("Pass Correct");
						u = DataProvider.getUsers(uIDText.getText());
						System.out.println("Food Panel");
						cl.show(c, "foodPanel");
					}
					else{
						System.out.println("Incorrect");
						incorrect.setText("*Please enter a valid password");
						
						cl.show(c, "loginPanel");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		loginPanel.add(login);
		
		JLabel showUserID = new JLabel("");
		loginPanel.add(showUserID);
		
		JButton signUp = new JButton("Sign Up");
		signUp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				signUpPanel.removeAll();
				cl.show(c, "signUpPanel");
				System.out.println("In signUp");
				
				JLabel name = new JLabel("Name: ");
				signUpPanel.add(name);
				
				JTextField nameText = new JTextField(20);
				nameText.setHorizontalAlignment(JTextField.CENTER);
				signUpPanel.add(nameText);
				
				JLabel pass = new JLabel("Pass: ");
				signUpPanel.add(pass);
				JPasswordField passText = new JPasswordField(20);
				passText.setHorizontalAlignment(JPasswordField.CENTER);
				signUpPanel.add(passText);
				
				JLabel phone = new JLabel("Phone: ");
				signUpPanel.add(phone);
				JTextField phoneText = new JTextField(20);
				phoneText.setHorizontalAlignment(JTextField.CENTER);
				signUpPanel.add(phoneText);
				
				JLabel address = new JLabel("Address: ");
				signUpPanel.add(address);
				JTextField addressText = new JTextField(20);
				addressText.setHorizontalAlignment(JTextField.CENTER);
				signUpPanel.add(addressText);
				
				JButton confirm = new JButton("Confirm");
				confirm.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							DataProvider.setUsers(nameText.getText(),passText.getText(),Integer.parseInt(phoneText.getText()),addressText.getText());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						
						try {
							showUserID.setText("Your User ID is: "+DataProvider.getUserID(nameText.getText()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						cl.show(c, "loginPanel");
						
					}
					
				});
				signUpPanel.add(confirm);
			}
			
		});
		loginPanel.add(signUp);
		
		// It is for FoodPanel
		JButton showMyInfo = new JButton("View Profile");
		showMyInfo.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userPanel.removeAll();
				JLabel name = new JLabel("Name: "+u.uName+" **** ");
				name.setBounds(100, 100, 50, 50);
				userPanel.add(name);
				JLabel id = new JLabel("ID: "+u.uID+" **** ");
				id.setBounds(100, 200, 50, 50);
				userPanel.add(id);
				JLabel phone = new JLabel("Phone no: "+u.phoneNo+" **** ");
				phone.setBounds(100, 300, 50, 50);
				userPanel.add(phone);
				JLabel address = new JLabel("Total Order: "+u.totalOrder+" **** ");
				address.setBounds(100, 400, 50, 50);
				userPanel.add(address);
				JLabel point = new JLabel("Point: "+u.point+" **** ");
				point.setBounds(100, 500, 50, 50);
				userPanel.add(point);
				
				JButton back = new JButton("Back");
				
				back.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						cl.show(c, "foodPanel");
					}			
				});
				
				userPanel.add(back);
				
				cl.show(c, "userPanel");
				System.out.println("User Panel");
			}
			
		});
		foodPanel.add(showMyInfo);	
		
		JComboBox<Restaurant> resCombo = new JComboBox<Restaurant>();
		
		
		ArrayList<Area> arr = null;
		try {
			arr = DataProvider.getAreaList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox<Area> areaCombo = new JComboBox<Area>();
		
		for(Area area : arr){
			areaCombo.addItem(area);
		}
		
		areaCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				a = (Area) areaCombo.getSelectedItem();
				System.out.println(a.areaName);
				ArrayList<Restaurant> resArr = new ArrayList<Restaurant>();
				resCombo.removeAllItems();
				
				try {
					resArr = DataProvider.getRestaurantList(a.AID);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Restaurant r:resArr){
					resCombo.addItem(r);
				}
				
			}
			
		});
		foodPanel.add(areaCombo);
		
		resCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				r= (Restaurant) resCombo.getSelectedItem();
			}
			
		});
		
		foodPanel.add(resCombo);
		
		JButton showMenu = new JButton("Show Menu");
		
		
		showMenu.addActionListener(new ActionListener(){
			int  rid;
			ArrayList<String> foodType;
			ArrayList<Integer> fid;
			ArrayList<String> fName;
			ArrayList<Integer> review;
			ArrayList<String> description;
			ArrayList<Integer> price;
			

			@Override
			public void actionPerformed(ActionEvent e) {
				menuPanel.removeAll();
				rid = r.rid;
				try {
					foodType = DataProvider.getFoodType(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fid = DataProvider.getFid(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					fName = DataProvider.getFname(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					review = DataProvider.getReview(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					description = DataProvider.getDescription(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					price = DataProvider.getPrice(r.rid);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				m = new Menu(rid,foodType,fid,fName,review,description,price);
				System.out.println("Menu is ready...");
				
				// Label for menuPanel
				int x = foodType.size();
				JLabel[] starting = new JLabel[x];
				JLabel[] ft = new JLabel[x];
				JLabel[] fi = new JLabel[x];
				JLabel[] fn = new JLabel[x];
				JLabel[] re = new JLabel[x];
				JLabel[] d = new JLabel[x];
				JLabel[] pr = new JLabel[x];
				JButton[] orderButton = new JButton[x];
				JButton[] putReview = new JButton[x];
				
				
				//Initialize
				for(int i=0; i<x; i++){
					starting[i] = new JLabel("### "+i+". ");
					menuPanel.add(starting[i]);
					ft[i] = new JLabel("Food Type: "+m.foodType.get(i));
					menuPanel.add(ft[i]);
					fi[i] = new JLabel(", ID: "+m.fid.get(i)+"");
					menuPanel.add(fi[i]);
					fn[i] = new JLabel(", Name: "+m.fName.get(i));
					menuPanel.add(fn[i]);
					re[i] = new JLabel(", Review: "+m.review.get(i)+"");
					menuPanel.add(re[i]);
					d[i] = new JLabel(", Description: "+m.description.get(i));
					menuPanel.add(d[i]);
					pr[i] = new JLabel(", Price: "+m.price.get(i)+"");
					menuPanel.add(pr[i]);
					orderButton[i] = new JButton("Order");
					orderButton[i].setBackground(Color.orange);
					putReview[i] = new JButton("Rate");
					putReview[i].setBackground(Color.green);
					int p =i;
										
					orderButton[i].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							//****** ORDER
							orderPanel.removeAll();
							JLabel enterAmount = new JLabel("Enter Amount: ");
							orderPanel.add(enterAmount);
							
							JTextField amountText = new JTextField(20);
							amountText.setHorizontalAlignment(JTextField.CENTER);
							orderPanel.add(amountText);
							
							JButton confirmOrder = new JButton("Confirm");
							orderPanel.add(confirmOrder);
							JButton backToFoodPanel = new JButton("Back");
							orderPanel.add(backToFoodPanel);
							
							JLabel confirmText = new JLabel();
							orderPanel.add(confirmText);
							
							confirmOrder.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									
									try {
										DataProvider.setUsersTotalOrder(u.uID);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									u.point = u.point+10;

									try {
										DataProvider.setUsersPoint(u.uID);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									int amount =Integer.parseInt(amountText.getText());
									//System.out.println(amount+"");
									int discount = (int) ((u.point)*.5);
									//System.out.println(discount+"");
									int money = amount*m.price.get(p)-discount;
									//System.out.println(money+"");
									
									confirmText.setText("***Discount: "+discount+" tk.     ***Total: "+money+"tk. ");
												
									o = new Order(u.uID,r.rid,m.fid.get(p),amount,discount,money);
									try {
										DataProvider.setOrder(u.uID,r.rid,m.fid.get(p),amount,discount,money);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}				
								}
								
							});
							
							backToFoodPanel.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									cl.show(c, "foodPanel");
									
								}	
							});

							cl.show(c, "orderPanel");
							
						}
						
					});
					menuPanel.add(orderButton[i]);
					
					putReview[i].addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							//***********************************************
							
							JLabel mark = new JLabel("Please put a review out of 5:  ");
							reviewPanel.add(mark);
							
							JComboBox<Integer> rateCombo = new JComboBox<Integer>();
							rateCombo.addItem(1);
							rateCombo.addItem(2);
							rateCombo.addItem(3);
							rateCombo.addItem(4);
							rateCombo.addItem(5);
							reviewPanel.add(rateCombo);
							
							rateCombo.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									try {
										DataProvider.setReview((Integer) rateCombo.getSelectedItem(),m.fid.get(p));
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							});
							JButton backToMenuPanel = new JButton("Back");
							backToMenuPanel.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									cl.show(c, "menuPanel");
									
								}	
							});
							
							reviewPanel.add(backToMenuPanel);
							
							cl.show(c, "reviewPanel");
							
						}
						
					});
					menuPanel.add(putReview[i]);
				}
				
				JButton backFromMenu = new JButton("Back");
				backFromMenu.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						cl.show(c, "foodPanel");
						
					}
					
				});
				menuPanel.add(backFromMenu);
				
				cl.show(c, "menuPanel");
				
			}
			
		});
		
		foodPanel.add(showMenu);
		
		JButton donateFood = new JButton("Donate Food");
		donateFood.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				donatePanel.removeAll();
				ArrayList<String> dName = null;
				ArrayList<String> dAdd= null;
				ArrayList<Integer> dPhone= null;
				try {
					dName = DataProvider.getDonatorsName();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					 dAdd = DataProvider.getDonatorsAddress();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dPhone = DataProvider.getDonatorsPhone();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int z = dName.size();
				
				JLabel donatorInfo[] = new JLabel[z];
				
				for(int i=0; i<z; i++){
					donatorInfo[i] = new JLabel("# "+i+". "+dName.get(i)+"***"+dAdd.get(i)+"***"+dPhone.get(i));
					donatePanel.add(donatorInfo[i]);
					
				}
				
				JButton backToFoodPanel = new JButton("Back");
				backToFoodPanel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						cl.show(c,"foodPanel");
					}
					
				});
				donatePanel.add(backToFoodPanel);
				
				cl.show(c,"donatePanel");
				
			}
			
		});
		foodPanel.add(donateFood);
		
		JButton applyForVolunteer = new JButton("Apply for Volunteer");
		applyForVolunteer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel l11 = new JLabel("***Write your details bellow");
				applyVolunterrPanel.add(l11);
				
				JTextField t11 = new JTextField(40);
				t11.setHorizontalAlignment(JTextField.CENTER);
				applyVolunterrPanel.add(t11);
				
				JButton apply = new JButton("Apply");
				apply.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							DataProvider.setVolunteerApply(t11.getText());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				});
				applyVolunterrPanel.add(apply);
				
				JButton backToFoodPanel = new JButton("Back");
				backToFoodPanel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						cl.show(c,"foodPanel");
					}
					
				});
				applyVolunterrPanel.add(backToFoodPanel);
				
				cl.show(c, "applyVolunterrPanel");
				
			}
			
		});
		foodPanel.add(applyForVolunteer);
		
		this.setBounds(400, 200, 285, 400);
		
		this.setTitle("FoodiesWorld");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		GUI obj = new GUI();

	}

}
