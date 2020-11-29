import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.io.*;




public class Chess extends JFrame implements ActionListener{
	
	private boolean moveFlag = false;

	private ImageIcon tempImg;

	private boolean colorFlag=true;

	private JButton casilla[][] =  new JButton[8][8];

	private String turn = "white";

	private String nameTemp = "";

	private JLabel lblTurn;

	public Chess(){

		
		JPanel tablero = new JPanel();
		tablero.setPreferredSize(new Dimension(600, 600));
		tablero.setLayout(new GridLayout(8, 8)); 
		
		for(int rows = 0; rows < 8; rows++){
			for(int column = 0; column < 8; column++){
				File imgFile = new File("PIECES/"+rows+"x"+column+".png");
				if (!imgFile.exists()) {
					casilla[rows][column] = new JButton("",null);
				}else{
					ImageIcon piece = new ImageIcon("PIECES/"+rows+"x"+column+".png");
					casilla[rows][column] = new JButton("",resizeIcon(piece, 70,70));
				}
				
				casilla[rows][column].addActionListener(this);
				casilla[rows][column].setMargin(new Insets(0, 0, 0, 0));
				if (!colorFlag) {
					casilla[rows][column].setBackground(new Color(255,255,255));
				}else{
					casilla[rows][column].setBackground(new Color(31,176,82));
				}
				colorFlag=!colorFlag;

				if (column==7) {
					colorFlag=!colorFlag;
				}
				casilla[rows][column].setBorder(null);
				casilla[rows][column].setPreferredSize(new Dimension(100, 100));
				
				tablero.add(casilla[rows][column]);

				if (rows<2) {
					casilla[rows][column].setName("black");
				}
				if (rows>5) {
					casilla[rows][column].setName("white");
				}
				if(rows>=2 && rows<=5){
					casilla[rows][column].setName("");
				}
			
			}
		}	
		
		this.add(tablero);

		JPanel statusBar = new JPanel();

		lblTurn = new JLabel("White moves");

		statusBar.setPreferredSize(new Dimension(100, 25));

		statusBar.add(lblTurn);



		this.add(statusBar, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600,600);		 
	}

	public void actionPerformed(ActionEvent e){
		String name = ((JButton)e.getSource()).getName();
		//System.out.println("Name celda: "+name+ "Y Ok: "+moveFlag+" y turno celda:" +turn);
		if (moveFlag || name.equals(turn)) {
			//System.out.println(name);
			if (moveFlag) {
				((JButton)e.getSource()).setIcon(resizeIcon(tempImg, 70,70));
				((JButton)e.getSource()).setName(nameTemp);
				moveFlag=false;
				if (turn.equals("white")) {
					turn = "black";
					lblTurn.setText("White moves");
				}else{
					turn = "white";
					lblTurn.setText("Black moves");
				}
			}else{
				tempImg = (ImageIcon)((JButton)e.getSource()).getIcon();
				if (tempImg!=null) {
					((JButton)e.getSource()).setIcon(null);
					((JButton)e.getSource()).setName("");
					nameTemp = name;
					moveFlag=true;
				}
			}
		}
		
			
	}

	private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();  
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(resizedImage);
	}	

	public static void main(String args[]){ 
		new Chess(); 
	} 
}
