package persistence;

import org.csu.mypetstore.domain.Category;

import java.util.List;

public interface CategoryDAO {

//含有三个属性通过categoryId去进行访问其他数据
//获取所有打雷集合返回
    List<Category> getCategoryList();
//悬着id打雷
    Category getCategory(String categoryId);

}
