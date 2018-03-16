package persistence;

import javafx.scene.paint.Color;

public class ColorConverter {

	public String converterColor(Color color) {
		return colorChanelToHex(color.getRed())
				+ colorChanelToHex(color.getGreen())
				+ colorChanelToHex(color.getBlue())
				+ colorChanelToHex(color.getOpacity());
	}

	private String colorChanelToHex(double chanelValue) {
		String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
		if (rtn.length() == 1) {
			rtn = "0" + rtn;
		}
		return rtn;
	}
}
