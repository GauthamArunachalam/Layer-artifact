package layer_artifact;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateSSD implements ActionListener{
	
	JFrame f;
	JButton b1,b2;
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	
	CreateSSD(){
		f = new JFrame();
		b1 = new JButton("Create the data");
		b2 = new JButton("Submit");
		l1 = new JLabel("Enter the Layer Name:");
		l2 = new JLabel("Enter the Arifact Group Name:");
		l3 = new JLabel("Enter the Artifact Name:");
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		
	}
	
	public void dis(){
		f.setSize(1800, 1000);
		f.setVisible(true);
		f.setLayout(null);
		
		b1.setBounds(550,550,180,60);
		b1.setBackground(Color.lightGray);
		f.add(b1);
		b1.addActionListener(this);
		
		l1.setBounds(100, 150, 180, 30);
		l1.setVisible(false);
		f.add(l1);
		
		t1.setBounds(300, 150, 180, 30);
		t1.setVisible(false);
		f.add(t1);
		
		l2.setBounds(100, 200, 180, 30);
		l2.setVisible(false);
		f.add(l2);
		
		t2.setBounds(300, 200, 180, 30);
		t2.setVisible(false);
		f.add(t2);
		
		l3.setBounds(100, 250, 180, 30);
		l3.setVisible(false);
		f.add(l3);
		
		t3.setBounds(300, 250, 180, 30);
		t3.setVisible(false);
		f.add(t3);
		
		b2.setBounds(400, 300, 80, 30);
		b2.setVisible(false);
		b2.addActionListener(this);
		f.add(b2);
		
		b1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        b1.setBackground(Color.DARK_GRAY);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        b1.setBackground(Color.LIGHT_GRAY);
		    }
		});
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==b1){
			b1.setVisible(false);
			
			l1.setVisible(true);
			t1.setVisible(true);
			l2.setVisible(true);
			t2.setVisible(true);
			l3.setVisible(true);
			t3.setVisible(true);
			b2.setVisible(true);
				
		}
		
		else if(e.getSource()==b2){
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			
			Connection con;
			try {
				con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement s = con.createStatement();
				
				String table = "CREATE TABLE layer (layers VARCHAR(50));";
				s.executeUpdate(table);
				
				table = "CREATE TABLE artifactGroup (aGroup VARCHAR(50) , layer VARCHAR(50));";
				s.executeUpdate(table);
				
				table = "CREATE TABLE artifact (artifact VARCHAR(50) , aGroup VARCHAR(50) , layer VARCHAR(50));";
				s.executeUpdate(table);
				
				table = "CREATE TABLE relation (artifactFrom VARCHAR(50) , artifactTo VARCHAR(50) , rel VARCHAR(50));";
				s.executeUpdate(table);
				
				String layer = t1.getText().toString();
				String group = t2.getText().toString();
				String artifact = t3.getText().toString();
				
				String query = "INSERT INTO layer (layers)"+ "values(?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, layer);
				ps.execute();
				
				query = "INSERT INTO artifactGroup (aGroup,layer)"+ "values(?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, group);
				ps.setString(2, layer);
				ps.execute();
				
				query = "INSERT INTO artifact (artifact,aGroup,layer)"+ "values(?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, artifact);
				ps.setString(2, group);
				ps.setString(3, layer);
				ps.execute();
				
				
				
				
			} catch (SQLException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
