package mvc.service;

import mvc.dao.pojo.ResourcesType;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
public interface ResourcesService {

    /**
     * 获取资源类型列表
     * @return
     */
    List<ResourcesType> getList();

    /**
     * 保存或添加新的资源类型
     * @return
     */
    ResourcesType save(ResourcesType resourcesType);

    /**
     * 删除资源类型
     * @return
     */
    int delete(ResourcesType resourcesType);

}
