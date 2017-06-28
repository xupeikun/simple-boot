package pri.xpk.Service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xpk on 2017/5/2.
 */
@Slf4j
@Transactional(readOnly = true)
public class BaseService {

    /***
     * 创建分页请求
     *  @param
     *
     */
    protected PageRequest createPageRequest(int pageSize , int pageNumber ,String sortType , boolean asc  ) {
        Sort sort = createSort(sortType, asc);
        return new PageRequest(pageNumber - 1, pageSize, sort);
    }

    /**
     *
     * @param sortType
     *            排序字段 默认：id 支持按多字段排序 "id:asc,name:desc" "id,name:desc"
     * @param asc
     *            是否升序
     * @return
     */
    protected Sort createSort(String sortType, boolean asc) {
        Sort sort = null;
        Sort.Direction defaultDirection = asc ? Sort.Direction.ASC : Sort.Direction.DESC;

        // 设置默认值
        if (StringUtils.isBlank(sortType)) {
            sortType = "id";
        }

        List<Sort.Order> orders = Lists.newArrayList();
        Sort.Direction otherDirection;
        for (String sortStr : sortType.split(",")) {
            String sortAndDirection[] = sortStr.split(":");
            if (sortAndDirection.length < 1) {
                continue;
            }
            if (sortAndDirection.length == 2) {
                otherDirection = sortAndDirection[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC
                        : Sort.Direction.DESC;
            } else {
                otherDirection = defaultDirection;
            }
            orders.add(new Sort.Order(otherDirection, sortAndDirection[0]));
        }
        sort = new Sort(orders);
        return sort;
    }




}
