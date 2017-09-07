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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Artifact implements ActionListener{
	
	JFrame f;
	JPanel p2;
	JButton create,read,update,delete,split,sumbit,search,change,remove,done,go;
	JLabel artifact,artifactGroup,layer,artifactRelationFrom,artifatRelationTo,searchArtifactLabel,layerDisplayLabel,GroupDisplayLayer,deleteLabel,splitArtifact,splitA,splitB;
	JTextField artifactName,relationInputFrom,relationInputTo,searchArtifactInput,deleteInput,splitInput,splitAName,splitBName,aRelationFrom,bRelationFrom,aRelationTo,bRelationTo;
	JComboBox artifactGroupSelect,layerSelect;
	
	ArrayList<String> artifactList = new ArrayList<String>();
	
	int count =0;
	
	Artifact(){
		f = new JFrame();
		
		p2 = new JPanel();
		
		create = new JButton("Create");
		read = new JButton("Read");
		update = new JButton("Update");
		delete = new JButton("Delete");
		split = new JButton("Split");
		
		artifact = new JLabel("Enter the Artifact Name: ");
		artifactGroup = new JLabel("Select Artifact Group: ");
		layer = new JLabel("Selet Layer: ");
		artifactName = new JTextField();
		artifactGroupSelect = new JComboBox();
		layerSelect = new JComboBox();
		artifactRelationFrom = new JLabel();
		relationInputFrom = new JTextField();
		artifatRelationTo = new JLabel();
		relationInputTo = new JTextField();
		sumbit = new JButton("Submit");
		
		searchArtifactLabel = new JLabel("Enter the artifact to search:");
		layerDisplayLabel = new JLabel();
		GroupDisplayLayer = new JLabel();
		searchArtifactInput = new JTextField();
		search = new JButton("Search");
		
		change = new JButton("Change");
		
		deleteLabel = new JLabel("Enter artifact to delete:");
		deleteInput =  new JTextField();
		remove = new JButton("Remove");
		
		splitArtifact = new JLabel("Enter Artifact to split:");
		splitA = new JLabel("Enter Part A:");
		splitB = new JLabel("Enter Part B:");
		splitInput = new JTextField();
		splitAName = new JTextField();
		splitBName = new JTextField();
		done = new JButton("Split");
		aRelationFrom = new JTextField();
		bRelationFrom = new JTextField();
		aRelationTo = new JTextField();
		bRelationTo = new JTextField();
		
		go = new JButton("GO");
		
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
		
		update.setBounds(230, 50, 80, 30);
		p2.add(update);
		update.addActionListener(this);
		
		delete.setBounds(350, 50, 80, 30);
		p2.add(delete);
		delete.addActionListener(this);
		
		split.setBounds(450, 50, 80, 30);
		p2.add(split);
		split.addActionListener(this);
		
		artifact.setBounds(30, 100, 120, 30);
		artifact.setVisible(false);
		p2.add(artifact);
		
		artifactName.setBounds(165, 100, 120, 30);
		artifactName.setVisible(false);
		p2.add(artifactName);
		
		artifactGroup.setBounds(30, 150, 120, 30);
		artifactGroup.setVisible(false);
		p2.add(artifactGroup);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
			Statement st = con.createStatement();
			String q = "SELECT * FROM artifactgroup;";
			ResultSet rs = st.executeQuery(q);
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			while(rs.next()){
				String lay = rs.getString("aGroup");
				arrayList.add(lay);
				}
			
			artifactGroupSelect.setModel(new DefaultComboBoxModel(arrayList.toArray()));
			artifactGroupSelect.setBounds(165,150,120,30);
			artifactGroupSelect.setVisible(false);
			p2.add(artifactGroupSelect);
			con.close();
		}

		catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		layer.setBounds(30, 200, 120, 30);
		layer.setVisible(false);
		p2.add(layer);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
			Statement st = con.createStatement();
			String q = "SELECT * FROM layer;";
			ResultSet rs = st.executeQuery(q);
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			while(rs.next()){
				String lay = rs.getString("layers");
				arrayList.add(lay);
				}
			
			layerSelect.setModel(new DefaultComboBoxModel(arrayList.toArray()));
			layerSelect.setBounds(165, 200, 120, 30);
			layerSelect.setVisible(false);
			p2.add(layerSelect);
			con.close();
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		artifactRelationFrom.setBounds(30, 250, 120, 30);
		artifactRelationFrom.setVisible(false);
		p2.add(artifactRelationFrom);
		
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
			artifactRelationFrom.setText(oneArtifact.toString());
			con.close();
	}catch (SQLException e1) {
		
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		relationInputFrom.setBounds(170, 250, 120, 30);
		relationInputFrom.setVisible(false);
		p2.add(relationInputFrom);
		
		String oneArtifact1 = "TO ";
		
		for(int i=0;i<artifactList.size();i++){
			oneArtifact1 = oneArtifact1 +artifactList.get(i)+",";
		}
		
		artifatRelationTo.setBounds(30, 300, 120, 30);
		artifatRelationTo.setVisible(false);
		p2.add(artifatRelationTo);
		
		artifatRelationTo.setText(oneArtifact1.toString());
		
		relationInputTo.setBounds(170, 300, 120, 30);
		relationInputTo.setVisible(false);
		p2.add(relationInputTo);
		
		
		sumbit.setBounds(30, 350, 80, 30);
		sumbit.setVisible(false);
		p2.add(sumbit);
		sumbit.addActionListener(this);
		
		searchArtifactLabel.setBounds(50, 50, 120, 30);
		searchArtifactLabel.setVisible(false);
		p2.add(searchArtifactLabel);
		

		searchArtifactInput.setBounds(190, 50, 120, 30);
		searchArtifactInput.setVisible(false);
		p2.add(searchArtifactInput);
		
		search.setBounds(80, 100, 80, 30);
		search.setVisible(false);
		search.addActionListener(this);
		p2.add(search);
		
		GroupDisplayLayer.setBounds(30, 150, 200, 30);
		GroupDisplayLayer.setVisible(false);
		p2.add(GroupDisplayLayer);
		
		layerDisplayLabel.setBounds(30, 200, 300, 30);
		layerDisplayLabel.setVisible(false);
		p2.add(layerDisplayLabel);
		
		change.setBounds(80, 100, 80, 30);
		change.setVisible(false);
		change.addActionListener(this);
		p2.add(change);
		
		deleteLabel.setBounds(50, 50, 120, 30);
		deleteLabel.setVisible(false);
		p2.add(deleteLabel);
		
		deleteInput.setBounds(190, 50, 120, 30);
		deleteInput.setVisible(false);
		p2.add(deleteInput);
		
		remove.setBounds(50, 100, 80, 30);
		remove.setVisible(false);
		p2.add(remove);
		remove.addActionListener(this);
		
		splitArtifact.setBounds(50, 50, 120, 30);
		splitArtifact.setVisible(false);
		p2.add(splitArtifact);
		
		splitInput.setBounds(190, 50, 120, 30);
		splitInput.setVisible(false);
		p2.add(splitInput);
		
		splitA.setBounds(50, 100, 120, 30);
		splitA.setVisible(false);
		p2.add(splitA);
		
		splitAName.setBounds(190, 100, 120, 30);
		splitAName.setVisible(false);
		p2.add(splitAName);
		
		splitB.setBounds(50, 150, 120, 30);
		splitB.setVisible(false);
		p2.add(splitB);
		
		splitBName.setBounds(190, 150, 120, 30);
		splitBName.setVisible(false);
		p2.add(splitBName);
		
		
		done.setBounds(190, 200, 120, 30);
		done.setVisible(false);
		done.addActionListener(this);
		p2.add(done);
		
		go.setBounds(330, 200, 120, 30);
		go.setVisible(false);
		go.addActionListener(this);
		p2.add(go);
		
		aRelationFrom.setBounds(150, 250, 120, 30);
		aRelationFrom.setVisible(false);
		p2.add(aRelationFrom);
		
		bRelationFrom.setBounds(50, 300, 120, 30);
		bRelationFrom.setVisible(false);
		p2.add(bRelationFrom);
		
		aRelationTo.setBounds(50, 350, 120, 30);
		aRelationTo.setVisible(false);
		p2.add(aRelationTo);
		
		bRelationTo.setBounds(50, 400, 120, 30);
		bRelationTo.setVisible(false);
		p2.add(bRelationTo);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
		String inArtifact = null,partA = null,partB = null,aRelFrom1 = null,bRelFrom1 = null,aRelTo1 =null,bRelTo1= null;
		if(e.getSource()==create){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			split.setVisible(false);
			
			artifact.setVisible(true);
			artifactGroup.setVisible(true);
			layer.setVisible(true);
			artifactName.setVisible(true);
			artifactGroupSelect.setVisible(true);
			layerSelect.setVisible(true);
			artifactRelationFrom.setVisible(true);
			relationInputFrom.setVisible(true);
			artifatRelationTo.setVisible(true);
			relationInputTo.setVisible(true);
			sumbit.setVisible(true);
		}
		
		else if(e.getSource()==sumbit){
			int t=0;
			String artifact = artifactName.getText().toString();
			String artifactgroup = (String) artifactGroupSelect.getSelectedItem();
			String layer = (String) layerSelect.getSelectedItem();
			String fromrel1 = relationInputFrom.getText().toString();
			String torel1 = relationInputTo.getText().toString();
			char[] fromrel = fromrel1.toCharArray();
			char[] torel = torel1.toCharArray();
			if(fromrel1.length()==count && torel1.length()==count){
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			try {
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				String q = "SELECT * FROM artifact;";
				ResultSet rs = st.executeQuery(q);
				
				
				while(rs.next()){
					String artifactTable = rs.getString("artifact");
					if(artifact.equals(artifactTable)){
						t=1;
					}
				}
				
				if(t!=0){
					JOptionPane.showMessageDialog(f, "The specified artifact already exist!");
				}
				else{
					
					
					
					String query = "INSERT INTO artifact (artifact,aGroup,layer)"+ "values(?,?,?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, artifact);
					ps.setString(2, artifactgroup);
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
			else{
				JOptionPane.showMessageDialog(f, "The relation entered does not match");
			}
		}
		
		else if(e.getSource()==read){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			split.setVisible(false);
			
			searchArtifactLabel.setVisible(true);
			layerDisplayLabel.setVisible(true);
			GroupDisplayLayer.setVisible(true);
			searchArtifactInput.setVisible(true);
			search.setVisible(true);
		}
		
		else if(e.getSource()==search){
			String search = searchArtifactInput.getText().toString();
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			try {
				int t = 0;
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				String q = "SELECT * FROM artifact;";
				ResultSet rs = st.executeQuery(q);
				
				String layer3 = null;
				String agroup3 = null;
				
				while(rs.next()){
					String lay = rs.getString("layer");
					String group1 = rs.getString("agroup");
					String artifact1 = rs.getString("artifact");
					if (artifact1.equals(search)){
						t=1;
						layer3 = lay;
						agroup3 = group1;
					}
					
					
				}
				if(t==0){
					JOptionPane.showMessageDialog(f, "The artifact already exist!");
				}
				else{
				String disLay = "The Layer is "+layer3;
				String disGrp = "The Artifact Group is "+agroup3;
				
				layerDisplayLabel.setText(disLay.toString());
				GroupDisplayLayer.setText(disGrp.toString());
				}
				con.close();
		}catch (SQLException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		}
		
		else if(e.getSource()==update){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			split.setVisible(false);
			
			searchArtifactLabel.setVisible(true);
			searchArtifactInput.setVisible(true);
			artifactGroupSelect.setVisible(true);
			artifactGroup.setVisible(true);
			change.setVisible(true);
		}
		
		else if(e.getSource()==change){
			String artifactInput = searchArtifactInput.getText().toString();
			String selcetGroup = (String) artifactGroupSelect.getSelectedItem();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Connection con;
			try {
				
				int t =0;
				con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				
				String q = "SELECT artifact FROM artifat;";
				ResultSet rs = st.executeQuery(q);
				
				while(rs.next()){
					String arti = rs.getString("artifact");
					if (artifactInput.equals(arti)){
						t = 1;
					}
				}
				
				if(t==0){
					JOptionPane.showMessageDialog(f, "Artifact does not exist");
				}
				else {
				q = "SELECT * FROM artifactGroup;";
				rs = st.executeQuery(q);
				
				String layer = null;
				
				while(rs.next()){
					String lay = rs.getString("layer");
					String group1 = rs.getString("agroup");
					if (selcetGroup.equals(group1)){
						layer = lay;
					}
				}
				System.out.println(layer);
				
				q = "UPDATE artifact SET aGroup=? , layer=? WHERE artifact=?";
				 PreparedStatement preparedStmt = con.prepareStatement(q);
				 preparedStmt.setString(1,selcetGroup );
				 preparedStmt.setString(2,layer );
				 preparedStmt.setString(3,artifactInput );
				 
				 preparedStmt.executeUpdate();
				}
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		else if(e.getSource()==delete){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			split.setVisible(false);
			
			deleteLabel.setVisible(true);
			deleteInput.setVisible(true);
			remove.setVisible(true);
			
		}
		
		else if(e.getSource() == remove){
			
			String del = deleteInput.getText().toString();
			
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
				
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				String sql = "DELETE FROM artifact WHERE artifact='"+del+"'";
				s.executeUpdate(sql);
				String q = "DELETE FROM relation WHERE artifactFrom='"+inArtifact+"' OR artifactTo='"+inArtifact+"';";
				 s.executeUpdate(q);
				    }
				    con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
		}
		
		else if(e.getSource()==go){
			artifactRelationFrom.setVisible(true);
			aRelationFrom.setVisible(true);
			bRelationFrom.setVisible(true);
			aRelationTo.setVisible(true);
			bRelationTo.setVisible(true);
			
			done.setVisible(true);
			
			inArtifact = splitInput.getText().toString();
			 partA = splitAName.getText().toString();
			 partB = splitBName.getText().toString();
			 
			
			
			int index = artifactList.indexOf(inArtifact);
			artifactList.remove(index);
			
			
			artifactList.add(partA);
			artifactList.add(partB);
			
			String oneArtifact = "";
			
			for(int i=0;i<artifactList.size();i++){
				oneArtifact = oneArtifact +artifactList.get(i)+",";
			}
			
			artifactRelationFrom.setText(oneArtifact.toString());
		}
		
		else if(e.getSource()==split){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			split.setVisible(false);
			
			splitArtifact.setVisible(true);
			splitInput.setVisible(true);
			splitA.setVisible(true);
			splitAName.setVisible(true);
			splitB.setVisible(true);
			splitBName.setVisible(true);
			
			
			go.setVisible(true);
			
		}
		
		else if(e.getSource()==done){
			
			aRelFrom1 = aRelationFrom.getText().toString();
			bRelFrom1 = bRelationFrom.getText().toString();
			aRelTo1 = aRelationTo.getText().toString();
			bRelTo1 = bRelationTo.getText().toString();
			
			inArtifact = splitInput.getText().toString();
			 partA = splitAName.getText().toString();
			 partB = splitBName.getText().toString();
			
			char[] arelfrom = aRelFrom1.toCharArray();
			char[] brelfrom = bRelFrom1.toCharArray();
			char[] arelto = aRelTo1.toCharArray();
			char[] brelto = bRelTo1.toCharArray();  
			int t =0;
			
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
				
				
				String q = "Select layer,aGroup FROM artifact WHERE artifact='"+inArtifact+"';";
				ResultSet rs = s.executeQuery(q);
				String layerTable = null;
				String groupTable = null;
				while(rs.next()){
					layerTable = rs.getString("layer");
					groupTable = rs.getString("aGroup");
					
				}
				
				
				 q = "DELETE FROM artifact WHERE artifact='"+inArtifact+"';";
				 s.executeUpdate(q);
				 
				 q = "DELETE FROM relation WHERE artifactFrom='"+inArtifact+"' OR artifactTo='"+inArtifact+"';";
				 s.executeUpdate(q);
				 
				 if(aRelFrom1.length()==artifactList.size() && bRelFrom1.length()==artifactList.size() && aRelTo1.length()==artifactList.size() && bRelTo1.length()==artifactList.size()){
						
						
							
						q = "SELECT * FROM artifact;";
						rs = s.executeQuery(q);
							
							
							while(rs.next()){
								String artifactTable = rs.getString("artifact");
								if(partA.equals(artifactTable) || partB.equals(artifactTable)){
									t=1;
								}
							}
							
							if(t!=0){
								JOptionPane.showMessageDialog(f, "The specified artifact already exist!");
							}
							else{
								
								
								
								String query = "INSERT INTO artifact (artifact,aGroup,layer)"+ "values(?,?,?)";
								PreparedStatement ps = con.prepareStatement(query);
								ps.setString(1, partA);
								ps.setString(2, groupTable);
								ps.setString(3, layerTable);
								ps.execute();
								
								query = "INSERT INTO artifact (artifact,aGroup,layer)"+ "values(?,?,?)";
								 ps = con.prepareStatement(query);
								ps.setString(1, partB);
								ps.setString(2, groupTable);
								ps.setString(3, layerTable);
								ps.execute();
								
								for (String Element : artifactList){
									int i = artifactList.indexOf(Element);
									//int r = (int)(rel[i]);
									String c = Character.toString(arelfrom[i]);
									
									query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
									ps = con.prepareStatement(query);
									ps.setString(1, partA);
									ps.setString(2, Element);
									ps.setString(3, c);
									ps.execute();
									
									
								}
								
								
								
								for (String Element : artifactList){
									int i = artifactList.indexOf(Element);
									//int r = (int)(rel[i]);
									String c = Character.toString(brelfrom[i]);
									
									query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
									ps = con.prepareStatement(query);
									ps.setString(1, partB);
									ps.setString(2, Element);
									ps.setString(3, c);
									ps.execute();
									
									
								}
								
								for (String Element : artifactList){
									int i = artifactList.indexOf(Element);
									//int r = (int)(rel[i]);
									String c = Character.toString(arelto[i]);
									
									query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
									ps = con.prepareStatement(query);
									ps.setString(1, Element);
									ps.setString(2, partB);
									ps.setString(3, c);
									ps.execute();
								}
								
								for (String Element : artifactList){
									int i = artifactList.indexOf(Element);
									//int r = (int)(rel[i]);
									String c = Character.toString(brelto[i]);
									
									query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
									ps = con.prepareStatement(query);
									ps.setString(1, Element);
									ps.setString(2, partB);
									ps.setString(3, c);
									ps.execute();
								}
								
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
