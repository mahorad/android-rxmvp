package mahorad.com.android_rxmvp.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Model {

    private static final List<Item> items = new ArrayList<Item>();

    private static final Map<String, Item> itemsMap = new HashMap<String, Item>();

    private static Item selected;
    private static PublishSubject<Item> selectedObservable = PublishSubject.create();

    private static final int COUNT = 3;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createItem(i));
        }
        selected = items.get(0);
    }

    public static Observable<Item> getItemsObservable() {
        return Observable
                .interval(3000L, MILLISECONDS)
                .timeInterval()
                .take(27)
                .map(i -> {
                    int position = getSize() + 1;
                    Item item = createItem(position);
                    addItem(item);
                    return item;
                });
    }

    private static void addItem(Item item) {
        items.add(item);
        itemsMap.put(item.id, item);
    }

    private static Item createItem(int position) {
        return new Item(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position + "\n");
        for (int i = 0; i < position; i++) {
            builder.append("\ndetails ");
        }
        return builder.toString();
    }

    public static Item getById(String itemId) {
        return itemsMap.get(itemId);
    }

    public static Item getByPosition(int index) {
        return items.get(index);
    }

    public static int getSize() {
        return items.size();
    }

    public static Item getSelected() {
        return selected;
    }

    public static void setSelected(Item item) {
        if (!items.contains(item)) return;
        selected = item;
        selectedObservable.onNext(item);
    }

    public static Observable<Item> getSelectedObservable() {
        return selectedObservable.hide();
    }
}
