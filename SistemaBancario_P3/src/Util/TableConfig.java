package Util;

import javafx.scene.control.TreeTableColumn;

public class TableConfig {
    public static void setConfigCol(TreeTableColumn col,int minWidth){
        col.setResizable(false);
        col.setMinWidth(minWidth);
        col.setEditable(false);
        col.setReorderable(false);
    }
    public static void setConfigCol(TreeTableColumn col){
        setConfigCol( col,200);
    }
}
