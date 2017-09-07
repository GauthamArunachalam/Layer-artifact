package layer_artifact;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Switch implements ActionListener{
	
	JFrame f;
	JPanel p1,p2;
	JComboBox artifactFrom,artifactTo;
	JTextField relInput;
	JButton change;
	
	ArrayList<String> artifactList = new ArrayList<String>();
	
	Switch(){
		f = new JFrame();
		
		p2 = new JPanel();
		
		artifactFrom = new JComboBox();
		artifactTo = new JComboBox();
		
		relInput = new JTextField();
		
		change = new JButton("Change");
	}
	
	public void dis()  {
		
		f.setSize(1800, 1000);
		f.setVisible(true);
		f.setLayout(null);
		
		p2.setBounds(450,150, 1000, 1000);
		p2.setVisible(true);
		p2.setLayout(null);
		f.add(p2);
		
		artifactFrom.setBounds(50, 50, 120, 30);
		p2.add(artifactFrom);
		
		artifactTo.setBounds(190, 50, 120, 30);
		p2.add(artifactTo);
		
		relInput.setBounds(330, 50, 120, 30);
		p2.add(relInput);
		
		change.setBounds(50, 100, 120, 30);
		change.addActionListener(this);
		p2.add(change);
		

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
			
			Statement st = con.createStatement();
			
			String q = "SELECT artifact FROM artifact";
			ResultSet rs = st.executeQuery(q);
			
			while(rs.next()){
				String arti = rs.getString("artifact");
				artifactList.add(arti);
				
				
				}
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		artifactFrom.setModel(new DefaultComboBoxModel(artifactList.toArray()));
		artifactTo.setModel(new DefaultComboBoxModel(artifactList.toArray()));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==change){
			
			String artiFrom = (String) artifactFrom.getSelectedItem();
			String artiTo = (String) artifactTo.getSelectedItem();
			
			String rel = relInput.getText().toString();
			
			if(!artiFrom.equals(artiTo)){
				if(rel.length()==1){
					
					try {
						Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
						
						Statement st = con.createStatement();
						
						String q = "UPDATE relation SET rel=? WHERE artifactFrom=? AND artifactTo=?";
						PreparedStatement preparedStmt = con.prepareStatement(q);
						 preparedStmt.setString(1,rel );
						 preparedStmt.setString(2,artiFrom );
						 preparedStmt.setString(3,artiTo );
						 
						 preparedStmt.executeUpdate();
						 
						 
						 con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		}
		
	}

}
