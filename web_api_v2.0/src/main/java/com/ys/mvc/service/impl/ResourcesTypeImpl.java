package com.ys.mvc.service.impl;

import com.ys.mvc.dao.mapper.ResourcesTypeMapper;
import com.ys.mvc.dao.pojo.ResourcesType;
import com.ys.mvc.service.ResourcesService;
import com.ys.utils.tools.Lang;
import com.ys.utils.tools.LocalFile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@Service
public class ResourcesTypeImpl implements ResourcesService {

    private static Logger log = Logger.getLogger(ResourcesTypeImpl.class);

    private final ResourcesTypeMapper resourcesTypeMapper;

    private final LocalFile localFile;

    public ResourcesTypeImpl(ResourcesTypeMapper resourcesTypeMapper, LocalFile localFile) {
        this.resourcesTypeMapper = resourcesTypeMapper;
        this.localFile = localFile;
    }

    /**
     * 获取资源类型列表
     *
     * @return
     */
    public List<ResourcesType> getList() {
        return resourcesTypeMapper.getList();
    }

    /**
     * 保存或添加新的资源类型
     *
     * @param resourcesType
     * @return
     */
    public ResourcesType save(ResourcesType resourcesType) {
        ResourcesType oldResourcesType = resourcesTypeMapper.findById(resourcesType);

        if(oldResourcesType == null){
            resourcesType.setId(Lang.getUUID());
            resourcesType.setUpdateTime(new Date());
            resourcesTypeMapper.save(resourcesType);

        }else{
            String oldCover = oldResourcesType.getCover();

            oldResourcesType.setName(resourcesType.getName());
            oldResourcesType.setCover(resourcesType.getCover());
            oldResourcesType.setUpdateTime(new Date());
            resourcesTypeMapper.update(resourcesType);

            //更新成功,如果是选择了新的封面，则删除旧的封面文件
            if(!oldResourcesType.equals(resourcesType.getCover())){
                localFile.removeLocalFile(oldCover, 4);
            }
        }

        return resourcesType;
    }

    /**
     * 删除资源类型
     *
     * @param resourcesType
     * @return
     */
    public int delete(ResourcesType resourcesType) {
        ResourcesType oldResourcesType = resourcesTypeMapper.findById(resourcesType);
        int line = resourcesTypeMapper.delete(resourcesType);
        if(line == 1){
            localFile.removeLocalFile(oldResourcesType.getCover(), 4);
        }
        return line;
    }
}
