package persistence;

import org.csu.mypetstore.domain.Item;

import java.util.List;
import java.util.Map;

public interface ItemDAO {
    void updateInventoryQuantity(Map<String, Object> param);

   //通过itemId得到得到它的库存量
    int getInventoryQuantity(String itemId);

    //通过一个productId即在点击主页进去的时候显示的RP-SN -1找到一些对应的Item
    List<Item> getItemListByProduct(String productId);

    //通过ItemId去找到这个Item
    Item getItem(String itemId);


}
