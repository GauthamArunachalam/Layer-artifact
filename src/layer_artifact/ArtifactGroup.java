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

public class ArtifactGroup implements ActionListener{
	
	int count = 0;
	
	ArrayList<String> artifactList = new ArrayList<String>();
	
	JFrame f;
	JPanel p2;
	JButton create,read,update,delete,submit,search,remove,change;
	JTextField artifactInput,artifactGroupInput,relationFromInput,relationToInput;
	JLabel artifactGroupName,artifactName,selectLayerName,artifactRelationFrom,artifactRelationTo,artifactsSet,Layer;
	JComboBox layerList;
	
	ArtifactGroup(){
		f = new JFrame();
		
		p2 = new JPanel();
		
		create = new JButton("Create");
		read = new JButton("Read");
		update = new JButton("Update");
		delete = new JButton("Delete");
		
		artifactGroupName = new JLabel("Enter artifact Group: ");
		artifactName = new JLabel("Enter artifact name: ");
		selectLayerName = new JLabel("Select Layer Name:");
		
		artifactInput = new JTextField();
		artifactGroupInput = new JTextField();
		
		layerList = new JComboBox();
		
		submit = new JButton("Submit");
		
		artifactRelationFrom = new  JLabel();
		relationFromInput = new JTextField();
		artifactRelationTo = new  JLabel();
		relationToInput = new JTextField();
		
		search = new JButton("Search");
		artifactsSet = new JLabel();
		Layer = new JLabel();
		
		remove = new JButton("Remove");
		
		change = new JButton("Change");
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
		
		artifactGroupName.setBounds(50, 50, 120, 30);
		artifactGroupName.setVisible(false);
		p2.add(artifactGroupName);
		
		artifactGroupInput.setBounds(190, 50, 120, 30);
		artifactGroupInput.setVisible(false);
		p2.add(artifactGroupInput);
		
		artifactName.setBounds(50, 100, 120, 30);
		artifactName.setVisible(false);
		p2.add(artifactName);
		
		
		artifactInput.setBounds(190, 100, 120, 30);
		artifactInput.setVisible(false);
		p2.add(artifactInput);
		
		selectLayerName.setBounds(50, 150, 120, 30);
		selectLayerName.setVisible(false);
		p2.add(selectLayerName);
		
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
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			while(rs.next()){
				String lay = rs.getString("layers");
				arrayList.add(lay);
				}
			
			layerList.setModel(new DefaultComboBoxModel(arrayList.toArray()));
			layerList.setBounds(190, 150, 120, 30);
			layerList.setVisible(false);
			p2.add(layerList);
			
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		
		submit.setBounds(80, 300, 80, 30);
		submit.setVisible(false);
		submit.addActionListener(this);
		p2.add(submit);
		
		change.setBounds(80, 250, 80, 30);
		change.setVisible(false);
		change.addActionListener(this);
		p2.add(change);
		
		artifactRelationFrom.setBounds(50,200,120,30);
		artifactRelationFrom.setVisible(false);
		p2.add(artifactRelationFrom);
		
			try {
			
			Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
			Statement st = con.createStatement();
			String q = "SELECT * FROM artifact;";
			ResultSet rs = st.executeQuery(q);
			

			
			while(rs.next()){
				count ++;
				String artifactTable = rs.getString("artifact");
				artifactList.add(artifactTable);
			}
			String oneArtifact ="From " ;
			for(int i=0;i<artifactList.size();i++){
				oneArtifact = oneArtifact +artifactList.get(i)+",";
			}
			artifactRelationFrom.setText(oneArtifact.toString());
			
	}catch (SQLException e1) {
		
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
		relationFromInput.setBounds(190, 200, 120, 30);
		relationFromInput.setVisible(false);
		p2.add(relationFromInput);
		
		String set = "To ";
		for(int i=0;i<artifactList.size();i++){
			set = set +artifactList.get(i)+",";
		}
		
		artifactRelationTo.setBounds(50,250,120,30);
		artifactRelationTo.setVisible(false);
		p2.add(artifactRelationTo);
		
		relationToInput.setBounds(190, 250, 120, 30);
		relationToInput.setVisible(false);
		p2.add(relationToInput);
		
		artifactRelationTo.setText(set.toString());
		
		search.setBounds(50, 100, 120, 30);
		search.setVisible(false);
		search.addActionListener(this);
		p2.add(search);
		
		artifactsSet.setBounds(50, 150, 120, 30);
		artifactsSet.setVisible(false);
		p2.add(artifactsSet);
		
		Layer.setBounds(50, 200, 120, 30);
		Layer.setVisible(false);
		p2.add(Layer);
		
		remove.setBounds(50, 100, 120, 30);
		remove.setVisible(false);
		remove.addActionListener(this);
		p2.add(remove);
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==create){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			
			artifactGroupName.setVisible(true);
			artifactName.setVisible(true);
			selectLayerName.setVisible(true);
			artifactInput.setVisible(true);
			artifactGroupInput.setVisible(true);
			layerList.setVisible(true);
			submit.setVisible(true);
			artifactRelationFrom.setVisible(true);
			relationFromInput.setVisible(true);
			artifactRelationTo.setVisible(true);
			relationToInput.setVisible(true);
			
		}
		
		else if(e.getSource()== submit){
			
			int t=0;
			String agroupp = artifactGroupInput.getText().toString();
			String arti = artifactInput.getText().toString();
			String lay = (String) layerList.getSelectedItem();
			String relfrom1 = relationFromInput.getText().toString();
			String relto1 = relationToInput.getText().toString();
			
			char[] relfrom = relfrom1.toCharArray(); 
			char[] relto = relto1.toCharArray();
			
			if(relfrom1.length()!=count && relto1.length()!=count){
				JOptionPane.showMessageDialog(f, "The relation entered does not match");
			}
			else{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			try {
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				Statement st = con.createStatement();
				String q = "SELECT * FROM artifactgroup;";
				ResultSet rs = st.executeQuery(q);
				
				//ArrayList<String> arrayList = new ArrayList<String>();
				
				while(rs.next()){
					String lay1 = rs.getString("agroup");
					System.out.println(lay1);
					if(lay.equals(agroupp)){
						t=1;
					}
				}
				
				if(t!=0){
					JOptionPane.showMessageDialog(f, "The specified artifact group already exist!");
				}
				else{
					
					String query = "INSERT INTO artifactGroup (aGroup,layer)"+ "values(?,?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, agroupp);
					ps.setString(2, lay);
					ps.execute();
					
					query = "INSERT INTO artifact (artifact,aGroup,layer)"+ "values(?,?,?)";
					ps = con.prepareStatement(query);
					ps.setString(1, arti);
					ps.setString(2, agroupp);
					ps.setString(3, lay);
					ps.execute();
					
					for (String Element : artifactList){
						int i = artifactList.indexOf(Element);
						String c = Character.toString(relfrom[i]);
						
						query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1, arti);
						ps.setString(2, Element);
						ps.setString(3, c);
						ps.execute();
						
						
					}
					
					for (String Element : artifactList){
						int i = artifactList.indexOf(Element);
						String c = Character.toString(relto[i]);
						
						query = "INSERT INTO relation(artifactFrom,artifactTo,rel)"+"values(?,?,?)";
						ps = con.prepareStatement(query);
						ps.setString(1, Element);
						ps.setString(2, arti);
						ps.setString(3, c);
						ps.execute();
						
						
					}
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}
		}
		
		else if(e.getSource()==read){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			
			artifactGroupName.setVisible(true);
			artifactGroupInput.setVisible(true);
			search.setVisible(true);
			artifactsSet.setVisible(true);
			Layer.setVisible(true);
		}
		
		else if(e.getSource()==search){
			
			
			String search = artifactGroupInput.getText().toString();
			
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
				
				String layerr = null;
				ArrayList<String> artifact = new ArrayList<String>();
				
				while(rs.next()){
					String lay = rs.getString("layer");
					String group1 = rs.getString("agroup");
					String artifact1 = rs.getString("artifact");
					if (group1.equals(search)){
						t=1;
						layerr =  lay;
						artifact.add(artifact1);
					}
					
					
				}
				if(t==0){
					JOptionPane.showMessageDialog(f, "The Artifact Group does not exist");
					
				}
				else{
				String g = "The layer is "+layerr;
				String oneArtifact ="" ;
				for(int i=0;i<artifact.size();i++){
					oneArtifact = oneArtifact +artifact.get(i)+",";
				}
				
				artifactsSet.setText(g.toString());
				Layer.setText(oneArtifact.toString());
				}
				
		}catch (SQLException e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		}
		
		else if(e.getSource()==delete){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			
			artifactGroupName.setVisible(true);
			artifactGroupInput.setVisible(true);
			remove.setVisible(true);
			
		}
		
		else if(e.getSource()==remove){
			
			String del = artifactGroupInput.getText().toString();
			
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
				
				ArrayList<String> deleteArtifact = new ArrayList<String>();
				
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION) {
				      
				    } else if (response == JOptionPane.YES_OPTION) {
				    	
				 String q = "SELECT * FROM artifact;";
				ResultSet rs = s.executeQuery(q);
				
				while(rs.next()){
					String artifat  = rs.getString("artifact");
					String grp = rs.getString("aGroup");
					if(grp.equals(del)){
						deleteArtifact.add(artifat);
					}
					}	
				    	
				String sql = "DELETE FROM artifactGroup WHERE aGroup='"+del+"'";
				s.executeUpdate(sql);
				
				sql = "DELETE FROM artifact WHERE aGroup='"+del+"'";
				s.executeUpdate(sql);
				
				for(String delElement : deleteArtifact){
					q = "DELETE FROM relation WHERE artifactFrom='"+delElement+"' OR artifactTo='"+delElement+"';";
					 s.executeUpdate(q);
				}
				
				    }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			
			
		}
		
		else if(e.getSource()==update){
			create.setVisible(false);
			read.setVisible(false);
			update.setVisible(false);
			delete.setVisible(false);
			
			artifactGroupName.setVisible(true);
			artifactGroupInput.setVisible(true);
			selectLayerName.setVisible(true);
			layerList.setVisible(true);
			change.setVisible(true);
		}
		
		else if(e.getSource()==change){
			
			String groupIn = artifactGroupInput.getText().toString();
			String lay = (String) layerList.getSelectedItem();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Connection con=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/lay_at","root","root");
				
				Statement st = con.createStatement();
				
				String q = "UPDATE artifactgroup SET layer=? WHERE aGroup=?;";
				PreparedStatement preparedStmt = con.prepareStatement(q);
				 preparedStmt.setString(1,lay );
				 preparedStmt.setString(2,groupIn );
				 
				 preparedStmt.executeUpdate();
				 
				 q = "SELECT * FROM artifactgroup";
				 ResultSet rs = st.executeQuery(q);
				 
				 while(rs.next()){
						String group1 = rs.getString("agroup");
						if (groupIn.equals(group1)){
							q = "UPDATE artifact SET layer=? WHERE aGroup=?;";
							preparedStmt = con.prepareStatement(q);
							 preparedStmt.setString(1,lay );
							 preparedStmt.setString(2,groupIn );
							 
							 preparedStmt.executeUpdate();
						}
					}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
	}
	

}
