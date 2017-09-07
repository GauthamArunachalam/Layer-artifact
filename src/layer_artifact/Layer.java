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
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Layer implements ActionListener{
	
	JFrame f;
	JButton create,read,delete,submit,search,remove;
	JLabel enterLayer,enterAGroup,enterArtifact,relationFrom,relationTo,layerRank,layerSearch,aGrpDisplay,artifactDisplay,dislay,disartif;
	JTextField layerInput,aGroupInput,artifactInput,relationFromInput,relationToInput,rankInput,laySearch;
	JPanel p2;
	
	int count;
	
	ArrayList<String> artifactList = new ArrayList<String>();
	
	Layer(){
		
		f = new JFrame();
		
		p2 = new JPanel();
		
		create = new JButton("Create");
		read = new JButton("Read");
		delete = new JButton("Delete");
		
		enterLayer = new JLabel ("Enter Layer:");
		enterAGroup = new JLabel("Enter Artifact Group:");
		enterArtifact = new JLabel("Enter Artifact");
		relationFrom = new JLabel();
		relationTo = new JLabel();
		layerInput = new JTextField();
		aGroupInput = new JTextField();
		artifactInput = new JTextField();
		relationFromInput = new JTextField();
		relationToInput = new JTextField();
		submit = new JButton("Submit");
		layerRank = new JLabel("Enter Layer Rank:");
		rankInput = new JTextField();
		
		
		
		layerSearch = new JLabel("Enter the Layer to be searched:");
		laySearch = new JTextField();
		search = new JButton("Search");
		aGrpDisplay = new JLabel();
		artifactDisplay = new JLabel();
		dislay = new JLabel("The Artifact Group under this layer is :");
		disartif = new JLabel("The Artifact under this layer is:");
	
		
		remove = new JButton("Remove");
	}
	
	
	public void dis(){
		f.setSize(1800, 1000);
		f.setVisible(true);
		f.setLayout(null);
		
		p2.setBounds(450,150, 1000, 1000);
		p2.setVisible(true);
		p2.setLayout(null);
		f.add(p2);
		
		
		create.setBounds(30,50,80,30);
		p2.add(create);
		create.addActionListener(this);
		
		read.setBounds(130, 50, 80, 30);
		p2.add(read);
		read.addActionListener(this);
		

		delete.setBounds(350, 50, 80, 30);
		p2.add(delete);
		delete.addActionListener(this);
		
		enterLayer.setBounds(50, 50, 120, 30);
		enterLayer.setVisible(false);
		p2.add(enterLayer);
		
		layerInput.setBounds(190, 50, 120, 30);
		layerInput.setVisible(false);
		p2.add(layerInput);
		
		remove.setBounds(80, 100, 120, 30);
		remove.setVisible(false);
		remove.addActionListener(this);
		p2.add(remove);
		
		layerRank.setBounds(50, 100, 120, 30);
		layerRank.setVisible(false);
		p2.add(layerRank);
		
		rankInput.setBounds(190, 100, 120, 30);
		rankInput.setVisible(false);
		p2.add(rankInput);
		
		enterAGroup.setBounds(50, 150, 120, 30);
		enterAGroup.setVisible(false);
		p2.add(enterAGroup);
		
		aGroupInput.setBounds(190, 150, 120, 30);
		aGroupInput.setVisible(false);
		p2.add(aGroupInput);
		
		enterArtifact.setBounds(50, 200, 120, 30);
		enterArtifact.setVisible(false);
		p2.add(enterArtifact);
		
		artifactInput.setBounds(190, 200, 120, 30);
		artifactInput.setVisible(false);
		p2.add(artifactInput);
		
		relationFrom.setBounds(50, 250, 120, 30);
		relationFrom.setVisible(false);
		p2.add(relationFrom);
		
		
		layerSearch.setBounds(50 ,50 ,120 ,30);
		layerSearch.setVisible(false);
		p2.add(layerSearch);
		
		laySearch.setBounds(190, 50, 80, 30);
		laySearch.setVisible(false);
		p2.add(laySearch);
		
		search.setBounds(120 ,100 ,80 ,30);
		search.setVisible(false);
		search.addActionListener(this);
		p2.add(search);
		
		dislay.setBounds(50, 150, 250, 250);
		dislay.setVisible(false);
		p2.add(dislay);
		
		disartif.setBounds(350, 150, 250, 250);
		disartif.setVisible(false);
		p2.add(disartif);
		
		aGrpDisplay.setBounds(50, 200, 250, 250);
		aGrpDisplay.setVisible(false);
		p2.add(aGrpDisplay);
		
		artifactDisplay.setBounds(350, 200, 250, 250);
		artifactDisplay.setVisible(false);
		p2.add(artifactDisplay);
		
try {
			
			Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
			Statement st = con.createStatement();
			String q = "SELECT * FROM artifact;";
			ResultSet rs = st.executeQuery(q);
			
			String oneArtifact ="From " ;
			
			while(rs.next()){
				count ++;
				String artifactTable = rs.getString("artifact");
				artifactList.add(artifactTable);
			}
			
			for(int i=0;i<artifactList.size();i++){
				oneArtifact = oneArtifact +artifactList.get(i)+",";
			}
			relationFrom.setText(oneArtifact.toString());
			
			con.close();
	}catch (SQLException e1) {
		
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
		relationFromInput.setBounds(190, 250, 120, 30);
		relationFromInput.setVisible(false);
		p2.add(relationFromInput);
		
		String s = "To ";
		
		for(int i=0;i<artifactList.size();i++){
			s = s +artifactList.get(i)+",";
		}
		
		relationTo.setBounds(50, 300, 120, 30);
		relationTo.setVisible(false);
		p2.add(relationTo);
		
		relationTo.setText(s.toString());
		
		
		relationToInput.setBounds(190, 300, 120, 30);
		relationToInput.setVisible(false);
		p2.add(relationToInput);
		
		
		submit.setBounds(80, 350, 120, 30);
		submit.setVisible(false);
		submit.addActionListener(this);
		p2.add(submit);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==create){
			create.setVisible(false);
			read.setVisible(false);
			delete.setVisible(false);
			
			enterLayer.setVisible(true);
			enterAGroup.setVisible(true);
			enterArtifact.setVisible(true);
			layerRank.setVisible(true);
			rankInput.setVisible(true);
			relationFrom.setVisible(true);
			relationTo.setVisible(true);
			layerInput.setVisible(true);
			aGroupInput.setVisible(true);
			artifactInput.setVisible(true);
			relationFromInput.setVisible(true);
			relationToInput.setVisible(true);
			submit.setVisible(true);
		}
		
		else if(e.getSource()==submit){
			
			int t=0;
			String layer = layerInput.getText().toString();
			int  rank = Integer.parseInt(rankInput.getText().toString());
			String group = aGroupInput.getText().toString();
			String artifact = artifactInput.getText().toString();
			
			String fromrel1 = relationFromInput.getText().toString();
			String torel1 = relationToInput.getText().toString();
			char[] fromrel = fromrel1.toCharArray();
			char[] torel = torel1.toCharArray();
			
			System.out.println(layer);
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			try {
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				String q = "SELECT * FROM layer;";
				ResultSet rs = st.executeQuery(q);
				
				ArrayList<String> layersList = new ArrayList<String>();
				
				while(rs.next()){
					String lay = rs.getString("layers");
					System.out.println(lay);
					layersList.add(lay);
					if(lay.equals(layer)){
						
						t=1;
					}
				}
				
				if(t!=0){
					JOptionPane.showMessageDialog(f, "The specified layer already exist!");
				}
				else{
					//layersList.add(rank-1, layer);
					
					String drop = "DROP TABLE layer";
					PreparedStatement ps = con.prepareStatement(drop);
					ps.execute();
					
					Statement s = con.createStatement();
					
					String table = "CREATE TABLE layer (layers VARCHAR(50));";
					s.executeUpdate(table);
					int tt=0;
					for (int i=0 ;i<=layersList.size();i++){
						//System.out.println("FUCK");
						if(i==rank-1){
							String query = "INSERT INTO layer (layers)"+ "values(?)";
							ps = con.prepareStatement(query);
							ps.setString(1, layer);
							ps.execute();
						}
						else{
						String query = "INSERT INTO layer (layers)"+ "values(?)";
						ps = con.prepareStatement(query);
						ps.setString(1, layersList.get(tt));
						ps.execute();
						tt++;
						}
						
						
					}
					
				
					String query = "INSERT INTO artifactGroup (aGroup,layer)"+ "values(?,?)";
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
					
					for (String Element : artifactList){
						int i = artifactList.indexOf(Element);
						String c = Character.toString(fromrel[i]);
						
						query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1, artifact);
						ps.setString(2, Element);
						ps.setString(3, c);
						ps.execute();
						
						
					}
					
					for (String Element : artifactList){
						int i = artifactList.indexOf(Element);
						String c = Character.toString(torel[i]);
						
						query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1, Element);
						ps.setString(2, artifact);
						ps.setString(3, c);
						ps.execute();
						
						
					}
				}
				con.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
		
		else if(e.getSource() == read){
			create.setVisible(false);
			read.setVisible(false);
			delete.setVisible(false);
			
			
			dislay.setVisible(true);
			disartif.setVisible(true);
			layerSearch.setVisible(true);
			laySearch.setVisible(true);
			search.setVisible(true);
			aGrpDisplay.setVisible(true);
			artifactDisplay.setVisible(true);
			
		}
		
		else if(e.getSource()==search){
			
			String search = laySearch.getText().toString();
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			try {
				int t =0;
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				String q = "SELECT * FROM artifact;";
				ResultSet rs = st.executeQuery(q);
				
				ArrayList<String> group = new ArrayList<String>();
				ArrayList<String> artifact = new ArrayList<String>();
				
				while(rs.next()){
					String lay = rs.getString("layer");
					String group1 = rs.getString("agroup");
					String artifact1 = rs.getString("artifact");
					if (lay.equals(search)){
						t = 1;
						group.add(group1);
						artifact.add(artifact1);
					}
					
					
				}
				
				Set<String> grp = new HashSet<>();
				grp.addAll(group);
				group.clear();
				group.addAll(grp);
				if(t==0){
					JOptionPane.showMessageDialog(f, "No such layer exist");
				}
				else{
				String oneGroup ="";
				for (int i=0;i<group.size();i++){
					oneGroup = oneGroup +group.get(i)+",";
				}
				String oneArtifact ="" ;
				for(int i=0;i<artifact.size();i++){
					oneArtifact = oneArtifact +artifact.get(i)+",";
				}
				aGrpDisplay.setText(oneGroup.toString());
				artifactDisplay.setText(oneArtifact.toString());
				
				}
				con.close();
		}catch (SQLException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
	
		else if(e.getSource()==delete){
			create.setVisible(false);
			read.setVisible(false);
			delete.setVisible(false);
			
			enterLayer.setVisible(true);
			layerInput.setVisible(true);
			remove.setVisible(true);
		}
		
		else if(e.getSource()==remove){
			String delLay = layerInput.getText().toString();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				Connection con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement s = con.createStatement();
				
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				    	String q = "DELETE FROM layer WHERE layers='"+delLay+"'";
				    	s.execute(q);
				    	
				    	ArrayList<String> agroup = new ArrayList<String>();
				    	ArrayList<String> artifact = new ArrayList<String>();
				    	
				    	q = "SELECT * FROM artifactGroup;";
				    	ResultSet rs = s.executeQuery(q);
				    	
				    	while(rs.next()){
							String agrp = rs.getString("aGroup");
							String lay = rs.getString("layer");
							if (lay.equals(delLay)){
								agroup.add(agrp);
							}
						}
				    	
				    	q = "DELETE FROM artifactGroup WHERE layer='"+delLay+"'";
				    	s.execute(q);
				    	
				    	q = "SELECT * FROM artifact";
				    	ResultSet rs1 = s.executeQuery(q);
				    	while(rs1.next()){
				    		String arti = rs1.getString("artifact");
				    		String lay = rs1.getString("layer");
				    		if(lay.equals(delLay)){
				    			artifact.add(arti);
				    		}
				    				
				    	}
				    	
				    	q = "DELETE FROM artifact WHERE layer='"+delLay+"'";
				    	s.execute(q);
				    	
				    	for(String delElement : artifact){
							q = "DELETE FROM relation WHERE artifactFrom='"+delElement+"' OR artifactTo='"+delElement+"';";
							 s.executeUpdate(q);
						}
				    	
				    }
				
				    con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
