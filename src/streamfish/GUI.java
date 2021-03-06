package streamfish;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Kristian
 */
public class GUI extends JFrame implements WindowListener {

	private ArrayList<JPanel> panels = new ArrayList<JPanel>();
	private StreamFish db;

	public GUI() {
		db = new StreamFish();
		addWindowListener(this);

		MainMenu mainMenu = new MainMenu(this);

		panels.add(mainMenu);
		add(mainMenu);
		System.out.println(mainMenu);
		mainMenu.setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		pack();
		


	}

	public void byttVindu(JPanel remove, JPanel add) {
		remove(remove);
		add(add);
		pack();
	}

	public void byttVindu(JPanel remove, String newWindow) {
		remove(remove);
		for (JPanel jp : panels) {
			System.out.println(jp.getClass().getName());
			if (jp.getClass().getName().equals(newWindow)) {
				add(jp);
			}
		}
		pack();
	}
	
	public boolean registerOrder(Order order){
		int suc = db.addOrder(order);
		if(suc>0){
			return true;
		}
		return false;
	}
	
	public boolean registerCustomer(Customer customer) {
		int suc = db.addCustomer(customer);
		if (suc > 0) {
			MainMenu main = (MainMenu) panels.get(0);
			main.updt();
			return true;
		}
		return false;
	}
	

	public Customer[] getCustomers(String s) {
		return db.getCustomers(s);
	}

	public Menu[] getMenus() {
		return db.getMenus();
	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		db.close();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}