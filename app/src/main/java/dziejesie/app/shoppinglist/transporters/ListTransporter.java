package dziejesie.app.shoppinglist.transporters;


import com.google.firebase.firestore.DocumentSnapshot;

import dziejesie.app.shoppinglist.models.ShoppingList;

public class ListTransporter {
    private static ShoppingList list;
    private static DocumentSnapshot snapshot;

    public static ShoppingList getList() {
        return list;
    }

    public static void setList(ShoppingList list) {
        ListTransporter.list = list;
    }

    public static DocumentSnapshot getSnapshot() {
        return snapshot;
    }

    public static void setSnapshot(DocumentSnapshot snapshot) {
        ListTransporter.snapshot = snapshot;
    }
}
