/*
 * @Author Tanner Curren
 * @Date 9/30/2015
 * This program emulates a Casio HS-8VS calculator with a functional user interface.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Calculator extends JFrame {
	// GUI Components
	private JLabel casio;
	private JLabel model;
	private JLabel opLabel;
	private JLabel memLabel;
	private JLabel display;
	private JLabel on;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnDecimal;
	private JButton btnEq;
	private JButton btnPlus;
	private JButton btnMinus;
	private JButton btnTimes;
	private JButton btnDivide;
	private JButton btnPercent;
	private JButton btnMU;
	private JButton btnCAC;
	private JButton btnMRC;
	private JButton btnMMinus;
	private JButton btnMPlus;
	private JButton btnSqrt;
	private JButton btnSign;
	
	// Internal components
	private boolean power;
	private boolean newNum;
	private boolean justCleared;
	private boolean mrcPressed;
	private String displayNum;
	private String memory;
	private String sign;
	private double opNum;
	private double memoryNum;
	private char op;
	
	// Constructor
	public Calculator() {
		// Initialize the components
		casio = new JLabel("CASIO");
		casio.setFont(new Font(casio.getFont().getName(), Font.BOLD, 26));
		model = new JLabel("HS-8VS");
		power = false;
		newNum = true;
		justCleared = false;
		mrcPressed = false;
		displayNum = "";
		memory = "";
		sign = "";
		opNum = 0;
		memoryNum = 0;
		
		// Load up font for screen
		Font numFont = null;
		Font numFontItal = null;
		try {
			// Register digital-7
	        numFont = Font.createFont(Font.TRUETYPE_FONT, new File("digital-7.ttf")).deriveFont(20f);
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("digital-7.ttf")));
	        
	        // Register italics digital-7
	        numFontItal = Font.createFont(Font.TRUETYPE_FONT, new File("digital-7 (italic).ttf")).deriveFont(76f);
	        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("digital-7.ttf")));
	    } catch (IOException | FontFormatException e) {
	        e.printStackTrace();
	    }

		model.setFont(new Font(casio.getFont().getName(), Font.PLAIN, 14));
		opLabel = new JLabel(sign);
		if (numFont != null) {
			opLabel.setFont(numFont);
		} else {
			opLabel.setFont(new Font(casio.getFont().getName(), Font.BOLD, 14));
		}
		memLabel = new JLabel(memory);
		if (numFont != null) {
			memLabel.setFont(numFont);
		} else {
			memLabel.setFont(new Font(casio.getFont().getName(), Font.BOLD, 14));
		}	
		display = new JLabel(displayNum);
		if (numFontItal != null) {
			display.setFont(numFontItal);
		} else {
			display.setFont(new Font(casio.getFont().getName(), Font.BOLD, 60));
		}
		on = new JLabel("ON");
		on.setFont(new Font(casio.getFont().getName(), Font.PLAIN, 14));
		
		// Initialize basic button layout values
		Font smallBtnFont = new Font(casio.getFont().getName(), Font.BOLD, 16);
		Font largeBtnFont = new Font(casio.getFont().getName(), Font.BOLD, 24);
		Dimension smallBtnSize = new Dimension(50, 40);
		Dimension largeBtnSize = new Dimension(60, 45);
		Dimension plusBtnSize = new Dimension(60, 99);
		Insets btnMargin = new Insets(2, 2, 2, 2);
		
		// Set up the window
		setSize(325, 600);
		setTitle("Casio HS-8VS");
		
		// Initialize all the buttons with properties
		btn0 = new JButton("0");
		btn0.setFont(largeBtnFont);
		btn0.setForeground(Color.WHITE);
		btn0.setBackground(Color.darkGray);
		btn0.setMargin(btnMargin);
		btn0.setPreferredSize(largeBtnSize);
		btn0.setFocusPainted(false);
		btn1 = new JButton("1");
		btn1.setFont(largeBtnFont);
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.darkGray);
		btn1.setMargin(btnMargin);
		btn1.setPreferredSize(largeBtnSize);
		btn1.setFocusPainted(false);
		btn2 = new JButton("2");
		btn2.setFont(largeBtnFont);
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.darkGray);
		btn2.setMargin(btnMargin);
		btn2.setPreferredSize(largeBtnSize);
		btn2.setFocusPainted(false);
		btn3 = new JButton("3");
		btn3.setFont(largeBtnFont);
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.darkGray);
		btn3.setMargin(btnMargin);
		btn3.setPreferredSize(largeBtnSize);
		btn3.setFocusPainted(false);
		btn4 = new JButton("4");
		btn4.setFont(largeBtnFont);
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.darkGray);
		btn4.setMargin(btnMargin);
		btn4.setPreferredSize(largeBtnSize);
		btn4.setFocusPainted(false);
		btn5 = new JButton("5");
		btn5.setFont(largeBtnFont);
		btn5.setForeground(Color.WHITE);
		btn5.setBackground(Color.darkGray);
		btn5.setMargin(btnMargin);
		btn5.setPreferredSize(largeBtnSize);
		btn5.setFocusPainted(false);
		btn6 = new JButton("6");
		btn6.setFont(largeBtnFont);
		btn6.setForeground(Color.WHITE);
		btn6.setBackground(Color.darkGray);
		btn6.setMargin(btnMargin);
		btn6.setPreferredSize(largeBtnSize);
		btn6.setFocusPainted(false);
		btn7 = new JButton("7");
		btn7.setFont(largeBtnFont);
		btn7.setForeground(Color.WHITE);
		btn7.setBackground(Color.darkGray);
		btn7.setMargin(btnMargin);
		btn7.setPreferredSize(largeBtnSize);
		btn7.setFocusPainted(false);
		btn8 = new JButton("8");
		btn8.setFont(largeBtnFont);
		btn8.setForeground(Color.WHITE);
		btn8.setBackground(Color.darkGray);
		btn8.setMargin(btnMargin);
		btn8.setPreferredSize(largeBtnSize);
		btn8.setFocusPainted(false);
		btn9 = new JButton("9");
		btn9.setFont(largeBtnFont);
		btn9.setForeground(Color.WHITE);
		btn9.setBackground(Color.darkGray);
		btn9.setMargin(btnMargin);
		btn9.setPreferredSize(largeBtnSize);
		btn9.setFocusPainted(false);
		btnDecimal = new JButton(".");
		btnDecimal.setFont(largeBtnFont);
		btnDecimal.setForeground(Color.WHITE);
		btnDecimal.setBackground(Color.darkGray);
		btnDecimal.setMargin(btnMargin);
		btnDecimal.setPreferredSize(largeBtnSize);
		btnDecimal.setFocusPainted(false);
		btnEq = new JButton("=");
		btnEq.setFont(largeBtnFont);
		btnEq.setForeground(Color.WHITE);
		btnEq.setBackground(Color.darkGray);
		btnEq.setMargin(btnMargin);
		btnEq.setPreferredSize(largeBtnSize);
		btnEq.setFocusPainted(false);
		btnPlus = new JButton("+");
		btnPlus.setFont(largeBtnFont);
		btnPlus.setForeground(Color.WHITE);
		btnPlus.setBackground(Color.darkGray);
		btnPlus.setMargin(btnMargin);
		btnPlus.setPreferredSize(plusBtnSize);
		btnPlus.setFocusPainted(false);
		btnMinus = new JButton("-");
		btnMinus.setFont(largeBtnFont);
		btnMinus.setForeground(Color.WHITE);
		btnMinus.setBackground(Color.darkGray);
		btnMinus.setMargin(btnMargin);
		btnMinus.setPreferredSize(largeBtnSize);
		btnMinus.setFocusPainted(false);
		btnTimes = new JButton("\u00D7");
		btnTimes.setFont(largeBtnFont);
		btnTimes.setForeground(Color.WHITE);
		btnTimes.setBackground(Color.darkGray);
		btnTimes.setMargin(btnMargin);
		btnTimes.setPreferredSize(largeBtnSize);
		btnTimes.setFocusPainted(false);
		btnDivide = new JButton("\u00F7");
		btnDivide.setFont(largeBtnFont);
		btnDivide.setForeground(Color.WHITE);
		btnDivide.setBackground(Color.darkGray);
		btnDivide.setMargin(btnMargin);
		btnDivide.setPreferredSize(largeBtnSize);
		btnDivide.setFocusPainted(false);
		btnPercent = new JButton("%");
		btnPercent.setFont(largeBtnFont);
		btnPercent.setForeground(Color.WHITE);
		btnPercent.setBackground(Color.darkGray);
		btnPercent.setMargin(btnMargin);
		btnPercent.setPreferredSize(largeBtnSize);
		btnPercent.setFocusPainted(false);
		btnMU = new JButton("MU");
		btnMU.setFont(largeBtnFont);
		btnMU.setForeground(Color.WHITE);
		btnMU.setBackground(Color.darkGray);
		btnMU.setMargin(btnMargin);
		btnMU.setPreferredSize(largeBtnSize);
		btnMU.setFocusPainted(false);
		btnCAC = new JButton("C/AC");
		btnCAC.setFont(smallBtnFont);
		btnCAC.setForeground(Color.WHITE);
		btnCAC.setBackground(Color.RED);
		btnCAC.setMargin(btnMargin);
		btnCAC.setPreferredSize(largeBtnSize);
		btnCAC.setFocusPainted(false);
		btnMRC = new JButton("MRC");
		btnMRC.setFont(smallBtnFont);
		btnMRC.setForeground(Color.WHITE);
		btnMRC.setBackground(Color.darkGray);
		btnMRC.setMargin(btnMargin);
		btnMRC.setPreferredSize(smallBtnSize);
		btnMRC.setFocusPainted(false);
		btnMMinus = new JButton("M-");
		btnMMinus.setFont(smallBtnFont);
		btnMMinus.setForeground(Color.WHITE);
		btnMMinus.setBackground(Color.darkGray);
		btnMMinus.setMargin(btnMargin);
		btnMMinus.setPreferredSize(smallBtnSize);
		btnMMinus.setFocusPainted(false);
		btnMPlus = new JButton("M+");
		btnMPlus.setFont(smallBtnFont);
		btnMPlus.setForeground(Color.WHITE);
		btnMPlus.setBackground(Color.darkGray);
		btnMPlus.setMargin(btnMargin);
		btnMPlus.setPreferredSize(smallBtnSize);
		btnMPlus.setFocusPainted(false);
		btnSqrt = new JButton("\u221A");
		btnSqrt.setFont(smallBtnFont);
		btnSqrt.setForeground(Color.WHITE);
		btnSqrt.setBackground(Color.darkGray);
		btnSqrt.setMargin(btnMargin);
		btnSqrt.setPreferredSize(smallBtnSize);
		btnSqrt.setFocusPainted(false);
		btnSign = new JButton("+/-");
		btnSign.setFont(smallBtnFont);
		btnSign.setForeground(Color.WHITE);
		btnSign.setBackground(Color.darkGray);
		btnSign.setMargin(btnMargin);
		btnSign.setPreferredSize(smallBtnSize);
		btnSign.setFocusPainted(false);
		
		// Set up the layout
		GridBagConstraints c = new GridBagConstraints();
		JPanel p = new JPanel(new GridBagLayout());
		// Constraints for large button section
		GridBagConstraints d = new GridBagConstraints();
		JPanel largeButtons = new JPanel(new GridBagLayout());
		add(p);
		
		// Labels
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.insets = new Insets(0,8,0,0);
		c.gridx = 0;
		c.gridy = 0;
		p.add(casio, c);
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0,8,0,0);
		c.gridy = 1;
		p.add(model, c);
		opLabel.setPreferredSize(new Dimension(60, 20));
		c.insets = new Insets(0,10,0,0);
		c.gridy = 2;
		p.add(opLabel, c);
		memLabel.setPreferredSize(new Dimension(60, 20));
		c.gridx = 2;
		c.gridy = 2;
		p.add(memLabel, c);
		display.setPreferredSize(new Dimension(300, 90));
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		c.insets = new Insets(0,8,14,8);
		c.gridx = 0;
		c.gridy = 3;
		p.add(display, c);
		
		// Small buttons
		c.gridwidth = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		c.gridy = 5;
		p.add(btnMRC, c);
		c.gridx = 1;
		p.add(btnMMinus, c);
		c.gridx = 2;
		p.add(btnMPlus, c);
		c.gridx = 3;
		p.add(btnSqrt, c);
		c.gridx = 4;
		p.add(btnSign, c);

		// ON text
		c.insets = new Insets(5,18,0,0);
		c.gridx = 0;
		c.gridy = 7;
		p.add(on, c);
		
		// Large buttons
		c.insets = new Insets(0,0,0,0);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridx = 0;
		c.gridy = 9;
		p.add(largeButtons, c);
		
		d.gridwidth = 1;
		d.weightx = 1;
		d.fill = GridBagConstraints.NONE;
		d.anchor = GridBagConstraints.CENTER;
		d.insets = new Insets(0,9,9,9);
		d.gridx = 0;
		d.gridy = 0;
		largeButtons.add(btnCAC, d);
		d.gridx = 1;
		largeButtons.add(btnMU, d);
		d.gridx = 2;
		largeButtons.add(btnPercent, d);
		d.gridx = 3;
		largeButtons.add(btnDivide, d);
		d.gridx = 0;
		d.gridy = 1;
		largeButtons.add(btn7, d);
		d.gridx = 1;
		largeButtons.add(btn8, d);
		d.gridx = 2;
		largeButtons.add(btn9, d);
		d.gridx = 3;
		largeButtons.add(btnTimes, d);
		d.gridx = 0;
		d.gridy = 2;
		largeButtons.add(btn4, d);
		d.gridx = 1;
		largeButtons.add(btn5, d);
		d.gridx = 2;
		largeButtons.add(btn6, d);
		d.gridx = 3;
		largeButtons.add(btnMinus, d);
		d.gridx = 0;
		d.gridy = 3;
		largeButtons.add(btn1, d);
		d.gridx = 1;
		largeButtons.add(btn2, d);
		d.gridx = 2;
		largeButtons.add(btn3, d);
		d.gridheight = 2;
		d.gridx = 3;
		largeButtons.add(btnPlus, d);
		d.gridheight = 1;
		d.gridx = 0;
		d.gridy = 4;
		largeButtons.add(btn0, d);
		d.gridx = 1;
		largeButtons.add(btnDecimal, d);
		d.gridx = 2;
		largeButtons.add(btnEq, d);
		
		
		// Set up Action Listeners
		btn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "0";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '0';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "1";
						newNum = false;
						sign = "";
					} else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '1';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "2";
						newNum = false;
						mrcPressed = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '2';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "3";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '3';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "4";
						newNum = false;
						mrcPressed = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '4';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "5";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '5';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "6";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '6';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "7";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '7';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "8";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '8';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btn9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if(!justCleared) {
							if (sign == "- MINUS"){
								opNum = Double.parseDouble(displayNum) * -1;
							} else {
								opNum = Double.parseDouble(displayNum);
							}
						}
						displayNum = "9";
						newNum = false;
						sign = "";
					}
					else if (displayNum.length() < 8 || (displayNum.indexOf('.') != -1 && displayNum.length() < 9)) {
						displayNum += '9';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btnDecimal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (newNum) {
						if (sign == "- MINUS"){
							opNum = Double.parseDouble(displayNum) * -1;
						} else {
							opNum = Double.parseDouble(displayNum);
						}
						displayNum = ".";
						newNum = false;
					}
					if (displayNum.length() < 8 && displayNum.indexOf('.') == -1) {
						displayNum += '.';
					}
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btnEq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
						displayNum = "0";
					}
					performOp();
					newNum = true;
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPressed('+');
				updateLabels();
			}
		});
		
		btnMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPressed('-');
				updateLabels();
			}
		});
		
		btnTimes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPressed('*');
				updateLabels();
			}
		});
		
		btnDivide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				opPressed('/');
				updateLabels();
			}
		});
		
		btnPercent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power && displayNum != "OVERFLOW") {
					if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
						displayNum = "0";
					}
					if (!newNum) {
						performOp();
					}
					displayNum = Double.toString(Double.parseDouble(displayNum) * 0.01);
					// Shave off trailing digits if necessary
					if (displayNum.length() > 9) {
						displayNum = displayNum.substring(0,9);
					}
					newNum = true;
					trimDisplay();
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btnMU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					opPressed('$');
					updateLabels();
				}
			}
		});
		
		btnCAC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!power) {
					power = true;
					displayNum = "0";
				} else if (justCleared) {
					opNum = 0;
					op = 0;
					justCleared = false;
				} else {
					displayNum = "0";
					sign = "";
					newNum = true;
					justCleared = true;
					mrcPressed = false;
				}
				updateLabels();
			}
		});
		
		btnMRC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (!mrcPressed) {
						if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
							displayNum = "0";
						}
						// If number is too large, return overflow error
						if (memoryNum >= 100000000) {
							displayNum = "OVERFLOW";
						} else {
							displayNum = Double.toString(Math.abs(memoryNum));
							if (memoryNum < 0) {
								sign = "- MINUS";
							} else {
								sign = "";
							}
							// Shave off trailing digits if necessary
							if (displayNum.length() > 9) {
								displayNum = displayNum.substring(0,9);
							}
						}
						trimDisplay();
						updateLabels();
						justCleared = false;
						mrcPressed = true;
					} else {
						memoryNum = 0;
						memory = "";
						updateLabels();
						mrcPressed = false;
					}
				}
			}
		});
		
		btnMMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power && displayNum != "OVERFLOW") {
					if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
						displayNum = "0";
					}
					if (sign == "- MINUS") {
						memoryNum += Double.parseDouble(displayNum);
					} else {
						memoryNum -= Double.parseDouble(displayNum);
					}
					if (memoryNum != 0) {
						memory = "MEMORY";
					} else {
						memory = "";
					}
					updateLabels();
					mrcPressed = false;
				}
			}
		});
		
		btnMPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power && displayNum != "OVERFLOW") {
					if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
						displayNum = "0";
					}
					if (sign == "- MINUS") {
						memoryNum -= Double.parseDouble(displayNum);
					} else {
						memoryNum += Double.parseDouble(displayNum);
					}
					if (memoryNum != 0) {
						memory = "MEMORY";
					} else {
						memory = "";
					}
					updateLabels();
					mrcPressed = false;
				}
			}
		});
		
		btnSqrt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power && displayNum != "OVERFLOW") {
					if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
						displayNum = "0";
					}
					if (!newNum) {
						performOp();
					}
					if (sign == "- MINUS") {
						displayNum = "OVERFLOW";
					} else {
						displayNum = Double.toString(Math.sqrt(Double.parseDouble(displayNum)));
						// Shave off trailing digits if necessary
						if (displayNum.length() > 9) {
							displayNum = displayNum.substring(0,9);
						}
					}
					newNum = true;
					trimDisplay();
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
		
		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (power) {
					if (sign == "- MINUS") {
						sign = "";
					} else {
						sign = "- MINUS";
					}
					trimDisplay();
					updateLabels();
					justCleared = false;
					mrcPressed = false;
				}
			}
		});
	}
	
	/*
	 * Perform an operation specified by the latest registered operation in op
	 */
	private void performOp() {
		double curr = Double.parseDouble(displayNum);
		double saved = curr;
		if (displayNum == "OVERFLOW") {
			newNum = true;
			return;
		}
		if (op == '+') {
			// Perform the operation
			if (sign == "- MINUS") {
				curr = opNum - curr;
			} else {
				curr = opNum + curr;
			}
			// If result is negative, change sign
			if (curr < 0) {
				sign = "- MINUS";
				curr *= -1;
			} else {
				sign = "";
			}
			// If number is too large, return overflow error
			if (curr >= 100000000) {
				displayNum = "OVERFLOW";
			} else {
				displayNum = Double.toString(curr);
			}
			// Shave off trailing digits if necessary
			if (displayNum.length() > 9) {
				displayNum = displayNum.substring(0,9);
			}
		} else if (op == '-') {
			// Perform the operation
			if (sign == "- MINUS") {
				curr = opNum + curr;
			} else {
				curr = opNum - curr;
			}
			// If result is negative, change sign
			if (curr < 0) {
				sign = "- MINUS";
				curr *= -1;
			} else {
				sign = "";
			}
			// If number is too large, return overflow error
			if (curr >= 100000000) {
				displayNum = "OVERFLOW";
			} else {
				displayNum = Double.toString(curr);
			}
			// Shave off trailing digits if necessary
			if (displayNum.length() > 9) {
				displayNum = displayNum.substring(0,9);
			}
		} else if (op == '*') {
			// Perform the operation
			if (sign == "- MINUS") {
				curr = opNum * curr * -1;
			} else {
				curr = opNum * curr;
			}
			// If result is negative, change sign
			if (curr < 0) {
				sign = "- MINUS";
				curr *= -1;
			} else {
				sign = "";
			}
			// If number is too large, return overflow error
			if (curr >= 100000000) {
				displayNum = "OVERFLOW";
			} else {
				displayNum = Double.toString(curr);
			}
			// Shave off trailing digits if necessary
			if (displayNum.length() > 9) {
				displayNum = displayNum.substring(0,9);
			}
		} else if (op == '/') {
			if (curr == 0) {
				displayNum = "OVERFLOW";
			} else {
				// Perform the operation
				if (sign == "- MINUS") {
					curr = opNum / curr * -1;
				} else {
					curr = opNum / curr;
				}
				// If result is negative, change sign
				if (curr < 0) {
					sign = "- MINUS";
					curr *= -1;
				} else {
					sign = "";
				}
				// If number is too large, return overflow error
				if (curr >= 100000000) {
					displayNum = "OVERFLOW";
				} else {
					displayNum = Double.toString(curr);
				}
				// Shave off trailing digits if necessary
				if (displayNum.length() > 9) {
					displayNum = displayNum.substring(0,9);
				}
			}
		} else if (op == '$') {
			// Perform the operation
			if (sign == "- MINUS") {
				curr = opNum - opNum * .01 * curr;
			} else {
				curr = opNum + opNum * .01 * curr;
			}
			// If result is negative, change sign
			if (curr < 0) {
				sign = "- MINUS";
				curr *= -1;
			} else {
				sign = "";
			}
			// If number is too large, return overflow error
			if (curr >= 100000000) {
				displayNum = "OVERFLOW";
			} else {
				displayNum = Double.toString(curr);
			}
			// Shave off trailing digits if necessary
			if (displayNum.length() > 9) {
				displayNum = displayNum.substring(0,9);
			}
		}
		opNum = saved;
		trimDisplay();
		justCleared = false;
		mrcPressed = false;
	}
		
	/*
	 * Called when an operation button is pressed to register the operation and
	 * displayed number and perform the last registered operation in op if necessary
	 */
	private void opPressed(char currOp) {
		if(power) {
			if (!newNum) {
				if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
					displayNum = "0";
				}
				performOp();
				newNum = true;
				op = currOp;
				opNum = Double.parseDouble(displayNum);
				if (sign == "- MINUS") {
					opNum *= -1;
				}
			} else {
				if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
					displayNum = "0";
				}
				op = currOp;
				opNum = Double.parseDouble(displayNum);
				if (sign == "- MINUS") {
					opNum *= -1;
				}
			}
		}
	}
	
	/*
	 * Trim leading and tailing zeros and tailing decimals from the display
	 */
	private void trimDisplay() {
		if (displayNum.length() != 1) {
			// Tailing zeros
			if (displayNum.charAt(displayNum.length() - 1) == '0' && displayNum.length() != 1 && displayNum.indexOf('.') != -1) {
				displayNum = displayNum.substring(0, displayNum.length() - 1);
			}
			// Leading zeros
			while (displayNum.charAt(0) == '0' && displayNum.length() != 1) {
				displayNum = displayNum.substring(1, displayNum.length());
			}
		}
		// Extra decimal
		if (displayNum.charAt(displayNum.length() - 1) == '.' && displayNum.length() == 1) {
			displayNum = "0";
		} else if (displayNum.charAt(displayNum.length() - 1) == '.') {
				displayNum = displayNum.substring(0, displayNum.length() - 1);
		}
	}
	
	private void updateLabels() {
		opLabel.setText(sign);
		memLabel.setText(memory);
		display.setText(displayNum);
	}

	public static void main(String [] args) {
		Calculator window = new Calculator();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
