package com.kartikexample.lnmlibrary.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<String> ITEMS = new ArrayList<String>();
    public static List<String> SELECT_ITEMS = new ArrayList<String>();
    public static List<String> ITEMS_TRANSAC = new ArrayList<String>();
    //public static List<String> ITEMS = new ArrayList<String>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, String> ITEM_MAP = new HashMap<String, String>();

   /* static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Item 1"));
        addItem(new DummyItem("2", "Item 2"));
        addItem(new DummyItem("3", "Item 3"));
    }*/

    public static void addItem(String details, String title) {
        ITEMS.add(details);
        SELECT_ITEMS.add(title);
        //ITEM_MAP.put(item.id, item);
    }

    public static void addItem_Transaction(String details) {
        ITEMS_TRANSAC.add(details);
        //SELECT_ITEMS.add(title);
        //ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
