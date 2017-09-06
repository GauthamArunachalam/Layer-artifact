package layer_artifact;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home implements ActionListener {
	
	JFrame f;
	JPanel p1,p2;
	JButton b1,b2,b3,b4;
	
	Home(){
		f = new JFrame();
		p1 = new JPanel();
		p2 = new JPanel();
		b1 = new JButton("LAYER");
		b2 = new JButton("Artifact Group");
		b3 = new JButton("Artifact");
		b4 = new JButton("Switch");
	}
	
	public void dis(){
		f.setSize(1800, 1000);
		f.setVisible(true);
		f.setLayout(null);
		
		p1.setBounds(0, 150, 200, 400);
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p1.setVisible(true);
		f.add(p1);
		
		b1.setSize(80, 100);
		b2.setSize(80, 100);
		b3.setSize(80, 100);
		b4.setSize(80, 100);
		
		b1.setFont(new Font("tahoma", Font.BOLD, 20));
		b2.setFont(new Font("tahoma", Font.BOLD, 20));
		b3.setFont(new Font("tahoma", Font.BOLD, 20));
		b4.setFont(new Font("tahoma", Font.BOLD, 20));
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		
		p2.setBounds(450,150, 1000, 1000);
		p2.setVisible(false);
		p2.setLayout(null);
		f.add(p2);
	
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==b1){
			p2.setVisible(true);
			f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			Layer pas = new Layer();
			pas.dis();
			
		}
		
		else if(e.getSource()==b2){
			p2.setVisible(true);
			f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			ArtifactGroup ag = new ArtifactGroup();
			ag.dis();
		}
		
		else if(e.getSource()==b3){
			p2.setVisible(true);
			f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			Artifact a = new Artifact();
			a.dis();
		}
		
		else if(e.getSource()==b4){
			p2.setVisible(true);
			f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
			Switch s = new Switch();
			//s.dis();
		}
		
	}

}
