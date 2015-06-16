package pb138.rss.gui.renderer;

import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Drimal
 */
public class CheckboxSelectionModel extends DefaultListSelectionModel {

    private int i0 = -1;
    private int i1 = -1;

    public void setSelectionInterval(int index0, int index1) {
        if (i0 == index0 && i1 == index1) {
            if (getValueIsAdjusting()) {
                setValueIsAdjusting(false);
                setSelection(index0, index1);
            }
        } else {
            i0 = index0;
            i1 = index1;
            setValueIsAdjusting(false);
            setSelection(index0, index1);
        }
    }

    private void setSelection(int index0, int index1) {
        if (super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
        } else {
            super.addSelectionInterval(index0, index1);
        }
    }
}
