package persistence;

import org.csu.mypetstore.domain.Product;

import java.util.List;

public interface ProductDAO {
    //通过点击上级目录的id去进行访问上级的所有下级
    List<Product> getProductListByCategory(String categoryId);
//
    //通过自己的Id去进行访问自己的所有内容
    Product getProduct(String productId);

    //通过一些关键字
    List<Product> searchProductList(String keywords);


}
