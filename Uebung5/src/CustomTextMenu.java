import lejos.nxt.LCD;
import lejos.util.TextMenu;

public class CustomTextMenu extends TextMenu {
	
	private int leftAlign = 0;
	
	public CustomTextMenu(String[] items) {
		super(items);
		// TODO Auto-generated constructor stub
	}

	public CustomTextMenu(String[] items, int topRow) {
		super(items, topRow);
		// TODO Auto-generated constructor stub
	}

	public CustomTextMenu(String[] items, int topRow, String title) {
		super(items, topRow, title);
		// TODO Auto-generated constructor stub
	}
	
	public CustomTextMenu(String[] items, int topRow, int leftAlign, String title ) {
		super(items, topRow, title);
		this.leftAlign = leftAlign;
	}
	
	/**
	 * helper method used by select()
	 */
	protected void display(int selectedIndex, int topIndex)
	{
		//LCD.asyncRefreshWait();
		if(_title != null)
			LCD.drawString(_title, leftAlign, _topRow - 1);
		int max = _topRow + _height;
		for (int i = _topRow; i < max; i++)
		{
			LCD.drawString(BLANK, leftAlign, i);
			int idx = i - _topRow + topIndex;
			if (idx >= 0 && idx < _length)
			{
				LCD.drawChar(idx == selectedIndex ? SEL_CHAR : ' ', leftAlign, i);
				LCD.drawString(_items[idx], leftAlign + 1, i);
			}
		}
		LCD.asyncRefresh();
	}

}
